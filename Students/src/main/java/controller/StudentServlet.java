package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;

import DAO.StudentDAO;
import DAO.GroupDAO;
import domain.Student;
import domain.Group;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
/**
* 
*/
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO daoStudent;
	private GroupDAO daoGroup;

	public void init() {
		daoStudent = new StudentDAO();
		daoGroup = new GroupDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
				default:
					listBlog(request, response);
					break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listBlog(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Group> listGroup = daoGroup.selectAllGroups();
		List<Student> listStudent = daoStudent.selectAllStudents();
		request.setAttribute("listGroup", listGroup);
		request.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/student.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
   