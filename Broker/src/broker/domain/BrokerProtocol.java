package broker.domain;

public interface BrokerProtocol {

	static final int BROKER_READY_TO_RECEIVE_MSG 	= 1;
	static final int CLIENT_MSG_SENT 				= 2;
	static final int BROKER_MSG_RECEIVED			= 3;
	static final int CLIENT_REQ_FOR_A_SERVICE		= 4;
	
	static final int SERVER_READY_TO_RECEIVE_REQ	= 5;
	static final int SERVER_REQ_RECEIVED			= 6;
	
	
}
