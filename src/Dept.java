
import java.util.ArrayList;
import java.util.List;

public class Dept {
	List<User> userList;

	// Constructors
	public Dept() {
		new ArrayList<User>();
	}
	public Dept(List<User> userList) {
		super();
		this.userList = userList;
	}
	
	// 유저 추가
	public void addUser(User user) {
		userList.add(user);
	}
	
	public void removeUser(User user) {
		if(userList.contains(user)) {
			userList.remove(user);
			System.out.println("해당 유저를 삭제하였습니다.");
		} 
		else {
			System.out.println("해당 유저가 존재하지 않습니다.");
		}
	}
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
}
