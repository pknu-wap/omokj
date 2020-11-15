package omok;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GUI_main extends JPanel implements ActionListener {
	
	public GUI_main() {
		setSize(700,700); //크기 지정. 수정 가능
		setLayout(null);
		
		JPanel picture = new JPanel() {
			ImageIcon icon = new ImageIcon("C:\\Users\\김서현\\Downloads\\balletbear.png");
			Image img = icon.getImage();
			public void paint(Graphics g) {
				g.drawImage(img, 0,0,500,500,null);
			}
		};
		picture.setSize(500,500);
		picture.setLocation(100,15);
		
		
		JPanel text = new JPanel();//닉네임 넣을 패널
		JTextField name = new JTextField(10); //닉네임 창
		text.setLocation(275,530);  //닉네임 텍스트 창 사이즈 및 위치 지정, 수정가능
		text.setSize(150,50);
		
		JButton entrance = new JButton("서버 입장"); //서버 입장 버튼
		entrance.addActionListener(e->{
			removeAll();
			repaint();
			add(new GUI_play());
			});//서버입장 버튼 ->게임중 창으로 이동, 차후에 서버 GUI창으로 이동
		entrance.setLocation(200, 600);
		entrance.setSize(100,30);
		
		JButton exit = new JButton("종료");// 서버 종료 버튼 
		exit.addActionListener(e->{System.exit(0);}); //종료 누르면 그냥 창 종료
		exit.setLocation(400,600);
		exit.setSize(100,30);
		
		add(picture);
		text.add(name);//패널에 닉네임 텍스트 필드 추가
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
