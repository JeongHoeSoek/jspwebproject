package ch10;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String JDBC_URL = "jdbc:h2:tcp://localhost/~/appleDB";

    public Connection open() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL, "apple", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public List<News> getAll() throws Exception {
        Connection conn = open();
        List<News> newsList = new ArrayList<>();

        String sql = "select aid, title, date from news";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        try (conn; pstmt; rs) {
            while (rs.next()) {
                News n = new News();
                n.setAid(rs.getInt("aid"));
                n.setTitle(rs.getString("title"));
                n.setDate(rs.getString("date"));
                newsList.add(n);
            }
            return newsList;
        }
    }

    public News getNews(int aid) throws SQLException {
        Connection conn = open();

        News n = new News();
        String sql = "select aid, title, img, date, content from news where aid=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, aid);
        ResultSet rs = pstmt.executeQuery();
        rs.next();

        try (conn; pstmt; rs) {
            n.setAid(rs.getInt("aid"));
            n.setTitle(rs.getString("title"));
            n.setImg(rs.getString("img"));
            n.setDate(rs.getString("date"));
            n.setContent(rs.getString("content"));
            return n;
        }
    }

    public void delNews(int aid) throws SQLException {
        Connection conn = open();

        String sql = "delete from news where aid = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        try (conn; pstmt) {
            pstmt.setInt(1, aid);
            if (pstmt.executeUpdate() == 0) {
                throw new SQLException("DB에러");
            }
        }
    }

    public void addNews(News n) throws Exception {
        Connection conn = open();
        String sql = "insert into news(title, img, date, content) values (?, ?, CURRENT_TIMESTAMP(), ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try (conn; pstmt) {
            pstmt.setString(1, n.getTitle());
            pstmt.setString(2, n.getImg());
            pstmt.setString(3, n.getContent());
            pstmt.executeUpdate();
        }
    }

    public void updateNews(int aid, String title, String content) throws SQLException {
        Connection conn = open();

        String sql = "update news set title=?, content=? where aid=?";
        PreparedStatement updatePstmt = conn.prepareStatement(sql);
        updatePstmt.setString(1, title);
        updatePstmt.setString(2, content);
        updatePstmt.setInt(3, aid);
        updatePstmt.executeUpdate();
    }
}
