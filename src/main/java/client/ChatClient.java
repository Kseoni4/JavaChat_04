package client;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket server = new Socket("192.168.91.162", 27015);

        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));

        if(server.isConnected()) {

            System.out.println("Подключение к серверу установлено");

            ReadThread readThread = new ReadThread(server, in);
            WriteThread writeThread = new WriteThread(server, out);

            Thread rThread = new Thread(readThread);
            Thread wThread = new Thread(writeThread);

            rThread.start();
            wThread.start();
        }

        while (server.isConnected()){
            Thread.onSpinWait();
        }
    }
}
