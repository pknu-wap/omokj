package omokJServer;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import omokJServer.OmokRoomManager.OmokRoom;

public class ServerMain {
	public final static int SERVER_PORT = 50505;
	private final static int MAX_ROOMS = 5;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		/* ������ �������� ����Ʈ�� */
		ArrayList<ServerClientProcessor> clientList = new ArrayList<ServerClientProcessor>();
		
		OmokRoom[] room = new OmokRoom[MAX_ROOMS]; // �� ���
		OmokRoomManager roomManager = new OmokRoomManager(room); 
		/*===============*/
		try {
			// Server Socket
			serverSocket = new ServerSocket();
			// Binding
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress(hostAddress, SERVER_PORT));
			consoleLog("Binding .. " + hostAddress + " : " + SERVER_PORT);
			//���������� �� Ŭ���̾�p 
			while(true) {
				// accept ( blocked until connection request is received)
				Socket socket = serverSocket.accept();
				ServerClientProcessor svc = new ServerClientProcessor(socket, clientList, roomManager);
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
