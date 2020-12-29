package omok;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import javax.swing.*;

import omokJServer.TransferObj.*;
import clientCommunication.*;


public class GUI extends JFrame  {
	public GUI() {
		setSize(700,700); //占쎄쾿疫뀐옙 筌욑옙占쎌젟. 占쎈땾占쎌젟 揶쏉옙占쎈뮟
		setLayout(null);
		setTitle("OmokJ"); //占쎌맊占쎈즲占쎌뒭 筌∽옙 占쎌뵠�뵳占� 筌욑옙占쎌젟, 占쎌벥占쎈걠
		//Toolkit toolkit = Toolkit.getDefaultToolkit();
		//Image img = toolkit.getImage("omok.gif");
		ImageIcon icon = new ImageIcon(getClass().getResource("omok.gif"));
		Image img = icon.getImage();
		setIconImage(img);
		
		GUI_main gui_main = new GUI_main();
		
		add(gui_main);
		setVisible(true); 
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		GUI gui = new GUI();
	}
}
