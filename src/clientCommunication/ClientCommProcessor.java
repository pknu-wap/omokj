package clientCommunication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import omokJServer.TransferObj.*;
import java.io.*;

public class ClientCommProcessor extends Thread {
	//첫 메인 메뉴에서 서버 입장 버튼 누르면 이 클래스에 닉네임을 전달하면서 쓰레드를 실행함
	private static final String SERVER_IP = "52.78.178.184"; 
	private static final int SERVER_PORT = 50505;
	
	private Socket socket = null;
	
	protected ObjectInputStream is;
	protected ObjectOutputStream os;
	
	private String nickname = null;
	private int roomNumber = 0; // 아무 방에도 안들어가 있으면 0
	
	Scanner sc;
	
	public ClientCommProcessor () {
		System.out.print("닉네임을 입력하세요 : ");
		sc = new Scanner(System.in);
		this.nickname = sc.nextLine();
	
	}
	
	@Override
	public void run() {
		//서버와 연결
		try {
			//InetAddress address= InetAddress.getByName(SERVER_IP);
			//socket=new Socket(address.getHostAddress(),SERVER_PORT);
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		// input, output 스트림 세팅 밑 데이타 객체 수신 대기 시작
		try {
			os = new ObjectOutputStream(socket.getOutputStream());
			is = new ObjectInputStream(socket.getInputStream());
			joinServer(nickname); // 스트림 연결 직후 바로 joinServer 실행 후 연결이 완벽히 되면 ShowRoomList를 받아서 출력
			
			while (true) {
				Opcode opcode = (Opcode)is.readObject();
				// 모든 전송을 객체 하나에 묶어서 직렬화 해서 주고받음. 객체에 operation code를 enum 클래스로 가짐. 각 operation 마다 필요 정보도 내부 클래스로 가짐.
				// operation process
				switch(opcode) {
				case turnOver: // 상대방 turnOver를 받거나 첫 턴을 서버에게 받음
					break;
				case denyEntry:
					return;
				case showRoomList:  // 각 방의 방번호, 들어있는 사람 닉네임 (빈 칸은 null로 전달)
					int[] roomNumbers = (int[])is.readObject(); // 0번방은 사용 안하고 현재 1~5번방 까지 있음
					String[] player1s = (String[])is.readObject();
					String[] player2s = (String[])is.readObject();
					for(int i = 1; i < roomNumbers.length;i++) {
						System.out.println("[" + roomNumbers[i] + "] P1:" + player1s[i] + " | P2:" + player2s[i]);
					}
					System.out.print("몇 번방에 들어가시겠습니까? (1~5번, 다른 입력시 종료) : ");
					int roomN = sc.nextInt();
					if(roomN>=1 && roomN <=5) 
					joinRoom(roomN);
					else 
						return;
					break;
				case showRoom: // 들어가는데 실패 했으면 0 이 날아오고 아니면 들어간 방번호, 들어있는 사람 닉네임이 날아옴
					this.roomNumber = (int)is.readObject();
					if(this.roomNumber != 0) {
						String player1 = (String)is.readObject();
						String player2 = (String)is.readObject();
						System.out.println(" >" + roomNumber + "< P1:" + player1 + " | P2:" + player2);
						int rC = 0;
						while(rC != 1 &&  rC != 2) {
							System.out.println(" 1. Ready 2. Quit ");
							rC = sc.nextInt();
							if(rC==1) {
								//get ready
								getReady();
							}
							else if(rC==2) {
								// quit room
								quitRoom();
							}
						}
					}
					else {
						System.out.println("해당 방에 접속하는데 실패하였습니다.");
					}
					break;
				default:
					break;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("오목 종료");
			try {
				if( socket != null && !socket.isClosed()) {
					is.close();
					os.close();
					socket.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
}
	//JoinServer 소켓, 스트림 연결된 직후에 바로 실행
	private void joinServer(String nickname) {
		Opcode opcode = Opcode.joinServer;
		this.nickname = nickname;
		try {
			os.writeObject(opcode);
			os.writeObject(nickname);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 방 리스트에서 원하는 방 더블클릭(?) 하면 방 번호 인자로 주며 실행. 이후 showRoom이 날아온다
	// 방의 player1, player2가 둘 다 null이 아니면 이 메소드 실행 않고 그냥 무시.
	public void joinRoom(int roomNum) { //showRoom을 받고 나서 그제서야 그 방 번호를 클라이언트 자기 방 번호에 저장!!!!!!!!!!
		this.roomNumber = roomNum;
		Opcode opcode = Opcode.joinRoom;
		try {
			os.writeObject(opcode);
			os.writeObject(this.roomNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getReady() {
		Opcode opcode = Opcode.getReady;
		try {
			os.writeObject(opcode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void quitRoom() {
		Opcode opcode = Opcode.quitRoom;
		try {
			os.writeObject(opcode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
