/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_sign_up;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Toka Attiah
 */
public class startmain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        startBase root = new startBase(primaryStage);
        
        Scene scene = new Scene(root, 700, 400);
        
        primaryStage.setTitle("startpage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
