package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BbsDAO {
    private Connection conn;
    private ResultSet rs;
    
    public BbsDAO() {
        try {
            String dbURL = "jdbc:h2:tcp://localhost/~/appleDB";
            String dbID = "apple";
            String dbPassword = "1234";
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getDate() {
    	String sql = "select now()";
    	try {
    			PreparedStatement pstmt = conn.prepareStatement(sql);
    			rs = pstmt.executeQuery();
    			if(rs.next()) {
    				return rs.getString(1);
    			}
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    		return ""; //데이터 베이스 오류
    }
    
    public int getNext() {
    	//현재 게시글을 내림차 순으로 조회, 마지막 글의 번호를 구하기
    	String sql = "SELECT bbsID FROM bbs ORDER BY bbsID DESC";
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		if(rs.next()) {
    			return rs.getInt(1) + 1;
    		}
    		return 1;
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	return -1;
    }
    public int write(String bbsTitle, String userID, String bbsContent) {
    	String sql = "insert into bbs values(?, ?, ?, ?, ?, ?)";
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, getNext());
    		pstmt.setString(2, bbsTitle);
    		pstmt.setString(3, userID);
    		pstmt.setString(4, getDate());
    		pstmt.setString(5, bbsContent);
    		pstmt.setInt(6, 1);
    		return pstmt.executeUpdate();
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	return -1;
    }
    
    public ArrayList<Bbs> getList(int pageNumber){
    	String sql = "SELECT * FROM bbs WHERE bbsID < ? and bbsAvailable = 1 ORDER BY bbsID DESC limit 5";
    	ArrayList<Bbs> list = new ArrayList<Bbs>();
    	try {
    			PreparedStatement pstmt = conn.prepareStatement(sql);
    			pstmt.setInt(1,  getNext() - (pageNumber - 1) * 5);
    			rs = pstmt.executeQuery();
    			while(rs.next()) {
    				Bbs bbs = new Bbs();
    				bbs.setBbsID(rs.getInt(1));
    				bbs.setBbsTitle(rs.getString(2));
    				bbs.setUserID(rs.getString(3));
    				bbs.setBbsDate(rs.getString(4));
    				bbs.setBbsContent(rs.getString(5));
    				bbs.setBbsAvailable(rs.getInt(6));
    				list.add(bbs);
    			}
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    
    public boolean nextPage(int pageNumber) {
    	String sql = "select * from bbs where bbsID < ? and bbsAvailable = 1";
    	try {
    			PreparedStatement pstmt = conn.prepareStatement(sql);
    			pstmt.setInt(1,  getNext() - (pageNumber - 1) * 5);
    			rs = pstmt.executeQuery();
    			if(rs.next()) {
    				return true;
    			}
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public Bbs getBbs(int bbsID) {
    	String sql = "select * from bbs where bbsID = ?";
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1,  bbsID);
    		rs = pstmt.executeQuery();
    		if(rs.next()) {
    			Bbs bbs = new Bbs();
    			bbs.setBbsID(rs.getInt(1));
    			bbs.setBbsTitle(rs.getString(2));
    			bbs.setUserID(rs.getString(3));
    			bbs.setBbsDate(rs.getString(4));
    			bbs.setBbsContent(rs.getString(5));
    			bbs.setBbsAvailable(rs.getInt(6));
    			return bbs;
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public int update(int bbsID, String bbsTitle, String bbsContent) {
    	String sql = "update bbs set bbsTitle = ?, bbs Content = ?, where bbsID = ?";
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1,  bbsID);
    		return pstmt.executeUpdate();
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	return -1;
    }
    
    public int delete(int bbsID) {
    	String sql = "update bbs set bbsAvailable = 0 where bbsID = ?";
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1,  bbsID);
    		return pstmt.executeUpdate();
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	return -1;
    }
    
}
