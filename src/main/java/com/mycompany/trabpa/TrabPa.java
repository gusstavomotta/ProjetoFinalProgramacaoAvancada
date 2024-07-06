/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.trabpa;

import com.fasterxml.jackson.databind.JsonNode;
import dal.Conexao;
import dal.ObjetoVoadorDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
        System.out.println(data.getDataAtual());
        
        ObjetoVoadorDao dao = new ObjetoVoadorDao();

        Requisicao req = new Requisicao();
        Iterator<Map.Entry<String, JsonNode>> campos = req.Requisicao();

        Conexao conn = new Conexao();
//        int contador = 0;
//        while (campos.hasNext()) {
//            Map.Entry<String, JsonNode> campo = campos.next();
//            JsonNode arrayDeAsteroides = campo.getValue();
//
//            for (JsonNode asteroide : arrayDeAsteroides) {
//                JsonNode proximidade = asteroide.get("close_approach_data").get(0);
//
//                ObjetoVoador objetoVoador = new ObjetoVoador(asteroide.get("id").asText(), campo.getKey(), asteroide.get("name").asText(),
//                        asteroide.get("estimated_diameter").get("kilometers").get("estimated_diameter_min").asText(),
//                        asteroide.get("estimated_diameter").get("kilometers").get("estimated_diameter_max").asText(), asteroide.get("is_potentially_hazardous_asteroid").asBoolean(),
//                        proximidade.get("close_approach_date").asText(), proximidade.get("relative_velocity").get("kilometers_per_hour").asDouble());
//
//                dao.inserirNoBanco(objetoVoador, conn);
//                contador++;
//            }
//        }
<<<<<<< Updated upstream
//
//        ArrayList<ObjetoVoador> objetosFiltrados = dao.listarComFiltro(conn, "risco", "1");
//        for (int i = 0; i < objetosFiltrados.size(); i++) {
//            System.out.println(objetosFiltrados.get(i).toString());
=======
       
        
        ArrayList<ObjetoVoador> lst = dao.listarComFiltro(conn, "risco", "1");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i).toString());
        }
        System.out.println(lst.toString());

//        ArrayList<ObjetoVoador> ordenada = dao.ordenarPorAtributo(conn, "data");
//        for (int i = 0; i < ordenada.size(); i++) {
//            System.out.println(ordenada.get(i).toString());
>>>>>>> Stashed changes
//        }
//
//        ArrayList<ObjetoVoador> objetosOrdenados = dao.ordenarPorAtributo(conn, "data");
//        for (int i = 0; i < objetosOrdenados.size(); i++) {
//            System.out.println(objetosOrdenados.get(i).toString());
//        }
        
//        ArrayList<ObjetoVoador> objetosProximos = dao.listarObjetosProximos(conn, data.getDataAtual());
//        for (int i = 0; i < objetosProximos.size(); i++) {
//            System.out.println(objetosProximos.get(i).toString());
//        }

//        System.out.println(contador);
    }
}
