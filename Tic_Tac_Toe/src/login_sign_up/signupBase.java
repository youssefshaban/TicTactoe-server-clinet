package login_sign_up;

import Core.Player;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import login_sign_up.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JFrame;
import static login_sign_up.loginBase.bufferedReader;
import static login_sign_up.loginBase.bufferedWriter;
import static login_sign_up.loginBase.me;
import tic_tac_toe.FXMLBase;

//import sun.security.jgss.GSSUtil.login(GSSCaller, Oid).FXMLsignupBase;

public class signupBase extends AnchorPane {

    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final ImageView imageView1;
    protected final ImageView imageView2;
    protected final Button button;
    protected final ImageInput imageInput;
    protected final Pane pane;
    protected final TextField textField;
    protected final TextField Email;
    protected final PasswordField passwordField;
    protected final ImageView imageView3;
    protected final Button button1;
    protected final PasswordField passwordField0;
    protected final Text text;
    String s1, s2, s3;
    private double xOffset = 0;
    private double yOffset = 0;

    DataInputStream dis;
    PrintStream ps;
    JFrame frame = new JFrame("JOptionPane showMessageDialog example");

    public signupBase(Stage primaryStage) {

        imageView = new ImageView();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        imageView2 = new ImageView();
        Email = new TextField();
        button = new Button();
        imageInput = new ImageInput();
        pane = new Pane();
        textField = new TextField();
        passwordField = new PasswordField();
        imageView3 = new ImageView();
        button1 = new Button();
        passwordField0 = new PasswordField();
        text = new Text();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(700.0);
        getStylesheets().add("style.css");

        imageView.setFitHeight(257.0);
        imageView.setFitWidth(259.0);
        imageView.setLayoutX(-15.0);
        imageView.setLayoutY(141.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("x2.png").toExternalForm()));

        imageView0.setFitHeight(178.0);
        imageView0.setFitWidth(179.0);
        imageView0.setLayoutX(166.0);
        imageView0.setLayoutY(177.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("o.png").toExternalForm()));

        imageView1.setFitHeight(131.0);
        imageView1.setFitWidth(590.0);
        imageView1.setId("logo");
        imageView1.setLayoutX(14.0);
        imageView1.setLayoutY(14.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("logo.jpg").toExternalForm()));

        imageView2.setFitHeight(76.0);
        imageView2.setFitWidth(43.0);
        imageView2.setLayoutX(613.0);
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

        button.setBlendMode(javafx.scene.effect.BlendMode.ADD);
        //button.setDisable(true);
        button.setId("#exit_button");
        button.setLayoutX(559.0);
        button.setLayoutY(14.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(43.0);
        button.setPrefWidth(67.0);
        button.getStylesheets().add("style.css");
        button.setTextOverrun(javafx.scene.control.OverrunStyle.WORD_ELLIPSIS);
        button.setCursor(Cursor.DEFAULT);

        button.setEffect(imageInput);

        pane.setId("pane");
        pane.setLayoutX(346.0);
        pane.setLayoutY(141.0);
        pane.setPrefHeight(257.0);
        pane.setPrefWidth(280.0);

        Email.setId("name");
        Email.setLayoutX(14.0);
        Email.setLayoutY(122.0);
        Email.setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);
        Email.setPrefHeight(31.0);
        Email.setPrefWidth(155.0);
        Email.setPromptText("Email");
        Email.getStylesheets().add("style.css");
        Email.setCursor(Cursor.DISAPPEAR);

        textField.setId("name");
        textField.setLayoutX(14.0);
        textField.setLayoutY(74.0);
        textField.setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);
        textField.setPrefHeight(31.0);
        textField.setPrefWidth(155.0);
        textField.setPromptText("User-name");
        textField.getStylesheets().add("style.css");
        textField.setCursor(Cursor.DISAPPEAR);
        textField.setVisible(false);

        passwordField.setId("passwprd");
        passwordField.setLayoutX(14.0);
        passwordField.setLayoutY(170.0);
        passwordField.setPrefHeight(31.0);
        passwordField.setPrefWidth(155.0);
        passwordField.setPromptText("password");

        imageView3.setFitHeight(49.0);
        imageView3.setFitWidth(52.0);
        imageView3.setLayoutX(116.0);
        imageView3.setLayoutY(14.0);
        imageView3.setPickOnBounds(true);
        imageView3.setPreserveRatio(true);
        imageView3.setImage(new Image(getClass().getResource("user6.png").toExternalForm()));

        button1.setId("login_btn");
        button1.setLayoutX(199.0);
        button1.setLayoutY(201.0);

        button1.getStylesheets().add("style.css");
        button1.setText("Sigin up");
        button1.setTextFill(javafx.scene.paint.Color.valueOf("#2ac2d3"));

        passwordField0.setId("passwprd");
        passwordField0.setLayoutX(14.0);
        passwordField0.setLayoutY(218.0);
        passwordField0.setPrefHeight(31.0);
        passwordField0.setPrefWidth(155.0);
        passwordField0.setPromptText("Re-enter password");

        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("Text");

        getChildren().add(imageView);
        getChildren().add(imageView0);
        getChildren().add(imageView1);
        getChildren().add(imageView2);
        getChildren().add(button);
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER_LEFT);
        box.getChildren().addAll(imageView3, textField, Email, passwordField, passwordField0, button1);
        pane.getChildren().add(box);
        getChildren().add(pane);
        getChildren().add(text);

        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(textField.textProperty(),
                        passwordField0.textProperty(),
                        passwordField.textProperty());

            }

            @Override
            protected boolean computeValue() {
                return ( passwordField0.getText().isEmpty()
                        || passwordField.getText().isEmpty());
            }
        };

        button.setOnAction((ActionEvent event) -> {
            System.exit(0);
        });

        button1.disableProperty().bind(bb);

        button1.setOnAction((ActionEvent event) -> {
            s1 = Email.getText();
            s2 = passwordField.getText();
            s3 = passwordField0.getText();
            Boolean b;
            b = s2.equals(s3);
            if (b == false) {
                Alert alt = new Alert(Alert.AlertType.ERROR, "Mismatch Password!", ButtonType.OK);
                alt.showAndWait();
//                FXMLsignupBase root = new FXMLsignupBase(primaryStage);
//                Scene scene = new Scene(root, 600, 400);
//            
//                primaryStage.setTitle("Registration Page");
//                primaryStage.setScene(scene);
//                primaryStage.show();
            } else {
                try {
                    loginBase.mySocket = new Socket(InetAddress.getLocalHost(), 5004);
//                    System.out.println("login_sign_up.signupBase.<init>()1");
                    dis = new DataInputStream(loginBase.mySocket.getInputStream());
//                    System.out.println("login_sign_up.signupBase.<init>()2");
                    ps = new PrintStream(loginBase.mySocket.getOutputStream());
//                    System.out.println("login_sign_up.signupBase.<init>()3");
                     bufferedWriter = new BufferedWriter(new OutputStreamWriter(loginBase.mySocket.getOutputStream()));
                     bufferedReader = new BufferedReader(new InputStreamReader(loginBase.mySocket.getInputStream()));
                    bufferedWriter.write("S-" + s1 + "-" + s2);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
//                    System.out.println("login_sign_up.signupBase.<init>()4");
                    String replyMsg = bufferedReader.readLine();
//                    System.out.println("login_sign_up.signupBase.<init>()5");
                    if (replyMsg.contains("S0")) {
                        Alert alt2 = new Alert(Alert.AlertType.ERROR, "Ooooops !! Wrong email or password", ButtonType.OK);
                        alt2.showAndWait();
                    } else {
                        System.out.println("okay");
                        String[] s=replyMsg.split("-");
                        me = new Player(Integer.valueOf(s[1]),s1, s2);
                        Pane root = new Pane();
                        root.getChildren().add(new startBase(primaryStage));
                        Scene scene = new Scene(root, 700, 420);
                        scene.setOnMousePressed((eventM) -> {
                            xOffset = eventM.getSceneX();
                            yOffset = eventM.getSceneY();
                        });
                        scene.setOnMouseDragged((eventM) -> {
                            primaryStage.setX(eventM.getScreenX() - xOffset);
                            primaryStage.setY(eventM.getScreenY() - yOffset);
                        });
                        primaryStage.setScene(scene);
                        primaryStage.show();

                    }
                } catch (UnknownHostException ex) {
                    Logger.getLogger(signupBase.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(signupBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );

    }
}
