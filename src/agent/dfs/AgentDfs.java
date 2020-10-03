package agent.dfs;

import agent.Agent;
import environment.EnvironmentInterface;
import environment.MoveInterface;

import java.util.HashSet;
import java.util.Set;

// M - class of moves that applied to environment
// I - class of ID of environment states
public class AgentDfs<M extends MoveInterface<M, I>, I> extends Agent {

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

    private boolean dfs() {
        visit();
        if (isFinish()) {
            return true;
        }
        for (M move : this.environment.getPossibleMoves()) {
            if (!visited(move.getTargetId()) && this.doMove(move)) {
                if (dfs()) {
                    return true;
                }
                this.doMove(move.getReverseMove());
            }
        }
        return false;
    }

    private void visit() {
        I currId = this.environment.getId();
        this.visited.add(currId);
    }

    private boolean isFinish () {
        return this.environment.isFinish();
    }

    private boolean visited(I id) {
        return this.visited.contains(id);
    }

    private boolean doMove(M move) {
        this.environment.doMove(move);
        boolean success = this.environment.movedSuccessfully();
        if (success) {
            this.totalCost += move.getCost();
        }
        return success;
    }

    @Override
    public Integer getTotalCost() {
        return this.totalCost;
    }
}
