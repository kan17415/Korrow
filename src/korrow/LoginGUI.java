package korrow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import static korrow.Login.Login;
public class LoginGUI extends javax.swing.JFrame {
    public static String tempUser;
    public LoginGUI() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filed_username = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        filed_password = new javax.swing.JPasswordField();
        button_login = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        filed_username.setBackground(new java.awt.Color(255, 153, 153));
        filed_username.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        filed_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filed_usernameActionPerformed(evt);
            }
        });
        getContentPane().add(filed_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 45, 226, 30));

        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Username");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 45, 72, 30));

        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("Password");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 93, -1, 30));

        filed_password.setBackground(new java.awt.Color(255, 153, 153));
        getContentPane().add(filed_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 93, 226, 30));

        button_login.setBackground(new java.awt.Color(255, 204, 204));
        button_login.setForeground(new java.awt.Color(255, 51, 51));
        button_login.setText("Login");
        button_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_loginActionPerformed(evt);
            }
        });
        getContentPane().add(button_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 141, -1, 32));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 230));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_loginActionPerformed
        //       JOptionPane.showMessageDialog(this, "Sawatdee : " );
        ItemUser.tempUserName = filed_username.getText();
        ShowTran.tempUserName1 = filed_username.getText();
        Login(filed_username.getText(),filed_password.getText(), new UserItem());

        
        /*try{
            String sql ="select * From User where user_id=? and user_password =?";
            Connection con = Db1.getConnectionBuilder();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,filed_username.getText());
            ps.setString(2,filed_password.getText());
            
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(null,"ยินดีต้อนรับคุณ  "+rs.getString("user_name"));
                
                
                if(rs.getString("user_type").equals("A")){
                    
                }else if(rs.getString("user_type").equals("B")){
                    
                }else if(rs.getString("user_type").equals("L")){
                    
                }
                 UserItem t1 =new UserItem();
                 t1.setVisible(true);
            }else{
                //System.out.println("Incorrect ID or Password");
                JOptionPane.showMessageDialog(null, "Incorrect ID or Password" );
            }
            con.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }*/
       
        
       
        
       
        

        // TODO add your handling code here:
    }//GEN-LAST:event_button_loginActionPerformed

    private void filed_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filed_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filed_usernameActionPerformed
    
    public  static void close(){
        
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
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_login;
    private javax.swing.JPasswordField filed_password;
    private javax.swing.JTextField filed_username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
