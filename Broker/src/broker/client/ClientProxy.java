package broker.client;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Hashtable;

import com.google.gson.Gson;

import broker.domain.ProcessRequestException;
import broker.domain.RequestInfo;


public class ClientProxy {
	private static ClientProxy proxyInst;
	private Socket connBroker;
	private ClientProxy(){
		
	}
	/**
	 * Singleton function used to get the only one ClientProxy instance
	 * @return ClientProxy instance
	 */
	public static ClientProxy getClientProxy(){
		if(proxyInst == null)
			proxyInst = new ClientProxy();
		return proxyInst;
	}
	
	
	private void createBrokerConnection(){
		BrokerLocation activeBroker = getActiveBroker();
		try {
			connBroker = new Socket(activeBroker.getIp(),activeBroker.getPort());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private BrokerLocation getActiveBroker(){
		return BrokerLocation.B_1;
	}
	
	private boolean isBrokerConnectionCreated(){
		return false;
	}
	/**
	 * Used to call a service through Broker
	 */
	public void callBrokerAService(String serviceName, Hashtable<String, Integer> data){
		//create connections
		if(!isBrokerConnectionCreated())
			createBrokerConnection();
		//pack request into JSON format..
		String reqWrapped = wrapRequest(new RequestInfo(serviceName, data));
		//send request to Broker using a protocol..
		try {
			new ProcessRequest(connBroker,reqWrapped).process();
		} catch (ProcessRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	private String wrapRequest(RequestInfo reqInfo){
		//Here an JSON is created based on service name and data
		//At this moment only JSON object creation but many other info would be added into
		//request thats why a function is used...
		Gson gson = new Gson();
		return gson.toJson(reqInfo);
	}
	
	public static void main(String args[]){
		Hashtable<String, Integer> datos = new Hashtable<String, Integer>();
		datos.put("param1", new Integer(2));
		ClientProxy.getClientProxy().callBrokerAService("hola", datos);
	}
}
