package minimax.game.pacman;

import minimax.game.Move;

public class PacmanMove implements Move<PacmanMove, PacmanState> {
    private final int cost;

    private PacmanAgent.Direction direction;

    private final PacmanState fromId;
    private final PacmanState toId;

    PacmanMove(PacmanState fromId, PacmanState toId, PacmanAgent.Direction direction, int cost){
        this.fromId = fromId;
        this.toId = toId;
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
                toId, fromId, PacmanAgent.reverseDirection(direction), cost
        );
    }

    @Override
    public PacmanState getTargetState() {
        return toId;
    }

}
