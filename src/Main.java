import agent.dfs.AgentDfs;
import environment.graph.EnvironmentGraph;
import environment.graph.MoveGraph;

public class Main {
    public static void main(String[] args) {
        EnvironmentGraph environmentGraph = new EnvironmentGraph();
        AgentDfs<MoveGraph, Integer> agentDfs = new AgentDfs<>(environmentGraph);
        agentDfs.benchmark();
    }
}
