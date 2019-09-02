package Constant;

import Bean.Val;

/**
 * @author nsu_zk
 * @create 2019-09-02 10:49
 */
public enum SqlBorrowinformation implements Val {
    INSERT("insert into borrowinformation values(?,?,?,?,'1')");
    private String sql;

    SqlBorrowinformation(String sql) {
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
