package socket.server;

import socket.connection.Connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//TODO
public class Server extends Connection implements Runnable
{
    public Server() throws IOException {
        super("server");
    }

    public void startServer()
    {
        try {

            clientSocket = serverSocket.accept();

            System.out.println("Client Online");

            clientOutput = new DataOutputStream(clientSocket.getOutputStream());

            clientOutput.writeUTF("Petition received and accepted\n");

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while ((serverMessage = reader.readLine()) != null)
            {
                System.out.println(serverMessage);
            }

            System.out.println("End of the connection\n");

            serverSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

    }
}
