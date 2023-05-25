
import java.time.LocalDate;

public class User {
	private String id;
	private String pw;
	private String name;
	private String position;
	private String dept;
	private String phone;
	private String email;
	private LocalDate hireDate;
	private Vacation vacation;

	public User() {
	}

	public User(String id, String pw, String name, String position, String dept, String phone, String email,
			LocalDate hireDate) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.position = position;
		this.dept = dept;
		this.phone = phone;
		this.email = email;
		this.hireDate = hireDate;
	}

	public User(String id, String pw, String name, String position, String dept, String phone, String email,
			LocalDate hireDate, Vacation vacation) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.position = position;
		this.dept = dept;
		this.phone = phone;
		this.email = email;
		this.hireDate = hireDate;
		this.vacation = vacation;
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

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pw=" + pw + ", name=" + name + ", position=" + position + ", dept=" + dept
				+ ", phone=" + phone + ", email=" + email + ", hireDate=" + hireDate + ", vacation=" + vacation + "]";
	}

}
