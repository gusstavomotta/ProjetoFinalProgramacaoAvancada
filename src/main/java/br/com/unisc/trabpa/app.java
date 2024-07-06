package br.com.unisc.trabpa;

import br.com.unisc.trabpa.adapter.ObjetoVoadorAdapter;
import com.fasterxml.jackson.databind.JsonNode;
import br.com.unisc.trabpa.dal.Conexao;
import br.com.unisc.trabpa.dal.NASAConnector;
import br.com.unisc.trabpa.model.DadosGrafico;
import br.com.unisc.trabpa.model.Grafico;
import br.com.unisc.trabpa.dal.ObjetoVoadorDao;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import br.com.unisc.trabpa.model.ObjetoVoador;
import br.com.unisc.trabpa.model.Relogio;

public class app {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, SQLException {

        String dataInicio = "2024-07-01";
        String dataFim = "2024-07-08";
        Iterator<Map.Entry<String, JsonNode>> dadosRequisicao = NASAConnector.Request(dataInicio, dataFim);
        ArrayList<ObjetoVoador> dadosTratados = ObjetoVoadorAdapter.IteratorToObjetoVoadorList(dadosRequisicao);
        ObjetoVoadorDao dao = new ObjetoVoadorDao();
        for (ObjetoVoador objetoVoador : dadosTratados) {
            dao.inserirObjetoVoadorNoBanco(objetoVoador);
        }
        /* ARMAZENAR DADOS DA NASA */
        
        
        
        
        
        //Relogio relogio = new Relogio();
        //ObjetoVoadorDao dao = new ObjetoVoadorDao();
        //Grafico graf = new Grafico();
        //Requisicao req = new Requisicao();

        //ArrayList<ArrayList<ObjetoVoador>> matriz = new ArrayList<>();
        //String dataInicial = "2024-07-06"; // Substitua pela data inicial desejada

        // Gera os dados e obtém as datas e a matriz de dados
        //DadosGrafico dadosGrafico = graf.geraDados(matriz, dataInicial, conn);

        // Chama o método geraGrafico com os dados gerados
        //Grafico.geraGrafico(dadosGrafico.getDias(), dadosGrafico.getMatriz());
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
