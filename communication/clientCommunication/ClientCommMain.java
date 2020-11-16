package clientCommunication;

import java.io.*;
import java.net.*;

public class ClientCommMain {
	private static final String SERVER_IP = "172.31.42.164";
	private static final int SERVER_PORT = 50505;
	
	public static void main(String[] args) {
		Socket socket = null;
		
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if( socket != null && !socket.isClosed()) {
					socket.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
