import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager { // 로그인, 유저정보 메소드 모음
   Scanner sc = new Scanner(System.in);
   Company cp = new Company();
   List<User> userList = Company.getUserList();
   List<Admin> adminList = Company.getAdminList();
   private static User loginUser = null;
   private static Admin loginAdmin = null;
   private String id;
   private String pw;
   File userFile = new File("C:\\user\\userDB.txt");

   public void init() { // 초기화면
      System.out.print("[1] 일반사원 로그인      [2]관리자 로그인      [0]Exit \n" + ">> ");
      String menu = sc.nextLine();
      while (true) {
         switch (menu) {
         case "1":
            userLogin();
            break;
         case "2":
            adminLogin();
            break;
         case "0":
            System.exit(0);
            break;

         default:
            System.out.println("다시 입력하세요.");
            init();
         }
      }
   }

   public void adminLogin() {
      while (true) {
         System.out.println("====관리자 로그인====");
         System.out.print("아이디를 입력하세요: ");
         id = sc.nextLine();
         System.out.print("비밀번호를 입력하세요: ");
         pw = sc.nextLine();

         if (findByAdminId(id) == null) {
            System.out.println("존재하지 않는 아이디입니다.");
            continue;
         } else {
            if (findByAdminId(id).getPw().equals(pw)) {
               loginAdmin = findByAdminId(id);
               System.out.println("관리자 " + loginAdmin.getName() + "님 환영합니다.");
               adminInfo();
               break;
            }
            System.out.println("비밀번호가 맞지 않습니다.");
            continue;
         }
      }
   }

   public void userLogin() { // 일반사원 로그인메소드
      userList = Company.getUserList();

      while (true) {
         System.out.println("====일반 유저 로그인====");
         System.out.print("아이디를 입력하세요: ");
         id = sc.nextLine();
         System.out.print("비밀번호를 입력하세요: ");
         pw = sc.nextLine();

         if (findByUserId(id) == null) {
            System.out.println("존재하지 않는 아이디입니다.");
            // continue;
         } else {
            if (findByUserId(id).getPw().equals(pw)) {
               loginUser = findByUserId(id);
               System.out.println(loginUser.getName() + "님 환영합니다.");
               myInfo();
               break;
            }
            System.out.println("로그인 실패 다시 시도하세요!");
            continue;
         }
      }

   }

   public void adminInfo() {

      System.out.println();
      boolean loop = true;
      while (true) {
         System.out.print("1. 사원등록 2. 사원삭제 3. 사원목록보기 4. 일정달력보기 5. 로그아웃 \r =>");
         int select = sc.nextInt();
         switch (select) {
         case 1:
            cp.addUser2();

            break;
         case 2:
            System.out.println("====사원목록보기====");
            for (User u : userList) {
               System.out.println("ID: " + u.getId() + "   Passwd : " + u.getPw() + "   이름: " + u.getName()
                     + "   직책: " + u.getPosition() + "   부서: " + u.getDept() + "   폰번호: " + u.getPhone() + "   이메일: "
                     + u.getEmail() + "   입사일 : " + u.getHireDate());
            }

            cp.delUser();
            break;
         case 3:
            System.out.println("====사원목록보기====");
            for (User u : userList) {
               System.out.println("ID: " + u.getId() + "   Passwd : " + u.getPw() + "   이름: " + u.getName()
                     + "   직책: " + u.getPosition() + "   부서: " + u.getDept() + "   폰번호: " + u.getPhone() + "   이메일: "
                     + u.getEmail() + "   입사일 : " + u.getHireDate());
            }

            break;
         case 4:
            CalendarSystem sys = new CalendarSystem();
            sys.init(loginUser);

            break;
         case 5:
            System.out.println("로그아웃");
            adminLogout();
            init();
            break;

         default:
            break;
         }
      }
   }

   public void myInfo() { // 일반사원 로그인한 후 화면
      System.out.println();
      boolean loop = true;
      while (true) {
         System.out.print("1. 내정보보기 2. 로그아웃 3. 일정달력보기");
         int select = Integer.parseInt(sc.nextLine());
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
//         case 2:
//            System.out.println("내정보수정");
//            while(true) {
//                System.out.println("1. 직책 2. 부서 3. 휴대폰번호 4. 이메일 5. 뒤로가기" );
//                 int key = sc.nextInt();
//                 switch (key) {
//                 case 1:
//                    System.out.print("어떤 직책으로 변경하시겠습니까?  ");
//                    String position = sc.nextLine();
//                    loginUser.setPosition(position);
//                    userList = Company.getUserList();
////                    FileSystem.save("C:\\user", "userDB", userList);
//                    try {
//                       //file  = new File("C:\\user\\userDB.txt");
//                        FileWriter fw = new FileWriter(userFile,false);
//                       
//                        for(int i=0; i<userList.size(); i++) {
//                           fw.append(userList.get(i).getId() + "\t");
//                           fw.append(userList.get(i).getPw() + "\t");
//                           fw.append(userList.get(i).getName()+ "\t");
//                           fw.append(userList.get(i).getPosition()+ "\t");
//                           fw.append(userList.get(i).getDept()+ "\t");
//                           fw.append(userList.get(i).getPhone()+ "\t");
//                          fw.append(userList.get(i).getEmail()+ "\t");
//                          fw.append(userList.get(i).getHireDate()+ "\r");
//                        }
//                        fw.flush();
//                        } catch (Exception e) {
//                    // TODO: handle exception
//                 }
//                    
//                    System.out.println("바뀐 직책은 " + loginUser.getPosition());
//                    
//                    break;
//                 case 2:
//                    System.out.print("어떤 부서로 이동하시겠습니까?  ");
//                    String dept = sc.nextLine();
//                    loginUser.setDept(dept);
//                    try {
//                       //file  = new File("C:\\user\\userDB.txt");
//                        FileWriter fw = new FileWriter(userFile,false);
//                       
//                        for(int i=0; i<userList.size(); i++) {
//                           fw.append(userList.get(i).getId() + "\t");
//                           fw.append(userList.get(i).getPw() + "\t");
//                           fw.append(userList.get(i).getName()+ "\t");
//                           fw.append(userList.get(i).getPosition()+ "\t");
//                           fw.append(userList.get(i).getDept()+ "\t");
//                           fw.append(userList.get(i).getPhone()+ "\t");
//                           fw.append(userList.get(i).getEmail()+ "\t");
//                          fw.append(userList.get(i).getHireDate()+ "\r");
//                        }
//                        fw.flush();
//                        } catch (Exception e) {
//                    // TODO: handle exception
//                 }
//                    System.out.println("바뀐 부서는 " + loginUser.getDept());
//                    break;
//                 case 3:
//                    System.out.print("어떤 번호로 변경하시겠습니까?  ");
//                    String phone = sc.nextLine();
//                    loginUser.setPhone(콜);
//                    try {
//                       //file  = new File("C:\\user\\userDB.txt");
//                        FileWriter fw = new FileWriter(userFile,false);
//                       
//                        for(int i=0; i<userList.size(); i++) {
//                           fw.append(userList.get(i).getId() + "\t");
//                           fw.append(userList.get(i).getPw() + "\t");
//                           fw.append(userList.get(i).getName()+ "\t");
//                           fw.append(userList.get(i).getPosition()+ "\t");
//                           fw.append(userList.get(i).getDept()+ "\t");
//                           fw.append(userList.get(i).getPhone()+ "\t");
//                           fw.append(userList.get(i).getEmail()+ "\t");
//                          fw.append(userList.get(i).getHireDate()+ "\r");
//                        }
//                        fw.flush();
//                        } catch (Exception e) {
//                    // TODO: handle exception
//                 }
//                    System.out.println("바뀐 휴대폰번호는 " + loginUser.getPhone());
//                    break;
//                 case 4:
//                    System.out.print("어떤 이메일로 변경하시겠습니까?  ");
//                    String email = sc.nextLine();
//                    loginUser.setEmail(email);
//                    try {
//                       userFile  = new File("C:\\user\\userDB.txt");
//                        FileWriter fw = new FileWriter(userFile,false);
//                       
//                        for(int i=0; i<userList.size(); i++) {
//                           fw.append(userList.get(i).getId() + "\t");
//                           fw.append(userList.get(i).getPw() + "\t");
//                           fw.append(userList.get(i).getName()+ "\t");
//                           fw.append(userList.get(i).getPosition()+ "\t");
//                           fw.append(userList.get(i).getDept()+ "\t");
//                           fw.append(userList.get(i).getPhone()+ "\t");
//                           fw.append(userList.get(i).getEmail()+ "t");
//                          fw.append(userList.get(i).getHireDate()+ "\r");
//                        }
//                        fw.flush();
//                        } catch (Exception e) {
//                    // TODO: handle exception
//                 }
//                    System.out.println("바뀐 이메일은 " + loginUser.getEmail());
//                    break;
//                 case 5:
//                   myInfo();
//
//                 default:
//                    break;
//                 }
//               
//            }

         case 2:
            System.out.println("로그아웃");
            userLogout();
            init();

            // System.exit(0);
         case 3:
            CalendarSystem sys = new CalendarSystem();
            sys.init(loginUser);
            break;

         default:
            break;
         }
      }
   }

//   public void changeUserInfo(String name) {
//      
//   }
   public void userLogout() { // 일반사원 로그아웃
      if (loginUser != null) {
         loginUser = null;
         System.out.println("로그아웃 되었습니다.  \n");
         init();
      }
   }

   public void adminLogout() { // 관리자 로그아웃
      if (loginAdmin != null) {
         loginAdmin = null;
         System.out.println("로그아웃 되었습니다.  \n");
         init();
      }
   }

   public User findByUserId(String id) { // 입력 아이디가 리스트에 있는지 비교
      for (User u : userList) {
         if (u.getId().equals(id)) {
            return u;
         }
      }
      return null;
   }

   public Admin findByAdminId(String id) { // 입력 아이디가 리스트에 있는지 비교
      for (Admin a : adminList) {
         if (a.getId().equals(id)) {
            return a;
         }
      }
      return null;
   }

   public void changeUserInfo(String name) { // 관리자가 사원정보변경
      for (User u : userList) {
         if (u.getName().equals(name)) {
            userList.remove(u);
            try {
               // file = new File("C:\\user\\userDB.txt");
               FileWriter fw = new FileWriter(userFile, false);

               for (int i = 0; i < userList.size(); i++) {
                  fw.append(userList.get(i).getId() + "\t");
                  fw.append(userList.get(i).getPw() + "\t");
                  fw.append(userList.get(i).getName() + "\t");
                  fw.append(userList.get(i).getPosition() + "\t");
                  fw.append(userList.get(i).getDept() + "\t");
                  fw.append(userList.get(i).getPhone() + "\t");
                  fw.append(userList.get(i).getEmail() + "\t");
                  fw.append(userList.get(i).getHireDate() + "\r");
               }
               fw.flush();
               break;
            } catch (Exception e) {
               // TODO: handle exception
            }
         }
      }

   }

   public void delUser() { // 관리자가 퇴사사원 유저목록에서 삭제(사번값으로)
      System.out.print("삭제할 사원의 사번을 입력하세요 : ");
      String id = sc.nextLine();
      for (User u : userList) {
         if (u.getId().equals(id)) {
            userList.remove(u);
            try {
               FileWriter fw = new FileWriter(userFile, false);

               for (int i = 0; i < userList.size(); i++) {
                  fw.append(userList.get(i).getId() + "\t");
                  fw.append(userList.get(i).getPw() + "\t");
                  fw.append(userList.get(i).getName() + "\t");
                  fw.append(userList.get(i).getPosition() + "\t");
                  fw.append(userList.get(i).getDept() + "\t");
                  fw.append(userList.get(i).getPhone() + "\t");
                  fw.append(userList.get(i).getEmail() + "\t");
                  fw.append(userList.get(i).getHireDate() + "\r");
               }
               fw.flush();
            } catch (Exception e) {
               // TODO: handle exception
            }
            System.out.println("삭제완료");
         }

      }
   }

   public List<User> getUserList() {
      return userList;
   }

   public void setUserList(List<User> userList) {
      this.userList = userList;
   }

   public static User getLoginUser() {
      return loginUser;
   }

   public static void setLoginUser(User loginUser) {
      UserManager.loginUser = loginUser;
   }
   
   
}