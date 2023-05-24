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
   public void add(Schedule schedule) {
      scheduleList.add(schedule);
   }
   
   public void daySchedule(int selectedDay) {
      System.out.println("번호\t일정이름\t일정 작성자\t일정설명");
      int i=0;
      for(Schedule sc : scheduleList) {
         
         if(sc.getStartDay()==selectedDay) {
            i++;
            System.out.println(sc.getNo()+"\t"+sc.getScheduleName()+"\t"+sc.getWriter()+"\t"+sc.getContent());
         }
      }
      if(i==0) {
         System.out.println("해당일자에 일정이 없습니다.");
      }
      System.out.print("1.일정 추가");
      if(i!=0) {
         System.out.println(" 2.일정 수정 3.일정 삭제 4.뒤로가기");
      }
   }
   
   


   
   

}