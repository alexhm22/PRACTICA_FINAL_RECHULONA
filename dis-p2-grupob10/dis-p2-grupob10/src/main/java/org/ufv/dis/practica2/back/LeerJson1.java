package org.ufv.dis.practica2.back;
import com.google.gson.Gson;
import org.ufv.dis.practica2.Data1;
import org.ufv.dis.practica2.Data2;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LeerJson1 {
    public ArrayList<Data1> leeJson(){
        try {

            Reader reader = Files.newBufferedReader(Paths.get("dis-p2-grupob10/src/main/resources/Covid19-TIA_ZonasBásicasSalud.json")); // Leer el archivo JSON
            Gson gson = new Gson();
            ZonaMenores60 datos = gson.fromJson(reader,ZonaMenores60.class); // Deserializar  objeto

            // Convertir fecha de string a Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            for (Data1 data : datos.getdatos()) {
                Date date = sdf.parse(data.getFecha_informe());
                data.setFecha_informe(sdf2.format(date));
            }


            //System.out.println(datos);
            for(int i = 0; i < datos.getdatos().size(); i++){
                datos.getdatos().get(i).setId(i+1);
            }
            return datos.getdatos(); // Devuelve array de objetos leidos del JSON
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }



    public ArrayList<Data2> leeJson2(){

        try {

            Reader reader = Files.newBufferedReader(Paths.get("dis-p2-grupob10/src/main/resources/Covid19-TIA_ZonasBásicasSalud_Mayores60.json")); // Leer el archivo JSON
            Gson gson = new Gson();
            ZonaMayores60 datos = gson.fromJson(reader,ZonaMayores60.class); // Deserializar  objeto

            // Convertir fecha de string a Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            for (Data2 data : datos.getdatos()) {
                Date date = sdf.parse(data.getFecha_informe());
                data.setFecha_informe(sdf2.format(date));
            }


            //System.out.println(datos);
            for(int i = 0; i < datos.getdatos().size(); i++){
                datos.getdatos().get(i).setId(i+1);
            }


            return datos.getdatos(); // Devuelve array de objetos leidos del JSON
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }


}
