package main.Connect_to_clinet.View;


import main.Connect_to_clinet.Controller.ConnectToClinetController;
import main.Core.PlayRoom;
import main.Core.Player;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Vector;

import static java.lang.Thread.sleep;


public class ConnectToClinetView {
    public void sendToAll(String obj, ObjectOutputStream objectOutputStream, Vector<ConnectToClinetController> clientsVector, int roomIDFromClient) throws IOException {
        for (ConnectToClinetController client : clientsVector) {
            if (
                    Integer.parseInt(obj.split("-")[0]) ==
                            client.playerInfo.getID()
                            || Integer.parseInt(obj.split("-")[1]) ==
                            client.playerInfo.getID()) {
                Thread th = new Thread(() -> {
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try {
                        client.bufferedWriter.write(obj + "-" + roomIDFromClient);
                        client.bufferedWriter.newLine();
                        client.bufferedWriter.flush();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                });
                th.start();
            }

        }
    }

    public void sendUserAcceptance(int status, BufferedWriter ps) {
        try {
            ps.write("S-" + status);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            ps.newLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            ps.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public void sendStep(PlayRoom obj, ObjectOutputStream objectOutputStream, Vector<ConnectToClinetController> clientsVector, String step, int pID) throws IOException {
        for (ConnectToClinetController client : clientsVector) {
            if ((obj.getPlayerO().getID() == client.playerInfo.getID())
                    || (obj.getPlayerX().getID() == client.playerInfo.getID())) {
                client.bufferedWriter.write(step);
                client.bufferedWriter.newLine();
                client.bufferedWriter.flush();


            }
        }
    }

    public void sendRoomHistory(String obj, BufferedWriter objectOutputStream){
        try {
            objectOutputStream.write(obj);
            objectOutputStream.newLine();
            objectOutputStream.flush();
        } catch (IOException ex) {


            ex.printStackTrace();
        }

    }


    public void sendHistory(List<String> list, BufferedWriter ps) throws IOException {


        if (list.size() > 0) {

            ps.write(String.join(",", list));
            ps.newLine();
            ps.flush();
        } else {

        }


    }

}
