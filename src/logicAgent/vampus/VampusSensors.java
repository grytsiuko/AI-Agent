package logicAgent.vampus;

public class VampusSensors {
    private final boolean wallLeft;
    private final boolean wallRight;
    private final boolean wallUp;
    private final boolean wallDown;

    public VampusSensors(boolean wallLeft, boolean wallRight, boolean wallUp, boolean wallDown) {
        this.wallLeft = wallLeft;
        this.wallRight = wallRight;
        this.wallUp = wallUp;
        this.wallDown = wallDown;
    }

    public boolean isWallLeft() {
        return wallLeft;
    }

    public boolean isWallRight() {
        return wallRight;
    }

    public boolean isWallUp() {
        return wallUp;
    }

    public boolean isWallDown() {
        return wallDown;
    }
}
