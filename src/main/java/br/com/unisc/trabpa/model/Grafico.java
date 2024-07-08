package br.com.unisc.trabpa.model;

import br.com.unisc.trabpa.dal.Conexao;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.awt.Color;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

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

        // Customizar o eixo Y para pular de 2 em 2 unidades
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setTickUnit(new NumberTickUnit(2));

        // Customiza a área ocupada do gráfico de azul
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesFillPaint(0, new Color(0, 0, 255, 50)); // Azul com transparência
        renderer.setUseFillPaint(true);
        plot.setRenderer(renderer);

        try {
            ChartUtilities.saveChartAsJPEG(new File("src\\main\\java\\view\\grafObjVoador.jpg"), chart, 500, 300);
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
                        rs.getDouble("diametroMinKm"),
                        rs.getDouble("diametroMaxKm"),
                        rs.getBoolean("risco"),
                        rs.getString("dataDeAproximacao"),
                        rs.getDouble("velocidadeAproxKm"),
                        rs.getDouble("distancia")
                );
                list.add(obj);
            }
        }

        return list;
    }

    public DadosGrafico geraDados(ArrayList<ArrayList<ObjetoVoador>> matriz, String d) throws SQLException {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(d, parser).minusDays(5);
        ArrayList<LocalDate> dias = new ArrayList<>();
        Grafico g = new Grafico();


        return new DadosGrafico(dias, matriz);
    }
}
