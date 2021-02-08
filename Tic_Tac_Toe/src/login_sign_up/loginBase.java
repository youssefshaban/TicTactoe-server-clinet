package login_sign_up;

import Core.AES;
import Core.Player;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import login_sign_up.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JFrame;
import tic_tac_toe.FXMLBase;
//import main.Core.AES;
//import login.FXMLsignupBase;

public class loginBase extends AnchorPane {

    final String secretKey = "ssshhhhhhhhhhh!!!!";
    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final ImageView imageView1;
    protected final ImageView imageView2;
    protected final Button button;
    protected final ImageInput imageInput;
    protected final Pane pane;
    protected final Button button0;
    protected final ImageView imageView3;
    protected final Text text;
    protected final Button button1;
    public static BufferedWriter bufferedWriter;
    public static BufferedReader bufferedReader;
    protected final PasswordField passwordField;
    protected final TextField textField;
    protected final Text text0;
    String s1, s2;
    public static Socket mySocket;
    public static DataInputStream dis;
    public static DataOutputStream ps;
    JFrame frame = new JFrame("JOptionPane showMessageDialog example");
    InputStream stream, stream1, stream2, stream3, stream4;
    public static Player me;
    public static ObjectInputStream objIn;
    public static ObjectOutputStream objOut;
    private double xOffset = 0;
    private double yOffset = 0;

    public Socket getSocket() {
        return mySocket;
    }

    public loginBase(Stage primaryStage) {

        imageView = new ImageView();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        imageView2 = new ImageView();
        button = new Button();

        imageInput = new ImageInput();
        pane = new Pane();
        button0 = new Button();
        imageView3 = new ImageView();
        text = new Text();
        button1 = new Button();
        passwordField = new PasswordField();
        textField = new TextField();
        text0 = new Text();
        
        try {
            if(mySocket != null) mySocket.setSoTimeout(0);
        } catch (SocketException ex) {
            Logger.getLogger(loginBase.class.getName()).log(Level.SEVERE, null, ex);
        }

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

        button0.setId("login_btn");
        button0.setLayoutX(174.0);
        button0.setLayoutY(154.0);

        button0.setPrefHeight(31.0);
        button0.setPrefWidth(67.0);
        button0.getStylesheets().add("style.css");
        button0.setText("log in ");
        button0.setTextFill(javafx.scene.paint.Color.valueOf("#2ac2d3"));

        imageView3.setFitHeight(49.0);
        imageView3.setFitWidth(52.0);
        imageView3.setLayoutX(116.0);
        imageView3.setLayoutY(14.0);
        imageView3.setPickOnBounds(true);
        imageView3.setPreserveRatio(true);
        imageView3.setImage(new Image(getClass().getResource("user6.png").toExternalForm()));

        text.setFill(javafx.scene.paint.Color.valueOf("#3d4c4dad"));
        text.setLayoutX(14.0);
        text.setLayoutY(225.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("Not a User ?");
        text.setWrappingWidth(93.99999637901783);
        text.setFont(new Font(14.0));

        button1.setId("login_btn");
        button1.setLayoutX(107.0);
        button1.setLayoutY(205.0);

        button1.getStylesheets().add("style.css");
        button1.setText("Sign_Up ");
        button1.setTextFill(javafx.scene.paint.Color.valueOf("#2ac2d3"));

        passwordField.setId("passwprd");
        passwordField.setLayoutX(14.0);
        passwordField.setLayoutY(113.0);
        passwordField.setPrefHeight(31.0);
        passwordField.setPrefWidth(155.0);
        passwordField.setPromptText("password");

        textField.setId("name");
        textField.setLayoutX(14.0);
        textField.setLayoutY(72.0);
        textField.setNodeOrientation(javafx.geometry.NodeOrientation.LEFT_TO_RIGHT);
        textField.setPrefHeight(31.0);
        textField.setPrefWidth(155.0);
        textField.setPromptText("Email");
        textField.getStylesheets().add("style.css");
        textField.setCursor(Cursor.DISAPPEAR);

        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("Text");

        getChildren().add(imageView);
        getChildren().add(imageView0);
        getChildren().add(imageView1);
        getChildren().add(imageView2);
        getChildren().add(button);
        pane.getChildren().add(button0);
        pane.getChildren().add(imageView3);
        pane.getChildren().add(text);
        pane.getChildren().add(button1);
        pane.getChildren().add(passwordField);
        pane.getChildren().add(textField);
        getChildren().add(pane);
        getChildren().add(text0);

        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(textField.textProperty(),
                        passwordField.textProperty());

            }

            @Override
            protected boolean computeValue() {
                return (textField.getText().isEmpty()
                        || passwordField.getText().isEmpty());
            }
        };

        button.setOnAction((ActionEvent event) -> {
            System.exit(0);
        });

        button0.disableProperty().bind(bb);

        button0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                s1 = textField.getText();
                s2 = passwordField.getText();
                String encryptedString = AES.encrypt(passwordField.getText(), secretKey);
                System.out.println(s1);
                System.out.println(encryptedString);

                try {
                    mySocket = new Socket(InetAddress.getLocalHost(), 5004);
                    dis = new DataInputStream(mySocket.getInputStream());
                    ps = new DataOutputStream(mySocket.getOutputStream());
                    objIn = new ObjectInputStream(dis);
                    objOut = new ObjectOutputStream(ps);

                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(loginBase.mySocket.getOutputStream(), StandardCharsets.UTF_8));
                    bufferedReader = new BufferedReader(new InputStreamReader(loginBase.mySocket.getInputStream(), StandardCharsets.UTF_8));
                    bufferedWriter.write("L-" + s1 + "-" + s2);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
//                    ps.writeUTF("L-" + s1 + "-" + s2 + "\n");
//                    ps.flush();
                    String replyMsg = bufferedReader.readLine();
                    System.out.println(replyMsg);
                    if (replyMsg.contains("S-0")) {
                        System.out.println("error");
                        Alert alt2 = new Alert(Alert.AlertType.ERROR, "Ooooops !! Wrong email or password", ButtonType.OK);
                        alt2.show();
//            FXMLsignupBase root = new FXMLsignupBase(primaryStage);
//            Scene scene = new Scene(root, 600, 400);
//            
//            primaryStage.setTitle("Registration Page");
//            primaryStage.setScene(scene);
//            primaryStage.show();
                    } else {
                        String[] s = replyMsg.split("-");
                        System.out.println("okay");
                        me = new Player(Integer.valueOf(s[1]), s1, s2);
                        Pane root = new Pane();
                        root.getChildren().add(new startBase(primaryStage));

//         primaryStage.initStyle(StageStyle.UNDECORATED);
                        Scene scene = new Scene(root, 700, 420);
                           scene.setOnMousePressed((eventM) -> {
                                    xOffset = eventM.getSceneX();
                                    yOffset = eventM.getSceneY();
                            });
                            scene.setOnMouseDragged((eventM) -> {
                                    primaryStage.setX(eventM.getScreenX() - xOffset);
                                    primaryStage.setY(eventM.getScreenY() - yOffset);
                            });
//                        primaryStage.setTitle("tec tac toe");
                        primaryStage.setScene(scene);
                        primaryStage.show();

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
//                ps.close();
//                dis.close();
//                mySocket.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                signupBase root = new signupBase(primaryStage);
//                Scene scene = new Scene(root, 700, 400);

//                primaryStage.setTitle("Registration Page");
                primaryStage.getScene().setRoot(root);
                primaryStage.show();

            }
        });

    }
}
