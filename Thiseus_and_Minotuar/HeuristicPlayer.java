import java.util.ArrayList;
import java.util.Random;


public class HeuristicPlayer extends Player {

    private ArrayList<Integer[]> path;
    private Integer[] a1 = new Integer[4];
    private int dice1 = 0;
    private int dice3 = 0;
    private int dice5 = 0;
    private int dice7 = 0;


    public HeuristicPlayer(int id, String name, Board board, int score, int x, int y) {
        super(id, name, board, score, x, y);
        path = new ArrayList<Integer[]>();

    }


    int idToX(int id) {                                 //meatrepoun to id se syntetagmenes
        if (idToY(id) < board.getN()) {
            return x = id;
        } else {
            return x = id % board.getN();
        }
    }

    int idToY(int id) {
        return y = (int) id / board.getN();
    }

    double evaluate(int currentPos, int minotaurTileId, int dice) {

        int numberOfSupplies = board.getS();
        int N = board.getN();
        int[] tileIdOfSupplies = new int[numberOfSupplies];

        int supplyGravity = 1;
        int OD = 0;                                              //oponent distance
        int SD = 0;                                              //sypply distance
        double a = 0.7;                                          // syntelestis vrikame oti 0.6<a<0.75 sto report

        double f;                                             //h synartisi varititas

        if (score == numberOfSupplies - 1) supplyGravity = 2; // varitita tou supply ama exei meinei ena teleftaio

        for (int i = 0; i < numberOfSupplies; i++) {                          // bazoume se pinaka ta id ton supplies tou board
            tileIdOfSupplies[i] = board.getSupplies()[i].getSupplyTileId();
        }

        int tempPos;

        if (dice == 7) {                                                                                       //gia kathe dice
            if (board.getTiles()[currentPos].isLeft())
                return f = -1;                                   //an iparxei toixos arnitiki axiologisi
            for (int k = 1; k < 4; k++) {                                                                    // edw vriskw tis pragmatikes apostaseis OD kai SD
                tempPos = currentPos - k;


                if (tempPos == minotaurTileId) OD = k;

                if (SD == 0) { //se periptwsi poy uparxoun 2 sti seira
                    for (int j = 0; j < numberOfSupplies; j++) {
                        if (tempPos == tileIdOfSupplies[j]) SD = k;
                    }
                } //System.out.println("SD "+SD);
                if (board.getTiles()[tempPos].isLeft())
                    break;                                       // an yparxei toixos h axiologisi tis katefthinsi stamata edo
            }

            if (OD != 0)
                OD = 4 - OD;                                       //metatrepoyme tis pragmatikes apostaseis se syntelestes varititas
            if (SD != 0) SD = 4 - SD;

            f = SD * (1 - a) * supplyGravity - OD * a;                        // h f mas
            return f;
        }

        if (dice == 3) {
            if (board.getTiles()[currentPos].isRight()) return f = -1;
            for (int k = 1; k < 4; k++) {  // edw vriskw tis pragmatikes apostaseis OD kai SD
                tempPos = currentPos + k;


                if (tempPos == minotaurTileId) OD = k;

                if (SD == 0) { //se periptwsi poy uparxoun 2 sti seira
                    for (int j = 0; j < numberOfSupplies; j++) {
                        if (tempPos == tileIdOfSupplies[j]) SD = k;
                    }
                } //System.out.println("SD "+SD);
                if (board.getTiles()[tempPos].isRight()) break;
            }

            if (OD != 0) OD = 4 - OD;
            if (SD != 0) SD = 4 - SD;

            f = SD * (1 - a) * supplyGravity - OD * a;

            return f;
        }

        if (dice == 1) {
            if (board.getTiles()[currentPos].isUp()) return f = -1;
            for (int k = 1; k < 4; k++) {
                tempPos = currentPos + k * N;


                if (tempPos == minotaurTileId) OD = k;

                if (SD == 0) {
                    for (int j = 0; j < numberOfSupplies; j++) {
                        if (tempPos == tileIdOfSupplies[j]) SD = k;
                    }
                } //System.out.println("SD "+SD);
                if (board.getTiles()[tempPos].isUp()) break;
            }

            if (OD != 0) OD = 4 - OD;
            if (SD != 0) SD = 4 - SD;

            f = SD * (1 - a) * supplyGravity - OD * a;

            return f;
        }

        if (dice == 5) {
            if (board.getTiles()[currentPos].isDown()) return f = -1;
            for (int k = 1; k < 4; k++) {
                tempPos = currentPos - k * N;


                if (tempPos == minotaurTileId) OD = k;

                if (SD == 0) {
                    for (int j = 0; j < numberOfSupplies; j++) {
                        if (tempPos == tileIdOfSupplies[j]) SD = k;
                    }
                } //System.out.println("SD "+SD);
                if (board.getTiles()[tempPos].isDown()) break;
            }

            if (OD != 0) OD = 4 - OD;
            if (SD != 0) SD = 4 - SD;

            f = SD * (1 - a) * supplyGravity - OD * a;

            return f;
        }
        // System.out.println("ttt");
        return 0;                                                       // se periptosi pou den mpei se kapoio if , den simbainei pote

    }

    int getNextMove(int currentPos, int minotaurTileId) {

        int dice;
        double[][] possibleMoves = new double[4][2];
        int p = 0;
        for (int k = 1; k < 8; k += 2) {      //double evaluate(int currentPos,int minotaurTileId, int dice)
            double f = evaluate(currentPos, minotaurTileId, k);
            // System.out.println("FD"+k+"__"+f);
            possibleMoves[p][0] = k; //i kinisi toy paikti
            possibleMoves[p][1] = f;//i vathmologia tis
            p++;
        }
        Random d = new Random();                                          // to random auto dimiourgei pikoilia se periptosei poy iparxoun kiniseis me idia axiologisi
        int defaultMove;
        defaultMove = d.nextInt(4);
        double bestMove = possibleMoves[defaultMove][1];
        int s = defaultMove;                                                // s=anagnoristiko seiras tis kaliteris kinisis
        for (int j = 0; j < 4; j++) {
            if (j != defaultMove) {
                if (possibleMoves[j][1] > bestMove) {
                    bestMove = possibleMoves[j][1];
                    s = j;
                }
            }
        }
        dice = (int) possibleMoves[s][0];

        int N = board.getN();
        int S = board.getS();
        int tookSupply = 0;
        int OD = 0;
        int SD = 0;

        int[] tileIdOfSupplies = new int[S];
        for (int i = 0; i < S; i++) {
            tileIdOfSupplies[i] = board.getSupplies()[i].getSupplyTileId();
        }

        if (dice == 1) currentPos += N;                                      // analoga me tin kinisi poy epilexthike kinoume ton paikti
        if (dice == 3) currentPos += 1;
        if (dice == 5) currentPos -= N;
        if (dice == 7) currentPos -= 1;
        boolean wallRight=false;
        boolean wallLeft=false;
        boolean wallUp=false;
        boolean wallDown=false;

        for (int i = 0; i < S; i++) {
            if (tileIdOfSupplies[i] == currentPos) {
                tookSupply = 1;
            }
        }
        for (int i = 1; i < 4; i++) {                                         //ipologizzoume to SD
            if (SD != 0) break;
            for (int j = 0; j < S; j++) {

                //try {
                if (wallUp == false) {
                    if (board.getTiles()[currentPos + i * N - N].isUp()) {
                        wallUp = true;
                    }
                    if (tileIdOfSupplies[j] == (currentPos + i * N) && board.getTiles()[currentPos + i * N - N].isUp() == false) {
                        SD = i;
                    }
                }

                if (wallDown==false) {
                    if (board.getTiles()[currentPos - i * N +N].isDown()) {
                        wallDown = true;
                    }
                    if (tileIdOfSupplies[j] == (currentPos - i * N) && board.getTiles()[currentPos - i * N + N].isDown() == false) {
                        SD = i;
                    }
                }

                if (wallLeft == false) {
                    if (board.getTiles()[currentPos - i + 1].isLeft()) {
                        wallLeft = true;
                    }
                    if (tileIdOfSupplies[j] == (currentPos - i) && board.getTiles()[currentPos - i + 1].isLeft() == false) {
                        SD = i;
                    }
                }

                    if(wallRight==false){
                        if (board.getTiles()[currentPos+i-1].isRight()){
                            wallRight=true;
                        }
                        if (tileIdOfSupplies[j] == (currentPos + i)&& board.getTiles()[currentPos+i-1].isRight() == false){
                                SD = i;
                        }
                    }

               }



            if (minotaurTileId == (currentPos + i * N)) OD = i;         //ipologizoume to oponnent distance
            if (minotaurTileId == (currentPos - i * N)) OD = i;
            if (minotaurTileId == (currentPos + i)) OD = i;
            if (minotaurTileId == (currentPos - i)) OD = i;
        }



    a1[0]=dice;                //pername ta statistika sto path
    a1[1]=tookSupply;
    a1[2]=SD;
    a1[3]=OD;

        path.add(a1);

        return currentPos;
}

    void statistics(boolean end ){               // to end mas deixnei an vriskomaste sto telos tou paixnidiou kai ara prepei na tiposoume ola ta statistika
        int SD=path.get(0)[2].intValue();
        System.out.println("Score: " + score + "\nSupply Distance: " + SD);//PREPEI NA VALOYME TO A2


        int currentDice = path.get(0)[0].intValue();

        switch(currentDice){
            case 1:
                dice1++;
                break;
            case 3:
                dice3++;
                break;
            case 5:
                dice5++;
                break;
            case 7:
                dice7++;
                break;
        }
        if(end==true){
            System.out.println("Moved forward: "+ dice1+"\nMove right: "+dice3+"\nMoved backwards: "+dice5+"\nMoved left: "+dice7);
        }

    }


}
