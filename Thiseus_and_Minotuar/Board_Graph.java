import javax.swing.*;
import java.awt.*;

public class Board_Graph extends JPanel {

    private JLabel[][] board_labels;
    private Board board;
    private GridBagConstraints constraints=new GridBagConstraints();

    public Board_Graph(Board board) {
        this.board=board;


        board_labels = new JLabel[board.getN()*2 +1][board.getN()];
        setLayout(new GridBagLayout());
        for (int i = 0; i < 2*board.getN()+1; i++) {
            for (int j = 0; j < board.getN(); j++) {
                board_labels[i][j] = new JLabel("");
                addGB(board_labels[i][j], j, i);
            }
        }
    }

    void addGB(Component component, int x, int y){
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }

    public void setBoard_labels(JLabel[][] board_labels) {
        this.board_labels = board_labels;
    }

    public JLabel[][] getBoard_labels() {
        return board_labels;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


}
