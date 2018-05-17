package client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import server.DES;

public class Operation {

	// 获取已经连接好的Socket值
	public Socket s;

	public void SetSocket(Socket s) {
		this.s = s;
	}

	// 客户端程序入口
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				try {
					UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				@SuppressWarnings("unused")
				Login login = new Login();
			}
		});
	}

	public Operation() throws Exception {
		// 实例化窗口
		javax.swing.JFrame frame = new javax.swing.JFrame();

		frame.setLayout(null);
		frame.setTitle("通讯录");
		frame.setLocation(500, 150);
		frame.setSize(550, 450);
		frame.setDefaultCloseOperation(3);

		JMenuBar bar = new JMenuBar();
		JMenu menuck = new JMenu("查看");
		JMenu menubj = new JMenu("编辑");
		JMenu menubf = new JMenu("备份");
		JMenu menugx = new JMenu("更新");
		JMenu menubz = new JMenu("帮助");
		JMenuItem itemcxmz = new JMenuItem("按名称查看");
		JMenuItem itemcxdh = new JMenuItem("按电话查看");
		JMenuItem itemzj = new JMenuItem("增加");
		JMenuItem itemsc = new JMenuItem("删除");
		JMenuItem itemxg = new JMenuItem("修改");
		JMenuItem itembf = new JMenuItem("备份");
		JMenuItem itemgx = new JMenuItem("更新");
		JMenuItem itembz = new JMenuItem("关于此系统的使用");
		menubj.add(itemzj);
		menubj.add(itemsc);
		menubj.add(itemxg);
		menuck.add(itemcxmz);
		menuck.add(itemcxdh);
		menubf.add(itembf);
		menugx.add(itemgx);
		menubz.add(itembz);
		bar.add(menuck);
		bar.add(menubj);
		bar.add(menubf);
		bar.add(menugx);
		bar.add(menubz);
		frame.setJMenuBar(bar);

		// 实例化欢迎面板
		JPanel Welcome = new JPanel();
		Welcome.setBounds(0, 0, 549, 449);
		Welcome.setLayout(null);
		frame.add(Welcome);

		// 实例化查询显示面板
		JPanel CXPanel = new JPanel();
		CXPanel.setLayout(null);
		CXPanel.setBounds(10, 10, 550, 450);
		CXPanel.setBackground(Color.white);

		// 实例化添加显示面板
		JPanel TJPanel = new JPanel();
		TJPanel.setLayout(null);
		TJPanel.setBounds(10, 10, 550, 450);
		TJPanel.setBackground(Color.white);

		// 实例化删除显示面板
		JPanel SCPanel = new JPanel();
		SCPanel.setLayout(null);
		SCPanel.setBounds(10, 10, 550, 450);
		SCPanel.setBackground(Color.white);

		// 实例化修改显示面板
		JPanel XGPanel = new JPanel();
		XGPanel.setLayout(null);
		XGPanel.setBounds(10, 10, 550, 450);
		XGPanel.setBackground(Color.white);

		// 实例化备份面板
		JPanel BFPanel = new JPanel();
		BFPanel.setLayout(null);
		BFPanel.setBounds(10, 10, 550, 450);
		BFPanel.setBackground(Color.white);

		// 实例化更新面板
		JPanel GXPanel = new JPanel();
		GXPanel.setLayout(null);
		GXPanel.setBounds(10, 10, 550, 450);
		GXPanel.setBackground(Color.white);

		// 实例化欢迎面板组件欢迎文本框并添加
		JLabel label1 = new JLabel(new ImageIcon("C:\\Users\\lenovo-pc\\Pictures\\电脑壁纸\\31.jpg"));
		label1.setBounds(0, 0, 550, 450);
		Welcome.add(label1);

		// 实例化帮助面板
		JPanel BZPanel = new JPanel();
		BZPanel.setBounds(10, 10, 550, 450);
		BZPanel.setBackground(Color.white);

		// 实例化查询界面组件并添加
		JTextArea CXText = new JTextArea(
				"请在下方输入文本框各中输入想要查询的联系人信息" + "\n" + "（可输入为联系人姓名或电话）" + "\n" + "若查询所有联系人信息，请直接点击下方查询按钮" + "\n");
		JScrollPane jsp = new JScrollPane(CXText);
		jsp.setBounds(10, 10, 500, 280);
		JTextField InputText1 = new JTextField();
		InputText1.setBounds(10, 310, 270, 30);
		JButton QR1 = new JButton("查询");
		QR1.setBounds(310, 310, 120, 30);
		CXPanel.add(jsp);
		CXPanel.add(InputText1);
		CXPanel.add(QR1);

		// 实例化添加面板组件并添加
		JTextArea TJText = new JTextArea("请在下方的文本框输入想要添加的联系人信息" + "\n" + "然后单击添加按钮" + "\n");
		TJText.setBounds(10, 5, 460, 80);
		JLabel name = new JLabel("姓名: ");
		name.setBounds(10, 120, 60, 30);
		JTextField nametext = new JTextField();
		nametext.setBounds(80, 120, 210, 30);
		JLabel tel = new JLabel("电话: ");
		tel.setBounds(10, 170, 60, 30);
		JTextField teltext = new JTextField();
		teltext.setBounds(80, 170, 210, 30);
		JLabel add = new JLabel("地址: ");
		add.setBounds(10, 220, 60, 30);
		JTextField addtext = new JTextField();
		addtext.setBounds(80, 220, 210, 30);
		JLabel qq = new JLabel("Q Q: ");
		qq.setBounds(10, 270, 60, 30);
		JTextField qqtext = new JTextField();
		qqtext.setBounds(80, 270, 210, 30);
		JButton QR2 = new JButton("添加");
		QR2.setBounds(320, 195, 120, 30);
		JTextArea nullarea = new JTextArea();
		nullarea.setBounds(10, 320, 460, 40);
		TJPanel.add(TJText);
		TJPanel.add(name);
		TJPanel.add(nametext);
		TJPanel.add(tel);
		TJPanel.add(teltext);
		TJPanel.add(add);
		TJPanel.add(addtext);
		TJPanel.add(qq);
		TJPanel.add(qqtext);
		TJPanel.add(nullarea);
		TJPanel.add(QR2);

		// 实例化删除面板组件并添加
		JTextArea SCText = new JTextArea("请在下方文本框中输入想要删除的联系人姓名" + "\n");
		SCText.setBounds(10, 10, 400, 80);
		JTextArea SCWCText = new JTextArea();
		SCWCText.setBounds(10, 200, 400, 80);
		JTextField InputText3 = new JTextField();
		InputText3.setBounds(10, 120, 210, 30);
		JButton QR3 = new JButton("删除");
		QR3.setBounds(260, 120, 120, 30);
		SCPanel.add(SCText);
		SCPanel.add(InputText3);
		SCPanel.add(QR3);
		SCPanel.add(SCWCText);

		// 实例化修改面板组件并添加

		JTextArea XGText = new JTextArea("请在下方的文本框输入想要修改的联系人名称" + "\n");
		XGText.setBounds(10, 5, 460, 50);
		JLabel xgname = new JLabel("名称: ");
		xgname.setBounds(10, 65, 60, 30);
		JTextField Inputxgname = new JTextField();
		Inputxgname.setBounds(80, 65, 210, 30);
		JTextArea XG1Text = new JTextArea("请在下方的文本框输入修改的联系人信息" + "\n");
		XG1Text.setBounds(10, 110, 460, 50);
		JLabel xgtel = new JLabel("电话: ");
		xgtel.setBounds(10, 170, 60, 30);
		JTextField xgteltext = new JTextField();
		xgteltext.setBounds(80, 170, 210, 30);
		JLabel xgadd = new JLabel("地址: ");
		xgadd.setBounds(10, 220, 60, 30);
		JTextField xgaddtext = new JTextField();
		xgaddtext.setBounds(80, 220, 210, 30);
		JLabel xgqq = new JLabel("Q Q: ");
		xgqq.setBounds(10, 270, 60, 30);
		JTextField xgqqtext = new JTextField();
		xgqqtext.setBounds(80, 270, 210, 30);
		JButton QRxg = new JButton("修改");
		QRxg.setBounds(320, 220, 120, 30);
		JTextArea xgwc = new JTextArea();
		xgwc.setBounds(10, 320, 460, 40);
		XGPanel.add(XGText);
		XGPanel.add(XG1Text);
		XGPanel.add(Inputxgname);
		XGPanel.add(xgname);
		XGPanel.add(xgtel);
		XGPanel.add(xgteltext);
		XGPanel.add(xgadd);
		XGPanel.add(xgaddtext);
		XGPanel.add(xgqq);
		XGPanel.add(xgqqtext);
		XGPanel.add(xgwc);
		XGPanel.add(QRxg);
		// 实例化备份面板组件备份文本框并添加
		JTextArea BFText = new JTextArea("请稍候，正在备份！" + "\n");
		BFText.setBounds(10, 10, 400, 80);
		BFPanel.add(BFText);

		// 实例化更新面板组件更新文本框并添加
		JTextArea GXText = new JTextArea("请稍候，正在更新！" + "\n");
		GXText.setBounds(10, 10, 400, 80);
		GXPanel.add(GXText);
		frame.setVisible(true);

		// 实例化帮助面板组件更新文本框并添加
		JTextArea BZText = new JTextArea("查询功能分为按名称查询和按电话查询" + "\n\n\n" + "编辑功能包括增加联系人、删除联系人以及修改联系人" + "\n\n\n"
				+ "备份功能可将本地通讯录备份至数据库中" + "\n\n\n" + "更新功能可将数据库中最新信息更新至本地通讯录中" + "\n\n\n" + "数据库中信息均已加密，请放心使用！" + "\n");
		BZText.setBounds(10, 10, 400, 80);
		BZPanel.add(BZText);
		frame.setVisible(true);
		// 设置查询按钮的监听
		itemcxmz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(Welcome);
				frame.remove(TJPanel);
				frame.remove(XGPanel);
				frame.remove(SCPanel);
				frame.remove(BFPanel);
				frame.remove(GXPanel);
				frame.remove(BZPanel);
				frame.add(CXPanel);
				CXText.setText("请在下方输入文本框中输入想要查询的联系人名称" + "\n" + "若查询所有联系人信息，请直接点击下方查询按钮" + "\n");
				frame.revalidate();
				frame.repaint();
			}
		});
		itemcxdh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(Welcome);
				frame.remove(TJPanel);
				frame.remove(XGPanel);
				frame.remove(SCPanel);
				frame.remove(BFPanel);
				frame.remove(GXPanel);
				frame.remove(BZPanel);
				frame.add(CXPanel);

				CXText.setText("请在下方输入文本框中输入想要查询的联系人电话" + "\n" + "若查询所有联系人信息，请直接点击下方查询按钮" + "\n");
				frame.revalidate();
				frame.repaint();
			}
		});
		QR1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 在本地文件中查询通讯录信息
					File f = new File("D:\\通讯录.txt");
					FileReader fr = new FileReader(f);
					BufferedReader br = new BufferedReader(fr);
					FileReader fr2 = new FileReader(f);
					BufferedReader br2 = new BufferedReader(fr2);
					String str = InputText1.getText();
					if (str.equals("")) {
						CXText.append("\n" + "全部的联系人信息是：" + "\n");
						while (br2.ready()) {
							String string = br.readLine();
							String str1 = string.split(" ")[0];
							String str2 = string.split(" ")[1];
							String str3 = string.split(" ")[2];
							String str4 = string.split(" ")[3];
							CXText.append("姓名: " + str1 + "\n" + "电话: " + str2 + "\n" + "地址: " + str3 + "\n" + "QQ: "
									+ str4 + "\n\n");
						}
						br2.close();
					} else {
						while (br.ready()) {
							String string = br.readLine();
							String str1 = string.split(" ")[0];
							String str2 = string.split(" ")[1];
							String str3 = string.split(" ")[2];
							String str4 = string.split(" ")[3];
							if (InputText1.getText().equals(str1) || InputText1.getText().equals(str2)) {
								CXText.append("\n" + "查询到的联系人信息是：" + "\n");
								CXText.append("姓名: " + str1 + "\n" + "电话: " + str2 + "\n" + "地址: " + str3 + "\n"
										+ "QQ: " + str4 + "\n\n");
								break;
							}
							if (!br.ready())
								CXText.append("\n" + "没有所查的联系人信息，请确认信息是否输入正确" + "\n");
						}
						br.close();
					}
					// 向服务器端传送查询的消息
					OutputStream os = s.getOutputStream();
					OutputStreamWriter osw = new OutputStreamWriter(os);
					PrintWriter pw = new PrintWriter(osw, true);
					pw.println("查询" + " " + InputText1.getText() + "的联系方式");
				} catch (Exception e1) {
				}
				InputText1.setText("");
			}
		});

		// 设置增添按钮的监听
		itemzj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(Welcome);
				frame.remove(CXPanel);
				frame.remove(XGPanel);
				frame.remove(SCPanel);
				frame.remove(BFPanel);
				frame.remove(BZPanel);
				frame.remove(GXPanel);
				frame.add(TJPanel);
				nullarea.setText("");
				frame.revalidate();
				frame.repaint();
			}
		});
		DES des = new DES();

		QR2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TJText.setText("\n" + "请在下方的文本框输入想要添加的联系人信息" + "\n" + "然后单击添加按钮" + "\n");
				try {
					// 在数据库中添加新的联系人
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/addressbook?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");

					System.out.println("Connecting to database...");
					System.out.println("Creating statement...");
					PreparedStatement ps = cn.prepareStatement("insert into people values(?,?,?,?)");
					// 将文本信息加密
					ps.setString(1, des.encrypt(nametext.getText().toString()));
					System.out.println(des.encrypt(nametext.getText().toString()));
					ps.setString(2, des.encrypt(teltext.getText().toString()));
					ps.setString(3, des.encrypt(addtext.getText().toString()));
					ps.setString(4, des.encrypt(qqtext.getText().toString()));
					int rows = ps.executeUpdate();
					System.out.println("Rows impacted : " + rows);
					ps.close();
					cn.close();

					nullarea.setText("已完成添加！" + "\n");
					// 向服务器端传送添加的指令
					OutputStream os = s.getOutputStream();
					OutputStreamWriter osw = new OutputStreamWriter(os);
					PrintWriter pw = new PrintWriter(osw, true);
					pw.println("添加" + " " + nametext.getText());
				} catch (SQLException se) {
					// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {
					// Handle errors for Class.forName
					e1.printStackTrace();
					InputText3.setText("");
				}
				nametext.setText("");
				teltext.setText("");
				addtext.setText("");
				qqtext.setText("");
			}
		});

		// 实例化删除按钮的监听
		itemsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(Welcome);
				frame.remove(CXPanel);
				frame.remove(TJPanel);
				frame.remove(XGPanel);
				frame.remove(BFPanel);
				frame.remove(GXPanel);
				frame.remove(BZPanel);
				frame.add(SCPanel);
				InputText3.setText("");
				SCWCText.setText("");
				frame.revalidate();
				frame.repaint();
			}
		});
		QR3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SCText.setText("请在下方文本框中输入想要删除的联系人姓名" + "\n");
				try {
					// 在数据库中按照姓名删除联系人
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/addressbook?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("Creating statement...");
					PreparedStatement ps = cn.prepareStatement("delete from people where pname=?");
					ps.setString(1, des.encrypt(InputText3.getText().toString()));
					int rows = ps.executeUpdate();
					System.out.println("Rows impacted : " + rows);
					ps.close();
					cn.close();
					if (rows == 0) {
						SCWCText.setText("没有该联系人" + "\n" + "请确认联系人姓名是否正确！" + "\n");
					} else {
						SCWCText.setText("删除成功！" + "\n");
						OutputStream os = s.getOutputStream();
						OutputStreamWriter osw = new OutputStreamWriter(os);
						PrintWriter pw = new PrintWriter(osw, true);
						pw.println("删除" + " " + InputText3.getText() + "的信息");
					}
				} catch (SQLException se) {
					// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e2) {
					// Handle errors for Class.forName
					e2.printStackTrace();
					InputText3.setText("");
				}
			}
		});

		// 实例化修改按钮的监听
		itemxg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(Welcome);
				frame.remove(CXPanel);
				frame.remove(TJPanel);
				frame.remove(SCPanel);
				frame.remove(BFPanel);
				frame.remove(GXPanel);
				frame.remove(BZPanel);
				frame.add(XGPanel);
				xgwc.setText("");
				frame.revalidate();
				frame.repaint();
			}
		});
		QRxg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 在数据库中修改联系人
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/addressbook?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("Creating statement...");
					PreparedStatement ps = cn.prepareStatement("delete from people where pname=?");
					ps.setString(1, des.encrypt(Inputxgname.getText().toString()));
					int rows = ps.executeUpdate();
					System.out.println("Rows impacted : " + rows);
					if (rows != 0) {
						PreparedStatement ps1 = cn.prepareStatement("insert into people values(?,?,?,?)");
						ps1.setString(1, des.encrypt(Inputxgname.getText().toString()));
						ps1.setString(2, des.encrypt(xgteltext.getText().toString()));
						ps1.setString(3, des.encrypt(xgaddtext.getText().toString()));
						ps1.setString(4, des.encrypt(xgqqtext.getText().toString()));
						int rows1 = ps1.executeUpdate();
						System.out.println("Rows1 impacted : " + rows1);
						if (rows1 != 0) {
							System.out.println("成功！");
							xgwc.setText("修改成功！" + "\n");
						} else {
							xgwc.setText("修改失败！" + "\n");
						}
						ps1.close();
					} else {
						xgwc.setText("该联系人不存在，修改失败！" + "\n");
					}
					ps.close();
					cn.close();

					// 向服务器端传送添加的指令
					OutputStream os = s.getOutputStream();
					OutputStreamWriter osw = new OutputStreamWriter(os);
					PrintWriter pw = new PrintWriter(osw, true);
					pw.println("添加" + " " + nametext.getText());
				} catch (SQLException se) {
					// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {
					// Handle errors for Class.forName
					e1.printStackTrace();
					InputText3.setText("");
				}
				Inputxgname.setText("");
				xgteltext.setText("");
				xgaddtext.setText("");
				xgqqtext.setText("");
			}
		});
		// 设置备份按钮的监听
		itembf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(Welcome);
				frame.remove(CXPanel);
				frame.remove(XGPanel);
				frame.remove(TJPanel);
				frame.remove(SCPanel);
				frame.remove(GXPanel);
				frame.remove(BZPanel);
				frame.add(BFPanel);
				frame.revalidate();
				frame.repaint();
				BFText.setText("请稍等，正在备份！" + "\n");
				try {
					// 向服务器发起备份的请求
					OutputStream os = s.getOutputStream();
					OutputStreamWriter osw = new OutputStreamWriter(os);
					PrintWriter pw = new PrintWriter(osw, true);//true表示自动刷新缓冲区
					pw.println("备份");
					File f = new File("D:\\通讯录.txt");
					FileReader fr = new FileReader(f);
					BufferedReader br1 = new BufferedReader(fr);
					while (br1.ready()) {
						String string = br1.readLine();
						pw.println(string);
					}
					br1.close();
					pw.println("end");
					// 接收服务器端传来备份的结果
					InputStream is = s.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					BFText.append(br.readLine() + "\n");
				} catch (Exception e1) {
				}
			}
		});

		// 设置更新按钮的监听
		itemgx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(Welcome);
				frame.remove(CXPanel);
				frame.remove(TJPanel);
				frame.remove(SCPanel);
				frame.remove(XGPanel);
				frame.remove(BFPanel);
				frame.remove(BZPanel);
				frame.add(GXPanel);
				frame.revalidate();
				frame.repaint();
				GXText.setText("请稍等，正在更新！" + "\n");
				try {
					// 向服务器发起更新的请求
					OutputStream os = s.getOutputStream();
					OutputStreamWriter osw = new OutputStreamWriter(os);
					PrintWriter pw = new PrintWriter(osw, true);
					pw.println("更新");
					// 接收服务器传来更新的结果
					InputStream is = s.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					File f = new File("D:\\通讯录.txt");
					FileWriter fw = new FileWriter(f);
					PrintWriter pw1 = new PrintWriter(fw);
					String string = br.readLine();
					while (!string.equals("更新成功!")) {
						pw1.append(string + "\r\n");
						string = br.readLine();
					}
					pw1.close();
					GXText.append("更新成功!" + "\n");
				} catch (Exception e1) {
				}

			}
		});
		itembz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(Welcome);
				frame.remove(CXPanel);
				frame.remove(SCPanel);
				frame.remove(XGPanel);
				frame.remove(BFPanel);
				frame.remove(GXPanel);
				frame.remove(TJPanel);
				frame.add(BZPanel);
				frame.revalidate();
				frame.repaint();
			}
		});
	}// Operation()结束

	private Object getJMenuBar1() {
		// TODO Auto-generated method stub
		return null;
	}

}
