import java.io.Serializable;
import java.util.Date;

public class Schedule implements Serializable{



static private int cnt=1;
   
   private int no;
   private String writer;
   private String scheduleName;
   private int period;
   private int startDay;
   private String content;
   private boolean authority;//나만 보기(개인일정)
   private Alarm alarm;
   private String dept;//개인일정 뭐 그런거 부서별 

   public Schedule() {
      no=cnt;
      cnt++;
   }

   public Schedule(String writer, String scheduleName, int period, int startDay, String content, boolean authority,
         Alarm alarm,String category) {
      super();
      no=cnt;
      cnt++;
      this.writer = writer;
      this.scheduleName = scheduleName;
      this.period = period;
      this.startDay = startDay;
      this.content = content;
      this.authority = authority; //true 나만보기, false 같이보기
      this.alarm = alarm;
      this.dept=category;
   }
   
@Override
public String toString() {
	return "일정 번호: " + no + "\n 작성자: " + writer + "\n 일정 이름: " + scheduleName + "\n 기간: " + period
			+ "\n 작성 날짜: " + startDay + "\n 내용: " + content + "\n 권한: " + authority + "\n 알람: " + alarm
			+ "\n 카테고리: " + dept + "\n";
}

public void show() {
	   System.out.println(getNo()+"\t"+getScheduleName()+"\t"+getWriter()+"\t"+getContent());
   }
   

   public int getNo() {
      return no;
   }
   public void setNo(int no) {
      this.no = no;
   }
   public String getWriter() {
      return writer;
   }

   public void setWriter(String writer) {
      this.writer = writer;
   }

   public String getScheduleName() {
      return scheduleName;
   }

   public void setScheduleName(String scheduleName) {
      this.scheduleName = scheduleName;
   }

   public int getPeriod() {
      return period;
   }

   public void setPeriod(int period) {
      this.period = period;
   }

   public int getStartDay() {
      return startDay;
   }

   public void setStartDay(int startDay) {
      this.startDay = startDay;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

	public boolean getAuthority() {
		return authority;
	}
	
	public void setAuthority(boolean authority) {
		this.authority = authority;
	}

	public Alarm getAlarm() {
		return alarm;
	}
	
	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}
	public String getDept() {
		return dept;
	}

	public void setDept(String category) {
		this.dept = category;
	}

   
   
}