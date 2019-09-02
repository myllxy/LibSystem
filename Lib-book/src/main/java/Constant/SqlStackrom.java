package Constant;

import Bean.Val;

/**
 * @author nsu_zk
 * @create 2019-09-02 8:59
 */
public enum SqlStackrom implements Val {
    UPDATE_STACKROOM("update stackroom set Bookamount=Bookamount-1 where Stackroomnumber=?"),
    UPDATE_STACKROOM_2("update stackroom set Bookamount=Bookamount+1 where Stackroomnumber=?"),
    UPDATE_STACKROOM_3("update stackroom set Bookamount=Bookamount-1 where Stackroomnumber=1"),
    SELECT_STACKROOM_1("select * from stackroom;")
    ;

    private String sql;

    SqlStackrom(String sql) {
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
