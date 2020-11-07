package logicAgent.vampus;

public class VampusCharacter {
    public enum VampusCharacterEnum {
        VAMPUS,
        HOLE,
        GOLD,
        EMPTY
    }

    private final VampusCharacterEnum vampusCharacterEnum;

    public VampusCharacter(VampusCharacterEnum vampusCharacterEnum) {
        this.vampusCharacterEnum = vampusCharacterEnum;
    }

    @Override
    public String toString() {
        switch (this.vampusCharacterEnum) {
            case VAMPUS:
                return "V";
            case HOLE:
                return "H";
            case GOLD:
                return "G";
            case EMPTY:
                return " ";
            default:
                return "";
        }
    }
}
