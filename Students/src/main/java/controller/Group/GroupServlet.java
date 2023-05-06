package controller.Group;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.ArrayList;
import java.util.List;

import domain.Group;

import DAO.GroupDAO;
/**
* 
*/
@WebServlet("/group")
public class GroupServlet extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
	private GroupDAO GroupDAO;

	public void init() {
		GroupDAO = new GroupDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException{
			doGet(request,response);
	}
	
	protected void doGet( HttpServletRequest request,  HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertGroup(request, response);
				break;
					
			case "/update":
				updateGroup(request, response);
				break;
			default:
				listGroup(request, response);
				break;
			}
 } catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listGroup( HttpServletRequest request,  HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Group> listGroup = GroupDAO.selectAllGroups();
		request.setAttribute("listGroup", listGroup);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/group.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm( HttpServletRequest request,  HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/group.jsp");
		dispatcher.forward(request, response);
	}

	private void insertGroup( HttpServletRequest request,  HttpServletResponse response) 
			throws SQLException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		String faculty = request.getParameter("faculty");
		String year = request.getParameter("year");
		String type = request.getParameter("type");
			
		Group newGroup = new Group(id, name, faculty, year, type);
		GroupDAO.insertGroup(newGroup);
		response.sendRedirect("list");
	}

	private void updateGroup(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		String faculty = request.getParameter("faculty");
		String year = request.getParameter("year");
		String type = request.getParameter("type");
			
		Group newGroup = new Group(id, name, faculty, year, type);
		GroupDAO.insertGroup(newGroup);
		response.sendRedirect("list");
	}

}