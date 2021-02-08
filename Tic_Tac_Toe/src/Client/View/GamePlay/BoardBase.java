package Client.View.GamePlay;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;

public class BoardBase extends AnchorPane {

    protected final ImageView xImageView;
    protected final ImageView oImageView;
    protected final ImageView player2Image;
    protected final ImageView imageView2;
    protected final ImageView exitImage;
    protected final Label PlayerOName;
    protected final ImageView backImageView;
    protected final ImageView player1Image;
    protected final Label playerXName;
    protected final Button position2;
    protected final Button position3;
    protected final Button position5;
    protected final Button position4;
    protected final Button position1;
    protected final Button position6;
    protected final Button position0;
    protected final ImageView imageView6;
    protected final Button position7;
    protected final Button position8;
    protected final Label player1ScoreLabel;
    protected final ImageView imageView7;
    protected final Label player2ScoreLabel;
    protected final Label winLoseLabel;
    protected final Button button8;
    protected final ImageView playAgainImageView;
    protected final AnchorPane gridAnchorPane;
    protected final MediaView winnerMediaView; 
    protected final MediaView loserMediaView; 

    public BoardBase() {

        xImageView = new ImageView();
        oImageView = new ImageView();
        player2Image = new ImageView();
        imageView2 = new ImageView();
        exitImage = new ImageView();
        PlayerOName = new Label();
        backImageView = new ImageView();
        player1Image = new ImageView();
        playerXName = new Label();
        position2 = new Button();
        position3 = new Button();
        position5 = new Button();
        position4 = new Button();
        position1 = new Button();
        position6 = new Button();
        position0 = new Button();
        imageView6 = new ImageView();
        position7 = new Button();
        position8 = new Button();
        player1ScoreLabel = new Label();
        imageView7 = new ImageView();
        player2ScoreLabel = new Label();
        winLoseLabel = new Label();
        button8 = new Button();
        playAgainImageView = new ImageView();
        gridAnchorPane = new AnchorPane();
        winnerMediaView = new MediaView();
        loserMediaView = new MediaView();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(700.0);
        getStylesheets().add("/Client/View/GamePlay/style.css");

        xImageView.setFitHeight(62.0);
        xImageView.setFitWidth(56.0);
        xImageView.setLayoutX(599.0);
        xImageView.setLayoutY(357.0);
        xImageView.setPickOnBounds(true);
        xImageView.setPreserveRatio(true);
        xImageView.setImage(new Image(getClass().getResource("x2.png").toExternalForm()));

        oImageView.setFitHeight(40.0);
        oImageView.setFitWidth(40.0);
        oImageView.setLayoutX(648.0);
        oImageView.setLayoutY(365.0);
        oImageView.setPickOnBounds(true);
        oImageView.setPreserveRatio(true);
        oImageView.setImage(new Image(getClass().getResource("o.png").toExternalForm()));

        player2Image.setFitHeight(64.0);
        player2Image.setFitWidth(56.0);
        player2Image.setId("user2");
        player2Image.setLayoutX(167.0);
        player2Image.setLayoutY(96.0);
        player2Image.setPickOnBounds(true);
        player2Image.setPreserveRatio(true);
        player2Image.setImage(new Image(getClass().getResource("user1.png").toExternalForm()));

        imageView2.setFitHeight(48.0);
        imageView2.setFitWidth(222.0);
        imageView2.setId("logo");
        imageView2.setLayoutX(14.0);
        imageView2.setLayoutY(14.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);
        imageView2.setImage(new Image(getClass().getResource("logo.jpg").toExternalForm()));

        exitImage.setFitHeight(40.0);
        exitImage.setFitWidth(40.0);
        exitImage.setLayoutX(648.0);
        exitImage.setLayoutY(14.0);
        exitImage.setPickOnBounds(true);
        exitImage.setPreserveRatio(true);
        exitImage.setImage(new Image(getClass().getResource("exit1.png").toExternalForm()));

        PlayerOName.setAlignment(javafx.geometry.Pos.CENTER);
        PlayerOName.setLayoutX(167.0);
        PlayerOName.setLayoutY(162.0);
        PlayerOName.setPrefHeight(26.0);
        PlayerOName.setPrefWidth(56.0);
        PlayerOName.setText("user2");
        PlayerOName.setTextFill(javafx.scene.paint.Color.valueOf("#19b2c6"));
        PlayerOName.setFont(new Font("System Bold", 17.0));

        backImageView.setFitHeight(40.0);
        backImageView.setFitWidth(40.0);
        backImageView.setLayoutX(14.0);
        backImageView.setLayoutY(365.0);
        backImageView.setPickOnBounds(true);
        backImageView.setPreserveRatio(true);
        backImageView.setImage(new Image(getClass().getResource("back.png").toExternalForm()));

        player1Image.setFitHeight(64.0);
        player1Image.setFitWidth(56.0);
        player1Image.setId("user");
        player1Image.setLayoutX(46.0);
        player1Image.setLayoutY(96.0);
        player1Image.setPickOnBounds(true);
        player1Image.setPreserveRatio(true);
        player1Image.setImage(new Image(getClass().getResource("user3.png").toExternalForm()));

        playerXName.setAlignment(javafx.geometry.Pos.CENTER);
        playerXName.setLayoutX(46.0);
        playerXName.setLayoutY(162.0);
        playerXName.setPrefHeight(26.0);
        playerXName.setPrefWidth(56.0);
        playerXName.setText("user1");
        playerXName.setTextFill(javafx.scene.paint.Color.valueOf("#dd1111"));
        playerXName.setFont(new Font("System Bold", 17.0));

        position2.setLayoutX(210.0);
        position2.setLayoutY(8.0);
        position2.setMnemonicParsing(false);
        position2.setPrefHeight(90.0);
        position2.setPrefWidth(91.0);
        position2.setId("2");

        position3.setLayoutX(5.0);
        position3.setLayoutY(105.0);
        position3.setMnemonicParsing(false);
        position3.setPrefHeight(90.0);
        position3.setPrefWidth(97.0);
        position3.setId("3");

        position5.setLayoutX(210.0);
        position5.setLayoutY(105.0);
        position5.setMnemonicParsing(false);
        position5.setPrefHeight(90.0);
        position5.setPrefWidth(91.0);
        position5.setId("5");

        position4.setLayoutX(110.0);
        position4.setLayoutY(105.0);
        position4.setMnemonicParsing(false);
        position4.setPrefHeight(90.0);
        position4.setPrefWidth(91.0);
        position4.setId("4");

        position1.setLayoutX(110.0);
        position1.setLayoutY(8.0);
        position1.setMnemonicParsing(false);
        position1.setPrefHeight(90.0);
        position1.setPrefWidth(91.0);
        position1.setId("1");

        position6.setLayoutX(5.0);
        position6.setLayoutY(200.0);
        position6.setMnemonicParsing(false);
        position6.setPrefHeight(88.0);
        position6.setPrefWidth(97.0);
        position6.setId("6");

        position0.setLayoutX(5.0);
        position0.setLayoutY(8.0);
        position0.setMnemonicParsing(false);
        position0.setPrefHeight(90.0);
        position0.setPrefWidth(97.0);
        position0.setId("0");

        imageView6.setFitHeight(63.0);
        imageView6.setFitWidth(249.0);
        imageView6.setFocusTraversable(true);
        imageView6.setLayoutX(14.0);
        imageView6.setLayoutY(191.0);
        imageView6.setPickOnBounds(true);
        imageView6.setImage(new Image(getClass().getResource("score2.jpg").toExternalForm()));

        position7.setLayoutX(110.0);
        position7.setLayoutY(200.0);
        position7.setMnemonicParsing(false);
        position7.setPrefHeight(88.0);
        position7.setPrefWidth(91.0);
        position7.setId("7");

        position8.setLayoutX(210.0);
        position8.setLayoutY(200.0);
        position8.setMnemonicParsing(false);
        position8.setPrefHeight(88.0);
        position8.setPrefWidth(91.0);
        position8.setId("8");

        player1ScoreLabel.setAlignment(javafx.geometry.Pos.CENTER);
        player1ScoreLabel.setLayoutX(43.0);
        player1ScoreLabel.setLayoutY(200.0);
        player1ScoreLabel.setPrefHeight(26.0);
        player1ScoreLabel.setPrefWidth(56.0);
        player1ScoreLabel.setText("score1");

        imageView7.setFitHeight(48.0);
        imageView7.setFitWidth(64.0);
        imageView7.setLayoutX(96.0);
        imageView7.setLayoutY(372.0);
        imageView7.setPickOnBounds(true);
        imageView7.setPreserveRatio(true);

        player2ScoreLabel.setAlignment(javafx.geometry.Pos.CENTER);
        player2ScoreLabel.setLayoutX(167.0);
        player2ScoreLabel.setLayoutY(200.0);
        player2ScoreLabel.setPrefHeight(26.0);
        player2ScoreLabel.setPrefWidth(56.0);
        player2ScoreLabel.setText("score2");
        
        winLoseLabel.setAlignment(javafx.geometry.Pos.CENTER);
        winLoseLabel.setLayoutX(54.0);
        winLoseLabel.setLayoutY(216.0);
        winLoseLabel.setPrefHeight(43.0);
        winLoseLabel.setPrefWidth(156.0);
        winLoseLabel.setFont(new Font("System Bold", 22.0));
        winLoseLabel.setText("");

        button8.setBlendMode(javafx.scene.effect.BlendMode.GREEN);
        button8.setLayoutX(67.0);
        button8.setLayoutY(294.0);
        button8.setMnemonicParsing(false);
        button8.setPrefHeight(48.0);
        button8.setPrefWidth(128.0);

        playAgainImageView.setFitHeight(45.0);
        playAgainImageView.setFitWidth(121.0);
        playAgainImageView.setLayoutX(71.0);
        playAgainImageView.setLayoutY(297.0);
        playAgainImageView.setPickOnBounds(true);
        playAgainImageView.setPreserveRatio(true);
        playAgainImageView.setImage(new Image(getClass().getResource("playagain.jpg").toExternalForm()));
        playAgainImageView.setVisible(false);
        
        MediaPlayer winnerMediaPlayer = new MediaPlayer(new Media(getClass().getResource("winnerVideo.mp4").toExternalForm()));
        winnerMediaView.setMediaPlayer(winnerMediaPlayer);
        winnerMediaView.setVisible(false);
        winnerMediaView.setLayoutX(0);
        winnerMediaView.setLayoutY(0);
        
        MediaPlayer loserMediaPlayer = new MediaPlayer(new Media(getClass().getResource("loserVideo.mp4").toExternalForm()));
        loserMediaView.setMediaPlayer(loserMediaPlayer);
        loserMediaView.setVisible(false);
        loserMediaView.setLayoutX(-10);
        loserMediaView.setLayoutY(0);
        
        gridAnchorPane.setLayoutX(310);
        gridAnchorPane.setLayoutY(50);
        gridAnchorPane.setPrefSize(230, 205);

        gridAnchorPane.getChildren().add(position0);
        gridAnchorPane.getChildren().add(position1);
        gridAnchorPane.getChildren().add(position2);
        gridAnchorPane.getChildren().add(position3);
        gridAnchorPane.getChildren().add(position4);
        gridAnchorPane.getChildren().add(position5);
        gridAnchorPane.getChildren().add(position6);
        gridAnchorPane.getChildren().add(position7);
        gridAnchorPane.getChildren().add(position8);
        
        getChildren().add(xImageView);
        getChildren().add(oImageView);
        getChildren().add(player2Image);
        getChildren().add(imageView2);
        getChildren().add(exitImage);
        getChildren().add(PlayerOName);
        getChildren().add(backImageView);
        getChildren().add(player1Image);
        getChildren().add(playerXName);
        getChildren().add(imageView6);
        getChildren().add(gridAnchorPane);
        getChildren().add(player1ScoreLabel);
        getChildren().add(imageView7);
        getChildren().add(player2ScoreLabel);
        getChildren().add(button8);
        getChildren().add(playAgainImageView);
        getChildren().add(winLoseLabel);
        getChildren().add(winnerMediaView);
        getChildren().add(loserMediaView);

    }

    public AnchorPane getGridAnchorPane() {
        return gridAnchorPane;
    }

    public ImageView getxImageView() {
        return xImageView;
    }

    public ImageView getoImageView() {
        return oImageView;
    }

    public Label getPlayerXName() {
        return playerXName;
    }

    public Label getPlayerOName() {
        return PlayerOName;
    }

    public ImageView getPlayAgainImageView() {
        return playAgainImageView;
    }

    public Label getPlayer2ScoreLabel() {
        return player2ScoreLabel;
    }

    public Label getPlayer1ScoreLabel() {
        return player1ScoreLabel;
    }

    public Label getWinLoseLabel() {
        return winLoseLabel;
    }

    public ImageView getExitImage() {
        return exitImage;
    }

    public ImageView getBackImageView() {
        return backImageView;
    }

    public MediaView getWinnerMediaView() {
        return winnerMediaView;
    }

    public MediaView getLoserMediaView() {
        return loserMediaView;
    }
    
}
