package Constant;

import Bean.Val;
import Book.BookJTable;

/**
 * @author nsu_zk
 * @create 2019-09-01 10:29
 */
public enum SqlBook implements Val {
    SELECT("select number,stackroom from book where bookname=?;"),
    UPDATE("update student set Totallend = Totallend + 1,Notreturned = Notreturned + 1 where StudentId=?;"),
    INSERT_BOOK_1("insert into book values(?,'','');"),
    SELECT_BOOK("select * from book;"),
    DELECT_BOOK_1("delete from book where number=?"),
    UPDATE_BOOK_BOOKNAME("update book set bookname=? where bookname=? and number =?;"),
    UPDATE_BOOK_STACKROOM("update book set stackroom=? where stackroom=? and number =?;")
    ;
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
