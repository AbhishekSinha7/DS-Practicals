import java.io.*;
import java.net.*;
import java.util.*;

public class BerkeleyAlgorithm {
    private static final int PORT = 1024;

    public static void main(String[] args) throws Exception {
        // Create a server socket to listen for incoming messages
        ServerSocket serverSocket = new ServerSocket(PORT);

        // Create a list to store the time differences for each node
        List<Long> timeDiffs = new ArrayList<Long>();

        // Create a new thread to handle the time requests from nodes
        Thread timeServerThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        // Wait for a node to connect and request the current time
                        Socket clientSocket = serverSocket.accept();
                        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

                        // Read the current time from the node's request
                        Date clientTime = (Date) in.readObject();
                        // Process the client time and calculate the time difference
                        long currentTime = System.currentTimeMillis();
                        long timeDiff = currentTime - clientTime.getTime();
                        timeDiffs.add(timeDiff);
                        System.out.println("Received time from node: " + clientTime);
                        System.out.println("Time difference: " + timeDiff + " ms");
                        // Close the input stream and socket
                        in.close();
                        clientSocket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Start the time server thread
        timeServerThread.start();

        // Create a new thread to send the current time to the server
        Thread timeClientThread = new Thread(new Runnable() {
            public void run() {
                try {
                    // Wait for a while before sending the current time
                    Thread.sleep(1000);
                    // Connect to the server and send the current time
                    Socket socket = new Socket("localhost", PORT);
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject(new Date());
                    out.close();
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Start the time client thread
        timeClientThread.start();
    }
}
