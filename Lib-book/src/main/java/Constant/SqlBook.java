package Constant;

import Bean.Val;

/**
 * @author nsu_zk
 * @create 2019-09-01 10:29
 */
public enum SqlBook implements Val {
    SELECT("select number,stackroom from book where bookname=?;"),
    INSERT("insert into borrowinformation values(?,?,?,?,'1') "),
    UPDATE("update student set Totallend = Totallend + 1,Notreturned = Notreturned + 1 where StudentId=?;");
    private String sql;

    SqlBook(String sql) {
        this.sql = sql;
    }

    @Override
    public void setName(String sql) {
        this.sql = sql;
    }

    @Override
    public String getName() {
        return sql;
    }
}
