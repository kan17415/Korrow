package korrow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Borrower extends User {

    private int gdp;

    public Borrower() {
    }    

    public Borrower(String userId, String password, String userName, String position, 
            ArrayList<String> email, ArrayList<String> tel, String userTypeId, 
            Transaction transactions, int gdp) {
        super(userId, password, userName, position, email, tel, userTypeId, transactions);
        this.gdp = gdp;
    }

    public int getGdp() {
        return gdp;
    }
    public void setGdp(int gdp) {
        this.gdp = gdp;
    }
    public boolean sendRequest(Transaction t) throws ClassNotFoundException {
        Boolean result = false;
        Connection con = null;
        try {
            con = Db1.getConnectionBuilder();
            
//            String sql = "INSERT into Transaction (transaction_amount,transaction_note,transaction_status,transaction_borrow_date,transaction_return_date,transaction_last_update,user_id_borrow,user_id_lender,item_id) values (?,?,?,?,?,?,?,?,?)";
            String sql = "INSERT into Transaction ";
            sql += "(transaction_amount,transaction_note,transaction_status,transaction_borrow_date,";
            sql += "transaction_return_date,transaction_last_update,user_id_borrow,user_id_lender,item_id)";
            sql += " values (?,?,?,?,?,?,?,?,?)";

            User u = new User();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setLong(1, t.getAmount());
            statement.setString(2, t.getNote());
//            String send = (String) t.getStatus().get(t.getStatusId());
//            statement.setString(3, send);
//            System.out.println(send);
            statement.setInt(3, t.getStatusId());
            statement.setTimestamp(4, t.getBorrowDate());
            statement.setTimestamp(5, t.getReturnDate());
            statement.setTimestamp(6, t.getLastUpdate());
            statement.setString(7, u.getUserId());
            System.out.println(u.getUserId());
            statement.setString(8, t.getLenderId());
            statement.setString(9, t.getItemId());
            statement.execute(); //ประมวลผลข้อมูล
            statement.close();//หยุดการประมวลผล
            result = true;
            con.close();
            result = true;
            JOptionPane.showMessageDialog(null, "submiting the request finish!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public boolean reSendRequest(Transaction transaction) {
        Boolean result = false;
        return result;
    }

}
