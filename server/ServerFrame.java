package server;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class ServerFrame extends JFrame{
    public JTextArea text = new JTextArea();
	public ServerFrame(){
		//设置窗口格式
		this.setTitle("服务端");
		this.setLocation(1200,300);
		this.setSize(300,400);
		this.setDefaultCloseOperation(3);
		//将文本框添加到窗口
		Dimension dim = new Dimension(300,400);
		text.setPreferredSize(dim);
		this.add(text);
		this.setVisible(true);
	}
}
