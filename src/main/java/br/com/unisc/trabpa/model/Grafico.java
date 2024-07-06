/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unisc.trabpa.model;

import br.com.unisc.trabpa.dal.Conexao;
import br.com.unisc.trabpa.model.DadosGrafico;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import br.com.unisc.trabpa.model.ObjetoVoador;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author caiok
 */
public class Grafico {

    public static void geraGrafico(ArrayList<LocalDate> dias, ArrayList<ArrayList<ObjetoVoador>> matriz) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < matriz.size(); i++) {
            ArrayList<ObjetoVoador> numObj = matriz.get(i);
            String dia = dias.get(i).format(formatter);
            dataset.addValue(numObj.size(), "Objetos Voadores", dia);
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Objetos Voadores",
                "Dias",
                "Unidades",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true,
                false,
                false);

        // Customizar eixo X para exibir datas
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        try {
            ChartUtilities.saveChartAsJPEG(new File("C:\\Users\\caiok\\OneDrive\\Documentos\\GitHub\\TrabPa1\\src\\main\\java\\view\\grafObjVoador.jpg"), chart, 500, 300);
        } catch (IOException exc) {
            System.err.println("Erro ao gerar a imagem");
        }
    }

    public ArrayList<ObjetoVoador> dadosData(LocalDate data) throws SQLException {
        ArrayList<ObjetoVoador> list = new ArrayList<>();
        String sql = "SELECT * FROM objeto_voador WHERE data = ?;";
        PreparedStatement st = Conexao.getInstance().prepareStatement(sql);
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

    public DadosGrafico geraDados(ArrayList<ArrayList<ObjetoVoador>> matriz, String d, Conexao conn) throws SQLException {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(d, parser).minusDays(3);
        ArrayList<LocalDate> dias = new ArrayList<>();
        Grafico g = new Grafico();

        for (int i = 0; i < 7; i++) {
            LocalDate currentDay = data.plusDays(i);
            matriz.add(g.dadosData(currentDay));
        }

        return new DadosGrafico(dias, matriz);
    }
}