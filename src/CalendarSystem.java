import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Calendar;

public class CalendarSystem {
   
    Scanner sc = new Scanner(System.in);
    MakeCalendar mc;
    FileSystem fs = new FileSystem();
    
    public void init() {
//        String filePath = "C:\\Temp";
//        String fileName="Schedule Data Base.txt";

        System.out.println("날짜를 입력하세요, 종료:q");
        System.out.println("형식 : yyyy-MM");
        System.out.print("> ");
        String date = sc.nextLine();
        if(date.equals("q")) return;
        String[] dateArr = date.split("-");  
        mc = new MakeCalendar(Integer.parseInt(dateArr[0]),Integer.parseInt(dateArr[1]));
//        mc.load(filePath, fileName);
        mc.show();
        menu();
    }
    
    public void menu() {
       while(true) {
           System.out.println("1.하루 일정 보기 2.일정 검색 3.뒤로가기");
            System.out.print("> ");
            String menu = sc.nextLine();
            switch (menu) {
          case "1":
             int[] info = showList();
             int selectedDay = info[0];
             int status= info[1];
             scheduleManage(status,selectedDay);
             break;
          case "2":
             searchSchedule();
             break;
          case "3":
             mc.show();
             break;

          default:
             break;
          }
       }
    }
    private void searchSchedule() {
      // TODO Auto-generated method stub
       System.out.println("찾을 방법을 입력하세요 1.일정번호 2.작성자 3.카테고리 ");
       System.out.print("> ");
       String menu = sc.nextLine();
       String obj;
       switch (menu) {
      case "1":
         System.out.print("검색할 일정의 일정번호를 입력하세요 > ");
         obj = sc.nextLine();
         mc.search(obj,0);
         break;
      case "2":
    	  System.out.print("검색할 일정의 작성자를 입력하세요 > ");
          obj = sc.nextLine();
    	  mc.search(obj,1);
         break;
      case "3":
    	  System.out.print("검색할 일정의 카테고리를 입력하세요 > ");
          obj = sc.nextLine();
    	  mc.search(obj,2);
         break;
      }
      
   }
   public int[] showList() {
       System.out.println("일정을 볼 날짜를 입력하세요");
        System.out.print("> ");
        int selectedDay = Integer.parseInt(sc.nextLine());
        //역직렬화로 불러온다
        int status = mc.daySchedule(selectedDay);
        int[] info ={selectedDay,status};
        return info;
        
        
    }
    public void scheduleManage(int status, int selectedDay) {
       if(status==0) {
            System.out.println("해당일자에 일정이 없습니다.");
            System.out.println("1.일정 추가");
         }else{
            System.out.println("1.일정 추가 2.일정 상세 보기  3.일정 수정 4.일정 삭제 5.뒤로가기6.리포트저장");
         }
       String menu = sc.nextLine();
        switch (menu) {
        case "1":
           addSchedule(selectedDay);
           break;
        case "2":
           scheduleDetail(selectedDay);
           break;
        case "3":
           updateSchedule(selectedDay);
           break;   
        case "4":
           removeSchedule(selectedDay);
           break;
        case "5":
           mc.show();
           break;
        case "6":
        	makeReport();
        }
    }
   private void removeSchedule(int selectedDay) {
      // TODO Auto-generated method stub
      System.out.println("삭제할 일정의 일정번호를 입력하세요 > ");
      int upNum = Integer.parseInt(sc.nextLine());
      Schedule sch= mc.search(upNum,0);
      if(sch!=null) {
         
         mc.remove(sch);
         }
      isSchedule(sch);
      }
   private void scheduleDetail(int selectedDay) {
      // TODO Auto-generated method stub
      System.out.println("상세보기할 일정의 일정번호를 입력하세요 > ");
      int upNum = Integer.parseInt(sc.nextLine());
      Schedule sch= mc.search(upNum,0);
      if(sch!=null) {
            System.out.println("일정 번호\t일정 이름\t일정 작성자\t일정 카테고리\t일정 권한\t일정설명");
            System.out.print(sch.getNo()+"\t");
            System.out.print(sch.getScheduleName()+"\t");
            System.out.print(sch.getWriter()+"\t");
            System.out.print(sch.getCategory()+"\t");
            System.out.print(sch.getAuthority()+"\t");
            System.out.print(sch.getContent()+"\n");
         }
      isSchedule(sch);
      }
      
   
   private void updateSchedule(int selectedDay) {
      System.out.print("수정할 일정의 일정번호를 입력하세요 > ");
      int upNum = Integer.parseInt(sc.nextLine());
      Schedule sch= mc.search(upNum,0);
      if(sch!=null) {
              System.out.println("수정할 일정을 입력하세요");
              System.out.print("일정이름 > ");
              sch.setScheduleName(sc.nextLine());
              System.out.print("작성자 > ");
              sch.setWriter(sc.nextLine());        
              System.out.print("일정권한 > ");
              sch.setAuthority(sc.nextLine());             
              System.out.print("설명 > ");
              sch.setContent(sc.nextLine());              
              System.out.print("기간 > ");
              sch.setPeriod(Integer.parseInt(sc.nextLine()));              
              System.out.print("알람여부 y or n > ");
              //Alarm alarm = new Alarm();
              if(sc.nextLine().equals("y")) {
                 sch.getAlarm().setStatus(true);
              }else {
                 sch.getAlarm().setStatus(false);
              }
              //sch.setAlarm(alarm);
              //Alarm alarm = sch.ge
              String al=sc.nextLine();
              System.out.print("카테고리 > ");
              sch.setCategory(sc.nextLine());
              //String category=sc.nextLine();
              //Alarm alarm = new Alarm();
              
         }
      isSchedule(sch);
      }
   
   private void addSchedule(int selectedDay) {
       // 파일 생성 및 쓰기
       String filePath = "C:\\Temp";
       String fileName="Schedule Data Base.txt";
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
        System.out.print("알람여부 y or n > ");
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
        mc.add(schedule,filePath,fileName);
        System.out.println("일정 추가 완료");
        //String sb ="";
//        StringBuilder sb= new StringBuilder();
//        sb.append(schedule.getNo()+" ");
//        sb.append(schedule.getScheduleName()+" ");
//        sb.append(schedule.getPeriod()+" ");
//        sb.append(schedule.getStartDay()+" ");
//        sb.append(schedule.getContent()+" ");
//        sb.append(schedule.getAuthority()+" ");
//        sb.append(schedule.getAlarm().getStatus()+" ");
//        sb.append(schedule.getCategory()+" ");
        

//        if(fs.writeFile(filePath,fileName,sb)) {
//        	System.out.println("일정을 저장을 완료했습니다.");
//        }else {
//        	System.out.println("파일을 저장하는 도중에 오류가 발생했습니다.");
//        }
        
        mc.show();
        menu();
   }
   
   public void isSchedule(Schedule schedule) {
      if(schedule==null) {
         System.out.println("해당하는 일정이 없습니다");
         mc.show();
         menu();
      }else {
         System.out.println("완료");
         mc.show();
         menu();
      }
   }
   
   // 리포트 생성
   public void makeReport() {
       List<Schedule> pastSchedules = new ArrayList<>();
       List<Schedule> upcomingSchedules = new ArrayList<>();

       Date currentDate = new Date(); // 현재 날짜 가져오기
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(currentDate);

       // 현재 날짜를 기준으로 지난 일정과 앞으로의 일정을 분류
       for (Schedule schedule : mc.getScheduleList()) {
           if (schedule.getStartDay() < calendar.get(Calendar.DAY_OF_MONTH)) {
               pastSchedules.add(schedule);
           } else {
               upcomingSchedules.add(schedule);
               System.out.println();
           }
       }

       // 분류된 일정들을 리포트로 작성하여 파일로 저장
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
       String timeStamp = dateFormat.format(currentDate);
       String fileName = "report_" + timeStamp + ".txt"; // 파일 확장자 : txt

       String filePath = "C:\\Temp";

       StringBuilder sb= new StringBuilder(); 
    // 지난 일정
       sb.append("===== Past Schedules =====\n");
       for(Schedule schedule : pastSchedules) {
    	   sb.append("일정 번호: " + schedule.getNo()+"\n");
    	   sb.append("일정 이름: " + schedule.getScheduleName()+"\n");
    	   sb.append("작성자: " + schedule.getWriter()+"\n\n\n");
       }
       // 앞으로의 일정
       sb.append("===== Upcoming Schedules =====");
       for (Schedule schedule : upcomingSchedules) {
    	   sb.append("일정 번호: " + schedule.getNo()+"\n");
    	   sb.append("일정 이름: " + schedule.getScheduleName()+"\n");
    	   sb.append("작성자: " + schedule.getWriter()+"\n\n\n");
       }
//       if(fs.writeFile(filePath,fileName,sb)) {
//    	   System.out.println("리포트가 성공적으로 생성되었습니다.");
//       }else {
//       	System.out.println("파일을 저장하는 도중에 오류가 발생했습니다.");
//       }

   }
    

}

