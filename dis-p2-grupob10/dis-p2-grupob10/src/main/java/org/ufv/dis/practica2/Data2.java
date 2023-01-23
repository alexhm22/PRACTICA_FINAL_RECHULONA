package org.ufv.dis.practica2;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Data2 {

    @NotNull
    private  int id;
    @NotNull
    private int codigo_geometria;
    @NotNull
    private String zona_basica_salud;
    @Min(value = 0)
    private Float tasa_incidencia_acumulada_P60mas_ultimos_14dias;
    @Min(value = 0)
    private Float casos_confirmados_P60mas_ultimos_14dias;
    @NotNull
    private String fecha_informe;


    public Data2(int codigo_geometria,String zona_basica_salud,Float tasa_incidencia_acumulada_P60mas_ultimos_14dias,Float casos_confirmados_P60mas_ultimos_14dias,String fecha_informe) {
        this.codigo_geometria = codigo_geometria;
        this.zona_basica_salud= zona_basica_salud;
        this.tasa_incidencia_acumulada_P60mas_ultimos_14dias= tasa_incidencia_acumulada_P60mas_ultimos_14dias;
        this.casos_confirmados_P60mas_ultimos_14dias= casos_confirmados_P60mas_ultimos_14dias;
        this.fecha_informe= fecha_informe;
    }

    public Data2(){
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

    public Float getTasa_incidencia_acumulada_P60mas_ultimos_14dias() {
        return tasa_incidencia_acumulada_P60mas_ultimos_14dias;
    }

    public void setTasa_incidencia_acumulada_P60mas_ultimos_14dias(Float tasa_incidencia_acumulada_P60mas_ultimos_14dias) {
        this.tasa_incidencia_acumulada_P60mas_ultimos_14dias = tasa_incidencia_acumulada_P60mas_ultimos_14dias;
    }

    public Float getCasos_confirmados_P60mas_ultimos_14dias() {
        return casos_confirmados_P60mas_ultimos_14dias;
    }

    public void setCasos_confirmados_P60mas_ultimos_14dias(Float casos_confirmados_P60mas_ultimos_14dias) {
        this.casos_confirmados_P60mas_ultimos_14dias = casos_confirmados_P60mas_ultimos_14dias;
    }
    public String getFecha_informe() {
        return fecha_informe;
    }

    public void setFecha_informe(String fecha_informe) {
        this.fecha_informe = fecha_informe;
    }

    @Override
    public String toString() {
        return "1{" +
                "codigo_geometria=" + codigo_geometria +
                ", zona_basica_salud=" + zona_basica_salud + '\'' +
                ", tasa_incidencia_acumulada_P60mas_ultimos_14dias=" + tasa_incidencia_acumulada_P60mas_ultimos_14dias + '\'' +
                ", casos_confirmados_P60mas_ultimos_14dias='" + casos_confirmados_P60mas_ultimos_14dias + '\'' +
                ", fecha_informe=" + fecha_informe +
                '}';
    }



}
