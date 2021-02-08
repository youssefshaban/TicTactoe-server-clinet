package login_sign_up;

import Client.ClientCore.AI;
import Client.Controller.GameplayController;
import Client.View.GamePlay.BoardBase;
import Core.Player;
import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import static login_sign_up.loginBase.me;

public class AIComplexityFXMLBase extends AnchorPane {

    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final ImageView imageView1;
    protected final Button button;
    protected final Button button0;
    protected final ImageView imageView2;
    protected final ImageView imageView3;
    protected final Label label;
    protected final Label label0;
    protected final ImageView imageView4;
    protected final Button button1;
    protected final Button button2;

    public AIComplexityFXMLBase(Stage primaryStage) {

        imageView = new ImageView();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        button = new Button();
        button0 = new Button();
        imageView2 = new ImageView();
        imageView3 = new ImageView();
        label = new Label();
        label0 = new Label();
        imageView4 = new ImageView();
        button1 = new Button();
        button2 = new Button();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(700.0);
        getStylesheets().add("/login_sign_up/style.css");

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
        imageView1.setImage(new Image(getClass().getResource("user6.png").toExternalForm()));

        button.setLayoutX(255.0);
        button.setLayoutY(114.0);
        button.setPrefHeight(50.0);
        button.setPrefWidth(331.0);
        button.getStyleClass().add("button1");
        button.setText("Easy");
        button.setTextFill(javafx.scene.paint.Color.valueOf("#2ea5e1"));
        button.setFont(new Font("Algerian", 30.0));
        button.setOnAction((event) -> {
            BoardBase root = new BoardBase();
            GameplayController gameController = new GameplayController(root, primaryStage, null, null);
            gameController.setPlayer(new Player(1, me.getName(), null));
            gameController.setAIDifficulty(AI.EASY);
            gameController.startGame();
            primaryStage.getScene().setRoot(root);
        });

        button0.setLayoutX(255.0);
        button0.setLayoutY(174.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(50.0);
        button0.setPrefWidth(331.0);
        button0.setText("Medium");
        button0.setCursor(Cursor.DEFAULT);
        button0.setTextFill(javafx.scene.paint.Color.valueOf("#2ea5e1"));
        button0.setFont(new Font("Algerian", 30.0));
        button0.setOnAction((event) -> {
            BoardBase root = new BoardBase();
            GameplayController gameController = new GameplayController(root, primaryStage, null, null);
            gameController.setPlayer(new Player(1, me.getName(), null));
            gameController.setAIDifficulty(AI.MEDIUM);
            gameController.startGame();
            primaryStage.getScene().setRoot(root);
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
        imageView3.setImage(new Image(getClass().getResource("exit1.png").toExternalForm()));
        imageView3.setOnMouseClicked((event) -> {
            Alert exitAlert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
            Optional response = exitAlert.showAndWait();
            if (response.isPresent() && response.get() == ButtonType.YES) {
                Platform.exit();
            }
        });
        

        label.setLayoutX(213.0);
        label.setLayoutY(62.0);
        label.setPrefHeight(70.0);
        label.setPrefWidth(358.0);
        label.setText("Choose Computer Difficulty");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#ee1818"));
        label.setFont(new Font(27.0));

        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setLayoutX(59.0);
        label0.setLayoutY(222.0);
        label0.setPrefHeight(17.0);
        label0.setPrefWidth(154.0);
        label0.setText(loginBase.me.getName());
        label0.setTextFill(javafx.scene.paint.Color.valueOf("#dd1111"));
        label0.setFont(new Font("System Bold", 20.0));

        imageView4.setFitHeight(40.0);
        imageView4.setFitWidth(40.0);
        imageView4.setLayoutX(14.0);
        imageView4.setLayoutY(345.0);
        imageView4.setPickOnBounds(true);
        imageView4.setPreserveRatio(true);
        imageView4.setImage(new Image(getClass().getResource("back.png").toExternalForm()));
        imageView4.setOnMouseClicked(event -> {
            primaryStage.getScene().setRoot(new offlinegameBase(primaryStage));
        });

        button1.setLayoutX(255.0);
        button1.setLayoutY(234.0);
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(50.0);
        button1.setPrefWidth(331.0);
        button1.setText("Hard");
        button1.setCursor(Cursor.DEFAULT);
        button1.setTextFill(javafx.scene.paint.Color.valueOf("#2ea5e1"));
        button1.setFont(new Font("Algerian", 30.0));
        button1.setOnAction((event) -> {
            BoardBase root = new BoardBase();
            GameplayController gameController = new GameplayController(root, primaryStage, null, null);
            gameController.setPlayer(new Player(1, me.getName(), null));
            gameController.setAIDifficulty(AI.HARD);
            gameController.startGame();
            primaryStage.getScene().setRoot(root);
        });


        button2.setLayoutX(255.0);
        button2.setLayoutY(294.0);
        button2.setMnemonicParsing(false);
        button2.setPrefHeight(50.0);
        button2.setPrefWidth(331.0);
        button2.setText("Impossible");
        button2.setCursor(Cursor.DEFAULT);
        button2.setTextFill(javafx.scene.paint.Color.valueOf("#2ea5e1"));
        button2.setFont(new Font("Algerian", 30.0));
        button2.setOnAction((event) -> {
            BoardBase root = new BoardBase();
            GameplayController gameController = new GameplayController(root, primaryStage, null, null);
            gameController.setPlayer(new Player(1, me.getName(), null));
            gameController.setAIDifficulty(AI.IMPOSSIBLE);
            gameController.startGame();
            primaryStage.getScene().setRoot(root);
        });


        getChildren().add(imageView);
        getChildren().add(imageView0);
        getChildren().add(imageView1);
        getChildren().add(button);
        getChildren().add(button0);
        getChildren().add(imageView2);
        getChildren().add(imageView3);
        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(imageView4);
        getChildren().add(button1);
        getChildren().add(button2);

    }
}
