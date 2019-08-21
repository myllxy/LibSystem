package Tools;

import Constant.DBinfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author nsu_zk
 * @create 2019-08-21 12:14
 */
public class DButils {
    static String user = DBinfo.USER.getName();
    static String pwd = DBinfo.PWD.getName();
    static String url = DBinfo.URL.getName();
    private static Statement stmt;
    private static Connection con;


    /**
     * ��ȡ���Ӷ���
     *
     * @return
     */
    public static Connection getCon() {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(user, pwd, url);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    /**
     * ��ȡ״̬����
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

    public static void main(String[] args) {
    }
}
