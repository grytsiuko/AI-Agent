package search;

import search.agent.Agent;
import search.agent.greedy.AgentGreedy;
import search.environment.EnvironmentInterface;
import search.environment.pacman.Board;
import search.environment.pacman.Pacman;
import search.environment.pacman.PacmanEnvironment;
import search.environment.pacman.PacmanMove;

public class MainLab4 {
    public static void main(String[] args) throws InterruptedException {
        Board greedyBoard = new Board();
        Pacman greedyPacman = new Pacman(greedyBoard);
        greedyPacman.run();
        EnvironmentInterface<PacmanMove, Integer> greedyEnvironment = new PacmanEnvironment(greedyBoard);
        Agent greedyAgent = new AgentGreedy<>(greedyEnvironment);
        Thread.sleep(1500);
        greedyAgent.benchmark();
    }
}
