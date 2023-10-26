package client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread implements Runnable{

    Socket server;

    BufferedWriter out;

    @Override
    public void run() {
        try{
            Scanner input = new Scanner(System.in);

            System.out.println("Введите никнейм: ");

            while (!server.isOutputShutdown()){
                String outputMessage = input.nextLine();

                out.write(outputMessage);
                out.newLine();
                out.flush();
            }
        } catch (IOException e){
            throw new RuntimeException();
        }

    }

    public WriteThread(Socket server, BufferedWriter out) {
        this.server = server;
        this.out = out;
    }
}
