/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unisc.trabpa.view.form;

import br.com.unisc.trabpa.dal.ObjetoVoadorDao;
import br.com.unisc.trabpa.model.ObjetoVoador;
import br.com.unisc.trabpa.model.Relogio;
import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Matheus
 */
public class PanelDashboard extends javax.swing.JPanel {

    private JLabel lblDataHora;
    private javax.swing.JPanel panelBody;

    public PanelDashboard() {
        initComponents();
        panelBody = new javax.swing.JPanel(); // Inicialize o panelBody
        setLayout(new BorderLayout()); // Defina o layout do PanelDashboard
        add(panelBody, BorderLayout.CENTER); // Adicione o panelBody ao layout principal
        adicionaWidgets();
    }

    private void adicionaWidgets() {
        widgetRelogio();
        widgetContadorObjetos();
        widgetProximasAproximacoes();
    }

    private void widgetRelogio() {
        lblDataHora = new JLabel();
        lblDataHora.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblDataHora.setHorizontalAlignment(JLabel.CENTER);

        panelBody.setLayout(new BorderLayout());  //
        panelBody.add(lblDataHora, BorderLayout.NORTH);  // Adiciona a data e hora no panelBody

        Relogio relogio = new Relogio();

        int delay = 1000; // Atualiza a cada segundo
        new Timer(delay, (e) -> {
            lblDataHora.setText(relogio.getDataHoraAtual());
        }).start();
    }

    private void widgetContadorObjetos() {
        try {
            ObjetoVoadorDao objetoVoadorDao = new ObjetoVoadorDao();

            ArrayList<ObjetoVoador> objetosProximos = objetoVoadorDao.listarObjetosProximos(new Relogio().getDataAtual());

            System.out.println("Objetos detectados: " + objetosProximos.size()); // Log para depuração

            JLabel lblCount = new JLabel("Objetos Próximos: " + objetosProximos.size());
            lblCount.setFont(new Font("SansSerif", Font.BOLD, 18));
            lblCount.setHorizontalAlignment(JLabel.CENTER);

            panelBody.add(lblCount, BorderLayout.SOUTH);
            panelBody.revalidate();
            panelBody.repaint();  // Garantir atualização visual
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar contagem de objetos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void widgetProximasAproximacoes() {
        String[] columnNames = {"Nome", "Data de Aproximação", "Distância Mínima", "Risco"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        try {
            ObjetoVoadorDao objetoVoadorDao = new ObjetoVoadorDao();
            ArrayList<ObjetoVoador> proximasAproximacoes = objetoVoadorDao.listarObjetosProximos(new Relogio().getDataAtual());
            for (ObjetoVoador obj : proximasAproximacoes) {
                model.addRow(new Object[]{obj.getNome(), obj.getDataDeAproximacao(), obj.getDiametroMinKm(), obj.getRisco() ? "Sim" : "Não"});
            }
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            panelBody.add(scrollPane, BorderLayout.CENTER);  // Mudança aqui
            panelBody.revalidate();
            panelBody.repaint();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao acessar dados de aproximações: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setBackground(new java.awt.Color(155, 156, 237));
        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("Tela Dashboard");
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 362, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
