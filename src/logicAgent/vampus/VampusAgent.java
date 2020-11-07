package logicAgent.vampus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VampusAgent {

    public VampusAgent() {

    }

    public VampusAgentMove decideMove(VampusSensors vampusSensors) {
        List<VampusAgentMove.Direction> directions = new ArrayList<>();
        if (!vampusSensors.isWallDown()) {
            directions.add(VampusAgentMove.Direction.DOWN);
        }
        if (!vampusSensors.isWallUp()) {
            directions.add(VampusAgentMove.Direction.UP);
        }
        if (!vampusSensors.isWallLeft()) {
            directions.add(VampusAgentMove.Direction.LEFT);
        }
        if (!vampusSensors.isWallRight()) {
            directions.add(VampusAgentMove.Direction.RIGHT);
        }
        VampusAgentMove.Direction choice = directions.get(new Random().nextInt(directions.size()));
        return new VampusAgentMove(choice);
    }

}
