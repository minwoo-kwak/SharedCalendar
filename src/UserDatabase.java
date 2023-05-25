import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDatabase {
    private Connection connection;
    private PreparedStatement addUserStatement;
    private PreparedStatement getUserStatement;
    private final String url = "jdbc:mysql://localhost:3306/mydatabase";
    private final String username = "root";
    private final String password = "1234";
    
    public UserDatabase() throws ClassNotFoundException {
        try {
            // MySQL 데이터베이스에 접속
            connection = DriverManager.getConnection(url, username, password);
            
            // 유저 추가를 위한 PreparedStatement 생성
            addUserStatement = connection.prepareStatement("INSERT INTO users (id, pw, name, position, dept, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?)");
            // 유저 조회를 위한 PreparedStatement 생성
            getUserStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // DB에 유저 추가
    public void addUser(User user) {
        try {
            // PreparedStatement에 유저 정보 바인딩
            addUserStatement.setString(1, user.getId());
            addUserStatement.setString(2, user.getPw());
            addUserStatement.setString(3, user.getName());
            addUserStatement.setString(4, user.getPosition());
            addUserStatement.setString(5, user.getDept());
            addUserStatement.setString(6, user.getPhone());
            addUserStatement.setString(7, user.getEmail());
            
            // 유저 정보 추가
            addUserStatement.executeUpdate();
            
            System.out.println("유저 정보가 성공적으로 추가되었습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public User getUser(String id) {
        try {
        	
            // PreparedStatement에 유저 ID 바인딩
            getUserStatement.setString(1, id);
            
            // 유저 정보 조회
            ResultSet resultSet = getUserStatement.executeQuery();
            
            // 결과가 존재하면 첫 번째 행의 데이터로 User 객체 생성
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setPw(resultSet.getString("pw"));
                user.setName(resultSet.getString("name"));
                user.setPosition(resultSet.getString("position"));
                user.setDept(resultSet.getString("dept"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                // hireDate와 vacation 등의 정보도 필요하다면 추가로 가져와서 User 객체에 설정
                
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null; // 유저 정보가 없으면 null 반환
    }
    
  
    // Connection and Related resources close
    public void close() {
        try {
            // 데이터베이스 연결과 관련된 리소스 정리
            if (addUserStatement != null)
                addUserStatement.close();
            
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // 텍스트로 추출
    public void exportUsersToTxt(String filePath) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             FileWriter writer = new FileWriter(filePath)) {

            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String pw = resultSet.getString("pw");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                String dept = resultSet.getString("dept");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String hireDate = resultSet.getString("hireDate");
                int vacation = resultSet.getInt("vacation");

                String userLine = String.format("ID: %s, Name: %s, Position: %s, Department: %s, Phone: %s, Email: %s, Hire Date: %s, Vacation: %d\n",
                        id, name, position, dept, phone, email, hireDate, vacation);
                writer.write(userLine);
            }

            System.out.println("User information exported to file: " + filePath);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
