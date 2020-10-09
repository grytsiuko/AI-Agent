package environment.pacman;

import environment.HeuristicMoveInterface;
import environment.pacman.PacmanEnvironment.Direction;

public class PacmanMove implements HeuristicMoveInterface<PacmanMove, Integer> {

    private final int cost;

    private Direction direction;
    private double currentHeuristic;
    private double targetHeuristic;

    private final int fromId;
    private final int toId;

    PacmanMove(int fromId, int toId, double currentHeuristic, double targetHeuristic, Direction direction, int cost){
        this.fromId = fromId;
        this.toId = toId;
        this.currentHeuristic = currentHeuristic;
        this.targetHeuristic = targetHeuristic;
        this.direction = direction;
        this.cost = cost;
    }

    public Direction getDirection(){
        return direction;
    }

    @Override
    public Integer getCost() {
        return cost;
    }

    @Override
    public PacmanMove getReverseMove() {
        return new PacmanMove(
                toId, fromId, targetHeuristic, currentHeuristic, PacmanEnvironment.reverseDirection(direction), cost
        );
    }

    @Override
    public Integer getTargetId() {
        return toId;
    }

    @Override
    public Double getTargetHeuristic() {
        return targetHeuristic;
    }
}
