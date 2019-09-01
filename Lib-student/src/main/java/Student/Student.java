package Student;

import Bean.Pagefunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author nsu_zk
 * @create 2019-08-30 20:37
 */
public class Student extends JFrame implements Pagefunction {
    private static final long serialVersionUID = 1L;
    private JLabel labTitle; // 设置标签
    private Font Titlefont; // 设置标题字体
    private Font buttonfont; // 设置按键字体
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private String ID;

    private JPanel panbtn; // 放置button的主JPanel

    public Student(String ID) {
        this.ID = ID;
        setTitle("Student"); // 设置标题
        setSize(500, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        newcomponent();
        setcomponent();
        addcomponentto();
        init();

    }

    public void init() {
        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                new Changepwd("student").setVisible(true);

            }
        });
        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                new Search().setVisible(true);
            }
        });
        btn3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Studentinformation(ID).setVisible(true);
            }
        });
    }

    public void refresh() {

    }

    public void newcomponent() {
        labTitle = new JLabel("欢迎登录图书管理系统");
        Titlefont = new Font("隶书", Font.BOLD, 40);
        buttonfont = new Font("TimesRoman", Font.BOLD, 15);
        btn1 = new JButton("修改密码");
        btn2 = new JButton("借阅图书");
        btn3 = new JButton("个人信息");
        panbtn = new JPanel();
    }

    public void setcomponent() {
        panbtn.setLayout(null);
        labTitle.setOpaque(true);
        labTitle.setFont(Titlefont);
        labTitle.setHorizontalAlignment(JLabel.CENTER);
        // 设置标签内容沿 X 轴的对齐方式。
        labTitle.setForeground(Color.RED);

        btn1.setSize(125, 250);
        btn1.setLocation(30, 15);
        btn1.setFont(buttonfont);

        btn2.setSize(125, 250);
        btn2.setLocation(180, 15);
        btn2.setFont(buttonfont);

        btn3.setSize(125, 250);
        btn3.setLocation(330, 15);
        btn3.setFont(buttonfont);
    }

    public void addcomponentto() {
        add(panbtn);
        add(labTitle, BorderLayout.NORTH);
        panbtn.add(btn1);
        panbtn.add(btn2);
        panbtn.add(btn3);
    }
}
