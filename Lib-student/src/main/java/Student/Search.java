package Student;

import Bean.Pagefunction;
import Book.BookSearchResultJTable;
import Constant.SqlSearch;
import Tools.DButils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author nsu_zk
 * @create 2019-09-01 10:04
 */
public class Search extends JFrame implements Pagefunction {
    private static final long serialVersionUID = 1L;
    public static String bookname;
    public static String ID;
    public static Search search;
    private JPanel jp1;
    private JTextField jtfLoginName;
    private JButton jbu1;
    private PreparedStatement preparedStatement;

    public Search(String ID) {
        Search.ID = ID;
        setTitle("Search");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        newcomponent();
        setcomponent();
        addcomponentto();
        init();
    }

    @Override
    public void init() {
        jbu1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                preparedStatement = DButils.getpreStmt(SqlSearch.SQLSEARCH.getName());
                bookname = jtfLoginName.getText();// 书名
                try {
                    preparedStatement.setString(1, bookname);
                    System.out.println(bookname);
                    ResultSet rs = preparedStatement.executeQuery();
                    if ("".equals(bookname)) {
                        JOptionPane.showMessageDialog(null, "书名不能为空", "提示",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (rs.next()) {
                        // search.setVisible(false);
                        // dispose();
                        new BookSearchResultJTable(bookname, ID).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "没有此书", "提示",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }

            }
        });
    }

    @Override
    public void refresh() {

    }

    @Override
    public void newcomponent() {
        jp1 = new JPanel();
        jtfLoginName = new JTextField();
        jbu1 = new JButton("搜索");
    }

    @Override
    public void setcomponent() {
        jbu1.setSize(60, 50);
        jbu1.setLocation(320, 85);
        jtfLoginName.setSize(200, 50);
        jtfLoginName.setLocation(120, 85);
        jp1.setLayout(null);
    }

    @Override
    public void addcomponentto() {
        add(jp1);
        jp1.add(jtfLoginName);
        jp1.add(jbu1);
    }
}
