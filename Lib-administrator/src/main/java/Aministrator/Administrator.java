package Aministrator;

import Bean.Pagefunction;
import Book.BookJTable;
import Book.Stackroom;
import Borrowinformation.BorrowRecordJTable;
import Tools.Changepwd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author nsu_zk
 * @create 2019-08-30 19:55
 */
public class Administrator extends JFrame implements Pagefunction {

    private static final long serialVersionUID = 1L;
    private JLabel labTitle; // 标签
    private Font Titlefont; // 标题字体
    private Font buttonfont; // 按键字体
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;

    private JPanel panbtn; // 放置button的主JPanel

    public Administrator() {
        // 设置标题
        setTitle("Administrator");
        setSize(620, 380);
        setLocationRelativeTo(null);
        // 设置窗口相对于指定组件的位置。
        // 如果组件当前未显示或者为 null，则此窗口将置于屏幕的中央。
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
                new BookJTable().setVisible(true);
            }
        });
        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Changepwd("administrator").setVisible(true);

            }
        });
        btn3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new BorrowRecordJTable().setVisible(true);

            }
        });
        btn4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Stackroom().setVisible(true);

            }
        });
    }

    public void refresh() {

    }

    public void newcomponent() {
        labTitle = new JLabel("欢迎登录图书管理系统");
        Titlefont = new Font("隶书", Font.BOLD, 40);
        buttonfont = new Font("TimesRoman", Font.BOLD, 20);
        btn1 = new JButton("管理图书");
        btn2 = new JButton("修改密码");
        btn3 = new JButton("借阅管理");
        btn4 = new JButton("查看书库");
        panbtn = new JPanel();
    }

    public void setcomponent() {
        panbtn.setLayout(null);
        labTitle.setOpaque(true);
        labTitle.setFont(Titlefont);
        labTitle.setHorizontalAlignment(JLabel.CENTER);
        // 设置标签内容沿 X 轴的对齐方式。
        labTitle.setForeground(Color.RED);
        btn1.setSize(130, 250);
        btn1.setLocation(10, 15);
        btn1.setFont(buttonfont);

        btn2.setSize(130, 250);
        btn2.setLocation(160, 15);
        btn2.setFont(buttonfont);

        btn3.setSize(130, 250);
        btn3.setLocation(310, 15);
        btn3.setFont(buttonfont);

        btn4.setSize(130, 250);
        btn4.setLocation(460, 15);
        btn4.setFont(buttonfont);
    }

    public void addcomponentto() {
        // 添加panbtn到主界面
        add(panbtn);
        // 添加标题
        add(labTitle, BorderLayout.NORTH);
        // 添加按钮
        panbtn.add(btn1);
        panbtn.add(btn2);
        panbtn.add(btn3);
        panbtn.add(btn4);

    }
}
