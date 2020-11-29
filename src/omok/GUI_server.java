package omok;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import omokJServer.*;
import clientCommunication.*;

@SuppressWarnings("serial")
public class GUI_server extends JPanel {
	
	private ClientCommProcessor ccp = new ClientCommProcessor(GUI_main.nickname); //닉네임 정보 전달
	private TransferObj to;
	
	public GUI_server() {
		ccp.start();
		
		setSize(700,700);
		setLayout(null);
		
		JLabel name = new JLabel(GUI_main.nickname); //ClientCommProcessor.nickname 넣어야하는데 오류발생
		name.setLocation(300,15);
		name.setSize(100,30);
		
		
		
		JButton exit = new JButton("종료");// 서버 종료 버튼 
		exit.addActionListener(e->{System.exit(0);}); //종료 누르면 그냥 창 종료
		exit.setLocation(300,600);
		exit.setSize(100,30);
		
		add(name);

		add(exit);
	}
}