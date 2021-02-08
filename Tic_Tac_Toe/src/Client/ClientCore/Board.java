/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.ClientCore;

import Core.Player;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author John
 */

public class Board implements Serializable {
    private Player playerX;
    private Player playerO;
    private Player winnerPlayer;
    private int roomID;
    
    private Date startTime;
    private Date endTime;
    
    private int turn = 0;
    private boolean gameOver = false;
    private int winner = -1;
    private final int[] grid = new int[9];
    private final int[] history = new int[9];

    public Board(Player playerX,Player playerO , int roomID, Date startTime,Date endTime) {
        for (int i = 0; i < 9; i++) {
            grid[i] = -1;
            history[i] = -1;
        }
        this.playerX = playerX;
        this.playerO = playerO;
        this.roomID = roomID;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Board() {
         for (int i = 0; i < 9; i++) {
            grid[i] = -1;
            history[i] = -1;
        }
    
    }

    public int getRoomID() {
        return roomID;
    }

    public Player getWinnerPlayer() {
        return winnerPlayer;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int[] getHistory() {
        return history;
    }

    public Player getPlayerO() {
        return playerO;
    }

    public Player getPlayerX() {
        return playerX;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
    
    public void setPlayerX(Player playerO){
        this.playerX = playerO;
    }
    
    public void setPlayerO(Player playerO){
        this.playerO = playerO;
    }
    
    private void setWinnerPlayer(int winner){
        this.winnerPlayer = winner==0?playerX:playerO;
    }

    public int[] getGrid() {
        return grid;
    }
    
    public void take(int square) throws AlreadyTakenException {
        if (!gameOver) {
            if (!taken(square)) {
                grid[square] = turn;
                history[turn] = square;
            } else {
                throw new AlreadyTakenException();
            }

            checkVictory();

            if (turn < 8) {
                turn++;
            } else {
                gameOver = true;
                this.endTime = new Date();
            }
        }
    }

    public boolean gameEnded() {
        return gameOver;
    }

    public int getWinner() {
        return this.winner;
    }
    
    

    public int nextPlayer() {
        return turn % 2;
    }

    public int owns(int i) {
        assert taken(i);
        return grid[i] % 2;
    }

    public boolean taken(int i) {
        return grid[i] >= 0;
    }

    private void checkVictory() {
        boolean[][] owned = new boolean[2][9];

        for (int i = 0; i < grid.length; i++) {
            owned[0][i] = false;
            owned[1][i] = false;
            if (taken(i)) {
                owned[owns(i)][i] = true;
            }
        }

        for (int p = 0; p < 2; p++) {
            boolean hasWon = false;

            for (int i = 0; i < 3; i++) {
                hasWon |= owned[p][3 * i] && owned[p][3 * i + 1] && owned[p][3 * i + 2];
                hasWon |= owned[p][i] && owned[p][i + 3] && owned[p][i + 6];
            }
            hasWon |= owned[p][0] && owned[p][4] && owned[p][8];
            hasWon |= owned[p][2] && owned[p][4] && owned[p][6];

            if (hasWon) {
                winner = p;
                setWinnerPlayer(p);
                gameOver = true;
                this.endTime = new Date();
            }
        }
    }

    public Board copy() {
        Board board = new Board(playerX, playerO, roomID, startTime, endTime);

        for (int i : history) {
            if (i == -1) {
                break;
            }
            try {
                board.take(i);
            } catch (AlreadyTakenException e) {
                throw new RuntimeException(e);
            }
        }

        return board;
    }
}
