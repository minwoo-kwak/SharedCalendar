
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager { //로그인, 유저정보 메소드 모음
   Scanner sc = new Scanner(System.in); 
   List<User>userList = new ArrayList<User>();
   private static User loginUser = null;
   private String id;
   private String pw;
   
   public void init() { //초기화면
        System.out.print(
                "[1] Login [0]Exit \n" +
                ">> ");
        String menu = sc.nextLine();

        if (menu.equals("1")) login();
        else if (menu.equals("0")) System.exit(0);
    }
   
   
   public void login() { // 로그인메소드
      userList.add(new User("1234","1234","나병희","사원","개발팀","010-8747-7304","nbh7301@naver.com"));
      userList.add(new User("1111","1111","너병희","주임","인사팀","010-8747-7304","nbh7301@naver.com"));
      
      while(true) {
      System.out.println("로그인");
      System.out.print("아이디를 입력하세요: ");
      id = sc.next();
      System.out.print("비밀번호를 입력하세요: ");
      pw = sc.next();
      
      if(findById(id) == null) {
         System.out.println("존재하지 않는 아이디입니다.");
         continue;
      }else {
         if(findById(id).getPw().equals(pw)) {
            loginUser = findById(id);
                System.out.println(loginUser.getName()+ "님 환영합니다.");
                
                break;
         }
         System.out.println("로그인 실패 다시 시도하세요!");
         continue;
      }
         
      }
      myInfo();
      
   }
   
   public void myInfo() { //로그인한 후 화면
      System.out.println();
      while(true) {
         System.out.print("1. 내정보보기 2. 내정보수정 3. 로그아웃 4. 일정달력보기");
         int select = sc.nextInt();
         switch (select) {
         case 1:
            System.out.println("내정보보기");
            System.out.println("id는  " + loginUser.getId());
            System.out.println("이름은  " + loginUser.getName());
            System.out.println("직책은  " + loginUser.getPosition());
            System.out.println("부서는  " + loginUser.getDept());
            System.out.println("휴대폰번호는  " + loginUser.getPhone());
            System.out.println("이메일은  " + loginUser.getEmail());
            System.out.println("입사일  " + loginUser.getHireDate());
            
            break;
         case 2:
            System.out.println("내정보수정");
            System.out.println("1. 직책 2. 부서 3. 휴대폰번호 4. 이메일: ");
            int key = sc.nextInt();
            switch (key) {
            case 1:
               System.out.print("어떤 직책으로 변경하시겠습니까?  ");
               String position = sc.next();
               loginUser.setPosition(position);
               System.out.println("바뀐 직책은 " + loginUser.getPosition());
               break;
            case 2:
               System.out.print("어떤 부서로 이동하시겠습니까?  ");
               String dept = sc.next();
               loginUser.setDept(dept);
               System.out.println("바뀐 부서는 " + loginUser.getDept());
               break;
            case 3:
               System.out.print("어떤 번호로 변경하시겠습니까?  ");
               String phone = sc.next();
               loginUser.setPhone(phone);
               System.out.println("바뀐 휴대폰번호는 " + loginUser.getPhone());
               break;
            case 4:
               System.out.print("어떤 이메일로 변경하시겠습니까?  ");
               String email = sc.next();
               loginUser.setEmail(email);
               System.out.println("바뀐 이메일은 " + loginUser.getEmail());
               break;

            default:
               break;
            }
         case 3:
            System.out.println("로그아웃");
            logout();
            login();
            
            //System.exit(0);
         case 4:
        	 CalendarSystem sys = new CalendarSystem();
             sys.init();
             break;
            

         default:
            break;
         }
      }
   }
    public void logout() { //로그아웃
           if (loginUser != null) {
              loginUser = null;
              System.out.println("로그아웃 되었습니다.  \n");
           }
       }
   
   public User findById(String id) { //입력 아이디가 리스트에 있는지 비교
        for (User u : userList) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
   }
   
}