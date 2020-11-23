package clientCommunication;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientCommMain {
	public static void main(String[] args) {
		ClientCommProcessor ccp = new ClientCommProcessor("닉네임");
		ccp.start();
		Scanner sc = new Scanner(System.in);
		System.out.println("이름을 입력하세요");
		sc.next();
	}
}
