package minimax.environment;

import minimax.environment.Board;

import javax.swing.*;

public class PacmanGame extends JFrame {

    Board board;

    public PacmanGame(Board board){
        this.board = board;
        initUI();
    }


    private void initUI() {

        add(board);

        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);
        setLocationRelativeTo(null);
    }

    public void run(){
        this.setVisible(true);
    }

}
