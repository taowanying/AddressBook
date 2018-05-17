package server;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;


public class Server {
	public static void main(String[] args) {
		
		try {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
					try {
						UIManager
						.setLookAndFeel(new org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
			});
			ServerFrame sf = new ServerFrame();
			// 服务器在8800端口监听
			@SuppressWarnings("resource")
			ServerSocket ss = new ServerSocket(8800);
			System.out.println("服务器正在8800端口监听……");
			Socket s = ss.accept();

			// 接收用户名和密码
			InputStream is = s.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String uandp = br.readLine();
			String u = uandp.split("%")[0];
			String p = uandp.split("%")[1];

			// 将用户名密码的验证信息传送到客户端
			OutputStream os = s.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(osw, true);
			if (u.equals("twycwt") && p.equals("twy97620")) {
				pw.println("ok");
				sf.text.append("客户登录成功" + "\n");
				while (true) {
					String message = br.readLine();
					 DES des = new DES();
					// 响应客户端备份命令
					if (message.equals("备份")) {
						sf.text.append("备份联系人信息" + "\n");
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection cn = DriverManager.getConnection(
									"jdbc:mysql://127.0.0.1:3306/addressbook?characterEncoding=utf8&useSSL=true",
									"root", "twy97620");
							System.out.println("Connecting to database...");
							System.out.println("Creating statement...");
							Statement st = cn.createStatement();
							int rows = st.executeUpdate("delete from people where pname is not null");
							System.out.println("Rows impacted : " + rows);
							String string = br.readLine();
							int count = 0;
							while (!string.equals("end")) {
								String pname = string.split(" ")[0];
								String telephone = string.split(" ")[1];
								String address = string.split(" ")[2];
								String qq = string.split(" ")[3];
								PreparedStatement ps = cn.prepareStatement(
										"insert into people(pname,telephone,address,qq) values(?,?,?,?)");
								//将本地记录加密后备份至数据库
								ps.setString(1, des.encrypt(pname));
								ps.setString(2, des.encrypt(telephone));
								ps.setString(3, des.encrypt(address));
								ps.setString(4, des.encrypt(qq));
								ps.executeUpdate();
								count++;
								System.out.println("正在备份..." + count);
								string = br.readLine();
							}
							st.close();
							cn.close();
							pw.println("备份成功!");
						} catch (SQLException se) {
							// Handle errors for JDBC
							se.printStackTrace();
							pw.println("数据库操作失败!");
						} catch (Exception e) {
							// Handle errors for Class.forName
							e.printStackTrace();
							pw.println("备份失败!");
						}
					}

					// 相应客户端更新的命令
					if (message.equals("更新")) {
						sf.text.append("更新联系人信息" + "\n");
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection cn = DriverManager.getConnection(
									"jdbc:mysql://127.0.0.1:3306/addressbook?characterEncoding=utf8&useSSL=true",
									"root", "twy97620");
							Statement st = cn.createStatement();
							System.out.println("Connecting to database...");
							System.out.println("Creating statement...");
							ResultSet rs = st.executeQuery("select pname,telephone,address,qq from people");
							rs.last();
							int rows = rs.getRow();
							System.out.println("Rows : " + rows);
							rs.beforeFirst();
							int count = 0;
							while (rs.next()) {
								String string = new String();
								System.out.println(string);
								//将数据库中的记录解密后更新至本地文件
								string =  des.decrypt(rs.getString(1)) + " " + des.decrypt(rs.getString(2)) + " " + des.decrypt(rs.getString(3)) + " "
										+ des.decrypt(rs.getString(4));
								pw.println(string);
								count++;
								System.out.println("正在更新..." + count);
							}
							rs.close();
							st.close();
							cn.close();
							pw.println("更新成功!");
						} catch (SQLException se) {
							// Handle errors for JDBC
							se.printStackTrace();
							pw.println("数据库操作失败！");
						} catch (Exception e) {
							// Handle errors for Class.forName
							e.printStackTrace();
							pw.println("更新失败!");
						}
					}
					if (message.split(" ")[0].equals("查询")) {
						sf.text.append(message + "\n");
					}
					if (message.split(" ")[0].equals("添加")) {
						sf.text.append(message + "\n");
					}
					if (message.split(" ")[0].equals("删除")) {
						sf.text.append(message + "\n");
					}
				}
			} else {
				// 发送错误信号到客户端
				pw.println("error");
			}
		} catch (Exception e) {
		}
	}
}