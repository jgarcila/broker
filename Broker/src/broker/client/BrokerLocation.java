package broker.client;

public enum BrokerLocation {
	B_1 ("127.0.0.1", 1111),
	B_2 ("127,0,0,1", 123);
	private final String ip;
	private final int port;
	BrokerLocation(String ip, int port){
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
