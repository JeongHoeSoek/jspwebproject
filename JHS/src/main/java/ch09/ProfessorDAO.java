package ch09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {
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
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Professor p) {
		open();
		String sql = "INSERT INTO professor(username, email) values(?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getUsername());
			pstmt.setString(2, p.getEmail());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void update(Professor p) {
		open();
		String sql = "UPDATE professor SET username=?, email=? WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getUsername());
			pstmt.setString(2, p.getEmail());
			pstmt.setInt(3, p.getId());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void delete(int id) {
		open();
		String sql = "DELETE FROM professor WHERE id=?";
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
	
	public Professor get(int id) {
		open();
		Professor p = new Professor();
		String sql = "SELECT * FROM professor WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				p.setId(rs.getInt("id"));
				p.setUsername(rs.getString("username"));
				p.setEmail(rs.getString("email"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return p;
	}
	
	public List<Professor> getAll() {
		open();
		List<Professor> professors = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement("select * from professor");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Professor p = new Professor();
				p.setId(rs.getInt("id"));
				p.setUsername(rs.getString("username"));
				p.setEmail(rs.getString("email"));
				professors.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return professors;
	}
}
