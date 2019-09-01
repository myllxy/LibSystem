package Constant;

import Bean.Val;

/**
 * @author nsu_zk
 * @create 2019-08-31 23:15
 */
public enum SqlStudent implements Val {
    STUDENTSQL("select * from student where StudentId=?;");

    SqlStudent(String sql) {
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

