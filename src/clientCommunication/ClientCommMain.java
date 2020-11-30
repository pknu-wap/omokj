package clientCommunication;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientCommMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String nick = sc.nextLine();
		ClientCommProcessor ccp = new ClientCommProcessor(nick);
		ccp.start();
		
		// 임시 블락
		sc.next();
	}
}
