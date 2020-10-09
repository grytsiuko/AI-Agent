package agent.greedy;

import agent.Agent;
import environment.EnvironmentInterface;
import environment.HeuristicMoveInterface;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

// M - class of moves that applied to environment
// I - class of ID of environment states
public class AgentGreedy<M extends HeuristicMoveInterface<M, I>, I> extends Agent {

    private final EnvironmentInterface<M, I> environment;
    private final Set<I> visited;
    private final Stack<M> path;

    private Integer totalCost;

    public AgentGreedy(EnvironmentInterface<M, I> environment) {
        this.environment = environment;
        this.visited = new HashSet<>();
        this.path = new Stack<>();
        this.totalCost = 0;
    }

    @Override
    public void run() {
        loop();
    }

    private void loop() {
        while (!isFinish()) {
            M next = getNextMove();
            if (next != null) {
                moveFurther(next);
            } else if (!path.empty()) {
                moveBack();
            } else {
                break;
            }
        }
    }

    private M getNextMove() {
        M next = null;
        for (M move : this.environment.getPossibleMoves()) {
            if (visited(move.getTargetId())) {
                continue;
            }
            if (next == null || move.getTargetHeuristic() < next.getTargetHeuristic()){
                next = move;
            }
        }
        return next;
    }

    private boolean isFinish () {
        return this.environment.isFinish();
    }

    private void checkIfOneReached() {
        if (this.environment.isReached()) {
            this.visited.clear();
        }
    }

    private boolean visited(I id) {
        return this.visited.contains(id);
    }

    private void moveBack() {
        M move = path.pop();
        doMove(move.getReverseMove());
    }

    private void moveFurther(M move){
        visited.add(move.getTargetId());
        path.push(move);
        doMove(move);
        checkIfOneReached();
    }

    private void doMove(M move) {
        this.environment.doMove(move);
        this.totalCost += move.getCost();
    }

    @Override
    public Integer getTotalCost() {
        return this.totalCost;
    }
}
