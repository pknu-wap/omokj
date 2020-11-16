package omokJServer;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import omokJServer.TransferObj.Opcode;

public class ServerClientProcessor extends Thread {
	private Socket socket = null;
	ArrayList<ServerClientProcessor> clientList = null; 
	
	protected ObjectInputStream is;
	protected ObjectOutputStream os;
	private String nickname = null;
	private int roomNumber = 0; // �ƹ� �濡�� �ȵ� ����
	
	public ServerClientProcessor(Socket socket, ArrayList<ServerClientProcessor> clientList) {
		this.socket = socket;
		this.clientList = clientList;
	}
	
	@Override
	public void run() {
		try {
			is = new ObjectInputStream(socket.getInputStream());
			os = new ObjectOutputStream(socket.getOutputStream());
			while (true) {
				TransferObj request = (TransferObj)is.readObject();
				// ��� ������ ��ü �ϳ��� ��� ����ȭ �ؼ� �ְ����. ��ü�� operation code�� enum Ŭ������ ����. �� operation ���� �ʿ� ������ ���� Ŭ������ ����.
				// operation process
				switch(request.getOpcode()) {
				case joinServer: // showRoomList
					break;
				case joinRoom: // showRoom 2���� �濡 ������ startOmok
					break;
				case turnOver: // �޾Ƽ� deliverTurn���� ��� Ŭ���̾�Ʈ���� ����
					break;
				case gameOver:
						break;
				case showRoomList:
					break;
				default:
					break;
				}
			}
		}
		catch(IOException e) {
			consoleLog(this.nickname + "���� �������ϴ�.");
		} catch (ClassNotFoundException e) {
			consoleLog(this.nickname + "���� �������ϴ�.");
		}
}
	
	private void doQuit(PrintWriter writer) {
		removeWriter(writer);
		
		String data = this.nickname = "���� �����߽��ϴ�.";
		broadcast(data);
	}
	
	private void removeWriter(PrintWriter writer) {
		synchronized (listWriters) {
			listWriters.remove(writer);
		}
	}
	
	private void doMessage(String data) {
		broadcast(this.nickname + " : " + data);
	}
	
	private void doJoin(String nickname, PrintWriter writer) {
		this.nickname = nickname;
		
		String data = nickname + "���� �����ϼ̽��ϴ�.";
		broadcast(data);
		
		//writer pool�� ����
		addWriter(writer);
	}
	
	private void addWriter(PrintWriter writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}
	
	private void broadcast(String data) {
		synchronized (listWriters) {
			for (PrintWriter writer : listWriters) {
				writer.println(data);
				writer.flush();
			}
		}
	}
	
	private void consoleLog(String log) {
		System.out.println(log);
	}
}
