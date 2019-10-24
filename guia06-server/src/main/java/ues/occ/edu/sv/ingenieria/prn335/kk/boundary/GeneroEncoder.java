/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.edu.sv.ingenieria.prn335.kk.boundary;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Genero;

/**
 *
 * @author sandoval
 */
@LocalBean
public class GeneroEncoder implements Encoder.Text<Genero> {

    Jsonb jsonb = JsonbBuilder.create();

    public String generoToJson(List<Genero> lista) {
        if (lista != null || !lista.isEmpty()) {
            try {
                return jsonb.toJson(lista);
            } catch (JsonbException e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
        return "";
    }

    @Override
    public String encode(Genero g) throws EncodeException {
        return jsonb.toJson(g);
    }

    @Override
    public void init(EndpointConfig arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
