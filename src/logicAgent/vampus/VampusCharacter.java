package logicAgent.vampus;

public class VampusCharacter {
    public enum Type {
        VAMPUS,
        HOLE,
        GOLD,
        EMPTY,
        WALL
    }

    private final Type type;

    public VampusCharacter(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        switch (this.type) {
            case VAMPUS:
                return "V";
            case HOLE:
                return "O";
            case GOLD:
                return "G";
            case EMPTY:
                return " ";
            case WALL:
                return "#";
            default:
                return "";
        }
    }
}
