import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Company {
   private String name;
   private int userCount;
   private int revenue;
   List<Dept> deptList = new ArrayList<Dept>();
   List<Notice> noticeList = new ArrayList<Notice>();
    static List<User>userList = new ArrayList<User>();
    static List<Admin>adminList = new ArrayList<Admin>();
    File userFile = new File("C:\\user\\userDB.txt");
    File adminFile  = new File("C:\\user\\admin.txt");
    Scanner  sc = new Scanner(System.in);
    
   // Constructors
   public Company() {
      new ArrayList<Dept>();
      insertUser();
      insertAdmin();
   }
   public Company(String name, int userCount, int revenue) {
      super();
      this.name = name;
      this.userCount = userCount;
      this.revenue = revenue;
   }
   
   public Company(String name, int userCount, int revenue, List<Dept> deptList) {
      super();
      this.name = name;
      this.userCount = userCount;
      this.revenue = revenue;
      this.deptList = deptList;
   }
   // 부서 추가
   public void addDept(Dept dept) {
      deptList.add(dept); 
   }
   
   // 유저 추가
//   public void addUser(User user, Dept dept) {
//      // 유저의 부서가 이미 존재하면
//      if(deptList.contains(dept)) {
//         dept.addUser(user);
//          //file  = new File("C:\\user\\userDB.txt");
//          try {
//            FileWriter filewriter = new FileWriter(userFile, true);
//            if(userFile.isFile() && userFile.canWrite()) {
//               //System.out.print("id: ");
//               filewriter.append(sc.nextLine());
//               filewriter.append("\t");
//               //System.out.print("pw: ");
//               filewriter.append(sc.nextLine());
//               filewriter.append("\r");
//               System.out.println("완료");
//            }
//            filewriter.close();
//         } catch (Exception e) {
//            // TODO: handle exception
//         }
//      } // 유저의 부서가 존재하지 않으면 
//      else {
//         addDept(dept); // 부서를 새로 생성
//         dept.addUser(user);
//      }
//      userCount++;
//   }
   //관리자가 유저 추가
   public void addUser2() {
      // 유저의 부서가 이미 존재하면
      System.out.print("사번입력: ");
      String id = sc.nextLine();
      System.out.print("비밀번호입력: ");
      String pw = sc.nextLine();
      System.out.print("이름입력: ");
      String name = sc.nextLine();
      System.out.print("직책입력: ");
      String position = sc.nextLine();
      System.out.print("부서입력: ");
      String dept = sc.nextLine();
      System.out.print("휴대폰번호입력: ");
      String phone = sc.nextLine();
      System.out.print("이메일입력: ");
      String email = sc.nextLine();
      System.out.print("입사일입력: ");
      LocalDate hireDate = LocalDate.parse(sc.nextLine());
      
      userList.add(new User(id, pw, name, position, dept, phone, email, hireDate));
      //file  = new File("C:\\user\\userDB.txt");
       try {
         FileWriter filewriter = new FileWriter(userFile, true);
         if(userFile.isFile() && userFile.canWrite()) {
            
            filewriter.append(id);
            filewriter.append("\t");
            filewriter.append(pw);
            filewriter.append("\t");
            filewriter.append(name);
            filewriter.append("\t");
            filewriter.append(position);
            filewriter.append("\t");
            filewriter.append(dept);
            filewriter.append("\t");
            filewriter.append(phone);
            filewriter.append("\t");
            filewriter.append(email);
            filewriter.append("\t");
            filewriter.append(hireDate.toString());
            filewriter.append("\r");
            
            System.out.println("완료");
         }
         filewriter.close();
      } catch (Exception e) {
         // TODO: handle exception
      }
      userCount++;
   }
   //텍스트파일에서 유저목록 객체로생성
   public void insertUser() {
      
      String line = "";
      try {
         FileReader filereader = new FileReader(userFile);
         BufferedReader bufReader = new BufferedReader(filereader);
         while((line = bufReader.readLine()) != null) {
            //System.out.println(line);
            String arr[] = line.split("\t");
            String id = arr[0];
            String pw = arr[1];
            String name = arr[2];
            String position = arr[3];
            String dept = arr[4];
            String phone = arr[5];
            String email = arr[6];
            LocalDate hireDate = LocalDate.parse(arr[7]);
            userCount++;
            userList.add(new User(id, pw, name, position, dept, phone, email, hireDate));
         }
         } catch (Exception e) {
            // TODO: handle exception
         }
      //System.out.println(userList.toString());
   
      
   }
   //텍스트파일에서 관리자목록 객체로생성
   public void insertAdmin() {
      String line = "";
      try {
         FileReader filereader = new FileReader(adminFile);
         BufferedReader bufReader = new BufferedReader(filereader);
         while((line = bufReader.readLine()) != null) {
            //System.out.println(line);
            String arr[] = line.split("\t");
            String id = arr[0];
            String pw = arr[1];
            String name = arr[2];
            
            //userCount++;
            adminList.add(new Admin(id, pw, name));
         }

         } catch (Exception e) {
            // TODO: handle exception
         }
      
   }
    public void changeUserInfo() { //관리자가 사원 정보변경(사번값으로)
         System.out.print("수정할 사원의 사번을 입력하세요 : ");
         String id = sc.nextLine();
         for(User u : userList) {
            if(u.getId().equals(id)) {
               System.out.print("수정할 정보를 고르세요 : ");
               System.out.print("1. 직책 2. 부서 3. 휴대폰번호 4. 이메일 5. 뒤로가기 : ");
               int key = sc.nextInt();
               switch (key) {
               case 1:
                  System.out.print("변경할 직책: ");
                  String newPosition = sc.nextLine();
                  u.setPosition(newPosition);
                  
                  break;
               case 2:
                  System.out.print("변경할 부서: ");
                  String newDept = sc.nextLine();
                  u.setDept(newDept);
                  break;
               case 3:
                  System.out.print("변경할 번호: ");
                  String newPhone = sc.nextLine();
                  u.setPhone(newPhone);
                  break;
               case 4:
                  System.out.print("변경할 이메일: ");
                  String newEmail= sc.nextLine();
                  u.setEmail(newEmail);
                  break;
               case 5:
                  break;

               default:
                  break;
               }
               try {
                       FileWriter fw = new FileWriter(userFile,false);
                      
                       for(int i=0; i<userList.size(); i++) {
                          fw.append(userList.get(i).getId() + "\t");
                          fw.append(userList.get(i).getPw() + "\t");
                          fw.append(userList.get(i).getName()+ "\t");
                          fw.append(userList.get(i).getPosition()+ "\t");
                          fw.append(userList.get(i).getDept()+ "\t");
                          fw.append(userList.get(i).getPhone()+ "\t");
                          fw.append(userList.get(i).getEmail()+ "\t");
                         fw.append(userList.get(i).getHireDate()+ "\r");
                       }
                       fw.close();
                       } catch (Exception e) {
                   // TODO: handle exception
                }
               System.out.println("수완료");
               break;
            }
            
         }
         userCount--;
         
      }
      public void delUser() { //관리자가 퇴사사원 유저목록에서 삭제(사번값으로)
         System.out.print("삭제할 사원의 사번을 입력하세요 : ");
         String id = sc.nextLine();
         for(User u : userList) {
            if(u.getId().equals(id)) {
               userList.remove(u);
               try {
                       FileWriter fw = new FileWriter(userFile,false);
                      
                       for(int i=0; i<userList.size(); i++) {
                          fw.append(userList.get(i).getId() + "\t");
                          fw.append(userList.get(i).getPw() + "\t");
                          fw.append(userList.get(i).getName()+ "\t");
                          fw.append(userList.get(i).getPosition()+ "\t");
                          fw.append(userList.get(i).getDept()+ "\t");
                          fw.append(userList.get(i).getPhone()+ "\t");
                          fw.append(userList.get(i).getEmail()+ "\t");
                         fw.append(userList.get(i).getHireDate()+ "\r");
                       }
                       fw.close();
                       } catch (Exception e) {
                   // TODO: handle exception
                }
               System.out.println("삭제완료");
               break;
            }
            
         }
         userCount--;
         
      }
      
   
   // 유저 삭제
   public void removeUser(User user) {
      // 사내 부서에 user가 존재하는지 확인
      for (Dept dept : deptList) {
         if(dept.getUserList().contains(user)) {
            dept.getUserList().remove(user);
            System.out.println("성공적으로 삭제되었습니다.");
            return;
         }
      }
      System.out.println("존재하지 않는 User입니다.");
      
   }
   
   // 부서 삭제
   public void removeDept(Dept dept) {
      // 부서가 존재하는지 확인
      if(deptList.contains(dept)) {
         deptList.remove(dept);
         System.out.println("해당 부서가 성공적으로 삭제되었습니다.");
         return;
      }
      System.out.println("존재하지 않는 부서입니다.");
   }
   
   // 공지사항 추가
   public void addNotice(Notice notice) {
      noticeList.add(notice);
   }
   
   // 공지사항 제거
   public void removeNotice(Notice notice) {
      if(noticeList.contains(notice)) {
         noticeList.remove(notice);
         System.out.println("공지사항이 제거되었습니다.");
      }
      else {
         System.out.println("존재하지 않는 공지사항입니다.");
      }
      
   }
   
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public int getUserCount() {
      return userCount;
   }
   public void setUserCount(int userCount) {
      this.userCount = userCount;
   }
   public int getRevenue() {
      return revenue;
   }
   public void setRevenue(int revenue) {
      this.revenue = revenue;
   }
   public List<Dept> getDeptList() {
      return deptList;
   }
   public void setDeptList(List<Dept> deptList) {
      this.deptList = deptList;
   }
   public static List<User> getUserList() {
      return userList;
   }
   public void setUserList(List<User> userList) {
      this.userList = userList;
   }
   public static List<Admin> getAdminList() {
      return adminList;
   }
   public static void setAdminList(List<Admin> adminList) {
      Company.adminList = adminList;
   }
   
   
   
   
   

}