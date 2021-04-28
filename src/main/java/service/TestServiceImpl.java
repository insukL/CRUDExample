package service;

import domain.Users;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    private static String className = "com.mysql.cj.jdbc.Driver";
    private static String dbUrl = "jdbc:mysql://localhost:3306/community?serverTimezone=UTC";
    private static String dbUser = "root";
    private static String DbPassword = "yourPassword";

    public List<Users> getUsers() {
        List<Users> ret = new ArrayList<Users>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(className);
            conn = DriverManager.getConnection(dbUrl, dbUser, DbPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                Users temp = new Users();
                temp.setId(rs.getInt("id"));
                temp.setAccount(rs.getString("account"));
                temp.setPassword(rs.getString("password"));
                temp.setNick_name(rs.getString("nick_name"));
                temp.setDeleted(rs.getInt("deleted"));
                ret.add(temp);
            }

            rs.close();
            stmt.close();
            conn.close();

        //이하는 발생 가능 오류 try catch입니다.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return ret;
    }
}