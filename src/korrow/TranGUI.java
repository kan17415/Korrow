/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korrow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kantarat
 */
public class TranGUI extends javax.swing.JFrame {
    private int check;
    public static String lender="";
    /**
     * Creates new form TranGUI
     */
    public TranGUI() {
        initComponents();
        OpenItems(ShowTran.ListTrans());
        table4.setAutoCreateRowSorter(true);
    }
    
   
    
    
    public void OpenItems(ArrayList<ShowTran> trans) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"transaction_id", "transaction_amount", "transaction_note", 
            "transaction_status", "transaction_borrow_date", 
            "transaction_return_date", "transaction_last_update", "user_id_borrow",
            "user_id_lender", "item_id"});
        Object[] colum = new Object[10];
        if (trans != null) {
            for (int i = 0; i < trans.size(); i++) {
                colum[0] = trans.get(i).getTran_id();
                colum[1] = trans.get(i).getTran_amount();
                colum[2] = trans.get(i).getTran_note();
                colum[3] = trans.get(i).getTran_sta();
                colum[4] = trans.get(i).getTran_bow();
                colum[5] = trans.get(i).getTran_return();
                colum[6] = trans.get(i).getTran_update();
                colum[7] = trans.get(i).getUser_bor();
                colum[8] = trans.get(i).getUser_len();
                colum[9] = trans.get(i).getItem_id();
                
                
                model.addRow(colum);
            }
            table4.setModel(model);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        table4 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jTextField1_namebo = new javax.swing.JTextField();
        jTextField1_test = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jTextField1_return = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        table4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table4MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table4);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 1000, -1));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 94, 100, 100));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setText("ดูข้อมูลคนยืม");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, -1, -1));

        jTextField1_namebo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1_nameboActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1_namebo, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 120, 30));
        jPanel2.add(jTextField1_test, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 130, 30));

        jButton4.setText("Reset");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 30, -1, -1));
        jPanel2.add(jTextField1_return, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 10, 40, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 510));

        jButton2.setText("jButton2");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        GuiBorrowProfile.tempUserName4 = jTextField1_namebo.getText();
        lender = jTextField1_test.getText();
        GuiBorrowProfile guiPro = new GuiBorrowProfile(this, true);
        
       
        
        guiPro.showProfile();
        guiPro.setVisible(true);
       
        //System.out.println("Test : "+GuiProfile.tempUserName3);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void table4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table4MouseClicked
        // TODO add your handling code here:
        
        try {
            int totalRow = table4.getSelectedRow();
            String tableClick = (table4.getModel().getValueAt(totalRow, 7).toString());
            jTextField1_namebo.setText(tableClick);
            
            int totalRow1 = table4.getSelectedRow();
            String tableClick1 = (table4.getModel().getValueAt(totalRow, 0).toString());
            jTextField1_return.setText(tableClick1);
            
            int totalRow2 = table4.getSelectedRow();
            String tableClick2 = (table4.getModel().getValueAt(totalRow, 1).toString());
             
            String tableClick3 = (table4.getModel().getValueAt(totalRow, 9).toString());
            
            GuiBorrowProfile gui3 = new GuiBorrowProfile();
            gui3.setItemId(tableClick3);
            
            
             GuiBorrowProfile gui = new GuiBorrowProfile();
             gui.setLender(tableClick1);
             
            
            GuiBorrowProfile gui1 = new GuiBorrowProfile();
            gui1.setReCur(tableClick2);
             
             
            
        } catch (Exception e) {
            e.getMessage();
        }
    }//GEN-LAST:event_table4MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1_nameboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1_nameboActionPerformed
        // TODO add your handling code here:
        //GuiProfile.tempUserName3 = jTextField1_namebo.getText();
    }//GEN-LAST:event_jTextField1_nameboActionPerformed

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
            java.util.logging.Logger.getLogger(TranGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TranGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TranGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TranGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TranGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1_namebo;
    private javax.swing.JTextField jTextField1_return;
    private javax.swing.JTextField jTextField1_test;
    private javax.swing.JTable table4;
    // End of variables declaration//GEN-END:variables

    
}
