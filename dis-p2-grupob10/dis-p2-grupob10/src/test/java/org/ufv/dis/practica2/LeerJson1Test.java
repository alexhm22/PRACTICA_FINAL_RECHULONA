package org.ufv.dis.practica2;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeerJson1Test {

    @Test
    void leeJson() {

        final Data1 datos = new Data1(001, "Abrantes", (float) 3.25224404839339, (float) 1014.70014309874, 0, 312,"2020/07/01 09:00:00");
        final Gson gson = new Gson();
        final String representacionJSON = gson.toJson(datos);
        assertEquals("{\"id\":0,\"codigo_geometria\":1,\"zona_basica_salud\":\"Abrantes\",\"tasa_incidencia_acumulada_ultimos_14dias\":3.252244,\"tasa_incidencia_acumulada_total\":1014.70013,\"casos_confirmados_totales\":0,\"casos_confirmados_ultimos_14dias\":312,\"fecha_informe\":\"2020/07/01 09:00:00\"}", representacionJSON);
    }

    @Test
    void leeJson2(){
        final Data2 dat= new Data2( 001,"Abrantes",(float)182.15,(float)13,"2022/11/29 10:47:00");
        final Gson gson= new Gson();
        final String representacionJSON = gson.toJson(dat);

        assertEquals("{\"id\":0,\"codigo_geometria\":\"001\",\"zona_basica_salud\":\"Abrantes\",\"tasa_incidencia_acumulada_P60mas_ultimos_14dias\":182.15,\"casos_confirmados_P60mas_ultimos_14dias\":13.0,\"fecha_informe\":\"2022/11/29 10:47:00\"}",representacionJSON);

    }


}
