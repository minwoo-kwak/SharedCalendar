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
    String filePath = "C:\\Temp";
    String fileName="Schedule Data Base.txt";
    User user;
    ReportSystem rs = new ReportSystem();
    public void init(User user) {

    	this.user=user;
        System.out.println("날짜를 입력하세요, 종료:q");
        System.out.println("형식 : yyyy-MM");
        System.out.print("> ");
        String date = sc.nextLine();
        if(date.equals("q")) return;
        String[] dateArr = date.split("-");  
        mc = new MakeCalendar(Integer.parseInt(dateArr[0]),Integer.parseInt(dateArr[1]));
        
        mc.load(filePath, fileName);
        
        mc.show();
        menu(user);
    }
    
    public void menu(User user) {
       while(true) {
           System.out.print("1.하루 일정 보기 2.일정 검색 3.뒤로가기 ");
           if(user.getId().equals("admin")) {
        	   System.out.println("4.모든 일정보기");
           }
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
          case "4":
              mc.daySchedule(0, user);
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
         //mc.search(obj, 0);
         searchAfter(obj,menu);
         break;
      case "2":
    	  System.out.print("검색할 일정의 작성자를 입력하세요 > ");
          obj = sc.nextLine();
          //mc.search(obj, 1);
          searchAfter(obj,menu);
         break;
      case "3":
    	  System.out.print("검색할 일정의 카테고리를 입력하세요 > ");
          obj = sc.nextLine();
          //mc.search(obj, 2);
          searchAfter(obj,menu);
         break;
      }
      
   }
    public void searchAfter(String str,String menu) {
        if(mc.search(str,menu)==0) {
          	 System.out.println("해당하는 일정이 없습니다.");
           	 mc.show();
           	 menu(user);
        }else {
        	System.out.println("1.일정 상세 보기 2.뒤로가기");
        	String afterMenu = sc.nextLine();
        	switch (afterMenu) {
    		case "1":
    			scheduleDetail();
    			break;
    		case "2":
    			mc.show();
    			break;
    		default:
    			searchAfter(str,menu);
    			break;
    		}
        }


    }
   public int[] showList() {
       System.out.println("일정을 볼 날짜를 입력하세요");
        System.out.print("> ");
        int selectedDay = Integer.parseInt(sc.nextLine());
        //역직렬화로 불러온다
        int status = mc.daySchedule(selectedDay,user);
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
           scheduleDetail();
           break;
        case "3":
           updateSchedule();
           break;   
        case "4":
           removeSchedule();
           break;
        case "5":
           mc.show();
           break;
        case "6":
        	rs.makeReport(mc);
        	break;
        case "7":
        	rs.loadReport();
        	break;

        }
        	
    }
   private void removeSchedule() {
      // TODO Auto-generated method stub
      System.out.println("삭제할 일정의 일정번호를 입력하세요 > ");
      int upNum = Integer.parseInt(sc.nextLine());
      Schedule sch= mc.search(upNum);
      if(sch!=null) {
         
         mc.remove(sch,filePath,fileName);
         }
      isSchedule(sch);
      }
   private void scheduleDetail() {
      // TODO Auto-generated method stub
      System.out.println("상세보기할 일정의 일정번호를 입력하세요 > ");
      int upNum = Integer.parseInt(sc.nextLine());
      Schedule sch= mc.search(upNum);
      if(sch!=null) {
            System.out.println("일정 번호\t일정 이름\t일정 작성자\t일정 권한\t일정설명");
//            System.out.print(sch.getNo()+"\t");
//            System.out.print(sch.getScheduleName()+"\t");
//            System.out.print(sch.getWriter()+"\t");
//            System.out.print(sch.getAuthority()+"\t");
//            System.out.print(sch.getContent()+"\n");
            System.out.println(sch);

         }
      isSchedule(sch);
      }
      
   
   private void updateSchedule() {
      System.out.print("수정할 일정의 일정번호를 입력하세요 > ");
      int upNum = Integer.parseInt(sc.nextLine());
      Schedule sch= mc.search(upNum);
      String str="";
      if(sch!=null) {
          System.out.println("수정할 일정을 입력하세요");
          System.out.print("일정이름 > ");
          str+=sc.nextLine()+",";
          System.out.print("작성자 > ");
          str+=sc.nextLine()+",";
          System.out.print("일정권한 y or n > ");
          str+=sc.nextLine()+",";
          System.out.print("설명 > ");
          str+=sc.nextLine()+",";
          System.out.print("기간 > ");
          str+=sc.nextLine()+",";
          System.out.print("알람여부 y or n > ");
          str+=sc.nextLine()+",";

          mc.update(sch,str,filePath,fileName);
         }
      isSchedule(sch);
      mc.daySchedule(sch.getStartDay(),user);
      }
   
   private void addSchedule(int selectedDay) {
       // 파일 생성 및 쓰기
//       String filePath = "C:\\Temp";
//       String fileName="Schedule Data Base.txt";
        System.out.println("추가할 일정을 입력하세요");
        System.out.print("일정이름 > ");
        String scheduleName=sc.nextLine();
        System.out.print("작성자 > ");
        String writer=sc.nextLine();
        System.out.print("일정권한 y or n> ");
        String aut=sc.nextLine();
        boolean authority;
        if(aut.equals("y")) {
        	authority=true;
         }else {
        	 authority=false;
         }
        System.out.print("설명 > ");
        String content=sc.nextLine();
        System.out.print("기간 > ");
        String period=sc.nextLine();
        System.out.print("알람여부 y or n > ");
        String al=sc.nextLine();
        Alarm alarm = new Alarm();
        if(al.equals("y")) {
           alarm.setStatus(true);
        }else {
           alarm.setStatus(false);
        }
        System.out.print("분류 > ");
        String dept=sc.nextLine();
        

        Schedule schedule = new Schedule(writer, scheduleName, Integer.parseInt(period), selectedDay, content, authority, alarm, dept);

        mc.add(schedule,filePath,fileName);
        System.out.println("일정 추가 완료"); 
        mc.show();
        menu(user);
   }
   
   public void isSchedule(Schedule schedule) {
      if(schedule==null) {
         System.out.println("해당하는 일정이 없습니다");
         mc.show();
         menu(user);
      }else {
         System.out.println("완료");
         mc.show();
         menu(user);
      }
   }

   public void scopeSelection() {
	   
   }
   


}

