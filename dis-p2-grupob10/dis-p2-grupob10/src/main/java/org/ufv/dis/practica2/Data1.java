package org.ufv.dis.practica2;


import org.apache.http.entity.AbstractHttpEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class Data1 {

    private  int id;
    private int codigo_geometria;
    private String zona_basica_salud;
    private Float tasa_incidencia_acumulada_ultimos_14dias;
    private Float tasa_incidencia_acumulada_total;
    private int casos_confirmados_totales;
    private int casos_confirmados_ultimos_14dias;
    private String fecha_informe;


    //Constructor
    public Data1(int codigo_geometria, String zona_basica_salud, Float tasa_incidencia_acumulada_ultimos_14dias, Float tasa_incidencia_acumulada_total, int casos_confirmados_totales, int casos_confirmados_ultimos_14dias, String fecha_informe) {

        this.codigo_geometria = codigo_geometria;
        this.zona_basica_salud = zona_basica_salud;
        this.tasa_incidencia_acumulada_ultimos_14dias = tasa_incidencia_acumulada_ultimos_14dias;
        this.tasa_incidencia_acumulada_total = tasa_incidencia_acumulada_total;
        this.casos_confirmados_totales = casos_confirmados_totales;
        this.casos_confirmados_ultimos_14dias = casos_confirmados_ultimos_14dias;
        this.fecha_informe = fecha_informe;
    }

    public Data1() {
        this.codigo_geometria = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getCodigo_geometria() {
        return codigo_geometria;
    }

    public void setCodigo_geometria(int codigo_geometria) {
        this.codigo_geometria = codigo_geometria;
    }

    public String getZona_basica_salud() {
        return zona_basica_salud;
    }

    public void setZona_basica_salud(String zona_basica_salud) {
        this.zona_basica_salud = zona_basica_salud;
    }

    public Float getTasa_incidencia_acumulada_ultimos_14dias() {
        return tasa_incidencia_acumulada_ultimos_14dias;
    }

    public void setTasa_incidencia_acumulada_ultimos_14dias(Float tasa_incidencia_acumulada_ultimos_14dias) {
        this.tasa_incidencia_acumulada_ultimos_14dias = tasa_incidencia_acumulada_ultimos_14dias;
    }

    public Float getTasa_incidencia_acumulada_total() {
        return tasa_incidencia_acumulada_total;
    }

    public void setTasa_incidencia_acumulada_total(Float tasa_incidencia_acumulada_total) {
        this.tasa_incidencia_acumulada_total = tasa_incidencia_acumulada_total;
    }

    public int getCasos_confirmados_totales() {
        return casos_confirmados_totales;
    }

    public void setCasos_confirmados_totales(int casos_confirmados_totales) {
        this.casos_confirmados_totales = casos_confirmados_totales;
    }

    public int getCasos_confirmados_ultimos_14dias() {
        return casos_confirmados_ultimos_14dias;
    }

    public void setCasos_confirmados_ultimos_14dias(int casos_confirmados_ultimos_14dias) {
        this.casos_confirmados_ultimos_14dias = casos_confirmados_ultimos_14dias;
    }

    public String getFecha_informe() {
        return fecha_informe;
    }

    public void setFecha_informe(String fecha_informe) {
        this.fecha_informe = fecha_informe;
    }

    //toString

    @Override
    public String toString() {
        return "1{" +
                "codigo_geometria=" + codigo_geometria +
                ", zona_basica_salud='" + zona_basica_salud + '\'' +
                ", tasa_incidencia_acumulada_ultimos_14dias='" + tasa_incidencia_acumulada_ultimos_14dias + '\'' +
                ", tasa_incidencia_acumulada_total='" + tasa_incidencia_acumulada_total + '\'' +
                ", casos_confirmados_totales=" + casos_confirmados_totales +
                ", casos_confirmados_ultimos_14dias=" + casos_confirmados_ultimos_14dias +
                ", fecha_informe=" + fecha_informe +

                '\n';
    }
}


