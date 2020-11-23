package omokJServer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import omokJServer.OmokRoomManager.OmokRoom;

public class ServerMain {
	public final static String SERVER_IP = "52.78.178.184"; // 172.31.42.164
	public final static int SERVER_PORT = 50505;
	private final static int MAX_ROOMS = 5;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		/* List that server has to manage */
		ArrayList<ServerCommProcessor> clientList = new ArrayList<ServerCommProcessor>();
		
		OmokRoomManager roomManager = new OmokRoomManager(); 
		/*===============*/
		try {
			// Server Socket
			serverSocket = new ServerSocket(SERVER_PORT);
			// Binding
			//String hostAddress = InetAddress.getLocalHost().getHostAddress();
			//serverSocket.bind(new InetSocketAddress(hostAddress, SERVER_PORT));
			//consoleLog("Binding .. " + hostAddress + " : " + SERVER_PORT);
			consoleLog("Binding .. " + "52.78.178.184" + " : " + SERVER_PORT);
			while(true) {
				// accept ( blocked until connection request is received)
				Socket socket = serverSocket.accept();
				ServerCommProcessor svc = new ServerCommProcessor(socket, clientList, roomManager);
				svc.start(); 
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if( serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void consoleLog(String log) {
		System.out.println("[SERVER " + Thread.currentThread().getId() + " ] " + log);
	}
}