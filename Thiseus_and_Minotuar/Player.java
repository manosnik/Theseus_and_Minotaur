import java.util.Random;

public class Player {							// O paixtis
    protected int playerId;						// O kwdikos tou paixti
    protected String name;						// To onoma tou paixti
    protected Board board;						// To tamplo tou paixnidiou
    protected int score;							// To skor tou paixti
    protected int x;								// Tetmimeni tou plakidiou opou vrisketai o paixtis
    protected int y;								// Tetagmeni tou plakidiou opou vrisketai o paixtis

    public Player(){}

    public Player(int id, String name, Board board, int score, int x, int y){
        this.playerId = id;
        this.name = name;
        this.board = board;
        this.score = score;
        this.x = x;
        this.y = y;
    }
    // Oi synartiseis set:
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBoard(Board board) {
        this.board = new Board(board);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    // Oi synartiseis get:

    public int getPlayerId(){ return this.playerId; }
    public String getName(){ return this.name; }
    public Board getBoard(){ return this.board; }
    public int getScore(){ return this.score; }
    public int getX(){ return this.x; }
    public int getY(){ return this.y; }


    int[] move(int id, boolean isHeuristic) {
        Random rand = new Random();
        int[] returnArray = new int[4];
        if(isHeuristic == false){

            Tile[] temp = new Tile[board.getN() * board.getN()];
            temp = board.getTiles();

            int[] dice = {1, 3, 5, 7};


            if (temp[id].isUp()) dice[0] = 0; 		// Toixos panw
            if (temp[id].isRight()) dice[1] = 0; 	// Toixos dexia
            if (temp[id].isDown()) dice[2] = 0;  	// Toixos katw
            if (temp[id].isLeft()) dice[3] = 0; 	// Toixos aristera

            int randomNumber = rand.nextInt(4);
            int play = dice[randomNumber];

            if (play == 0) {
                System.out.println("Player " + this.playerId + " can't move!");

            } else if (play == 1) {					// Kinisi pros ta panw
                id += board.getN();					// Metavoli Id paixti
                System.out.println("Player " + this.playerId + " moves up."); // Ektipwsi kinisis gia thn klasi game
            }

            else if (play == 3) {					// Kinisi pros ta deksia
                id += 1;							// Metavoli Id paixti
                System.out.println("Player " + this.playerId + " moves right.");
            }
            else if(play == 5) {					// Kinisi pros ta katw
                id -= board.getN();					// Metavoli Id paixti
                System.out.println("Player " + this.playerId + " moves down.");
            }else if (play == 7) {					// Kinisi pros ta aristera
                id -= 1;							// Metavoli Id paixti
                System.out.println("Player " + this.playerId + " moves left.");

            }
        }

        returnArray[0]=id;

        if (id < board.getN()) {
            x = id;
        } else {
            x = id % board.getN();
        }
        y = (int) id / board.getN();
        returnArray[1]=x;
        returnArray[2]=y;

        Supply[] temp2=new Supply[board.getS()];
        temp2=board.getSupplies();
        for(int i=0;i< board.getS();i++){
            if(id==temp2[i].getSupplyTileId()&& playerId==1){
                this.score += 1;
                returnArray[3]=temp2[i].getSupplyId();
                temp2[i].setSupplyTileId(-1);
                temp2[i].setY(-1);
                temp2[i].setX(-1);
                System.out.println("Player " + playerId + " picked a supply.");
                board.setSupplies(temp2);
            }

        }
        return returnArray;
    }
}
