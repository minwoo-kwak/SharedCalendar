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
	File file = new File("C:\\user\\userDB.txt");

	public void init() { // 초기화면
		System.out.print("[1] 일반사원 로그인		[2]관리자 로그인		[0]Exit \n" + ">> ");
		String menu = sc.nextLine();

		if (menu.equals("1"))
			userLogin();
		else if (menu.equals("2"))
			adminLogin();
		else
			System.exit(0);
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
					System.out.println("관리자님 환영합니다.");
					adminInfo();
					break;
				}
				System.out.println("로그인 실패 다시 시도하세요!");
				continue;
			}
		}
	}

	public void userLogin() { // 일반사원 로그인메소드
		// userList.add(new
		// User("1234","1234","나병희","사원","개발팀","010-8747-7304","nbh7301@naver.com"));
		// userList.add(new
		// User("1111","1111","너병희","주임","인사팀","010-8747-7304","nbh7301@naver.com"));

		// int pass = 0;
		while (true) {
			System.out.println("====일반 유저 로그인====");
			System.out.print("아이디를 입력하세요: ");
			id = sc.nextLine();
			System.out.print("비밀번호를 입력하세요: ");
			pw = sc.nextLine();
//    	  try {
//			FileReader filereader = new FileReader(file);
//			BufferedReader bufReader = new BufferedReader(filereader);
//			String line = "";
//			try {
//				while((line = bufReader.readLine()) != null) {
//					//System.out.println(line);
//					String arr[] = line.split("\t");
//					if(arr[0].equals(id) && pw.equals(arr[1])) {
//						System.out.println("로그인성공");
//						bufReader.close();
//						myInfo();
//					}else {
//						System.out.println("로그인실패");
//						
//					}
//				}
////					//System.out.println(line.indexOf(id) + "\t" + line.indexOf(pw));
////					int passId = line.indexOf(id);
////					int passPw = line.indexOf(pw);
////					if(passId != -1 && passPw != -1) {
////						System.out.println("로그인성공");
////						System.out.println(line.indexOf(id) + "\t" + line.indexOf(pw));
////						pass = 1;
////						bufReader.close();
////						myInfo();	}
////					if(pass == 0) {
////						System.out.println("로그인실패"); }
//				
//						
//						//myInfo();
//					//if(pass == 0) System.out.println("로그인실패 다시 시도하세요");
//				}
//				
//			 catch (Exception e) {
//				// TODO: handle exception
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}

			if (findByUserId(id) == null) {
				System.out.println("존재하지 않는 아이디입니다.");
				continue;
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
			System.out.print("1. 사원등록 2. 사원수정 3. 사원삭제 4. 일정달력보기 5. 로그아웃");
			int select = sc.nextInt();
			switch (select) {
			case 1:
				cp.addUser2();

				break;
			case 2:

				break;
			case 3:
				System.out.print("지울 사원의 이름을 입력하세요: ");
				String name = sc.next();
				delUser(name);
				System.out.println("삭제완료");
				break;
			case 4:
				CalendarSystem sys = new CalendarSystem();
				//sys.init();

				break;
			case 5:
				System.out.println("로그아웃");
				adminLogout();
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
				while (true) {
					System.out.println("1. 직책 2. 부서 3. 휴대폰번호 4. 이메일 5. 뒤로가기");
					int key = sc.nextInt();
					switch (key) {
					case 1:
						System.out.print("어떤 직책으로 변경하시겠습니까?  ");
						String position = sc.next();
						loginUser.setPosition(position);
//                    FileSystem.save("C:\\user", "userDB", userList);
						try {
							file = new File("C:\\user\\userDB.txt");
							FileWriter fw = new FileWriter(file, false);

							for (int i = 0; i < userList.size(); i++) {
								fw.append(userList.get(i).getId() + "\t");
								fw.append(userList.get(i).getPw() + "\t");
								fw.append(userList.get(i).getName() + "\t");
								fw.append(userList.get(i).getPosition() + "\t");
								fw.append(userList.get(i).getDept() + "\t");
								fw.append(userList.get(i).getPhone() + "\t");
								fw.append(userList.get(i).getHireDate() + "\t");
								fw.append(userList.get(i).getEmail() + "\r");
							}
							fw.flush();
						} catch (Exception e) {
							// TODO: handle exception
						}

						System.out.println("바뀐 직책은 " + loginUser.getPosition());

						break;
					case 2:
						System.out.print("어떤 부서로 이동하시겠습니까?  ");
						String dept = sc.next();
						loginUser.setDept(dept);
						try {
							file = new File("C:\\user\\userDB.txt");
							FileWriter fw = new FileWriter(file, false);

							for (int i = 0; i < userList.size(); i++) {
								fw.append(userList.get(i).getId() + "\t");
								fw.append(userList.get(i).getPw() + "\t");
								fw.append(userList.get(i).getName() + "\t");
								fw.append(userList.get(i).getPosition() + "\t");
								fw.append(userList.get(i).getDept() + "\t");
								fw.append(userList.get(i).getPhone() + "\t");
								fw.append(userList.get(i).getEmail() + "\r");
								fw.append(userList.get(i).getHireDate() + "\r");
							}
							fw.flush();
						} catch (Exception e) {
							// TODO: handle exception
						}
						System.out.println("바뀐 부서는 " + loginUser.getDept());
						break;
					case 3:
						System.out.print("어떤 번호로 변경하시겠습니까?  ");
						String phone = sc.next();
						loginUser.setPhone(phone);
						try {
							file = new File("C:\\user\\userDB.txt");
							FileWriter fw = new FileWriter(file, false);

							for (int i = 0; i < userList.size(); i++) {
								fw.append(userList.get(i).getId() + "\t");
								fw.append(userList.get(i).getPw() + "\t");
								fw.append(userList.get(i).getName() + "\t");
								fw.append(userList.get(i).getPosition() + "\t");
								fw.append(userList.get(i).getDept() + "\t");
								fw.append(userList.get(i).getPhone() + "\t");
								fw.append(userList.get(i).getEmail() + "\r");
							}
							fw.flush();
						} catch (Exception e) {
							// TODO: handle exception
						}
						System.out.println("바뀐 휴대폰번호는 " + loginUser.getPhone());
						break;
					case 4:
						System.out.print("어떤 이메일로 변경하시겠습니까?  ");
						String email = sc.next();
						loginUser.setEmail(email);
						try {
							file = new File("C:\\user\\userDB.txt");
							FileWriter fw = new FileWriter(file, false);

							for (int i = 0; i < userList.size(); i++) {
								fw.append(userList.get(i).getId() + "\t");
								fw.append(userList.get(i).getPw() + "\t");
								fw.append(userList.get(i).getName() + "\t");
								fw.append(userList.get(i).getPosition() + "\t");
								fw.append(userList.get(i).getDept() + "\t");
								fw.append(userList.get(i).getPhone() + "\t");
								fw.append(userList.get(i).getEmail() + "\r");
							}
							fw.flush();
						} catch (Exception e) {
							// TODO: handle exception
						}
						System.out.println("바뀐 이메일은 " + loginUser.getEmail());
						break;
					case 5:
						myInfo();

					default:
						break;
					}

				}

			case 3:
				System.out.println("로그아웃");
				userLogout();
				init();

				// System.exit(0);
			case 4:
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
					file = new File("C:\\user\\userDB.txt");
					FileWriter fw = new FileWriter(file, false);

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

	public void delUser(String name) { // 관리자가 사원이름입력받고 리스트랑 파일에서 삭제
		for (User u : userList) {
			if (u.getName().equals(name)) {
				userList.remove(u);
				try {
					file = new File("C:\\user\\userDB.txt");
					FileWriter fw = new FileWriter(file, false);

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

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Admin> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<Admin> adminList) {
		this.adminList = adminList;
	}

	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		UserManager.loginUser = loginUser;
	}

	public static Admin getLoginAdmin() {
		return loginAdmin;
	}

	public static void setLoginAdmin(Admin loginAdmin) {
		UserManager.loginAdmin = loginAdmin;
	}

}