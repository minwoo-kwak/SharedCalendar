

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
