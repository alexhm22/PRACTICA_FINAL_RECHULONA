package org.ufv.dis.practica2.front;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import org.ufv.dis.practica2.Data1;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FormularioDatos1 extends FormLayout {

    Binder<Data1> binder = new BeanValidationBinder<>(Data1.class);

    TextField zona_basica_salud= new TextField("zona_basica_salud");

    NumberField tasa_incidencia_acumulada_ultimos_14dias= new NumberField("tasa_incidencia_acumulada_ultimos_14dias");
    NumberField tasa_incidencia_acumulada_total= new NumberField("tasa_incidencia_acumulada_total");
    NumberField casos_confirmados_totales= new NumberField("casos_confirmados_totales");
    NumberField casos_confirmados_ultimos_14dias= new NumberField("casos_confirmados_ultimos_14dias");
    DateTimePicker fecha_informe= new DateTimePicker("fecha_informe");

    Button guardar= new Button("Guardar");
    Button cerrar= new Button("Cerrar");




    ComboBox<Data1> data1= new ComboBox<>("Data1");
    private Data1 datos1;

    public FormularioDatos1(List<Data1> dat){

        addClassName("Formulario-data1");

        binder.forField(zona_basica_salud)
                .bind(Data1::getZona_basica_salud, Data1::setZona_basica_salud);

        DoubleToFloatConverter doubleToFloatConverter = new DoubleToFloatConverter();
        binder.forField(tasa_incidencia_acumulada_ultimos_14dias)
                .withConverter(doubleToFloatConverter)
                .bind(Data1::getTasa_incidencia_acumulada_ultimos_14dias, Data1::setTasa_incidencia_acumulada_ultimos_14dias);
        binder.forField(tasa_incidencia_acumulada_total)
                .withConverter(doubleToFloatConverter)
                .bind(Data1::getTasa_incidencia_acumulada_total, Data1::setTasa_incidencia_acumulada_total);

        DoubleToIntegerConverter doubleToIntegerConverter = new DoubleToIntegerConverter();
        binder.forField(casos_confirmados_totales)
                .withConverter(doubleToIntegerConverter)
                .bind(Data1::getCasos_confirmados_totales, Data1::setCasos_confirmados_totales);
        binder.forField(casos_confirmados_ultimos_14dias)
                .withConverter(doubleToIntegerConverter)
                .bind(Data1::getCasos_confirmados_ultimos_14dias, Data1::setCasos_confirmados_ultimos_14dias);

        LocalDateToStringConverter localDateToStringConverter = new LocalDateToStringConverter("dd/MM/yyyy HH:mm:ss");
        binder.forField(fecha_informe)
                .withConverter(localDateToStringConverter)
                .bind(Data1::getFecha_informe, Data1::setFecha_informe);

        data1.setItems(dat);
        //data1.setItemLabelGenerator(Data1::getCodigo_geometria);
        add(
                zona_basica_salud,
                tasa_incidencia_acumulada_ultimos_14dias,
                tasa_incidencia_acumulada_total,
                casos_confirmados_totales,
                casos_confirmados_ultimos_14dias,
                fecha_informe,

                createButtonLayout()

        );

    }

    public void setData1(Data1 datos1){
        this.datos1 = datos1;
        binder.readBean(datos1);
    }
    private Component createButtonLayout() {
    
        guardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cerrar.addThemeVariants(ButtonVariant.LUMO_ERROR);

        guardar.addClickListener(event -> validaryguardar());
        cerrar.addClickListener(event -> fireEvent(new CerrarEvent(this)));

        guardar.addClickShortcut(Key.ENTER);
        cerrar.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(guardar,cerrar);

    }

    private void validaryguardar() {
        try{
            binder.writeBean(datos1);
            fireEvent(new GuardarEvent(this, datos1));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static abstract class FormularioDatos1Event extends ComponentEvent<FormularioDatos1>{
        private Data1 datos1;

        protected FormularioDatos1Event(FormularioDatos1 source, Data1 datos1){
            super(source, false);
            this.datos1 = datos1;
        }
        public Data1 getDatos1(){
            return datos1;
        }
    }

    public static class GuardarEvent extends FormularioDatos1Event{
        GuardarEvent(FormularioDatos1 source, Data1 datos1){
            super(source, datos1);
        }
    }
    public static class CerrarEvent extends FormularioDatos1Event{
        CerrarEvent(FormularioDatos1 source){
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>>Registration addListener(Class<T> eventType, ComponentEventListener<T> listener){
        return getEventBus().addListener(eventType, listener);
    }

    public class DoubleToFloatConverter implements Converter<Double, Float> {

        @Override
        public Result<Float> convertToModel(Double value, ValueContext context) {
            if (value == null) {
                return Result.ok(null);
            }
            return Result.ok(value.floatValue());
        }

        @Override
        public Double convertToPresentation(Float value, ValueContext context) {
            if (value == null) {
                return null;
            }
            return value.doubleValue();
        }
    }

    public class DoubleToIntegerConverter implements Converter<Double, Integer> {
        @Override
        public Result<Integer> convertToModel(Double value, ValueContext context) {

            if (value == null) {
                return Result.ok(null);
            }
            return Result.ok(value.intValue());
        }

        @Override
        public Double convertToPresentation(Integer value, ValueContext context) {
            return (double) value;
        }
    }


    public class LocalDateToStringConverter implements Converter<LocalDateTime, String> {

        private DateTimeFormatter formatter;

        public LocalDateToStringConverter(String pattern) {
            formatter = DateTimeFormatter.ofPattern(pattern);
        }

        @Override
        public Result<String> convertToModel(LocalDateTime value, ValueContext context) {
            if (value == null) {
                return Result.ok(null);
            }
            return Result.ok(value.format(formatter));
        }

        @Override
        public LocalDateTime convertToPresentation(String value, ValueContext context) {
            if (value == null) {
                return null;
            }
            return LocalDateTime.parse(value, formatter);
        }
    }




}
