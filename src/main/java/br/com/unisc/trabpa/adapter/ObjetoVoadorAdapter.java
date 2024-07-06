/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unisc.trabpa.adapter;

import br.com.unisc.trabpa.model.ObjetoVoador;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author caiok
 */
public class ObjetoVoadorAdapter {
    
    public static ArrayList<ObjetoVoador> IteratorToObjetoVoadorList(Iterator<Map.Entry<String, JsonNode>> iterator){
        ArrayList<ObjetoVoador> result = new ArrayList<ObjetoVoador>();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> campo = iterator.next();
            JsonNode listaDeObjetos = campo.getValue();

            for (JsonNode asteroide : listaDeObjetos) {
                JsonNode proximidade = asteroide.get("close_approach_data").get(0);

                ObjetoVoador objetoVoador = new ObjetoVoador(asteroide.get("id").asText(), campo.getKey(), asteroide.get("name").asText(),
                        asteroide.get("estimated_diameter").get("kilometers").get("estimated_diameter_min").asText(),
                        asteroide.get("estimated_diameter").get("kilometers").get("estimated_diameter_max").asText(), asteroide.get("is_potentially_hazardous_asteroid").asBoolean(),
                        proximidade.get("close_approach_date").asText(), proximidade.get("relative_velocity").get("kilometers_per_hour").asDouble());

                result.add(objetoVoador);
            }
        }
        return result;
    }
    
}
