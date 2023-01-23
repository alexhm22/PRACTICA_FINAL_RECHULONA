package org.ufv.dis.practica2.back;

import org.ufv.dis.practica2.Data2;

import java.util.ArrayList;

public class ZonaMayores60 {

    private ArrayList<Data2> data;

    public ZonaMayores60(){
        this.data=new ArrayList();
    }

    public ArrayList<Data2> getdatos() {
        return data;
    }

    public void setdatos(ArrayList<Data2> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "2 datos=" + data + '}';
    }


}
