package minimax;


import minimax.game.Heuristic;
import minimax.game.pacman.*;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainLab5 {

    public static void main(String[] args) throws Exception {
        int [][] levelData = {
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
//        int [][] levelData = {
//                {0, 0, 0, 0},
//                {0, 0, 0, 0},
//                {0, 0, 0, 0},
//                {0, 0, 0, 0}
//        };
        Board      board      = new Board(levelData);
        PacmanGame pacmanGame = new PacmanGame(board);
//        int x = 0;
        pacmanGame.run();
        PacmanAgent pacmanAgent = new PacmanAgent(board, -1);
//        Random random = new Random();
        Heuristic<PacmanState> heuristic = (s ->
                s.getGhostsX().stream().map(gX -> Math.abs(s.getPacmanX() - gX)).reduce(0, Integer::sum) +
                s.getGhostsY().stream().map(gY -> Math.abs(s.getPacmanY() - gY)).reduce(0, Integer::sum));
        Minimax<PacmanAgent, PacmanMove, PacmanState> minimax = new Minimax<>(board, pacmanAgent, heuristic, 2);
        minimax.start();
    }

}
