package br.com.unisc.trabpa.view.form;
import br.com.unisc.trabpa.dal.ObjetoVoadorDao;
import br.com.unisc.trabpa.model.ObjetoVoador;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;


public class PanelResultados extends javax.swing.JPanel {

    private List<ObjetoVoador> objetosVoadores;

    public PanelResultados() {
        initComponents();
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarDados();
            }
        });

        jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarDados();
            }
        });
    }
     private void carregarDados() {
        ObjetoVoadorDao dao = new ObjetoVoadorDao();
        try {
            objetosVoadores = dao.obterTodosObjetosVoadores();
            atualizarTabela(objetosVoadores);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     private void ordenarDados() {
        if (objetosVoadores == null || objetosVoadores.isEmpty()) {
            return;
        }

        String criterio = (String) jComboBox1.getSelectedItem();
        List<ObjetoVoador> ordenados = objetosVoadores.stream().sorted(getComparator(criterio)).collect(Collectors.toList());
        atualizarTabela(ordenados);
    }
     
     private Comparator<ObjetoVoador> getComparator(String criterio) {
        switch (criterio) {
            case "Distância":
                return Comparator.comparing(ObjetoVoador::getDistancia);
            case "Tamanho do Objeto":
                return Comparator.comparing(ObjetoVoador::getDiametroMaxKm, Comparator.reverseOrder()); 
            case "Velocidade":
                return Comparator.comparing(ObjetoVoador::getVelocidadeAproxKm).reversed();
            case "Risco":
                return Comparator.comparing(ObjetoVoador::getCategoriaRisco).reversed();  
            default:
                return Comparator.comparing(ObjetoVoador::getDistancia);
        }
    }
     
     private void atualizarTabela(List<ObjetoVoador> dados) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        for (ObjetoVoador obj : dados) {
            model.addRow(new Object[]{
                obj.getDistancia(),
                obj.getDiametroMaxKm(),
                obj.getVelocidadeAproxKm(),
                obj.getCategoriaRisco()
                
            
            });
        }
        
    }

    @SuppressWarnings("unchecked")
 

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setAutoscrolls(true);

        jLabel1.setText("Ordenar por:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Distância", "Tamanho do objeto", "Velocidade", "Risco" }));

        jButton1.setText("Carregar");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
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
                        .addGap(88, 88, 88)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
