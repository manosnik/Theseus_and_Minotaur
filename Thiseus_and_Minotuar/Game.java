import javax.swing.*;
import java.util.Random;

public class Game {								// To paixnidi

    private int round;
    private int player_kind;// O trexwn sgyros

    public Game() {
        this.round = 0;
        this.player_kind=0;
    }						// Arxikopoiisi gyrou
                                                // Synartiseis get k' set
    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getPlayer_kind() {
        return player_kind;
    }

    public void setPlayer_kind(int player_kind) {
        this.player_kind = player_kind;
    }

    public static void main(String[] args) {             // Ekkinisi paixnidiou

        Game game = new Game();
        Random rand = new Random();

        int walls = rand.nextInt(338);			 // Synolika teixh: (N*N*3+1)/2
        int dimension = 15;						         // Diastasi tamplou
        int supplies = 4;						         // Eisagwgi efodiwn

        Board board = new Board(dimension, supplies, walls);
        board.createBoard();					         // Dhmiourgia tamplou

        JFrame frame=new JFrame("Theseus and Minotaur");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1250,1750);
        frame.setLocation(20,20);
        Border border=new Border(board);
        frame.setContentPane(border);
        frame.setVisible(true);

        Player player2 = new Player(2, "Minotaur", board, 0, dimension/2, dimension/2);

        ComboListener comboListener=new ComboListener(border.getTheseus_kind(), game,border,board,player2);

    }
}