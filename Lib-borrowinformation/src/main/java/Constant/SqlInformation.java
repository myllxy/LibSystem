package Constant;

import Bean.Val;

/**
 * @author nsu_zk
 * @create 2019-09-02 17:07
 */
public enum SqlInformation implements Val {
    SQLINFORMATION("delete from borrowinformation where Number=?;"),
    SQLSTUDENT("update student set Notreturned=Notreturned-1 where StudentId=?;"),
    SQLSELECT("select * from Borrowinformation ");
    private String sql;

    SqlInformation(String sql) {
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
