package kosa.sharedcalendar.vacation;
import java.util.ArrayList;
import java.util.List;

import kosa.sharedcalendar.user.User;
import kosa.sharedcalendar.user.UserManager;

public class VacationSystem {

	public VacationSystem() {
	}

	// 유저들의 연차 정보 불러오고 출력
	public void printVacationList(UserManager userManager) {
		
        for (User user : userManager.getUserList()) {
        	System.out.printf("%s %s %s %s", user.getId() + user.getName() 
        	+ user.getPosition() + user.getDept());
			user.getVacation().leaveCal(user);
        }
    }
	
	// 유저들의 연차 정보 수정
	
}
