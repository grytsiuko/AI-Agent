package logicAgent.vampus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VampusAgent {

    public VampusAgent() {

    }

    public VampusAgentMove decideMove(VampusSensors vampusSensors) {
        if (vampusSensors.isGlitter()) {
            return new VampusAgentMove(VampusAgentMove.Type.GRAB_GOLD);
        }

        List<VampusAgentMove.Type> types = new ArrayList<>();
        types.add(VampusAgentMove.Type.MOVE_DOWN);
        types.add(VampusAgentMove.Type.MOVE_UP);
        types.add(VampusAgentMove.Type.MOVE_LEFT);
        types.add(VampusAgentMove.Type.MOVE_RIGHT);
        types.add(VampusAgentMove.Type.ARROW_DOWN);
        types.add(VampusAgentMove.Type.ARROW_UP);
        types.add(VampusAgentMove.Type.ARROW_LEFT);
        types.add(VampusAgentMove.Type.ARROW_RIGHT);
        types.add(VampusAgentMove.Type.FINISH);
        VampusAgentMove.Type choice = types.get(new Random().nextInt(types.size()));
        return new VampusAgentMove(choice);
    }

}
