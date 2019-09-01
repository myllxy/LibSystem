package Constant;

import Bean.Val;

/**
 * @author nsu_zk
 * @create 2019-09-01 10:46
 */
public enum SqlModel implements Val {
    SQLSELECT("select * from book where bookname=?;");
    private String sql;

    SqlModel(String sql) {
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
