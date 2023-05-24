
public class User {
	private String id;
	private String pw;
	private String name;
	private String position;
	private String dept;
	private String phone;
	private String email;
	private String join;
	
	public User() {}
	public User(String id, String pw, String name, String position, String dept, String phone, String email,
			String join) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.position = position;
		this.dept = dept;
		this.phone = phone;
		this.email = email;
		this.join = join;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
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
	public String getJoin() {
		return join;
	}
	public void setJoin(String join) {
		this.join = join;
	}
	
	
}


