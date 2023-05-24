

import java.util.ArrayList;
import java.util.List;

public class Company {
	private String name;
	private int userCount;
	private int revenue;
	List<Dept> deptList;
	
	public Company() {
		new ArrayList<Dept>();
	}
	public Company(String name, int userCount, int revenue, List<Dept> deptList) {
		super();
		this.name = name;
		this.userCount = userCount;
		this.revenue = revenue;
		this.deptList = deptList;
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
