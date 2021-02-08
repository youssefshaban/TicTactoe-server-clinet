package main.Core;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Vector;

public class PlayRoom implements Serializable  {
    Player playerX;
    Player playerO;
    int roomID;
    Date startTime;
    Date endTime;
    Vector<Step> steps = new Vector<Step>();
    Player winner;
    public PlayRoom(Player x,Player o, Player w ,int RoomID,Date StartTime,Date EndTime){
        playerX=x;
        playerO=o;
        winner=w;
        roomID=RoomID;
        startTime=StartTime;
        endTime=EndTime;
    }
     public PlayRoom(Player x,Player o ,int RoomID,Date StartTime,Date EndTime){
        playerX=x;
        playerO=o;
        roomID=RoomID;
        startTime=StartTime;
        endTime=EndTime;
    }
     public PlayRoom(Player x, int RoomID,Date StartTime,Date EndTime){
        playerX=x;
        roomID=RoomID;
        startTime=StartTime;
        endTime=EndTime;
    }
     

    public PlayRoom() {
    }

    public PlayRoom(int check, Player id, Instant now) {
        roomID= check;
        playerX=id;

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

    public int getID() {
        return roomID;
    }

    public Player getPlayerX() {
       return playerX;
    }

    public Player getPlayerO() {
      return playerO;
    }

    public Player getWinner() {
       return winner;
    }
    public Date getStartDate(){
    return startTime;
    }
      public Date getEndDate(){
    return endTime;
    }
      public void setRoomID(int id ){
      roomID = id;
      }

    public int getRoomID() {
        return roomID;
    }
}
