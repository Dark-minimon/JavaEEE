package controller.Group;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.ConnectionProperty;
import domain.Group;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/group")
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;
	String SELECT_ALL_GROUPS = "SELECT id, name, faculty, year, type FROM studgroups";
	String INSERT_GROUP = "INSERT INTO studgroups (name, faculty, year, type) VALUES (?, ?, ?, ?)";
	ArrayList<Group> groups = new ArrayList<>();
	String userPath;
	public GroupServlet() throws FileNotFoundException, IOException {
		prop = new ConnectionProperty();
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
		} catch (Exception e) {
			System.out.println(e);
		}
		userPath = request.getServletPath();
		if("/group".equals(userPath)){
			request.getRequestDispatcher("/WEB-INF/group.jsp")
					.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConnectionProperty builder = new ConnectionProperty();
		try (Connection conn = builder.getConnection()){
			String name = request.getParameter("name");
			String faculty = request.getParameter("faculty");
			String year = request.getParameter("year");
			String type = request.getParameter("type");
			Group newGroup = new Group(name, faculty, year, type);
			try (PreparedStatement preparedStatement =
						 conn.prepareStatement(INSERT_GROUP)){
				preparedStatement.setString(1, newGroup.getName());
				preparedStatement.setString(2, newGroup.getFaculty());
				preparedStatement.setString(3, newGroup.getYear());
				preparedStatement.setString(4, newGroup.getType());
				int result = preparedStatement.executeUpdate();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
			getServletContext().getRequestDispatcher("/WEB-INF/group.jsp")
					.forward(request, response);
		}
		doGet(request, response);
	}
}