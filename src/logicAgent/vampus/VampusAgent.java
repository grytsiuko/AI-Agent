package logicAgent.vampus;

import java.util.Random;

public class VampusAgent {

    public VampusAgent() {

    }

    public VampusAgentMove decideMove() {
        return new VampusAgentMove(VampusAgentMove.Direction.DOWN);
    }

}
