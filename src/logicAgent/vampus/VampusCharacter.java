package logicAgent.vampus;

public class VampusCharacter {
    public enum VampusCharacterEnum {
        VAMPUS,
        HOLE,
        GOLD,
        EMPTY,
        WALL
    }

    private final VampusCharacterEnum vampusCharacterEnum;

    public VampusCharacter(VampusCharacterEnum vampusCharacterEnum) {
        this.vampusCharacterEnum = vampusCharacterEnum;
    }

    public VampusCharacterEnum getVampusCharacterEnum() {
        return vampusCharacterEnum;
    }

    @Override
    public String toString() {
        switch (this.vampusCharacterEnum) {
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
