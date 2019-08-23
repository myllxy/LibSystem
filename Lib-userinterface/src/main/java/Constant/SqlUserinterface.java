package Constant;

import Bean.Val;

/**
 * Userinterface界面的预编译sql
 *
 * @author nsu_zk
 * @create 2019-08-23 20:55
 */
public enum SqlUserinterface implements Val {
    ADMINISTRATORSQL("select * from Administrator where StudentId=? AND Password=?;"),
    STUDENTSQL("select * from Student where StudentId=? AND Password=?;");

    SqlUserinterface(String sql) {
        this.sql = sql;
    }

    private String sql;

    @Override
    public void setName(String sql) {
        this.sql = sql;
    }

    @Override
    public String getName() {
        return sql;
    }
}
