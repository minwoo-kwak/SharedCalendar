import java.util.Scanner;

public class CalendarSystem {
	
    Scanner sc = new Scanner(System.in);
    MakeCalendar mc;
    
    public void init() {
    	System.out.println("날짜를 입력하세요, 종료:q");
        System.out.println("형식 : yyyy-MM");
        System.out.print("> ");
        String date = sc.nextLine();
        String[] dateArr = date.split("-");  
        mc = new MakeCalendar(Integer.parseInt(dateArr[0]),Integer.parseInt(dateArr[1]));
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
    			//search();
    			break;
    		case "3":
    			mc.show();
    			break;

    		default:
    			break;
    		}
    	}
    }
    public int[] showList() {
    	System.out.println("일정을 볼 날짜를 입력하세요");
        System.out.print("> ");
        int selectedDay = Integer.parseInt(sc.nextLine());
        int status = mc.daySchedule(selectedDay);
        int[] info ={selectedDay,status};
        return info;
        
        
    }
    public void scheduleManage(int status, int selectedDay) {
    	if(status==0) {
            System.out.println("해당일자에 일정이 없습니다.");
            System.out.println("1.일정 추가");
         }else{
            System.out.println("1.일정 추가 2.일정 수정 3.일정 삭제 4.뒤로가기");
         }
    	String menu = sc.nextLine();
        switch (menu) {
        case "1":
        	addSchedule(selectedDay);
           break;
        case "2":
        	updateSchedule(selectedDay);
           break;   
        case "3":
        	removeSchedule(selectedDay);
           break;
        case "4":
        	mc.show();
           break;
        }
    }
	private void updateSchedule(int selectedDay) {
		System.out.print("수정할 일정번호를 입력하세요 > ");
		int upNum = Integer.parseInt(sc.nextLine());
		//mc.daySchedule(selectedDay);
		for(Schedule sch : mc.getScheduleList()) {
			if(sch.getNo()==upNum) {
		        System.out.println("수정할 일정을 입력하세요");
		        System.out.print("일정이름 > ");
		        sch.setScheduleName(sc.nextLine());
		
		        System.out.print("작성자 > ");
		        sch.setWriter(sc.nextLine());
		        String writer=sc.nextLine();
		        System.out.print("일정권한 > ");
		        sch.setAuthority(sc.nextLine());
		        String authority=sc.nextLine();
		        System.out.print("설명 > ");
		        sch.setContent(sc.nextLine());
		        String content=sc.nextLine();
		        System.out.print("기간 > ");
		        sch.setPeriod(Integer.parseInt(sc.nextLine()));
		        String period=sc.nextLine();
		        System.out.print("알람여부 y or n > ");
		        sch.
		        if(al.equals("y")) {
			           alarm.setStatus(true);
			        }else {
			           alarm.setStatus(false);
			        }
		        String al=sc.nextLine();
		        System.out.print("카테고리 > ");
		        String category=sc.nextLine();
		        Alarm alarm = new Alarm();
		        
			}
		}
		
	}
	private void addSchedule(int selectedDay) {
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
        
        mc.add(schedule);
        System.out.println("일정 추가 완료");
        mc.show();
	}
    

}


