/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ConnectDB.Database;
import Entity.Dia;
import Entity.Loai;
import Entity.Tieude;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Thanh Nhan
 */
public class DAOdia {
    ArrayList<Dia> dsd;
  
    
    public DAOdia(){
		dsd= new ArrayList<Dia>();	
             
		
    }
    
     public  ArrayList<Dia> doctubang()  {
			
		try {
		Connection con = Database.getInstance().getConnection();
		 String sql = "Select * from dia";
		 Statement statement = con.createStatement();
	      
	      ResultSet rs = statement.executeQuery(sql);
	     
	      while (rs.next()) {
	      
                 
	          String madia= rs.getString(1);
	          String matieude= rs.getString(2);
                  
                  String trangthai=rs.getString(3);
               
                  
	          Dia d=new Dia(madia, new Tieude(matieude), trangthai);
	         
                   dsd.add(d);
	      }
		} catch (SQLException e) {
			e.printStackTrace();
	     
		}
	return dsd;
    }
           public boolean kttrung(String madia){
        boolean check =false;
        try {
            String sql="select * from dia where madia=?";
            Connection cnn = Database.getInstance().getConnection();
            PreparedStatement stt = null;
            stt= cnn.prepareStatement(sql);
            stt.setString(1, madia);
            ResultSet re=stt.executeQuery();
            if(re.next()){
                check=true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
       
        return check;
    }
            public boolean themdia(Dia d){
        Connection con = Database.getInstance().getConnection();
		PreparedStatement statement = null;
		int n= 0;
		try {
			statement = con.prepareStatement("insert into dia values(?,?,?)");
			statement.setString(1, d.getMadia());
			statement.setString(2, d.getMatieude().getMatieude());
                   
                        statement.setString(3,d.getTrangthai());
	
                       
			
			n = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
                }
                finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n>0;
        
    }
             public boolean deletedia(String ma) {
			Connection con = Database.getInstance().getConnection();
			PreparedStatement stmt = null;
			int n = 0;
			try {
				stmt = con.prepareStatement("delete from dia where madia = ?");
				stmt.setString(1, ma);
				n = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return n > 0; 
    }
     
}
