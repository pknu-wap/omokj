package omok;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clientCommunication.ClientCommProcessor;

public class GUI_room extends JPanel implements ActionListener{
	
	public GUI_room(ClientCommProcessor ccp, String player1, String player2, boolean[] playerReady) {
		setSize(700,700); 
		setLayout(null);
		setBackground(new Color(148, 242, 197));

		GUI_main gui_main = new GUI_main();
		
		JLabel label = new JLabel();
		label.setBounds(250,30,200,35);
		label.setText(player1);
		label.setFont(new Font("", Font.BOLD, 25));
		label.setForeground(Color.black);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		
		if(playerReady[0] == true)
			label.setBorder(BorderFactory.createLineBorder(Color.WHITE,5, true));
		
		JLabel label2 = new JLabel();
		label2.setBounds(250,90,200,35);
		label2.setText(player2);
		label2.setFont(new Font("", Font.BOLD, 25));
		label2.setForeground(Color.white);
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setVerticalAlignment(JLabel.CENTER);
		
		if(playerReady[1] == true)
			label2.setBorder(BorderFactory.createLineBorder(Color.BLACK,5, true));
		
		JPanel picture = new JPanel() {
			ImageIcon icon = new ImageIcon(getClass().getResource("omok.png"));
			Image img = icon.getImage();
			public void paint(Graphics g) {
				g.drawImage(img, 0,0,500,500,null);
			}
		};
		picture.setSize(500,500);
		picture.setLocation(100,15);
		
		
		JButton button1 = new JButton("READY"); 
		button1.addActionListener(e->{
	    	
			removeAll();
			repaint();
			
			ccp.getReady();
		    });
		button1.setLocation(200,200);
		button1.setSize(300,70);
		button1.setBackground(new Color(0,0,0));
		button1.setForeground(new Color(255,255,255));
		
		
		
		JButton button2 = new JButton("QUIT"); 
		button2.addActionListener(e->{
	
			removeAll();
			repaint(); 
			
			ccp.quitRoom();
		    });
		button2.setLocation(200,300);
		button2.setSize(300,70);
		button2.setBackground(new Color(255,255,255));
		button2.setForeground(new Color(0,0,0));
		
	    add(label);
	    add(label2);
		add(button1); 
		add(button2);
		add(picture);
		setVisible(true); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		
	}
	
	
}
	
