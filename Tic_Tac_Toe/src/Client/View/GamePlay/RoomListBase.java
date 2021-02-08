package Client.View.GamePlay;

import Client.ClientCore.Board;
import Client.Controller.GameplayController;
import Core.Player;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Optional;
import java.util.logging.Level;
import static java.util.logging.Level.parse;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import static login_sign_up.loginBase.bufferedReader;
import static login_sign_up.loginBase.bufferedWriter;
import static login_sign_up.loginBase.dis;
import static login_sign_up.loginBase.me;
import static login_sign_up.loginBase.mySocket;
import static login_sign_up.loginBase.objIn;
import static login_sign_up.loginBase.ps;

public class RoomListBase extends AnchorPane implements Runnable {

//    protected ImageView imageView;
//    protected ImageView imageView0;
    protected final ImageView imageView1;
    protected final ImageView imageView2;
    protected Label label;
    protected final ImageView imageView3;
    public  volatile ScrollPane scrollPane;
    protected final AnchorPane anchorPane;
    protected final VBox vBox;
    Stage p;

    

//     List<String> History = new ArrayList<String>();
    protected VBox BoxDialog;
    Thread th;

    public RoomListBase(Stage primaryStage) {
        p = primaryStage;
        try {
            bufferedWriter.write("3");
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(RoomListBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        th = new Thread(this);
        th.start();
        BoxDialog = new VBox();
        BoxDialog.setPrefWidth(350);
        label = new Label();
        imageView1 = new ImageView();
        imageView2 = new ImageView();
//        vBox = new VBox();
        imageView3 = new ImageView();
        scrollPane = new ScrollPane();
        anchorPane = new AnchorPane();
        vBox = new VBox();


        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(700.0);
        getStylesheets().add("style.css");
        
        vBox.setLayoutX(0);
        vBox.setLayoutY(7.0);
        vBox.setPrefHeight(USE_COMPUTED_SIZE);
        vBox.setPrefWidth(USE_COMPUTED_SIZE);
        vBox.setBackground(Background.EMPTY);

        imageView1.setFitHeight(48.0);
        imageView1.setFitWidth(222.0);
        imageView1.setId("logo");
        imageView1.setLayoutX(14.0);
        imageView1.setLayoutY(14.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("logo.jpg").toExternalForm()));

        imageView2.setFitHeight(40.0);
        imageView2.setFitWidth(40.0);
        imageView2.setLayoutX(648.0);
        imageView2.setLayoutY(14.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);
        imageView2.setImage(new Image(getClass().getResource("exit1.png").toExternalForm()));
        imageView2.setOnMouseClicked((event) -> {
            Alert exitAlert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
            Optional response = exitAlert.showAndWait();
            if (response.isPresent() && response.get() == ButtonType.YES) {
                Platform.exit();
            }
        });

        imageView3.setFitHeight(40.0);
        imageView3.setFitWidth(40.0);
        imageView3.setLayoutX(14.0);
        imageView3.setLayoutY(345.0);
        imageView3.setPickOnBounds(true);
        imageView3.setPreserveRatio(true);
        imageView3.setImage(new Image(getClass().getResource("back.png").toExternalForm()));
        imageView3.setOnMouseClicked((event) -> {
            JoinOnline returnRoot = new JoinOnline(primaryStage);
            primaryStage.getScene().setRoot(returnRoot);
        });

        scrollPane.setLayoutX(92.0);
        scrollPane.setLayoutY(124.0);
        scrollPane.setPrefHeight(220);
        scrollPane.setPrefWidth(510.0);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
//        scrollPane.setBackground(Background.EMPTY);
//        scrollPane.

        anchorPane.setMinHeight(0.0);
        anchorPane.setMinWidth(0.0);
        anchorPane.setPrefHeight(204.0);
        anchorPane.setPrefWidth(552.0);
//        anchorPane.setStyle("-fx-background-color: none;");
        anchorPane.setBackground(Background.EMPTY);

        

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setId("title");
        label.setLayoutX(257.0);
        label.setLayoutY(54.0);
        label.setPrefHeight(70.0);
        label.setPrefWidth(289.0);
        label.setText("waiting rooms ");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#ee1818"));
        label.setFont(new Font("Algerian", 36.0));

        getChildren().add(imageView1);
        getChildren().add(imageView2);
        getChildren().add(label);
        getChildren().add(imageView3);
        anchorPane.getChildren().add(BoxDialog);
        getChildren().add(scrollPane);

    }

    @Override
    public void run() {
//         System.out.println("les");
//      while(true){
        String playerName = "";
        String roomID = "";
//            System.out.println("les");
        try {
            String rooms = bufferedReader.readLine();

            System.out.println("list updated " + rooms);
            if (rooms != null) {
                for (String room : rooms.split(",")) {
                    String[] data = room.split("-");
                    playerName = data[1];
                    roomID = data[0];

                    
                    Label label0 = new Label();
                    Label label1 = new Label();
                    Label label2 = new Label();
                    
                    ImageView imageView = new ImageView();
                    ImageView imageView0 = new ImageView();
                    ImageView imageView4 = new ImageView();
                    
                    HBox hBox = new HBox();
                    
                    imageView.setFitHeight(62.0);
                    imageView.setFitWidth(56.0);
                    imageView.setLayoutX(600.0);
                    imageView.setLayoutY(337.0);
                    imageView.setPickOnBounds(true);
                    imageView.setPreserveRatio(true);
                    imageView.setImage(new Image(getClass().getResource("x2.png").toExternalForm()));

                    imageView0.setFitHeight(40.0);
                    imageView0.setFitWidth(40.0);
                    imageView0.setLayoutX(648.0);
                    imageView0.setLayoutY(345.0);
                    imageView0.setPickOnBounds(true);
                    imageView0.setPreserveRatio(true);
                    imageView0.setImage(new Image(getClass().getResource("o.png").toExternalForm()));

                    imageView4.setFitHeight(56.0);
                    imageView4.setFitWidth(48.0);
                    imageView4.setId("user");
                    imageView4.setPickOnBounds(true);
                    imageView4.setPreserveRatio(true);
                    imageView4.setImage(new Image(getClass().getResource("user1.png").toExternalForm()));

                    label0.setAlignment(javafx.geometry.Pos.CENTER);
                    label0.setPrefHeight(17.0);
                    label0.setPrefWidth(125);
                    label0.setText(playerName);
                    label0.setTextFill(javafx.scene.paint.Color.valueOf("#dd1111"));
                    label0.setFont(new Font("System Bold", 12.0));

                    label1.setAlignment(javafx.geometry.Pos.CENTER);
                    label1.setPrefHeight(17.0);
                    label1.setPrefWidth(180);
                    label1.setText("room number : " + roomID);
                    label1.setTextFill(javafx.scene.paint.Color.valueOf("#dd1111"));
                    label1.setFont(new Font("System Bold", 12.0));

                    label2.setAlignment(javafx.geometry.Pos.CENTER);
                    label2.setPrefHeight(17.0);
                    label2.setText("wating");
                    label2.setTextFill(javafx.scene.paint.Color.valueOf("#dd1111"));
                    label2.setFont(new Font("System Bold", 12.0));

                    HBox.setMargin(label0, new Insets(0, 30, 0, 0));
                    HBox.setMargin(label1, new Insets(0, 30, 0, 0));
                    HBox.setMargin(label2, new Insets(0, 30, 0, 0));

                    
                    hBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                    hBox.setBackground(Background.EMPTY);
                    hBox.getChildren().add(imageView4);
                    hBox.getChildren().add(label0);
                    hBox.getChildren().add(label1);
                    hBox.getChildren().add(label2);
                    
                    
                    Platform.runLater(()->{
                        vBox.getChildren().add(hBox);
                        scrollPane.setContent(vBox);
                    });
                    hBox.setOnMouseClicked(event -> {

                        try {
                            bufferedWriter.write("roomID-" + data[0]);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();
                            System.out.print("waiting for loop");

                            System.out.print("waiting for obj");
                            String st;
                            st = bufferedReader.readLine();

                            System.out.println("==========================");
                            System.out.println(st + "/////////");
                            if (st != null) {

                                String[] listOfUsers = st.split("-");
                                Board x = new Board();
                                System.out.println(me.getID() + ":" + me.getName());
                                if(me.getID()!=parseInt(listOfUsers[1])){
                                     x.setPlayerX(new Player(parseInt(listOfUsers[1]),listOfUsers[3],""));
                                     x.setPlayerO(me);
                                }else {
                                    x.setPlayerX(new Player(parseInt(listOfUsers[0]),listOfUsers[2],""));
                                    x.setPlayerO(me);
                                }
                             x.setRoomID(Integer.parseInt(listOfUsers[4]));

                                BoardBase board = new BoardBase();
                                System.out.print(x.getPlayerX());
                                System.out.print(x.getPlayerO());
                                GameplayController gameController = new GameplayController(board, p, mySocket, x);
                                gameController.setGameBoard(x);
                                System.out.println(x.getPlayerX().getID() + "PlayerX");
                                System.out.print(x.getPlayerO().getID() + "PlayerO");
                                gameController.setPlayer(x.getPlayerX());
                                gameController.setOpponent(x.getPlayerO());
                                gameController.startGame();
                                System.out.println(p);
                                p.getScene().setRoot(board);
                                p.show();
                            }
                        } catch (IOException ex) {
                            ex.getStackTrace();
                        }
                    });
                    System.out.println(room);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
//}
