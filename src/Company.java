

import java.util.ArrayList;
import java.util.List;

public class Company {
	private String name;
	private int userCount;
	private int revenue;
	List<Dept> deptList = new ArrayList<Dept>();
	List<Notice> noticeList = new ArrayList<Notice>();;
	
	// Constructors
	public Company() {
		new ArrayList<Dept>();
	}
	public Company(String name, int userCount, int revenue) {
		super();
		this.name = name;
		this.userCount = userCount;
		this.revenue = revenue;
	}
	
	public Company(String name, int userCount, int revenue, List<Dept> deptList) {
		super();
		this.name = name;
		this.userCount = userCount;
		this.revenue = revenue;
		this.deptList = deptList;
	}
	// 부서 추가
	public void addDept(Dept dept) {
		deptList.add(dept); 
	}
	
	// 유저 추가
	public void addUser(User user, Dept dept) {
		// 유저의 부서가 이미 존재하면
		if(deptList.contains(dept)) {
			dept.addUser(user);
		} // 유저의 부서가 존재하지 않으면 
		else {
			addDept(dept); // 부서를 새로 생성
			dept.addUser(user);
		}
		userCount++;
	}
	
	// 유저 삭제
	public void removeUser(User user) {
		// 사내 부서에 user가 존재하는지 확인
		for (Dept dept : deptList) {
			if(dept.getUserList().contains(user)) {
				dept.getUserList().remove(user);
				System.out.println("성공적으로 삭제되었습니다.");
				return;
			}
		}
		System.out.println("존재하지 않는 User입니다.");
		
	}
	
	// 부서 삭제
	public void removeDept(Dept dept) {
		// 부서가 존재하는지 확인
		if(deptList.contains(dept)) {
			deptList.remove(dept);
			System.out.println("해당 부서가 성공적으로 삭제되었습니다.");
			return;
		}
		System.out.println("존재하지 않는 부서입니다.");
	}
	
	// 공지사항 추가
	public void addNotice(Notice notice) {
		noticeList.add(notice);
	}
	
	// 공지사항 제거
	public void removeNotice(Notice notice) {
		if(noticeList.contains(notice)) {
			noticeList.remove(notice);
			System.out.println("공지사항이 제거되었습니다.");
		}
		else {
			System.out.println("존재하지 않는 공지사항입니다.");
		}
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	public int getRevenue() {
		return revenue;
	}
	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}
	public List<Dept> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Dept> deptList) {
		this.deptList = deptList;
	}
	
	
	
	

}
