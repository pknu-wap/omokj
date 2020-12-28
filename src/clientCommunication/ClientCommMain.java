package clientCommunication;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.swing.*;

import omok.GUI_main;
import omok.GUI_play;

public class ClientCommMain extends JFrame {
	
	public ClientCommMain(ClientCommProcessor ccomp) {
		setSize(700,700); 
		setLayout(null);
		setTitle("오목 J");
		
		add(new GUI_play(ccomp));
		setVisible(true); 
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("닉네임을 입력하세요: ");
		String nick = sc.nextLine();
		int choice = 0;
		ClientCommProcessor ccp = new ClientCommProcessor(nick);
		ccp.start();
		while(ccp.isAlive()) {
			choice = sc.nextInt();
			ccp.consoleChoice(choice);
		}
		sc.close();
	}
}
