package project.beans;

import java.nio.channels.SocketChannel;

public class tagConnectInfo{
	public SocketChannel	socket;
	public int				index;
	public tagConnectInfo() {
		socket = null;
		index = 0;
	}
}

