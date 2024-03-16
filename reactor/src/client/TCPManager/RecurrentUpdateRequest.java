package client.TCPManager;

import java.io.PrintStream;

public class RecurrentUpdateRequest implements SendingRequestStrategy{
    @Override
    public void sendRequest(PrintStream socOut) {
        System.out.println("Client wants to udpate current values");
        socOut.println("update");
        socOut.flush();
    }
}
