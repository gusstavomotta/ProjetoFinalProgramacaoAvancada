/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import model.ObjetoVoador;
import org.jfree.chart.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author caiok
 */
public class Grafico {

    public static void geraGrafico() {

        XYSeries series = new XYSeries("Objetos Voadores");

        XYDataset dataset = new XYSeriesCollection(series);

        JFreeChart chart = ChartFactory.createXYAreaChart(
                "Objetos Voadores",
                "Dias",
                "Unidades",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true,
                false,
                false);

        try {
            ChartUtilities.saveChartAsJPEG(
                    new java.io.File("grafObjVoador.jpg"), chart, 500, 300);
        } catch (java.io.IOException exc) {
            System.err.println("Erro em gerar a imagem");
        }
    }

    public ArrayList<ObjetoVoador> dadosData(LocalDate data, Conexao conn) throws SQLException {

        ArrayList<ObjetoVoador> list = new ArrayList();
        String sql = "SELECT * FROM objeto_voador WHERE data = ?;";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, data.toString());

        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                ObjetoVoador obj = new ObjetoVoador(
                        rs.getString("id"),
                        rs.getString("data"),
                        rs.getString("nome"),
                        rs.getString("diametroMinKm"),
                        rs.getString("diametroMaxKm"),
                        rs.getBoolean("risco"),
                        rs.getString("dataDeAproximacao"),
                        rs.getDouble("velocidadeAproxKm")
                );
                list.add(obj);
            }
        }

        return list;
    }

       public ArrayList<ArrayList<ObjetoVoador>> geraDados(ArrayList<ArrayList<ObjetoVoador>> matriz, String d, Conexao conn) throws SQLException {

        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(d, parser).minusDays(3);
        Grafico g = new Grafico();

        for (int i = 0; i < 7; i++) {
            matriz.add(g.dadosData(data.plusDays(i), conn));
        }

        return matriz;
    }
}