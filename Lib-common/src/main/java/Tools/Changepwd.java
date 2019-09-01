package Tools;

import Bean.Pagefunction;
import Constant.SqlChangepwd;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * 用于修改student和administrator的密码
 */
public class Changepwd extends JFrame implements Pagefunction {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel jp1;
    private JLabel pwdJLabel_1;
    private JLabel pwdJLabel_2;
    private JLabel pwdJLabel_3;
    private JTextField pwdField_1;
    private JTextField pwdField_2;
    private JTextField pwdField_3;
    private JButton jbu1;
    private String User;
    private PreparedStatement preparedStatement;

    public Changepwd(String User) {
        this.User = User;
        setTitle("Changepwd");
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
                String pwd_1 = pwdField_1.getText();
                String pwd_2 = pwdField_2.getText();
                String pwd_3 = pwdField_3.getText();
//                String sql = "update " + User + " set Password= '" + pwd_2
//                        + "' where Password='" + pwd_1 + "';";

                try {
                    if (User.equals("student")) {
                        preparedStatement = DButils.getpreStmt(SqlChangepwd.SQLCHANGEPWD_ST.getName());
                        preparedStatement.setString(1, pwd_2);
                        preparedStatement.setString(2, pwd_1);
                    } else if (User.equals("administrator")) {
                        preparedStatement = DButils.getpreStmt(SqlChangepwd.SQLCHANGEPWD_AD.getName());
                        preparedStatement.setString(1, pwd_2);
                        preparedStatement.setString(2, pwd_1);
                    } else {
                        refresh();
                        throw new Exception("只存在student和administrator用户");
                    }
                    if (!pwd_2.equals(pwd_3)) {
                        JOptionPane.showMessageDialog(null, "再次輸入密碼錯誤", "提示",
                                JOptionPane.ERROR_MESSAGE);
                        refresh();
                    } else {
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "修改密碼成功", "提示",
                                JOptionPane.INFORMATION_MESSAGE);
                        refresh();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }

            }
        });
    }

    @Override
    public void refresh() {
        pwdField_1.setText("");
        pwdField_2.setText("");
        pwdField_3.setText("");
    }

    @Override
    public void newcomponent() {
        jp1 = new JPanel();
        pwdJLabel_1 = new JLabel("原密码");
        pwdJLabel_2 = new JLabel("新密码");
        pwdJLabel_3 = new JLabel("再输入一次");
        pwdField_1 = new JTextField();
        pwdField_2 = new JTextField();
        pwdField_3 = new JTextField();
        jbu1 = new JButton("確認");
    }

    @Override
    public void setcomponent() {
        jbu1.setSize(60, 30);
        jbu1.setLocation(220, 175);
        pwdField_1.setSize(225, 35);
        pwdField_1.setLocation(145, 45);
        pwdField_2.setSize(225, 35);
        pwdField_2.setLocation(145, 85);
        pwdField_3.setSize(225, 35);
        pwdField_3.setLocation(145, 125);
        pwdJLabel_1.setSize(120, 40);
        pwdJLabel_1.setLocation(20, 43);
        pwdJLabel_1.setHorizontalAlignment(JLabel.RIGHT);
        pwdJLabel_2.setSize(120, 40);
        pwdJLabel_2.setLocation(20, 83);
        pwdJLabel_2.setHorizontalAlignment(JLabel.RIGHT);
        pwdJLabel_3.setSize(120, 40);
        pwdJLabel_3.setLocation(20, 121);
        pwdJLabel_3.setHorizontalAlignment(JLabel.RIGHT);
        jp1.setLayout(null);
    }

    @Override
    public void addcomponentto() {
        add(jp1);
        jp1.add(pwdField_1);
        jp1.add(pwdField_2);
        jp1.add(pwdField_3);
        jp1.add(pwdJLabel_1);
        jp1.add(pwdJLabel_2);
        jp1.add(pwdJLabel_3);
        jp1.add(jbu1);
    }
}
