
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class Player implements Serializable {

    /*
     * score sheet is saved as a hashmap upper one, two, three, four, five, six
     * lower 3ok, 4ok, full, sst, lst, yahtzee, chance, lowerbonus, upperbonus
     */

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public String name;

    int playerId = 0;

    Game game = new Game();
    private int pts;

    static Client clientConnection;

    Player[] players = new Player[3];
//	private ArrayList<String> scoreSheetKey = new ArrayList<String>(Arrays.asList("one", "two", "three", "four", "five",
//			"six", "3ok", "4ok", "full", "sst", "lst", "yahtzee", "chance", "bonus"));

    /*
     * play a round of the game
     */
    public void playRound() {
        try {
            System.out.println(clientConnection.dIn.readUTF());
        }
        catch (IOException e)
        {
            System.out.println("Score Sheet not received");
        }
        while(true) {
            try {
                Scanner myObj = new Scanner(System.in);
                int result;
                System.out.println(clientConnection.dIn.readUTF());
                result = clientConnection.dIn.readInt();


                if(result == -1)
                {
                    System.out.println(clientConnection.dIn.readUTF());
                    break;
                    //return 0;
                }
                else
                {
                    System.out.println(clientConnection.dIn.readUTF());


                    int num = myObj.nextInt();
                    clientConnection.dOut.writeInt(num);
                    clientConnection.dOut.flush();
                    num = clientConnection.dIn.readInt();

                    if(num == 100)
                    {
                        System.out.println(clientConnection.dIn.readUTF());
                        clientConnection.dOut.writeUTF(myObj.next());
                        clientConnection.dOut.flush();
                    }
                    else
                    {
                        System.out.println(clientConnection.dIn.readUTF());
                        break;
                    }



                }


            } catch (IOException e) {
                System.out.println("Did not receive anything");
                //return -1;
            }
        }
        // return 100;

    }



    public Player getPlayer() {
        return this;
    }

    /*
     * ----------Network Stuff------------
     */

    /*
     * send the to do to test server
     */
    public void sendStringToServer(String str) {
        clientConnection.sendString(str);
    }

    public void connectToClient() {
        clientConnection = new Client();
    }

    public void connectToClient(int port) {
        clientConnection = new Client(port);
    }

    public void initializePlayers() {
        for (int i = 0; i < 3; i++) {
            players[i] = new Player(" ");
        }
    }

    public void startGame() {
        while (true) {
            // System.out.println("In Start Game");
            int num = clientConnection.receiveRoundNo();
            // System.out.println(num);
            if (num == 1) {
                //clientConnection.sendScores(playRound());
                playRound();
            }
            else if(num == 0)
            {
                try {
                    System.out.println(clientConnection.dIn.readUTF());
                    System.out.println("You won the Game!!!!");

                    break;
                }
                catch (IOException e)
                {
                    System.out.println("WRONG RESULT in Player");
                }
            }
            else
            {
                try {
                    System.out.println(clientConnection.dIn.readUTF());
                    System.out.println("You Lost !!!");
                    break;
                }
                catch (IOException e)
                {
                    System.out.println("WRONG RESULT IN PLAYER");
                }
            }
        }
    }

    private class Client {
        Socket socket;
        private ObjectInputStream dIn;
        private ObjectOutputStream dOut;

        public Client() {
            try {
                socket = new Socket("localhost", 51303);
                dOut = new ObjectOutputStream(socket.getOutputStream());
                dIn = new ObjectInputStream(socket.getInputStream());

                playerId = dIn.readInt();

                System.out.println("Connected as " + playerId);
                // sendPlayer();

            } catch (IOException ex) {
                System.out.println("Client failed to open");
            }
        }

        public Client(int portId) {
            try {
                socket = new Socket("localhost", portId);
                dOut = new ObjectOutputStream(socket.getOutputStream());
                dIn = new ObjectInputStream(socket.getInputStream());

                playerId = dIn.readInt();

                System.out.println("Connected as " + playerId);
                sendPlayer();

            } catch (IOException ex) {
                System.out.println("Client failed to open");
            }
        }

        /*
         * function to send the score sheet to the server
         */
        public void sendPlayer() {
            try {
                System.out.println(getPlayer().getClass());
                dOut.writeObject(getPlayer());
                dOut.flush();
            } catch (IOException ex) {
                System.out.println("Player not sent");
                ex.printStackTrace();
            }
        }

        /*
         * function to send strings
         */
        public void sendString(String str) {
            try {
                dOut.writeUTF(str);
                dOut.flush();
            } catch (IOException ex) {
                System.out.println("Player not sent");
                ex.printStackTrace();
            }
        }

        /*
         * receive scoresheet
         */
        public void sendScores(int score) {
            try {

                dOut.writeInt(score);

                dOut.flush();

            } catch (IOException e) {
                System.out.println("Score sheet not received");
                e.printStackTrace();
            }
        }

        /*
         * receive scores of other players
         */
        public Player[] receivePlayer() {
            Player[] pl = new Player[3];
            try {
                Player p = (Player) dIn.readObject();
                pl[0] = p;
                p = (Player) dIn.readObject();
                pl[1] = p;
                p = (Player) dIn.readObject();
                pl[2] = p;
                return pl;

            } catch (IOException e) {
                System.out.println("Score sheet not received");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                e.printStackTrace();
            }
            return pl;
        }

        /*
         * receive scores of other players
         */
        public int[][] receiveScores() {
            try {
                int[][] sc = new int[3][15];
                for (int j = 0; j < 3; j++) {
                    for (int i = 0; i < 15; i++) {
                        sc[j][i] = dIn.readInt();
                    }
                    System.out.println();
                }

                return sc;
            } catch (Exception e) {
                System.out.println("Score sheet not received");
                e.printStackTrace();
            }
            return null;
        }

        /*
         * receive scores of other players
         */
        public int receiveRoundNo() {
            try {
                return dIn.readInt();

            } catch (IOException e) {
                System.out.println("Score sheet not received");
                e.printStackTrace();
            }
            return 0;
        }

    }

    /*
     * ---------Constructor and Main class-----------
     */

    /*
     * constructor takes the name of the player and sets the score to 0
     */
    public Player(String n) {
        name = n;
        pts =0;
    }

    public int getPoints(){
        return pts;
    }

    public static void main(String args[]) {
        Scanner myObj = new Scanner(System.in);
        System.out.print("What is your name ? ");
        String name = myObj.next();
        Player p = new Player(name);
        p.initializePlayers();
        p.connectToClient();
        System.out.println("Welcome "+name );
        p.startGame();


        myObj.close();
    }
}
