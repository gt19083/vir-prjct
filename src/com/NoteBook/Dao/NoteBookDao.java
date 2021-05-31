package com.NoteBook.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.NoteBook.bean.NoteBook;
import com.model.User;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class NoteBookDao {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/virtusa?useSSL=false";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "root";
   
    private static final String INSERT_USERS_SQL = "INSERT INTO notebook" + "  (notebookid, userid, name) VALUES " +
        " (null, ?, ?);";
    private static final String SELECT_USER_BY_NAME = "select userid from user where username =?";

    private static final String SELECT_ALL_USERS = "select * from notebook";


    public NoteBookDao() {}

    protected static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public static void insertUser(String username,String notename) throws SQLException {
    	
    	 
    		
    	
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            
        	int userid=getuserid(username);
        	System.out.println("session user"+username);
        	System.out.println("Notebook name"+notename);
            preparedStatement.setInt(1, userid);
            preparedStatement.setString(2, notename);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static int getuserid(String username) {
    	int id=0;
try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_NAME)) {
	preparedStatement.setString(1, username);
	ResultSet rs = preparedStatement.executeQuery();
    
	while (rs.next()) {
        id = rs.getInt("userid");
        
    }
        
    
           System.out.println(preparedStatement); 
            
        } catch (SQLException e) {
            printSQLException(e);
        }
return id;
    }

    public static List < NoteBook > selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < NoteBook > users = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                users.add((NoteBook) new NoteBook(id, name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    

    

    private static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}