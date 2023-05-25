import java.io.Serializable;
import java.util.Date;

public class Alarm implements Serializable{
   private boolean status;
   private Date time;
   
   public Alarm() { }

   public Alarm(boolean status) {
      super();
      this.status = status;
   }

   
   
   public boolean getStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   public Date getTime() {
      return time;
   }

   public void setTime(Date time) {
      this.time = time;
   }
}