package agent.dfs;

import agent.Agent;
import agent.EnvironmentInterface;
import agent.MoveInterface;

import java.util.HashSet;
import java.util.Set;

// M - class of moves that applied to environment
// I - class of ID of environment states
public class AgentDfs<M extends MoveInterface<M>, I> extends Agent {

    private final EnvironmentInterface<M, I> environment;
    private final Set<I> visited;

    private Integer totalCost;

    public AgentDfs(EnvironmentInterface<M, I> environment) {
        this.environment = environment;
        this.visited = new HashSet<>();
        this.totalCost = 0;
    }

    @Override
    public void run() {
        dfs();
    }

    private void dfs() {
        if (cycle()) {
            return;
        }
        visit();
        for (M move : this.environment.getPossibleMoves()) {
            this.doMove(move);
            dfs();
            this.doMove(move.getReverseMove());
        }
    }

    private void visit() {
        I currId = this.environment.getId();
        this.visited.add(currId);
    }

    private boolean cycle() {
        I currId = this.environment.getId();
        return this.visited.contains(currId);
    }

    private void doMove(M move) {
        this.totalCost += move.getCost();
        this.environment.doMove(move);
    }

    @Override
    public Integer getTotalCost() {
        return this.totalCost;
    }
}
