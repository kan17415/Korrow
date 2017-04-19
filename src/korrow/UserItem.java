/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korrow;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kantarat
 */
public class UserItem extends javax.swing.JFrame {

    String ImgPath = null;
    int type;
    public static String tempUserName;
    /**
     * Creates new form UserItem
     */
    public UserItem() {
        initComponents();
        findItems(ItemUser.ListItems());
        tableItem3.setAutoCreateRowSorter(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     *
     *
     */
    /* public Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://54.169.139.189:3306/Project?zeroDateTimeBehavior=convertToNull",
                "sitfreshy", "123456789");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }    */
    public void findItems(ArrayList<ItemUser> items) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"item_id", "item_name", "item_owner_id", "item_unit", "item_capacity_amount", "item_current_amount", "item_note", "item_rent_detail", "item_available", "item_type", "category_item_id_category_item"});
        Object[] row = new Object[13];
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                row[0] = items.get(i).getId();
                row[1] = items.get(i).getName();
                row[2] = items.get(i).getOwnerId();
                row[3] = items.get(i).getUnit();
                row[4] = items.get(i).getCapaAmount();
                row[5] = items.get(i).getCurrentAmount();
                
                row[6] = items.get(i).getNote();
                row[7] = items.get(i).getRentDe();
                row[8] = items.get(i).getAvali();
                row[9] = items.get(i).getType();
                row[10] = items.get(i).getCata();
                model.addRow(row);
            }
            tableItem3.setModel(model);
        }
    }

    public ImageIcon ResizeImage(String imagePath, byte[] pic) {
        ImageIcon myImage = null;

        if (imagePath != null) {
            myImage = new ImageIcon(imagePath);
        } else {
            myImage = new ImageIcon(pic);
        }

        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTextField1_search = new javax.swing.JTextField();
        jButton1_btnSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_item = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_idOw = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        current = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1_insert = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        jButton3_Delete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        btn_choIm = new javax.swing.JButton();
        jTextField1_cur = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1_tran = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        txt_note = new javax.swing.JTextField();
        jComboBox1_current = new javax.swing.JComboBox<>();
        jradio2 = new javax.swing.JRadioButton();
        jradio1 = new javax.swing.JRadioButton();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableItem3 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jTextField1_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 141, -1));

        jButton1_btnSearch.setText("ค้นหา");
        jButton1_btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1_btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1_btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 20, -1, -1));

        jLabel1.setText("ไอดีของ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 60, 20));

        txt_id.setBackground(new java.awt.Color(0, 204, 204));
        txt_id.setBorder(null);
        jPanel1.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 100, -1));

        txt_item.setBackground(new java.awt.Color(0, 204, 204));
        txt_item.setBorder(null);
        jPanel1.add(txt_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 100, -1));

        jLabel2.setText("ชื่อของ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 60, 20));

        txt_idOw.setBackground(new java.awt.Color(0, 204, 204));
        txt_idOw.setBorder(null);
        txt_idOw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idOwActionPerformed(evt);
            }
        });
        jPanel1.add(txt_idOw, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 100, -1));

        jLabel3.setText("รหัสผู้ฝาก");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 60, 20));

        current.setBackground(new java.awt.Color(255, 204, 204));
        current.setBorder(null);
        current.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentActionPerformed(evt);
            }
        });
        jPanel1.add(current, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 30, -1));

        jLabel4.setText("จำนวน");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 60, 20));

        jLabel5.setText("Note Item");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 60, 20));

        jButton1_insert.setText("Insert");
        jButton1_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1_insertActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1_insert, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, -1, -1));

        btn_Update.setText("Update");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Update, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, -1, -1));

        jButton3_Delete.setText("Delete");
        jButton3_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3_DeleteActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3_Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 480, 80, -1));

        jLabel6.setText("รูปภาพ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        image.setBackground(new java.awt.Color(240, 255, 250));
        image.setOpaque(true);
        jPanel1.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 160, 100));

        btn_choIm.setText("Choose image");
        btn_choIm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_choImActionPerformed(evt);
            }
        });
        jPanel1.add(btn_choIm, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, -1, -1));

        jTextField1_cur.setBackground(new java.awt.Color(255, 204, 204));
        jTextField1_cur.setBorder(null);
        jPanel1.add(jTextField1_cur, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 60, -1));

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setFont(new java.awt.Font("Rockwell Condensed", 0, 24)); // NOI18N
        jLabel13.setText("     Korrow Borrower");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 280, 70));

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, -1, -1));

        jButton1_tran.setText("คำร้องขอ");
        jButton1_tran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1_tranActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1_tran, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, -1, -1));

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\Kantarat\\Downloads\\kickstarter (1).png")); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 90, 80));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 100, 10));

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 100, 10));

        jSeparator8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 100, 10));

        txt_note.setBackground(new java.awt.Color(153, 255, 255));
        txt_note.setBorder(null);
        txt_note.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_noteActionPerformed(evt);
            }
        });
        jPanel2.add(txt_note, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 170, 50));

        jComboBox1_current.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "เครื่อง ", "ชิ้น", "เเท่ง ", "เเผ่น ", "อัน ", "ผืน ", "ขวด" }));
        jComboBox1_current.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1_currentActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox1_current, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, -1, -1));

        buttonGroup1.add(jradio2);
        jradio2.setText("เครื่องไม่ใช้ไฟฟ้า");
        jradio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradio2ActionPerformed(evt);
            }
        });
        jPanel2.add(jradio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, -1, -1));

        buttonGroup1.add(jradio1);
        jradio1.setText("เครื่องใช้ไฟฟ้า");
        jradio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradio1ActionPerformed(evt);
            }
        });
        jPanel2.add(jradio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 530));

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 100, 10));

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 100, 10));

        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\Kantarat\\Downloads\\exit-to-app-button.png")); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 0, 50, 40));

        jPanel3.setBackground(new java.awt.Color(255, 102, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableItem3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name Item", "Owner Id", "Item Unit", "Item Capacity", "Item Current", "Item note", "Item rent", "Item available", "Item type", "Item Category"
            }
        ));
        tableItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableItem3MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableItem3);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 980, 450));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 1020, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1_btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1_btnSearchActionPerformed
        findItems(ItemUser.findItems(jTextField1_search.getText()));

    }//GEN-LAST:event_jButton1_btnSearchActionPerformed
    
    private void tableItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableItem3MouseClicked
        // TODO add your handling code here:
        try {
            int totalRow = tableItem3.getSelectedRow();
            String tableClick = (tableItem3.getModel().getValueAt(totalRow, 0).toString());
            
            String sql = "select * from Item where Item_Id = '" + tableClick + "'";
            Connection c = Db1.getConnectionBuilder();
            PreparedStatement pre = c.prepareStatement(sql);
            ResultSet rs = c.createStatement().executeQuery(sql);
            if (rs.next()) {
                pre.execute();
                String add1 = rs.getString("Item_Id");
                txt_id.setText(add1);
                String add2 = rs.getString("Item_Name");
                txt_item.setText(add2);
                String add3 = rs.getString("Item_Owner_Id");
                txt_idOw.setText(add3);
                String add4 = rs.getString("item_current_amount");
                current.setText(add4);
                GuiBorrowProfile gui1 = new GuiBorrowProfile();
                gui1.setCur(add4);
                System.out.println(gui1.getCur());
                String add5 = rs.getString("item_unit");
                jTextField1_cur.setText(add5);
                String add6 = rs.getString("item_note");
                txt_note.setText(add6);

                image.setIcon(ResizeImage(null, Item.ListItems().get(totalRow).getPicture()));
                
                
                c.close();
            }
            int totalRow1 = tableItem3.getSelectedRow();
            String tableClick1 = (tableItem3.getModel().getValueAt(totalRow, 5).toString());
            
            GuiBorrowProfile gui1 = new GuiBorrowProfile();
            gui1.setCur(tableClick1);
        } catch (Exception e) {
            e.getMessage();
        }

    }//GEN-LAST:event_tableItem3MouseClicked

    private void txt_idOwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idOwActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idOwActionPerformed

    private void currentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentActionPerformed

    private void txt_noteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_noteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_noteActionPerformed

    private void jButton1_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1_insertActionPerformed
        // TODO add your handling code here:

        try {
            Connection con = Db1.getConnectionBuilder();
            PreparedStatement ps = con.prepareStatement("insert into Item(item_id,item_name,item_owner_id,item_unit,item_current_amount,item_note,Image,category_item_id_category_item)"
                    + "values(?,?,?,?,?,?,?,1) ");
            ps.setString(1, txt_id.getText());
            ps.setString(2, txt_item.getText());
            ps.setString(3, txt_idOw.getText());
            ps.setString(4, jTextField1_cur.getText());
            ps.setString(5, current.getText());
            ps.setString(6, txt_note.getText());
            

            InputStream img = new FileInputStream(new File(ImgPath));
            ps.setBlob(7, img);
            if (ps.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "Image Inserted");
            }

            ps.executeUpdate();
            findItems(ItemUser.ListItems());
            JOptionPane.showMessageDialog(null, "Data Inserted");
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        //Test
        System.out.println("Name => " + txt_id.getText());
        System.out.println("ชื่อของ => " + txt_item.getText());
        System.out.println("Owner => " + txt_idOw.getText());
        System.out.println("Current => " + current.getText());
        System.out.println("Image => " + ImgPath);
    }//GEN-LAST:event_jButton1_insertActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        // TODO add your handling code here:
        if (ImgPath == null) {
            try {
                int index = tableItem3.getSelectedRow();
                String id = tableItem3.getValueAt(index, 0).toString();
                Connection con = Db1.getConnectionBuilder();
                PreparedStatement ps = con.prepareStatement("UPDATE Item SET item_name=?,item_owner_id=?,item_current_amount=?,item_unit=?,item_note=? WHERE item_id=?");

                ps.setString(1, txt_item.getText());
                ps.setString(2, txt_idOw.getText());

                ps.setString(3, current.getText());
                ps.setString(4, jTextField1_cur.getText());
                ps.setString(5, txt_note.getText());
                ps.setString(6, id);

                ps.executeUpdate();
                findItems(ItemUser.ListItems());
                con.close();
                JOptionPane.showMessageDialog(null, " Update Success");

            } catch (Exception ex) {
                Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void jButton3_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3_DeleteActionPerformed
        // TODO add your handling code here:
        try {
            int index = tableItem3.getSelectedRow();
            String id = tableItem3.getValueAt(index, 0).toString();

            Connection con = Db1.getConnectionBuilder();
            String sql = "DELETE FROM Item WHERE item_id = '" + id + "'";
            Db1.getConnectionBuilder().createStatement().executeUpdate(sql);
            findItems(ItemUser.ListItems());
            con.close();

            JOptionPane.showMessageDialog(null, "Deleted");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton3_DeleteActionPerformed

    private void btn_choImActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_choImActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            image.setIcon(ResizeImage(path, null));
            ImgPath = path;
        } else {
            System.out.println("No File");
        }

    }//GEN-LAST:event_btn_choImActionPerformed

    private void jComboBox1_currentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1_currentActionPerformed
        // TODO add your handling code here:
        jTextField1_cur.setText(jComboBox1_current.getSelectedItem().toString());

    }//GEN-LAST:event_jComboBox1_currentActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        findItems(ItemUser.ListItems());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jradio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradio1ActionPerformed
        // TODO add your handling code here:
        type = 1;
        System.out.println(type);
    }//GEN-LAST:event_jradio1ActionPerformed

    private void jradio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradio2ActionPerformed
        // TODO add your handling code here:
        type = 2;
        System.out.println(type);
    }//GEN-LAST:event_jradio2ActionPerformed

    private void jButton1_tranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1_tranActionPerformed
        // TODO add your handling code here:
        TranGUI t2 = new TranGUI();
        t2.setVisible(true);
    }//GEN-LAST:event_jButton1_tranActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    
    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(UserItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserItem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Update;
    private javax.swing.JButton btn_choIm;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JTextField current;
    private javax.swing.JLabel image;
    private javax.swing.JButton jButton1_btnSearch;
    private javax.swing.JButton jButton1_insert;
    private javax.swing.JButton jButton1_tran;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3_Delete;
    private javax.swing.JComboBox<String> jComboBox1_current;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField jTextField1_cur;
    private javax.swing.JTextField jTextField1_search;
    private javax.swing.JRadioButton jradio1;
    private javax.swing.JRadioButton jradio2;
    private javax.swing.JTable tableItem3;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_idOw;
    private javax.swing.JTextField txt_item;
    private javax.swing.JTextField txt_note;
    // End of variables declaration//GEN-END:variables
}