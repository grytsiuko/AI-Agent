import agent.Agent;
import agent.bfs.AgentBfs;
import agent.bfsTree.AgentBfsTree;
import agent.dfs.AgentDfs;
import environment.pacman.Board;
import environment.pacman.Pacman;
import environment.pacman.PacmanEnvironment;
import environment.pacman.PacmanMove;
import environment.EnvironmentInterface;
import environment.graph.EnvironmentGraph;
import environment.graph.MoveGraph;

public class MainLab3 {
    public static void main(String[] args) throws InterruptedException {
        EnvironmentGraph environmentGraph = new EnvironmentGraph();
        AgentDfs<MoveGraph, Integer> agentDfs = new AgentDfs<>(environmentGraph);
        agentDfs.benchmark();

        EnvironmentGraph environmentGraph2 = new EnvironmentGraph();
        AgentBfs<MoveGraph, Integer> agentBfs = new AgentBfs<>(environmentGraph2);
        agentBfs.benchmark();


        Board dfsBoard = new Board();
        Pacman dfsPacman = new Pacman(dfsBoard);
        dfsPacman.run();
        EnvironmentInterface<PacmanMove, Integer> dfsEnvironment = new PacmanEnvironment(dfsBoard);
        Agent dfsAgent = new AgentDfs<>(dfsEnvironment);
        Thread.sleep(1500);
        dfsAgent.benchmark();

        Board bfsTreeBoard = new Board();
        Pacman bfsTreePacman = new Pacman(bfsTreeBoard);
        bfsTreePacman.run();
        EnvironmentInterface<PacmanMove, Integer> bfsTreeEnvironment = new PacmanEnvironment(bfsTreeBoard);
        Agent bfsTreeAgent = new AgentBfsTree<>(bfsTreeEnvironment);
        Thread.sleep(1500);
        bfsTreeAgent.benchmark();

        Board bfsBoard = new Board();
        Pacman bfsPacman = new Pacman(bfsBoard);
        bfsPacman.run();
        EnvironmentInterface<PacmanMove, Integer> bfsEnvironment = new PacmanEnvironment(bfsBoard);
        Agent bfsAgent = new AgentBfs<>(bfsEnvironment);
        Thread.sleep(1500);
        bfsAgent.benchmark();
    }

}
