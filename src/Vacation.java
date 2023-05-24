import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Vacation {
	private int annualLeave;
	private int usedLeave;
	private int ableLeave;
	
	public Vacation() {}
	public Vacation(int annualLeave, int usedLeave, int ableLeave) {
		super();
		this.annualLeave = annualLeave;
		this.usedLeave = usedLeave;
		this.ableLeave = ableLeave;
	}
	
	// 연차 계산
	public void leaveCal(User user) {
		// 직원의 입사일과 연차 계산 적용일
		LocalDate hireDate = user.getHireDate();
		LocalDate now = LocalDate.now();
		
		// 입사일로부터 몇 년이 지났는지 계산
		long totalDays = ChronoUnit.DAYS.between(hireDate, now);
		int yearsWorked = (int) totalDays / 365; // 입사일로부터 경과한 연수
		int vacationDays;
		
		// 경과한 연수에 따른 연차 계산
		if(yearsWorked < 1) {
			vacationDays = 0;
		} else if (yearsWorked < 5) {
			vacationDays = 15;
		} else if (yearsWorked < 10) {
			vacationDays = 20;
		} else {
			vacationDays = 25;
		}
		annualLeave = vacationDays; // 총 연차일수
		ableLeave = annualLeave - usedLeave; // 남은 연차일수
		System.out.println("총 연차 일수: " + annualLeave);
		System.out.println("사용한 연차 일수: " + usedLeave);
		System.out.println("남은 연차 일수: " + ableLeave);
	}
	
	public void leavePro(User user) {
		System.out.println("사용한 연차 일수: " + usedLeave);
	}
	
	public int getAnnualLeave() {
		return annualLeave;
	}
	public void setAnnualLeave(int annualLeave) {
		this.annualLeave = annualLeave;
	}
	public int getUsedLeave() {
		return usedLeave;
	}
	public void setUsedLeave(int usedLeave) {
		this.usedLeave = usedLeave;
	}
	public int getAbleLeave() {
		return ableLeave;
	}
	public void setAbleLeave(int ableLeave) {
		this.ableLeave = ableLeave;
	}
	
	

}
