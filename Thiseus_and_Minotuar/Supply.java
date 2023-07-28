public class Supply 							// Ta efodia
{
    private int supplyId;						// Id tou efodiou
    private int x;                             	// syntetagmeni x tou plakidiou
    private int y;								// syntetagmeni y tou plakidiou
    private int supplyTileId;                   // id plakidiou tou tamblo me to efodio

    public Supply(){

    }
    public  Supply(int id,int tileId,int x,int y){		// constructor gia tyxaia arxikopoiisi
        this.supplyId=id;
        this.supplyTileId=tileId;
        this.y=y;
        this.x=x;
    }
    public Supply(Supply supply){            			// 2os constructor me orisma antikeimeno Supply
        this.supplyId=supply.getSupplyId();
        this.x=supply.getX();
        this.y=supply.getY();
        this.supplyTileId=supply.getSupplyTileId();
    }
    // oi synartiseis get:
    public int getSupplyId() {return supplyId;}

    public int getX() {return x;}

    public int getY() {return y;}

    public int getSupplyTileId() {return supplyTileId;}
    // oi synartiseis set:
    public void setSupplyId(int supplyId) {
        this.supplyId = supplyId;
    }

    public void setX(int x) {this.x = x;}

    public void setY(int y) {this.y = y;}

    public void setSupplyTileId(int supplyTileId) {
        this.supplyTileId = supplyTileId;
    }

}