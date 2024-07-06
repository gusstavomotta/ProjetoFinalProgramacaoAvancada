package com.mycompany.trabpa;

import com.fasterxml.jackson.databind.JsonNode;
import dal.Conexao;
import dal.Grafico;
import dal.ObjetoVoadorDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import model.ObjetoVoador;
import model.Relogio;
import model.Requisicao;

public class TrabPa {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, SQLException {
        
        Relogio relogio = new Relogio();
        ObjetoVoadorDao dao = new ObjetoVoadorDao();
        Grafico graf = new Grafico();
        Requisicao req = new Requisicao();
        Conexao conn = new Conexao();

        String dataInicio = "2024-07-01";
        String dataFim = "2024-07-08";

        Iterator<Map.Entry<String, JsonNode>> dadosRequisicao = req.chamaApi(dataInicio, dataFim);

        while (dadosRequisicao.hasNext()) {
            Map.Entry<String, JsonNode> campo = dadosRequisicao.next();
            JsonNode listaDeObjetos = campo.getValue();

            for (JsonNode asteroide : listaDeObjetos) {
                JsonNode proximidade = asteroide.get("close_approach_data").get(0);

                ObjetoVoador objetoVoador = new ObjetoVoador(asteroide.get("id").asText(), campo.getKey(), asteroide.get("name").asText(),
                        asteroide.get("estimated_diameter").get("kilometers").get("estimated_diameter_min").asText(),
                        asteroide.get("estimated_diameter").get("kilometers").get("estimated_diameter_max").asText(), asteroide.get("is_potentially_hazardous_asteroid").asBoolean(),
                        proximidade.get("close_approach_date").asText(), proximidade.get("relative_velocity").get("kilometers_per_hour").asDouble());

                dao.inserirObjetoVoadorNoBanco(objetoVoador, conn);
            }
        }
        
        String s = "2024-07-06";
        ArrayList<ArrayList<ObjetoVoador>> list = new ArrayList<>();
        try {
            list = graf.geraDados(list, s, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(list);
//        System.out.println("OBJETOS FILTRADOS");
//        ArrayList<ObjetoVoador> objetosFiltrados = dao.listarObjetosPorAtributo(conn, "risco", "1");
//        for (int i = 0; i < objetosFiltrados.size(); i++) {
//            System.out.println(objetosFiltrados.get(i).toString());
//        }
//
//        System.out.println("OBJETOS ORDENADOS POR ATRIBUTO");
//        ArrayList<ObjetoVoador> objetosOrdenados = dao.ordernarObjetosPorAtributo(conn, "data");
//        for (int i = 0; i < objetosOrdenados.size(); i++) {
//            System.out.println(objetosOrdenados.get(i).toString());
//        }
//
//        System.out.println("OBJETOS PRÓXIMOS");
//        ArrayList<ObjetoVoador> objetosProximos = dao.listarObjetosProximos(conn, relogio.getDataAtual());
//        for (int i = 0; i < objetosProximos.size(); i++) {
//            System.out.println(objetosProximos.get(i).toString());
//        }

    }
}
