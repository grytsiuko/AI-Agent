package logicAgent.vampus;

public class VampusAgentMove {
    public enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        GRAB_GOLD
    }
    
    private final Direction direction;
    
    VampusAgentMove(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }
}
