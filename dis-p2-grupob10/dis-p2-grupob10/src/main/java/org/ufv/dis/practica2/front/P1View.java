package org.ufv.dis.practica2.front;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.ufv.dis.practica2.Data1;
import org.ufv.dis.practica2.Data2;
import org.ufv.dis.practica2.back.LeerJson1;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@PageTitle("Datos")
@Route(value = "", layout = MainView.class)

public class P1View extends VerticalLayout {


    Grid<Data1> grid=new Grid<>(Data1.class);
    FormularioDatos1 form;

    public P1View() {

        addClassName("List-view");
        setSizeFull();

        configureGrid(); //Grid de datos
        configureForm(); //Grid formulario

        add(
                getToolbar(),
                getContent()
        );

        cerrarEditor();

    }

    private void cerrarEditor() {
        form.setData1(null);
        form.setVisible(false);
        removeClassName("editing");
    }


    private Component getContent() {
        HorizontalLayout content= new HorizontalLayout(grid,form);
        content.setFlexGrow(2,grid);
        content.setFlexGrow(2,form);
        content.addClassName("contect");
        content.setSizeFull();
        return content;
    }

    //FORMULARIO
    private void configureForm() {

        form= new FormularioDatos1(Collections.emptyList());
        form.setWidth("25em");
        form.addListener(FormularioDatos1.GuardarEvent.class, this::guardarDatos1);
        //form.addListener(FormularioDatos1.GuardarEvent.class, e -> guardarDatos1());
        form.addListener(FormularioDatos1.CerrarEvent.class, e -> cerrarEditor());

    }



    private void guardarDatos1(FormularioDatos1.GuardarEvent event) {
        if(event.getDatos1().getCodigo_geometria() == -1){
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForEntity("http://localhost:8080/PostData1", event.getDatos1(), Data1.class);
            cerrarEditor();
            configureGrid();
        }else{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.put("http://localhost:8080/PutData1", event.getDatos1());
            cerrarEditor();
            configureGrid();
        }

    }

    private Component getToolbar() { // BUSCADOR arriba a la izq

        Button addDatos1= new Button("Añadir datos");
        addDatos1.addClickListener(e -> addDatos1());

        HorizontalLayout toolbar=new HorizontalLayout(addDatos1);
        toolbar.addClassName("Toolbar");

        return toolbar;
    }

    private void addDatos1() {
        grid.asSingleSelect().clear();
        editData1(new Data1());
    }

    private void configureGrid() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArrayList<Data1>> response = restTemplate.exchange("http://localhost:8080/GetData1", HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Data1>>() {});
        ArrayList<Data1> DatosMenores60 = response.getBody();


        grid.addClassName("contact-grid");
        grid.setSizeFull();

        //Nombre columnas Datos1 -> Covid19-TIA_ZonasBásicasSalud.json = ZONA MENORES 60

        grid.setColumns("codigo_geometria","zona_basica_salud","tasa_incidencia_acumulada_ultimos_14dias","tasa_incidencia_acumulada_total","casos_confirmados_totales","casos_confirmados_ultimos_14dias","fecha_informe");
        grid.setItems(DatosMenores60);


        grid.asSingleSelect().addValueChangeListener(e -> editData1(e.getValue()));
    }

    private void editData1(Data1 datos1) {
        if(datos1 == null){
            cerrarEditor();
        }else{
            form.setData1(datos1);
            form.setVisible(true);
            addClassName("editing");

        }
    }


}
