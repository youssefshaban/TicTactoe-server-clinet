package Core;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

public class Step implements Serializable  {
    int playerID;
    int stepOn;
    int roomID;
    Date time;
    Player winner;
    public Step(int PlayerId,int StepOn,int RoomId,Date Time,Player Winner ){
        playerID=PlayerId;
        winner=Winner;
        stepOn=StepOn;
        roomID=RoomId;
        time=Time;
    }
    public int getPlayerID(){return playerID;}
    public int getStepPosition(){return stepOn;}
    public int getroomID(){return roomID;}
    public Instant getTime(){return Instant.now();}
    public Player getWinner(){return winner;}
}
