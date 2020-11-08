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

    private final List<List<CellInfo>> vampusInfo;
    private final List<List<CellInfo>> holeInfo;
    private final List<List<CellInfo>> wallInfo;

    public VampusAgent() {
        this.vampusInfo = generateInitInfo();
        this.holeInfo = generateInitInfo();
        this.wallInfo = generateInitInfo();
        this.rules = List.of(
                new VampusStenchRule(vampusInfo, holeInfo, wallInfo)
        );
    }

    private List<List<CellInfo>> generateInitInfo(){
        List<List<CellInfo>> info = new ArrayList<>();
        for(int i = 0; i < VampusGame.HEIGHT; i++){
            info.add(new ArrayList<>());

            for(int j = 0; j < VampusGame.WIDTH; j++){
                info.get(i).add(CellInfo.Unknown);
            }
        }
        return info;
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
