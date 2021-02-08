package main.Connect_to_clinet.Model;


import main.Core.*;

import java.sql.*;
import java.util.Vector;

/**
 * @author youssefshaban
 */
public class ConnectToDatabaseModel {
    Connection con;
    PlayRoom room;
    int id;

    public int UserLogin(Player playerx) throws SQLException {
        openConnecttion();
        if (!checkConnection()) {
            return 0;
        }
        int id = 0;
        java.sql.Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select * from players where pName = " + "'" + playerx.getName() + "'" + "and pPass = " + "'" + playerx.getToken() + "'");

        if (rs.next()) {
            id = rs.getInt(1);

        }
        stmt.close();
        return id;

    }

    public int signUp(Player player) throws SQLException {
        openConnecttion();
        if (!checkConnection()) {
            return 0;
        }

        java.sql.Statement stmt = con.createStatement();

        PreparedStatement ps = con.prepareStatement("insert into players (pName,pPass) values(?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, player.getName());
        ps.setString(2, player.getToken());
        ps.executeUpdate();
        ResultSet rsw = ps.getGeneratedKeys();
        if (rsw.next()) {
            id = rsw.getInt(1);
        }
        stmt.close();
        System.out.println(id);
        return id;
    }

    public void openConnecttion() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TicTacToe", "javadbms", "NewPassword@@22");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insertStep(String step) throws SQLException {
        openConnecttion();
        if (!checkConnection()) {
            return 0;
        }
        java.sql.Statement stmt = con.createStatement();
        int stepS = Integer.parseInt(step.split("-")[1]);
        int playerID = Integer.parseInt(step.split("-")[0]);
        int roomID = Integer.parseInt(step.split("-")[2]);
        int rs = stmt.executeUpdate("insert into Steps (loc,playerID,roomID)values(" + stepS + "," + playerID + "," + roomID + ")");
        stmt.close();
        return rs;
    }

    public int insertRoom(int player) throws SQLException {
        openConnecttion();
        if (!checkConnection()) {
            return 0;
        }
        java.sql.Statement stmt = con.createStatement();
        int playerID = player;

        PreparedStatement ps = con.prepareStatement("insert into rooms (Player1) values(?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, player);
        ps.executeUpdate();
        ResultSet rsw = ps.getGeneratedKeys();
        if (rsw.next()) {
            id = rsw.getInt(1);
        }
        stmt.close();
        return id;
    }

    public String insertPlayer(int room, Player player) throws SQLException {
        openConnecttion();

        if (!checkConnection()) {
            return null;
        }
        PlayRoom room2;
        Player playerW=null;
        Player playerX = null;
        Player player0 = null;
        try {
            java.sql.Statement stmt = con.createStatement();
            java.sql.Statement stmtaddd = con.createStatement();
             stmt.executeUpdate("update rooms set player2 ="+player.getID() +" where roomid = "+ room);
            java.sql.Statement stmt2 = con.createStatement();
            java.sql.Statement stmt3 = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from rooms where roomid = " + room);
            while (rs.next()) {
                System.out.println("select * from Players  where ID in (" + rs.getInt(4) + "," + rs.getInt(5) + ")");
                ResultSet GetPlayerX = stmt2.executeQuery("select * from Players  where ID in (" + rs.getInt(4) + "," + rs.getInt(5) + ")");
                while (GetPlayerX.next()) {
                    if (playerX == null)
                        playerX = new Player(GetPlayerX.getInt(1), GetPlayerX.getString(2), GetPlayerX.getString(3));
                    else
                        player0 = new Player(GetPlayerX.getInt(1), GetPlayerX.getString(2), GetPlayerX.getString(3));
                }
                room2 = new PlayRoom(playerX, player0, playerW, rs.getInt(1), rs.getDate(2), rs.getDate(3));
            }
            stmt.close();
            stmt2.close();
            stmt3.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return playerX.getID()+"-"+player0.getID()+"-"+playerX.getName()+"-"+player0.getName();
    }

    public Vector<PlayRoom> getAllHistory(int id) throws SQLException {
        openConnecttion();
        if (!checkConnection()) {
            return null;
        }
        Vector<PlayRoom> listOfRooms = new Vector<PlayRoom>();
        Player playerX = null;
        Player player0 = null;
        Player playerW = null;
        try {
            java.sql.Statement stmt = con.createStatement();
            java.sql.Statement stmt2 = con.createStatement();
            java.sql.Statement stmt3 = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from rooms where Player1 = " + id + " or Player2 = " + id);
            while (rs.next()) {
                ResultSet GetPlayerX = stmt2.executeQuery("select * from Players  where ID in (" + rs.getInt(4) + "," + rs.getInt(5) + ")");
                while (GetPlayerX.next()) {
                    if (playerX == null)
                        playerX = new Player(GetPlayerX.getInt(1), GetPlayerX.getString(2), GetPlayerX.getString(3));
                    else
                        player0 = new Player(GetPlayerX.getInt(1), GetPlayerX.getString(2), GetPlayerX.getString(3));
                }
                room = new PlayRoom(playerX, player0, playerW, rs.getInt(1), rs.getDate(2), rs.getDate(3));
                listOfRooms.add(room);
            }
            stmt.close();
            stmt2.close();
            stmt3.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOfRooms;
    }

    public String getRoomHistory(int roomIDFromClient) throws SQLException {
        openConnecttion();
        if (!checkConnection()) {
            return null;
        }
        Player playerX = null;
        Player player0 = null;
        String fil = "";
        int[] locations = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
        int i = 0;
        Board b = new Board();
        java.sql.Statement stmt = con.createStatement();
        java.sql.Statement stmt2 = con.createStatement();
        java.sql.Statement stmt4 = con.createStatement();
        java.sql.Statement stmt3 = con.createStatement();

        ResultSet rs2 = stmt2.executeQuery("select * from rooms where roomID = " + roomIDFromClient);
        if (rs2.next()) {
            ResultSet GetPlayerX = stmt4.executeQuery("select * from Players  where ID = " + rs2.getInt(4));
            if (GetPlayerX.next()) {
                playerX = new Player(GetPlayerX.getInt(1), GetPlayerX.getString(2), GetPlayerX.getString(3));
            }
            ResultSet GetPlayer0 = stmt3.executeQuery("select * from Players where ID = " + rs2.getInt(5));
            if (GetPlayerX.next()) {
                player0 = new Player(GetPlayer0.getInt(1), GetPlayer0.getString(2), GetPlayer0.getString(3));
            }
            b.setPlayerO(player0);
            b.setPlayerX(playerX);
            fil = rs2.getInt(4) + "-" + rs2.getInt(5) + "-";
        }






        Player playerX1 = null;
        Player player02 = null;
        try {
            java.sql.Statement stmt1 = con.createStatement();
            java.sql.Statement stmt21 = con.createStatement();
            ResultSet rs1 = stmt1.executeQuery("select * from rooms where roomid = " + roomIDFromClient);
            while (rs1.next()) {
                ResultSet GetPlayerX = stmt21.executeQuery("select * from Players  where ID in (" + rs1.getInt(4) + "," + rs1.getInt(5) + ")");
                while (GetPlayerX.next()) {
                    if (playerX1 == null)
                        playerX1 = new Player(GetPlayerX.getInt(1), GetPlayerX.getString(2), GetPlayerX.getString(3));
                    else
                        player02 = new Player(GetPlayerX.getInt(1), GetPlayerX.getString(2), GetPlayerX.getString(3));
                }
            }
            stmt1.close();
            stmt21.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }






        ResultSet rs = stmt.executeQuery("select * from steps where roomID = " + roomIDFromClient);
        while (rs.next()) {

            try {
                locations[i] = rs.getInt(3);
                i++;
            } catch (Exception e) {


            }
        }
        for (int x = 0; x < locations.length; x++)
            if (x != locations.length - 1) {
                if (locations[x] != -1)
                    fil = fil.concat(locations[x] + ",");
            } else {
                if (locations[x] != -1)
                    fil = fil.concat(locations[x] + "");
            }
        stmt.close();
        stmt2.close();
        stmt3.close();


        fil=fil.concat("-"+playerX1.getName()+"-"+player02.getName());
        return fil;
    }


    public Vector getOpenRooms() throws SQLException {

        Vector<PlayRoom> listOfRooms = new Vector<PlayRoom>();
        openConnecttion();

        if (!checkConnection()) {
            return null;
        }

        Player playerX = null;
        try {
            java.sql.Statement stmt = con.createStatement();
            java.sql.Statement stmt2 = con.createStatement();
            java.sql.Statement stmt3 = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from rooms where player2 IS NULL and winnerid IS NULL");
            while (rs.next()) {
                ResultSet GetPlayerX = stmt2.executeQuery("select * from Players  where ID = " + rs.getInt(4));
                if (GetPlayerX.next()) {
                    playerX = new Player(GetPlayerX.getInt(1), GetPlayerX.getString(2), GetPlayerX.getString(3));
                }
                room = new PlayRoom(playerX, rs.getInt(1), rs.getDate(2), rs.getDate(3));
                listOfRooms.add(room);

            }
            stmt.close();
            stmt2.close();
            stmt3.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOfRooms;
    }

    boolean checkConnection() {
        if (con == null) {

            return false;
        } else
            return true;
    }
}
