package logicAgent.vampus;

public class VampusAgent {

    public VampusAgent() {

    }

    public VampusAgentMove decideMove(VampusSensors vampusSensors) {
        if (!vampusSensors.isWallDown()) {
            return new VampusAgentMove(VampusAgentMove.Direction.DOWN);
        }
        if (!vampusSensors.isWallUp()) {
            return new VampusAgentMove(VampusAgentMove.Direction.UP);
        }
        if (!vampusSensors.isWallLeft()) {
            return new VampusAgentMove(VampusAgentMove.Direction.LEFT);
        }
        return new VampusAgentMove(VampusAgentMove.Direction.RIGHT);
    }

}
