package com.cev.prueba.prueba.service;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.cev.prueba.prueba.domain.Cine;

@Service
public class CinesService {
    ArrayList<Cine> cines = new ArrayList<>();

    public CinesService() {
        Cine cine = new Cine();
        cine.setNombre("Cinesa");
        cine.setPoblacion("Madrid");
        cine.setCodigoPostal("28051");
        cine.setProvincia("Madrid");
        cine.setPrecio(12);
        cines.add(cine);
        Cine cine2 = new Cine();
        cine2.setNombre("Yelmo");
        cine2.setPoblacion("Madrid");
        cine2.setCodigoPostal("28001");
        cine2.setProvincia("Madrid");
        cine2.setPrecio(10);
        cines.add(cine2);
    }

    public Cine getCine(int id) {
        return cines.get(id-1);
    }

    public int add(Cine cine) {
        cines.add(cine);
        return cines.size();
    }

    public ArrayList<Cine> getCines() {
        return cines;
    }

    public void guarda(int id, Cine cine ) {
        cines.set(id-1, cine);
    }

    public void borra(int id) {
        cines.remove(id-1);
    }

    public  List<Cine> buscaPorNombre(String nombre) {

        List<Cine> cinesResultado = new ArrayList<Cine>();
        for(Cine cine: cines) {
            if( cine.getNombre().contains(nombre)) {
                cinesResultado.add(cine);
            }
        }
        return cinesResultado;
    }
}