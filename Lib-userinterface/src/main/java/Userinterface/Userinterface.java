package Userinterface;

import Aministrator.Administrator;
import Bean.Pagefunction;
import Constant.SqlUserinterface;
import Student.Student;
import Tools.DButils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author nsu_zk
 * @create 2019-08-21 13:43
 */
public class Userinterface extends JFrame implements Pagefunction {
    private JLabel labTitle; // 标题
    private Font Titlefont; // 标题字体
    private Font Optionsfont; // 操作选项字体
    private JButton btnSure; // 登录键按钮
    private JButton btnSure1; // 注册键按钮
    private JPanel panBtn; // 放置按钮组件的JPanel

    private JLabel labLoginName; // 登录名
    private JLabel labPWD; // 登录密码
    public static JTextField jtfLoginName; // 登录名域
    public static JPasswordField jpfPWD; // 登录密码域
    public static String ID;
    public static String pwd;
    private JPanel panMain; // 放置JLabel、JTextField、JPasswordField组件的JPanel

    public Userinterface() {
        setTitle("Userinterface");
        setSize(550, 330);
        // 设置窗口相对于指定组件的位置。
        // 如果组件当前未显示或者为 null，则此窗口将置于屏幕的中央。
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        newcomponent();
        setcomponent();
        addcomponentto();
        init();
    }

    @Override
    public void newcomponent() {
        labTitle = new JLabel("图书管理系统");
        Titlefont = new Font("隶书", Font.BOLD, 40);
        Optionsfont = new Font("TimesRoman", Font.BOLD, 15);
        btnSure = new JButton("登录");
        btnSure1 = new JButton("注册");
        panBtn = new JPanel();
        labLoginName = new JLabel("账号:");
        labPWD = new JLabel("密码:");
        jtfLoginName = new JTextField();
        jpfPWD = new JPasswordField();
        panMain = new JPanel();
    }

    @Override
    public void init() {
        btnSure1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Statement stmt = DButils.getStmt();
                ID = jtfLoginName.getText();// 用户名
                pwd = new String(jpfPWD.getPassword()).trim();
                String Studentsql = "insert into student values('" + ID + "','"
                        + pwd + "','0','0')";
                try {
                    stmt.executeUpdate(Studentsql);
                    JOptionPane.showMessageDialog(null, "学生注册成功", "提示",
                            JOptionPane.INFORMATION_MESSAGE);
                    refresh();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, "此用户已存在", "提示",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSure.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 获得两个sql的预编译PreparedStatement
                PreparedStatement preparedStatement_ad = DButils.getpreStmt(SqlUserinterface.ADMINISTRATORSQL.getName());
                PreparedStatement preparedStatement_st = DButils.getpreStmt(SqlUserinterface.STUDENTSQL.getName());
                ID = jtfLoginName.getText();// 用户名
                pwd = new String(jpfPWD.getPassword());// 密码
//                String Administratorsql = "select * from Administrator where StudentId='"
//                        + ID + "'AND Password='" + pwd + "';";
//                String Studentsql = "select * from Student where StudentId='"
//                        + ID + "'AND Password='" + pwd + "';";
                try {
                    preparedStatement_ad.setString(1, ID);
                    preparedStatement_ad.setString(2, pwd);
                    preparedStatement_st.setString(1, ID);
                    preparedStatement_st.setString(2, pwd);
                    ResultSet resultSet_ad = preparedStatement_ad.executeQuery();
                    ResultSet resultSet_st = preparedStatement_st.executeQuery();
                    if ("".equals(ID) || "".equals(pwd)) {
                        JOptionPane.showMessageDialog(null, "賬號和密码不能为空", "提示",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    // 在resultSet_ad找到如输入对应的值，证明是管理员账号
                    else if (resultSet_ad.next()) {
                        JOptionPane.showMessageDialog(null, "管理员登录成功", "提示",
                                JOptionPane.INFORMATION_MESSAGE);
                        // 跳转到管理员账户界面
                        new Administrator().setVisible(true);
                    } else {
                        try {
                            if ("".equals(ID) || "".equals(pwd)) {
                                JOptionPane.showMessageDialog(null,
                                        "賬號和密码不能为空", "提示",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            // 在resultSet_st找到如输入对应的值，证明是学生账号
                            else if (resultSet_st.next()) {
                                JOptionPane.showMessageDialog(null, "学生登录成功",
                                        "提示", JOptionPane.INFORMATION_MESSAGE);

                                // 跳转到普通用户界面
                                new Student().setVisible(true);
                            } else {
                                int n = JOptionPane.showConfirmDialog(null,
                                        "不存在此账号", "提示",
                                        JOptionPane.INFORMATION_MESSAGE);
                                if (n == 0) {
                                    refresh();
                                } else if (n == 1 || n == 2) {
                                    System.out.println(ID + "/" + pwd);
                                    System.exit(0);
                                }
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }

                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });

    }

    /**
     * 清空jtfLoginName、jpfPWD域的数据
     */
    @Override
    public void refresh() {
        jtfLoginName.setText("");
        jpfPWD.setText("");
    }

    @Override
    public void setcomponent() {
        labTitle.setFont(Titlefont);
        labTitle.setHorizontalAlignment(JLabel.CENTER);
        // 设置标签内容沿 X 轴的对齐方式。
        labTitle.setForeground(Color.RED);
        labTitle.setOpaque(true);
        labTitle.setBackground(Color.gray);
        labLoginName.setSize(100, 40);// 用户名
        labLoginName.setFont(Optionsfont);
        labLoginName.setLocation(55, 50);
        labLoginName.setHorizontalAlignment(JLabel.RIGHT);
        labPWD.setSize(100, 40);// 密码
        labPWD.setFont(Optionsfont);
        labPWD.setLocation(55, 50 + 40 + 10);
        labPWD.setHorizontalAlignment(JLabel.RIGHT);
        jtfLoginName.setSize(200, 40);
        jtfLoginName.setLocation(55 + 100 + 20, 50);
        jpfPWD.setSize(200, 40);
        jpfPWD.setLocation(55 + 100 + 20, 50 + 40 + 10);

        panBtn.setBackground(Color.gray);
        panMain.setBackground(Color.gray);
        panMain.setLayout(null);
    }

    @Override
    public void addcomponentto() {
        // 添加组件到panBtn
        panBtn.add(btnSure);
        panBtn.add(btnSure1);

        // 添加组件到panMain
        panMain.add(labLoginName);
        panMain.add(labPWD);
        panMain.add(jtfLoginName);
        panMain.add(jpfPWD);

        add(labTitle, BorderLayout.NORTH);// 将labTitle放在全局界面上方
        add(panBtn, BorderLayout.SOUTH); // 将panBtn放在全局界面下方
        add(panMain, BorderLayout.CENTER);// 将panMain放在全局界面中间
    }

    public static void main(String[] args) {
        Userinterface userinterface = new Userinterface();
        userinterface.setVisible(true);
    }
}

