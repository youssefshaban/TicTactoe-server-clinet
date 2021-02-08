package Core;

import java.io.Serializable;

public class Player implements Serializable {

    int id = 0;
    String name = "";
    String token = "";
    int totalScore = 100;

    public Player(int ID, String Name, String Token) {
        id = ID;
        name = Name;
        token = Token;
    }

    public Player() {

    }

    public Player(String s1, String s2) {
       name = s1;
       token = s2;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int Total) {
        totalScore = Total;
    }

    public void addWinningScore() {
        totalScore = totalScore + 10;
    }

    public void removeLosingScore() {
        if (totalScore - 10 < 0) {
            totalScore = totalScore - 10;
        } else {
            totalScore = 0;
        }
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }
}
