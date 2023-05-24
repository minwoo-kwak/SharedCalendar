
import java.util.ArrayList;
import java.util.List;

public class Dept {
	List<User> userList;

	public Dept() {
		new ArrayList<User>();
	}
	public Dept(List<User> userList) {
		super();
		this.userList = userList;
	}
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
}
