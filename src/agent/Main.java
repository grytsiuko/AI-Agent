package agent;

import agent.dfs.AgentDfs;
import agent.graph.EnvironmentGraph;
import agent.graph.MoveGraph;

public class Main {
    public static void main(String[] args) {
        EnvironmentGraph environmentGraph = new EnvironmentGraph();
        AgentDfs<MoveGraph, Integer> agentDfs = new AgentDfs(environmentGraph);
        agentDfs.benchmark();
    }
}
