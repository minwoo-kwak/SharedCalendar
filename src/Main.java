
import java.util.Scanner;
import java.util.regex.Pattern;

//import kosa.project.UserManager;

public class Main {
	static int a = 0;

	public static void main(String[] args) {
//	   UserDatabase userDb = new UserDatabase();
//	   
//	   

	   //User user = new User();

//	   
//	  userDb.addUser(user);
//       
//       User user2 = userDb.getUser("john.doe");
//       if (user != null) {
//           System.out.println("ID: " + user.getId());
//           System.out.println("Name: " + user.getName());
//           System.out.println("Position: " + user.getPosition());
//           System.out.println("Department: " + user.getDept());
//           System.out.println("Phone: " + user.getPhone());
//           System.out.println("Email: " + user.getEmail());
//       } else {
//           System.out.println("유저를 찾을 수 없습니다.");
//       }
      // TODO Auto-generated method stub
	   Company cp = new Company();
	   UserManager um = new UserManager();
	   cp.insertUser();
	   um.init();
//	   CalendarSystem sys = new CalendarSystem();
//	   sys.init(user);
   }
}