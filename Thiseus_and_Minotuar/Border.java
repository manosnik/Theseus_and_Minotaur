import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Border extends JPanel {
    Board board;

    private GridBagConstraints constraints=new GridBagConstraints();
    private JButton button_play;
    private JButton button_quit;
    private JButton button_generateBoard;
    private JLabel round;
    private JTextArea theseus_score;
    private JTextArea minotaur_score;
    private JComboBox theseus_kind;
    private JComboBox minotaur_kind;

    private Board_Graph board_graph;

    private JLabel result;

  /*  private JLabel[][] board_labels;
    private JPanel board_graph;
*/


    public Border(Board board){
        button_play=new JButton("Play");
        button_quit=new JButton("Quit");
        button_generateBoard=new JButton("Generate Board");
        round=new JLabel("Round:0");
        theseus_score=new JTextArea("Theseus\nMove Score:0\nTotal Score:0");
        minotaur_score=new JTextArea("Minotaur\nMove Score:\nTotal Score:");
        theseus_score.setEditable(false);
        theseus_score.setBackground(button_play.getBackground());
        minotaur_score.setEditable(false);
        minotaur_score.setBackground(button_play.getBackground());
        theseus_kind=new JComboBox();
        theseus_kind.addItem("");
        theseus_kind.addItem("Random Player");
        theseus_kind.addItem("Heuristic Player");
        theseus_kind.addItem("MinMax Player");
        minotaur_kind=new JComboBox();
        minotaur_kind.addItem("Random Player");

        result=new JLabel("Choose theseus kind ");

/*
        board_graph=new JPanel();
        board_labels=new JLabel[board.getN()][board.getN()];
        board_graph.setLayout(new GridLayout(board.getN(),board.getN()));
        for(int i=0; i<board.getN();i++){
            for(int j=0;j<board.getN();j++){
                board_labels[i][j]=new JLabel("");
                board_graph.add(board_labels[i][j],i,j);
            }
        }

*/

        board_graph=new Board_Graph(board);

        setLayout(new GridBagLayout());
        constraints.weightx = 1.0;
        constraints.weighty =1.0;
        addGB(button_play, 2,6);
        addGB(button_quit, 3, 6);
        addGB( button_generateBoard,1,6);
        addGB(round,0,0);
        constraints.gridwidth=3;
        addGB(result,1,0);
        constraints.gridwidth=1;
        addGB(theseus_score,0,4);
        addGB(theseus_kind,0,5);
        addGB(minotaur_score,4,4);
        addGB(minotaur_kind,4,5);
        constraints.gridwidth = 3;
        constraints.gridheight = 3;
        addGB(board_graph,1,1);
        constraints.gridheight=1;
        constraints.gridwidth = 1;


    }

    void addGB(Component component, int x, int y){
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

  /*  public JLabel[][] getBoard_labels() {
        return board_labels;
    }

    public void setBoard_labels(JLabel[][] board_labels) {
        this.board_labels = board_labels;
    }
*/
    public void setRound(JLabel round) {
        this.round = round;
    }

    public JButton getButton_generateBoard() {
        return button_generateBoard;
    }

    public JButton getButton_play() {
        return button_play;
    }

    public JButton getButton_quit() {
        return button_quit;
    }

    public JComboBox getMinotaur_kind() {
        return minotaur_kind;
    }

    public JLabel getRound() {
        return round;
    }

   public Board_Graph getBoard_graph() {
        return board_graph;
    }

    public JTextArea getMinotaur_score() {
        return minotaur_score;
    }

    public JTextArea getTheseus_score() {
        return theseus_score;
    }

   /* public void setBoard_graph(JPanel board_graph) {
        this.board_graph = board_graph;
    }*/

    public void setButton_generateBoard(JButton button_generateBoard) {
        this.button_generateBoard = button_generateBoard;
    }

    public void setButton_play(JButton button_play) {
        this.button_play = button_play;
    }

    public void setButton_quit(JButton button_quit) {
        this.button_quit = button_quit;
    }

    public void setMinotaur_kind(JComboBox minotaur_kind) {
        this.minotaur_kind = minotaur_kind;
    }

    public void setTheseus_score(JTextArea theseus_score) {
        this.theseus_score = theseus_score;
    }

    public void setTheseus_kind(JComboBox theseus_kind) {
        this.theseus_kind = theseus_kind;
    }

    public void setMinotaur_score(JTextArea minotaur_score) {
        this.minotaur_score = minotaur_score;
    }

    public JComboBox getTheseus_kind() {
        return theseus_kind;
    }

    public JLabel getResult() {
        return result;
    }

    public void setResult(JLabel result) {
        this.result = result;
    }
}
