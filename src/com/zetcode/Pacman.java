package com.zetcode;

import agent.Agent;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Pacman extends JFrame {

    Board board;

    public Pacman(Board board){
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
        EventQueue.invokeLater(() -> {

//            var ex = new Pacman(board);
            this.setVisible(true);
        });
    }

}
