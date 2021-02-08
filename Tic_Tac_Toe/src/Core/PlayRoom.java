package Core;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PlayRoom implements Serializable  {
    Player playerX;
    Player playerO;
    int roomID;
    Date startTime;
    Date endTime;
    List<Step> steps;
    Player winner;
    public PlayRoom(Player x,Player o , int RoomID,Date StartTime,Date EndTime){
        playerX=x;
        playerO=o;
        roomID=RoomID;
        startTime=StartTime;
        endTime=EndTime;
    }

    public PlayRoom() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PlayRoom(Player playerX, Player player0, Player playerW, int aInt, java.sql.Date date, java.sql.Date date0) {
          playerX=playerX;
        playerO=player0;
        winner = playerW;
        roomID=aInt;
        startTime=date;
        endTime=date0;
    }

    public PlayRoom(Player player, int aInt, java.sql.Date date, java.sql.Date date0) {
      playerX=player;
      roomID=aInt;
      startTime=date;
      endTime=date0;
      
    }

    public void setStep(Step step){
        steps.add(step);
    }
    public void setWinner(Player Winner){
        winner = Winner;
    }
    public void setPlayerO(Player o){
        playerO = o;
    }
    public Player getPlayerX()
    {
        return playerX;
    }
    public Player getPlayerO()
    {
        return playerO;
    }
    public int getID()
    {
        return roomID;
    }
    public void setRoomID(int x)
    {
        roomID=x;
    }
}
