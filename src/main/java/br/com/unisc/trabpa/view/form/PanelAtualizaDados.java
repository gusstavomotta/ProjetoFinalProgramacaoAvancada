package br.com.unisc.trabpa.view.form;

import br.com.unisc.trabpa.adapter.ObjetoVoadorAdapter;
import br.com.unisc.trabpa.dal.ObjetoVoadorDao;
import br.com.unisc.trabpa.dal.Requisicao;
import br.com.unisc.trabpa.model.ObjetoVoador;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class PanelAtualizaDados extends javax.swing.JPanel {

    private ObjetoVoadorDao objetoVoadorDao;

    public PanelAtualizaDados() {
        initComponents();
        objetoVoadorDao = new ObjetoVoadorDao();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtDataInicio = new javax.swing.JTextField();
        txtDataFim = new javax.swing.JTextField();
        btnAtualizar = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();

        txtDataInicio.setText("Formato: yyyy-MM-dd");
        txtDataInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataInicioActionPerformed(evt);
            }
        });

        txtDataFim.setText("Formato: yyyy-MM-dd");

        btnAtualizar.setText("Atualizar Dados:");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        lblStatus.setText("Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDataFim, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(txtDataInicio)
                            .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(338, 338, 338)
                        .addComponent(btnAtualizar)))
                .addContainerGap(351, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(txtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(txtDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(lblStatus)
                .addGap(113, 113, 113)
                .addComponent(btnAtualizar)
                .addContainerGap(162, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDataInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataInicioActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        String dataInicio = txtDataInicio.getText();
        String dataFim = txtDataFim.getText();

        System.out.println("CLICOU NO BOTAO ");
        System.out.println(dataInicio);
        System.out.println(dataFim);

        if (!dataInicio.matches("\\d{4}-\\d{2}-\\d{2}") || !dataFim.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Por favor, insira as datas no formato yyyy-MM-dd. Exemplo: 2024-07-05", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }
        lblStatus.setText("Atualizando...");
        btnAtualizar.setEnabled(false);

        new SwingWorker<Void, Void>() {
            protected Void doInBackground() throws Exception {
                try {

                    Iterator<Map.Entry<String, JsonNode>> dados = Requisicao.Request(dataInicio, dataFim);
                    ArrayList<ObjetoVoador> objetos = ObjetoVoadorAdapter.IteratorToObjetoVoadorList(dados);
                    for (ObjetoVoador obj : objetos) {
                        objetoVoadorDao.inserirObjetoVoadorNoBanco(obj);
                    }
                    lblStatus.setText("Dados atualizados com sucesso!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(PanelAtualizaDados.this, "Erro ao atualizar dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    lblStatus.setText("Erro ao atualizar dados.");
                }
                return null;
            }

            protected void done() {
                btnAtualizar.setEnabled(true);
            }
        }.execute();
    }//GEN-LAST:event_btnAtualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextField txtDataFim;
    private javax.swing.JTextField txtDataInicio;
    // End of variables declaration//GEN-END:variables
}
