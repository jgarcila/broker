package broker.broker;

import java.io.IOException;
import java.net.ServerSocket;

public class Broker {
	ServerSocket server;
	public Broker(){
	}
	
	public void initBroker(){
		try {
			server = new ServerSocket(1111);
			for(;;){
				System.out.println("Esperando conexi—n");
				new Thread(new ProcessClient(server.accept())).start();	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[]){
		new Broker().initBroker();
	}

}
