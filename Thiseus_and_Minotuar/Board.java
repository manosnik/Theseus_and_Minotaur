import java.util.Random;						// eisagwgi tis entolis random

public class Board {							// To tamplo tou paixnidiou
    int N;										// O arithmos twn diastasewn
    int S;										// O arithmos twn efodiwn
    int W;										// O arithmos twn teixwn
    Tile[] tiles;								// Pinakas me antikeimena typou Tile
    Supply[] supplies;							// Pinakas me antikeimeta typou Supply

    public Board() {							// Kenos constructor

    }

    public Board(int n, int s, int w) {			// 2os constructor me orismata:
        this.N = n;								// Diastaseis
        this.S = s;								// Efodia
        this.W = w;								// Teixh
        int j = (N * N);                 		// Megethos pinaka tile n*n-1
        this.tiles = new Tile[j];
        this.supplies = new Supply[S];
    }

    public Board(Board board) {					// never used warning https://stackoverflow.com/questions/42577259/constructor-is-never-used
        this.N = board.getN();
        this.S = board.getS();
        this.W = board.getW();
        int j = N * N;
        this.tiles = new Tile[j];
        for (int i = 0; i < j; i++) {			// https://stackoverflow.com/questions/8199945/java-how-to-make-a-copy-of-an-array-of-object
            // Tile temp=new Tile(board.tiles[i]);
            this.tiles[i] = new Tile(board.tiles[i]);
        }
        this.supplies = new Supply[S];
        for (int k = 0; k < S; k++) {
            this.supplies[k] = new Supply(board.supplies[k]);
        }
    }
    // Synartiseis get:
    public int getN() {
        return N;
    }

    public int getS() {
        return S;
    }

    public int getW() {
        return W;
    }
    public Tile[] getTiles() {
        return tiles;              //https://www.codejava.net/coding/java-getter-and-setter-tutorial-from-basics-to-best-practices
    }

    public Supply[] getSupplies() {
        return supplies;
    }

    // Synartiseis set:
    public void setN(int n) {
        this.N = n;
    }

    public void setS(int s) {
        this.S = s;
    }

    public void setW(int w) {
        this.W = w;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    public void setSupplies(Supply[] supplies) {
        this.supplies = supplies;
    }

    public int boolToInt(boolean b) {			// An yparxei teixos epistrefei 1, an oxi 0
        return b ? 1 : 0;
    }

    public int sumOfBools(boolean b1, boolean b2, boolean b3, boolean b4) {
        int a = boolToInt(b1) + boolToInt(b2) + boolToInt(b3) + boolToInt(b4);
        return a;
    }

    public void createTile() {					// Arxikopoiisi pinaka Tile me tyxaio tropo
        Random rb = new Random();

        for (int i = 0; i < N * N; i++) {
            int x;
            if (i < N) {
                x = i;
            } else {
                x = i % N;
            }
            int y = (int) i / N;
            tiles[i] = new Tile(i, x, y);
            if (tiles[i].getX() == 0) {
                tiles[i].setLeft(true);
            }
            if (tiles[i].getX() == (N - 1)) {
                tiles[i].setRight(true);
            }
            if (tiles[i].getY() == 0) {
                tiles[i].setDown(true);
            }
            if (tiles[i].getY() == (N - 1)) {
                tiles[i].setUp(true);
            }
            if (tiles[i].getX() > 0) {
                if (tiles[i - 1].isRight()) {
                    tiles[i].setLeft(true);
                }
            }
            if (tiles[i].getY() > 0) {
                if (tiles[i - N].isUp()) {
                    tiles[i].setDown(true);
                }
            }

            if (sumOfBools(tiles[i].isDown(), tiles[i].isLeft(), tiles[i].isUp(), tiles[i].isRight()) == 0) {   //ara einai ola false
                tiles[i].setUp(rb.nextBoolean());
                tiles[i].setRight(rb.nextBoolean());
            }

            if (sumOfBools(tiles[i].isDown(), tiles[i].isLeft(), tiles[i].isUp(), tiles[i].isRight()) == 1) {
                if (tiles[i].isDown()) {
                    tiles[i].setRight(rb.nextBoolean());

                    if (sumOfBools(tiles[i].isDown(), tiles[i].isLeft(), tiles[i].isUp(), tiles[i].isRight()) < 2) {
                        tiles[i].setUp(rb.nextBoolean());
                    }
                } else if (tiles[i].isUp()) {
                    tiles[i].setRight(rb.nextBoolean());
                } else if (tiles[i].isLeft()) {
                    tiles[i].setRight(rb.nextBoolean());

                    if (sumOfBools(tiles[i].isDown(), tiles[i].isLeft(), tiles[i].isUp(), tiles[i].isRight()) < 2) {
                        tiles[i].setUp(rb.nextBoolean());
                    }
                } else if (tiles[i].isRight()) {
                    tiles[i].setUp(rb.nextBoolean());

                }
            }
            if (tiles[i].getX()==0&&tiles[i].getY()==(N-1)){
                if ((sumOfBools(tiles[i].isDown(), tiles[i].isLeft(), tiles[i].isUp(), tiles[i].isRight()) > 2) && (tiles[i].isDown())) {
                    tiles[i].setDown(false);
                    if (i > N) {
                        tiles[i - N].setUp(false);
                    }
                }
                if ((sumOfBools(tiles[i].isDown(), tiles[i].isLeft(), tiles[i].isUp(), tiles[i].isRight()) > 2) && (tiles[i].isLeft())) {
                    tiles[i].setLeft(false);
                    if (tiles[i].getX() > 0) {
                        tiles[i - 1].setRight(false);
                    }
                }
            }
            else {
                if ((sumOfBools(tiles[i].isDown(), tiles[i].isLeft(), tiles[i].isUp(), tiles[i].isRight()) > 2) && (tiles[i].isLeft())) {
                    tiles[i].setLeft(false);
                    if (tiles[i].getX() > 0) {
                        tiles[i - 1].setRight(false);
                    }

                }
                if ((sumOfBools(tiles[i].isDown(), tiles[i].isLeft(), tiles[i].isUp(), tiles[i].isRight()) > 2) && (tiles[i].isDown())) {
                    tiles[i].setDown(false);
                    if (i > N) {
                        tiles[i - N].setUp(false);
                    }
                }
            }
        }

    }

    public void createSupply() {				// Arxikopoiisi pinaka Supply me tyxaio tropo
        Random ri = new Random();
        boolean duplicate = false;
        int j;
        for (int i = 0; i < S; i++) {
            supplies[i] = new Supply();
            supplies[i].setSupplyId(i + 1);
            do {
                duplicate = false;
                j = ri.nextInt(N * N - 2)+1;
                for (int k = 0; k < i; k++) {
                    if (supplies[k].getSupplyTileId() == j) {
                        duplicate = true;
                    }
                }
            } while (duplicate);
            supplies[i].setSupplyTileId(j);
            int x;
            if (j < N) {
                x = j;
            } else {
                x = j % N;
            }
            int y = (int) j / N;
            supplies[i].setX(x);
            supplies[i].setY(y);
        }

    }

    public void createBoard() {					// Dhmiourgei to tamplo tou paixnidiou
        createTile();
        createSupply();
    }

    public String[][] getStringRepresentation(int thiseusTile, int minotaurTIle) {
        String[][] stringRepresentation = new String[2 * N + 1][N];
        for (int i=0;i<N*N;i++){
            if (tiles[i].getX()!=N-1) {
                if (tiles[i].isDown()) {
                    stringRepresentation[tiles[i].getY() * 2][tiles[i].getX()] = "+---";
                } else {
                    stringRepresentation[tiles[i].getY() * 2][tiles[i].getX()] = "+   ";
                }
                if (tiles[i].isLeft() && (tiles[i].getTileId() == minotaurTIle)) {
                    stringRepresentation[tiles[i].getY() * 2 + 1][tiles[i].getX()] = "|  M";

                } else if (!tiles[i].isLeft() && (tiles[i].getTileId() == minotaurTIle)) {
                    stringRepresentation[tiles[i].getY() * 2 + 1][tiles[i].getX()] = "   M";

                }
                else if (tiles[i].isLeft() && (tiles[i].getTileId() == thiseusTile)) {
                    stringRepresentation[tiles[i].getY() * 2 + 1][tiles[i].getX()] = "|  T";

                } else if (!tiles[i].isLeft() && (tiles[i].getTileId() == thiseusTile)) {
                    stringRepresentation[tiles[i].getY() * 2 + 1][tiles[i].getX()] = "   T";

                }  else if (tiles[i].isLeft()) {
                    stringRepresentation[tiles[i].getY() * 2 + 1][tiles[i].getX()] = "|   ";
                } else if (!tiles[i].isLeft()) {
                    stringRepresentation[tiles[i].getY() * 2 + 1][tiles[i].getX()] = "    ";
                }
                for (int k = 0; k < S; k++) {
                    if (supplies[k].getSupplyTileId() == tiles[i].getTileId() && tiles[i].getTileId() != thiseusTile) {
                        if (tiles[i].isLeft()) {
                            stringRepresentation[tiles[i].getY() * 2 + 1][tiles[i].getX()] = "| S" + supplies[k].getSupplyId();
                        } else if (!tiles[i].isLeft()) {
                            stringRepresentation[tiles[i].getY() * 2 + 1][tiles[i].getX()] = "  S" + supplies[k].getSupplyId();
                        }
                    }
                }

            }else {
                if (tiles[i].isDown()) {
                    stringRepresentation[tiles[i].getY() * 2][tiles[i].getX()] = "+---+";
                } else {
                    stringRepresentation[tiles[i].getY() * 2][tiles[i].getX()] = "+   +";
                }
                if (tiles[i].isLeft() && (tiles[i].getTileId() == minotaurTIle)) {
                    stringRepresentation[tiles[i].getY() * 2 + 1][tiles[i].getX()] = "|  M|";

                } else if (!tiles[i].isLeft() && (tiles[i].getTileId() == minotaurTIle)) {
                    stringRepresentation[tiles[i].getY() * 2 + 1][tiles[i].getX()] = "   M|";

                }
                else if (tiles[i].isLeft() && (tiles[i].getTileId() == thiseusTile)) {
                    stringRepresentation[tiles[i].getY() * 2 + 1][tiles[i].getX()] = "|  T|";

                } else if (!tiles[i].isLeft() && (tiles[i].getTileId() == thiseusTile)) {
                    stringRepresentation[tiles[i].getY() * 2 + 1][tiles[i].getX()] = "   T|";

                }  else if (tiles[i].isLeft()) {
                    stringRepresentation[tiles[i].getY() * 2 + 1][tiles[i].getX()] = "|   |";
                } else if (!tiles[i].isLeft()) {
                    stringRepresentation[tiles[i].getY() * 2 + 1][tiles[i].getX()] = "    |";
                }
                for (int k = 0; k < S; k++) {
                    if (supplies[k].getSupplyTileId() == tiles[i].getTileId() && tiles[i].getTileId() != thiseusTile) {
                        if (tiles[i].isLeft()) {
                            stringRepresentation[tiles[i].getY() * 2 + 1][tiles[i].getX()] = "| S" + supplies[k].getSupplyId()+"|";
                        } else if (!tiles[i].isLeft()) {
                            stringRepresentation[tiles[i].getY() * 2 + 1][tiles[i].getX()] = "  S" + supplies[k].getSupplyId()+"|";
                        }
                    }
                }

            }
        }
        for(int h=0;h<N;h++) {
            if (h != N - 1) {
                stringRepresentation[2 * N][h] = "+---";
            } else {
                stringRepresentation[2 * N][h] = "+---+";
            }
        }


        for(int y=2*N;y>=0;y--){
            for(int x=0;x<N;x++){
                System.out.print(stringRepresentation[y][x]);

            }
            System.out.print("\n");
        }

        return stringRepresentation;

    }
}

