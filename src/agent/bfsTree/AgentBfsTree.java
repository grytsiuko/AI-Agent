package agent.bfsTree;

import agent.Agent;
import environment.EnvironmentInterface;
import environment.MoveInterface;

import java.util.*;


// M - class of moves that applied to environment
// I - class of ID of environment states
public class AgentBfsTree<M extends MoveInterface<M, I>, I> extends Agent {

    private final EnvironmentInterface<M, I> environment;

    private Integer totalCost;

    Queue<ArrayList<M>> queue;
    Set<I>              visited;

    ArrayList<M> currentPos;

    public AgentBfsTree(EnvironmentInterface<M, I> environment) {
        this.environment = environment;
        this.totalCost = 0;

        queue = new ArrayDeque<>();
        visited = new HashSet<>();
        currentPos = new ArrayList<>();
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
                if (!moveForward(moves)) {
                    moves.remove(moves.size() - 1);
                    currentPos = moves;
                    continue;
                }

                for (M move : environment.getPossibleMoves()) {
                    if (!visited.contains(move.getTargetId())) {
                        ArrayList<M> list = new ArrayList<>(moves);
                        list.add(move);
                        queue.add(list);
                    }
                }

                visited.add(environment.getId());
                currentPos = moves;
            }
        }
    }


    private boolean moveForward(ArrayList<M> moves) {
        int prefix = commonPrefix(moves);
        for (int i = currentPos.size() - 1; i >= prefix; i--) {
            doMove(currentPos.get(i).getReverseMove());
        }
        for (int i = prefix; i < moves.size(); i++) {
            if (!doMove(moves.get(i))) {
                return false;
            }
        }
        return true;
    }

    private int commonPrefix(ArrayList<M> moves) {
        int length = 0;
        for (int i = 0; i < currentPos.size(); i++) {
            if (!currentPos.get(i).equals(moves.get(i))) {
                break;
            }
            length++;
        }
        return length;
    }


    private boolean isFinish() {
        return this.environment.isFinish();
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
