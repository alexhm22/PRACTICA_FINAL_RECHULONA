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
import org.ufv.dis.practica2.Data2;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FormularioDatos2 extends FormLayout {
    Binder<Data2> binder = new BeanValidationBinder<>(Data2.class);

    TextField zona_basica_salud = new TextField("zona_basica_salud");
    NumberField tasa_incidencia_acumulada_P60mas_ultimos_14dias = new NumberField("tasa_incidencia_acumulada_P60mas_ultimos_14dias");
    NumberField casos_confirmados_P60mas_ultimos_14dias = new NumberField("casos_confirmados_P60mas_ultimos_14dias");
    DateTimePicker fecha_informe= new DateTimePicker("fecha_informe");

    ComboBox<Data2> data2= new ComboBox<>("Data2");

    Button guardar = new Button("Guardar");
    Button cerrar = new Button("Cerrar");
    private Data2 datos2;


    public FormularioDatos2(List<Data2> dat){
        addClassName("contact-form");

        binder.forField(zona_basica_salud)
                .bind(Data2::getZona_basica_salud, Data2::setZona_basica_salud);

        FormularioDatos2.DoubleToFloatConverter doubleToFloatConverter = new FormularioDatos2.DoubleToFloatConverter();
        binder.forField(tasa_incidencia_acumulada_P60mas_ultimos_14dias)
                .withConverter(doubleToFloatConverter)
                .bind(Data2::getTasa_incidencia_acumulada_P60mas_ultimos_14dias, Data2::setTasa_incidencia_acumulada_P60mas_ultimos_14dias);

        binder.forField(casos_confirmados_P60mas_ultimos_14dias)
                .withConverter(doubleToFloatConverter)
                .bind(Data2::getCasos_confirmados_P60mas_ultimos_14dias, Data2::setCasos_confirmados_P60mas_ultimos_14dias);

        FormularioDatos2.LocalDateToStringConverter localDateToStringConverter = new FormularioDatos2.LocalDateToStringConverter("dd/MM/yyyy HH:mm:ss");
        binder.forField(fecha_informe)
                .withConverter(localDateToStringConverter)
                .bind(Data2::getFecha_informe, Data2::setFecha_informe);

        data2.setItems(dat);

        add(
                zona_basica_salud,
                tasa_incidencia_acumulada_P60mas_ultimos_14dias,
                casos_confirmados_P60mas_ultimos_14dias,
                fecha_informe,
                createButtonLayout()
        );
    }
    public void setData2(Data2 datos2){
        this.datos2 = datos2;
        binder.readBean(datos2);
    }
    private Component createButtonLayout() {
        guardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cerrar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        guardar.addClickListener(event -> validaryguardar());
        cerrar.addClickListener(event -> fireEvent(new FormularioDatos2.CerrarEvent(this)));

        guardar.addClickShortcut(Key.ENTER);
        cerrar.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(guardar,cerrar);
    }



    private void validaryguardar() {
        try{
            binder.writeBean(datos2);
            fireEvent(new FormularioDatos2.GuardarEvent(this, datos2));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    public static abstract class FormularioDatos2Event extends ComponentEvent<FormularioDatos2>{
        private Data2 datos2;

        protected FormularioDatos2Event(FormularioDatos2 source, Data2 datos2){
            super(source, false);
            this.datos2 = datos2;
        }
        public Data2 getDatos2(){
            return datos2;
        }
    }

    public static class GuardarEvent extends FormularioDatos2.FormularioDatos2Event {
        GuardarEvent(FormularioDatos2 source, Data2 datos2){
            super(source, datos2);
        }
    }
    public static class CerrarEvent extends FormularioDatos2.FormularioDatos2Event {
        CerrarEvent(FormularioDatos2 source){
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
