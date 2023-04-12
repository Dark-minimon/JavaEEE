package domain;

public class Student {

	private Long id;
	private String lastname;
	private String name;
	private String fname;
	private String bdate;
	private String phone;
	private String email;
	
	private Long idGroup;
	private Group group;
	
	public Student() {
	}
	public Student(String lastname, String name, String fname, String bdate, String phone, String email, Group group) {
	this.lastname = lastname;
	this.name = name;
	this.fname = fname;
	this.bdate = bdate;
	this.phone = phone;
	this.email = email;
	this.group = group;
	}
	public Student(String lastname, String name, String fname, String bdate, String phone, String email, Long idGroup, Group group) {
	this.lastname = lastname;
	this.name = name;
	this.fname = fname;
	this.bdate = bdate;
	this.phone = phone;
	this.email = email;
	this.idGroup = idGroup;
	this.group = group;
	}
	public Student(Long id, String lastname, String name, String fname, String bdate, String phone, String email, Long idGroup, Group group) {
	this.id = id;
	this.lastname = lastname;
	this.name = name;
	this.fname = fname;
	this.bdate = bdate;
	this.phone = phone;
	this.email = email;
	this.idGroup = idGroup;
	this.group = group;
	}
	public Long getId() {
	return id;
	}
	public void setId(Long id) {
	this.id = id;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getName() {
	return name;
	}
	public void setName(String name) {
	this.name = name;
	}
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
		
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
			
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Group group () {
		return group;
	}
	
	public String getGroup() {
		return group.getName();
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Long getIdGroup() {
		return idGroup;
	}
	public void setIdGroup(Long idGroup) {
		this.idGroup = idGroup;
	}
	
	@Override
	public String toString() {
	return "Student {" + "Id = " + id + ", LastName = " + lastname + ", Name = " + name + ", Fname = " + fname + 
			", Bdate = " + bdate + ", Phone = " + phone + ", Email = " + email + ", Group.name = " + getGroup() + "}";
	}
	}

