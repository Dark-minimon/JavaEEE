package domain;

public class Group {

	private Long id;
	private String name;
	private String faculty;
	private String year;
	private String type;

	public Group() {
	}
	public Group(String name, String faculty, String year, String type) {
	this.name = name;
	this.faculty = faculty;
	this.year = year;
	this.type = type;
	}
	public Group(Long id, String name, String faculty, String year, String type) {
	this.id = id;
	this.name = name;
	this.faculty = faculty;
	this.year = year;
	this.type = type;
	}
	public Long getId() {
	return id;
	}
	public void setId(Long id) {
	this.id = id;
	}
	
	public String getName() {
	return name;
	}
	public void setName(String name) {
	this.name = name;
	}
	
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
		
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
		
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
				
	@Override
	public String toString() {
	return "Student {" + "Id = " + id + ", Name = " + name + ", Faculty = " + faculty + 
			", Year = " + year + ", Type = " + type + "}";
	}
	}
