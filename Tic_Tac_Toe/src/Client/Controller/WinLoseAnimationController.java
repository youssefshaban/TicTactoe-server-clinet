/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Controller;

//import Client.View.GamePlay.LosingFXMLBase;
//import Client.View.GamePlay.WinningFXMLBase;
import Core.Player;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author John
 */
public class WinLoseAnimationController {
    private final Player winner;
    private final boolean isAIWinner;
    private final boolean isNormalPlayersGame;
    private final Stage primaryStage;
//    private final WinningFXMLBase rootWinning;
//    private final LosingFXMLBase rootLosing;
    private Scene scene;

    public WinLoseAnimationController(Player winner, boolean isAIWinner, boolean isNormalPlayersGame, Stage primaryStage) {
        this.winner = winner;
        this.isAIWinner = isAIWinner;
        this.primaryStage = primaryStage;
//        this.rootWinning = new WinningFXMLBase();
//        this.rootLosing = new LosingFXMLBase();
        this.isNormalPlayersGame = isNormalPlayersGame;
        
        
        startAnimation();
    }
    
    private void startAnimation(){
        System.out.println(winner==null);
        System.out.println(winner);
        if(isAIWinner){
//             scene = new Scene(rootLosing);
        }
        else if(winner == null){
//            rootLosing.getLosingText().setText("Draw");
//            scene = new Scene(rootLosing);
        }
        else{
//           if(isNormalPlayersGame) rootWinning.getWinnerText().setText(winner.getName()+" Wins");
//           scene = new Scene(rootWinning);
        }
        this.primaryStage.setScene(scene);
    }
    
}
