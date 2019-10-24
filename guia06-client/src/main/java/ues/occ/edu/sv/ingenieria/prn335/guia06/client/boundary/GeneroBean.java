/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.guia06.client.boundary;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Genero;

/**
 *
 * @author sandoval
 */
@Named(value = "generoBean")
@ViewScoped
public class GeneroBean implements Serializable {

    WebsocketClient client = new WebsocketClient();
    List<Genero> generos = new ArrayList();
    Genero genero = new Genero();
    String[] names = {"activo", "descripcion", "idGenero", "nombre"};

    public void crear() throws IOException, InterruptedException {
        if (genero != null) {
            String message = "{\"activo\":" + genero.getActivo() + ",\"descripcion\":\"" + genero.getDescripcion() + "\",\"idGenero\":" + genero.getIdGenero() + ",\"nombre\":\"" + genero.getNombre() + "\"}";
            System.out.println(message);
            client.sendMessage(message);
            Thread.sleep(100);
            this.cancelar();
            generos.clear();
            this.generosGet();
        }
    }
    public void cancelar(){
        genero.setActivo(false);
        genero.setDescripcion(null);
        genero.setIdGenero(null);
        genero.setNombre(null);
    }

    public void generosGet() throws IOException, InterruptedException {
        client.sendMessage("Hola");
        System.out.println("Se conecto");
        Thread.sleep(100);
        System.out.println(client.message);
        JSONArray jsonArray = new JSONArray(client.message);
        for (int i = 0; i < jsonArray.length(); i++) {
            System.out.println(jsonArray.get(i));
            JSONObject json = jsonArray.getJSONObject(i);
            generos.add(new Genero(json.getInt("idGenero"), json.getString("nombre"), json.getString("descripcion"), json.getBoolean("activo")));
        }
        for (int i = 0; i < generos.size(); i++) {
            System.out.println(generos.get(i).getNombre());
        }

    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public List<Genero> getGeneros() {
        return generos;
    }
}
