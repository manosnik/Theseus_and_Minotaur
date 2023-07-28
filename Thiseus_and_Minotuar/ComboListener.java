import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboListener implements ItemListener {

    private JComboBox comboBox;
    private Game game;
    private Border border;
    private Board board;
    private Player player2;


    public ComboListener(JComboBox comboBox,Game game,Border border,Board board,Player player2){
        this.comboBox=comboBox;
        comboBox.addItemListener(this);
        this.game=game;
        this.border=border;
        this.board=board;
        this.player2=player2;

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getItem().equals("Random Player")){
            game.setPlayer_kind(1);
            border.getResult().setText("");

            Player player1=new Player(1,"Thiseus",board,0,0,0);

            int thiseusTileId = board.getN() * player1.getY() + player1.getX();
            int minotaurTileId = board.getN() * player2.getY() + player2.getX();

            board.getStringRepresentation(thiseusTileId, minotaurTileId);
            System.out.println("____________________________________________________________________");

            ButtonListener buttonListener2=new ButtonListener(border, board.getStringRepresentation(thiseusTileId,minotaurTileId),game,player1,player2,board,thiseusTileId,minotaurTileId);

        }
        if(e.getItem().equals("Heuristic Player")){
            game.setPlayer_kind(2);
            border.getResult().setText("");

            HeuristicPlayer player1=new HeuristicPlayer(1,"Thiseus",board,0,0,0);

            int thiseusTileId = board.getN() * player1.getY() + player1.getX();
            int minotaurTileId = board.getN() * player2.getY() + player2.getX();

            board.getStringRepresentation(thiseusTileId, minotaurTileId);
            System.out.println("____________________________________________________________________");

            ButtonListener buttonListener2=new ButtonListener(border, board.getStringRepresentation(thiseusTileId,minotaurTileId),game,player1,player2,board,thiseusTileId,minotaurTileId);

        }
        if(e.getItem().equals("MinMax Player")){
            game.setPlayer_kind(3);
            border.getResult().setText("");

            MinMaxPlayer player1 = new MinMaxPlayer(1, "Thiseus", board, 0, 0, 0);

            int thiseusTileId = board.getN() * player1.getY() + player1.getX();
            int minotaurTileId = board.getN() * player2.getY() + player2.getX();

            board.getStringRepresentation(thiseusTileId, minotaurTileId);
            System.out.println("____________________________________________________________________");

            ButtonListener buttonListener2=new ButtonListener(border, board.getStringRepresentation(thiseusTileId,minotaurTileId),game,player1,player2,board,thiseusTileId,minotaurTileId);

        }
    }
}
