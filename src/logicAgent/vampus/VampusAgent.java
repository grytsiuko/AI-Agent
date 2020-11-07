package logicAgent.vampus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VampusAgent {

    public VampusAgent() {

    }

    public VampusAgentMove decideMove(VampusSensors vampusSensors) {
        if (vampusSensors.isGlitter()) {
            return new VampusAgentMove(VampusAgentMove.Direction.GRAB_GOLD);
        }

        List<VampusAgentMove.Direction> directions = new ArrayList<>();
        directions.add(VampusAgentMove.Direction.DOWN);
        directions.add(VampusAgentMove.Direction.UP);
        directions.add(VampusAgentMove.Direction.LEFT);
        directions.add(VampusAgentMove.Direction.RIGHT);
        VampusAgentMove.Direction choice = directions.get(new Random().nextInt(directions.size()));
        return new VampusAgentMove(choice);
    }

}
