package agent.bfs;

import agent.Agent;
import environment.EnvironmentInterface;
import environment.MoveInterface;

import java.util.*;


// M - class of moves that applied to environment
// I - class of ID of environment states
public class AgentBfs<M extends MoveInterface<M, I>, I> extends Agent {

    private final EnvironmentInterface<M, I> environment;

    private Integer totalCost;

    Queue<ArrayList<M>> queue;
    Set<I>              visited;

    public AgentBfs(EnvironmentInterface<M, I> environment) {
        this.environment = environment;
        this.totalCost = 0;

        queue = new ArrayDeque<>();
        visited = new HashSet<>();
    }

    @Override
    public void run() {
        //initialization
        for (M move : environment.getPossibleMoves()) {
            ArrayList<M> list = new ArrayList<>();
            list.add(move);
            queue.add(list);
        }
        visited.add(environment.getId());

        //bfs
        while (!queue.isEmpty() && !isFinish()) {
            ArrayList<M> moves = queue.poll();

            if (!visited.contains(moves.get(moves.size() - 1).getTargetId())) {
                moveForward(moves);
                for (M move : environment.getPossibleMoves()) {
                    if (!visited.contains(move.getTargetId())) {
                        ArrayList<M> list = new ArrayList<>(moves);
                        list.add(move);
                        queue.add(list);
                    }
                }

                visited.add(environment.getId());
                moveBack(moves);
            }
        }
    }


    private void moveForward(ArrayList<M> moves) {
        for (M move : moves) {
            doMove(move);
        }
    }

    private void moveBack(ArrayList<M> moves) {
        for(int i = moves.size() - 1; i >= 0; --i){
            doMove(moves.get(i).getReverseMove());
        }
    }

    private boolean isFinish() {
        return this.environment.isFinish();
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
