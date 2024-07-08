package br.com.unisc.trabpa.model;

import br.com.unisc.trabpa.dal.Conexao;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class Grafico {

    public static String geraGrafico(LocalDate dataInicio, LocalDate dataFim) throws SQLException {
        ArrayList<LocalDate> dias = new ArrayList<>();
        ArrayList<ArrayList<ObjetoVoador>> matriz = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = dataInicio;

        while (!currentDate.isAfter(dataFim)) {
            dias.add(currentDate);
            matriz.add(dadosData(currentDate));
            currentDate = currentDate.plusDays(1);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < matriz.size(); i++) {
            String dia = dias.get(i).format(formatter);
            dataset.addValue(matriz.get(i).size(), "Objetos Voadores", dia);
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

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setTickUnit(new NumberTickUnit(2));

        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesFillPaint(0, new Color(0, 0, 255, 50)); // Azul com transparência
        renderer.setUseFillPaint(true);
        plot.setRenderer(renderer);

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filePath = "src/main/java/view/grafObjVoador_" + timestamp + ".jpg";

        try {
            File outputFile = new File(filePath);
            outputFile.getParentFile().mkdirs(); // Cria diretórios se não existirem
            ChartUtilities.saveChartAsJPEG(outputFile, chart, 1280, 680);
        } catch (IOException exc) {
            System.err.println("Erro ao gerar a imagem: " + exc.getMessage());
        }
        return filePath;
    }

    public static ArrayList<ObjetoVoador> dadosData(LocalDate data) throws SQLException {
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
}
