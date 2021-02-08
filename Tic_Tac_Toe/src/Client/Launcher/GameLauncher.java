/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Launcher;

import Client.ClientCore.AI;
import Client.Controller.GameplayController;
import Client.View.GamePlay.BoardBase;
import Core.Player;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author John
 */
public class GameLauncher extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
//        WinningFXMLBase root = new WinningFXMLBase();
//        LosingFXMLBase root = new LosingFXMLBase();
        
        BoardBase root = new BoardBase();
        GameplayController gameController = new GameplayController(root, primaryStage, null,null);
        gameController.setPlayer(new Player(1, "Test", null));
//        gameController.setOpponent(new Player(2, "Test2", null));
//        gameController.setAIDifficulty(AI.EASY);
        gameController.startGame();
//        
        Scene scene = new Scene(root, 700, 420);
        
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
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
