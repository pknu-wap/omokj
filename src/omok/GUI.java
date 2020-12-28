package omok;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import javax.swing.*;

import omokJServer.TransferObj.*;
import clientCommunication.*;


public class GUI extends JFrame  {
	public GUI() {
		setSize(700,700); //�겕湲� 吏��젙. �닔�젙 媛��뒫
		setLayout(null);
		setTitle("오목J"); //�쐢�룄�슦 李� �씠由� 吏��젙, �쓽�끉
		
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
