package server;

import java.io.IOException;
import java.util.ArrayList;

public class ChatLog {

    private ArrayList<String> chatHistory;

    private int pointer = 0;

    public void putMessage(String message, ClientHandler clientHandler) {
        chatHistory.add(message);
        System.out.println(message);
        update(clientHandler);
        pointer++;
    }

    private void update(ClientHandler clientHandler) {
        for(ClientHandler client : ServerListener.getClients()){
            if(client != clientHandler){
                client.sendMessageToClient(chatHistory.get(pointer));
            }
        }
    }


    public ChatLog() {
        this.chatHistory = new ArrayList<>();
    }
}
