package org.ufv.dis.practica2.back;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.ufv.dis.practica2.Data1;
import org.ufv.dis.practica2.Data2;
import org.ufv.dis.practica2.front.FormularioDatos1;
import org.ufv.dis.practica2.front.FormularioDatos2;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class APIController {
    public void editarDatos1(Data1 datos1) {



            Gson gson = new Gson();
            LeerJson1 milectorJson1 = new LeerJson1();
            ArrayList<Data1> DatosMenores60 = milectorJson1.leeJson();



            for (Data1 data1 : DatosMenores60) {
                if (data1.getId() == datos1.getId()) {
                    data1.setZona_basica_salud(datos1.getZona_basica_salud());
                    data1.setTasa_incidencia_acumulada_ultimos_14dias(datos1.getTasa_incidencia_acumulada_ultimos_14dias());
                    data1.setTasa_incidencia_acumulada_total(datos1.getTasa_incidencia_acumulada_total());
                    data1.setCasos_confirmados_ultimos_14dias(datos1.getCasos_confirmados_ultimos_14dias());
                    data1.setCasos_confirmados_totales(datos1.getCasos_confirmados_totales());
                    data1.setFecha_informe(datos1.getFecha_informe());
                    break;
                }
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            for (Data1 data : DatosMenores60) {
                Date date = null;
                try {
                    date = sdf.parse(data.getFecha_informe());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                data.setFecha_informe(sdf2.format(date));
            }

            JsonObject jsonData = new JsonObject();
            jsonData.add("data",gson.toJsonTree(DatosMenores60));
            //String jsonData = gson.toJsonTree(DatosMenores60);
            try (FileWriter file = new FileWriter("dis-p2-grupob10/src/main/resources/Covid19-TIA_ZonasBásicasSalud.json")) {
                file.write(jsonData.toString());
                System.out.println("Archivo JSON sobreescrito con éxito");
            } catch (IOException e) {
                e.printStackTrace();
            }

    }


    public void crearDatos1(Data1 datos1) {


            Gson gson = new Gson();
            LeerJson1 milectorJson1 = new LeerJson1();
            ArrayList<Data1> DatosMenores60 = milectorJson1.leeJson();

            List<String> zonasBasicasSaludUnicas = DatosMenores60.stream()
                    .map(Data1::getZona_basica_salud)
                    .distinct()
                    .collect(Collectors.toList());

            for (String datos : zonasBasicasSaludUnicas) {
                if (datos.equals(datos1.getZona_basica_salud())) {
                    for (Data1 data1 : DatosMenores60)
                        if (data1.getZona_basica_salud().equals(datos)) {
                            datos1.setCodigo_geometria(data1.getCodigo_geometria());
                        }
                    break;
                } else {
                    int maxCodigoGeometria = Collections.max(DatosMenores60.stream().map(Data1::getCodigo_geometria).collect(Collectors.toList()));
                    datos1.setCodigo_geometria(maxCodigoGeometria + 1);
                }
            }

            int maxid = DatosMenores60.size() + 1;
            datos1.setId(maxid);
            DatosMenores60.add(datos1);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            for (Data1 data : DatosMenores60) {
                Date date = null;
                try {
                    date = sdf.parse(data.getFecha_informe());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                data.setFecha_informe(sdf2.format(date));
            }


            JsonObject jsonData = new JsonObject();
            jsonData.add("data", gson.toJsonTree(DatosMenores60));
            //String jsonData = gson.toJsonTree(DatosMenores60);
            try (FileWriter file = new FileWriter("dis-p2-grupob10/src/main/resources/Covid19-TIA_ZonasBásicasSalud.json")) {
                file.write(jsonData.toString());
                System.out.println("Archivo JSON sobreescrito con éxito");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void editarDatos2(Data2 datos2) {

        Gson gson = new Gson();
        LeerJson1 milectorJson2 = new LeerJson1();
        ArrayList<Data2> DatosMayores60 = milectorJson2.leeJson2();



        for (Data2 data2 : DatosMayores60) {
            if (data2.getId() == datos2.getId()) {
                data2.setZona_basica_salud(datos2.getZona_basica_salud());
                data2.setTasa_incidencia_acumulada_P60mas_ultimos_14dias(datos2.getTasa_incidencia_acumulada_P60mas_ultimos_14dias());
                data2.setCasos_confirmados_P60mas_ultimos_14dias(datos2.getCasos_confirmados_P60mas_ultimos_14dias());
                data2.setFecha_informe(datos2.getFecha_informe());
                break;
            }
        }


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        for (Data2 data : DatosMayores60) {
            Date date = null;
            try {
                date = sdf.parse(data.getFecha_informe());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            data.setFecha_informe(sdf2.format(date));
        }


        JsonObject jsonData = new JsonObject();
        jsonData.add("data",gson.toJsonTree(DatosMayores60));
        //String jsonData = gson.toJsonTree(DatosMayores60);
        try (FileWriter file = new FileWriter("dis-p2-grupob10/src/main/resources/Covid19-TIA_ZonasBásicasSalud_Mayores60.json")) {
            file.write(jsonData.toString());
            System.out.println("Archivo JSON sobreescrito con éxito");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
