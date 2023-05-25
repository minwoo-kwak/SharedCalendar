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
    File file;
    Scanner  sc = new Scanner(System.in);
    
   // Constructors
   public Company() {
      new ArrayList<Dept>();
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
   public void addUser(User user, Dept dept) {
      // 유저의 부서가 이미 존재하면
      if(deptList.contains(dept)) {
         dept.addUser(user);
          file  = new File("C:\\user\\userDB.txt");
          try {
            FileWriter filewriter = new FileWriter(file, true);
            if(file.isFile() && file.canWrite()) {
               //System.out.print("id: ");
               filewriter.append(sc.nextLine());
               filewriter.append("\t");
               //System.out.print("pw: ");
               filewriter.append(sc.nextLine());
               filewriter.append("\r");
               System.out.println("완료");
            }
            filewriter.close();
         } catch (Exception e) {
            // TODO: handle exception
         }
      } // 유저의 부서가 존재하지 않으면 
      else {
         addDept(dept); // 부서를 새로 생성
         dept.addUser(user);
      }
      userCount++;
   }
   //유저추가데모
   public void addUser2() {
      // 유저의 부서가 이미 존재하면
      String id = sc.next();
      String pw = sc.next();
      String name = sc.next();
      String position = sc.next();
      String dept = sc.next();
      String phone = sc.next();
      String email = sc.next();
      LocalDate hireDate = LocalDate.parse(sc.next());
      
      new User(id, pw, name, position, dept, phone, email, hireDate);
      file  = new File("C:\\user\\userDB.txt");
       try {
         FileWriter filewriter = new FileWriter(file, true);
         if(file.isFile() && file.canWrite()) {
            //System.out.print("id: ");
            filewriter.append(id);
            filewriter.append("\t");
            filewriter.append(pw);
            filewriter.append(" \t");
            filewriter.append(name);
            filewriter.append("\t");
            filewriter.append(position);
            filewriter.append("\t");
            filewriter.append(dept);
            filewriter.append("\t");
            filewriter.append(phone);
            filewriter.append("\t");
            filewriter.append(email);
            filewriter.append("\r");
            //System.out.print("pw: ");
//            filewriter.append(sc.nextLine());
//            filewriter.append("\r");
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
      File file  = new File("C:\\user\\userDB.txt");
      
      
      String line = "";
      try {
         FileReader filereader = new FileReader(file);
         BufferedReader bufReader = new BufferedReader(filereader);
         while((line = bufReader.readLine()) != null) {
            System.out.println(line);
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
//            if(arr[0].equals(id) && pw.equals(arr[1])) {
//               System.out.println("로그인성공");
//               bufReader.close();
//               //myInfo();
//            }else {
//               System.out.println("로그인실패");
//               
//            }
         } catch (Exception e) {
            // TODO: handle exception
         }
      System.out.println(userList.toString());
   
      
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
   
   
   
   
   

}