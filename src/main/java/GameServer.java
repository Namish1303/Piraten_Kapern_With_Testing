
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.constant.Constable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class GameServer implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int turnsMade;
    private int maxTurns;
    private boolean gameEnd;

    Server[] playerServer = new Server[3];
    Player[] players = new Player[3];

    ServerSocket ss;

    Game game = new Game();
    int numPlayers;
    int[] scores;
    int[] turns;

    public static void main(String args[]) throws Exception {
        GameServer sr = new GameServer();

        sr.acceptConnections();
        sr.gameLoop();
    }

    public GameServer() {
        System.out.println("Starting game server");
        numPlayers = 0;
        turnsMade = 0;
        maxTurns = 13;
        gameEnd = true;
        scores = new int[3];
        turns = new int[3];
        scores[0] = 0;
        scores[1] = 2950;
        scores[2] = 0;
        turns[0] = 0;
        turns[1] = 0;
        turns[2] = 0;
        // initialize the players list with new players
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(" ");
        }

        try {
            ss = new ServerSocket(51303);
        } catch (IOException ex) {
            System.out.println("Server Failed to open");
        }

    }

    /*
     * -----------Networking stuff ----------
     *
     */
    public void acceptConnections() throws ClassNotFoundException {
        try {
            System.out.println("Waiting for players...");
            while (numPlayers < 3) {
                Socket s = ss.accept();
                numPlayers++;

                Server server = new Server(s, numPlayers);

                // send the player number
                server.dOut.writeInt(server.playerId);
                server.dOut.flush();

                // get the player name
                //Player in = (Player) server.dIn.readObject();
                //System.out.println("Player " + server.playerId + " ~ " + in.name + " ~ has joined");
                System.out.println("Player Joined");
                // add the player to the player list
                //players[server.playerId - 1] = in;
                playerServer[numPlayers - 1] = server;
            }
            System.out.println("Three players have joined the game");

            // start the server threads
            for (int i = 0; i < playerServer.length; i++) {
                Thread t = new Thread(playerServer[i]);
                t.start();
            }
            // start their threads
        } catch (IOException ex) {
            System.out.println("Could not connect 3 players");
        }
    }

    public void gameLoop() {
        /*
        int pts = 0;
        while (gameEnd) {

            // for player 1
            playerServer[0].sendTurnNo(1);
            playTurn(playerServer[0], 0);
            turns[0] += 1;
            if (game.isGameOver(scores, turns)) {
                System.out.println(scores);
                System.out.println(turns);
                gameEnd = false;
                break;
            }
            ///pts += playerServer[0].receiveScores();

            // for player 2
            playerServer[1].sendTurnNo(1);
            playTurn(playerServer[1], 1);
            turns[1] += 1;
            if (game.isGameOver(scores, turns)) {
                gameEnd = false;
                break;
            }
            ////pts += playerServer[1].receiveScores();

            // for player 3
            playerServer[2].sendTurnNo(1);
            playTurn(playerServer[2], 2);
            turns[2] += 1;
            if (game.isGameOver(scores, turns)) {
                System.out.println("SCORE 3: "+scores[2]);
                System.out.println("TURNS 3: "+turns[2]);

                gameEnd = false;
                break;
            }

        }
        //announce winner after the loop ends
        returnWinner();*/

    }

   /* public void returnWinner() {
        int j = 0;
        int highest=0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > highest) {
                highest = scores[i];
                j = i;
            }
        }

        for (int i = 0; i < playerServer.length; i++) {
            if (i == j) {
                try {
                    playerServer[i].sendTurnNo(0);
                    playerServer[i].dOut.writeUTF("\n\n\n"+game.gameScores(scores));
                    playerServer[i].dOut.flush();
                }
                catch (IOException e)
                {
                    System.out.println("RESULT NOT SENT");
                }

            } else {
                try {
                    playerServer[i].sendTurnNo(-1);
                    playerServer[i].dOut.writeUTF("\n\n\n"+game.gameScores(scores));
                    playerServer[i].dOut.flush();
                }
                catch (IOException e)
                {
                    System.out.println("RESULT NOT SENT");
                }

            }
        }

    }*/


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

        /*
         * run function for threads --> main body of the thread will start here
         */
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

        /*
         * send the scores to other players
         */
        public void sendPlayers(Player[] pl) {
            try {
                for (Player p : pl) {
                    dOut.writeObject(p);
                    dOut.flush();
                }

            } catch (IOException ex) {
                System.out.println("Score sheet not sent");
                ex.printStackTrace();
            }

        }

        /*
         * receive scores of other players
         */
        public void sendTurnNo(int r) {
            try {
                dOut.writeInt(r);
                dOut.flush();
            } catch (Exception e) {
                System.out.println("Score sheet not received");
                e.printStackTrace();
            }
        }

        /*
         * receive scores of other players
         */
        public int receiveScores() {
            try {
                int sc = 0;
                sc = dIn.readInt();
                return sc;
            } catch (Exception e) {
                System.out.println("Score sheet not received");
                e.printStackTrace();
            }
            return -1;
        }

        /*
         * send scores of other players
         */
        public void sendScores(Player[] pl) {
            try {
                for (int i = 0; i < pl.length; i++) {

                    dOut.writeInt(pl[i].getPoints());

                }
                dOut.flush();
            } catch (Exception e) {
                System.out.println("Score sheet not sent");
                e.printStackTrace();
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

   /* public void playTurn(Server s, int playerNum) {
        Dice[] d = new Dice[8];
        Card c;
        c = game.getGameCard();
        d = game.getGameDices();
        try {
            s.dOut.writeUTF(game.gameScores(scores));
            s.dOut.flush();
        } catch (IOException e) {
            System.out.println("Scores not sent");
        }
        if (c.reveal() == "Sea Battle") {
            SeaBattle(s, d, c, playerNum);
        } else if (game.isIsleOfDead(d, 1, c)) {
            IsleOfDead(s, d, c, playerNum);
        } else {
            String temp = "";
            while (true) {
                try {
                    int choice;

                    s.dOut.writeUTF(temp + "\n" + game.printDandC(d, c));
                    temp = "";
                    s.dOut.flush();
                    if (game.isDead(d, c)) {
                        s.dOut.writeInt(-1);
                        s.dOut.writeUTF("You Are Dead !!!! Chance over \n You scored 0");
                        s.dOut.flush();

                        break;
                    } else {
                        s.dOut.writeInt(1);
                        s.dOut.writeUTF("1. Roll Dices \n2. End Turn");
                        s.dOut.flush();

                        System.out.println("REACHED");
                        choice = s.dIn.readInt();
                        System.out.println(choice);
                    }
                    if (choice == 1) {
                        String[] positions;
                        s.dOut.writeInt(100);
                        s.dOut.flush();
                        s.dOut.writeUTF("Enter positions of dice you want to reroll (1,4,5...)");
                        s.dOut.flush();
                        positions = s.dIn.readUTF().split(",");
                        int[] temp2 = new int[positions.length];
                        for (int j = 0; j < positions.length; j++) {
                            temp2[j] = Integer.parseInt(positions[j]);
                        }
                        if (game.isMoveValid(d, temp2, c) == 0) {
                            temp = "Cannot roll Skulls";
                            continue;
                        } else if (game.isMoveValid(d, temp2, c) == 1) {
                            d = game.shuffleDice(d, temp2);
                            continue;
                        } else {
                            d = game.shuffleDice(d, temp2);
                            c = new Card("NULL", 1, 0);
                            continue;
                        }
                        //System.out.println("Shuffling dices");

                    } else if (choice == 2) {
                        s.dOut.writeInt(200);
                        s.dOut.flush();
                        s.dOut.writeUTF("Chance Ended!! \n You scored " + game.regularPts(d, c));
                        s.dOut.flush();
                        //s.dIn.readUTF();
                        addToSheet(game.regularPts(d, c), playerNum);
                        break;
                    } else if (choice == 3) {   // for treasure chest card
                        String[] positions;
                        s.dOut.writeUTF("Enter positions of dice you want to keep in chest (1,4,5...)");
                        s.dOut.flush();
                        positions = s.dIn.readUTF().split(",");
                        int[] temp2 = new int[positions.length];
                        for (int j = 0; j < positions.length; j++) {
                            temp2[j] = Integer.parseInt(positions[j]);
                        }

                        //System.out.println("Shuffling dices");
                        d = game.shuffleDice(d, temp2);
                        continue;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void SeaBattle(Server s, Dice[] d, Card c, int playerNum) {
        String temp = "";
        while (true) {
            try {
                int choice;

                s.dOut.writeUTF(temp + "\n" + game.printDandC(d, c));
                temp = "";
                s.dOut.flush();

                if (game.isDead(d, c)) {
                    s.dOut.writeInt(-1);
                    s.dOut.writeUTF("You Are Dead !!!! Chance over \n You scored 0 but received a deduction of " + c.bonus);
                    s.dOut.flush();
                    addToSheet(0 - c.bonus, playerNum);
                    break;

                } else {
                    s.dOut.writeInt(1);
                    s.dOut.writeUTF("1. Roll Dices \n2. End Turn");
                    s.dOut.flush();

                    //System.out.println("REACHED");
                    choice = s.dIn.readInt();
                    System.out.println(choice);
                }
                if (choice == 1) {
                    String[] positions;
                    s.dOut.writeInt(100);
                    s.dOut.flush();
                    s.dOut.writeUTF("Enter positions of dice you want to reroll (1,4,5...)");
                    s.dOut.flush();
                    positions = s.dIn.readUTF().split(",");
                    int[] temp2 = new int[positions.length];
                    for (int j = 0; j < positions.length; j++) {
                        temp2[j] = Integer.parseInt(positions[j]);
                    }
                    if (game.isMoveValid(d, temp2, c) == 0) {
                        temp = "Cannot roll Skulls";
                        continue;
                    } else if (game.isMoveValid(d, temp2, c) == 1) {
                        d = game.shuffleDice(d, temp2);
                        continue;
                    } else {
                        d = game.shuffleDice(d, temp2);
                        c = null;
                        continue;
                    }
                    //System.out.println("Shuffling dices");

                } else if (choice == 2) {
                    int pts = 0;
                    s.dOut.writeInt(200);
                    s.dOut.flush();
                    HashMap<String, Integer> tempMap = new HashMap<>();
                    tempMap = game.DiceToCollection(d);
                    if (tempMap.containsKey("Sword")) {
                        if (tempMap.get("Sword") >= c.number) {
                            pts += c.bonus;
                            pts += game.regularPts(d, c);
                        }
                        else
                        {
                            pts = 0 - c.bonus;
                        }
                    }
                    else
                    {
                        pts = 0 - c.bonus;
                    }

                    s.dOut.writeUTF("Chance Ended!! \n You scored " + pts);
                    s.dOut.flush();
                    //s.dIn.readUTF();
                    addToSheet(pts, playerNum);
                    break;
                }


                choice = s.dIn.readInt();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void IsleOfDead(Server s, Dice[] d, Card c, int playerNum) {
        int choice;
        String tempS;
        HashMap<String, Integer> before = new HashMap<>();
        HashMap<String, Integer> after = new HashMap<>();
        before = game.DiceToCollection(d);
        after = null;
        tempS = "Welcome to Isle of Dead";
        while (true) {
            try {
                s.dOut.writeUTF(game.printDandC(d, c) + "\n" + tempS);
                tempS = "";
                s.dOut.flush();

                if (after != null && after.get("Skull") == before.get("Skull")) {
                    s.dOut.writeInt(-1);
                    s.dOut.writeUTF("Could not roll Skull!! \n You deducted " + game.IslePts(d, c) + " of all other players");
                    s.dOut.flush();
                    break;

                } else {
                    s.dOut.writeInt(1);
                    s.dOut.writeUTF("1. Roll Dices \n2. End Turn");
                    s.dOut.flush();

                    choice = s.dIn.readInt();

                    if (choice == 1) {
                        String[] positions;
                        s.dOut.writeInt(100);
                        s.dOut.flush();
                        s.dOut.writeUTF("Enter positions of dice you want to reroll (1,4,5...)");
                        s.dOut.flush();
                        positions = s.dIn.readUTF().split(",");
                        int[] temp2 = new int[positions.length];
                        for (int j = 0; j < positions.length; j++) {
                            temp2[j] = Integer.parseInt(positions[j]);
                        }
                        if (game.isMoveValid(d, temp2, c) == 0) {
                            tempS = "Cannot roll Skulls";
                            continue;
                        } else if (game.isMoveValid(d, temp2, c) == 1) {
                            before = game.DiceToCollection(d);
                            d = game.shuffleDice(d, temp2);
                            after = game.DiceToCollection(d);
                            continue;
                        } else {
                            before = game.DiceToCollection(d);
                            d = game.shuffleDice(d, temp2);
                            after = game.DiceToCollection(d);
                            c = null;
                            continue;
                        }
                    } else {
                        s.dOut.writeInt(200);
                        s.dOut.flush();

                        s.dOut.writeUTF("Chance Ended!! \n You deducted " + game.IslePts(d, c) + " of all other players");
                        s.dOut.flush();
                        //s.dIn.readUTF();


                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("ISLE OF DEAD FAILED");
            }
        }
        for (int i = 0; i < scores.length; i++) {
            if (playerNum == i) {
                continue;
            } else {
                addToSheet(0 - game.IslePts(d, c), i);
            }
        }

    }*/

}



