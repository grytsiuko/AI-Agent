import agent.Agent;
import agent.greedy.AgentGreedy;
import environment.EnvironmentInterface;
import environment.pacman.Board;
import environment.pacman.Pacman;
import environment.pacman.PacmanEnvironment;
import environment.pacman.PacmanMove;

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
