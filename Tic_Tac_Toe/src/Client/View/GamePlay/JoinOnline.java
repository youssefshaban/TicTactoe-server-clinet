package Client.View.GamePlay;

import Client.ClientCore.Board;
import Client.Controller.GameplayController;
import Core.Player;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Worker.State;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import static login_sign_up.loginBase.bufferedReader;
import static login_sign_up.loginBase.bufferedWriter;
import static login_sign_up.loginBase.dis;
import static login_sign_up.loginBase.me;
import static login_sign_up.loginBase.mySocket;
import static login_sign_up.loginBase.objIn;
import static login_sign_up.loginBase.ps;
import login_sign_up.startBase;

public class JoinOnline extends AnchorPane {

    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final ImageView imageView1;
    protected final Button joinRoomBtn;
    protected final Button createNewRoomBtn;
    protected final ImageView imageView2;
    protected final Label CreateBtn;
    protected final Label OpenRoomsBtn;
    protected final ImageView imageView3;
    protected final Label label1;
    protected final Label PlayerName;
    protected final ImageView imageView4;

    public JoinOnline(Stage primaryStage) {

        imageView = new ImageView();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        joinRoomBtn = new Button();
        createNewRoomBtn = new Button();
        imageView2 = new ImageView();
        CreateBtn = new Label();
        OpenRoomsBtn = new Label();
        imageView3 = new ImageView();
        label1 = new Label();
        PlayerName = new Label();
        imageView4 = new ImageView();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(700.0);
        getStylesheets().add("/Client/View/GamePlay/style.css");

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

        imageView1.setFitHeight(116.0);
        imageView1.setFitWidth(97.0);
        imageView1.setId("user");
        imageView1.setLayoutX(87.0);
        imageView1.setLayoutY(108.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("user3.png").toExternalForm()));

//        joinRoomBtn.setBlendMode(javafx.scene.effect.BlendMode.GREEN);
        joinRoomBtn.setLayoutX(255.0);
        joinRoomBtn.setLayoutY(194.0);
        joinRoomBtn.setPrefHeight(86.0);
        joinRoomBtn.setPrefWidth(331.0);
        joinRoomBtn.getStyleClass().add("button1");
        joinRoomBtn.setTextFill(javafx.scene.paint.Color.valueOf("#2ea5e1"));
        joinRoomBtn.setFont(new Font("Algerian", 36.0));
        joinRoomBtn.setText("Join Room");

//        createNewRoomBtn.setBlendMode(javafx.scene.effect.BlendMode.GREEN);
        createNewRoomBtn.setLayoutX(255.0);
        createNewRoomBtn.setLayoutY(294.0);
        createNewRoomBtn.setMnemonicParsing(false);
        createNewRoomBtn.setPrefHeight(86.0);
        createNewRoomBtn.setPrefWidth(331.0);
        createNewRoomBtn.setCursor(Cursor.DEFAULT);
        createNewRoomBtn.setFont(new Font("Algerian", 36.0));
        createNewRoomBtn.setText("Create Room");
        createNewRoomBtn.setTextFill(javafx.scene.paint.Color.valueOf("#2ea5e1"));

        imageView2.setFitHeight(48.0);
        imageView2.setFitWidth(222.0);
        imageView2.setId("logo");
        imageView2.setLayoutX(14.0);
        imageView2.setLayoutY(14.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);
        imageView2.setImage(new Image(getClass().getResource("logo.jpg").toExternalForm()));

//        CreateBtn.setAlignment(javafx.geometry.Pos.CENTER);
//        CreateBtn.setId("mytext");
//        CreateBtn.setLayoutX(255.0);
//        CreateBtn.setLayoutY(305.0);
//        CreateBtn.setPrefHeight(64.0);
//        CreateBtn.setPrefWidth(331.0);
//        CreateBtn.getStyleClass().add("text");
//        CreateBtn.getStylesheets().add("/Client/View/GamePlay/style.css");
//        CreateBtn.setText("create  new room");
//        CreateBtn.setTextFill(javafx.scene.paint.Color.valueOf("#2ea5e1"));
//        CreateBtn.setFont(new Font("Algerian", 34.0));
//        OpenRoomsBtn.setId("mytext");
//        OpenRoomsBtn.setLayoutX(311.0);
//        OpenRoomsBtn.setLayoutY(200.0);
//        OpenRoomsBtn.setPrefHeight(74.0);
//        OpenRoomsBtn.setPrefWidth(220.0);
//        OpenRoomsBtn.setStyle("-fx-effect-: dropshadow; hadoe-color: red;");
//        OpenRoomsBtn.setText("open rooms");
//        OpenRoomsBtn.setTextFill(javafx.scene.paint.Color.valueOf("#2ea5e1"));
//        OpenRoomsBtn.setFont(new Font("Algerian", 34.0));
        imageView3.setFitHeight(40.0);
        imageView3.setFitWidth(40.0);
        imageView3.setLayoutX(648.0);
        imageView3.setLayoutY(14.0);
        imageView3.setPickOnBounds(true);
        imageView3.setPreserveRatio(true);
        imageView3.setImage(new Image(getClass().getResource("exit1.png").toExternalForm()));
        imageView3.setOnMouseClicked((event) -> {
            Alert exitAlert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
            Optional response = exitAlert.showAndWait();
            if (response.isPresent() && response.get() == ButtonType.YES) {
                Platform.exit();
            }
        });

        label1.setLayoutX(219.0);
        label1.setLayoutY(73.0);
        label1.setPrefHeight(70.0);
        label1.setPrefWidth(358.0);
        label1.setText("Online game");
        label1.setTextFill(javafx.scene.paint.Color.valueOf("#ee1818"));
        label1.setFont(new Font("Kinda 3D", 46.0));

        PlayerName.setAlignment(javafx.geometry.Pos.CENTER);
        PlayerName.setLayoutX(59.0);
        PlayerName.setLayoutY(222.0);
        PlayerName.setPrefHeight(17.0);
        PlayerName.setPrefWidth(154.0);
        PlayerName.setTextFill(javafx.scene.paint.Color.valueOf("#dd1111"));
        PlayerName.setFont(new Font("System Bold", 20.0));
        PlayerName.setText(me.getName());

        imageView4.setFitHeight(40.0);
        imageView4.setFitWidth(40.0);
        imageView4.setLayoutX(14.0);
        imageView4.setLayoutY(345.0);
        imageView4.setPickOnBounds(true);
        imageView4.setPreserveRatio(true);
        imageView4.setImage(new Image(getClass().getResource("back.png").toExternalForm()));
        imageView4.setOnMouseClicked((event) -> {
            startBase returnRoot = new startBase(primaryStage);
            primaryStage.getScene().setRoot(returnRoot);
        });

        joinRoomBtn.setOnAction(event -> {
            Pane root = new Pane();
            root.getChildren().add(new RoomListBase(primaryStage));
            primaryStage.getScene().setRoot(root);
            primaryStage.show();

        });

        createNewRoomBtn.setOnAction(event -> {
            try {
                bufferedWriter.write("2");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException ex) {
                Logger.getLogger(JoinOnline.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("button0");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Waiting...");
            alert.setHeaderText(null);
            alert.setContentText("Waiting for other player to join. Please wait...");

            alert.show();
            Thread th = new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JoinOnline.class.getName()).log(Level.SEVERE, null, ex);
                }

                Platform.runLater(() -> {
                    try {
                        Board B;
                        String msg;
                        msg = bufferedReader.readLine();
                        System.out.println("create room " + msg);
                        if (msg != null) {
                            alert.close();
                            B = new Board();
                            String[] list = msg.split("-");
                            System.out.println(me.getID() + ":" + me.getName());
                            if (me.getID() != parseInt(list[0])) {
                                B.setPlayerO(new Player(parseInt(list[0]), list[2], ""));
                                B.setPlayerX(me);
                            } else {
                                B.setPlayerO(new Player(parseInt(list[1]), list[3], ""));
                                B.setPlayerX(me);
                            }
                            B.setRoomID(Integer.parseInt(list[4]));

                            BoardBase bord = new BoardBase();
                            GameplayController gameController = new GameplayController(bord, primaryStage, mySocket, B);
                            gameController.setPlayer(B.getPlayerX());
                            gameController.setOpponent(B.getPlayerO());

                            gameController.startGame();
//                            Scene scene = new Scene(bord, 700, 420);
                            primaryStage.getScene().setRoot(bord);
                            primaryStage.show();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(JoinOnline.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

            });
            th.start();

        });
        getChildren().add(imageView);
        getChildren().add(imageView0);
        getChildren().add(imageView1);
        getChildren().add(joinRoomBtn);
        getChildren().add(createNewRoomBtn);
        getChildren().add(imageView2);
        getChildren().add(CreateBtn);
        getChildren().add(OpenRoomsBtn);
        getChildren().add(imageView3);
        getChildren().add(label1);
        getChildren().add(PlayerName);
        getChildren().add(imageView4);

    }

    public Label getPlayerName() {
        return PlayerName;
    }

    public Button getCreateNewRoomBtn() {
        return createNewRoomBtn;
    }

}
