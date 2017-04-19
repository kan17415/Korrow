/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korrow;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class GuiBorrower extends javax.swing.JFrame {

    /**
     * Creates new form GuiBorrower
     */
    
    
  
    
    Transaction t = new Transaction();
    long remainItem = 0;//สร้างตัวแปร remainItem

    public void icon(){//ใช้ในการดึงรูป icon มาแสดง
        Image refreshIcon = new ImageIcon(this.getClass().getResource("bell.png")).getImage();
        label_icon.setIcon(new ImageIcon(refreshIcon.getScaledInstance(label_icon.getWidth(), label_icon.getHeight(), 0)));
    }

    public GuiBorrower() {
        initComponents();
    }
    
    String notice = "";//สร้างตัวแปร notice ในการเก็บข้อความที่จะแสดง
    int count;//ใช้ในการนับรายการที่แสดง
    public void noticeUser() {
        try {
            User u = new User();
            String sql = "select transaction_return_date,Item.item_name from Transaction INNER JOIN Item ";
            sql += "where user_id_borrow='" + u.getUserId() + "' AND transaction_status=3 ";
            sql += "AND Transaction.item_id = Item.item_id";
            Connection con = Db1.getConnectionBuilder();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {//วนลูปเพื่อดึงค่าแต่ละอันมาเช็ค
                //ดึงวันที่ที่ต้องคืนของมาจาก database
                String returnDateStr = rs.getString("transaction_return_date");
                //ตัด String ส่วนที่เป็นวันที่ ที่อยู่ในรูปแบบ yyyy-MM-dd
                String subReturnDate = returnDateStr.substring(0, 10);
                System.out.println(returnDateStr);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                //แปลงจาก String เป็น date
                Date returnDate = sdf.parse(returnDateStr);
                //สร้าง Date เป็นวันที่และเวลาในปัจจุบัน
                Date today = new Date();
                System.out.println("today"+today);
                //แปลง Date เป็น String 
                String td = new Timestamp(today.getTime())+"";
                //ตัด String ส่วนที่เป็นวันที่ ที่อยู่ในรูปแบบ yyyy-MM-dd
                String subtd = td.substring(0, 10);   
                Calendar cal = Calendar.getInstance();//สร้าง Object Calendar
                cal.add(Calendar.DATE, 1);//1 หมายถึง วันถัดไปจากวันนี้
                Date nextOneDay = cal.getTime();//แปลงจาก Calendar เป็น Date
                String nextOne = nextOneDay.getTime()+"";//แปลงจาก Date เป็น String
                //ตัด String ส่วนที่เป็นวันที่ ที่อยู่ในรูปแบบ yyyy-MM-dd
                String subNextOne= nextOne.substring(0, 10);
                System.out.println(nextOneDay);
                if(subNextOne.equals(subReturnDate)){ //เช็คว่าพรุ่งนี้ต้องคืนของใช่หรือไม่
                    notice += "-Next day, you must return "+rs.getString("item_name")+"\n";
                    ++count;//ถ้าเงื่อนไขนี้ถูกทำ count จะถูกบวกเพิ่มหนึ่ง
                }
                else if(subtd.equals(subReturnDate)){//เช็คว่าวันนี้เป็นวันที่ต้องยืนของใช่หรือไม่
                    notice += "-Today, you must return "+rs.getString("item_name")+"\n";
                    ++count;//ถ้าเงื่อนไขนี้ถูกทำ count จะถูกบวกเพิ่มหนึ่ง
                }
                else if (today.after(returnDate)) {//เช็คว่าเลยเวลาคืนของรึยัง
                    notice += "-You must return "+rs.getString("item_name")+".You are late!!!!"+"\n";
                    ++count;//ถ้าเงื่อนไขนี้ถูกทำ count จะถูกบวกเพิ่มหนึ่ง
                }
            }            
            System.out.println(count);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
//    public void populateData(ArrayList<Item> item) {
//        DefaultTableModel model = new DefaultTableModel();
//        model.setColumnIdentifiers(new Object[]{"item_id", "item_name", "current_amount", 
//            "item_owner_id"});
//        Object[] row = new Object[4];
//        if (item != null) {
//            for (int i = 0; i < item.size(); i++) {
//                row[0] = item.get(i).getId();
//                row[1] = item.get(i).getName();
//                row[2] = item.get(i).getCurrentAmount();
//                row[3] = item.get(i).getOwnerId();
//                model.addRow(row);
//            }
//            itemTable.setModel(model);
//        }
//    }

    public void populateData() {
        Statement s = null;
        try {
            label_num.setText(count+"");
            itemTable.setModel(new DefaultTableModel());
            DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
            model.addColumn("item_id");
            model.addColumn("item_name");
            model.addColumn("item_current_amount");
            model.addColumn("item_owner_id");
            Connection con = Db1.getConnectionBuilder();
            String sql = "SELECT * from Item";
            s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            int row = 0;
            while (rs != null && rs.next()) {
                model.addRow(new Object[0]);
                model.setValueAt(rs.getString("item_id"), row, 0);
                model.setValueAt(rs.getString("item_name"), row, 1);
                model.setValueAt(rs.getString("item_current_amount"), row, 2);
                model.setValueAt(rs.getString("item_owner_id"), row, 3);
                row++;
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void search(String search) {
        Statement s = null;
        try {
            itemTable.setModel(new DefaultTableModel());
            DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
            model.addColumn("item_id");
            model.addColumn("item_name");
            model.addColumn("item_current_amount");
            model.addColumn("item_owner_id");
            Connection con = Db1.getConnectionBuilder();
            String sql = "SELECT item_id,item_name,item_current_amount,item_owner_id from Item ";
            sql += "where item_name like '%"+search+"%'";
            
            s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            int row = 0;
            while (rs != null && rs.next()) {
                model.addRow(new Object[0]);
                model.setValueAt(rs.getString("item_id"), row, 0);
                model.setValueAt(rs.getString("item_name"), row, 1);
                model.setValueAt(rs.getString("item_current_amount"), row, 2);
                model.setValueAt(rs.getString("item_owner_id"), row, 3);
                row++;
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    public void clickTable() {
        try {
            int index = itemTable.getSelectedRow();
            String click = (itemTable.getModel().getValueAt(index, 0)).toString();
            String sql = "SELECT Item.*,Status.status_name from Item INNER Join Status where item_id='" + click + "'";
            sql += " and Status.status_id='1'";
            Connection con = Db1.getConnectionBuilder();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = con.createStatement().executeQuery(sql);
            if (rs.next()) {
                ps.execute();
                String textId = rs.getString("item_id");
                field_id.setText(textId);
                t.setItemId(textId);
                String textName = rs.getString("item_name");
                field_name.setText(textName);
                String textOwner = rs.getString("item_owner_id");
                field_owner.setText(textOwner);
                t.setLenderId(textOwner);
                User u = new User();
                System.out.println(u.getUserId());
                t.setBorrowerId(u.getUserId());
                field_borrowId.setText(t.getBorrowerId());
                t.setStatusId((byte) 1);
                field_status.setText(rs.getString("status_name"));
                image.setIcon(ResizeImage(null,Item.ListItems().get(index).getPicture()));
                remainItem = rs.getInt("item_current_amount");//remainItem ดึงค่าจำนวนของที่มีให้ยืมมาจาก database
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    //                HashMap hmStatus = new HashMap();
//                hmStatus.put(t.getStatusId(), "ส่งคำขอ");
//                t.setStatus(hmStatus);
//                String result = (String) t.getStatus().get(t.getStatusId());
//                field_status.setText(result);
       
    public void subAmount(){//method นี้ใช้ในนการลดจำนวนสิ่งของเมื่อถูกจอง
        try {
            long borrowAmount = t.getAmount();
            remainItem = remainItem-borrowAmount;
            System.out.println("remain"+remainItem);
            int index = itemTable.getSelectedRow();
            String click = (itemTable.getModel().getValueAt(index, 0)).toString();
            String sql = "update Item set item_current_amount=? where item_id='"+click+"'";
            Connection con = Db1.getConnectionBuilder();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setLong(1, remainItem);
            statement.executeUpdate();//ประมวลผลข้อมูล
            statement.close();//หยุดการประมวลผล
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GuiBorrower.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GuiBorrower.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //                image.setIcon(ResizeImage(null,Item.ListItems().get(totalRow).getPicture()));
    
    /*byte statusId = t.getStatusId();
            if(statusId==1)
                hmStatus.put((byte)1, "ส่งคำขอ");
            else if(statusId==2)
                hmStatus.put((byte)2, "ตอบรับแล้ว");
            else if(statusId==3)
                hmStatus.put((byte)3, "กำลังยืม");
            else if(statusId==4)
                hmStatus.put((byte)4, "คืนแล้ว");
            else if(statusId==-1)
                hmStatus.put((byte)-1, "โดนยกเลิกคำขอ");*/
    
    public ImageIcon ResizeImage(String imagePath, byte[] pic)
    {
        ImageIcon myImage = null;
        
        if(imagePath != null)
        {
            myImage = new ImageIcon(imagePath);
        }else{
            myImage = new ImageIcon(pic);
        }
        
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
        
    }

    public void setData() {
        try {
            //getค่าวันที่ที่ได้ จากการเลือกใน Combo Box แล้วแปลงค่าที่ได้เป็น String โดยการบวก"" แล้วนำค่า Stringที่ไดเก็บลงตัวแปร dayBor
            String dayBor = comboBox_day.getSelectedItem()+"/";
            //getค่าเดือนที่ได้ จากการเลือกใน Combo Box แล้วแปลงค่าที่ได้เป็น String โดยการบวก"" แล้วนำค่า Stringที่ไดเก็บลงตัวแปร monthBor
            String monthBor = comboBox_month.getSelectedItem()+"";
            //แปลงค่าชื่อเดือนที่ได้เป็นแบบตัวเลข โดยใช้ switch case
            System.out.println(monthBor);
            String numMth="";
            switch (monthBor) {
                case "January":
                    numMth = "01/";
                    break;
                case "February":
                    numMth = "02/";
                    break;
                case "March":
                    numMth = "03/";
                    break;
                case "April":
                    numMth = "04/";
                    break;
                case "May":
                    numMth = "05/";
                    break;
                case "June":
                    numMth = "06/";
                    break;
                case "July":
                    numMth = "07/";
                    break;
                case "August":
                    numMth = "08/";
                    break;
                case "September":
                    numMth = "09/";
                    break;
                case "October":
                    numMth = "10/";
                    break;
                case "November":
                    numMth = "11/";
                    break;
                case "December":
                    numMth = "12/";
                    break;
                default:
                    break;
            }
            System.out.println(numMth);
            //getค่าวันที่ที่ได้ จากการเลือกใน Combo Box แล้วแปลงค่าที่ได้เป็น String โดยการบวก"" แล้วนำค่า Stringที่ไดเก็บลงตัวแปร yearBor
            String yearBor = comboBox_year.getSelectedItem()+"";
            //นำค่า String วัน เดือน ปี มาเชื่อมกัน โดยทำให้เป็นรูปแบบ dd/MM/yyyy
            String strDateBor = dayBor+numMth+yearBor;
            //จัดรูปแบบวันที่ ให้เป็น dd/MM/yyyy
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            //แปลงจาก String เป็น Date
            Date borrowDate = df.parse(strDateBor);
            System.out.println(borrowDate);
            //แปลงจาก date เป็น timestamp
            Timestamp borrowDateTS = new Timestamp(borrowDate.getTime());
            System.out.println(borrowDateTS);
            t.setBorrowDate(borrowDateTS);//setค่าวันที่
            System.out.println("timestamp bor"+t.getBorrowDate());
            
            String dayRe = comboBox_day1.getSelectedItem()+"/";
            String monthRe = comboBox_month1.getSelectedItem()+"";
            switch (monthRe) {
                case "January":
                    numMth = "01/";
                    break;
                case "February":
                    numMth = "02/";
                    break;
                case "March":
                    numMth = "03/";
                    break;
                case "April":
                    numMth = "04/";
                    break;
                case "May":
                    numMth = "05/";
                    break;
                case "June":
                    numMth = "06/";
                    break;
                case "July":
                    numMth = "07/";
                    break;
                case "August":
                    numMth = "08/";
                    break;
                case "September":
                    numMth = "09/";
                    break;
                case "October":
                    numMth = "10/";
                    break;
                case "November":
                    numMth = "11/";
                    break;
                case "December":
                    numMth = "12/";
                    break;
                default:
                    break;
            }
            String yearRe = comboBox_year1.getSelectedItem()+"";
            String strDateRe = dayRe+numMth+yearRe;
            Date returnDate = df.parse(strDateRe);
            Timestamp returnDateTS = new Timestamp(returnDate.getTime());
            t.setReturnDate(returnDateTS);
            System.out.println("timestamp re"+t.getReturnDate());
            
            //สร้าง Object Date
            Date lastUpdateDate = new Date();
            //แปลงจาก Date เป็น timestamp
            Timestamp lastUpdateDateTS = new Timestamp(lastUpdateDate.getTime());
            //จัดรูปแบบวันที่ ให้เป็น dd/MM/yyyy
            SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
            //แปลงจาก timestamp เป็น String
            String strLastUpdate = sfd.format(lastUpdateDateTS);
            field_update.setText(strLastUpdate);//เซ็ตค่าวันที่ที่update ให้แสดงในช่อง textfield
            t.setLastUpdate(lastUpdateDateTS);
            String amount = field_amount.getText();
            int amountItem = Integer.parseInt(amount);
            t.setAmount(amountItem);
            t.setNote(field_note.getText());

            Item item = new Item();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
//    public boolean sendRequest() {
//        Boolean result = false;
//        Connection con = null;
//        try {
//            con = ConnectionBuilder.ConnectionBuilder();
//            String sql2 = "INSERT into Transaction (transaction_amount,transaction_note,transaction_status,transaction_borrow_date,transaction_return_date,transaction_last_update,user_id_borrow,user_id_lender,item_id) values (?,?,?,?,?,?,?,?,?)";
//            PreparedStatement statement2 = con.prepareStatement(sql2);
//
//            statement2.setLong(1, t.getAmount());
//            statement2.setString(2, t.getNote());
//            statement2.setInt(3, 0);
//            statement2.setTimestamp(4, t.getBorrowDate());
//            statement2.setTimestamp(5, t.getReturnDate());
//            statement2.setTimestamp(6, t.getLastUpdate());
//            statement2.setString(7, t.getBorrowerId());
//            statement2.setString(8, t.getLenderId());
//            statement2.setString(9, t.getItem());
//            statement2.execute(); //ประมวลผลข้อมูล
//            statement2.close();//หยุดการประมวลผล
//            result = true;
//            con.close();
//            result = true;
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            System.out.println(ex.getMessage());//ใช้แสดงความผิดปกติเป็นString
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return result;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();
        image = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        field_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        field_name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        field_owner = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        field_borrowId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        field_note = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        field_amount = new javax.swing.JTextField();
        field_status = new javax.swing.JTextField();
        field_update = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        comboBox_day = new javax.swing.JComboBox<>();
        comboBox_month = new javax.swing.JComboBox<>();
        comboBox_year = new javax.swing.JComboBox<>();
        comboBox_day1 = new javax.swing.JComboBox<>();
        comboBox_month1 = new javax.swing.JComboBox<>();
        comboBox_year1 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        field_search = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        label_icon = new javax.swing.JLabel();
        label_num = new javax.swing.JLabel();

        jTextField2.setBackground(new java.awt.Color(0, 102, 153));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("TH Sarabun New", 0, 18)); // NOI18N
        jButton1.setText("submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 640, -1, -1));

        itemTable.setFont(new java.awt.Font("TH Sarabun New", 0, 20)); // NOI18N
        itemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "1", "2", "3", "4"
            }
        ));
        itemTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(itemTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 490, 500));

        image.setBackground(new java.awt.Color(240, 255, 250));
        image.setOpaque(true);
        getContentPane().add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 540, 160, 100));

        jLabel6.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel6.setText("Picture");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, -1, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel1.setText("Item id");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 60, 20));

        field_id.setEditable(false);
        field_id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        field_id.setToolTipText("");
        getContentPane().add(field_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 154, -1));

        jLabel2.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel2.setText("Item name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, 20));

        field_name.setEditable(false);
        field_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(field_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 154, -1));

        jLabel3.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel3.setText("Lender id");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 80, 20));

        field_owner.setEditable(false);
        field_owner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        field_owner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_ownerActionPerformed(evt);
            }
        });
        getContentPane().add(field_owner, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 154, -1));

        jLabel4.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel4.setText("Note");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, -1));

        field_borrowId.setEditable(false);
        field_borrowId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        field_borrowId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_borrowIdActionPerformed(evt);
            }
        });
        getContentPane().add(field_borrowId, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 154, -1));

        jLabel5.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel5.setText("Status");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, -1, -1));

        field_note.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(field_note, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 154, -1));

        jLabel7.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel7.setText("Borrow date");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, -1, -1));

        jLabel8.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel8.setText("Return date");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, -1, -1));

        jLabel9.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel9.setText("Update date");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, -1, -1));

        jLabel10.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel10.setText("Amount ");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));

        jLabel11.setFont(new java.awt.Font("TH Sarabun New", 0, 24)); // NOI18N
        jLabel11.setText("Borrower id");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, -1));

        field_amount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        field_amount.setText("Enter number");
        field_amount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                field_amountMouseClicked(evt);
            }
        });
        getContentPane().add(field_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 154, -1));

        field_status.setEditable(false);
        field_status.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        field_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                field_statusActionPerformed(evt);
            }
        });
        getContentPane().add(field_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 500, 154, -1));

        field_update.setEditable(false);
        field_update.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(field_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 460, 154, -1));

        jLabel12.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 50)); // NOI18N
        jLabel12.setForeground(java.awt.Color.darkGray);
        jLabel12.setText("KORROW");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel13.setFont(new java.awt.Font("TH Sarabun New", 1, 24)); // NOI18N
        jLabel13.setText("Welcome user");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jButton2.setFont(new java.awt.Font("TH Sarabun New", 0, 18)); // NOI18N
        jButton2.setForeground(java.awt.Color.darkGray);
        jButton2.setText("My pofile");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, -1, -1));

        comboBox_day.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBox_day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        comboBox_day.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox_dayActionPerformed(evt);
            }
        });
        getContentPane().add(comboBox_day, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, -1, -1));

        comboBox_month.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBox_month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        comboBox_month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox_monthActionPerformed(evt);
            }
        });
        getContentPane().add(comboBox_month, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, -1, -1));

        comboBox_year.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBox_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2560", " " }));
        comboBox_year.setToolTipText("");
        getContentPane().add(comboBox_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, -1, -1));

        comboBox_day1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBox_day1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        getContentPane().add(comboBox_day1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, -1, -1));

        comboBox_month1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBox_month1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        getContentPane().add(comboBox_month1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, -1, -1));

        comboBox_year1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboBox_year1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2560" }));
        getContentPane().add(comboBox_year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 410, -1, -1));

        jButton3.setFont(new java.awt.Font("TH Sarabun New", 0, 18)); // NOI18N
        jButton3.setText("Log out");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, -1, -1));

        field_search.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(field_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 73, 210, 30));

        jButton4.setFont(new java.awt.Font("TH Sarabun New", 0, 18)); // NOI18N
        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 70, -1, -1));

        jButton5.setFont(new java.awt.Font("TH Sarabun New", 0, 18)); // NOI18N
        jButton5.setText("Reset");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 70, -1, -1));

        jPanel1.setBackground(new java.awt.Color(102, 214, 174));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 440, 680));

        jButton6.setFont(new java.awt.Font("TH Sarabun New", 0, 18)); // NOI18N
        jButton6.setText("History");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, -1, -1));

        jPanel2.setBackground(new java.awt.Color(0, 126, 135));
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 10));

        jPanel3.setBackground(new java.awt.Color(0, 126, 135));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 10, 690));

        jPanel5.setBackground(new java.awt.Color(0, 126, 135));
        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, 980, 10));

        jPanel6.setBackground(new java.awt.Color(0, 126, 135));
        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 0, 10, 690));
        jPanel6.getAccessibleContext().setAccessibleName("");

        label_icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_iconMouseClicked(evt);
            }
        });
        getContentPane().add(label_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 40, 40));

        label_num.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(label_num, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, 20, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            setData();
            Borrower b = new Borrower();
            
            Timestamp tmBor = t.getBorrowDate();
            Date dateBor = new Date(tmBor.getTime());
            Timestamp tmRe = t.getReturnDate();
            Date dateRe = new Date(tmRe.getTime());
            Timestamp tmUp = t.getLastUpdate();
            Date dateUp = new Date(tmUp.getTime());
            
//            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//            String dateBorrow = field_dateBorrow.getText();
////            Date dBorrow = df.parse(dateBorrow);
//            String dateReturn = field_dateReturn.getText();
//            Date dReturn = df.parse(dateReturn);
//            String update = field_update.getText();
//            Date dUpdate = df.parse(update);
            if(dateUp.before(dateBor)&&dateBor.before(dateRe)){
                int amount = Integer.parseInt(field_amount.getText());
                if (remainItem >= amount) {
                    b.sendRequest(t);
                    subAmount();
                } else {
                    JOptionPane.showMessageDialog(null, "Item does not have enough");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Date invalid");
            }
                } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed
//new Transactio();
//t.set(text.get)

//            int amount = Integer.parseInt(field_amount.getText());
//            if (remainItem >= amount) {
//                b.sendRequest(t);
//            } else {
//                JOptionPane.showMessageDialog(null, "Cannot sendRequest");
//            }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        populateData();
        noticeUser();
        System.out.println("notice"+count);
        label_num.setText(count+"");
        count=0;
        icon();
    }//GEN-LAST:event_formWindowOpened

    private void field_ownerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_ownerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_ownerActionPerformed

    private void itemTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemTableMouseClicked
        clickTable();
    }//GEN-LAST:event_itemTableMouseClicked

    private void field_borrowIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_borrowIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_borrowIdActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        GuiProfile guiPro = new GuiProfile(this, true);
        guiPro.showProfile();
        guiPro.setVisible(true);


    }//GEN-LAST:event_jButton2ActionPerformed

    private void field_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_field_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_field_statusActionPerformed

    private void comboBox_monthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox_monthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox_monthActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        super.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        search(field_search.getText());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        populateData();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void comboBox_dayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox_dayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox_dayActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        DialogHis history = new DialogHis(this, true);
        history.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void field_amountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field_amountMouseClicked
        // TODO add your handling code here:
        field_amount.setText("");
    }//GEN-LAST:event_field_amountMouseClicked

    private void label_iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_iconMouseClicked
        DialogNotice noticeDia = new DialogNotice(this, true, notice);
        noticeDia.setVisible(true);
        label_num.setText("");
    }//GEN-LAST:event_label_iconMouseClicked

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
            java.util.logging.Logger.getLogger(GuiBorrower.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiBorrower.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiBorrower.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiBorrower.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiBorrower().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBox_day;
    private javax.swing.JComboBox<String> comboBox_day1;
    private javax.swing.JComboBox<String> comboBox_month;
    private javax.swing.JComboBox<String> comboBox_month1;
    private javax.swing.JComboBox<String> comboBox_year;
    private javax.swing.JComboBox<String> comboBox_year1;
    private javax.swing.JTextField field_amount;
    private javax.swing.JTextField field_borrowId;
    private javax.swing.JTextField field_id;
    private javax.swing.JTextField field_name;
    private javax.swing.JTextField field_note;
    private javax.swing.JTextField field_owner;
    private javax.swing.JTextField field_search;
    private javax.swing.JTextField field_status;
    private javax.swing.JTextField field_update;
    private javax.swing.JLabel image;
    private javax.swing.JTable itemTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel label_icon;
    private javax.swing.JLabel label_num;
    // End of variables declaration//GEN-END:variables
}
