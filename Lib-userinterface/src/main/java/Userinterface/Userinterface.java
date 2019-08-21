package Userinterface;

import Bean.Pagefunction;
import Tools.DButils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author nsu_zk
 * @create 2019-08-21 13:43
 */
public class Userinterface extends JFrame implements Pagefunction {
    private JLabel labTitle; // ����
    private Font Titlefont; // ��������
    private Font Optionsfont; // ����ѡ������
    private JButton btnSure; // ��¼����ť
    private JButton btnSure1; // ע�����ť
    private JPanel panBtn; // ���ð�ť�����JPanel

    private JLabel labLoginName; // ��¼��
    private JLabel labPWD; // ��¼����
    public static JTextField jtfLoginName; // ��¼����
    public static JPasswordField jpfPWD; // ��¼������
    public static String ID;
    public static String pwd;
    private JPanel panMain; // ����JLabel��JTextField��JPasswordField�����JPanel

    public Userinterface() {
        setTitle("Userinterface");
        setSize(550, 330);
        // ���ô��������ָ�������λ�á�
        // ��������ǰδ��ʾ����Ϊ null����˴��ڽ�������Ļ�����롣
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        newcomponent();
        setcomponent();
        addcomponentto();
        init();
    }
    @Override
    public void newcomponent() {
        labTitle = new JLabel("ͼ�����ϵͳ");
        Titlefont = new Font("����", Font.BOLD, 40);
        Optionsfont = new Font("TimesRoman", Font.BOLD, 15);
        btnSure = new JButton("��¼");
        btnSure1 = new JButton("ע��");
        panBtn = new JPanel();
        labLoginName = new JLabel("�˺�:");
        labPWD = new JLabel("����:");
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
                ID = jtfLoginName.getText();// �û���
                pwd = new String(jpfPWD.getPassword()).trim();
                String Studentsql = "insert into student values('" + ID + "','"
                        + pwd + "','0','0')";
                try {
                    stmt.executeUpdate(Studentsql);
                    JOptionPane.showMessageDialog(null, "ѧ��ע��ɹ�", "��ʾ",
                            JOptionPane.INFORMATION_MESSAGE);
                    refresh();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, "���û��Ѵ���", "��ʾ",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnSure.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Statement stmt = DButils.getStmt();
                ID = jtfLoginName.getText();// �û���
                pwd = new String(jpfPWD.getPassword()).trim();// ����
                String Administratorsql = "select * from Administrator where StudentId='"
                        + ID + "'AND Password='" + pwd + "';";
                String Studentsql = "select * from Student where StudentId='"
                        + ID + "'AND Password='" + pwd + "';";
                try {
                    ResultSet rs = stmt.executeQuery(Administratorsql);
                    if ("".equals(ID) || "".equals(pwd)) {
                        JOptionPane.showMessageDialog(null, "�~̖�����벻��Ϊ��", "��ʾ",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "����Ա��¼�ɹ�", "��ʾ",
                                JOptionPane.INFORMATION_MESSAGE);
//                        new Administrator().setVisible(true);
                    } else {
                        try {
                            ResultSet rs1 = stmt.executeQuery(Studentsql);
                            if ("".equals(ID) || "".equals(pwd)) {
                                JOptionPane.showMessageDialog(null,
                                        "�~̖�����벻��Ϊ��", "��ʾ",
                                        JOptionPane.ERROR_MESSAGE);
                            } else if (rs1.next()) {
                                JOptionPane.showMessageDialog(null, "ѧ����¼�ɹ�",
                                        "��ʾ", JOptionPane.INFORMATION_MESSAGE);
//                                new Student().setVisible(true);
                            } else {
                                int n = JOptionPane.showConfirmDialog(null,
                                        "�����ڴ��˺�", "��ʾ",
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
     * ���jtfLoginName��jpfPWD�������
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
        // ���ñ�ǩ������ X ��Ķ��뷽ʽ��
        labTitle.setForeground(Color.RED);
        labTitle.setOpaque(true);
        labTitle.setBackground(Color.gray);
        labLoginName.setSize(100, 40);// �û���
        labLoginName.setFont(Optionsfont);
        labLoginName.setLocation(55, 50);
        labLoginName.setHorizontalAlignment(JLabel.RIGHT);
        labPWD.setSize(100, 40);// ����
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
        // ��������panBtn
        panBtn.add(btnSure);
        panBtn.add(btnSure1);

        // ��������panMain
        panMain.add(labLoginName);
        panMain.add(labPWD);
        panMain.add(jtfLoginName);
        panMain.add(jpfPWD);

        add(labTitle, BorderLayout.NORTH);// ��labTitle����ȫ�ֽ����Ϸ�
        add(panBtn, BorderLayout.SOUTH); // ��panBtn����ȫ�ֽ����·�
        add(panMain, BorderLayout.CENTER);// ��panMain����ȫ�ֽ����м�
    }

    public static void main(String[] args) {
        Userinterface userinterface = new Userinterface();
        userinterface.init();
        userinterface.setVisible(true);
    }
}

