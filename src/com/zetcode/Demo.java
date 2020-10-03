package com.zetcode;

import agent.Agent;
import environment.EnvironmentInterface;
import environment.MoveInterface;

import java.util.List;

public class Demo {
    public static void main(String[] args) {

        Board board = new Board();
        Pacman pacman = new Pacman(board);
        EnvironmentInterface<PacmanMove, Integer> environment = new PacmanEnvironment(board);

        Agent agent = new Agent() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++) {
                    cycleL();
                    cycleR();
                }

            }

            private void cycleL(){
                List<PacmanMove> moveList = environment.getPossibleMoves();
                System.out.println(environment.getId());
                PacmanMove m = moveList.get(2);
                environment.doMove(m);
                System.out.println(environment.movedSuccessfully());
                System.out.println(environment.getId());
                System.out.println("---------------");
            }
            private void cycleR(){
                List<PacmanMove> moveList = environment.getPossibleMoves();
                System.out.println(environment.getId());
                PacmanMove m = moveList.get(3);
                environment.doMove(m);
                System.out.println(environment.movedSuccessfully());
                System.out.println(environment.getId());
                System.out.println("---------------");
            }

            @Override
            public Integer getTotalCost() {
                return null;
            }
        };

        board.changeAgent(agent);

        pacman.run();
    }
}
