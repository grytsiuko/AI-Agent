package agent.greedy;

import agent.Agent;
import environment.EnvironmentInterface;
import environment.HeuristicMoveInterface;

// M - class of moves that applied to environment
// I - class of ID of environment states
public class AgentGreedy<M extends HeuristicMoveInterface<M, I>, I> extends Agent {

    private final EnvironmentInterface<M, I> environment;
//    private final Set<I> visited;

    private Integer totalCost;

    public AgentGreedy(EnvironmentInterface<M, I> environment) {
        this.environment = environment;
//        this.visited = new HashSet<>();
        this.totalCost = 0;
    }

    @Override
    public void run() {
        loop();
    }

    private void loop() {
        while (!isFinish()) {
            M next = null;
            for (M move : this.environment.getPossibleMoves()) {
                if (next == null || move.getTargetHeuristic() < next.getTargetHeuristic()){
                    next = move;
                }
            }
            doMove(next);
        }
    }

    private boolean isFinish () {
        return this.environment.isFinish();
    }

//    private boolean visited(I id) {
//        return this.visited.contains(id);
//    }

    private void doMove(M move) {
        this.environment.doMove(move);
        this.totalCost += move.getCost();
    }

    @Override
    public Integer getTotalCost() {
        return this.totalCost;
    }
}
