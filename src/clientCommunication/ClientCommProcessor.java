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
	//ù ���� �޴����� ���� ���� ��ư ������ �� Ŭ������ �г����� �����ϸ鼭 �����带 ������
	private static final String SERVER_IP = "52.78.178.184"; 
	private static final int SERVER_PORT = 50505;
	
	private Socket socket = null;
	
	protected ObjectInputStream is;
	protected ObjectOutputStream os;
	
	private String nickname = null;
	private int roomNumber = 0; // �ƹ� �濡�� �ȵ� ������ 0
	
	public boolean placeAuth = false; // ������ ���� turnOver ������ �̰� ������ �ϰ� �̰� ���� ���� ���� Ŭ���� ó����.
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
		//������ ����
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
		
		// input, output ��Ʈ�� ���� �� ����Ÿ ��ü ���� ��� ����
		try {
			os = new ObjectOutputStream(socket.getOutputStream());
			is = new ObjectInputStream(socket.getInputStream());
			joinServer(nickname); // ��Ʈ�� ���� ���� �ٷ� joinServer ���� �� ������ �Ϻ��� �Ǹ� ShowRoomList�� �޾Ƽ� ���
			
			while (true) {
				Opcode opcode = (Opcode)is.readObject();
				// ��� ������ ��ü �ϳ��� ��� ����ȭ �ؼ� �ְ����. ��ü�� operation code�� enum Ŭ������ ����. �� operation ���� �ʿ� ������ ���� Ŭ������ ����.
				// operation process
				switch(opcode) {
				case denyEntry:
					return;
				case showRoomList:  // �� ���� ���ȣ, ����ִ� ��� �г��� (�� ĭ�� null�� ����)
					state = State.channel;
					int[] roomNumbers = (int[])is.readObject(); // 0������ ��� ���ϰ� ���� 1~5���� ���� ����
					String[] player1s = (String[])is.readObject();
					String[] player2s = (String[])is.readObject();
					guim.removeAll();
					guim.repaint();
					guim.add(new GUI_waitroom(this));
					guim.repaint();
					break;
				case showRoom: // ���µ� ���� ������ 0 �� ���ƿ��� �ƴϸ� �� ���ȣ, ����ִ� ��� �г����� ���ƿ�
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
				case turnOver: // ���� turnOver�� �ްų� ù ���� �������� ����
					placeAuth = true; // true�� �ٲ������ ���带 Ŭ���ϸ� ��ġ ������ ������ ���۵�.
					break;
				case notifyBoard: // turnOver ���� ���Ŀ� �ٷ� �� ��ɵ� ���ƿ�.
					omokBoard = (int[][])is.readObject(); 
					dBo.board = omokBoard;
					this.dBo.repaint();
					break;
				case winner:
					JLabel wlabel = new JLabel("��");
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
					JLabel llabel = new JLabel("��");
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
	//JoinServer ����, ��Ʈ�� ����� ���Ŀ� �ٷ� ����
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
	
	// �� ����Ʈ���� ���ϴ� �� ����Ŭ��(?) �ϸ� �� ��ȣ ���ڷ� �ָ� ����. ���� showRoom�� ���ƿ´�
	// ���� player1, player2�� �� �� null�� �ƴϸ� �� �޼ҵ� ���� �ʰ� �׳� ����.
	public void joinRoom(int roomNum) { //showRoom�� �ް� ���� �������� �� �� ��ȣ�� Ŭ���̾�Ʈ �ڱ� �� ��ȣ�� ����!!!!!!!!!!
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
	
	public void consoleChoice(int choice) { // main ���� Ŭ���̾�Ʈ ���¿� ���� ���� ó��
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
