package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bean.RegisterBean;
import com.util.DBConnection;

public class profileDao {
	 private final static String UPDATE_PROFILE_SQL = "update user set name = ?,mobile=?,password=? where userid = ?;";
	public static boolean updateprofile(RegisterBean book) {
		// TODO Auto-generated method stub
boolean rowUpdated = false;
            
           
        
        Connection con = null;
                
        try
        {
            con = DBConnection.createConnection();
           
            PreparedStatement statement = con.prepareStatement(UPDATE_PROFILE_SQL);
            RegisterBean ob=new RegisterBean();
            statement.setString(1, ob.getUserName());
            
            statement.setInt(2, ob.getMobileNo());
            statement.setString(3, ob.getPassword());
            statement.setInt(2, ob.getId());
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        catch(SQLException e)
        {
           e.printStackTrace();
        }     
        return rowUpdated;
	}

}
