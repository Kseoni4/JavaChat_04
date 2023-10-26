package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerListener {

    private ServerSocket serverSocket;

    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    public void start() throws IOException {
        serverSocket = new ServerSocket(27015);

        System.out.println("Сервер запущен");

        ChatLog log = new ChatLog();

        while (true) {
            Socket client = serverSocket.accept();

            ClientHandler clientHandler = new ClientHandler(client, log);
            clientHandlers.add(clientHandler);

            Thread clientThread = new Thread(clientHandler);
            clientThread.start();
        }
    }

    public static List<ClientHandler> getClients(){
        return clientHandlers;
    }

    public static void removeClient(ClientHandler clientHandler){
        clientHandlers.remove(clientHandler);
    }

}
