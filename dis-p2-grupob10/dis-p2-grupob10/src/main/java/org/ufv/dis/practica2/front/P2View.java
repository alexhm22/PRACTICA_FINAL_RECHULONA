package org.ufv.dis.practica2.front;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vaadin.flow.component.Component;
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


@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@PageTitle("Datos")
@Route(value = "P2",layout = MainView.class)



public class P2View extends VerticalLayout {

    Grid<Data2> grid=new Grid<>(Data2.class);

    FormularioDatos2 form;
    public P2View() {

        addClassName("List-view");
        setSizeFull();

        configureGrid(); //Grid de datos
        configureForm();

        add(
                getContent()
        );

        cerrarEditor();
    }
    private void cerrarEditor() {
        form.setData2(null);
        form.setVisible(false);
        removeClassName("editing");
    }
    private void configureForm() {
        form = new FormularioDatos2(Collections.emptyList());
        form.setWidth("25em");
        form.addListener(FormularioDatos2.GuardarEvent.class, this::guardarDatos2);
        form.addListener(FormularioDatos2.CerrarEvent.class, e -> cerrarEditor());
    }

    //COMPONENTES PESTAÑA 2
    private Component getContent() {
        HorizontalLayout content= new HorizontalLayout(grid, form); // (grid, form)
        content.setFlexGrow(2,grid);
        content.setFlexGrow(1,form);// // (2, form)
        content.addClassName("contect");
        content.setSizeFull();
        return content;
    }

    private void guardarDatos2(FormularioDatos2.GuardarEvent event) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put("http://localhost:8080/PutData2", event.getDatos2());

        cerrarEditor();
        configureGrid();
    }


    private void configureGrid() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArrayList<Data2>> response = restTemplate.exchange("http://localhost:8080/GetData2", HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Data2>>() {});
        ArrayList<Data2> DatosMayores60 = response.getBody();


        grid.addClassName("contact-grid");
        grid.setSizeFull();

        grid.setColumns("codigo_geometria","zona_basica_salud","tasa_incidencia_acumulada_P60mas_ultimos_14dias","casos_confirmados_P60mas_ultimos_14dias","fecha_informe");
        grid.setItems(DatosMayores60);
        grid.getColumns().forEach(col->col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(e -> editData2(e.getValue()));
    }


    private void editData2(Data2 datos2) {
        if(datos2 == null){
            cerrarEditor();
        }else{
            form.setData2(datos2);
            form.setVisible(true);
            addClassName("editing");

        }
    }

}