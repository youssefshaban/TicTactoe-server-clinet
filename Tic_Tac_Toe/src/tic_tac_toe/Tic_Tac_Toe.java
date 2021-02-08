/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe;

import Core.PlayRoom;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import static java.lang.Thread.currentThread;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import login_sign_up.loginBase;
//import login.loginBase;

/**
 *
 * @author youssefshaban
 */
public class Tic_Tac_Toe extends Application {

    static DataInputStream dis;
    static PrintStream ps;
    InputStream inputStream;
    OutputStream outStream;
    Pane pane;
    static public TextArea tr = new TextArea();
    ObjectOutputStream objectOutputStream;
    Thread th;
    Thread th2;
    private double xOffset = 0;
    private double yOffset = 0;

    static ObjectInputStream objectInputStream;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane root = new Pane();
        System.out.println(primaryStage);
        root.getChildren().add(new loginBase(primaryStage));

        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root, 680, 420);
        
        scene.setOnMousePressed((event) -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
        });
        scene.setOnMouseDragged((event) -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
        });

        primaryStage.setTitle("tec tac toe");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        launch(args);
    }
}
