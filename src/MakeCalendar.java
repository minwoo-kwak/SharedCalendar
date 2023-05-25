import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MakeCalendar {
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
      scheduleList = new ArrayList<Schedule>();

//      Calendar gc = Calendar.getInstance();
//      int n = gc.getActualMaximum(Calendar.DATE);
//
//      gc.set(this.year,this.year,1); //5월 1일
//      int week = gc.get(Calendar.DAY_OF_WEEK);//1~7
//      
//      System.out.println("<"+this.year+"년 "+(this.month)+"월>");
//      System.out.println("일\t월\t화\t수\t목\t금\t토");
//      for(int i=1;i<week;i++) {
//         System.out.print("\t");
//      } 
//      for(int i =1;i<=n;i++) {
//         System.out.print(i+"\t");
//         if((week+i-1)%7==0) {
//            System.out.println();
//         }
//      }
//      System.out.println();
//      System.out.println();
   }
   
//   public static MakeCalendar getInstance(int year,int month) {
//      if(makeCalendar == null) {
//         makeCalendar = new MakeCalendar(year, month);
//         return makeCalendar;
//      }else {
//         return makeCalendar;
//      }
//   }
   public void show() {
      this.defaultCalendar = 1;
      //this.year = year;
      //this.month = month;
      

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
   
   public void remove(Schedule schedule) {
	   scheduleList.remove(schedule);
   }
   
   public void add(Schedule schedule) {
	      scheduleList.add(schedule);
	   }
   
//   public Schedule search(int no) {
//	   for(Schedule sch : scheduleList) {
//		   if(sch.getNo()==no) {
//			   return sch;
//		   }
//	   } 
//	   return null;
//   }
   public Schedule search(Object obj,int i) {
	   //숫자는 0
	   if(obj instanceof Integer) {
		   int no = (int)(obj);
		   for(Schedule sch : scheduleList) {
			   if(sch.getNo()==no) {
				   return sch;
			   }
		   }
		   //1작성자 2카테고리
	   }else {
		   String writer = obj+"";
		   for(Schedule sch : scheduleList) {
			   if(i==1) {
				   if(sch.getWriter().equals(writer)) {
					   return sch;
				   }
			   }else if(i==2) {
				   if(sch.getCategory().equals(writer)) {
					   return sch;
				   }
			   }
		   }
	   }
	return null;
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