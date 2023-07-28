
import java.util.ArrayList;
import java.util.Random;

public class MinMaxPlayer extends Player{
    private ArrayList<Integer[]> path;
    private Integer[] a1=new Integer[4];
    private int dice1 = 0;
    private int dice3 = 0;
    private int dice5 = 0;
    private int dice7 = 0;

    public MinMaxPlayer(int id, String name, Board board, int score, int x, int y) {
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

    double evaluate(int currentPos, int minotaurTileId, int dice,Board board) {

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

    double evaluateMinotaur(int currentPos, int theseusTileId, int dice, Board tempBoard) {

       // Board tempBoard = new Board(board);

        int numberOfSupplies = tempBoard.getS();
        int N = tempBoard.getN();
        int[] tileIdOfSupplies = new int[numberOfSupplies];

        int supplyGravity = 1;
        int OD = 0;                                              //oponent distance
        int SD = 0;                                              //sypply distance
        double a = 0.8;                                          // syntelestis: gia ton Minotaur theloume 0.75 < a (<1)

        double f;                                             //h synartisi varititas

        if (score == numberOfSupplies - 1) supplyGravity = 2; // varitita tou supply ama exei meinei ena teleftaio

        for (int i = 0; i < numberOfSupplies; i++) {                          // bazoume se pinaka ta id ton supplies tou board
            tileIdOfSupplies[i] = tempBoard.getSupplies()[i].getSupplyTileId();
        }

        int tempPos;

        if (dice == 7) {                                                                                       //gia kathe dice
            if (tempBoard.getTiles()[currentPos].isLeft())
                return f = -1;                                   //an iparxei toixos arnitiki axiologisi
            for (int k = 1; k < 4; k++) {                                                                    // edw vriskw tis pragmatikes apostaseis OD kai SD
                tempPos = currentPos - k;


                if (tempPos == theseusTileId) OD = k;

                if (SD == 0) { //se periptwsi poy uparxoun 2 sti seira
                    for (int j = 0; j < numberOfSupplies; j++) {
                        if (tempPos == tileIdOfSupplies[j]) SD = k;
                    }
                } //System.out.println("SD "+SD);
                if (tempBoard.getTiles()[tempPos].isLeft())
                    break;                                       // an yparxei toixos h axiologisi tis katefthinsi stamata edo
            }

            if (OD != 0)
                OD = 4 - OD;                                       //metatrepoyme tis pragmatikes apostaseis se syntelestes varititas
            if (SD != 0) SD = 4 - SD;

            f = SD * (1 - a) + OD * a;                        // h f mas
            return f;
        }

        if (dice == 3) {
            if (tempBoard.getTiles()[currentPos].isRight()) return f = -1;
            for (int k = 1; k < 4; k++) {  // edw vriskw tis pragmatikes apostaseis OD kai SD
                tempPos = currentPos + k;


                if (tempPos == theseusTileId) OD = k;

                if (SD == 0) { //se periptwsi poy uparxoun 2 sti seira
                    for (int j = 0; j < numberOfSupplies; j++) {
                        if (tempPos == tileIdOfSupplies[j]) SD = k;
                    }
                } //System.out.println("SD "+SD);
                if (tempBoard.getTiles()[tempPos].isRight()) break;
            }

            if (OD != 0) OD = 4 - OD;
            if (SD != 0) SD = 4 - SD;

            f = SD * (1 - a) + OD * a;

            return f;
        }

        if (dice == 1) {
            if (tempBoard.getTiles()[currentPos].isUp()) return f = -1;
            for (int k = 1; k < 4; k++) {
                tempPos = currentPos + k * N;


                if (tempPos == theseusTileId) OD = k;

                if (SD == 0) {
                    for (int j = 0; j < numberOfSupplies; j++) {
                        if (tempPos == tileIdOfSupplies[j]) SD = k;
                    }
                } //System.out.println("SD "+SD);
                if (tempBoard.getTiles()[tempPos].isUp()) break;
            }

            if (OD != 0) OD = 4 - OD;
            if (SD != 0) SD = 4 - SD;

            f = SD * (1 - a) + OD * a;

            return f;
        }

        if (dice == 5) {
            if (tempBoard.getTiles()[currentPos].isDown()) return f = -1;
            for (int k = 1; k < 4; k++) {
                tempPos = currentPos - k * N;


                if (tempPos == theseusTileId) OD = k;

                if (SD == 0) {
                    for (int j = 0; j < numberOfSupplies; j++) {
                        if (tempPos == tileIdOfSupplies[j]) SD = k;
                    }
                } //System.out.println("SD "+SD);
                if (tempBoard.getTiles()[tempPos].isDown()) break;
            }

            if (OD != 0) OD = 4 - OD;
            if (SD != 0) SD = 4 - SD;

            f = SD * (1 - a) + OD * a;

            return f;
        }
        // System.out.println("ttt");
        return 0;                                                       // se periptosi pou den mpei se kapoio if , den simbainei pote

    }

    int chooseMinMaxMove(Node root){
        double min;
        double max;
        int default_;

        double[][] mins=new double[root.getChildren().size()][2];

        for(int i=0;i<root.getChildren().size(); i++){

            mins[i][0]=root.getChildren().get(i).getNodeMove()[2];  // edo thelei na mpenei to dice tis kinisis
            min=root.getChildren().get(i).getChildren().get(0).getNodeEvaluation();

            for(int j=1;j<root.getChildren().get(i).getChildren().size();j++){

                if(root.getChildren().get(i).getChildren().get(j).getNodeEvaluation()<min){
                    min=root.getChildren().get(i).getChildren().get(j).getNodeEvaluation();
                }
            }
            mins[i][1]=min;

        }
        Random r=new Random();     // GIA NA MIN KANEI SINEXEIA THN IDIA KINISI OS PROEPILOGI
        default_= r.nextInt(root.getChildren().size());
        max=mins[default_][1];
        int k_max;      //DEIKTHS THESIS
        k_max=default_;
        for(int k=1;k<root.getChildren().size();k++){
            if(mins[k][1]>max){
                max=mins[k][1];
                k_max=k;
            }
        }
        return (int)mins[k_max][0];

    }


    int[] getNextMove(int currentPos,int opponentCurrentPos){
        int[] returnArray = new int[3];

        int N = board.getN();
        int S = board.getS();
        int tookSupply = 0;
        int OD = 0;
        int SD = 0;

        int[] tileIdOfSupplies = new int[S];
        for (int i = 0; i < S; i++) {
            tileIdOfSupplies[i] = board.getSupplies()[i].getSupplyTileId();
        }
        Node root=new Node();

        root.setNodeBoard(board);

        createMySubTree(currentPos, opponentCurrentPos, root, 1);

        int move = chooseMinMaxMove(root);

        if(move ==1) currentPos += N;
        else if(move == 3) currentPos += 1;
        else if(move == 5) currentPos -= N;
        else if(move == 7) currentPos -= 1;

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

            if (opponentCurrentPos == (currentPos + i * N)) OD = i;         //ipologizoume to oponnent distance
            if (opponentCurrentPos == (currentPos - i * N)) OD = i;
            if (opponentCurrentPos== (currentPos + i)) OD = i;
            if (opponentCurrentPos == (currentPos - i)) OD = i;
        }

        returnArray[0]=currentPos;

        if (currentPos < N ) {
            x = currentPos;
        } else {
            x = currentPos % N;
        }
        y = (int) currentPos / N;
        returnArray[1]=x;
        returnArray[2]=y;


        a1[0]=move;                //pername ta statistika sto path
        a1[1]=tookSupply;
        a1[2]=SD;
        a1[3]=OD;
        path.add(a1);

        return returnArray;
    }

    void createMySubTree(int currentPos,int opponentCurrentPos,Node root,int depth){
        int[] availableDices={1,3,5,7};

        if(root.getNodeBoard().getTiles()[currentPos].isUp()) availableDices[0]=0;
        if(root.getNodeBoard().getTiles()[currentPos].isRight()) availableDices[1]=0;
        if(root.getNodeBoard().getTiles()[currentPos].isDown()) availableDices[2]=0;
        if(root.getNodeBoard().getTiles()[currentPos].isLeft()) availableDices[3]=0;

        int tempPosition=currentPos;
        for(int i=0;i<4;i++){
            if (availableDices[i]!=0){
                Board cloneBoard=new Board(root.getNodeBoard());
                if (availableDices[i]== 1) {
                   tempPosition=currentPos + cloneBoard.getN();
                }

                else if (availableDices[i] == 3) {
                    tempPosition=currentPos+ 1;
                }
                else if(availableDices[i]== 5) {
                    tempPosition=currentPos - cloneBoard.getN();
                }else if (availableDices[i] == 7) {
                    tempPosition=currentPos - 1;
                }


                Node child=new Node(root,depth,cloneBoard,0);
                int[] a={idToX(tempPosition),idToY(tempPosition),availableDices[i]};
                child.setNodeMove(a);
                child.setNodeEvaluation(evaluate(currentPos,opponentCurrentPos,availableDices[i],cloneBoard));
                root.getChildren().add(child);
                createOpponentSubtree(tempPosition,opponentCurrentPos,child,depth+1,evaluate(currentPos,opponentCurrentPos,availableDices[i],cloneBoard));
            }
        }


    }

    void createOpponentSubtree(int currentPos,int opponentCurrentPos,Node parent,int depth,double parentEval){
        int[] availableDices={1,3,5,7};
        if(parent.getNodeBoard().getTiles()[opponentCurrentPos].isUp()) availableDices[0]=0;
        if(parent.getNodeBoard().getTiles()[opponentCurrentPos].isRight()) availableDices[1]=0;
        if(parent.getNodeBoard().getTiles()[opponentCurrentPos].isDown()) availableDices[2]=0;
        if(parent.getNodeBoard().getTiles()[opponentCurrentPos].isLeft()) availableDices[3]=0;

        int tempPosition=opponentCurrentPos;
        for(int i=0;i<4;i++){
            if (availableDices[i]!=0){
                Board cloneBoard=new Board(parent.getNodeBoard());
                if (availableDices[i]== 1) {
                    tempPosition=currentPos + cloneBoard.getN();
                }

                else if (availableDices[i] == 3) {
                    tempPosition=currentPos+ 1;
                }
                else if(availableDices[i]== 5) {
                    tempPosition=currentPos - cloneBoard.getN();
                }else if (availableDices[i] == 7) {
                    tempPosition=currentPos - 1;
                }

                Node child=new Node(parent,depth,cloneBoard,0);
                int[] a={idToX(tempPosition),idToY(tempPosition),availableDices[i]};
                child.setNodeMove(a);
                child.setNodeEvaluation(parentEval-evaluateMinotaur(opponentCurrentPos,currentPos,availableDices[i],cloneBoard));
                parent.getChildren().add(child);
            }
        }

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
