package omok;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI extends JFrame  {
	public GUI() {
		setSize(700,700); //크기 지정. 수정 가능
		setLayout(null);
		setTitle("오목 7조"); //윈도우 창 이름 지정, 의논
		
		GUI_main gui_main = new GUI_main();
		GUI_play gui_play = new GUI_play();
		
		add(gui_main);
		setVisible(true); 
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		GUI gui = new GUI();
	}
}
