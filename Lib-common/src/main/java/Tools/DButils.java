package Tools;

import Constant.DBinfo;
import org.junit.Test;

import java.sql.*;

/**
 * @author nsu_zk
 * @create 2019-08-21 12:14
 */
public class DButils {
    static String user = DBinfo.USER.getName();
    static String pwd = DBinfo.PWD.getName();
    static String url = DBinfo.URL.getName();
    private static Statement stmt;
    private static PreparedStatement prestmt;
    private static Connection con;


    /**
     * 获取连接对象
     *
     * @return
     */
    public static Connection getCon() {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, user, pwd);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    /**
     * 获取状态对象
     *
     * @return
     */
    public static Statement getStmt() {
        if (con == null) {
            con = getCon();
        }
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }

    /**
     * 获取预编译sql状态对象
     * @param sql
     * @return
     */
    public static PreparedStatement getpreStmt(String sql) {
        if (con == null) {
            con = getCon();
        }
        try {
            prestmt = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestmt;
    }

    public static void main(String[] args) {
    }
}
