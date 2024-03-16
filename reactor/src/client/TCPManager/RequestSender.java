package client.TCPManager;

import java.io.PrintStream;

public class RequestSender {
    private SendingRequestStrategy strategy;
    public RequestSender(SendingRequestStrategy strategyGiven) {
        strategy = strategyGiven;
    }
    public void performSending(PrintStream sockOut) {
        strategy.sendRequest(sockOut);
    }
    public void setSenderStrategy(SendingRequestStrategy strategyGiven){
        strategy = strategyGiven;
    }
}
