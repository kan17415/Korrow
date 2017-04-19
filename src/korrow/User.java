package korrow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

//    private HashMap<char,String> userType;
public class User {

    private static String userId;
    private String password;
    private String userName;
    private String position;
    private List<String> email;
    private List<String> tel;
    private String userTypeId;
    private Transaction transactions;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPosition() {
        return position;
    }

    public List<String> getEmail() {
        return email;
    }

    public List<String> getTel() {
        return tel;
    }

    public String getPassword() {
        return password;
    }

    public String getUserTypeId() {
        return userTypeId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public void setTel(List<String> tel) {
        this.tel = tel;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Transaction getTransections() {
        return transactions;
    }

    public void setTransactions(Transaction transactions) {
        this.transactions = transactions;
    }

    public void getData(ResultSet rs, User u) throws SQLException {
        u.setUserId(rs.getString("user_id"));
        u.setUserName(rs.getString("user_name"));
//        u.setUserName(rs.getString("user_password"));
        u.setPosition(rs.getString("user_id"));
        String arrEmail[] = rs.getString("user_email").split(",");
        List<String> userMail = new ArrayList<>();
        for (String mail : arrEmail) {
            userMail.add(mail);
        }
        u.setEmail(userMail);

        String arrTel[] = rs.getString("user_tel").split(",");
        List<String> userTel = new ArrayList<>();
        for (String thistel : arrTel) {
            userTel.add(thistel);
        }
        u.setTel(userTel);
        u.setUserTypeId(rs.getString("user_type"));
        

//        u.setEmail((ArrayList<String>) rs.getArray("email"));
//        u.setTel((ArrayList<String>) rs.getArray("tel"));
    }
    
     public ArrayList<User> searchUser(String search){
  
        
        ArrayList<User> userList = null;
        try{
            String sql = "SELECT * FROM User where user_name like('%"+search+"%')";
            //String searchQuery = "SELECT * FROM Item WHERE CONCAT('item_id','item_name','item_owner_id','item_unit','item_capacity_amount','item_current_amount','item_image_path','item_note','item_rent_detail','item_available','item_type','category_item_id_category_item')LIKE '%"+ValToSearch+"%'";
            Connection con = Db1.getConnectionBuilder();
            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1,"%"+search+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User();
                if(userList == null){
                    userList = new ArrayList<>();
                }
                getData(rs,user);
                userList.add(user);
               
              
//                itemList.add(item);
            }
            con.close();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return userList;
    }

    public User() {

    }

    public User(String userId, String password, String userName, String position,
            ArrayList<String> email, ArrayList<String> tel, String userTypeId, Transaction transactions) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.position = position;
        this.email = email;
        this.tel = tel;
        this.userTypeId = userTypeId;
        this.transactions = transactions;
    }

    public void insertUser(Department dep) {
        System.out.println("insert " + userId + " finish!");
        Connection con = null;
        try {
            String strMail = "";
            for (int i = 0; i < email.size(); i++) {
                strMail += email.get(i);
                if (i == email.size() - 1) {
                    break;
                }
                strMail += ",";

            }
            String strTel = "";
            for (int i = 0; i < tel.size(); i++) {
                strTel += tel.get(i);
                if (i == tel.size() - 1) {
                    break;
                }
                strTel += ",";
            }
            String sql = "INSERT into User (user_id,user_password,user_name,user_position,user_email,user_tel,user_type,department_id,gdp)";
            sql += " values (?,?,?,?,?,?,?,?,?)";
            con = Db1.getConnectionBuilder();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, userId);
            statement.setString(2, password);
            statement.setString(3, userName);
            statement.setString(4, position);
            statement.setString(5, strMail);
            statement.setString(6, strTel);
            statement.setString(7, userTypeId);
//            Department dep = new Department();
            statement.setInt(8, dep.getDepId());
            statement.setInt(9, 0);
            statement.executeUpdate();//ประมวลผลข้อมูล
            statement.close();//หยุดการประมวลผล
 
//            FormWindowOpened(null);
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
//            System.out.println(ex.getMessage());//ใช้แสดงความผิดปกติเป็นString
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateUser() throws SQLException {
        Department d= new Department();
        System.out.println("userId " + userId + "update finish!");
        Connection con = null;
        try {
            String strMail = "";
            for (int i = 0; i < email.size(); i++) {
                strMail += email.get(i);
                if(i == email.size()-1){
                    break;
                }
                strMail += ",";
            }
            String strTel = "";
            for (int i = 0; i < tel.size(); i++) {
                strTel += tel.get(i);
                if(i == tel.size()-1){
                    break;
                }
                strTel += ",";
            }
            String sql = "update User set";
            sql += " user_password=?,";
            sql += " user_name=?,";
            sql += " user_position=?,";
            sql += " user_email=?,";
            sql += " user_tel=?,";
            sql += " user_type=?,";
            sql += " department_id=?";
            sql += " WHERE user_id=?";
            con = Db1.getConnectionBuilder();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, password);
            statement.setString(2, userName);
            statement.setString(3, position);
            statement.setString(4, strMail);
            statement.setString(5, strTel);
            statement.setString(6, userTypeId);
            statement.setInt(7, d.getDepId());
            statement.setString(8, userId);
            statement.executeUpdate();//ประมวลผลข้อมูล
            statement.close();//หยุดการประมวลผล

        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());//ใช้แสดงความผิดปกติเป็นString
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            con.close();
        }
    }
//    for(int i =0; i< items.size();i++){
//            row[0] = items.get(i).getId();
//            row[1] = items.get(i).getName();
//            row[2] = items.get(i).getOwnerId();
//            row[3] = items.get(i).getUnit();
//            row[4] = items.get(i).getCapaAmount();
//            row[5] = items.get(i).getCurrentAmount();
//            row[6] = items.get(i).getPic();
//            row[7] = items.get(i).getNote();
//            row[8] = items.get(i).getRentDe();
//            row[9] = items.get(i).getAvali();
//            row[10] = items.get(i).getType();
//            row[11] = items.get(i).getCata();
//            
//            
//            model.addRow(row);
//            
//        }

    public ArrayList<User> serachByName(String name) {
        ArrayList<User> userList = null;
        try {

            String sql = "select * from User where user_name like ?";
            Connection con = Db1.getConnectionBuilder();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User u = new User();
                if (userList == null) {
                    userList = new ArrayList<>();

                }

                getData(rs, u);
                userList.add(u);

            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;

    }

    public void Login() {
        try {
            String sql = "select * From User where user_id=? and user_password =?";
            Connection con = Db1.getConnectionBuilder();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
             if (rs.next()) {
                String username = rs.getString("user_name");
                korrow.LoginDiaSuc loginDia = new korrow.LoginDiaSuc(new JFrame(), true, "ยินต้อนรับคุณ: " + "\t" + rs.getString("user_name"));

                System.out.println(rs.getString("user_name"));
                //LoginDiaSuc.setLoginDiaSuc("ยินดีต้อนรับคุณ");

                loginDia.setVisible(true);

                //JOptionPane.showMessageDialog(null,"ยินดีต้อนรับคุณ  "+rs.getString("user_name"));
                if (rs.getString("user_type").equals("A")) {
                    Table t1 = new Table();
                    t1.setVisible(true);

                } else if (rs.getString("user_type").equals("B")) {
                    GuiBorrower gu = new GuiBorrower();
                    gu.setVisible(true);

                } else if (rs.getString("user_type").equals("L")) {
                    UserItem guiL = new UserItem();
                    guiL.setVisible(true);
                }
               
            } else {
                korrow.LoginDiaSuc loginDia = new korrow.LoginDiaSuc(new JFrame(), true, "รหัสผิดไอสัส กรอกใหม่");
                loginDia.setVisible(true);

                //System.out.println("Incorrect ID or Password");
                //JOptionPane.showMessageDialog(null, "Incorrect ID or Password" );
            }
                con.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
        

//    public static ArrayList<Item>findItems(String search){
//        ArrayList<Item> itemList = null;
//        
//        
//        try{
//            String searchQuery = "SELECT * FROM Item where item_name like ?";
//            //String searchQuery = "SELECT * FROM Item WHERE CONCAT('item_id','item_name','item_owner_id','item_unit','item_capacity_amount','item_current_amount','item_image_path','item_note','item_rent_detail','item_available','item_type','category_item_id_category_item')LIKE '%"+ValToSearch+"%'";
//            Connection con = Db1.getConnectionBuilder();
//            PreparedStatement ps = con.prepareStatement(searchQuery);
//            ps.setString(1,"%"+search+"%");
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                Item item = new Item();
//                if(itemList == null){
//                    itemList = new ArrayList<>();
//                }
//                getDataFromDB(rs,item);
//                itemList.add(item);
//            }
//            con.close();
//            
//        }catch(Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        return itemList;
//    }
//    }

//    public static void deleteUserById(String userId) {
//        Connection con = null;
//        try {
//            String sql = "delete from User where user_id=?";
//            con = ConnectionBuilder.ConnectionBuilder();
//            PreparedStatement statement = con.prepareStatement(sql);//เป็นการเตรียมคำสั่งsqlสำหรับเรียกดูข้อมูลในตาราง
//            statement.setString(1, userId);
//            statement.execute();//ประมวลผลข้อมูล
//            statement.close();//หยุดการประมวลผล
//            con.close();
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

