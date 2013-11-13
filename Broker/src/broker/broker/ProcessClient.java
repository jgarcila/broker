package broker.broker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import broker.client.BrokerLocation;
import broker.domain.BrokerProtocol;
import broker.domain.ProcessRequestException;

public class ProcessClient implements Runnable {
	Socket client;
	private ObjectOutputStream brokerOut;
	private ObjectInputStream brokerIn;

	private String request;
	
	public ProcessClient(Socket _client){
		client = _client;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		generatePipes();
		try {
			processRequest();
		} catch (ProcessRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void processRequest() throws ProcessRequestException{
		try {
			brokerOut.writeInt(BrokerProtocol.BROKER_READY_TO_RECEIVE_MSG);
			brokerOut.flush();
			request = (String)brokerIn.readObject();
			System.out.println("Cliente envi— solicitud");
			brokerOut.writeInt(BrokerProtocol.BROKER_MSG_RECEIVED);
			System.out.println(request);
			new ServerBrokerConnection(getActiveServer(),request).processServerConnection();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// JSONObject was not sent by client
			e.printStackTrace();
		}
	}
	
	private void generatePipes(){
		try {
			brokerOut = new ObjectOutputStream(client.getOutputStream());
			brokerIn = new ObjectInputStream(client.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private ServerLocation getActiveServer(){
		return ServerLocation.SERVER_1;
	}
	
}
