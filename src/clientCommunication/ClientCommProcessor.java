package clientCommunication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JLabel;

import omok.GUI;
import omok.GUI_main;
import omok.GUI_play;
import omok.GUI_room;
import omok.GUI_waitroom;
import omok.omok_drawBoard;
import omokJServer.TransferObj.*;

import java.awt.Color;
import java.awt.Font;
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
	
	public boolean placeAuth = false; // 서버로 부터 turnOver 받으면 이걸 참으로 하고 이게 참일 때만 보드 클릭을 처리함.
	public int[][] omokBoard;
	
	public String[] player;
	
	omok_drawBoard dBo;
	
	public int enterState = 0; // 0 none 1 waiting 2 succeeded 3 failed
	
	GUI_main guim;
	
	public static enum State { 
		first, channel, room, game
	}
	
	State state = State.first;
	
	public ClientCommProcessor (String nickname, GUI_main guim) {
		this.nickname = nickname;
		enterState = 1;
		this.guim = guim;
		this.player = new String[2];
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
			enterState = 3;
			e.printStackTrace();
		}
		if(enterState != 3)
			enterState = 2;
		
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
				case denyEntry:
					return;
				case showRoomList:  // 각 방의 방번호, 들어있는 사람 닉네임 (빈 칸은 null로 전달)
					state = State.channel;
					int[] roomNumbers = (int[])is.readObject(); // 0번방은 사용 안하고 현재 1~5번방 까지 있음
					String[] player1s = (String[])is.readObject();
					String[] player2s = (String[])is.readObject();
					guim.removeAll();
					guim.repaint();
					guim.add(new GUI_waitroom(this));
					guim.repaint();
					break;
				case showRoom: // 들어가는데 실패 했으면 0 이 날아오고 아니면 들어간 방번호, 들어있는 사람 닉네임이 날아옴
					this.roomNumber = (int)is.readObject();
					if(this.roomNumber != 0) {
						state = State.room;
						String player1 = (String)is.readObject();
						String player2 = (String)is.readObject();
						this.player[0] = player1;
						this.player[1] = player2;
						boolean[] playerReady = (boolean[])is.readObject();
						guim.removeAll();
						guim.repaint();
						guim.add(new GUI_room(this, player1, player2, playerReady));
						guim.repaint();
					}
					else {
						guim.removeAll();
						guim.repaint();
						guim.add(new GUI_waitroom(this));
						guim.repaint();
					}
					break;
				case startOmok:
					JLabel slabel = new JLabel("3");
					slabel.setBounds(0,0,700,700);
					slabel.setFont(new Font("", Font.BOLD, 50));
					slabel.setForeground(Color.white);
					slabel.setHorizontalAlignment(JLabel.CENTER);
					slabel.setVerticalAlignment(JLabel.CENTER);
					guim.removeAll();
					guim.repaint();
					guim.add(slabel);
					guim.repaint();
					Thread.sleep(1000);
					slabel.setText("2");
					guim.removeAll();
					guim.repaint();
					guim.add(slabel);
					guim.repaint();
					Thread.sleep(1000);
					slabel.setText("1");
					guim.removeAll();
					guim.repaint();
					guim.add(slabel);
					guim.repaint();
					Thread.sleep(1000);
					
					state = State.game;
					omokBoard = new int[19][19];
					guim.removeAll();
					guim.repaint();
					guim.add(new GUI_play(this));
					guim.repaint();
					break;
				case turnOver: // 상대방 turnOver를 받거나 첫 턴을 서버에게 받음
					placeAuth = true; // true로 바뀌었으니 보드를 클릭하면 배치 정보가 서버로 전송됨.
					break;
				case notifyBoard: // turnOver 받은 직후엔 바로 이 명령도 날아옴.
					omokBoard = (int[][])is.readObject(); 
					dBo.board = omokBoard;
					this.dBo.repaint();
					break;
				case winner:
					JLabel wlabel = new JLabel("승");
					wlabel.setBounds(0,0,700,700);
					wlabel.setFont(new Font("", Font.BOLD, 50));
					wlabel.setForeground(Color.black);
					wlabel.setHorizontalAlignment(JLabel.CENTER);
					wlabel.setVerticalAlignment(JLabel.CENTER);
					guim.removeAll();
					guim.repaint();
					guim.add(wlabel);
					guim.repaint();
					Thread.sleep(3000);
					this.joinRoom(this.roomNumber);
					break;
				case loser:
					JLabel llabel = new JLabel("패");
					llabel.setBounds(0,0,700,700);
					llabel.setFont(new Font("", Font.BOLD, 50));
					llabel.setForeground(Color.black);
					llabel.setHorizontalAlignment(JLabel.CENTER);
					llabel.setVerticalAlignment(JLabel.CENTER);
					guim.removeAll();
					guim.repaint();
					guim.add(llabel);
					guim.repaint();
					Thread.sleep(3000);
					this.joinRoom(this.roomNumber);
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
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
	
	public void sendStone(int x, int y) {
		this.placeAuth = false;
		Opcode opcode = Opcode.sendStone;
		try {
			os.writeObject(opcode);
			os.writeObject(x);
			os.writeObject(y);
		} catch (IOException e) {
			e.printStackTrace();
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
		state = State.channel;
	}
	
	public void setDrawBoard(omok_drawBoard dbd) {
		this.dBo = dbd;
	}
	
	public void consoleChoice(int choice) { // main 에서 클라이언트 상태에 따른 선택 처리
		switch(state) {
		case first:
			break;
		case channel:
			int roomN = choice;
			if(roomN>=1 && roomN <=5) 
			joinRoom(roomN);
			else 
				return;
			break;
		case room:
			if(choice==1)
				getReady();
			else if(choice==2) 
				quitRoom();
			else ;
			break;
		case game:
			break;
		default:
			break;	
		}
	}
}
