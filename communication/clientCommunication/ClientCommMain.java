package clientCommunication;

import java.io.*;
import java.net.*;

public class ClientCommMain {
	public static void main(String[] args) {
		ClientCommProcessor ccp = new ClientCommProcessor("WAP7");
		ccp.start();
	}
}
