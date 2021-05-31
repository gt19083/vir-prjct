package com.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.bean.RegisterBean;
import com.util.DBConnection;
 
public class RegisterDao { 
     public String registerUser(RegisterBean registerBean)
     {
         String fullName = registerBean.getFullName();
         int mobileno = registerBean.getMobileNo();
         String username = registerBean.getUserName();
         String password = registerBean.getPassword();
         
         Connection con = null;
         PreparedStatement preparedStatement = null;         
         try
         {
             con = DBConnection.createConnection();
             String query = "insert into user(userid,username,mobileno,password) values (NULL,?,?,?)"; //Insert user details into the table 'USERS'
             preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
             preparedStatement.setString(1, username);
             preparedStatement.setInt(2, mobileno);
             preparedStatement.setString(3, password);
             
             
             int i= preparedStatement.executeUpdate();
             
             if (i!=0)  //Just to ensure data has been inserted into the database
             return "SUCCESS"; 
         }
         catch(SQLException e)
         {
            e.printStackTrace();
         }       
         return "Oops.. Something went wrong there..!";  // On failure, send a message from here.
     }
}
