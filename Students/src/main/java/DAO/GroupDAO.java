package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Group;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class GroupDAO {
	private String jdbcURL = "jdbc:postgresql://localhost:5432/studs";
	private String jdbcUsername = "postgres";
	private String jdbcPassword = "87654321";

	private static final String INSERT_GROUPS_SQL = "INSERT INTO studgroups" + " (name, faculty, year ,type) VALUES " + " (?, ?, ?, ?);";
	String insert_group = "INSERT INTO studgroups(name) VALUES(?)";
	private static final String SELECT_ALL_GROUPS = "select * from studgroups";
	 private static final String UPDATE_GROUPS_SQL = "update studgroups set name = ?, faculty = ?, year= ?, type = ?";
	public GroupDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
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
		
	public List<Group> selectAllGroups() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Group> groups = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GROUPS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long id = rs.getLong("id");
				String name = rs.getString("name");
				String faculty = rs.getString("faculty");
				String year = rs.getString("year");
				String type = rs.getString("type");
			
				groups.add(new Group(id, name, faculty, year, type));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return groups;
	}
	
/////
	
	public void insertGroup(Group group) throws SQLException {
		System.out.println(INSERT_GROUPS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GROUPS_SQL)) {
			preparedStatement.setString(1, group.getName());
			preparedStatement.setString(2, group.getFaculty());
			preparedStatement.setString(3, group.getYear());
			preparedStatement.setString(4, group.getType());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public boolean updateGroup(Group group) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_GROUPS_SQL);) {
			System.out.println("updated Author:"+statement);
			statement.setString(1, group.getName());
			statement.setString(2, group.getFaculty());
			statement.setString(3, group.getYear());
			statement.setString(4, group.getType());
			
			statement.setLong(5, group.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
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