package main.Connect_to_clinet.Controller;

import main.Connect_to_clinet.Model.ConnectToDatabaseModel;
import main.Connect_to_clinet.View.ConnectToClinetView;
import main.Core.*;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author youssefshaban
 */
public class ConnectToClinetController extends Thread {
    final String secretKey = "ssshhhhhhhhhhh!!!!";
    ConnectToClinetView View = new ConnectToClinetView();
    Socket soket;
    public Player playerInfo = null;
    BufferedReader bufferedReader;
    public BufferedWriter bufferedWriter;
    PlayRoom roomInfo = new PlayRoom();
    String[] msg;
    volatile Queue<String> q = new LinkedList();
    String message;
    String UserOperation;
    public ObjectOutputStream objectOutputStream;
    InputStream inputStream;
    OutputStream outputStream;
    Thread th;
    ConnectToDatabaseModel con = new ConnectToDatabaseModel();
    static Vector<ConnectToClinetController> clientsVector = new Vector<>();
    static Vector<PlayRoom> rooms = new Vector<>();

    public ConnectToClinetController(Socket s) {
        soket = s;
        try {
            playerInfo = new Player();
            bufferedReader = new BufferedReader(new InputStreamReader(soket.getInputStream(), StandardCharsets.UTF_8));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(soket.getOutputStream(), StandardCharsets.UTF_8));
            inputStream = soket.getInputStream();
            outputStream = soket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            clientsVector.add(this);
            th = new Thread(this);
            th.start();
        } catch (IOException e) {
            Logger.getLogger(ConnectToClinetController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void run() {
        while (playerInfo.getID() == 0) {
            try {
                UserOperation = bufferedReader.readLine();
                msg = UserOperation.split("-");
                if (msg == null) {
                    closeConnection();
                    break;
                }
                if (msg[0].contains("L"))
                    login();
                else if (msg[0].contains("S"))
                    signUp();
            } catch (IOException ioException) {
                Logger.getLogger(ConnectToClinetController.class.getName()).log(Level.SEVERE, null, ioException);
            }
        }
        while (true) {
            try {
                message = bufferedReader.readLine();
                if (message == null) {
                    closeConnection();
                    break;
                }
                if (message != null) {
                    if (message.equals("1")) {
                        getAllHistiry();
                    } else if (message.equals("2")) {
                        addNewRoom();
                    } else if (message.equals("3")) {
                        getOpenRooms();
                    } else {
                        String[] msg = message.split("-", 0);
                        if (msg[0].equals("roomID")) {
                            joinRoom();
                        } else if (msg[0].equals("historyID")) {
                            getRoomHistory();
                        } else if (msg[0].equals("step")) {
                            insertStep();
                        }
                    }
                }

            } catch (IOException ex) {
                try {
                    closeConnection();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Logger.getLogger(ConnectToClinetController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    void closeConnection() throws IOException {


        bufferedWriter.close();
        bufferedReader.close();
        inputStream.close();
        soket.close();

        clientsVector.remove(this);
    }

    void getAllHistiry() {
        Vector<String> history = new Vector<String>();
        Vector<PlayRoom> listOfRooms = null;
        try {
            listOfRooms = con.getAllHistory(playerInfo.getID());

        } catch (SQLException ex) {
            Logger.getLogger(ConnectToClinetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (listOfRooms == null)
            return;
        for (PlayRoom room : listOfRooms) {
            if (room != null) {
                history.add(room.getID() + "-"
                        + room.getPlayerX().getName() + "-"
                        + room.getPlayerO().getName()
                );
            }
        }
        try {
            System.out.println(history);
            View.sendHistory(history, bufferedWriter);
        } catch (IOException ex) {
            Logger.getLogger(ConnectToClinetController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void addNewRoom() {
        roomInfo = new PlayRoom();
        Board b = new Board();
        int check = 0;
        try {
            check = con.insertRoom(playerInfo.getID());
        } catch (SQLException ex) {
            Logger.getLogger(ConnectToClinetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (check != 0) {
            rooms.add(new PlayRoom(check, playerInfo, Instant.now()));
            b.setPlayerX(playerInfo);
        } else {
            Logger.getLogger(ConnectToClinetController.class.getName()).log(Level.SEVERE, "error in insertion " + Instant.now());
        }
    }

    void getRoomHistory() {
        String board2 = "";
        int roomIDFromClient = Integer.parseInt(message.substring(10));
        try {
            board2 = con.getRoomHistory(roomIDFromClient);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectToClinetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        View.sendRoomHistory(board2, bufferedWriter);
    }

    void joinRoom() {
        Board b = new Board();
        String s = "";
        int roomIDFromClient = Integer.parseInt(message.substring(7));
        try {
            s = con.insertPlayer(roomIDFromClient, playerInfo);
            roomInfo.setRoomID(roomIDFromClient);
            rooms.stream().filter(room -> room.getID() == roomIDFromClient).forEach(room -> room.setPlayerO(playerInfo));
        } catch (SQLException ex) {
            Logger.getLogger(ConnectToClinetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (b != null) {
            try {
                System.out.println("s");
                View.sendToAll(s, objectOutputStream, clientsVector, roomIDFromClient);
            } catch (IOException ex) {
                Logger.getLogger(ConnectToClinetController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
    }

    void insertStep() {
        while (true) {
            try {
                String step = bufferedReader.readLine();
                System.out.println(step);
                if(step.equals("null"))
                    break;
                if (step != null)
                    q.add(step);
                rooms.stream().filter(room -> room.getID() == Integer.parseInt(step.split("-")[2])).forEach(room -> {
                    try {
                        View.sendStep(room, objectOutputStream, clientsVector, step, playerInfo.getID());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });

            } catch (IOException ex) {
                Logger.getLogger(ConnectToClinetController.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    closeConnection();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            Thread x = new Thread(() -> {
                while (q.isEmpty() != true)
                    try {
                        int result = con.insertStep(q.peek());
                        if (result != 0)
                            q.poll();
                    } catch (SQLException ex) {
                        Logger.getLogger(ConnectToClinetController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            });
            x.start();

        }
    }

    void getOpenRooms() {
        try {
            Vector<String> history = new Vector<String>();
            Vector<PlayRoom> listOfRooms = con.getOpenRooms();
            if (listOfRooms == null)
                return;
            for (PlayRoom room : listOfRooms) {

                if (room != null) {
                    history.add(room.getID() + "-"
                            + room.getPlayerX().getName()
                    );
                }
            }
            View.sendHistory(history, bufferedWriter);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ConnectToClinetController.class.getName()).log(Level.SEVERE, Instant.now().toString(), ex);
        }
    }

    void login() {
        int checker = 0;
        try {
            String encryptedString = AES.encrypt(msg[2], secretKey);
            checker = con.UserLogin(new Player(msg[1], encryptedString));
        } catch (SQLException ex) {
            Logger.getLogger(ConnectToClinetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (checker != 0) {
            playerInfo.setID(checker);
            playerInfo.setName(msg[1]);
            playerInfo.setPassword(msg[2]);
            View.sendUserAcceptance(checker, bufferedWriter);
        } else {
            View.sendUserAcceptance(checker, bufferedWriter);
        }
    }

    void signUp() {
        int id = 0;
        String encryptedString = AES.encrypt(msg[2], secretKey);
        try {
            id = con.signUp(new Player(msg[1], encryptedString));
        } catch (SQLException ex) {
            Logger.getLogger(ConnectToClinetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (id != 0) {
            playerInfo.setID(id);
            playerInfo.setName(msg[1]);
            playerInfo.setPassword(encryptedString);
            View.sendUserAcceptance(id, bufferedWriter);
        } else {
            View.sendUserAcceptance(id, bufferedWriter);
        }
    }
}
