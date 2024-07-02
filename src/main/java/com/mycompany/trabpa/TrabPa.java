/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.trabpa;

import com.fasterxml.jackson.databind.JsonNode;
import dal.Conexao;
import dal.ObjetoVoadorDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import model.ObjetoVoador;
import model.Relogio;
import model.Requisicao;

/**
 *
 * @author Gustavo Motta
 */
public class TrabPa {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, SQLException {
        
        Relogio data = new Relogio();
        System.out.println(data.getDataHoraAtual());

        Requisicao req = new Requisicao();
        Iterator<Map.Entry<String, JsonNode>> campos = req.Requisicao();

        Conexao conn = new Conexao();
        conn.getConexao();

        while (campos.hasNext()) {
            Map.Entry<String, JsonNode> campo = campos.next();
            JsonNode arrayDeAsteroides = campo.getValue();

            for (JsonNode asteroide : arrayDeAsteroides) {
                JsonNode proximidade = asteroide.get("close_approach_data").get(0);

                ObjetoVoador objetoVoador = new ObjetoVoador(campo.getKey(), asteroide.get("id").asText(), asteroide.get("name").asText(),
                        asteroide.get("estimated_diameter").get("kilometers").get("estimated_diameter_min").asText(),
                        asteroide.get("estimated_diameter").get("kilometers").get("estimated_diameter_max").asText(), asteroide.get("is_potentially_hazardous_asteroid").asBoolean(),
                        proximidade.get("close_approach_date").asText(), proximidade.get("relative_velocity").get("kilometers_per_hour").asDouble());
                
                System.out.println(objetoVoador.toString());
                ObjetoVoadorDao dao = new ObjetoVoadorDao();
                dao.inserirNoBanco(objetoVoador);
            }
        }

    }
}
