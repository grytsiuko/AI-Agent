package logicAgent.vampus;

import logicAgent.vampus.rules.VampusAbstractRule;
import logicAgent.vampus.rules.VampusStenchRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VampusAgent {

    public static final int START_AGENT_COL = VampusGame.START_AGENT_COL;
    public static final int START_AGENT_ROW = VampusGame.START_AGENT_ROW;

    private int agentRow = START_AGENT_ROW;
    private int agentCol = START_AGENT_COL;

    private final List<VampusAbstractRule> rules;

    public VampusAgent() {
        this.rules = List.of(
                new VampusStenchRule(5)
                //
                //
        );
    }

    public VampusAgentMove decideMove(VampusSensors vampusSensors) {
        for (VampusAbstractRule rule:rules) {
            rule.conclude(agentRow, agentCol, vampusSensors);
        }

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
