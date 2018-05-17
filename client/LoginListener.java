package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginListener implements ActionListener{
	  public String Name;
	  public String Password;
	  public JFrame frame;
	  public JTextField text;
	  public JPasswordField pw;
	  public LoginListener(JFrame frame,JTextField text,JPasswordField pw){
		  this.frame = frame;
		  this.text = text;
		  this.pw = pw;
	  }
	  @SuppressWarnings("deprecation")
	  public void actionPerformed(ActionEvent e){
		  try{
			  //发送密码和用户名到客户端
			  String user = text.getText();
			  String pass = pw.getText();
			  Socket s = new Socket("127.0.0.1",8800);
			  
			  OutputStream os = s.getOutputStream();
			  OutputStreamWriter osw = new OutputStreamWriter(os);
			  PrintWriter pw = new PrintWriter(osw ,true);
			  
			  pw.println(user+"%"+pass);
			  
			  //接收服务器发回来的确认信息
			  InputStream is = s.getInputStream();
			  InputStreamReader isr = new InputStreamReader(is);
			  BufferedReader br = new BufferedReader(isr);
			  
			  String answer = br.readLine();
			  
			  //显示登录成功界面或密码错误界面
			  if(answer.equals("ok")){
				  Operation o = new Operation();
				  o.SetSocket(s);
				  frame.dispose();//关闭登录界面
			  }
			  else{
				  JTextField text = new JTextField("            用户名或密码错误");
				  JFrame frame = new JFrame();
			      frame.setTitle("错误");
			      frame.setLocation(550,300);
			      frame.setSize(200,100);
			      frame.setDefaultCloseOperation(2);
			      frame.add(text);
			      frame.setVisible(true);
			  } 
		  }catch(Exception e1){}
		    
	  }
}
