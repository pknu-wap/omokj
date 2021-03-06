package omok;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import omokJServer.omok_logicSet;
import omokJServer.TransferObj.*;
import clientCommunication.*;

@SuppressWarnings("serial")
public class GUI_main extends JPanel implements ActionListener {
	static String nickname;
	
	public GUI_main() {
		setSize(700,700); //크기 지정. 수정 가능
		setLayout(null);
		setBackground(new Color(148, 242, 197));
		
		JPanel picture = new JPanel() {
			ImageIcon icon = new ImageIcon(getClass().getResource("omok.png"));
			Image img = icon.getImage();
			public void paint(Graphics g) {
				g.drawImage(img, 0,0,500,500,null);
			}
		};
		picture.setSize(500,500);
		picture.setLocation(100,15);
		
		
		JPanel text = new JPanel();//닉네임 넣을 패널
		JTextField name = new JTextField(10); //닉네임 창
		name.setBorder(BorderFactory.createLineBorder(Color.GREEN,5, true));
		name.setPreferredSize(new Dimension(150, 35));
		name.setFont(new Font("", Font.BOLD, 15));
		name.setHorizontalAlignment(JLabel.CENTER);
		text.setBackground(new Color(148, 242, 197));
		text.add(name);//패널에 닉네임 텍스트 필드 추가
		text.setLocation(275,530);  //닉네임 텍스트 창 사이즈 및 위치 지정, 수정가능
		text.setSize(150,50);
		
		
		JButton entrance = new JButton("서버 입장"); //서버 입장 버튼
		entrance.setBackground(new Color(255, 145, 0));
		entrance.setFont(new Font("", Font.BOLD, 15));
		entrance.addActionListener(e->{
			nickname = name.getText();
			
			ClientCommProcessor ccp = new ClientCommProcessor(nickname, this); // 입장 버튼 누르면 닉네임 가져가서 서버 연결
			ccp.start();
			
			while(ccp.enterState == 1) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(ccp.enterState == 3) {
				ccp.enterState = 0;
				return;
			}
			ccp.enterState = 0;
			
			removeAll(); //프레임에 있는 패널 및 각종 항목 모두 제거
			repaint(); //다시 그림
			add(new GUI_waitroom(ccp));
			});//서버입장 버튼 ->서버창으로 이동
		entrance.setLocation(200, 600);
		entrance.setSize(100,30);
		
		JButton exit = new JButton("종료");// 서버 종료 버튼 
		exit.addActionListener(e->{System.exit(0);}); //종료 누르면 그냥 창 종료
		exit.setLocation(400,600);
		exit.setSize(100,30);
		exit.setFont(new Font("", Font.BOLD, 15));
		
		add(picture);
		add(text);
		add(entrance); // 패널에 입장 버튼 추가
		add(exit); // 패널에 종료 버튼 추가
		setVisible(true); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
