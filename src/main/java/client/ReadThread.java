package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class ReadThread implements Runnable{

    private Socket server;

    private BufferedReader in;

    @Override
    public void run() {
        try {
            while (!server.isInputShutdown()) {
                String message = in.readLine();

                if(Objects.isNull(message)){
                    break;
                }

                System.out.println(message);
            }
        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    public ReadThread(Socket server, BufferedReader in) {
        this.server = server;
        this.in = in;
    }
}
