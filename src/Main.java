
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
   static int a=0;
   public static void main(String[] args) {
      // TODO Auto-generated method stub
      Scanner sc = new Scanner(System.in);
      
	      //로그인
	      UserManager um = new UserManager();
	      um.init();
      
         System.out.println("날짜를 입력하세요, 종료:q");
         System.out.println("형식 : yyyy-MM");
         System.out.print("> ");
         //String pattern = "(19|20)\\d{2}(0[1-9]|1[012])";
         String date = sc.nextLine();
         //if(date.equals("q"))break;
         //if(Pattern.matches(pattern,date)) {
            String[] dateArr = date.split("-");
            Schedule sc1 = new Schedule();
            sc1.setStartDay(1);
            Schedule sc2 = new Schedule();
            sc2.setStartDay(1);
            Schedule sc3 = new Schedule();
            sc3.setStartDay(1);
            
            
            
            //년월입력
            
            MakeCalendar mc = new MakeCalendar(Integer.parseInt(dateArr[0]),Integer.parseInt(dateArr[1]));
            //MakeCalendar mc = MakeCalendar.getInstance(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]));
            while(true) {
               mc.show();
               System.out.println("1.하루 일정 보기 2.일정 검색 3.뒤로가기");
               System.out.print("> ");
               String menu = sc.nextLine();
               switch (menu) {
               case "1":
                  System.out.println("일정을 볼 날짜를 입력하세요");
                  System.out.print("> ");
                  int selectedDay = Integer.parseInt(sc.nextLine());
                  mc.daySchedule(selectedDay);
                  menu=sc.nextLine();
                  switch (menu) {
                  case "1":
                     System.out.println("추가할 일정을 입력하세요");
                     System.out.print("일정이름 > ");
                     String scheduleName=sc.nextLine();
                     System.out.print("작성자 > ");
                     String writer=sc.nextLine();
                     System.out.print("일정권한 > ");
                     String authority=sc.nextLine();
                     System.out.print("설명 > ");
                     String content=sc.nextLine();
                     System.out.print("기간 > ");
                     String period=sc.nextLine();
                     System.out.print("알람여부 > y or n");
                     String al=sc.nextLine();
                     System.out.print("카테고리 > ");
                     String category=sc.nextLine();
                     Alarm alarm = new Alarm();
                     if(al.equals("y")) {
                        alarm.setStatus(true);
                     }else {
                        alarm.setStatus(false);
                     }
                     Schedule schedule = new Schedule(writer, scheduleName, Integer.parseInt(period), selectedDay, content, authority, alarm, category);
                     
                     mc.add(schedule);
                     break;
                  case "2":
                     
                     break;   
                  case "3":
                     
                     break;
                  case "4":
                     
                     break;
   
                  }
                  break;
               case "2":
                  
                  break;
               case "3":
      
                  break;
   
               default:
                  break;
               }
               //날짜

               //수정삭제추가
                  //추가
                  //추가할 날짜 입력 -> selecedDay dayList schedule
                  //수정
                  //삭제
                  //검색   
         //}
      }
      
      
   }

}