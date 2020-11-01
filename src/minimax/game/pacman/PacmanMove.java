package minimax.game.pacman;

import minimax.game.Move;

public class PacmanMove implements Move<PacmanMove, Integer> {
    private final int cost;

    private PacmanAgent.Direction direction;
    private double                currentHeuristic;
    private double targetHeuristic;

    private final int fromId;
    private final int toId;

    PacmanMove(int fromId, int toId, double currentHeuristic, double targetHeuristic, PacmanAgent.Direction direction, int cost){
        this.fromId = fromId;
        this.toId = toId;
        this.currentHeuristic = currentHeuristic;
        this.targetHeuristic = targetHeuristic;
        this.direction = direction;
        this.cost = cost;
    }

    public PacmanAgent.Direction getDirection(){
        return direction;
    }

    @Override
    public Integer getCost() {
        return cost;
    }

    @Override
    public PacmanMove getReverseMove() {
        return new PacmanMove(
                toId, fromId, targetHeuristic, currentHeuristic, PacmanAgent.reverseDirection(direction), cost
        );
    }

    @Override
    public Integer getTargetState() {
        return toId;
    }

}
