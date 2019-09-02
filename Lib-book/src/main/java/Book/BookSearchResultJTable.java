package Book;

import Bean.Modelfunction;
import Bean.Pagefunction;
import Constant.SqlBorrowinformation;
import Constant.SqlModel;
import Constant.SqlBook;
import Tools.DButils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BookSearchResultJTable extends JFrame implements Pagefunction {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JButton btn; // 借阅按钮
    Font font; // 字体
    private JScrollPane jsp; // 滚动面板
    private JTable table;// MVC Model View Controller
    static int row;
    public static String bookname; // 书名
    public static String ID; // 学生ID

    public BookSearchResultJTable(String bookname, String ID) {
        setTitle("BookSearchResultJTable");
        BookSearchResultJTable.bookname = bookname;
        BookSearchResultJTable.ID = ID;
        setSize(500, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        newcomponent();
        setcomponent();
        addcomponentto();
        init();
    }

    public void init() {
        /**
         * 借阅按钮
         * 这个按钮的作用：
         * 1.从book中获取图书的number,stackroom
         * 2.将借阅信息插入到borrowinformation
         * 3.同步更新student中的Totallend和Notreturned
         */
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // "select number,stackroom from book where bookname=?;"
                PreparedStatement stmt_1 = DButils.getpreStmt(SqlBook.SELECT.getName());
                // "insert into borrowinformation values(?,?,?,?,'1')"
                PreparedStatement stmt_2 = DButils.getpreStmt(SqlBorrowinformation.INSERT.getName());
                // "update student set Totallend = Totallend + 1,Notreturned = Notreturned + 1 where StudentId=?;"
                PreparedStatement stmt_3 = DButils.getpreStmt(SqlBook.UPDATE.getName());
                try {
                    stmt_1.setString(1, BookSearchResultJTable.bookname);
                    ResultSet rs = stmt_1.executeQuery();
                    rs.last();
                    // 因为只有一本!
                    String number = rs.getString(1);
                    String stackroom = rs.getString(2);

                    stmt_2.setString(1, BookSearchResultJTable.ID);
                    stmt_2.setString(2, BookSearchResultJTable.bookname);
                    stmt_2.setString(3, number);
                    stmt_2.setString(4, stackroom);
                    stmt_3.setString(1, BookSearchResultJTable.ID);
                    // 如果insert和update操作都执行成功
                    if (stmt_2.executeUpdate() == 1 && stmt_3.executeUpdate() == 1) {
                        JOptionPane.showMessageDialog(null, "借阅成功，时间为1个月", "提示",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void refresh() {

    }

    public void newcomponent() {
        btn = new JButton("Borrow");
        font = new Font("comic sans ms", Font.BOLD, 15);
        jsp = new JScrollPane();
        table = new JTable();
    }

    public void setcomponent() {
        table.setGridColor(Color.BLUE);
        table.setSelectionBackground(Color.BLUE);
        table.setRowHeight(30);
        table.setModel(new MyModel_2());
        jsp.setViewportView(table);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        table.getTableHeader().setFont(font);
    }

    public void addcomponentto() {
        this.add(jsp);
        this.add(btn, BorderLayout.SOUTH);
    }
}

class MyModel_2 extends AbstractTableModel implements Modelfunction {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    static String[] n = {"number", "bookname", "Stackroom"};
    private int column;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public MyModel_2() {
        execute();
    }

    @Override
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return n[column];
    }

    @Override
    public int getRowCount() {
        return BookSearchResultJTable.row;
    }

    @Override
    public int getColumnCount() {
        return column;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        try {
            preparedStatement = DButils.getpreStmt(SqlModel.SQLSELECT.getName());
            preparedStatement.setString(1, BookSearchResultJTable.bookname);
            rs = preparedStatement.executeQuery();
            rs.absolute(rowIndex + 1);

            value = rs.getString(columnIndex + 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public void execute() {
        preparedStatement = DButils.getpreStmt(SqlModel.SQLSELECT.getName());
        try {
            preparedStatement.setString(1, BookSearchResultJTable.bookname);
            rs = preparedStatement.executeQuery();
            rs.last();
            BookSearchResultJTable.row = rs.getRow();
            ResultSetMetaData rsmd = rs.getMetaData();
            column = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
