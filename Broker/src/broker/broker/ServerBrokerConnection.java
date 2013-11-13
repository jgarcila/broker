package broker.broker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import broker.domain.BrokerProtocol;

public class ServerBrokerConnection {
	String reqInfo;
	ServerLocation server;
	ObjectOutputStream serverOut;
	ObjectInputStream serverIn;
	public ServerBrokerConnection(ServerLocation serverLoc, String requestInfo){
		this.reqInfo = requestInfo;
		this.server = serverLoc;
	}		
	
	public String processServerConnection(){
	int protocol;
	String response = "";
		generatePipesToServer();
		try {
			protocol = serverIn.readInt();
			if(protocol != BrokerProtocol.SERVER_READY_TO_RECEIVE_REQ) return null;
			serverOut.writeObject(reqInfo);
			protocol = serverIn.readInt();
			if(protocol != BrokerProtocol.SERVER_REQ_RECEIVED) return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	private void generatePipesToServer(){
		Socket serverSocket = new Socket();
		try{
			serverSocket = new Socket(server.getIp(),server.getPort());
			serverOut = new ObjectOutputStream(serverSocket.getOutputStream());
			serverIn = new ObjectInputStream(serverSocket.getInputStream());
			
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
