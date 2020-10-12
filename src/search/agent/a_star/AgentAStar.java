package search.agent.a_star;

import search.agent.Agent;
import search.environment.HeuristicMoveInterface;
import search.environment.MoveInterface;
import search.environment.PlanningEnvironment;

import java.util.*;

public class AgentAStar <M extends HeuristicMoveInterface<M, I>, I> extends Agent {

    private final PlanningEnvironment<M, I> environment;
    Queue<List<M>> waysQueue;

    private Integer totalCost;

    public AgentAStar(PlanningEnvironment<M, I> environment) {
        this.environment = environment;

        Comparator<List<M>> costComparator = (w1, w2) -> {
            double w1Cost =
                    w1.stream().mapToInt(MoveInterface::getCost).sum() + w1.get(w1.size() - 1).getTargetHeuristic();
            double w2Cost =
                    w2.stream().mapToInt(MoveInterface::getCost).sum() + w2.get(w2.size() - 1).getTargetHeuristic();
            return (int) (w1Cost - w2Cost);
        };

        waysQueue = new PriorityQueue<>(costComparator);
        this.totalCost = 0;
    }


    @Override
    public void run() {
        while (!environment.isFinish()) {
            initQueue();
            List<M> way = findWay();
            if(way != null){
                moveToGoal(way);
            }
        }
    }

    private void initQueue(){
        waysQueue.clear();
        for(M move : environment.getPossibleMoves()){
            List<M> way = new ArrayList<>();
            way.add(move);
            waysQueue.add(way);
        }
    }

    private List<M> findWay(){
        while (!waysQueue.isEmpty()) {
            List<M> bestWay  = waysQueue.poll();
            M       lastMove = bestWay.get(bestWay.size() - 1);

            if(environment.isReached(lastMove.getTargetId())){
                return bestWay;
            }

            for (M move : environment.getPossibleMoves(lastMove.getTargetId())) {
                boolean shouldMoveBack = bestWay.stream().anyMatch(wayMove -> wayMove.getTargetId().equals(move.getTargetId()));
                if (!shouldMoveBack) {
                    List<M> way = new ArrayList<>();
                    way.addAll(bestWay);
                    way.add(move);
                    waysQueue.add(way);
                }
            }
        }
        return null;
    }


    @Override
    public Integer getTotalCost() {
        return totalCost;
    }

    private void moveToGoal(List<M> way){
        for(M move: way){
            totalCost += move.getCost();
            environment.doMove(move);
        }
    }

}
