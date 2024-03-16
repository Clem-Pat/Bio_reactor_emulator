package client.TCPManager;

import java.io.PrintStream;

public interface SendingRequestStrategy {
    void sendRequest(PrintStream socOut);
}
