package korrow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Login {
   public  static void Login (String id,String password,UserItem i){
        try{
            String sql ="select * From User where user_id=? and user_password =?";
            Connection con = Db1.getConnectionBuilder();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,password);
            
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                String username = rs.getString("user_name");
                LoginDiaSuc loginDia = new LoginDiaSuc(new JFrame(),true,"ยินต้อนรับคุณ : "+"\t"+rs.getString("user_name"));
                
                System.out.println(rs.getString("user_name"));
                //LoginDiaSuc.setLoginDiaSuc("ยินดีต้อนรับคุณ");
                
                
                loginDia.setVisible(true);
                
                //JOptionPane.showMessageDialog(null,"ยินดีต้อนรับคุณ  "+rs.getString("user_name"));
                
                
                if(rs.getString("user_type").equals("A")){
                    
                }else if(rs.getString("user_type").equals("B")){
                    
                }else if(rs.getString("user_type").equals("L")){
                    
                }
                
                
                
                i.setVisible(true);
            }else{
                LoginDiaSuc loginDia = new LoginDiaSuc(new JFrame(),true,"รหัสผิด กรอกใหม่อีกครั้ง");
                loginDia.setVisible(true);
                
                //System.out.println("Incorrect ID or Password");
                //JOptionPane.showMessageDialog(null, "Incorrect ID or Password" );
            }
            con.close();
        }catch(Exception ex){
            
            System.out.println(ex.getMessage());
        }
    }
}
