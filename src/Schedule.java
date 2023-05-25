import java.io.Serializable;
import java.util.Date;

public class Schedule implements Serializable{

   @Override
	public String toString() {
		return "Schedule [no=" + no + ", writer=" + writer + ", scheduleName=" + scheduleName + ", period=" + period
				+ ", startDay=" + startDay + ", content=" + content + ", authority=" + authority + ", alarm=" + alarm
				+ ", category=" + category + "]";
	}

static private int cnt=1;
   
   private int no;
   private String writer;
   private String scheduleName;
   private int period;
   private int startDay;
   private String content;
   private String authority;
   private Alarm alarm;
   private String category;
   
   public Schedule() {
      no=cnt;
      cnt++;
   }

   public Schedule(String writer, String scheduleName, int period, int startDay, String content, String authority,
         Alarm alarm, String category) {
      super();
      no=cnt;
      cnt++;
      this.writer = writer;
      this.scheduleName = scheduleName;
      this.period = period;
      this.startDay = startDay;
      this.content = content;
      this.authority = authority;
      this.alarm = alarm;
      this.category = category;
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

   public String getAuthority() {
      return authority;
   }

   public void setAuthority(String authority) {
      this.authority = authority;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

	public Alarm getAlarm() {
		return alarm;
	}
	
	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}


   
   
}