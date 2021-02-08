package main.Core;

import java.io.Serializable;

public class Player implements Serializable {

    int id=0;
    String name="";
    String password="";
    int totalScore=100;
    public Player(int ID, String Name, String Token){
        id = ID;
        name= Name;
        password=Token;
    }
      public Player(){
       
    }

    public Player(String Name, String Password) {
       name= Name;
       password = Password;
    }



    public void setTotalScore(int Total){
        totalScore = Total;
    }
    public void addWinningScore(){
        totalScore=totalScore+10;
    }
    public void removeLosingScore(){
        if(totalScore-10<0)
            totalScore=totalScore-10;
        else
            totalScore=0;
    }
    public int getID(){
    return id;
    }
     public String getName(){
    return name;
    }
      public String getToken(){
    return password;
    }

    public void setName(String Name) {
      name = Name;
    }

    public void setPassword(String Password) {
       password = Password;
    }

    public void setID(int checker) {
       id = checker;
    }
}
