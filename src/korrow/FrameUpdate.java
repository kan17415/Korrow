/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korrow;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class FrameUpdate extends javax.swing.JFrame {

    /**
     * Creates new form FrameUpdate
     */
    public FrameUpdate() {
        initComponents();
    }
//    public void setPassword(String pass){
//        passwordField.setText(pass);
//    }
//    public void setName(String name){
//        field_name.setText(name);
//    }
//    public void setPosition(String position){
//        field_position.setText(position);
//    }
//    public void setEmail(String email){
//        field_email.setText(email);
//    }
//    public void setTel(String tel){
//        field_tel.setText(tel);
//    }
//    public void setType(String type){
//        field_type.setText(type);
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        field_name = new javax.swing.JTextField();
        field_position = new javax.swing.JTextField();
        field_email = new javax.swing.JTextField();
        field_tel = new javax.swing.JTextField();
        field_type = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        button_Update = new javax.swing.JButton();
        button_Cancel = new javax.swing.JButton();
        label_id = new javax.swing.JLabel();
        field_id = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("password");

        jLabel2.setText("name");

        jLabel3.setText("position");

        jLabel4.setText("email");

        jLabel5.setText("Tel");

        jLabel6.setText("Type");

        field_position.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_positionActionPerformed(evt);
            }
        });

        button_Update.setText("Update");
        button_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_UpdateActionPerformed(evt);
            }
        });

        button_Cancel.setText("Cancel");
        button_Cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_CancelMouseClicked(evt);
            }
        });

        label_id.setText("id");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(label_id))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(field_type)
                            .addComponent(field_tel)
                            .addComponent(field_position)
                            .addComponent(field_name)
                            .addComponent(field_email, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(field_id)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(button_Update)
                        .addGap(35, 35, 35)
                        .addComponent(button_Cancel)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_id)
                    .addComponent(field_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(field_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(field_position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(field_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(field_tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_Update)
                    .addComponent(button_Cancel))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void field_positionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_positionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_positionActionPerformed

    private void button_CancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_CancelMouseClicked
        // TODO add your handling code here:
//        GuiUser user = new GuiUser();
//        user.setVisible(true);
        super.dispose();
    }//GEN-LAST:event_button_CancelMouseClicked
  
    private void button_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_UpdateActionPerformed
        try {
            
            User u = new User();
//            GuiUser gui = new GuiUser();
//            String sql = "SELECT * from User where user_id = GuiUser.id";
//            field_id.setText(string);
            u.setUserId(field_id.getText());
            u.setPassword(passwordField.getText());
            u.setUserName(field_name.getText());
            u.setPosition(field_position.getText());
//        u.setEmail(field_email.getText());
//        u.setTel(field_tel.getText());

            String arrTel[] = field_tel.getText().split(",");
            ArrayList<String> arrListTel = new ArrayList<String>();
            for (String tel : arrTel) {
                arrListTel.add(tel);
            }
            u.setTel(arrListTel);
            ArrayList<String> arrListMail = new ArrayList<String>();
            String arrEmail[] = field_email.getText().split(",");
            for (String mail : arrEmail) {
                arrListMail.add(mail);
            }
            u.setEmail(arrListMail);
            u.setUserTypeId(field_type.getText());
            u.updateUser();

        } catch (SQLException ex) {
            Logger.getLogger(FrameUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_button_UpdateActionPerformed

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
            java.util.logging.Logger.getLogger(FrameUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameUpdate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Cancel;
    private javax.swing.JButton button_Update;
    private javax.swing.JTextField field_email;
    private javax.swing.JTextField field_id;
    private javax.swing.JTextField field_name;
    private javax.swing.JTextField field_position;
    private javax.swing.JTextField field_tel;
    private javax.swing.JTextField field_type;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel label_id;
    private javax.swing.JPasswordField passwordField;
    // End of variables declaration//GEN-END:variables

}
