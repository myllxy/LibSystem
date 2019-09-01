package Student;

import Bean.Pagefunction;
import Constant.SqlStudent;
import Tools.DButils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.sql.*;

/**
 * @author nsu_zk
 * @create 2019-08-31 23:09
 */
public class Studentinformation extends JFrame implements Pagefunction {
    private static final long serialVersionUID = 1L;
    Font font;
    private JScrollPane jsp;
    private JTable table;// MVC Model View Controller
    static int row;
    private String ID;

    public Studentinformation(String ID) {
        this.ID = ID;
        setTitle("Studentinformation");
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

    }

    @Override
    public void refresh() {

    }

    @Override
    public void newcomponent() {
        font = new Font("comic sans ms", Font.BOLD, 15);
        jsp = new JScrollPane();
        table = new JTable();
    }

    @Override
    public void setcomponent() {

        table.getTableHeader().setFont(font);
        table.setGridColor(Color.BLUE);
        table.setRowHeight(30);
        table.setModel(new MyModel(ID));
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
    private PreparedStatement preparedStatement;
    private ResultSet rs;
    static String[] n = {"StudentId", "Password", "Totallend",
            "Notreturned"};
    private String ID;

    private String sql = SqlStudent.STUDENTSQL.getName();

    public MyModel(String ID) {
        this.ID = ID;
        preparedStatement = DButils.getpreStmt(sql);
        try {
            preparedStatement.setString(1, ID);
            rs = preparedStatement.executeQuery();
            rs.last();
            Studentinformation.row = rs.getRow();
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
        return Studentinformation.row;
    }

    @Override
    public int getColumnCount() {
        return column;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        try {
            preparedStatement = DButils.getpreStmt(sql);
            preparedStatement.setString(1, ID);
            rs = preparedStatement.executeQuery();
            rs.absolute(rowIndex + 1);
            value = rs.getString(columnIndex + 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

}
