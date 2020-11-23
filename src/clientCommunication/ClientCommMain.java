package clientCommunication;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientCommMain {
	public static void main(String[] args) {
		ClientCommProcessor ccp = new ClientCommProcessor("NICK123");
		ccp.start();
		Scanner sc = new Scanner(System.in);
		System.out.println("TEMPTEMTPEMT");
		sc.next();
	}
}
