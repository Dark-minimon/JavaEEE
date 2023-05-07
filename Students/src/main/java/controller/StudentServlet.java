package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.ConnectionProperty;
import domain.Student;
import domain.Group;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String SELECT_ALL_GROUPS = "SELECT id, name, faculty, year, type FROM studgroups";
	String SELECT_ALL_STUDENTS = "SELECT id, idGroup, lastname, name, fname, bdate, phone, email FROM students";
	String INSERT_STUDENT = "INSERT INTO students (idGroup, lastname, name, fname, bdate, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
	ArrayList<Group> groups = new ArrayList<>();
	ArrayList<Student> students = new ArrayList<>();
	String userPath;
	public StudentServlet() throws FileNotFoundException, IOException {
		prop = new ConnectionProperty();
	}

	private Group findById(Long id, ArrayList<Group> groups) {
		if(groups != null) {
			for(Group r: groups) {
				if((r.getId()).equals(id)) {
					return r;
				}
			}
		} else {
			return null;
		}
		return null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		ConnectionProperty builder = new ConnectionProperty();
		try (Connection conn = builder.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL_GROUPS);
			if(rs != null) {
				groups.clear();
				while (rs.next()) {
					groups.add(new Group(rs.getLong("id"),
							rs.getString("name"),
							rs.getString("faculty"),
							rs.getString("year"),
							rs.getString("type")));
				}
				rs.close();
				request.setAttribute("groups", groups);
			}

			long idGroup;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL_STUDENTS);
			if(rs != null) {
				students.clear();
				while (rs.next()) {
					idGroup = rs.getLong("idGroup");
					students.add(new Student(rs.getLong("id"),
							rs.getString("lastname"),
							rs.getString("name"),
							rs.getString("fname"),
							rs.getString("bdate"),
							rs.getString("phone"),
							rs.getString("email"),
							idGroup,
							findById(idGroup, groups)
					));
				}
				rs.close();
				request.setAttribute("students", students);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		String userPath = request.getServletPath();
		if("/student".equals(userPath)){
			request.getRequestDispatcher("/WEB-INF/student.jsp")
					.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConnectionProperty builder = new ConnectionProperty();
		try (Connection conn = builder.getConnection()){
			String lastname = request.getParameter("lastname");
			String name = request.getParameter("name");
			String fname = request.getParameter("fname");
			String bdate = request.getParameter("bdate");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String group = request.getParameter("group");
			int index1 = group.indexOf('=');
			int index2 = group.indexOf(",");
			String r1 = group.substring(index1+1, index2);
			Long idGroup = Long.parseLong(r1.trim());
			try (PreparedStatement preparedStatement =
					conn.prepareStatement(INSERT_STUDENT)){
			preparedStatement.setLong(1, idGroup);
			preparedStatement.setString(2, lastname);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, fname);
			preparedStatement.setString(5, bdate);
			preparedStatement.setString(6, phone);
			preparedStatement.setString(7, email);
			int rows = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	} catch (Exception e) {
		System.out.println(e);
		getServletContext().getRequestDispatcher("/WEB-INF/view/computer.jsp")
				.forward(request, response);
	}
		doGet(request, response);
	}
}