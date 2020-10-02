package agent.dfs;

import agent.Agent;
import agent.EnvironmentInterface;
import agent.MoveInterface;

// M - class of moves that applied to environment
// I - class of ID of environment states
public class AgentDfs<M extends MoveInterface, I> extends Agent {
    private final EnvironmentInterface<M, I> environment;

    public AgentDfs(EnvironmentInterface<M, I> environment) {
        this.environment = environment;
    }

    @Override
    public void run() {

    }

    @Override
    public Number getTotalCost() {
        return 0;
    }
}
