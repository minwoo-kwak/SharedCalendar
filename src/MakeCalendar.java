import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


public class MakeCalendar {
//   public static 
	private List<Schedule> scheduleList;
   private int defaultCalendar;
   private int year;
   private int month;
   private int selectedDay;
   //private static MakeCalendar makeCalendar;
   //private List<Schedule>[] dayList;
   
   public MakeCalendar() {
      super();
   }
   public MakeCalendar( int year, int month) {
      super();
      this.defaultCalendar = 1;
      this.year = year;
      this.month = month;
      scheduleList = new LinkedList<Schedule>();
   }

   public void show() {
      this.defaultCalendar = 1;
      
      Calendar gc = Calendar.getInstance();
      int n = gc.getActualMaximum(Calendar.DATE);

      gc.set(this.year,this.year,1); //5월 1일
      int week = gc.get(Calendar.DAY_OF_WEEK);//1~7
      
      System.out.println("<"+this.year+"년 "+(this.month)+"월>");
      System.out.println("일\t월\t화\t수\t목\t금\t토");
      for(int i=1;i<week;i++) {
         System.out.print("\t");
      } 
      for(int i =1;i<=n;i++) {
         System.out.print(i+"\t");
         if((week+i-1)%7==0) {
            System.out.println();
         }
      }
      System.out.println();
      System.out.println();
   }

   
   public int daySchedule(int selectedDay) {
      System.out.println("번호\t일정이름\t일정 작성자\t일정설명");
      int i=0;
      for(Schedule sc : scheduleList) {
         
         if(sc.getStartDay()==selectedDay) {
            i++;
            System.out.println(sc.getNo()+"\t"+sc.getScheduleName()+"\t"+sc.getWriter()+"\t"+sc.getContent());
         }
      }
      return i;
   }
   public void update(Schedule sch,String str,String filePath,String fileName) {
	   String[] arr = str.split(",");
	   sch.setScheduleName(arr[0]);
	   sch.setWriter(arr[1]); 
	   sch.setAuthority(arr[2]);
	   sch.setContent(arr[3]);
	   sch.setPeriod(Integer.parseInt(arr[4]));
	   if(arr[5].equals("y")) {
           sch.getAlarm().setStatus(true);
        }else {
           sch.getAlarm().setStatus(false);
        }
	   sch.setCategory(arr[6]);
	   FileSystem.save(filePath,fileName,scheduleList);
   }
   
   public void remove(Schedule schedule,String filePath,String fileName) {
	   scheduleList.remove(schedule);
	   FileSystem.save(filePath,fileName,scheduleList);
   }
   
   public void add(Schedule schedule,String filePath,String fileName) {
	   if(scheduleList!=null) {
		   schedule.setNo(scheduleList.size()+1);
	   }
      scheduleList.add(schedule);
      FileSystem.save(filePath,fileName,scheduleList);
   	}
   
	public void load(String filePath,String fileName) {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(filePath+"\\"+fileName));
			scheduleList = (List<Schedule>) ois.readObject();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

   
   public Schedule search(int no) {
	   for(Schedule sch : scheduleList) {
		   if(sch.getNo()==no) {
			   return sch;
		   }
	   } 
	   return null;
   }
   public int search(String str,String menu) {
	   System.out.println("번호\t일정이름\t일정 작성자\t일정설명");
	   int cnt=0;
	   //숫자는 0 어짜피 하나 밖에 없음
	   //if(str instanceof Integer) {
	   try {
		int no = Integer.parseInt(str);
		   for(Schedule sch : scheduleList) {
			   if(sch.getNo()==no) {
				   System.out.println(sch.getNo()+"\t"+sch.getScheduleName()+"\t"+sch.getWriter()+"\t"+sch.getContent());
				   cnt++;
				   //return sch;
			   }
		   } System.out.println();
	} catch (Exception e) {
		// TODO: handle exception
		//2작성자 3카테고리
	   for(Schedule sch : scheduleList) {
		   if(menu.equals("2")) {
			   if(sch.getWriter().equals(str)) {
				   System.out.println(sch.getNo()+"\t"+sch.getScheduleName()+"\t"+sch.getWriter()+"\t"+sch.getContent());
				   cnt++;
				   //return sch;
			   }
		   }else if(menu.equals("3")) {
			   if(sch.getCategory().equals(str)) {
				   System.out.println(sch.getNo()+"\t"+sch.getScheduleName()+"\t"+sch.getWriter()+"\t"+sch.getContent());
				   cnt++;
				   //return sch;
			   }
		   }
	   }
	}
	return cnt;
}

   
   

public List<Schedule> getScheduleList() {
	return scheduleList;
}
public void setScheduleList(List<Schedule> scheduleList) {
	this.scheduleList = scheduleList;
}
public int getDefaultCalendar() {
	return defaultCalendar;
}
public void setDefaultCalendar(int defaultCalendar) {
	this.defaultCalendar = defaultCalendar;
}
public int getYear() {
	return year;
}
public void setYear(int year) {
	this.year = year;
}
public int getMonth() {
	return month;
}
public void setMonth(int month) {
	this.month = month;
}
public int getSelectedDay() {
	return selectedDay;
}
public void setSelectedDay(int selectedDay) {
	this.selectedDay = selectedDay;
}

   
   

}