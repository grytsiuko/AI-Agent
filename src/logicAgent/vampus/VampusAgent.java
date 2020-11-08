package logicAgent.vampus;

import logicAgent.vampus.rules.VampusAbstractRule;
import logicAgent.vampus.rules.VampusBreezeRule;
import logicAgent.vampus.rules.VampusStenchRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VampusAgent {

    public static final int START_AGENT_ROW = VampusGame.START_AGENT_ROW;
    public static final int START_AGENT_COL = VampusGame.START_AGENT_COL;

    private int agentRow = START_AGENT_ROW;
    private int agentCol = START_AGENT_COL;

    private final List<VampusAbstractRule> rules;

    private final CellInfo[][] cellsInfo;
    private final VampusSensors[][] sensorsInfo;

    public VampusAgent() {
        this.cellsInfo = initCellsInfo();
        this.sensorsInfo = new VampusSensors[VampusGame.HEIGHT][VampusGame.WIDTH];
        this.rules = List.of(
                new VampusStenchRule(cellsInfo, sensorsInfo),
                new VampusBreezeRule(cellsInfo, sensorsInfo)
        );
    }

    public CellInfo[][] initCellsInfo(){
        CellInfo[][] info = new CellInfo[VampusGame.HEIGHT][VampusGame.WIDTH];

        for(int row = 0; row < VampusGame.HEIGHT; row++){
            for(int col = 0; col < VampusGame.WIDTH; col++){
                info[row][col] = new CellInfo();
            }
        }

        info[START_AGENT_ROW][START_AGENT_COL] = new CellInfo(false, false, false);
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
