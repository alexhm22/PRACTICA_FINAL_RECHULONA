package org.ufv.dis.practica2.back;

import org.springframework.web.bind.annotation.*;
import org.ufv.dis.practica2.Data1;
import org.ufv.dis.practica2.Data2;

import java.util.ArrayList;
@RestController
public class API {
    @GetMapping("/GetData1")
    public ArrayList<Data1> GetData1(){
        ArrayList<Data1> listaData1 = new LeerJson1().leeJson();
        return listaData1;
    }

    @PutMapping("/PutData1")
    public void guardarData1(@RequestBody Data1 data1) {
        new APIController().editarDatos1(data1);
    }

    @PostMapping("/PostData1")
    public void crearData1(@RequestBody Data1 data1) {
        new APIController().crearDatos1(data1);
    }

    @GetMapping("/GetData2")
    public ArrayList<Data2> GetData2(){
        ArrayList<Data2> listaData2 = new LeerJson1().leeJson2();
        return listaData2;
    }

    @PutMapping("/PutData2")
    public void guardarData2(@RequestBody Data2 data2) {
        new APIController().editarDatos2(data2);
    }
}
