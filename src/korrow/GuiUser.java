/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korrow;

import korrow.Department;
import korrow.FrameInsert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class GuiUser extends javax.swing.JFrame {

    private DefaultTableModel model;
    Department d = new Department();

    /**
     * Creates new form GuiUser
     */
    public GuiUser() {
        initComponents();
        model = (DefaultTableModel) tableUser.getModel();
    }

    /*    public void insertUser() {
//        System.out.println("insert " + userId + " finish!");
        Connection con = null;
        User u = new User();
        try {
            String strMail = "";
            for (int i = 0; i < u.getEmail().size(); i++) {
                strMail += u.getEmail().get(i);
                if(i==u.getEmail().size()-1){
                    break;
                }
                strMail += ",";
                
            }
            String strTel = "";
            for (int i = 0; i < u.getTel().size(); i++) {
                strTel += u.getTel().get(i);
                if(i==u.getTel().size()-1){
                    break;
                }
                strTel += ",";
            }
            String sql = "INSERT into User (user_id,user_password,user_name,user_position,user_email,user_tel,user_type)";
            sql +=" values (?,?,?,?,?,?,?)";
            con = ConnectionBuilder.ConnectionBuilder();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, u.getUserId());
            statement.setString(2, u.getPassword());
            statement.setString(3, u.getUserName());
            statement.setString(4, u.getPosition());
            statement.setString(5, strMail);
            statement.setString(6, strTel);
            statement.setString(7, u.getUserTypeId());
            statement.execute();//ประมวลผลข้อมูล
            statement.close();//หยุดการประมวลผล
            
            formWindowOpened(null);
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());//ใช้แสดงความผิดปกติเป็นString
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    private String pass;
    public void clickTable() {
        try {
            int index = tableUser.getSelectedRow();
            String id = tableUser.getValueAt(index, 0).toString();
            String sql = "select * from User where user_id='" + id + "'";
            Connection con = Db1.getConnectionBuilder();;
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = con.createStatement().executeQuery(sql);
            if (rs.next()) {
                statement.execute();
                String textId = rs.getString("user_id");
                field_id.setText(textId);
//                String textPass = rs.getString("user_password");
//                field_pass.setText(textPass);
                String textName = rs.getString("user_name");
                field_name.setText(textName);
                String textPosition = rs.getString("user_position");
                field_position.setText(textPosition);
                String textType = rs.getString("user_type");
                pass = rs.getString("user_password");
                
                
                if(textType.equals("A")){
                    comboBox_type.setSelectedItem("Admin");
                }
                else if(textType.equals("B")){
                    comboBox_type.setSelectedItem("Borrower");
                }
                
                String textEmail = rs.getString("user_email");
                field_email.setText(textEmail);
                String textTel = rs.getString("user_tel");
                field_tel.setText(textTel);
                d.setDepId(rs.getInt("department_id"));
            }

            con.close();
        } catch (SQLException ex) {

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GuiUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectDep() {
        try {
            int depId = d.getDepId();
            String sql = "select * from Department where dep_id ='" + depId + "'";
            Connection con = Db1.getConnectionBuilder();
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = con.createStatement().executeQuery(sql);
            statement.executeQuery();
            if (rs.next()) {
                field_dep.setText(rs.getString("dep_name"));
                statement.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GuiUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GuiUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//        public void insertUser() {
////        System.out.println("insert " + userId + " finish!");
//        Connection con = null;
//        try {
//
//            String sql = "INSERT into User (user_id,user_password,user_name,user_position,user_email,user_tel,user_type)";
//            sql +=" values (?,?,?,?,?,?,?)";
//            con = ConnectionBuilder.ConnectionBuilder();
//            PreparedStatement statement = con.prepareStatement(sql);
//            User u = new User();
//            u.setUserId(field_id.getText());
//            statement.setString(1, u.getUserId());
//            u.setPassword(field_pass.getText());
//            statement.setString(2, u.getPassword());
//            u.setUserName(field_name.getText());
//            statement.setString(3, u.getUserName());
//            u.setPosition(field_position.getText());
//            statement.setString(4, u.getPosition());
//            statement.setString(5, field_email.getText());
//            ArrayList<String> arrListMail = new ArrayList<String>();
//            String arrEmail[] = field_email.getText().split(",");
//            for (String mail : arrEmail) {
//                arrListMail.add(mail);
//                u.setEmail(arrListMail);
//            }
//            
//            statement.setString(6, field_tel.getText());
//            ArrayList<String> arrListTel = new ArrayList<String>();
//            String arrTel[] = field_tel.getText().split(",");
//            for (String tel : arrTel) {
//                arrListTel.add(tel);
//                u.setTel(arrListTel);
//            }
//            
//            u.setUserTypeId(field_type.getText());
//            statement.setString(7, u.getUserTypeId());
//            statement.execute();//ประมวลผลข้อมูล
//            statement.close();//หยุดการประมวลผล
//            GuiUser gui = new GuiUser();
////            FormWindowOpened(null);
//            con.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
////            System.out.println(ex.getMessage());//ใช้แสดงความผิดปกติเป็นString
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    public boolean UpdateUser(){
//        boolean result = false;
//        try {
//            
//            int index = tableUser.getSelectedRow();
//            String id = tableUser.getValueAt(index, 0).toString();
////            String sql = "update User set (user_password,user_name,user_position,user_email,user_tel,user_type) ";
////            sql += "values (?,?,?,?,?,?) ";
////            sql += "where userId='"+id+"'";
//            String sql = "update User set";
//            sql += " user_password=?,";
//            sql += " user_name=?,";
//            sql += " user_position=?,";
//            sql += " user_email=?,";
//            sql += " user_tel=?,";
//            sql += " user_type=?";
//            sql += " WHERE user_id=?";
//            Connection con = ConnectionBuilder.ConnectionBuilder();
//            PreparedStatement statement = con.prepareStatement(sql);
//            User u =new User();
//            u.setPassword(field_pass.getText());
//            statement.setString(1, u.getPassword());
//            u.setUserName(field_name.getText());
//            statement.setString(2, u.getUserName());
//            u.setPosition(field_position.getText());
//            statement.setString(3, u.getPosition());
//            ArrayList<String> arrListMail = new ArrayList<String>();
//            String arrEmail[] = field_email.getText().split(",");
//            for (String mail : arrEmail) {
//                arrListMail.add(mail);
//                u.setEmail(arrListMail);
//            }
//            statement.setString(4, field_email.getText());
//           
//            ArrayList<String> arrListTel = new ArrayList<String>();
//            String arrTel[] = field_tel.getText().split(",");
//            for (String tel : arrTel) {
//                arrListTel.add(tel);
//                u.setTel(arrListTel);
//            }
//            statement.setString(5, field_tel.getText());
//            
//            u.setUserTypeId(field_type.getText());
//            statement.setString(6, u.getUserTypeId());
//            u.setUserId(field_id.getText());
//            statement.setString(7, u.getUserId());
//            statement.executeUpdate();
//            formWindowOpened(null);
//            statement.close();
//            con.close();
//            result = true;
//            
//            
//            
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(GuiUser.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(GuiUser.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return result;
//    }
    public void deleteUserById() {
        Connection con = null;
        try {
            int index = tableUser.getSelectedRow();
            String id = tableUser.getValueAt(index, 0).toString();

            String sql = "delete from User where user_id=" + id;
            con = Db1.getConnectionBuilder();
            PreparedStatement statement = con.prepareStatement(sql);//เป็นการเตรียมคำสั่งsqlสำหรับเรียกดูข้อมูลในตาราง
//            statement.setString(1, userId);
            statement.executeUpdate();//ประมวลผลข้อมูล
            statement.close();//หยุดการประมวลผล

            formWindowOpened(null);
            con.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GuiUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void searchUser() {
//        try {
//            String search = field_search.getText();
//            String sql = "SELECT * from User WHERE user_id LIKE('%" + search + "%')"
//                    + "OR user_name LIKE('%" + search + "%')";
//
//            Connection con = ConnectionBuilder.ConnectionBuilder();
//            PreparedStatement statement = con.prepareStatement(sql);
//            statement.execute();//ประมวลผลข้อมูล
//            statement.close();//หยุดการประมวลผล
//            con.close();
//        
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(GuiUser.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(GuiUser.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//
//    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Button_delete = new javax.swing.JButton();
        Button_insert = new javax.swing.JButton();
        Button_update = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        field_search = new javax.swing.JTextField();
        button_search = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        field_id = new javax.swing.JTextField();
        field_name = new javax.swing.JTextField();
        field_position = new javax.swing.JTextField();
        field_email = new javax.swing.JTextField();
        field_tel = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboBox_type = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        field_dep = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        Button_delete.setBackground(new java.awt.Color(255, 153, 0));
        Button_delete.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        Button_delete.setForeground(new java.awt.Color(153, 51, 0));
        Button_delete.setText("Delete");
        Button_delete.setBorder(null);
        Button_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_deleteMouseClicked(evt);
            }
        });
        Button_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_deleteActionPerformed(evt);
            }
        });

        Button_insert.setBackground(new java.awt.Color(255, 153, 0));
        Button_insert.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        Button_insert.setForeground(new java.awt.Color(153, 51, 0));
        Button_insert.setText("Insert");
        Button_insert.setBorder(null);
        Button_insert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_insertMouseClicked(evt);
            }
        });
        Button_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_insertActionPerformed(evt);
            }
        });

        Button_update.setBackground(new java.awt.Color(255, 153, 0));
        Button_update.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        Button_update.setForeground(new java.awt.Color(153, 51, 0));
        Button_update.setText("Update");
        Button_update.setBorder(null);
        Button_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_updateMouseClicked(evt);
            }
        });
        Button_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_updateActionPerformed(evt);
            }
        });

        tableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "user_id", "user_name", "user_position", "user_email", "user_tel", "user_type"
            }
        ));
        tableUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableUser);

        button_search.setText("search");
        button_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_searchActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel1.setText("User id");

        jLabel3.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel3.setText("Name");

        jLabel4.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel4.setText("Position");

        jLabel5.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel5.setText("E-mail");

        jLabel6.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel6.setText("Tel.");

        jLabel7.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel7.setText("Type");

        field_id.setEditable(false);
        field_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_idActionPerformed(evt);
            }
        });

        field_name.setEditable(false);

        field_position.setEditable(false);

        field_email.setEditable(false);

        field_tel.setEditable(false);

        jLabel8.setFont(new java.awt.Font("TH Sarabun New", 0, 36)); // NOI18N
        jLabel8.setText("Korrow");

        jLabel9.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel9.setText("Welcome admin");

        comboBox_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Borrower" }));

        jLabel2.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel2.setText("Department");

        field_dep.setEditable(false);

        jButton1.setText("refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("log out");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(field_email, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        .addComponent(field_tel)
                                        .addComponent(field_dep)
                                        .addComponent(field_id)
                                        .addComponent(field_name)
                                        .addComponent(field_position)
                                        .addComponent(comboBox_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(Button_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(Button_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(Button_update, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(field_search, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(button_search)
                                .addGap(91, 91, 91)
                                .addComponent(jButton1)))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(50, 50, 50))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(40, 40, 40))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(field_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_search)
                            .addComponent(jButton1))
                        .addGap(29, 29, 29)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                        .addGap(60, 60, 60))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(field_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(field_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(field_position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(field_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(field_tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(field_dep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(comboBox_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Button_insert)
                            .addComponent(Button_update)
                            .addComponent(Button_delete))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_deleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_deleteActionPerformed

    private void Button_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_updateMouseClicked
        // TODO add your handling code here:
//        int index = tableUser.getSelectedRow();
//        String id = tableUser.getValueAt(index, 0).toString();
//
//        FrameUpdate update = new FrameUpdate();
//        update.setVisible(true);
//        
//        formWindowOpened(null);
//        update.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }//GEN-LAST:event_Button_updateMouseClicked

    private void Button_insertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_insertMouseClicked
        // TODO add your handling code here:
//        FrameInsert insert = new FrameInsert();
//        insert.setVisible(true);
//        formWindowOpened(null);
//        insert.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }//GEN-LAST:event_Button_insertMouseClicked

    private void Button_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_deleteMouseClicked
        // TODO add your handling code here:
//        FrameDelete delete = new FrameDelete();
//        delete.setVisible(true);
//        delete.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteUserById();
    }//GEN-LAST:event_Button_deleteMouseClicked

    private void Button_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_updateActionPerformed

        try {
            User u = new User();
            u.setUserId(field_id.getText());
//            u.setPassword(field_pass.getText());
            u.setPassword(pass);
            u.setUserName(field_name.getText());
            u.setPosition(field_position.getText());
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
            String type = comboBox_type.getSelectedItem()+"";
            if(type.equals("Admin")){
                type = "A";
            }
            else if(type.equals("Borrower")){
                type = "B";
            }
            u.setUserTypeId(type);
//            comboBox_type.setSelectedItem("Admin");
//            comboBox_type.setSelectedItem("Borrower");
//            u.setUserTypeId(field_type.getText());
            u.updateUser();
            formWindowOpened(null);
        } catch (SQLException ex) {
            Logger.getLogger(GuiUser.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_Button_updateActionPerformed

    
//    public void showData(ArrayList<User> user){
//        DefaultTableModel model = new DefaultTableModel();
//        model.setColumnIdentifiers(new Object[]{"User_id","User_name","User_position","User_email","User_tel","User_type"});
//        Object[] row = new Object[6];
//        
//        for(int i =0; i< user.size();i++){
//            row[0] = user.get(i).getUserId();
//            row[1] = user.get(i).getUserName();
//            row[2] = user.get(i).getPosition();
//            row[3] = user.get(i).getEmail();
//            row[4] = user.get(i).getTel();
//            row[5] = user.get(i).getUserTypeId();
////            row[6] = d.get(i).ge
//            
//            model.addRow(row);
//        }
//        tableUser.setModel(model);
//    }
    
    public void search(String search) {
        Statement s = null;
        try {
            tableUser.setModel(new DefaultTableModel());
            DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
            model.addColumn("user_id");
            model.addColumn("user_name");
            model.addColumn("user_position");
            model.addColumn("user_email");
            model.addColumn("user_tel");
            model.addColumn("user_type");
            model.addColumn("department_id");
            model.addColumn("gdp");
            Connection con = Db1.getConnectionBuilder();
            String sql = "SELECT user_id,user_name,user_position,user_email,user_tel,user_type,department_id,gdp from User ";
            sql += "where user_name like '%"+search+"%'";
            sql += " OR user_id like '%"+search+"%'";
            s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            int row = 0;
            while (rs != null && rs.next()) {
                model.addRow(new Object[0]);
                model.setValueAt(rs.getString("user_id"), row, 0);
                model.setValueAt(rs.getString("user_name"), row, 1);
                model.setValueAt(rs.getString("user_position"), row, 2);
                model.setValueAt(rs.getString("user_email"), row, 3);
                model.setValueAt(rs.getString("user_tel"), row, 4);
                model.setValueAt(rs.getString("user_type"), row, 5);
                model.setValueAt(rs.getString("department_id"), row, 6);
                model.setValueAt(rs.getString("gdp"), row, 7);
                row++;
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GuiUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void populateData() {
        Statement s = null;
        try {
            tableUser.setModel(new DefaultTableModel());
            DefaultTableModel model = (DefaultTableModel) tableUser.getModel();
            model.addColumn("user_id");
            model.addColumn("user_name");
            model.addColumn("user_position");
            model.addColumn("user_email");
            model.addColumn("user_tel");
            model.addColumn("user_type");
            model.addColumn("department_id");
            model.addColumn("gdp");
            Connection con = Db1.getConnectionBuilder();
            String sql = "SELECT user_id,user_name,user_position,user_email,user_tel,user_type,department_id,gdp from User";
         
            s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            int row = 0;
            while (rs != null && rs.next()) {
                model.addRow(new Object[0]);
                model.setValueAt(rs.getString("user_id"), row, 0);
                model.setValueAt(rs.getString("user_name"), row, 1);
                model.setValueAt(rs.getString("user_position"), row, 2);
                model.setValueAt(rs.getString("user_email"), row, 3);
                model.setValueAt(rs.getString("user_tel"), row, 4);
                model.setValueAt(rs.getString("user_type"), row, 5);
                model.setValueAt(rs.getString("department_id"), row, 6);
                model.setValueAt(rs.getString("gdp"), row, 7);
                row++;
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GuiUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        populateData();
//        try {
//            String sql = "select * from User";
//            Connection con = ConnectionBuilder.ConnectionBuilder();
//            int totalRow = tableUser.getRowCount() - 1;
//            while (totalRow > -1) {
//                model.removeRow(totalRow--);
//            }
//            PreparedStatement statement = con.prepareStatement(sql);
//            ResultSet rs = statement.executeQuery();
////        int row = 0;
//            while (rs.next()) {
//                Vector row = new Vector();
//                
//                row.add(rs.getString("user_id"));
////                row.add(rs.getString("user_password"));
//                row.add(rs.getString("user_name"));
//                row.add(rs.getString("user_position"));
//                row.add(rs.getString("user_email"));
//                row.add(rs.getString("user_tel"));
//                row.add(rs.getString("user_type"));
////                row.add(rs.getInt("department_id"));
//                model.addRow(row);
//            }
//            rs.close();
//            con.close();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_formWindowOpened

    private void Button_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_insertActionPerformed
        // TODO add your handling code here:
        FrameInsert insert = new FrameInsert();
        insert.setVisible(true);

//        User u = new User();
//        u.setUserId(field_id.getText());
////        u.setPassword(field_pass.getText());
//        u.setUserName(field_name.getText());
//        u.setPosition(field_position.getText());
////            u.setEmail(field_email.getText());
////            u.setTel(field_tel.getText());
//        String arrTel[] = field_tel.getText().split(",");
//        ArrayList<String> arrListTel = new ArrayList<String>();
//        for (String tel : arrTel) {
//            arrListTel.add(tel);
//        }
//        u.setTel(arrListTel);
//        ArrayList<String> arrListMail = new ArrayList<String>();
//        String arrEmail[] = field_email.getText().split(",");
//        for (String mail : arrEmail) {
//            arrListMail.add(mail);
//        }
//
//        u.setEmail(arrListMail);
//        u.setUserTypeId(field_type.getText());
//        u.insertUser();
        formWindowOpened(null);

//        FrameInsert insert = new FrameInsert();
//        insert.setVisible(true);
////        formWindowOpened(null);
//        insert.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        

    }//GEN-LAST:event_Button_insertActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    
    private void button_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_searchActionPerformed
//        User u = new User();  
//        showData(u.searchUser(field_search.getText()));

            search(field_search.getText());
        
        /*try {
            String sql = "SELECT * FROM User where user_name like('%"+field_search.getText()+"%')";
            System.out.println(sql);
            Connection con = ConnectionBuilder.ConnectionBuilder();
            int totalRow = tableUser.getRowCount() - 1;
            System.out.println(totalRow);
            while (totalRow > -1) {
                model.removeRow(totalRow--);
            }
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
//        int row = 0;
            while (rs.next()) {
                Vector row = new Vector();
                
                row.add(rs.getString("user_id"));
//                row.add(rs.getString("user_password"));
                row.add(rs.getString("user_name"));
                row.add(rs.getString("user_position"));
                row.add(rs.getString("user_email"));
                row.add(rs.getString("user_tel"));
                row.add(rs.getString("user_type"));
                row.add(rs.getInt("department_id"));
                model.addRow(row);
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
//        showData(u.searchUser(field_search.getText()));
        
//          u.searchUser(field_search.getText());
//        searchUser();
//        formWindowOpened(null);
//        field_search.getText();

//        User u = new User();
//        u.serachByName(field_search.getText());
//        formWindowOpened(null);
// TODO add your handling code here:
        //searchUser();
        //formWindowOpened(null);
//        int totalRow = tableUser.getModel().getRowCount();
//        for (int i = 0; i < totalRow; i++) {
////            System.out.println(tableUser.getModel().getValueAt(i, 1));
//            if (field_search.getText().equalsIgnoreCase((String) tableUser.getModel().getValueAt(i, 1))) {
//                System.out.println(tableUser.getModel().getValueAt(i, 1));
//                break;
//            }
//        }
        //System.out.println(tableUser.getModel().getValueAt(1, 2));
    }//GEN-LAST:event_button_searchActionPerformed

    private void field_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_idActionPerformed

    private void tableUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUserMouseClicked
        // TODO add your handling code here:
        clickTable();

        selectDep();
    }//GEN-LAST:event_tableUserMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        formWindowOpened(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        super.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(GuiUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_delete;
    private javax.swing.JButton Button_insert;
    private javax.swing.JButton Button_update;
    private javax.swing.JButton button_search;
    private javax.swing.JComboBox<String> comboBox_type;
    private javax.swing.JTextField field_dep;
    private javax.swing.JTextField field_email;
    private javax.swing.JTextField field_id;
    private javax.swing.JTextField field_name;
    private javax.swing.JTextField field_position;
    private javax.swing.JTextField field_search;
    private javax.swing.JTextField field_tel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableUser;
    // End of variables declaration//GEN-END:variables
}
