package com.NoteBook.Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.NoteBook.bean.NoteBook;
import com.model.User;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class NoteBookShowDao {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/virtusa?useSSL=false";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "root";

    private static final String INSERT_USERS_SQL = "INSERT INTO notebook" + "  (notebookid, userid, name) VALUES " +
        " (null, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select notebookid,userid,name from notebook where notebookid =?";
    private static final String SELECT_ALL_USERS = "select * from notebook";
    private static final String DELETE_USERS_SQL = "delete from notebook where notebookid = ?;";
    private static final String UPDATE_USERS_SQL = "update notebook set name = ?";

    public NoteBookShowDao() {}

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
/**
    public void insertUser(NoteBook user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public NoteBook selectUser(int id) {
        NoteBook user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int notebookid = rs.getInt("notebookid");
                int userid = rs.getInt("userid");
                String name = rs.getString("name");
                user = new NoteBook(id, name, email, country);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
**/
    public List < NoteBook > selectAllUsers() {

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
                int id=rs.getInt("notebookid");
                String name = rs.getString("name");
                System.out.println(name+"/t");
                users.add(new NoteBook(id,name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
        	
        	statement.setInt(1, id);
            
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    
    
    
    public static int getnid(String id) {
    	int nid=0;
try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("select notebookid from notebook where name =?;")) {
	preparedStatement.setString(1, id);
	ResultSet rs = preparedStatement.executeQuery();
    
	while (rs.next()) {
        nid = rs.getInt("notebookid");
        
    }
    
    
} catch (SQLException e) {
    printSQLException(e);
}
return nid;
}

	public NoteBook selectUser(int id) {
        NoteBook user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                
                user = new NoteBook(name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }
    
    public boolean updateUser(NoteBook user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            
            statement.setString(1, user.getName());
            

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
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
