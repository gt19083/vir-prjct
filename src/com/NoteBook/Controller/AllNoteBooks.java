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

import com.NoteBook.Dao.NoteBookShowDao;
import com.NoteBook.bean.NoteBook;

@WebServlet("/AllNoteBooks")
public class AllNoteBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NoteBookShowDao userDAO;

    public void init() {
        userDAO = new NoteBookShowDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	List < NoteBook > listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("notebookshow.jsp");
        dispatcher.forward(request, response);
        String action = request.getServletPath();

        try {
            switch (action) {
                
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    
/**
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }
**/
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        NoteBook existingUser = userDAO.selectUser(id);
        System.out.println("here");
        RequestDispatcher dispatcher = request.getRequestDispatcher("list");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        
        String name = request.getParameter("name");
        

        NoteBook book = new NoteBook(name);
        userDAO.updateUser(book);
        response.sendRedirect("list");
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	        List < NoteBook > listUser = userDAO.selectAllUsers();
    	        request.setAttribute("listUser", listUser);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("AllNoteBooks.jsp");
    	        dispatcher.forward(request, response);
    	    }
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(userDAO.deleteUser(id));
        response.sendRedirect("list");

    }
}





