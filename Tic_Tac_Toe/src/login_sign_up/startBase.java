package login_sign_up;

import Client.ClientCore.AlreadyTakenException;
import Client.ClientCore.Board;
import Client.Controller.GameplayController;
import Client.View.GamePlay.BoardBase;
import Client.View.GamePlay.JoinOnline;
import Client.View.GamePlay.RoomListBase;
import Core.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import login_sign_up.loginBase;
import static login_sign_up.loginBase.bufferedReader;
import static login_sign_up.loginBase.bufferedWriter;
import static login_sign_up.loginBase.me;
import static login_sign_up.loginBase.mySocket;
import static login_sign_up.loginBase.objIn;
import static tic_tac_toe.Tic_Tac_Toe.tr;

public class startBase extends AnchorPane {

    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final Pane pane22;
    protected final ImageView imageView1;
    protected final ImageView imageView2;
    protected final Button button;
    protected final Button button0;
    protected final ImageView imageView3;
    protected final Label label;
    protected final Label label0;
    protected final Button button1;
    protected final ImageView imageView4;
    protected ScrollPane paneDialog;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
     public Alert a1;
    List<String> History = new ArrayList<String>();
    VBox BoxDialog;

    InputStream stream, stream1, stream2, stream3, stream4, stream5;

    public startBase(Stage primaryStage) {

        BoxDialog = new VBox();
//        pane = new Pane();
        pane22 = new Pane();
        paneDialog = new ScrollPane();
//            paneDialog.setMinSize(500, 200);
        paneDialog.setContent(BoxDialog);
//            paneDialog.setPrefWidth(100);
        paneDialog.setPrefSize(89 * 5.3, 300);
//            paneDialog.setFitToWidth(100.0);
        pane22.setPrefSize(89 * 5, 300);
        pane22.getChildren().add(paneDialog);

        imageView = new ImageView();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        imageView2 = new ImageView();
        button = new Button();
        button0 = new Button();
        imageView3 = new ImageView();
        label = new Label();
        label0 = new Label();
        button1 = new Button();
        imageView4 = new ImageView();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(700.0);
        getStylesheets().add("style.css");

        imageView.setFitHeight(70.0);
        imageView.setFitWidth(273.0);
        imageView.setId("welcome");
        imageView.setLayoutX(239.0);
        imageView.setLayoutY(19.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        try {
            stream = new FileInputStream("src/login_sign_up/wlcome8.jpg");
            Image image = new Image(stream);
            imageView.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(loginBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        // imageView.setImage(new Image(getClass().getResource("./src/startpage/wlcome8.jpg").toExternalForm()));
        imageView0.setFitHeight(62.0);
        imageView0.setFitWidth(56.0);
        imageView0.setLayoutX(600.0);
        imageView0.setLayoutY(337.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("x2.png").toExternalForm()));
       

        imageView1.setFitHeight(40.0);
        imageView1.setFitWidth(40.0);
        imageView1.setLayoutX(648.0);
        imageView1.setLayoutY(345.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("o.png").toExternalForm()));

        imageView2.setFitHeight(116.0);
        imageView2.setFitWidth(97.0);
        imageView2.setId("user");
        imageView2.setLayoutX(87.0);
        imageView2.setLayoutY(108.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);
        imageView2.setImage(new Image(getClass().getResource("user1.png").toExternalForm()));
        
        
        button.setLayoutX(350.0);
        button.setLayoutY(194.0);
        button.setPrefHeight(80.0);
        button.getStyleClass().add("button1");
        button.setTextFill(javafx.scene.paint.Color.valueOf("#2ea5e1"));
        button.setFont(new Font("Algerian", 48.0));
        button.setText("Offline");
        button.setOnAction(event -> {
            System.out.println("button");
            Pane root = new Pane();
            root.getChildren().add(new offlinegameBase(primaryStage));

//         primaryStage.initStyle(StageStyle.UNDECORATED);
//            Scene scene = new Scene(root, 600, 400);
//        
//            primaryStage.setTitle("tec tac toe");
//            primaryStage.setScene(scene);
            primaryStage.getScene().setRoot(root);
            primaryStage.show();
        });
        
        
        
        button0.setLayoutX(351.0);
        button0.setLayoutY(294.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(80.0);
        button0.setCursor(Cursor.DEFAULT);
        button0.setFont(new Font("Algerian", 48.0));
        button0.setTextFill(javafx.scene.paint.Color.valueOf("#2ea5e1"));
        button0.setText("Online  ");
        
        button0.setOnAction(event -> {
            System.out.println("button0");
            Pane root = new Pane();
            root.getChildren().add(new JoinOnline(primaryStage));

//         primaryStage.initStyle(StageStyle.UNDECORATED);
//        
//            primaryStage.setTitle("tec tac toe");
//            primaryStage.setScene(scene);
            primaryStage.getScene().setRoot(root);
            primaryStage.show();

//           ;
//           Pane root = new Pane();
//        root.getChildren().add(new offlinegameBase(primaryStage));
//      
////         primaryStage.initStyle(StageStyle.UNDECORATED);
//        Scene scene = new Scene(root, 600, 400);
////        
//        primaryStage.setTitle("tec tac toe");
//        primaryStage.setScene(scene);
//        primaryStage.show();
        });
        imageView3.setFitHeight(48.0);
        imageView3.setFitWidth(222.0);
        imageView3.setId("logo");
        imageView3.setLayoutX(14.0);
        imageView3.setLayoutY(14.0);
        imageView3.setPickOnBounds(true);
        imageView3.setPreserveRatio(true);
        imageView3.setImage(new Image(getClass().getResource("logo.jpg").toExternalForm()));

        

        
        

//        button1.setBlendMode(javafx.scene.effect.BlendMode.GREEN);
        button1.setLayoutX(40.0);
        button1.setLayoutY(303.0);
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(64.0);
//        button1.setPrefWidth(205.0);
        button1.setText("History");
        button1.setTextFill(javafx.scene.paint.Color.valueOf("#2fa5e0"));
        button1.setFont(new Font("Algerian", 44.0));
        
        button1.setOnAction(event -> {
            try {
                System.out.println("button1");
                bufferedWriter.write("1");
                bufferedWriter.newLine();
                bufferedWriter.flush();
                History.clear();
                
                System.out.println("history");
                Thread th = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String MSG = bufferedReader.readLine();
                            String[] listHistory = null;
                            if (MSG != null) {
                                listHistory = MSG.split(",");
                            }
                            for (String h : listHistory) {
                                History.add(h);
                                System.out.println(h);
                                Button x = new Button();
                                x.setText(h);
                                x.setPrefWidth(89 * 5);
                                x.setPadding(new Insets(12));
                                x.setOnAction(event -> {
                                    try {
                                        System.out.println("history list id " + h.split("-")[0]);
                                        bufferedWriter.write("historyID-" + h.split("-")[0]);
                                        bufferedWriter.newLine();
                                        bufferedWriter.flush();
                                        String x122 = bufferedReader.readLine();
                                          Platform.runLater(()->{a1.close();});
                                        Board x2 = new Board();
                                        System.out.println(x122);
                                        String[] game=x122.split("-");
                                        Player playerX=new Player(Integer.parseInt(game[0]),game[3],"");
                                        Player playerO=new Player(Integer.parseInt(game[1]),game[4],"");
                                        x2.setPlayerX(playerX);
                                        x2.setPlayerO(playerO);
                                        String[] locations= game[2].split(",");
                                        for (int v =0;v<locations.length;v++){
                                            try {
                                                if(Integer.parseInt(locations[v])!= -1){
                                                    System.out.println(locations[v]);
                                                x2.take(Integer.parseInt(locations[v]));
                                                }
                                            } catch (AlreadyTakenException ex) {
                                                Logger.getLogger(startBase.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                        System.out.println(x2.getGrid()+"-"+x2.getHistory()+x2.gameEnded());
                                            BoardBase board = new BoardBase();
                                            GameplayController gameController = new GameplayController(board, primaryStage, null,x2);
                                            gameController.setGameBoard(x2);
                                            gameController.setPlayer(x2.getPlayerX());
                                            gameController.setOpponent(x2.getPlayerO());
                                            gameController.startGame();
                                            primaryStage.getScene().setRoot(board);
                                            
                                    } catch (IOException ex) {
                                        Logger.getLogger(startBase.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });
//                                  x.setM
//                                  x.setMinSize(BoxDialog.getWidth(),BoxDialog.getHeight()/13);
                                Platform.runLater(() -> {
                                    BoxDialog.getChildren().add(x);
                                });

                            }

//              tr.appendText(MSG+"=====\n");
                        } catch (IOException ex) {
                            Logger.getLogger(startBase.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                th.start();

                a1 = new Alert(Alert.AlertType.NONE,
                        "", ButtonType.CANCEL);
//                    a1.setWidth(100);

                a1.setGraphic(pane22);
                a1.getDialogPane().setPrefWidth(69 * 7);
                a1.setWidth(69 * 7);
                a1.setTitle("History");
//                a1.setContentText(History.stream()
//                       .map(Object::toString)
//                       .collect(Collectors.joining("\n")));
                a1.show();

            } catch (IOException ex) {
                Logger.getLogger(startBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        imageView4.setFitHeight(40.0);
        imageView4.setFitWidth(40.0);
        imageView4.setLayoutX(648.0);
        imageView4.setLayoutY(14.0);
        imageView4.setPickOnBounds(true);
        imageView4.setPreserveRatio(true);
        imageView4.setOnMouseClicked((event) -> {
            Alert exitAlert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
            Optional response = exitAlert.showAndWait();
            if (response.isPresent() && response.get() == ButtonType.YES) {
                Platform.exit();
            }
        });

        imageView4.setImage(new Image(getClass().getResource("exit1.png").toExternalForm()));

        

        label2.setLayoutX(398.0);
        label2.setLayoutY(122.0);
        label2.setPrefHeight(70.0);
        label2.setPrefWidth(148.0);
        label2.setText("Play");
        label2.setTextFill(javafx.scene.paint.Color.valueOf("#ee1818"));
        label2.setFont(new Font("Kinda 3D", 46.0));

        label3.setAlignment(javafx.geometry.Pos.CENTER);
        label3.setLayoutX(59.0);
        label3.setLayoutY(222.0);
        label3.setPrefHeight(17.0);
        label3.setPrefWidth(154.0);
        label3.setText(me.getName());
        label3.setTextFill(javafx.scene.paint.Color.valueOf("#dd1111"));
        label3.setFont(new Font("System Bold", 20.0));

        getChildren().add(imageView);
        getChildren().add(imageView0);
        getChildren().add(imageView1);
        getChildren().add(imageView2);
        getChildren().add(button);
        getChildren().add(button0);
        getChildren().add(imageView3);
        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(button1);
        getChildren().add(imageView4);
        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(label3);

    }
}
