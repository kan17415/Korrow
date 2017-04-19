/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korrow;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import static korrow.ItemUser.getDataFromDB;

/**
 *
 * @author Kantarat
 */
public class ShowTran {
    private long tran_id;
    private long tran_amount;
    private String tran_note;
    private String tran_sta;
    private Timestamp tran_bow;
    private Timestamp tran_return;
    private Timestamp tran_update;
    private String user_bor;
    private String user_len;
    private String item_id;
    public static String tempUserName1;

    public static String getTempUserName1() {
        return tempUserName1;
    }

    public static void setTempUserName1(String tempUserName1) {
        ShowTran.tempUserName1 = tempUserName1;
    }

    public ShowTran(long tran_id, long tran_amount, String tran_note, String tran_sta, Timestamp tran_bow, Timestamp tran_return, Timestamp tran_update, String user_bor, String user_len, String item_id) {
        this.tran_id = tran_id;
        this.tran_amount = tran_amount;
        this.tran_note = tran_note;
        this.tran_sta = tran_sta;
        this.tran_bow = tran_bow;
        this.tran_return = tran_return;
        this.tran_update = tran_update;
        this.user_bor = user_bor;
        this.user_len = user_len;
        this.item_id = item_id;
    }

    private ShowTran() {
        
    }
    
   

    public long getTran_id() {
        return tran_id;
    }

    public void setTran_id(long tran_id) {
        this.tran_id = tran_id;
    }

    public long getTran_amount() {
        return tran_amount;
    }

    public void setTran_amount(long tran_amount) {
        this.tran_amount = tran_amount;
    }

    public String getTran_note() {
        return tran_note;
    }

    public void setTran_note(String tran_note) {
        this.tran_note = tran_note;
    }

    public String getTran_sta() {
        
        
        return tran_sta ;
    }

    public void setTran_sta(String tran_sta) {
        this.tran_sta = tran_sta;
    }

    public Timestamp getTran_bow() {
        return tran_bow;
    }

    public void setTran_bow(Timestamp tran_bow) {
        this.tran_bow = tran_bow;
    }

    public Timestamp getTran_return() {
        return tran_return;
    }

    public void setTran_return(Timestamp tran_return) {
        this.tran_return = tran_return;
    }

    public Timestamp getTran_update() {
        return tran_update;
    }

    public void setTran_update(Timestamp tran_update) {
        this.tran_update = tran_update;
    }

    public String getUser_bor() {
        return user_bor;
    }

    public void setUser_bor(String user_bor) {
        this.user_bor = user_bor;
    }

    public String getUser_len() {
        return user_len;
    }

    public void setUser_len(String user_len) {
        this.user_len = user_len;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }
    
    
    
    
     public static void getDataFromDB(ResultSet rs, ShowTran i) throws SQLException{

        i.setTran_id(rs.getLong("transaction_id"));
        i.setTran_amount(rs.getLong("transaction_amount"));
        i.setTran_note(rs.getString("transaction_note"));
        i.setTran_sta(rs.getString("status_name"));
        i.setTran_bow(rs.getTimestamp("transaction_borrow_date"));
        i.setTran_return(rs.getTimestamp("transaction_return_date"));
        i.setTran_update(rs.getTimestamp("transaction_last_update"));
        i.setUser_bor(rs.getString("user_id_borrow"));
        i.setUser_len(rs.getString("user_id_lender"));
        i.setItem_id(rs.getString("item_id"));
        
    }
    
    
    
     public static ArrayList<ShowTran>ListTrans(){
        ArrayList<ShowTran> tranList = null;
        Statement st;
        
        try{
            //String searchQuery = "SELECT * FROM Transaction where user_id_lender = '"+ShowTran.tempUserName1+"'";
            //"SELECT Item.*, Category.category_name FROM Item join Category on Item.category_item_id_category_item = Category.category_id where item_name like ?";
            String se = "SELECT Transaction.*, Status.status_name FROM Transaction join Status on Transaction.transaction_status = Status.status_id where user_id_lender = '"+ShowTran.tempUserName1+"'";
            
            Connection con = Db1.getConnectionBuilder();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(se);
            while(rs.next()){
                ShowTran tran = new ShowTran();
                if(tranList == null){
                    tranList = new ArrayList<>();
                }
                getDataFromDB(rs,tran);
                
                tranList.add(tran);
            }
            con.close();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return tranList;
    }
     
    public static ArrayList<ShowTran>findtran(){
        ArrayList<ShowTran> tranList = null;
        Statement st;
        
        try{
            //String searchQuery = "SELECT * FROM Transaction where user_id_lender = '"+ShowTran.tempUserName1+"'";
            //"SELECT Item.*, Category.category_name FROM Item join Category on Item.category_item_id_category_item = Category.category_id where item_name like ?";
            String se = "SELECT Transaction.*, Status.status_name FROM Transaction join Status on Transaction.transaction_status = Status.status_id where user_id_lender = '"+ShowTran.tempUserName1+"'";
            
            Connection con = Db1.getConnectionBuilder();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(se);
            while(rs.next()){
                ShowTran tran = new ShowTran();
                if(tranList == null){
                    tranList = new ArrayList<>();
                }
                getDataFromDB(rs,tran);
                
                tranList.add(tran);
            }
            con.close();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return tranList;
    }
     
     
    
}
