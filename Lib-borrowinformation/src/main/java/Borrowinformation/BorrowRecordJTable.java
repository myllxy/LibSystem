package Borrowinformation;

import Bean.Pagefunction;
import Constant.SqlInformation;
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
 * 管理员管理借阅信息
 */
public class BorrowRecordJTable extends JFrame implements Pagefunction {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Font font;
    private JScrollPane jsp;
    private JTable table;// MVC Model View Controller
    static int row;
    static String getnumber;

    public BorrowRecordJTable() {
        setTitle("BorrowRecordJTable");
        setSize(500, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        newcomponent();
        setcomponent();
        addcomponentto();
        init();

    }

    public static void main(String[] args) {
        new BorrowRecordJTable().setVisible(true);
    }

    @Override
    public void init() {
        table.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    JPopupMenu popMenu = new JPopupMenu();
                    JMenuItem delMenItem = new JMenuItem();
                    popMenu.add(delMenItem);
                    final JTable table = (JTable) e.getComponent();
                    // 获取鼠标右键选中的行
                    int row = table.rowAtPoint(e.getPoint());
                    if (row == -1) {
                        return;
                    }
                    final String ID = table.getValueAt(row, 0).toString();
                    delMenItem.setText("删除該行");
                    delMenItem.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // "delete from borrowinformation where Number=?;"
                            PreparedStatement stmt_1 = DButils.getpreStmt(SqlInformation.SQLINFORMATION.getName());
                            // "update student set Notreturned=Notreturned-1 where StudentId=?;"
                            PreparedStatement stmt_2 = DButils.getpreStmt(SqlInformation.SQLSTUDENT.getName());
                            try {
                                stmt_1.setString(1, getnumber);
                                stmt_1.executeUpdate();
                                stmt_2.setString(1, ID);
                                stmt_2.executeUpdate();
                                table.setModel(new MyModel());
                                JOptionPane.showMessageDialog(null, "书籍已归还", "提示",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                    });
                    // 获取已选中的行
                    int[] rows = table.getSelectedRows();
                    getnumber = table.getValueAt(row, 2).toString();
                    boolean inSelected = false;
                    // 判断当前右键所在行是否已选中
                    for (int r : rows) {
                        if (row == r) {
                            inSelected = true;
                            break;
                        }
                    }
                    // 当前鼠标右键点击所在行不被选中则高亮显示选中行
                    if (!inSelected) {
                        table.setRowSelectionInterval(row, row);
                    }
                    // 生成右键菜单
                    popMenu.show(e.getComponent(), e.getX(), e.getY());
                }

            }

        });
    }

    @Override
    public void refresh() {

    }

    @Override
    public void newcomponent() {
        font = new Font("comic sans ms", Font.BOLD, 15);
        jsp = new JScrollPane();
        table = new JTable();// MVC Model View Controller
    }

    @Override
    public void setcomponent() {
        table.getTableHeader().setFont(font);

        table.setRowHeight(30);
        table.setModel(new MyModel());
        jsp.setViewportView(table);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    @Override
    public void addcomponentto() {
        this.add(jsp);
    }
}

class MyModel extends AbstractTableModel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int column;
    private PreparedStatement stmt;
    private ResultSet rs;
    static String[] n = {"StudentId", "Bookame", "Number", "Stackroom",
            "Borrowtime"};

    private String sql = SqlInformation.SQLSELECT.getName();

    public MyModel() {

        stmt = DButils.getpreStmt(sql);
        try {
            rs = stmt.executeQuery();
            rs.last();
            BorrowRecordJTable.row = rs.getRow();
            ResultSetMetaData rsmd = rs.getMetaData();
            // 使用 rsmd 查找 rs 有多少列
            column = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getColumnName(int column) {
        return n[column];
    }

    @Override
    public int getRowCount() {
        return BorrowRecordJTable.row;
    }

    @Override
    public int getColumnCount() {
        return column;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        try {
            rs = stmt.executeQuery();
            rs.absolute(rowIndex + 1);
            value = rs.getString(columnIndex + 1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

}
