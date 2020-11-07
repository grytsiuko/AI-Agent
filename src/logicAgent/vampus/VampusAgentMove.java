package logicAgent.vampus;

public class VampusAgentMove {
    public enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
    
    private final Direction direction;
    
    VampusAgentMove(Direction direction) {
        this.direction = direction;
    }
}
