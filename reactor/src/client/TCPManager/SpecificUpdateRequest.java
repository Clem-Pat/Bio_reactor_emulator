package client.TCPManager;

import java.io.PrintStream;

public class SpecificUpdateRequest implements SendingRequestStrategy{
    private final int order;

    public SpecificUpdateRequest(int orderGiven){
        order = orderGiven;
    }
    @Override
    public void sendRequest(PrintStream socOut) {
        System.out.println("Client wants to get values at time " + order);
        socOut.println("get " + order);
        socOut.flush();
    }
}
