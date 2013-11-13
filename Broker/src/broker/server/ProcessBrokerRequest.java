package broker.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.google.gson.Gson;

import broker.domain.BrokerProtocol;
import broker.domain.RequestInfo;

public class ProcessBrokerRequest implements Runnable{

	Socket reqBroker;
	ObjectOutputStream brokerOut;
	ObjectInputStream brokerIn;
	
	public ProcessBrokerRequest(Socket _reqBroker){
		this.reqBroker = _reqBroker;
	}
	@Override
	public void run() {
		generatePipes();
		processBrokerReq();
	}
	
	private void processBrokerReq(){
		String req;
		try {
			brokerOut.writeInt(BrokerProtocol.SERVER_READY_TO_RECEIVE_REQ);
			brokerOut.flush();
			req = (String)brokerIn.readObject();
			brokerOut.writeInt(BrokerProtocol.SERVER_REQ_RECEIVED);
			System.out.println("Req recibido"+req);
			callService(unWrapRequest(req));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String callService(RequestInfo req){
		return null;
		
	}
	private RequestInfo unWrapRequest(String req){
		Gson json = new Gson();
		return json.fromJson(req,RequestInfo.class);
	}
	
	private void generatePipes(){
		try {
			brokerOut = new ObjectOutputStream(reqBroker.getOutputStream());
			brokerIn = new ObjectInputStream(reqBroker.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
