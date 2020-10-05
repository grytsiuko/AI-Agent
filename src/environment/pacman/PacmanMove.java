package environment.pacman;

import environment.MoveInterface;
import environment.pacman.PacmanEnvironment.Direction;

public class PacmanMove implements MoveInterface<PacmanMove, Integer> {

    private final int cost;

    private Direction direction;

    private final int fromId;
    private final int toId;

    PacmanMove(int fromId, int toId, Direction direction, int cost){
        this.fromId = fromId;
        this.toId = toId;
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
        return new PacmanMove(toId, fromId, PacmanEnvironment.reverseDirection(direction), cost);
    }

    @Override
    public Integer getTargetId() {
        return toId;
    }
}
