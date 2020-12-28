package omok;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import omokJServer.omok_logicSet;
import omokJServer.TransferObj.*;
import clientCommunication.*;


@SuppressWarnings("serial")
public class GUI_waitroom extends JPanel implements ActionListener{
	
	public GUI_waitroom(ClientCommProcessor ccp) {
		setSize(700,700); 
		setLayout(null);

		GUI_main gui_main = new GUI_main();
		System.out.println("닉네임: " + gui_main.nickname);
		
		JLabel label = new JLabel();
		label.setBounds(250,-10,200,100);
		label.setText(gui_main.nickname);
		label.setFont(new Font("", Font.BOLD, 25));
		label.setForeground(Color.white);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel picture = new JPanel() {
			ImageIcon icon = new ImageIcon(getClass().getResource("omok.png"));
			Image img = icon.getImage();
			public void paint(Graphics g) {
				g.drawImage(img, 0,0,500,500,null);
			}
		};
		picture.setSize(500,500);
		picture.setLocation(100,15);
		
		
		JButton button1 = new JButton("1번방"); 
		button1.addActionListener(e->{
	    	
			removeAll();
			repaint(); 
			
			ccp.joinRoom(1);
			
			//add(new GUI_play(ccp));
		    });
		button1.setLocation(200,70);
		button1.setSize(300,70);
		button1.setBackground(new Color(0,0,0));
		button1.setForeground(new Color(255,255,255));
		
		
		JButton button2 = new JButton("2번방"); 
		button2.addActionListener(e->{

			removeAll();
			repaint(); 
			
			ccp.joinRoom(2);
		    });
		button2.setLocation(200,160);
		button2.setSize(300,70);
		button2.setBackground(new Color(255,255,255));
		button2.setForeground(new Color(0,0,0));
		

		JButton button3 = new JButton("3번방"); 
		button3.addActionListener(e->{

			removeAll();
			repaint(); 
			
			ccp.joinRoom(3);
		    });
		button3.setLocation(200,250);
		button3.setSize(300,70);
		button3.setBackground(new Color(0,0,0));
		button3.setForeground(new Color(255,255,255));
		
		JButton button4 = new JButton("4번방"); 
		button4.addActionListener(e->{

			removeAll();
			repaint(); 
			
			ccp.joinRoom(4);
		    });
		button4.setLocation(200,340);
		button4.setSize(300,70);
		button4.setBackground(new Color(255,255,255));
		button4.setForeground(new Color(0,0,0));
		
		JButton button5 = new JButton("5번방"); 
		button5.addActionListener(e->{

			removeAll();
			repaint(); 
			
			ccp.joinRoom(5);
		    });
		button5.setLocation(200,430);
		button5.setSize(300,70);
		button5.setBackground(new Color(0,0,0));
		button5.setForeground(new Color(255,255,255));
		
		JButton exit = new JButton("종료"); 
		exit.addActionListener(e->{System.exit(0);});
		exit.setLocation(300,550);
		exit.setSize(100,30);
		
	    add(label);
		add(button1); 
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		add(picture);
		add(exit);
		setVisible(true); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		
	}
	
	
}
	