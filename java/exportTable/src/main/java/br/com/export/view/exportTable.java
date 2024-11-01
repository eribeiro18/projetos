/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.export.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author evandro
 */
public class exportTable extends javax.swing.JFrame {

    private Connection connection = null;
    private boolean sucessoCon = false;
    private String[] tb;

    /**
     * Creates new form exportTable
     */
    public exportTable() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        cbBase = new javax.swing.JComboBox<>();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        edtAreaTabelas = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        edtAreaCondicao = new javax.swing.JTextArea();
        btnExporta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setTitle("Dados de Conexão ao Banco de Dados");
        jInternalFrame1.setVisible(true);

        jLabel1.setText("Banco");

        cbBase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CsfTeste1", "CsfTeste2", "CsfTeste3", "Quality", "Desenv" }));
        cbBase.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cbBase, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jInternalFrame2.setTitle("Informações para Exportação");
        jInternalFrame2.setVisible(true);

        jLabel8.setText("Tabelas");

        edtAreaTabelas.setColumns(20);
        edtAreaTabelas.setRows(5);
        edtAreaTabelas.setToolTipText("Informe uma tabela ou mais separadas por ponto e virgula");
        jScrollPane1.setViewportView(edtAreaTabelas);

        jLabel9.setText("Condições");

        edtAreaCondicao.setColumns(20);
        edtAreaCondicao.setRows(3);
        edtAreaCondicao.setToolTipText("Informe a codição que será utilizada em todas as tabelas incluidas no campo acima.");
        jScrollPane2.setViewportView(edtAreaCondicao);

        btnExporta.setText("Exportar");
        btnExporta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jInternalFrame2Layout.createSequentialGroup()
                        .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExporta)))
                .addContainerGap())
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExporta)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jInternalFrame1)
                    .addComponent(jInternalFrame2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jInternalFrame2)
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExportaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportaActionPerformed
        if (this.validaTabela()) {
            this.conecta();
            this.exportarDados();
        } else {
            JOptionPane.showMessageDialog(null, "Informe a(s) tabela(s) que deseja exportar.");
        }
    }//GEN-LAST:event_btnExportaActionPerformed

    private void exportarDados() {
        List<StringBuilder> result = this.consultaDados(this.edtAreaTabelas.getText(), this.edtAreaCondicao.getText());
        for (StringBuilder str : result) {
            FileWriter arq = null;
            try {
                int index = result.indexOf(str);
                arq = new FileWriter("." + File.separator + tb[index] + ".txt");
                PrintWriter gravarArq = new PrintWriter(arq);
                gravarArq.printf(str.toString());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao salvar o arquivo... " + ex.getMessage());
            } finally {
                try {
                    arq.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Falha ao salvar o arquivo... " + ex.getMessage());
                }
            }
        }
        if(result.isEmpty()){
            JOptionPane.showMessageDialog(null, "Concluido! Para a(s) tabela(s) não foram identificado registros ou não foi localizado a tabela na base!");
        }else{
            JOptionPane.showMessageDialog(null, "Concluido com sucesso! Caso falte algum arquivo ou não existia registros ou não foi localizado a tabela na base!");
        }
    }

    private List<StringBuilder> consultaDados(String tabela, String condicoes) {
        List<StringBuilder> txtList = new ArrayList<>();
        List<String> consultas = new ArrayList<>();
        tb = null;
        tb = tabela.contains(";") ? tabela.split(";") : new String[]{tabela};
        for (int i = 0; i < tb.length; i++) {
            if (!tb[i].equals("")) {
                consultas.add("SELECT * FROM " + tb[i] + " " + condicoes);
            }
        }
        for (String sql : consultas) {
            int index = consultas.indexOf(sql);
            StringBuilder txtTb = new StringBuilder();
            try {
                PreparedStatement stmt = this.connection.prepareStatement(sql);
                ResultSet rsCountColuns = stmt.executeQuery();
                rsCountColuns.next();
                int count = countColuns(rsCountColuns);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    do {
                        txtTb.append("|").append(tb[index].toUpperCase()).append("|");
                        for (int j = 1; j <= count; j++) {
                            txtTb.append(rs.getString(j)).append("|");
                        }
                        txtTb.append("\n");
                    } while (rs.next());
                    txtList.add(txtTb);
                }
            } catch (SQLException ex) {
                Logger.getLogger(exportTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return txtList;
    }

    private int countColuns(ResultSet rs) {
        int i = 1;
        while (true) {
            try {
                rs.getString(i);
                i++;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return --i;
            }
        }
    }

    private void conecta() {
        try {
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);
            String url = this.montaURL();
            String usuario = "";
            String senha = "";
            connection = DriverManager.getConnection(url, usuario, senha);
            this.sucessoCon = true;
        } catch (SQLException | ClassNotFoundException e) {
            this.sucessoCon = false;
            JOptionPane.showMessageDialog(null, "Falha ao conectar ao banco de dados... " + e.getMessage());
        }
    }
    
    private boolean validaTabela() {
        boolean valida = true;
        if (this.edtAreaTabelas.getText().equals("")) {
            valida = false;
        }
        return valida;
    }

    private String montaURL() {
        String url = "";
        String o = (String) this.cbBase.getSelectedItem();
        if(o.equals("db1")){
            url = "jdbc:oracle:thin:@192.168.1.20:1521:db1";
        }else if(o.equals("db2")){
            url = "jdbc:oracle:thin:@192.168.1.20:1521:db2";
        }else if(o.equals("db3")){
            url = "jdbc:oracle:thin:@192.168.1.20:1521:db3";
        }else if(o.equals("db4")){
            url = "jdbc:oracle:thin:@192.168.1.10:1521:db4";
        }else {
            url = "jdbc:oracle:thin:@192.168.6:9283:db5";
        }
        return url;
    }

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
            java.util.logging.Logger.getLogger(exportTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(exportTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(exportTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(exportTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new exportTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExporta;
    private javax.swing.JComboBox<String> cbBase;
    private javax.swing.JTextArea edtAreaCondicao;
    private javax.swing.JTextArea edtAreaTabelas;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
