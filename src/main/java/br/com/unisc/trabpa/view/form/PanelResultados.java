package br.com.unisc.trabpa.view.form;

import br.com.unisc.trabpa.dal.ObjetoVoadorDao;
import br.com.unisc.trabpa.model.ObjetoVoador;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.BadAttributeValueExpException;

public class PanelResultados extends javax.swing.JPanel {

    ObjetoVoadorDao dao = new ObjetoVoadorDao();
    private List<ObjetoVoador> objetosVoadores;

    public PanelResultados() {
        this.setBackground(Color.WHITE);
        initComponents();
    }

    private void ordenarDados() throws SQLException {
        if (jComboBox1.getSelectedIndex() != 0) {
            List<ObjetoVoador> ordenados = null;
            try {
                ordenados = dao.ordernarObjetosPorAtributo(getStringAtributoComboBox1());
            } catch (BadAttributeValueExpException ex) {
                Logger.getLogger(PanelResultados.class.getName()).log(Level.SEVERE, null, ex);
            }
            atualizarTabela(ordenados);
        }
    }

    private void filtrarDados() throws SQLException, BadAttributeValueExpException {
        List<ObjetoVoador> filtrados = null;
        filtrados = dao.filtrarObjetos(getStringAtributoComboBox2(), getStringAtributoComboBox3(), getStringAtributoComboBox1());
        atualizarTabela(filtrados);
    }

    private String getStringAtributoComboBox1() {
        return switch ((String) jComboBox1.getSelectedItem()) {
            case "Distância" ->
                "distancia";
            case "Risco" ->
                "risco";
            case "Velocidade" ->
                "velocidadeAproxKm";
            default ->
                "diametroMaxKm";
        };
    }

    private String getStringAtributoComboBox2() {
        String selectedItem = (String) jComboBox2.getSelectedItem();
        if (selectedItem == null) {
            return "default"; // valor padrão ou lançamento de exceção
        }

        return switch (selectedItem) {
            case "Distância" ->
                "distancia";
            case "Risco" ->
                "risco";
            case "Velocidade" ->
                "velocidadeAproxKm";
            case "Tamanho do objeto" ->
                "diametroMaxKm";
            default ->
                "default"; // valor padrão ou lançamento de exceção
        };
    }

    private String getStringAtributoComboBox3() {
        String selectedItem = (String) jComboBox3.getSelectedItem();
        if (selectedItem == null) {
            return "default"; // valor padrão ou lançamento de exceção
        }

        return switch (selectedItem) {
            case "True" ->
                "true";
            case "False" ->
                "0";
            case "Até 10000000km" ->
                "10000000";
            case "Até 50000000km" ->
                "50000000";
            case "Até 100000000km" ->
                "100000000";
            case "Até 0,5" ->
                "0.5";
            case "Até 1" ->
                "1";
            case "Até 2" ->
                "2";
            case "10000km/h" ->
                "10000";
            case "50000km/h" ->
                "50000";
            case "100000km/h" ->
                "100000";
            default ->
                "default"; // valor padrão ou lançamento de exceção
        };
    }

    private void atualizarTabela(List<ObjetoVoador> dados) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        for (ObjetoVoador obj : dados) {
            model.addRow(new Object[]{
                obj.getDistancia(),
                obj.getDiametroMaxKm(),
                obj.getVelocidadeAproxKm(),
                obj.getRisco()

            });
        }

    }

    private void inserirAtributosComboBox3(String s) {
        jComboBox3.removeAllItems();
        switch (s) {
            case "Distância" -> { // Distância
                jComboBox3.addItem("Até 10000000km");
                jComboBox3.addItem("Até 50000000km");
                jComboBox3.addItem("Até 100000000km");
            }
            case "Tamanho do objeto" -> { // Tamanho do objeto
                jComboBox3.addItem("Até 0,5");
                jComboBox3.addItem("Até 1");
                jComboBox3.addItem("Até 2");
            }
            case "Velocidade" -> { // Velocidade
                jComboBox3.addItem("10000km/h");
                jComboBox3.addItem("50000km/h");
                jComboBox3.addItem("100000km/h");
            }
            case "Risco" -> { // Risco
                jComboBox3.addItem("True");
                jComboBox3.addItem("False");
            }
            default ->
                jComboBox3.addItem(" ");
        }
    }

    @SuppressWarnings("unchecked")


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();

        setAutoscrolls(true);

        jLabel1.setText("Ordenar por:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Distância", "Tamanho do objeto", "Velocidade", "Risco" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Distância", "Tamanho do objeto", "Velocidade", "Risco"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Filtrar por:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Distância", "Tamanho do objeto", "Velocidade", "Risco" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Valor do atributo:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(61, 61, 61))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        try {
            ordenarDados();
        } catch (SQLException ex) {
            Logger.getLogger(PanelResultados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        inserirAtributosComboBox3((String) jComboBox2.getSelectedItem());
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        if (jComboBox2.getSelectedIndex() != 0) {
            try {
                filtrarDados();
            } catch (SQLException ex) {
                Logger.getLogger(PanelResultados.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadAttributeValueExpException ex) {
                Logger.getLogger(PanelResultados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
