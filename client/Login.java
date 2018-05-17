package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.LoginListener;

public class Login {
	public Login(){
		//实例化一个窗体;
		JFrame  frame = new JFrame();
		frame.setTitle("登录界面");
		frame.setLocation(650,450);
		frame.setSize(430,160);
		frame.setDefaultCloseOperation(3);
		
		//实例化westpanel并添加到窗体上
		JPanel westpanel = new JPanel();
		westpanel.setPreferredSize(new Dimension(100,150));
		westpanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		frame.add(westpanel, BorderLayout.WEST);
		
		//实例化panel并添加到窗体上
		JPanel panel = new JPanel();
		//panel.setBackground(Color.white);
		frame.add(panel, BorderLayout.CENTER);
		
		
		//实例化组件并添加到westpanel上
	    JLabel label2 = new JLabel(new ImageIcon("C:\\Users\\lenovo-pc\\Pictures\\表情包\\21.jpg"));
	    westpanel.add(label2);
	    
	    //实例化组件并添加到panel上
	    JLabel accountLabel = new JLabel("账号:");
	    panel.add(accountLabel);
	    JTextField accountText = new JTextField();
	    Dimension dim1 = new Dimension(240,30);
	    accountText.setPreferredSize(dim1);
	    panel.add(accountText);
	    javax.swing.JLabel pwLabel = new javax.swing.JLabel("密码:");
	    panel.add(pwLabel);
	    JPasswordField pwText = new JPasswordField();
	    Dimension dim2 = new Dimension(240,30);
	    pwText.setPreferredSize(dim2);
	    panel.add(pwText);
	    
	    JButton button2 = new JButton("登录");
	    Dimension bDim = new Dimension(150,25);
	    button2.setPreferredSize(bDim);
	    panel.add(button2);
	    frame.setVisible(true);
	    //给登录按钮增加监听
	    LoginListener lis = new LoginListener(frame,accountText,pwText);
	    button2.addActionListener(lis);
	}
}
