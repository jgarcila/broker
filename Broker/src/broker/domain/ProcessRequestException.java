package broker.domain;

@SuppressWarnings("serial")
public class ProcessRequestException extends Exception {
	public ProcessRequestException(String txt){
		super(txt);
	}
}
