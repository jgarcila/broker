package broker.domain;
import java.util.Hashtable;


public class RequestInfo {

String serviceName;
Hashtable<String, Integer> data;
	
	public RequestInfo(String serviceName, Hashtable<String, Integer> data) {
		super();
		this.serviceName = serviceName;
		this.data = data;
	}

	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Hashtable<String, Integer> getData() {
		return data;
	}
	public void setData(Hashtable<String, Integer> data) {
		this.data = data;
	}
	
	
	
}
