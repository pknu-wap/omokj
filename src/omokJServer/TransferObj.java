package omokJServer;

import java.io.*;
import java.util.ArrayList;

// This class contains everything about transfer information

public class TransferObj implements Serializable {
	public static enum Opcode implements Serializable { 
		joinServer, joinRoom, getReady, quitRoom, // for Client
		denyEntry, showRoomList, showRoom, startOmok, turnOver, notifyBoard // for Server
	}
}
