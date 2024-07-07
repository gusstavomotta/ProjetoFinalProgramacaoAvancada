/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unisc.trabpa.view.main;

import br.com.unisc.trabpa.dal.ObjetoVoadorDao;
import br.com.unisc.trabpa.model.ObjetoVoador;
import br.com.unisc.trabpa.model.Relogio;
import br.com.unisc.trabpa.view.form.Panel1;
import br.com.unisc.trabpa.view.form.pnlSobre;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import br.com.unisc.trabpa.view.menu.MenuItem;
import java.awt.Dimension;
import java.net.URL;
import java.sql.SQLException;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Matheus
 */
public class Main extends javax.swing.JFrame {

    private JLabel lblDataHora;


    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        adicionaWidgets();
        execute();
    }

    private void adicionaWidgets() {
        widgetRelogio();
        widgetContadorObjetos();
        widgetProximasAproximacoes();
    }

    private void execute() {
        ImageIcon iconDados = new ImageIcon(getClass().getResource("/dados.png"));
        ImageIcon iconConfig = new ImageIcon(getClass().getResource("/configuraçao.png"));
        ImageIcon iconArquivo = new ImageIcon(getClass().getResource("/arquivo.png"));
        ImageIcon iconAjuda = new ImageIcon(getClass().getResource("/ajuda.png"));
        ImageIcon iconArrow = new ImageIcon(getClass().getResource("/arrow.png"));

        //  CRIA SUBMENU ARQUIVO
        MenuItem menuDashboard = new MenuItem(iconArrow, "Dashboard", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.add(new Panel1());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });
        MenuItem menuSair = new MenuItem(iconArrow, "Sair", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0); // Fecha o programa
            }
        });

        //  CRIA SUBMENU DADOS
        MenuItem manuDados = new MenuItem(iconArrow, "Atualizar dados", null);
        MenuItem menuresultados = new MenuItem(iconArrow, "Resultados", null);

        //  CRIA SUBMENU CONFIGURAÇÕES 
        MenuItem menuPref = new MenuItem(iconArrow, "Preferências", null);

        // CRIA SUBMENU AJUDA
        MenuItem menuSobre = new MenuItem(iconArrow, "Sobre", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pnlSobre pnlSobre = new pnlSobre();
                panelBody.removeAll();
                panelBody.add(pnlSobre);
                panelBody.repaint();
                panelBody.revalidate();
            }
        });

        MenuItem menuArquivo = new MenuItem(iconArquivo, "Arquivo", null, menuDashboard, menuSair);
        MenuItem menuDados = new MenuItem(iconDados, "Dados", null, manuDados, menuresultados);
        MenuItem menuConfig = new MenuItem(iconConfig, "Configurações", null, menuPref);
        MenuItem menuAjuda = new MenuItem(iconAjuda, "Ajuda", null, menuSobre);

        addMenu(menuArquivo, menuDados, menuConfig, menuAjuda);
    }

    private void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            menus.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);
            }
        }
        menus.revalidate();
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        panelMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        panelBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelHeader.setBackground(new java.awt.Color(0, 204, 204));
        panelHeader.setPreferredSize(new java.awt.Dimension(561, 50));

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 855, Short.MAX_VALUE)
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        getContentPane().add(panelHeader, java.awt.BorderLayout.PAGE_START);

        panelMenu.setBackground(new java.awt.Color(115, 120, 230));
        panelMenu.setPreferredSize(new java.awt.Dimension(250, 384));

        jScrollPane1.setBorder(null);

        menus.setBackground(new java.awt.Color(0, 204, 204));
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(menus);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
        );

        getContentPane().add(panelMenu, java.awt.BorderLayout.LINE_START);

        panelBody.setBackground(new java.awt.Color(255, 255, 255));
        panelBody.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelBody, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(871, 473));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelMenu;
    // End of variables declaration//GEN-END:variables
}
