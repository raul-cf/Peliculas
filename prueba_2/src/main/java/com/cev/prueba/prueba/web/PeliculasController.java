package com.cev.prueba.prueba.web;

import java.util.List;

import com.cev.prueba.prueba.service.CinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import com.cev.prueba.prueba.domain.Pelicula;
import com.cev.prueba.prueba.domain.Cine;
import com.cev.prueba.prueba.service.PeliculasService;
import com.cev.prueba.prueba.web.error.CustomError;
import java.util.Collections;

@RestController
public class PeliculasController {

    @Autowired
    PeliculasService peliculasService;
    @Autowired
    CinesService cinesService;

    @GetMapping(path = "/peliculas")
    List<Pelicula> getPeliculas(@RequestParam(required = false) String titulo, @RequestParam(required = false, name = "puntuacion.min", defaultValue = "2") int puntuacionMinima ){

        if (puntuacionMinima<2) {
            throw new CustomError("La puntuacion no puede ser menor que 2");
        }
        if( titulo!= null) {
            return peliculasService.buscaPorTitulo(titulo);
        }else return peliculasService.getPeliculas();
    }


    @GetMapping(path = "/peliculas/{id}")
    Pelicula getPeliculas(@PathVariable int id){
        return peliculasService.getPelicula(id);
    }

    @PostMapping(path = "/peliculas")
    int altaPelicula(@RequestBody Pelicula pelicula ) {
        return peliculasService.add(pelicula);
    }

    @PutMapping(path = "/peliculas/{id}")
    Pelicula modificaPelicula(@RequestBody Pelicula pelicula,@PathVariable int id) {
        peliculasService.guarda(id, pelicula);
        return pelicula;
    }

    @DeleteMapping(path="/peliculas/{id}")
    String borraPelicula(@PathVariable int id) {
        peliculasService.borra(id);
        return("OK");
    }
    @GetMapping(path = "/cines")
    List<Cine> getCines(@RequestParam(required = false) String nombre, @RequestParam(required = false, name = "precio.min", defaultValue = "12") int precio ){

        if (precio<2) {
            throw new CustomError("La puntuacion no puede ser menor que 2");
        }
        if( nombre!= null) {
            return cinesService.buscaPorNombre(nombre);
        }else return cinesService.getCines();
    }

    @GetMapping(path = "/cines/{id}")
    Cine getCines(@PathVariable int id){
        return cinesService.getCine(id);
    }

    @PostMapping(path = "/cines")
    int altaCine(@RequestBody Cine cine ) {
        return cinesService.add(cine);
    }

    @PutMapping(path = "/cines/{id}")
    Cine modificaCine(@RequestBody Cine cine,@PathVariable int id) {
        cinesService.guarda(id, cine);
        return cine;
    }

    @DeleteMapping(path="/cines/{id}")
    String borraCine(@PathVariable int id) {
        cinesService.borra(id);
        return("OK");
    }

    //Cines en los que se proyecta ordenados por precio, de más barato a más caro
    @GetMapping(path = "/cinesProyectados/{id}")
    ArrayList<Cine> getCinesProyectados(@PathVariable int id){
        try {
            ArrayList<Cine> cinesOrdered = new ArrayList<>();
            cinesOrdered = getCinesById(id);
            Collections.sort(cinesOrdered);
            return cinesOrdered;
        } catch (Exception exception){
            return new ArrayList<>();
        }
    }

    //Cines en los que se proyecta con un determinado código postal.
    @GetMapping(path = "/peliCodigoPostal/{id}/{codigoPostal}")
    ArrayList<Cine> getpeliCodigoPostal(@PathVariable int id, @PathVariable String codigoPostal){
        try {
            ArrayList<Cine> cines;
            ArrayList<Cine> cinesCode = new ArrayList<>();
            cines = getCinesById(id);
            for (Cine c : cines) {
                if (c.getCodigoPostal().equals(codigoPostal)){
                    cinesCode.add(c);
                }
            }
            return  cinesCode;
        } catch (Exception exception){
            return new ArrayList<>();
        }
    }

    @GetMapping(path = "/getReviews")
    ArrayList<String> getReviews(){
        try {
            ArrayList<String> reviews = new ArrayList<>();
            List<Pelicula> peliculas = peliculasService.getPeliculas();
            Collections.sort(peliculas);
            for (Pelicula p : peliculas) {
                reviews.add(p.getPuntuacion()+" "+p.getTitulo()+" "+p.getSinopsis());
            }
            return reviews;
        } catch (Exception exception){
            return new ArrayList<>();
        }
    }

    @GetMapping(path = "/peliculasHeader")
        ResponseEntity<List<Pelicula>> getPeliculasHeader(){
            HttpHeaders headers = new HttpHeaders();
            headers.add("MiHeaderRespuesta", "HeaderRespuesta");

            return ResponseEntity.ok().headers(headers).body(peliculasService.getPeliculas());
        }
    ArrayList<Cine> getCinesById(int id) {
        Pelicula pelicula = peliculasService.getPelicula(id);
        ArrayList<String> cines;
        cines = pelicula.getCines();
        ArrayList<Cine> cinesList;
        ArrayList<Cine> cinesOrdered = new ArrayList<>();
        cinesList = cinesService.getCines();
        for (Cine c : cinesList) {
            if (cines.contains(c.getNombre())){
                cinesOrdered.add(c);
            }
        }
        return cinesOrdered;
    }


}

