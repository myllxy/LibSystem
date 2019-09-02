package Book;

import Bean.Modelfunction;
import Bean.Pagefunction;
import Constant.SqlStackrom;
import Tools.DButils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.sql.*;

/**
 * @author nsu_zk
 * @create 2019-09-02 9:52
 */
public class Stackroom extends JFrame implements Pagefunction {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private JScrollPane jsp;
    Font font;

    public Stackroom() {
        setTitle("Stackroom");
        setSize(500, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    @Override
    public void init() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void newcomponent() {
        table = new JTable();
        jsp = new JScrollPane();
        font = new Font("comic sans ms", Font.BOLD, 15);
    }

    @Override
    public void setcomponent() {
        table.getTableHeader().setFont(font);
        table.setRowHeight(30);
        table.setModel(new MyModel_1());
        jsp.setViewportView(table);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    @Override
    public void addcomponentto() {
        this.add(jsp);
    }
}

class MyModel_1 extends AbstractTableModel implements Modelfunction {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    static String[] n = {"Stackroomnumber", "Bookamount"};
    private PreparedStatement stmt;
    private ResultSet rs;
    static int row;
    static int column;
    private String sql = SqlStackrom.SELECT_STACKROOM_1.getName();

    public MyModel_1() {
        execute();
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return row;
    }

    @Override
    public String getColumnName(int column) {
        return n[column];
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return column;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        try {
            stmt = DButils.getpreStmt(sql);
            rs = stmt.executeQuery();
            rs.absolute(rowIndex + 1);
            value = rs.getString(columnIndex + 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public void execute() {
        PreparedStatement stmt = DButils.getpreStmt(sql);
        try {
            rs = stmt.executeQuery();
            rs.last();
            row = rs.getRow();
            ResultSetMetaData rsmd = rs.getMetaData();
            column = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
