public class Tile {								// Ta plakidia
    private int tileId;							// Id tou plakidiou tou tamplo
    private int x;								// syntetagmeni x tou plakidiou
    private int y;								// syntetagmeni y tou plakidiou
    private boolean up;							// Yparksi toixou stin panw pleura
    private boolean down;						// Yparksi toixou stin katw pleura
    private boolean left;						// Yparksi toixou stin aristeri pleura
    private boolean right;						// Yparksi toixou stin deksia pleura


    public Tile(int id,int x,int y){
        this.tileId=id;
        this.y=y;
        this.x=x;
        this.up=false;							// Arxikopoiisi stis boolean pou tha xreiastoun meta sta string representation
        this.down=false;
        this.left=false;
        this.right=false;
    }

    public Tile(Tile tile){						// O 2os constructor me orisma Tile
        this.tileId=tile.getTileId();
        this.x=tile.getX();
        this.y=tile.getY();
        this.up=tile.isUp();
        this.down=tile.isDown();
        this.left=tile.isLeft();
        this.right=tile.isRight();

    }
    // Synartiseis get:
    public int getTileId() {
        return tileId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDown() {           		// Exoun is kai oxi get giati einai boolean
        return down;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
    // Synartiseis set:
    public void setTileId(int tileId) {
        this.tileId = tileId;
    }

    public void setX(int x){
        this.x=x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
