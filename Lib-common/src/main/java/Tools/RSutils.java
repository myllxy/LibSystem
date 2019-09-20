package Tools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * RS结果集的处理方法
 * @author nsu_zk
 * @create 2019-09-20 20:59
 */
public class RSutils {
    // getColumnCount方法所返回的数组，索引0为当前行号，此时游标在表末尾，索引1为列总数
    static int[] ints = new int[2];

    public static int[] getColumnCount(PreparedStatement preparedStatement, ResultSet rs) {

        try {
            rs = preparedStatement.executeQuery();
            rs.last();
            ints[0] = rs.getRow();
            ResultSetMetaData rsmd = rs.getMetaData();
            ints[1] = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ints;
    }
}
