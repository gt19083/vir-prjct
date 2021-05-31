package com.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.model.Note;

public class NoteDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/Project?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";
    
    private static final String SELECT_SPEC_USERS= "select * from notes where notebookid=?";
    private static final String INSERT_USERS_SQL = "INSERT INTO notes" + "  (noteid,startdate,enddate,reminderdate,status,tag,Descp,notebookid) VALUES " +
        " (null,?,?,?,?,?,?,?);";

    private static final String SELECT_USER_BY_ID = "select noteid,startdate,enddate,reminderdate,status,tag,Descp,notebookid from notes where noteid =?";
    private static final String SELECT_ALL_USERS = "select * from notes";
    private static final String SELECT_ALL_USERS_USERID = "Select * from notes where notebookid in (select notebookid from notebook where userid =1)";
    private static final String DELETE_USERS_SQL = "delete from notes where noteid = ?;";
    private static final String UPDATE_USERS_SQL = "update notes set status = ?,tag= ?, Descp =? where noteid = ?;";

    public NoteDao() {}

    protected Connection getConnection() {
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

    public void insertUser(Note user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            
            String startdate = user.getStartdate();
            System.out.println("string "+startdate);
            String enddate = user.getEnddate();
            String reminderdate = user.getReminderdate();
            System.out.println( "change"+Date.valueOf(enddate));
           
            preparedStatement.setDate(1, Date.valueOf(startdate));
            preparedStatement.setDate(2, Date.valueOf(enddate));
            preparedStatement.setDate(3, Date.valueOf(reminderdate));
            preparedStatement.setString(4, user.getStatus());
            preparedStatement.setString(5, user.getTag());
            preparedStatement.setString(6, user.getDescription());
            preparedStatement.setInt(7, user.getNotebookid());
            System.out.println(preparedStatement);
            System.out.println(user.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public static java.sql.Date convertFromJAVADateToSQLDate(
            java.util.Date javaDate) {
        java.sql.Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = new Date(javaDate.getTime());
        }
        return sqlDate;
    }
    public Note selectUser(int id) {
        Note user = null;
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
            	int noteid=rs.getInt("noteid");
                String startdate = rs.getString("startdate");
                String enddate = rs.getString("enddate");
                String reminderdate = rs.getString("reminderdate");
                String status = rs.getString("status");
                String tag = rs.getString("tag");
                String Descp = rs.getString("Descp");
                int notebookid=rs.getInt("notebookid");
                user = new Note(noteid,startdate,enddate,reminderdate,status,tag,Descp,notebookid);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public List < Note > selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Note > users = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int id=rs.getInt("noteid");
                String startdate = rs.getString("startdate");
                String enddate = rs.getString("enddate");
                String reminderdate = rs.getString("reminderdate");
                String status = rs.getString("status");
                String tag = rs.getString("tag");
                String Descp = rs.getString("Descp");
                int notebookid=rs.getInt("notebookid");
                String notebookname=getnname(notebookid);
                System.out.println(id+" "+startdate+" "+enddate+" "+reminderdate+" "+status+" "+tag+" "+Descp+" "+notebookid);
                users.add(new Note(id,startdate,enddate,reminderdate,status,tag,Descp,notebookid,notebookname));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }
    
    public List < Note > selectAllUsersbyid(int usid) {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Note > users = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_USERID);) {
        	preparedStatement.setInt(1, usid);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int id=rs.getInt("noteid");
                String startdate = rs.getString("startdate");
                String enddate = rs.getString("enddate");
                String reminderdate = rs.getString("reminderdate");
                String status = rs.getString("status");
                String tag = rs.getString("tag");
                String Descp = rs.getString("Descp");
                int notebookid=rs.getInt("notebookid");
                String notebookname=getnname(notebookid);
                System.out.println(id+" "+startdate+" "+enddate+" "+reminderdate+" "+status+" "+tag+" "+Descp+" "+notebookid);
                users.add(new Note(id,startdate,enddate,reminderdate,status,tag,Descp,notebookid,notebookname));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }


    private String getnname(int notebookid) {
    	
    		String nid = null;
    		try (Connection connection = getConnection();
    	            // Step 2:Create a statement using connection object
    	            PreparedStatement preparedStatement = connection.prepareStatement
    	            		("select name from notebook where notebookid=?;");) {
    	            preparedStatement.setInt(1, notebookid);
    	            System.out.println(preparedStatement);
    	            ResultSet rs = preparedStatement.executeQuery();
    	    		while (rs.next()) {
    	    			nid = rs.getString("name");
    	    		}
    	        } catch (SQLException e) {
    	            printSQLException(e);
    	        }
    		return nid;
    	
		
	}

	public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(Note user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, user.getStatus());
            statement.setString(2, user.getTag());
            statement.setString(3, user.getDescription());
            statement.setInt(4, user.getUserid());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
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

	public int getnnid(int id) {
		int nid = 0;
		try (Connection connection = getConnection();
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement
	            		("select notebookid from notebook where userid=?;");) {
	            preparedStatement.setInt(1, id);
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	    		while (rs.next()) {
	    			nid = rs.getInt("notebookid");
	    		}
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
		return nid;
	}
	public int getuid(String username) {
		int id = 0;
		try (Connection connection = getConnection();
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement
	            		("select userid from user where username=?;");) {
	            preparedStatement.setString(1, username);
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	    		while (rs.next()) {
	    			id = rs.getInt("userid");
	    		}
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
		return id;
	}

	public List<Note> selectSpecificNotes(int nid) {
		

        
        List < Note > users = new ArrayList < > ();
        
        try (Connection connection = getConnection();

            
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SPEC_USERS);) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, nid);
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int id=rs.getInt("noteid");
                String startdate = rs.getString("startdate");
                String enddate = rs.getString("enddate");
                String reminderdate = rs.getString("reminderdate");
                String status = rs.getString("status");
                String tag = rs.getString("tag");
                String Descp = rs.getString("Descp");
                //int notebookid=rs.getInt("notebookid");
                String notebookname=getnname(nid);
                System.out.println(id+" "+startdate+" "+enddate+" "+reminderdate+" "+status+" "+tag+" "+Descp+" "+nid);
                users.add(new Note(id,startdate,enddate,reminderdate,status,tag,Descp,nid,notebookname));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
	}
	
}