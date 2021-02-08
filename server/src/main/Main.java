package main;

import main.Connect_to_clinet.Controller.ConnectToClinetController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {


    public static void main(String[] args) {
        try {

            ServerSocket serverSocket;
            serverSocket = new ServerSocket(5004);
            while (true) {
                Socket socket = serverSocket.accept();
                new ConnectToClinetController(socket);
            }
        } catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, Instant.now().toString(), e);

        }

    }
}
