package com.NoteBook.Controller;

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

import com.NoteBook.bean.NoteBook;
import com.NoteBook.Dao.NoteBookDao;


/**
 * Servlet implementation class NoteBookServlet
 */
@WebServlet("/NoteBookServlet")
public class NoteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoteBookDao notebookDao;
    public NoteBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() {
    	notebookDao = new NoteBookDao();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        doGet(request, response);
    	    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        
    	                    
    	                    	String name = request.getParameter("username");
    	            	        HttpSession session=request.getSession(false);
    	            	        String username=(String) session.getAttribute("namewa");
    	                        System.out.println("try catch session "+username);
    	                        System.out.println("try catch notbook "+name);
    	            	        
    	            	        try {
									NoteBookDao.insertUser(username,name);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
    	            	        System.out.println("inert");
    	            	        response.sendRedirect("Welcome.jsp");
							
    	                    
    	    }
    
    private void listUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	        List < NoteBook > listUser = NoteBookDao.selectAllUsers();
    	        request.setAttribute("listUser", listUser);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
    	        dispatcher.forward(request, response);
    	    }
}
