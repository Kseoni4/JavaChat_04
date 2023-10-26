package server;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class ClientHandler implements Runnable {

    Socket client;

    BufferedReader in;

    BufferedWriter out;

    ChatLog log;

    @Override
    public void run() {
        String nickName = "";
        try {

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            nickName = in.readLine();

            log.putMessage("\u001b[38;5;34m"+nickName + " connected to chat \u001b[38;5;0m", this);

            while(!Thread.currentThread().isInterrupted()){
                String message = in.readLine();

                if(Objects.isNull(message) || !client.isConnected()){
                    Thread.currentThread().interrupt();
                    continue;
                }

                log.putMessage(nickName+":"+message, this);
            }

        } catch (IOException e) {
            log.putMessage("\u001b[38;5;160m"+nickName + " disconnected from chat \u001b[38;5;0m", this);
        } finally {
            ServerListener.removeClient(this);
        }
    }

    public void sendMessageToClient(String message) {
        try {
            if (!client.isOutputShutdown()) {
                out.write(message);
                out.newLine();
                out.flush();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public ClientHandler(Socket client, ChatLog log) {
        this.client = client;
        this.log = log;
    }
}
