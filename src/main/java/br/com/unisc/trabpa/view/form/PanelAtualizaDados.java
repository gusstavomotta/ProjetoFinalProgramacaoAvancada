package br.com.unisc.trabpa.view.form;

import br.com.unisc.trabpa.adapter.ObjetoVoadorAdapter;
import br.com.unisc.trabpa.dal.ObjetoVoadorDao;
import br.com.unisc.trabpa.dal.Requisicao;
import br.com.unisc.trabpa.model.ObjetoVoador;
import com.fasterxml.jackson.databind.JsonNode;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class PanelAtualizaDados extends javax.swing.JPanel {

    private ObjetoVoadorDao objetoVoadorDao;
    private JDateChooser dateChooserInicio;
    private JDateChooser dateChooserFim;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JLabel lblStatus;

    public PanelAtualizaDados() {
        this.setBackground(Color.WHITE);
        initComponents();
        objetoVoadorDao = new ObjetoVoadorDao();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        dateChooserInicio = new JDateChooser();
        dateChooserFim = new JDateChooser();
        btnAtualizar = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();

        dateChooserInicio.setDateFormatString("yyyy-MM-dd");
        dateChooserFim.setDateFormatString("yyyy-MM-dd");

        btnAtualizar.setText("Atualizar Dados");
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
                                                        .addComponent(dateChooserFim, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                                        .addComponent(dateChooserInicio)
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
                                .addComponent(dateChooserInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(dateChooserFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(lblStatus)
                                .addGap(113, 113, 113)
                                .addComponent(btnAtualizar)
                                .addContainerGap(162, Short.MAX_VALUE))
        );
    }

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {
        Date dataInicio = dateChooserInicio.getDate();
        Date dataFim = dateChooserFim.getDate();
       
        if (dataInicio == null || dataFim == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione ambas as datas.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!dataFim.after(dataInicio)) {
            JOptionPane.showMessageDialog(this, "A data final deve ser maior que a data inicial.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Calcula a diferença em dias
        long difEmMiliSec = Math.abs(dataFim.getTime() - dataInicio.getTime());
        long difEmDias = TimeUnit.DAYS.convert(difEmMiliSec, TimeUnit.MILLISECONDS);
        
        if (difEmDias > 7){
            JOptionPane.showMessageDialog(this, "O intervalo deve ser de no máximo 1 semana.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        lblStatus.setText("Atualizando...");
        btnAtualizar.setEnabled(false);

        new SwingWorker<Void, Void>() {
            protected Void doInBackground() throws Exception {
                try {
                    String dataInicioStr = new java.text.SimpleDateFormat("yyyy-MM-dd").format(dataInicio);
                    String dataFimStr = new java.text.SimpleDateFormat("yyyy-MM-dd").format(dataFim);

                    Iterator<Map.Entry<String, JsonNode>> dados = Requisicao.Request(dataInicioStr, dataFimStr);
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
    }
}
