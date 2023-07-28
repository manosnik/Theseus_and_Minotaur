import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonListener implements ActionListener {
       Border border;
       String[][] board_str;
       int round;
       MinMaxPlayer player;
       HeuristicPlayer heuristicPlayer;
       Player randomPlayer;
       Player minotaur;
       Board board;
       int thiseusTileId, minotaurTileId;
       boolean minotaur_hide;

       Game game;

       boolean buttonClicked;

    public ButtonListener(Border border,String[][] board_str,Game game){
        this.border=border;
        border.getButton_play().addActionListener(this);
        border.getButton_quit().addActionListener(this);
        border.getButton_generateBoard().addActionListener(this);
        this.board_str=board_str;
        this.game=game;

        buttonClicked=false;
    }

    public ButtonListener(Border border,String[][] board_str,Game game,MinMaxPlayer player,Player minotaur,Board board,int thiseusTileId,int minotaurTileId){
        this.border=border;
        border.getButton_play().addActionListener(this);
        border.getButton_quit().addActionListener(this);
        border.getButton_generateBoard().addActionListener(this);
        this.board_str=board_str;
        this.game=game;

        buttonClicked=false;
        this.player=player;
        this.minotaur=minotaur;
        this.thiseusTileId=thiseusTileId;
        this.minotaurTileId=minotaurTileId;
        this.board=board;

    }
    public ButtonListener(Border border,String[][] board_str,Game game,HeuristicPlayer heuristicPlayer,Player minotaur,Board board,int thiseusTileId,int minotaurTileId){
        this.border=border;
        border.getButton_play().addActionListener(this);
        border.getButton_quit().addActionListener(this);
        border.getButton_generateBoard().addActionListener(this);
        this.board_str=board_str;
        this.game=game;

        buttonClicked=false;
        this.heuristicPlayer=heuristicPlayer;
        this.minotaur=minotaur;
        this.thiseusTileId=thiseusTileId;
        this.minotaurTileId=minotaurTileId;
        this.board=board;

    }

    public ButtonListener(Border border,String[][] board_str,Game game,Player randomPlayer,Player minotaur,Board board,int thiseusTileId,int minotaurTileId){
        this.border=border;
        border.getButton_play().addActionListener(this);
        border.getButton_quit().addActionListener(this);
        border.getButton_generateBoard().addActionListener(this);
        this.board_str=board_str;
        this.game=game;

        buttonClicked=false;
        this.randomPlayer=randomPlayer;
        this.minotaur=minotaur;
        this.thiseusTileId=thiseusTileId;
        this.minotaurTileId=minotaurTileId;
        this.board=board;

    }



    @Override
    public void actionPerformed(ActionEvent e) {
           buttonClicked=true;



        if(e.getActionCommand().equals("Play")){

            game.setRound(game.getRound()+1);
            border.getRound().setText("Round:" + game.getRound());

            if(game.getRound()==100){
                border.getResult().setText("In a game of 100 rounds it's a tie but you can continue");
            }else if(game.getRound()!=100 && border.getResult().getText().equals("In a game of 100 rounds it's a tie but you can continue")){
                border.getResult().setText("");
            }

            if(game.getPlayer_kind()==1){
                int score_before_move=randomPlayer.getScore();

                randomPlayer.move(thiseusTileId, false);
                if(randomPlayer.getScore()!=score_before_move){
                    border.getTheseus_score().setText("Theseus\nMove Score:1\nTotal Score:"+randomPlayer.getScore());

                }else {
                    border.getTheseus_score().setText("Theseus\nMove Score:0\nTotal Score:"+randomPlayer.getScore());
                }
                if (randomPlayer.getScore() == 4) {
                    System.out.println("Thiseus wins!");
                    border.getResult().setText("Thiseus wins!");
                    border.getButton_play().setVisible(false);
                    border.getButton_quit().setVisible(false);
                                    }
                if (minotaurTileId == thiseusTileId) {
                    System.out.println("Minotaur wins!");
                    border.getResult().setText("Minotaur wins!");
                    border.getButton_play().setVisible(false);
                    border.getButton_quit().setVisible(false);
                    randomPlayer.setX(-1);
                    randomPlayer.setY(-1);

                }
                minotaur.move(minotaurTileId, false);
                if (minotaurTileId == thiseusTileId) {
                    System.out.println("Minotaur wins!");
                    border.getResult().setText("Minotaur wins!");
                    border.getButton_play().setVisible(false);
                    border.getButton_quit().setVisible(false);
                    randomPlayer.setX(-1);
                    randomPlayer.setY(-1);

                }


                System.out.println("Round: " + game.getRound());

                thiseusTileId = board.getN() * randomPlayer.getY() + randomPlayer.getX();
                minotaurTileId = board.getN() * minotaur.getY() + minotaur.getX();

            }
            else if (game.getPlayer_kind() == 2) {
                int score_before_move=heuristicPlayer.getScore();
                heuristicPlayer.move(heuristicPlayer.getNextMove(thiseusTileId, minotaurTileId),true);


                if(heuristicPlayer.getScore()!=score_before_move){
                    border.getTheseus_score().setText("Theseus\nMove Score:1\nTotal Score:"+heuristicPlayer.getScore());

                }else {
                    border.getTheseus_score().setText("Theseus\nMove Score:0\nTotal Score:"+heuristicPlayer.getScore());
                }
                if (heuristicPlayer.getScore() == 4) {
                    System.out.println("Thiseus wins!");
                    border.getResult().setText("Thiseus wins!");
                    border.getButton_play().setVisible(false);
                    border.getButton_quit().setVisible(false);
                    heuristicPlayer.statistics(true);
                }
                if (minotaurTileId == thiseusTileId) {
                    System.out.println("Minotaur wins!");
                    border.getResult().setText("Minotaur wins!");
                    border.getButton_play().setVisible(false);
                    border.getButton_quit().setVisible(false);
                    heuristicPlayer.statistics(true);
                    heuristicPlayer.setX(-1);
                    heuristicPlayer.setY(-1);

                }
                minotaur.move(minotaurTileId, false);
                if (minotaurTileId == thiseusTileId) {
                    System.out.println("Minotaur wins!");
                    border.getResult().setText("Minotaur wins!");
                    border.getButton_play().setVisible(false);
                    border.getButton_quit().setVisible(false);
                    heuristicPlayer.statistics(true);
                    heuristicPlayer.setX(-1);
                    heuristicPlayer.setY(-1);

                }


                System.out.println("Round: " + game.getRound());
                heuristicPlayer.statistics(false);

                thiseusTileId = board.getN() * heuristicPlayer.getY() + heuristicPlayer.getX();
                minotaurTileId = board.getN() * minotaur.getY() + minotaur.getX();


            }
            else if (game.getPlayer_kind()==3){
                int score_before_move=player.getScore();

                player.move(player.getNextMove(thiseusTileId, minotaurTileId)[0],true);

                if(player.getScore()!=score_before_move){
                    border.getTheseus_score().setText("Theseus\nMove Score:1\nTotal Score:"+player.getScore());

                }else {
                    border.getTheseus_score().setText("Theseus\nMove Score:0\nTotal Score:"+player.getScore());
                }
                if (player.getScore() == 4) {
                    System.out.println("Thiseus wins!");
                    border.getResult().setText("Thiseus wins!");
                    border.getButton_play().setVisible(false);
                    border.getButton_quit().setVisible(false);
                    player.statistics(true);
                }
                if (minotaurTileId == thiseusTileId) {
                    System.out.println("Minotaur wins!");
                    border.getResult().setText("Minotaur wins!");
                    border.getButton_play().setVisible(false);
                    border.getButton_quit().setVisible(false);
                    player.statistics(true);
                    player.setX(-1);
                    player.setY(-1);

                }
                minotaur.move(minotaurTileId, false);
                if (minotaurTileId == thiseusTileId) {
                    System.out.println("Minotaur wins!");
                    border.getResult().setText("Minotaur wins!");
                    border.getButton_play().setVisible(false);
                    border.getButton_quit().setVisible(false);
                    player.statistics(true);
                    player.setX(-1);
                    player.setY(-1);

                }


                System.out.println("Round: " + game.getRound());
                player.statistics(false);

                thiseusTileId = board.getN() * player.getY() + player.getX();
                minotaurTileId = board.getN() * minotaur.getY() + minotaur.getX();
            }

            minotaur_hide=false;
            for(int i=0;i<board.getSupplies().length;i++){
                if(minotaurTileId==board.getSupplies()[i].getSupplyTileId()){
                    minotaur_hide=true;
                }
            }
            if (minotaur_hide&& border.getResult().getText().equals("")){
                border.getResult().setText("The cunning Minotaur has hide behind the supply ");
            }else if(!minotaur_hide&&border.getResult().getText().equals("The cunning Minotaur has hide behind the supply ")){
                border.getResult().setText("");
            }


            board_str=board.getStringRepresentation(thiseusTileId, minotaurTileId);

            String str=new String();
            int k=0;
            for(int i=board_str.length-1;i>-1;i--) {
                for (int j = 0; j < board_str[0].length; j++) {
                    str =board_str[i][j];
                    border.getBoard_graph().getBoard_labels()[k][j].setText(str);
                }
                k++;

            }
        }

        if(e.getActionCommand().equals("Quit")){
            border.getResult().setText("YOU QUITED");
            border.getButton_play().setVisible(false);

        }

        if(e.getActionCommand().equals("Generate Board")){
            String str=new String();
            int k=0;
            for(int i=board_str.length-1;i>-1;i--) {

                for (int j = 0; j < board_str[0].length; j++) {
                    str =board_str[i][j];
                    border.getBoard_graph().getBoard_labels()[k][j].setText(str);
                }
                k++;
            }

        }

    }

    public boolean isButtonClicked() {
        return buttonClicked;
    }

    public void setButtonClicked(boolean buttonClicked) {
        this.buttonClicked = buttonClicked;
    }
}
