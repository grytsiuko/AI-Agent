import agent.Agent;
import agent.dfs.AgentDfs;
import com.zetcode.Board;
import com.zetcode.Pacman;
import com.zetcode.PacmanEnvironment;
import com.zetcode.PacmanMove;
import environment.EnvironmentInterface;
import environment.graph.EnvironmentGraph;
import environment.graph.MoveGraph;

public class Main {
    public static void main(String[] args) {
//        EnvironmentGraph environmentGraph = new EnvironmentGraph();
//        AgentDfs<MoveGraph, Integer> agentDfs = new AgentDfs<>(environmentGraph);
//        agentDfs.benchmark();


        Board board = new Board();
        Pacman pacman = new Pacman(board);
        pacman.run();
        EnvironmentInterface<PacmanMove, Integer> environment = new PacmanEnvironment(board);
        Agent agent = new AgentDfs<>(environment);
        agent.benchmark();
    }
}
