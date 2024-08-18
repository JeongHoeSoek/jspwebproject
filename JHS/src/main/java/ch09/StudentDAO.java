package ch09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    Connection conn = null;
    PreparedStatement pstmt;

    final String JDBC_DRIVER = "org.h2.Driver";
    final String JDBC_URL = "jdbc:h2:tcp://localhost/~/appleDB";

    public void open(){
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL,"apple","1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Student s) {
        open();
        String sql = "INSERT INTO student(username, email) values(?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s.getUsername());
            pstmt.setString(2, s.getEmail());
            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void update(Student s) {
        open();
        String sql = "UPDATE student SET username=?, email=? WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s.getUsername());
            pstmt.setString(2, s.getEmail());
            pstmt.setInt(3, s.getId());
            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void delete(int id) {
        open();
        String sql = "DELETE FROM student WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public Student get(int id) {
        open();
        Student s = new Student();
        String sql = "SELECT * FROM student WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                s.setId(rs.getInt("id"));
                s.setUsername(rs.getString("username"));
                s.setEmail(rs.getString("email"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return s;
    }

    public List<Student> getAll() {
        open();
        List<Student> students = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM student");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setUsername(rs.getString("username"));
                s.setEmail(rs.getString("email"));
                students.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return students;
    }
}
