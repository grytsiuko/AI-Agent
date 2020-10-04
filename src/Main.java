import agent.Agent;
import agent.bfs.AgentBfs;
import agent.dfs.AgentDfs;
import com.zetcode.Board;
import com.zetcode.Pacman;
import com.zetcode.PacmanEnvironment;
import com.zetcode.PacmanMove;
import environment.EnvironmentInterface;
import environment.graph.EnvironmentGraph;
import environment.graph.MoveGraph;

import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        EnvironmentGraph environmentGraph = new EnvironmentGraph();
//        AgentDfs<MoveGraph, Integer> agentDfs = new AgentDfs<>(environmentGraph);
//        agentDfs.benchmark();

//        EnvironmentGraph environmentGraph = new EnvironmentGraph();
//        AgentBfs<MoveGraph, Integer> agentDfs = new AgentBfs<>(environmentGraph);
//        agentDfs.benchmark();


        Board dfsBoard = new Board();
        Pacman dfsPacman = new Pacman(dfsBoard);
        dfsPacman.run();
        EnvironmentInterface<PacmanMove, Integer> dfsEnvironment = new PacmanEnvironment(dfsBoard);
        Agent dfsAgent = new AgentDfs<>(dfsEnvironment);
        Thread.sleep(1500);
        dfsAgent.benchmark();

        Board bfsBoard = new Board();
        Pacman bfsPacman = new Pacman(bfsBoard);
        bfsPacman.run();
        EnvironmentInterface<PacmanMove, Integer> bfsEnvironment = new PacmanEnvironment(bfsBoard);
        Agent bfsAgent = new AgentBfs<>(bfsEnvironment);
        Thread.sleep(1500);
        bfsAgent.benchmark();
    }

}
