package Constant;

import Bean.Val;

/**
 * @author nsu_zk
 * @create 2019-09-01 12:05
 */
public enum SqlChangepwd implements Val {
    SQLCHANGEPWD_ST("update student set Password=? where Password=?;")
    ,SQLCHANGEPWD_AD("update administrator set Password=? where Password=?;")
    ;
    private String sql;

    SqlChangepwd(String sql) {
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
