package logicAgent.vampus;

public class VampusAgentMove {
    public enum Type {
        MOVE_LEFT,
        MOVE_RIGHT,
        MOVE_UP,
        MOVE_DOWN,
        GRAB_GOLD
    }
    
    private final Type type;
    
    VampusAgentMove(Type type) {
        this.type = type;
    }

    public Type getDirection() {
        return this.type;
    }
}
