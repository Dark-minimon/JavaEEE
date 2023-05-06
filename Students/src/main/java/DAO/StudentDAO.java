package DAO;

import java.io.FileNotFoundException;
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
import domain.Student;

public class StudentDAO extends ConnectionProperty {

	public StudentDAO()  {		}

	private static final String SELECT_ALL_GROUPS = "select id, name, faculty, year, type from studgroups";
	private static final String SELECT_ALL_STUDENTS = "SELECT * from students";

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

	public List<Student> selectAllStudents() {

		
		List<Student> students = new ArrayList<>();
		List<Group> groups = new ArrayList<>();
		
		try (Connection connection = getConnection()){

			
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GROUPS);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				groups.add(new Group(
					rs.getLong("id"),
					rs.getString("name"),
					rs.getString("faculty"),
					rs.getString("year"),
					rs.getString("type")));
		}
			long idGroup;
			preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				idGroup = rs.getLong("idGroup");
				students.add(new Student(
							rs.getLong("id"),
							rs.getString("lastname"),
							rs.getString("name"),
							rs.getString("fname"),
							rs.getString("bdate"),
							rs.getString("phone"),
							rs.getString("email"),
							idGroup,
							findById(idGroup, (ArrayList<Group>) groups)
						));
				
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return students;
	}
	

}
