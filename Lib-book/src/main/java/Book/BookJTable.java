package Book;

import Bean.Modelfunction;
import Bean.Pagefunction;
import Constant.SqlBook;
import Constant.SqlStackrom;
import Tools.DButils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

/**
 * @author nsu_zk
 * @create 2019-09-02 8:27
 */
public class BookJTable extends JFrame implements Pagefunction {
    private static final long serialVersionUID = 1L;
    private JButton btn;
    private JButton btn1;
    Font font; // 字体
    private JScrollPane jsp; // 滚动面板
    private JTable table;
    static String getnumber;
    static String getstacknumber;
    static int row; // 表中数据总行数
    static Object selected; // 被鼠标选中的单元内容
    static String Selectcolumnname; // 被鼠标选中的列名
    static int Selectcolumn; // 被鼠标选中的列号
    static int Selectrow; // 被鼠标选中的行号

    public BookJTable() {
        setSize(500, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        newcomponent();
        setcomponent();
        addcomponentto();
        init();
    }

    @Override
    public void init() {
        /**
         * 这个按钮用于插入新行
         */
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getnumber = table.getValueAt(Selectrow, 0).toString();
                int getnumber_1 = Integer.parseInt(getnumber) + 1;
                // "insert into book values(?,'','');"
                PreparedStatement preparedStatement = DButils.getpreStmt(SqlBook.INSERT_BOOK_1.getName());
                try {
                    if (table.getRowCount() == Selectrow + 1) {
                        JOptionPane.showMessageDialog(null, "请使用Addrow添加新行",
                                "提示", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (!(Integer.parseInt(table.getValueAt(Selectrow, 0)
                                .toString()) + 2 == Integer.parseInt(table
                                .getValueAt(Selectrow + 1, 0).toString()))) {
                            JOptionPane.showMessageDialog(null, "此位置不能插入新行",
                                    "提示", JOptionPane.ERROR_MESSAGE);
                        } else {
                            preparedStatement.setInt(1, getnumber_1);
                            preparedStatement.executeUpdate();
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                table.setModel(new MyModel());
            }
        });
        /**
         * 这个按钮用于在末尾添加新行
         */
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowcount;
                // "select * from book;"
                PreparedStatement preparedStatement = DButils.getpreStmt(SqlBook.SELECT_BOOK.getName());
                try {
                    ResultSet rs = preparedStatement.executeQuery();
                    rs.last();
                    // 得到表中数据总行数
                    row = rs.getRow();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
                rowcount = Integer.parseInt(table.getValueAt(row - 1, 0)
                        .toString()) + 1;
                try {
                    // INSERT_BOOK("insert into book values(?,'','');")
                    preparedStatement = DButils.getpreStmt(SqlBook.INSERT_BOOK_1.getName());
                    preparedStatement.setInt(1, rowcount);
                    preparedStatement.executeUpdate();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                table.setModel(new MyModel());
            }
        });
    }

    @Override
    public void refresh() {

    }

    @Override
    public void newcomponent() {
        btn = new JButton("Addrow");
        btn1 = new JButton("Insertrow");
        font = new Font("comic sans ms", Font.BOLD, 15);
        jsp = new JScrollPane();
        table = new JTable();
    }

    @Override
    public void setcomponent() {
        table.getTableHeader().setFont(font);
        table.setRowHeight(30);
        table.setModel(new MyModel());
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1
                        && e.getClickCount() == 1) {
                    Selectrow = table.getSelectedRow();
                    Selectcolumn = table.getSelectedColumn();
                    Selectcolumnname = table.getColumnName(Selectcolumn);
                    selected = table.getValueAt(Selectrow, Selectcolumn);
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    JPopupMenu popMenu = new JPopupMenu();
                    JMenuItem delMenItem = new JMenuItem();
                    delMenItem.setText("删除该行");
                    delMenItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // 设置不能删除第一行
                            if ("1".equals(getnumber)) {
                                JOptionPane.showMessageDialog(null, "不能删除第一行",
                                        "提示", JOptionPane.ERROR_MESSAGE);
                            } else {
                                // DELECT_BOOK("delete from book where number=?")
                                PreparedStatement stmt_1 = DButils.getpreStmt(SqlBook.DELECT_BOOK_1.getName());
                                // UPDATE_STACKROOM("update stackroom set Bookamount=Bookamount-1 where Stackroomnumber=?")
                                PreparedStatement stmt_2 = DButils.getpreStmt(SqlStackrom.UPDATE_STACKROOM.getName());
                                try {
                                    stmt_1.setString(1, getnumber);
                                    stmt_1.executeUpdate();
                                    stmt_2.setString(1, getstacknumber);
                                    stmt_2.executeUpdate();
                                    new Stackroom();
                                    table.setModel(new MyModel());
                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });
                    popMenu.add(delMenItem);
                    JTable table = (JTable) e.getComponent();
                    // 获取鼠标右键选中的行
                    int row = table.rowAtPoint(e.getPoint());
                    // 获取鼠标右键选中的行的number和stacknumber
                    getstacknumber = table.getValueAt(row, 2).toString();
                    getnumber = table.getValueAt(row, 0).toString();
                    boolean inSelected = false;
                    // 当前鼠标右键点击所在行不被选中则高亮显示选中行
                    if (!inSelected) {
                        table.setRowSelectionInterval(row, row);
                    }
                    // 生成右键菜单
                    popMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        jsp.setViewportView(table);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    @Override
    public void addcomponentto() {
        this.add(jsp);
        this.add(btn, BorderLayout.SOUTH);
        this.add(btn1, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new BookJTable().setVisible(true);
    }
}

class MyModel extends AbstractTableModel implements Modelfunction {
    private static final long serialVersionUID = 1L;
    static String[] n = {"number", "bookname", "stackroom"};
    public static String aValue;
    static int row;
    static int column;
    private ResultSet rs;
    private PreparedStatement preparedStatement;

    public MyModel() {
        execute();
    }

    /**
     * 这里设置除了number列之外的列单元格可修改
     *
     * @param row
     * @param column
     * @return
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int getRowCount() {
        return row;
    }

    @Override
    public int getColumnCount() {
        return column;
    }

    @Override
    public String getColumnName(int column) {
        return n[column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        MyModel.aValue = aValue.toString();
        int rownumber = BookJTable.Selectrow + 1;
        // "update book set bookname=? where bookname=? and number =?;"
        // "update book set stackroom=? where stackroom=? and number =?;"
        PreparedStatement stmt_1 = DButils.getpreStmt(SqlBook.UPDATE_BOOK_BOOKNAME.getName());
        PreparedStatement stmt_2 = DButils.getpreStmt(SqlBook.UPDATE_BOOK_STACKROOM.getName());
        // "update stackroom set Bookamount=Bookamount-1 where Stackroomnumber=?"
        // "update stackroom set Bookamount=Bookamount+1 where Stackroomnumber=?"
        PreparedStatement stmt_3 = DButils.getpreStmt(SqlStackrom.UPDATE_STACKROOM.getName());
        PreparedStatement stmt_4 = DButils.getpreStmt(SqlStackrom.UPDATE_STACKROOM_2.getName());
        try {
            // 这里用update set ?会加一个''，语法不通过，没解决
            if (BookJTable.Selectcolumnname.equals("bookname")) {
                stmt_1.setString(1, MyModel.aValue);
                stmt_1.setString(2, BookJTable.selected.toString());
                stmt_1.setInt(3, rownumber);
                stmt_1.executeUpdate();
            } else {
                if (isNumerOrigin(BookJTable.selected.toString())) {
                    int a = Integer.valueOf(BookJTable.selected.toString());
                    if (a < 1 || a > 5) {
                        JOptionPane.showMessageDialog(null, "书库号输入不正确",
                                "提示", JOptionPane.ERROR_MESSAGE);
                    }
                }
                // 修改图书对应的书库号
                stmt_2.setString(1, MyModel.aValue);
                stmt_2.setString(2, BookJTable.selected.toString());
                stmt_2.setInt(3, rownumber);
                stmt_2.executeUpdate();

                // 当修改图书对应的书库号时修改书库存书量，比如把图书"java"的书库号由"1"改为"2"，则"1"书库书减少1
                // "2"书库书增加1
                stmt_3.setString(1, BookJTable.selected.toString());
                stmt_3.executeUpdate();
                stmt_4.setString(1, MyModel.aValue);
                stmt_4.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumerOrigin(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        preparedStatement = DButils.getpreStmt(SqlBook.SELECT_BOOK.getName());
        try {
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
        preparedStatement = DButils.getpreStmt(SqlBook.SELECT_BOOK.getName());
        try {
            rs = preparedStatement.executeQuery();
            rs.last();
            row = rs.getRow();
            ResultSetMetaData rsmd = rs.getMetaData();
            column = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
