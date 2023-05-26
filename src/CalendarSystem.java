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
    UserManager um = new UserManager();
    String filePath = "C:\\Temp";
    String fileName="Schedule Data Base.txt";
    User user;

    Calendar gc;

    ReportSystem rs = new ReportSystem();

    public void init(User user) {

    	this.user=user;
        System.out.println("날짜를 입력하세요, 뒤로가기:q");
        System.out.println("형식 : yyyy-MM");
        System.out.print(" ☞ ");
        String date = sc.nextLine();
        if(date.matches("(\\d{4}-\\d{2})|q")) {
        	if(date.equals("q")) um.init();
            String[] dateArr = date.split("-");  
            mc = new MakeCalendar(Integer.parseInt(dateArr[0]),Integer.parseInt(dateArr[1]));
            mc.load(filePath, fileName);
            gc=mc.show(gc);
            menu(user);
        }else {
        	System.out.println("형식 오류");
        	System.out.println();
        	init(user);
        	return;
        }
    }

    
    public void menu(User user) {
       while(true) {
       		System.out.println("이전\t\t\t\t\t\t다음");
       		System.out.println("<\t\t\t\t\t\t>");
           System.out.print("1.하루 일정 보기 2.일정 검색 3.뒤로가기 ");
           if(user.getId().equals("admin")) {
        	   System.out.print("4.모든 일정보기");
           }
            System.out.print("\n ☞ ");
            String menu = sc.nextLine();
            if(!menu.matches("[1-4<>]")) {
            	System.out.println("입력 오류");
            	menu(user);
            }
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
             um.myInfo();
             break;
          case "4":
              mc.daySchedule(0,0, user);
              scheduleManage(1, 1);
              break;
          case "<":
        	  mc.setMonth(mc.getMonth()-1);
              gc.roll(Calendar.MONTH, -1);
              mc.show(gc);
              break;
          case ">":
        	  mc.setMonth(mc.getMonth()+1);
        	  gc.roll(Calendar.MONTH, +1);
              mc.show(gc);
              break;


          default:
             continue;
          }
       }
    }
    private void searchSchedule() {
      // TODO Auto-generated method stub
    	String str;
       System.out.println("찾을 방법을 입력하세요 1.일정번호 2.작성자 3.부서 ");
       System.out.print(" ☞ ");
       String menu = sc.nextLine();
       menu=onlyNum(menu,3);
       if(menu==null) {
       	System.out.println("입력 오류");
       	searchSchedule();
       	return;
       }
       switch (menu) {
      case "1":
         System.out.print("검색할 일정의 일정번호를 입력하세요  ");
         System.out.print(" ☞ ");
         str = sc.nextLine();
         //mc.search(obj, 0);
         searchAfter(str,menu);
         break;
      case "2":
    	  System.out.print("검색할 일정의 작성자를 입력하세요");
    	  System.out.print(" ☞ ");
          str = sc.nextLine();
          //mc.search(obj, 1);
          searchAfter(str,menu);
         break;
      case "3":
    	  System.out.print("검색할 일정의 카테고리를 입력하세요");
    	  System.out.print(" ☞ ");
          str = sc.nextLine();
          //mc.search(obj, 2);
          searchAfter(str,menu);
         break;
      }
      
   }
    public void searchAfter(String str,String menu) {
        if(mc.search(str,menu)==0) {
          	 System.out.println("해당하는 일정이 없습니다.");

           	 mc.show(gc);

           	 menu(user);
        }else {
        	System.out.println("1.일정 상세 보기 2.뒤로가기");
        	System.out.print(" ☞ ");
        	String afterMenu = sc.nextLine();
        	afterMenu=onlyNum(afterMenu,2);
            if(afterMenu==null) {
            	System.out.println("입력 오류 ");
            	searchAfter(str, menu);
            	return;
            }
        	switch (afterMenu) {
    		case "1":
    			scheduleDetail();
    			break;
    		case "2":
    			mc.show(gc);
    			break;
    		default:
    			searchAfter(str,menu);
    			break;
    		}
        }


    }
   public int[] showList() {
       System.out.println("일정을 볼 날짜를 입력하세요");
       System.out.print(" ☞ ");
       String str=sc.nextLine();
//       str=onlyNum(str,31);
//       if(str==null) {
//       	System.out.println("입력 오류");
//       	showList();
//       }
       int selectedDay=Integer.parseInt(str);
        
        int status = mc.daySchedule(mc.getMonth(),selectedDay,user);
        int[] info ={selectedDay,status};
        return info;
        
        
    }
    public void scheduleManage(int status, int selectedDay) {
    	while(true) {
	       if(status==0) {
	            System.out.println("해당일자에 일정이 없습니다.");
	            System.out.println("1.일정 추가");
	         }else{
	            System.out.println("1.일정 추가 2.일정 상세 보기  3.일정 수정 4.일정 삭제 5.뒤로가기 6.리포트저장 7.리포트 불러오기");
	         }
	       System.out.print(" ☞ ");
	       String menu = sc.nextLine();
	       menu=onlyNum(menu,7);
	       if(menu==null) {
	       	System.out.println("입력 오류");
	       	scheduleManage(status, selectedDay);
	       	//return;
	       }
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
	           mc.show(gc);
	           return;
	        case "6":
	        	rs.makeReport(mc);
	        	break;
	        case "7":
	        	rs.loadReport();
	        	break;
	        }
    	}

        	
    }
   private void removeSchedule() {
      // TODO Auto-generated method stub
      System.out.println("삭제할 일정의 일정번호를 입력하세요");
      System.out.print(" ☞ ");
      String str=sc.nextLine();
      str=onlyNum(str,31);
      if(str==null) {
      	System.out.println("입력 오류");
      	removeSchedule();
      }
      int upNum=Integer.parseInt(str);

      Schedule sch= mc.search(upNum);
      if(sch!=null) {
         
         mc.remove(sch,filePath,fileName);
         }
      isSchedule(sch);
      }
   private void scheduleDetail() {
      // TODO Auto-generated method stub
      System.out.println("상세보기할 일정의 일정번호를 입력하세요");
      System.out.print(" ☞ ");
      String str=sc.nextLine();
      str=onlyNum(str,31);
      if(str==null) {
      	System.out.println("입력 오류");
      	scheduleDetail();
      }
      int upNum=Integer.parseInt(str);
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
      System.out.print("수정할 일정의 일정번호를 입력하세요");
      System.out.print(" ☞ ");
      String str=sc.nextLine();
      str=onlyNum(str,31);
      if(str==null) {
      	System.out.println("입력 오류");
      	updateSchedule();
      }
      int upNum=Integer.parseInt(str);
      Schedule sch= mc.search(upNum);
      String str1="";
      String input;
      if(sch!=null) {
          System.out.println("수정할 일정을 입력하세요");
          System.out.println("일정이름");
          System.out.print(" ☞ ");
          str1+=sc.nextLine()+",";
          System.out.println("일정권한 y or n");
          System.out.print(" ☞ ");
          input=sc.nextLine();
          if(input.matches("[yn]")) {
        	  str1+=input+",";
          }else {
        	  System.out.println("형식 오류");
        	  updateSchedule();
          }
          System.out.println("설명");
          System.out.print(" ☞ ");
          str1+=sc.nextLine()+",";
          System.out.println("기간");
          System.out.print(" ☞ ");
          str1+=sc.nextLine()+",";
          System.out.println("알람여부 y or n");
          System.out.print(" ☞ ");
          input=sc.nextLine();
          if(input.matches("[yn]")) {
        	  str1+=input+",";
          }else {
        	  System.out.println("형식 오류");
        	  updateSchedule();
          }

          mc.update(sch,str1,filePath,fileName);
         }
      isSchedule(sch);
      mc.daySchedule(mc.getMonth(),sch.getStartDay(),user);
      }
   
   private void addSchedule(int selectedDay) {
	   String input;
        System.out.println("추가할 일정을 입력하세요");
        System.out.println("일정이름");
        System.out.print(" ☞ ");
        String scheduleName=sc.nextLine();
        String writer=user.getId();
        System.out.println("일정권한 y or n");
        System.out.print(" ☞ ");
        input=sc.nextLine();
        String aut="";
        if(input.matches("[yn]")) {
      	   aut=input;
        }else {
      	  System.out.println("형식 오류");
      	  addSchedule(selectedDay);
        }
        boolean authority;
        if(aut.equals("y")) {
        	authority=true;
         }else {
        	 authority=false;
         }
        System.out.println("설명");
        System.out.print(" ☞ ");
        String content=sc.nextLine();
        System.out.println("기간");
        System.out.print(" ☞ ");
        String period=sc.nextLine();
        System.out.println("알람여부 y or n");
        System.out.print(" ☞ ");
        input=sc.nextLine();
        String al="";
        if(input.matches("[yn]")) {
      	   al=input;
        }else {
      	  System.out.println("형식 오류");
      	  addSchedule(selectedDay);
        }
        Alarm alarm = new Alarm();
        if(al.equals("y")) {
           alarm.setStatus(true);
        }else {
           alarm.setStatus(false);
        }

//        System.out.print("분류 > ");
//        String dept=sc.nextLine();
        
        Schedule schedule = new Schedule(writer, scheduleName, Integer.parseInt(period), selectedDay, content, authority, alarm, user.getDept(), mc.getMonth());
        mc.add(schedule,filePath,fileName);
        System.out.println("일정 추가 완료"); 
        mc.show(gc);

        menu(user);
   }
   
   public void isSchedule(Schedule schedule) {
      if(schedule==null) {
         System.out.println("해당하는 일정이 없습니다");

         mc.show(gc);
         menu(user);
      }else {
         System.out.println("완료");
         mc.show(gc);
         menu(user);
      }
   }
   public String onlyNum(String input,int num) {
	   if(input.matches("[1-"+num+"]")){
		   return input;
	   }else {
		   return null;
	   }
	   
   }
}

