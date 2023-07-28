import java.util.ArrayList;

public class Node {
    private Node parent;
    private ArrayList<Node> children;
    private int nodeDepth;
    private int [] nodeMove;
    private Board nodeBoard;
    private double nodeEvaluation;

    public Node(){
        children=new ArrayList<Node>();
        nodeMove=new int[3];
    }

    public Node(Node parent, int nodeDepth,Board nodeBoard,double nodeEvaluation){
        this.parent=parent;
        this.nodeDepth=nodeDepth;
        this.nodeBoard=nodeBoard;
        this.nodeEvaluation=nodeEvaluation;

        children=new ArrayList<Node>();
        nodeMove=new int[3];

    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public Board getNodeBoard() {
        return nodeBoard;
    }

    public double getNodeEvaluation() {
        return nodeEvaluation;
    }

    public int getNodeDepth() {
        return nodeDepth;
    }

    public int[] getNodeMove() {
        return nodeMove;
    }

    public Node getParent() {
        return parent;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public void setNodeBoard(Board nodeBoard) {
        this.nodeBoard = nodeBoard;
    }

    public void setNodeDepth(int nodeDepth) {
        this.nodeDepth = nodeDepth;
    }

    public void setNodeEvaluation(double nodeEvaluation) {
        this.nodeEvaluation = nodeEvaluation;
    }

    public void setNodeMove(int[] nodeMove) {
        this.nodeMove = nodeMove;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
