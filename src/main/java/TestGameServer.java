import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.constant.Constable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class TestGameServer implements Serializable {

    private static final long serialVersionUID = 1L;
    private int turnsMade;
    private int maxTurns;
    private boolean gameEnd;

    TestGameServer.Server[] playerServer = new TestGameServer.Server[3];
    Player[] players = new Player[3];

    ServerSocket ss;

    Game game = new Game();
    int numPlayers;
    int[] scores;
    int[] turns;

    public static void main(String args[]) throws Exception {
        TestGameServer sr = new TestGameServer();

        sr.acceptConnections();
        sr.gameLoop();
    }

    public TestGameServer() {
        //System.out.println("Starting game server");
        numPlayers = 0;
        turnsMade = 0;
        maxTurns = 13;
        gameEnd = true;
        scores = new int[3];
        turns = new int[3];
        scores[0] = 0;
        scores[1] = 0;
        scores[2] = 0;
        turns[0] = 0;
        turns[1] = 0;
        turns[2] = 0;
        // initialize the players list with new players
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(" ");
        }


    }

    public void acceptConnections() throws ClassNotFoundException {


    }

    public void gameLoop() {

        int check = returnWinner();

    }

    public int returnWinner() {
        int j = 0;
        int highest=0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > highest) {
                highest = scores[i];
                j = i;
            }
        }

        return j+1;

    }


    public class Server implements Runnable {
        private Socket socket;
        private ObjectInputStream dIn;
        private ObjectOutputStream dOut;
        private int playerId;

        public Server(Socket s, int playerid) {
            socket = s;
            playerId = playerid;
            try {
                dOut = new ObjectOutputStream(socket.getOutputStream());
                dIn = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                System.out.println("Server Connection failed");
            }
        }
        public void run() {
            try {
                while (true) {
                }

            } catch (Exception ex) {
                {
                    System.out.println("Run failed");
                    ex.printStackTrace();
                }
            }
        }



    }

    public void addToSheet(int pt, int i) {
        if (pt >= 0) {
            scores[i] = scores[i] + pt;
        } else {
            scores[i] = scores[i] + pt;
            if (scores[i] < 0) {
                scores[i] = 0;
            }
        }

    }

    public void playTurn(int playerNum,Dice[] dice, Card card) {
        Dice[] d = new Dice[8];
        Card c;
        turns[playerNum] += 1;
        c = card;
        d = dice;

        int[] positions2 = {1,2,3,4,5,6,7,8};
        game.DiceOutChest(d,positions2);
        if (c.reveal() == "Sea Battle") {
            SeaBattle(d, c, playerNum);
        } else if (game.isIsleOfDead(d, 1, c)) {
            IsleOfDead(d, c, playerNum);
        }
        else if(c.reveal()=="Chest") {
            ChestPlay(d,c,playerNum);
        }else{
            String temp = "";

            if (!game.endTurn(d, c)) {
                addToSheet(game.regularPts(d, c), playerNum);
            }
            //System.out.println("Shuffling dices");
        }
    }



    public void SeaBattle(Dice[] d, Card c, int playerNum) {
        addToSheet(game.SeaBattlePts(d,c), playerNum);
    }

    public void IsleOfDead(Dice[] d, Card c, int playerNum) {
        for (int i = 0; i < scores.length; i++) {
            if (playerNum == i) {
            } else {
                addToSheet(-game.IslePts(d, c), i);
            }
        }

    }

    public void ChestPlay(Dice[] d, Card c, int playerNum)
    {
        addToSheet(game.ChestPts(d, c), playerNum);
    }


}
