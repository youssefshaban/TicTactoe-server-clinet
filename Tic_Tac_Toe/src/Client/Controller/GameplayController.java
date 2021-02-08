package Client.Controller;

import Client.ClientCore.AI;
import Client.ClientCore.Board;
import Client.View.GamePlay.BoardBase;
import Core.Player;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import Client.ClientCore.AlreadyTakenException;
import Client.Services.Database.DBConnection;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Optional;
import java.util.stream.IntStream;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import static login_sign_up.loginBase.bufferedReader;
import static login_sign_up.loginBase.bufferedWriter;
import static login_sign_up.loginBase.me;
import login_sign_up.startBase;

public class GameplayController {

    private Player player;
    private Player opponent;
//    private Player me;
    private final Player AIPlayer = new Player(0, "AI", null);
    private final AI ai;
    private boolean isAIGame = true;
    private Board gameBoard;
    private Player turn;
    private int step;
    private final Stage primaryStage;
    private final DBConnection model;

    private boolean historyPlayback = false;

    private final BoardBase root;

    private final AnchorPane gameGrid;

    private final Label playerXName;
    private final Label playerOName;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Socket socket;
    private final boolean isOnlineGame;

    public GameplayController(BoardBase root, Stage primaryStage, Socket socket, Board b) {
        this.gameBoard = b;
        this.root = root;
        this.gameGrid = this.root.getGridAnchorPane();
        this.playerXName = this.root.getPlayerXName();
        this.playerOName = this.root.getPlayerOName();
        this.model = new DBConnection();

        this.primaryStage = primaryStage;

        this.socket = socket;
        this.isOnlineGame = this.socket != null;
        if (this.isOnlineGame) {
            initOnlineGame();
        }

        this.gameGrid.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getTarget() instanceof Button && !this.historyPlayback && (turn.getID() == me.getID() || !isOnlineGame)) {
                String PlayerStep = ((Button) event.getTarget()).getId();
                this.step = Integer.parseInt(PlayerStep);
                this.play();
            }
        });

        this.root.getPlayAgainImageView().setOnMouseClicked(event -> {
            root.getPlayAgainImageView().setVisible(false);
            root.getWinLoseLabel().setText("");
            this.clearBoard();
            if (historyPlayback) {
                playbackGame();
            } else {
                this.gameBoard = new Board(this.gameBoard.getPlayerX(),
                        this.gameBoard.getPlayerO(),
                        this.gameBoard.getRoomID() + 1,
                        this.gameBoard.getStartTime(),
                        this.gameBoard.getEndTime());
                glowX();
                this.root.getBackImageView().setVisible(false);
                this.startGame();
            }
        });

        this.root.getExitImage().setOnMouseClicked(event -> {
            Alert exitAlert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
            Optional response = exitAlert.showAndWait();
            if (response.isPresent() && response.get() == ButtonType.YES) {
                Platform.exit();
            }
        });

        this.root.getBackImageView().setVisible(false);
        this.root.getBackImageView().setOnMouseClicked(event -> {
            startBase returnRoot = new startBase(primaryStage);
            this.primaryStage.getScene().setRoot(returnRoot);
        });

        this.root.getWinnerMediaView().fitWidthProperty().set(this.primaryStage.getWidth());
        this.root.getWinnerMediaView().getMediaPlayer().setVolume(0.1);
        this.root.getWinnerMediaView().setOnMouseClicked((event) -> {
            this.root.getWinnerMediaView().getMediaPlayer().stop();
            this.root.getWinnerMediaView().getMediaPlayer().seek(Duration.ZERO);
            this.root.getWinnerMediaView().setVisible(false);
        });
        
        this.root.getLoserMediaView().fitWidthProperty().set(this.primaryStage.getWidth());
        this.root.getLoserMediaView().getMediaPlayer().setVolume(0.1);
        this.root.getLoserMediaView().setOnMouseClicked((event) -> {
            this.root.getLoserMediaView().getMediaPlayer().stop();
            this.root.getLoserMediaView().getMediaPlayer().seek(Duration.ZERO);
            this.root.getLoserMediaView().setVisible(false);
        });

        ai = new AI(AI.IMPOSSIBLE);

        glowX();
    }

    private void initOnlineGame() {
        Thread onlineGameThread = new Thread(() -> {
            while (!gameBoard.gameEnded()) {
                try {
                    socket.setSoTimeout(60*1000);
                    String[] msg = bufferedReader.readLine().split("-");
                    socket.setSoTimeout(0);
                    if ((Integer.parseInt(msg[0]) == turn.getID()) && (Integer.parseInt(msg[0]) != me.getID())) {
                        step = Integer.parseInt(msg[1]);
                        this.play();
                    }
                }catch(SocketTimeoutException ex){
                    try {
                        bufferedWriter.write("null");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } catch (IOException ex1) {
                        Logger.getLogger(GameplayController.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    Platform.runLater(()-> {
                        Alert alertTimeOut = new Alert(Alert.AlertType.INFORMATION, "Playing TimeOut", ButtonType.OK);
                        alertTimeOut.showAndWait();
                        startBase redirectRoot  = new startBase(primaryStage);
                        this.primaryStage.getScene().setRoot(redirectRoot);
                    });
                    break;
                } 
                catch (IOException ex) {
                    Logger.getLogger(GameplayController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        onlineGameThread.setName("OnlineGameThread");
        onlineGameThread.start();

    }

    public void setPlayer(Player player) {
        this.player = player;
        rename();
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
        rename();
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setAIDifficulty(int AIDifficulty) {
        this.ai.setDifficulty(AIDifficulty);
    }

    private Button getGameGridTextById(String id) {
        return (Button) this.gameGrid.lookup("#" + id);
    }

    private void clearBoard() {
        IntStream.range(0, 9).forEach(value -> {
            this.getGameGridTextById(String.valueOf(value)).setGraphic(null);
        });
    }

    private void rename() {
        if (player != null) {
            this.playerXName.setText(player.getName());
        }
        if (opponent != null) {
            this.playerOName.setText(opponent.getName());
        }
    }

    public void startGame() {
        if (player == null && opponent == null) {
            return;
        }
        if (player == null) {
            player = AIPlayer;
            rename();
        } else if (opponent == null) {
            opponent = AIPlayer;
            rename();
        } else {
            if (player != AIPlayer && opponent != AIPlayer) {
                isAIGame = false;
            }
        }

//        this.root.getPlayer1ScoreLabel().setText(String.valueOf(player.getTotalScore()));
//        this.root.getPlayer2ScoreLabel().setText(String.valueOf(opponent.getTotalScore()));
        this.root.getPlayer1ScoreLabel().setText("");
        this.root.getPlayer2ScoreLabel().setText("");
        if (this.gameBoard == null) {
            this.gameBoard = new Board(player, opponent, 0, new Date(), null);
        }
        beginGame();
    }

    private ImageView copyImageView(ImageView imView) {
        ImageView imageView = new ImageView(imView.getImage());
        imageView.setFitHeight(imView.getFitHeight());
        imageView.setFitWidth(imView.getFitWidth());
        imageView.setLayoutX(imView.getLayoutX());
        imageView.setLayoutY(imView.getLayoutY());
        imageView.setPickOnBounds(imView.pickOnBoundsProperty().get());
        imageView.setPreserveRatio(imView.preserveRatioProperty().get());
        return imageView;
    }

    private void glowX() {
        root.lookup("#user").styleProperty().set("dropshadow(three-pass-box, red, 20, 0.5, 0, 0);");
        root.lookup("#user2").styleProperty().set("-fx-effect: none;");
    }

    private void glowO() {
        root.lookup("#user").styleProperty().set("-fx-effect: none;");
        root.lookup("#user2").styleProperty().set("dropshadow(three-pass-box, #19b2c6, 20, 0.5, 0, 0);");
    }

    private void recordAndSendStep() {

        if (!historyPlayback) {
            if (isOnlineGame && turn == me) {
                try {
                    bufferedWriter.write(turn.getID() + "-" + step + "-" + this.gameBoard.getRoomID() + "-" + this.gameBoard.getWinnerPlayer());
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } catch (IOException ex) {
                    Logger.getLogger(GameplayController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private boolean takeStep() {
        try {
            if ( gameBoard.gameEnded()||( step<0 || gameBoard.taken(step))) {
                return false;
            }
            gameBoard.take(step);

            recordAndSendStep();
        } catch (AlreadyTakenException ex) {
            Logger.getLogger(GameplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Button temp = getGameGridTextById(step + "");
        Platform.runLater(() -> {
            temp.setGraphic(turn != player ? copyImageView(root.getxImageView()) : copyImageView(root.getoImageView()));
        });

        return true;
    }

    private void AIPlayWait() {
        Thread AISleepPlayThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Platform.runLater(() -> {
                play();
            });
        });
        AISleepPlayThread.start();
    }

    private void play() {
        if (isAIGame && !historyPlayback) {
            if (turn == AIPlayer) {
                this.step = ai.play(gameBoard);
                takeStep();

                turn = AIPlayer == player ? opponent : player;
            } else {
                if (!takeStep()) {
                    return;
                }
                turn = AIPlayer;
            }
        } else {
            if (!takeStep()) {
                return;
            }
            turn = turn == player ? opponent : player;

        }
        if (turn == player) {
            glowX();
        } else {
            glowO();
        }

        if (gameBoard.gameEnded()) {
            Platform.runLater(() -> {
                Player winner = this.gameBoard.getWinnerPlayer();
                boolean isAIWinner = winner == AIPlayer;
                boolean isAIPlayerGame = player == AIPlayer || opponent == AIPlayer;
                if (!isOnlineGame) {
                    root.getPlayAgainImageView().setVisible(true);
                }else{
                    try {
                        bufferedWriter.write("null");
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(GameplayController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                this.root.getBackImageView().setVisible(true);
                Label winLoseLabel = this.root.getWinLoseLabel();
                if (winner != null && (player == AIPlayer || opponent == AIPlayer || isOnlineGame) && !historyPlayback) {
                    if (winner.getName().equals(me.getName())) {
                        winLoseLabel.setTextFill(Color.GREEN);
                        winLoseLabel.setText("Winner");
                        this.root.getWinnerMediaView().getMediaPlayer().play();
                        this.root.getWinnerMediaView().setVisible(true);
                    } else {
                        winLoseLabel.setTextFill(Color.DARKBLUE);
                        winLoseLabel.setText("Loser");
                        this.root.getLoserMediaView().getMediaPlayer().play();
                        this.root.getLoserMediaView().setVisible(true);
                    }

                }else if(winner != null && (player == AIPlayer || opponent == AIPlayer || isOnlineGame) && historyPlayback){
                    if (winner.getID() ==me.getID()) {
                        winLoseLabel.setTextFill(Color.GREEN);
                        winLoseLabel.setText("Winner");
                        this.root.getWinnerMediaView().getMediaPlayer().play();
                        this.root.getWinnerMediaView().setVisible(true);
                    } else {
                        winLoseLabel.setTextFill(Color.DARKBLUE);
                        winLoseLabel.setText("Loser");
                        this.root.getLoserMediaView().getMediaPlayer().play();
                        this.root.getLoserMediaView().setVisible(true);
                    }
                }
                else if (winner != null && !isOnlineGame) {
                    Player other = player.getName().equals(me.getName()) ? opponent : player;
                    winLoseLabel.setTextFill(Color.GREEN);
                    winLoseLabel.setText((winner.getName().equals(me.getName()) ? me.getName().toUpperCase() : other.getName().toUpperCase()) + " WINS");
                    this.root.getWinnerMediaView().getMediaPlayer().play();
                    this.root.getWinnerMediaView().setVisible(true);
                } else {
                    winLoseLabel.setTextFill(Color.CYAN);
                    winLoseLabel.setText("Draw");
                }
            });

        } else if (turn == AIPlayer && isAIGame) {
            AIPlayWait();
        }
    }

    private void playbackGame() {
        this.historyPlayback = true;
        Board historyBoard = this.gameBoard.copy();
        this.gameBoard = new Board(historyBoard.getPlayerX(), historyBoard.getPlayerO(), historyBoard.getRoomID(), historyBoard.getStartTime(), historyBoard.getEndTime());
        this.turn = player;
        int[] steps = historyBoard.getHistory();
//        int[] steps = {0, 4, 8, 7, 1, 2, 6, 3, 5};
        Thread th = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
            IntStream.of(steps).forEach((historyStep) -> {
                this.step = historyStep;
                Platform.runLater(() -> {
                    play();
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameplayController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        });
        th.start();
    }

    private void beginGame() {
        if (!this.gameBoard.gameEnded()) {
            if (isOnlineGame) {
                try {

                    bufferedWriter.write("step");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                } catch (IOException ex) {
                    Logger.getLogger(GameplayController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            turn = player;
            if (turn == AIPlayer) {
                play();
            }
        } else {
            playbackGame();
        }
    }

}
