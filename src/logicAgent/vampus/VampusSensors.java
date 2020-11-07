package logicAgent.vampus;

public class VampusSensors {
    private final boolean stench;
    private final boolean breeze;
    private final boolean glitter;
    private final boolean bump;
    private final boolean scream;

    public VampusSensors(boolean stench, boolean breeze, boolean glitter, boolean bump, boolean scream) {
        this.stench = stench;
        this.breeze = breeze;
        this.glitter = glitter;
        this.bump = bump;
        this.scream = scream;
    }

    public boolean isStench() {
        return stench;
    }

    public boolean isBreeze() {
        return breeze;
    }

    public boolean isGlitter() {
        return glitter;
    }

    public boolean isBump() {
        return bump;
    }

    public boolean isScream() {
        return scream;
    }
}
