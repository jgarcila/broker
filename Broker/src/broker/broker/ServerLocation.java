package broker.broker;

public enum ServerLocation {
	SERVER_1 ("127.0.0.1", 1112),
	SERVER_2 ("127,0,0,1", 123);
	private final String ip;
	private final int port;
	ServerLocation(String ip, int port){
		this.ip = ip;
		this.port = port;
	}
	public String getIp() {
		return ip;
	}
	public int getPort() {
		return port;
	}
}
