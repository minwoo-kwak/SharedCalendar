package kosa.sharedcalendar.report;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import kosa.sharedcalendar.calendar.MakeCalendar;
import kosa.sharedcalendar.calendar.Schedule;
import kosa.sharedcalendar.filesystem.FileSystem;
import kosa.sharedcalendar.user.UserManager;

public class ReportSystem {
	List<Report> reportList = new ArrayList<Report>();
	Scanner scanner = new Scanner(System.in);

	public ReportSystem() {
	}

	// 리포트 생성
	public void makeReport(MakeCalendar mc) {
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

		StringBuilder sb = new StringBuilder();
		// 지난 일정
		sb.append("===== Past Schedules =====\n");
		for (Schedule schedule : pastSchedules) {
			sb.append("일정 번호: " + schedule.getNo() + "\n");
			sb.append("일정 이름: " + schedule.getScheduleName() + "\n");
			sb.append("작성자: " + schedule.getWriter() + "\n\n\n");
		}
		// 앞으로의 일정
		sb.append("===== Upcoming Schedules =====");
		for (Schedule schedule : upcomingSchedules) {
			sb.append("일정 번호: " + schedule.getNo() + "\n");
			sb.append("일정 이름: " + schedule.getScheduleName() + "\n");
			sb.append("작성자: " + schedule.getWriter() + "\n\n\n");
		}
		FileSystem fs = new FileSystem();
		if (fs.writeFile(filePath, fileName, sb)) {
			// Report 객체 생성 및 reportList에 추가
			Report report = new Report();
			System.out.print("리포트 이름을 입력해주세요: ");
			report.setName(scanner.nextLine()); // 리포트 이름 설정
			report.setWriter(UserManager.getLoginUser().getName()); // 작성자 설정
			report.setPeriod(timeStamp); // 기간 설정
			report.setDept(UserManager.getLoginUser().getDept()); // 부서 설정
			report.setContents(sb.toString()); // 보고서 내용 삽입
			reportList.add(report); // 보고서 목록에 보고서 추가
			
			// 보고서 리스트 DB 저장
			FileSystem.save(filePath, "reportList", reportList);
			System.out.println("리포트가 성공적으로 생성되었습니다.");

		} else {
			System.out.println("파일을 저장하는 도중에 오류가 발생했습니다.");
		}

	}
	
	// 저장된 보고서 목록 출력 및 상세보기
	public void loadReport() {
	    String filePath = "C:\\Temp";
	    reportList = (List<Report>) FileSystem.load(filePath, "reportList");

	    if (reportList != null && !reportList.isEmpty()) {
	        System.out.println("==== Report List ====");
	        for (int i = 0; i < reportList.size(); i++) {
	            Report report = reportList.get(i);
	            System.out.println((i + 1) + ". " + report.getName());
	        }

	        // 사용자에게 어떤 report를 볼지 물어보기
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("보고 싶은 리포트의 번호를 입력하세요: ");
	        int choice = scanner.nextInt();

	        if (choice > 0 && choice <= reportList.size()) {
	            Report selectedReport = reportList.get(choice - 1);
	            System.out.println("==== Selected Report ====");
	            System.out.println("리포트 이름: " + selectedReport.getName());
	            System.out.println("작성자: " + selectedReport.getWriter());
	            System.out.println("기간: " + selectedReport.getPeriod());
	            System.out.println("부서: " + selectedReport.getDept());
	            System.out.println("추가 정보: " + selectedReport.getContents());
	        } else {
	            System.out.println("유효하지 않은 선택입니다.");
	        }
	    } else {
	        System.out.println("리포트가 존재하지 않습니다.");
	    }
	}
}
