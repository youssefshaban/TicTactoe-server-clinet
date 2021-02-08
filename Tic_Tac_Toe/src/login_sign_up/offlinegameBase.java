package login_sign_up;

import Client.Controller.GameplayController;
import Client.View.GamePlay.BoardBase;
import Core.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import login_sign_up.loginBase;
import static login_sign_up.loginBase.me;
import static login_sign_up.loginBase.mySocket;

public class offlinegameBase extends AnchorPane {

    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final ImageView imageView1;
    protected final Button button;
    protected final Button button0;
    protected final ImageView imageView2;
    protected final Label label;
    protected final Label label0;
    protected final ImageView imageView3;
    protected final Label label1;
    protected final Label label2;
    protected final ImageView imageView4;
    InputStream stream, stream1, stream2, stream3, stream4, stream5;

    public offlinegameBase(Stage primaryStage) {

        imageView = new ImageView();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        button = new Button();
        button0 = new Button();
        imageView2 = new ImageView();
        label = new Label();
        label0 = new Label();
        imageView3 = new ImageView();
        label1 = new Label();
        label2 = new Label();
        imageView4 = new ImageView();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(700.0);
        getStylesheets().add("style.css");

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
        imageView1.setImage(new Image(getClass().getResource("user1.png").toExternalForm()));

        button.setLayoutX(255.0);
        button.setLayoutY(194.0);
        button.setPrefHeight(86.0);
        button.setPrefWidth(331.0);
        button.getStyleClass().add("button1");
        button.setTextFill(javafx.scene.paint.Color.valueOf("#2ea5e1"));
        button.setText("Single player");
        button.setFont(new Font("Algerian", 34.0));
        button.setOnAction(event -> primaryStage.getScene().setRoot(new AIComplexityFXMLBase(primaryStage)));

        button0.setLayoutX(255.0);
        button0.setLayoutY(294.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(86.0);
        button0.setPrefWidth(331.0);
        button0.setCursor(Cursor.DEFAULT);
        button0.setText("Two players");
        button0.setTextFill(javafx.scene.paint.Color.valueOf("#2ea5e1"));
        button0.setFont(new Font("Algerian", 34.0));
        button0.setOnAction(action -> {
            BoardBase root = new BoardBase();
            GameplayController gameController = new GameplayController(root, primaryStage, null, null);
            gameController.setPlayer(new Player(1, me.getName(), null));
            gameController.setOpponent(new Player("test", "test"));
            gameController.setAIDifficulty(1);
//        gameController.s
//        gameController.setOpponent(new Player(2, "Test2", null));
//        gameController.setAIDifficulty(AI.EASY);
            gameController.startGame();
//        
//            Scene scene = new Scene(root, 700, 420);

//        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.setResizable(false);
//            primaryStage.setScene(scene);
            primaryStage.getScene().setRoot(root);
            primaryStage.show();
        });
        imageView2.setFitHeight(48.0);
        imageView2.setFitWidth(222.0);
        imageView2.setId("logo");
        imageView2.setLayoutX(14.0);
        imageView2.setLayoutY(14.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);
        imageView2.setImage(new Image(getClass().getResource("logo.jpg").toExternalForm()));

        imageView3.setFitHeight(40.0);
        imageView3.setFitWidth(40.0);
        imageView3.setLayoutX(648.0);
        imageView3.setLayoutY(14.0);
        imageView3.setPickOnBounds(true);
        imageView3.setPreserveRatio(true);
        imageView3.setOnMouseClicked((event) -> {
            Alert exitAlert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
            Optional response = exitAlert.showAndWait();
            if (response.isPresent() && response.get() == ButtonType.YES) {
                Platform.exit();
            }
        });
        imageView3.setImage(new Image(getClass().getResource("exit1.png").toExternalForm()));

        label1.setLayoutX(219.0);
        label1.setLayoutY(73.0);
        label1.setPrefHeight(70.0);
        label1.setPrefWidth(358.0);
        label1.setText("Offline game");
        label1.setTextFill(javafx.scene.paint.Color.valueOf("#ee1818"));
        label1.setFont(new Font("Kinda 3D", 46.0));

        label2.setAlignment(javafx.geometry.Pos.CENTER);
        label2.setLayoutX(59.0);
        label2.setLayoutY(222.0);
        label2.setPrefHeight(17.0);
        label2.setPrefWidth(154.0);
        label2.setText(me.getName());
        label2.setTextFill(javafx.scene.paint.Color.valueOf("#dd1111"));
        label2.setFont(new Font("System Bold", 20.0));

        imageView4.setFitHeight(40.0);
        imageView4.setFitWidth(40.0);
        imageView4.setLayoutX(14.0);
        imageView4.setLayoutY(345.0);
        imageView4.setPickOnBounds(true);
        imageView4.setPreserveRatio(true);
        imageView4.setOnMouseClicked(event -> {
            Pane root = new Pane();
            root.getChildren().add(new startBase(primaryStage));

//         primaryStage.initStyle(StageStyle.UNDECORATED);
//        Scene scene = new Scene(root, 600, 400);
//        
//        primaryStage.setTitle("tec tac toe");
//        primaryStage.setScene(scene);
            primaryStage.getScene().setRoot(root);
            primaryStage.show();

        });
        imageView4.setImage(new Image(getClass().getResource("back.png").toExternalForm()));

        getChildren().add(imageView);
        getChildren().add(imageView0);
        getChildren().add(imageView1);
        getChildren().add(button);
        getChildren().add(button0);
        getChildren().add(imageView2);
        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(imageView3);
        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(imageView4);

    }
}
