package org.ufv.dis.practica2.back;


import org.ufv.dis.practica2.Data1;

import java.util.ArrayList;


public class ZonaMenores60 {
    private ArrayList<Data1> data;

    public ZonaMenores60(){
        this.data=new ArrayList();
    }

    public ArrayList<Data1> getdatos() {
        return data;
    }

    public void setdatos(ArrayList<Data1> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "2 datos=" + data + '}';
    }


}

