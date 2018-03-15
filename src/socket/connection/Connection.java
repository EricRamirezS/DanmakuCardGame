package socket.connection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 25-02-2018.
 * Github Account: https://github.com/EricRamirezS
 */

//TODO
public class Connection {
    private final int PORT = 1234; //Connection's port
    private final String HOST = "localhost"; //Connection's host
    protected String serverMessage; //Server's incoming message
    protected ServerSocket serverSocket; //Server's socket
    protected Socket clientSocket; //Client's socket
    protected DataOutputStream serverOutput, clientOutput; //Output data flow

    public Connection(String type) throws IOException {
        if(type.equalsIgnoreCase("server")) {
            serverSocket = new ServerSocket(PORT);
            clientSocket = new Socket();
        }
        else {
            clientSocket = new Socket(HOST, PORT);
        }
    }
}
