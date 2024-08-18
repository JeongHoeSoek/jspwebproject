package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    public UserDAO() {
        try {
            String dbURL = "jdbc:h2:tcp://localhost/~/appleDB";
            String dbID = "apple";
            String dbPassword = "1234";
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            System.out.println("Database connection successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int login(String userID, String userPassword) {
        String sql = "select userPassword from MEMBER where userID = ?";
        try {
            System.out.println("Executing query for userID: " + userID);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID); 
            rs = pstmt.executeQuery();
            if(rs.next()) {
                System.out.println("User found, checking password...");
                if(rs.getString(1).equals(userPassword)) {
                    System.out.println("Password matches");
                    return 1;
                } else {
                    System.out.println("Password does not match");
                    return 0;
                }
            }
            System.out.println("User not found");
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -2;
    }
    
    public int join(User user) {
    	String sql = "insert into MEMBER values(?,?,?,?,?)";
    	try {
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, user.getUserID());
    		pstmt.setString(2, user.getUserPassword());
    		pstmt.setString(3, user.getUserName());
    		pstmt.setString(4, user.getUserGender());
    		pstmt.setString(5, user.getUserEmail());
    		return pstmt.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return -1;
    }
}
