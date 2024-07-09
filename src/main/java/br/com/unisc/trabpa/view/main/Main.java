package br.com.unisc.trabpa.view.main;

import br.com.unisc.trabpa.model.Grafico;
import br.com.unisc.trabpa.view.form.PanelDashboard;
import br.com.unisc.trabpa.view.form.PanelAtualizaDados;
import br.com.unisc.trabpa.view.form.PanelGrafico;
import br.com.unisc.trabpa.view.form.PanelResultados;
import br.com.unisc.trabpa.view.form.PanelSobre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import br.com.unisc.trabpa.view.menu.MenuItem;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends javax.swing.JFrame {
    public Main() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        execute();
    }

    private void execute() {
        ImageIcon iconDados = new ImageIcon(getClass().getResource("/dados.png"));
        ImageIcon iconArquivo = new ImageIcon(getClass().getResource("/arquivo.png"));
        ImageIcon iconAjuda = new ImageIcon(getClass().getResource("/ajuda.png"));
        ImageIcon iconArrow = new ImageIcon(getClass().getResource("/arrow.png"));

        MenuItem menuDashboard = new MenuItem(iconArrow, "Dashboard", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new PanelDashboard());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });

        MenuItem manuDados = new MenuItem(iconArrow, "Atualizar dados", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelBody.removeAll();
                panelBody.add(new PanelAtualizaDados());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });
        MenuItem menuresultados = new MenuItem(iconArrow, "Resultados", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panelBody.removeAll();
                panelBody.add(new PanelResultados());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });

        MenuItem menuGrafico = new MenuItem(iconArrow, "Gráfico", new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panelBody.removeAll();
                panelBody.add(new PanelGrafico());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });

        MenuItem menuSobre = new MenuItem(iconArrow, "Sobre", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                PanelSobre pnlSobre = new PanelSobre();
                panelBody.removeAll();
                panelBody.add(pnlSobre);
                panelBody.repaint();
                panelBody.revalidate();
            }
        });

        MenuItem menuArquivo = new MenuItem(iconArquivo, "Arquivo", null, menuDashboard, menuGrafico);
        MenuItem menuDados = new MenuItem(iconDados, "Dados", null, manuDados, menuresultados);
        MenuItem menuAjuda = new MenuItem(iconAjuda, "Ajuda", null, menuSobre);

        addMenu(menuArquivo, menuDados, menuAjuda);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        panelBody = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelHeader.setBackground(new java.awt.Color(0, 204, 204));
        panelHeader.setPreferredSize(new java.awt.Dimension(561, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NASALYTICS.png"))); // NOI18N

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(413, Short.MAX_VALUE))
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
        );

        getContentPane().add(panelMenu, java.awt.BorderLayout.LINE_START);

        panelBody.setBackground(new java.awt.Color(255, 255, 255));
        panelBody.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setViewportView(jTextPane1);

        panelBody.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelBody, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(871, 473));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelMenu;
    // End of variables declaration//GEN-END:variables
}
