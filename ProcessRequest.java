package broker.client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import broker.domain.BrokerProtocol;
import broker.domain.ProcessRequestException;

/**
 * Class oriented to process all related to TCP-IP Broker communications
 * JSON format is used to transfer info between Client and Broker
 * @author juangarcilazo_iMac
 *
 */
public class ProcessRequest {
	private ObjectOutputStream brokerOut;
	private ObjectInputStream brokerIn;
	private String request;
	
	public ProcessRequest(Socket socketBroker, String request){
		try{
			this.brokerIn= new ObjectInputStream(socketBroker.getInputStream());
			this.brokerOut= new ObjectOutputStream(socketBroker.getOutputStream());
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error al crear OOIn and Out");
		}
		this.request=request;
	}
	
	public void process() throws ProcessRequestException{
		int protocolInfo;
		//First we send an request for a service call
		try {
			protocolInfo = brokerIn.readInt();
			if(protocolInfo != BrokerProtocol.BROKER_READY_TO_RECEIVE_MSG)
				throw new ProcessRequestException("Broker is not ready to receive a request");
			//Here a request object will be sent
			System.out.println("Message Broker: Broker Ready");
			brokerOut.writeObject(request);
			protocolInfo = brokerIn.readInt(); //waiting for a broker reception confirmation
			if(protocolInfo != BrokerProtocol.BROKER_MSG_RECEIVED)
				throw new ProcessRequestException("Broker reject request");
			System.out.println("Broker received the message");
			processResponse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void processResponse(){
	}
	
}
