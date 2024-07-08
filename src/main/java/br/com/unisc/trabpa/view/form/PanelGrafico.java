package br.com.unisc.trabpa.view.form;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import br.com.unisc.trabpa.model.Grafico;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.BoxLayout;

public class PanelGrafico extends JPanel {
   
    private JDateChooser dateChooserInicio;
    private JDateChooser dateChooserFim;
    private JButton btnCriarGrafico;
    private JLabel lblStatus;
    private JLabel graficoLabel;
    
    private void initComponents() {
        dateChooserInicio = new JDateChooser();
        dateChooserFim = new JDateChooser();
        btnCriarGrafico = new JButton("Criar Gráfico");
        lblStatus = new JLabel();
        
        dateChooserInicio.setDateFormatString("yyyy-MM-dd");
        dateChooserFim.setDateFormatString("yyyy-MM-dd");
    }

    public PanelGrafico() {
        initComponents();
        widgetGrafico();
        addActionEvents();
    }

    private void widgetGrafico() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(dateChooserInicio);
        topPanel.add(dateChooserFim);
        topPanel.add(btnCriarGrafico);
        
        graficoLabel = new JLabel();
        
        this.add(topPanel, BorderLayout.NORTH);
        this.add(graficoLabel, BorderLayout.CENTER);
    }

    private void addActionEvents() {
        btnCriarGrafico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarGraficoAction(e);
            }
        });
    }

    private void criarGraficoAction(ActionEvent e) {
        LocalDate dataInicio = dateChooserInicio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataFim = dateChooserFim.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        try {
            String filePath = Grafico.geraGrafico(dataInicio, dataFim);
            atualizarGrafico(filePath);
            System.out.println("Gráfico gerado com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao gerar dados para o gráfico: " + ex.getMessage());
        }
    }

    private void atualizarGrafico(String imagePath) {
        File imageFile = new File(imagePath);

        if (imageFile.exists()) {
            try {
                BufferedImage bufferedImage = ImageIO.read(imageFile);
                ImageIcon graficoIcon = new ImageIcon(bufferedImage);
                graficoLabel.setIcon(graficoIcon);
                graficoLabel.repaint();
                graficoLabel.revalidate();
                System.out.println("Imagem atualizada com sucesso!");
            } catch (IOException e) {
                System.err.println("Erro ao carregar a imagem: " + e.getMessage());
            }
        } else {
            System.err.println("Imagem não encontrada: " + imagePath);
        }
    }
}
