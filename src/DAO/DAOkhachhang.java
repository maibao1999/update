/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ConnectDB.Database;
import Entity.Khachhang;
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
public class DAOkhachhang {
     ArrayList<Khachhang> dskh;
  
    
    public DAOkhachhang(){
		dskh= new ArrayList<Khachhang>();	
             
		
    }
    
     public  ArrayList<Khachhang> doctubang()  {
			
		try {
		Connection con = Database.getInstance().getConnection();
		 String sql = "select * from khachhang";
		 Statement statement = con.createStatement();
	      
	      ResultSet rs = statement.executeQuery(sql);
	     
	      while (rs.next()) {
	      
                 
	          String makh= rs.getString(1);
	          String tenkh= rs.getString(2);
                 
                  String sdt=rs.getString(3);
                
                  String diachi=rs.getString(4);
                  
	          Khachhang kh=new Khachhang(makh, tenkh, sdt, diachi);
	         
                dskh.add(kh);
	      }
		} catch (SQLException e) {
			e.printStackTrace();
	     
		}
	return dskh;
}
     
     
     
       public boolean themkhachhang(Khachhang kh){
        Connection con = Database.getInstance().getConnection();
		PreparedStatement statement = null;
		int n= 0;
		try {
			statement = con.prepareStatement("insert into khachhang values(?,?,?,?)");
			statement.setString(1, kh.getMakh());
			statement.setString(2, kh.getTenkh());
                       
                        statement.setString(3,kh.getSdt());
			
                        statement.setString(4,kh.getDiachi());
                       
			
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
      
      public boolean deletekhachhang(String ma) {
			Connection con = Database.getInstance().getConnection();
			PreparedStatement stmt = null;
			int n = 0;
			try {
				stmt = con.prepareStatement("delete from khachhang where makh = ?");
				stmt.setString(1, ma);
				n = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return n > 0; 
    }
      
      public boolean kttrung(String makh){
        boolean check=false;
        try {
            String sql="select tenkh from khachhang where makh=?";
            Connection cnn = Database.getInstance().getConnection();
            PreparedStatement stt = null;
            stt= cnn.prepareStatement(sql);
            stt.setString(1, makh);
            ResultSet re=stt.executeQuery();
            if(re.next()){
                check=true;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
       
        return check;
    }  
      
        public boolean updatekh(String makh,String tenkh,String sdt,String diachi) {
		Connection cnn = Database.getInstance().getConnection();
		int n = 0;
		PreparedStatement stt = null;
		try {
			stt = cnn.prepareStatement("UPDATE khachhang SET tenkh=?,sdt=?,diachi=? WHERE makh=?");
			stt.setString(1, tenkh);
			
			stt.setString(2, sdt);
                        stt.setString(3, diachi);
			stt.setString(4, makh);
			n = stt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;		
	}
}
