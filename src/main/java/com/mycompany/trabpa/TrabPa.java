package com.mycompany.trabpa;

import com.fasterxml.jackson.databind.JsonNode;
import dal.Conexao;
import model.DadosGrafico;
import model.Grafico;
import dal.ObjetoVoadorDao;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import model.ObjetoVoador;
import model.Relogio;
import dal.Requisicao;

public class TrabPa {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, SQLException {

        Relogio relogio = new Relogio();
        ObjetoVoadorDao dao = new ObjetoVoadorDao();
        Grafico graf = new Grafico();
        Requisicao req = new Requisicao();
        Conexao conn = new Conexao();

        

        ArrayList<ArrayList<ObjetoVoador>> matriz = new ArrayList<>();
        String dataInicial = "2024-07-06"; // Substitua pela data inicial desejada

        // Gera os dados e obtém as datas e a matriz de dados
        DadosGrafico dadosGrafico = graf.geraDados(matriz, dataInicial, conn);

        // Chama o método geraGrafico com os dados gerados
        Grafico.geraGrafico(dadosGrafico.getDias(), dadosGrafico.getMatriz());
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
