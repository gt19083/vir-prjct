package com.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;
import com.model.User;
import com.bean.RegisterBean;
import com.dao.NoteDao;
import com.model.Note;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Ramesh Fadatare
 */

@WebServlet(urlPatterns={"/"})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private NoteDao NoteDAO;
    private RegisterBean RegisterDAO;

   
    public void init() {
        userDAO = new UserDAO();
        NoteDAO = new NoteDao();
        RegisterDAO=new RegisterBean();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/newnnote":
                    showNewFormSpec(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                case "/newnote":
                    noteshowNewForm(request, response);
                    break;
                case "/insertnote":
                    noteinsertUser(request, response);
                    break;
                case "/deletenote":
                    notedeleteUser(request, response);
                    break;
                case "/editnote":
                    noteshowEditForm(request, response);
                    break;
                case "/updatenote":
                    noteupdateUser(request, response);
                    break;
                case "/listnotes":
                	notelistUser(request, response);
                    break;
                case "/listingnotes":
                	notelistingUser(request, response);
                    break;
                case "/list":
                	listUser(request, response);
                    break;
                case "/listing":
                	listingUser(request, response);
                    break;
                case "/addnotes":
                	noteshowNewFormspec(request, response);
                    break;
                case "/specnotes":
                	specNotes(request, response);
                    break;
                case "/insertspec":
                	insertspec(request, response);
                    break;
                
                default:
                    System.out.println("WrongChoice");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

   

	private void noteshowNewFormspec(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException, IOException {
		// TODO Auto-generated method stub
    	int id = Integer.parseInt(request.getParameter("id"));
    	System.out.println(id);
    	request.setAttribute("id", id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("noteform.jsp");
        dispatcher.forward(request, response);
	}

	private void insertspec(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException, IOException {
		// TODO Auto-generated method stub
    	int id = Integer.parseInt(request.getParameter("id"));
    	System.out.println(id);
        String startdate = request.getParameter("startdate");
        String enddate = request.getParameter("enddate");
        String reminderdate = request.getParameter("reminderdate");
        String status = request.getParameter("status");
        String tag = request.getParameter("tag");
        String description = request.getParameter("Descp");
        
        Note newUser = new Note(startdate,enddate,reminderdate,status,tag,description,id);
        NoteDAO.insertUser(newUser);
        response.sendRedirect("list");
	}

	private void showNewFormSpec(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, IOException {
		// TODO Auto-generated method stub
    	int id = Integer.parseInt(request.getParameter("id"));
    	System.out.println(id);
    	request.setAttribute("id", id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("specnoteform.jsp");
        dispatcher.forward(request, response);
		
	}

	private void specNotes(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, IOException {
		// TODO Auto-generated method stub
    	int id = Integer.parseInt(request.getParameter("id"));
    	List < Note > listUser = NoteDAO.selectSpecificNotes(id);
        request.setAttribute("list", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("specnote.jsp");
        dispatcher.forward(request, response);
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < User > listUser = userDAO.selectAllUsers();
        System.out.println(listUser);
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

	private void listingUser(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServletException {
				
		String name= request.getParameter("id");
		int id=UserDAO.getuserid(name);
		System.out.println(id);
		        List < User > listUser = userDAO.selectAllUsersbyuserid(id);
		        System.out.println(listUser);
		        request.setAttribute("listUser", listUser);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		        dispatcher.forward(request, response);
		    }
	
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
    	String name = request.getParameter("name");
        HttpSession session=request.getSession(false);
        String username=(String) session.getAttribute("namewa");
        try {
        	userDAO.insertUser(username,name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.sendRedirect("list");
    }
    
    

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        

        User book = new User(id, name);
        userDAO.updateUser(book);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteNoteUser(id);
        userDAO.deleteUser(id);
        response.sendRedirect("list");

    }
    private void notelistUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	        List < Note > listUser = NoteDAO.selectAllUsers();
    	        request.setAttribute("list", listUser);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("note-list.jsp");
    	        dispatcher.forward(request, response);
    	    }
    
    private void notelistingUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
		
		String name= request.getParameter("id");
		int id=UserDAO.getuserid(name);
    	        List < Note > listUser = NoteDAO.selectAllUsersbyid(id);
    	        request.setAttribute("list", listUser);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("note-list.jsp");
    	        dispatcher.forward(request, response);
    	    }

    	    private void noteshowNewForm(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("note-form.jsp");
    	        dispatcher.forward(request, response);
    	    }

    	    private void noteshowEditForm(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        Note existingUser = NoteDAO.selectUser(id);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("note-form.jsp");
    	        request.setAttribute("user", existingUser);
    	        dispatcher.forward(request, response);

    	    }

    	    private void noteinsertUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	    	HttpSession session=request.getSession(false);
    	        String username=(String) session.getAttribute("namewa");
    	        int uid=NoteDAO.getuid(username);
    	        int notebookid=NoteDAO.getnnid(uid);
    	        
    	        String startdate = request.getParameter("startdate");
    	        String enddate = request.getParameter("enddate");
    	        String reminderdate = request.getParameter("reminderdate");
    	        String status = request.getParameter("status");
    	        String tag = request.getParameter("tag");
    	        String description = request.getParameter("Descp");
    	        
    	        Note newUser = new Note(startdate,enddate,reminderdate,status,tag,description,notebookid);
    	        NoteDAO.insertUser(newUser);
    	        response.sendRedirect("listnotes");
    	    }

    	    private void noteupdateUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	    	int userid=Integer.parseInt(request.getParameter("id"));
    	    	System.out.println(userid);
    	        String startdate = request.getParameter("startdate");
    	        String enddate = request.getParameter("enddate");
    	        String reminderdate = request.getParameter("reminderdate");
    	        String status = request.getParameter("status");
    	        String tag = request.getParameter("tag");
    	        String description = request.getParameter("Descp");
    	        
    	        Note newUser = new Note(userid,startdate,enddate,reminderdate,status,tag,description);
    	        NoteDAO.updateUser(newUser);
    	        response.sendRedirect("listnotes");
    	    }

    	    private void notedeleteUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        int id = Integer.parseInt(request.getParameter("id"));
    	        NoteDAO.deleteUser(id);
    	        response.sendRedirect("listnotes");

    	    }
}