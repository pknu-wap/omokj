package clientCommunication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import omokJServer.TransferObj.*;
import omokJServer.TransferObj;

public class ClientCommProcessor extends Thread {
	//첫 메인 메뉴에서 서버 입장 버튼 누르면 이 클래스에 닉네임을 전달하면서 쓰레드를 실행함
	private static final String SERVER_IP = "52.78.178.184"; // 172.31.42.164       52.78.178.184
	private static final int SERVER_PORT = 50505;
	
	private Socket socket = null;
	
	protected ObjectInputStream is;
	protected ObjectOutputStream os;
	
	private String nickname = null;
	private int roomNumber = 0; // 아무 방에도 안들어가 있으면 0
	
	public ClientCommProcessor (String nickname) {
		this.nickname = nickname;
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
			System.out.println("JOIN===============");
			os = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("JOIN===============");
			is = new ObjectInputStream(socket.getInputStream());
			System.out.println("JOIN===============");
			joinServer(nickname); // 스트림 연결 직후 바로 joinServer 실행 후 연결이 완벽히 되면 ShowRoomList를 받아서 출력
			System.out.println("JOIN===============");
			
			while (true) {
				Opcode opcode = (Opcode)is.readObject();
				// 모든 전송을 객체 하나에 묶어서 직렬화 해서 주고받음. 객체에 operation code를 enum 클래스로 가짐. 각 operation 마다 필요 정보도 내부 클래스로 가짐.
				// operation process
				switch(opcode) {
				case turnOver: // 상대방 turnOver를 받거나 첫 턴을 서버에게 받음
					break;
				case showRoomList:  // 각 방의 방번호, 들어있는 사람 닉네임 (빈 칸은 null로 전달)
					ShowRoomList sRL = new ShowRoomList();
					sRL = (ShowRoomList)is.readObject();
					
					ShowRoomList.roomNumbers = null;
					ShowRoomList.player1= null;
					ShowRoomList.player2= null;
					
					break;
				case showRoom: // 들어가는데 실패 했으면 0 이 날아오고 아니면 들어간 방번호, 들어있는 사람 닉네임이 날아옴
					ShowRoom sR = new ShowRoom();
					sR = (ShowRoom)is.readObject();
		
					ShowRoom.roomNumber = 0;
					ShowRoom.player1 = null;
					ShowRoom.player2 = null;
					
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
		JoinServer.nickname = nickname;
		try {
			os.writeObject(opcode);
			os.writeObject(new JoinServer());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 방 리스트에서 원하는 방 더블클릭(?) 하면 방 번호 인자로 주며 실행. 이후 showRoom이 날아온다
	// 방의 player1, player2가 둘 다 null이 아니면 이 메소드 실행 않고 그냥 무시.
	public void joinRoom(int roomNum) { //showRoom을 받고 나서 그제서야 그 방 번호를 클라이언트 자기 방 번호에 저장!!!!!!!!!!
		this.roomNumber = roomNum;
		Opcode opcode = Opcode.joinRoom;
		JoinRoom jR = new JoinRoom();
		JoinRoom.roomNumber = roomNum;
		try {
			os.writeObject(opcode);
			os.writeObject(jR);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
