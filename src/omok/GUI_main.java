package omok;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import omokJServer.TransferObj.*;
import clientCommunication.*;

@SuppressWarnings("serial")
public class GUI_main extends JPanel implements ActionListener {
	static String nickname;
	
	public GUI_main() {
		setSize(700,700); //크기 지정. 수정 가능
		setLayout(null);
		
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
		text.add(name);//패널에 닉네임 텍스트 필드 추가
		text.setLocation(275,530);  //닉네임 텍스트 창 사이즈 및 위치 지정, 수정가능
		text.setSize(150,50);
		
		JButton entrance = new JButton("서버 입장"); //서버 입장 버튼
		entrance.addActionListener(e->{
			nickname = name.getText();
			removeAll(); //프레임에 있는 패널 및 각종 항목 모두 제거
			repaint(); //다시 그림
			add(new GUI_play());
			});//서버입장 버튼 ->서버창으로 이동
		entrance.setLocation(200, 600);
		entrance.setSize(100,30);
		
		JButton exit = new JButton("종료");// 서버 종료 버튼 
		exit.addActionListener(e->{System.exit(0);}); //종료 누르면 그냥 창 종료
		exit.setLocation(400,600);
		exit.setSize(100,30);
		
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
