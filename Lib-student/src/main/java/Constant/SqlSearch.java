package Constant;

import Bean.Val;

/**
 * @author nsu_zk
 * @create 2019-09-01 10:11
 */
public enum SqlSearch implements Val {
    SQLSEARCH("select * from book where bookname = ?;");
    private String sql;

    SqlSearch(String sql) {
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
