package clientCommunication;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientCommMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("닉네임을 입력하세요 : ");
		String nick = sc.nextLine();
		int choice = 0;
		ClientCommProcessor ccp = new ClientCommProcessor(nick);
		ccp.start();
		while(ccp.isAlive()) {
			choice = sc.nextInt();
			ccp.consoleChoice(choice);
		}
	}
}
