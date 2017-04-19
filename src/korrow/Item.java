/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korrow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Kantarat
 */
public class Item {
    private String id;
    private String name;
    private String ownerId;
    private String unit;
    private long capaAmount;
    private long currentAmount;
    
    private String note;
    private String rentDe;
    private byte avali;
    private String type;
    private String cata;
    private byte[] picture;

    public Item(String id, String name, String ownerId, String unit, long capaAmount, long currentAmount, String note, String rentDe, byte avali, String type, String cata, byte[] picture) {
        this.id = id;
        this.unit = unit;
        this.capaAmount = capaAmount;
        this.currentAmount = currentAmount;
        
        this.note = note;
        this.name = name;
        this.ownerId = ownerId;
        this.rentDe = rentDe;
        this.avali = avali;
        this.type = type;
        this.cata = cata;
        this.picture = picture;
    }

    public Item() {
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getUnit() {
        return unit;
    }

    public long getCapaAmount() {
        return capaAmount;
    }

    public long getCurrentAmount() {
        return currentAmount;
    }

    

    public String getNote() {
        return note;
    }

    public String getRentDe() {
        return rentDe;
    }

    public byte getAvali() {
        return avali;
    }

    public String getType() {
        return type;
    }

    public String getCata() {
        return cata;
    }

    public byte[] getPicture() {
        return picture;
    }
    
    public static void getDataFromDB(ResultSet rs, Item i) throws SQLException{
        i.setId(rs.getString("item_id"));
        i.setName(rs.getString("item_name"));
        i.setOwnerId(rs.getString("item_owner_id"));
        i.setUnit(rs.getString("item_unit"));
        i.setCapaAmount(rs.getLong("item_capacity_amount"));
        i.setCurrentAmount(rs.getLong("item_current_amount"));
        
        i.setNote(rs.getString("item_note"));
        i.setRentDe(rs.getString("item_rent_detail"));
        i.setAvali(rs.getByte("item_available"));
        i.setType(rs.getString("item_type"));
        i.setCata(rs.getString("category_name"));
        i.setPicture(rs.getBytes("Image"));
    }
    
     public static ArrayList<Item>ListItems(){
        ArrayList<Item> itemList = null;
        Statement st;
        
        try{
            String searchQuery = "SELECT Item.*, Category.category_name FROM Item join Category on Item.category_item_id_category_item = Category.category_id ";
            Connection con = Db1.getConnectionBuilder();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(searchQuery);
            while(rs.next()){
                Item item = new Item();
                if(itemList == null){
                    itemList = new ArrayList<>();
                }
                getDataFromDB(rs,item);
                itemList.add(item);
            }
            con.close();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return itemList;
    }
     
     
     public static ArrayList<Item> findItems(String search){
        ArrayList<Item> itemList = null;
        try{
            String searchQuery = "SELECT Item.*, Category.category_name FROM Item join Category on Item.category_item_id_category_item = Category.category_id where item_name like ?";
            Connection con = Db1.getConnectionBuilder();
            PreparedStatement ps = con.prepareStatement(searchQuery);
            ps.setString(1,"%"+search+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Item item = new Item();
                if(itemList == null){
                    itemList = new ArrayList<>();
                }
                getDataFromDB(rs,item);
                itemList.add(item);
            }
            con.close();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return itemList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setCapaAmount(long capaAmount) {
        this.capaAmount = capaAmount;
    }

    public void setCurrentAmount(long currentAmount) {
        this.currentAmount = currentAmount;
    }

   

    public void setNote(String note) {
        this.note = note;
    }

    public void setRentDe(String rentDe) {
        this.rentDe = rentDe;
    }

    public void setAvali(byte avali) {
        this.avali = avali;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCata(String cata) {
        this.cata = cata;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
    
}