package broker.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ProxyServer {

	ServerSocket server;
	public ProxyServer(){
	}
	
	public void initServer(){
		try {
			server = new ServerSocket(1112);
			for(;;){
				System.out.println("SERVER: Esperando conexi—n");
				new Thread(new ProcessBrokerRequest(server.accept())).start();	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[]){
		new ProxyServer().initServer();
	}
	
	
}
