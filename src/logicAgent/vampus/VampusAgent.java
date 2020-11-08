package logicAgent.vampus;

import logicAgent.vampus.rules.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VampusAgent {

    public static final int START_AGENT_ROW = VampusGame.START_AGENT_ROW;
    public static final int START_AGENT_COL = VampusGame.START_AGENT_COL;

    private int agentRow = START_AGENT_ROW;
    private int agentCol = START_AGENT_COL;

    private final int WIDTH = VampusGame.WIDTH;
    private final int HEIGHT = VampusGame.HEIGHT;

    private final List<VampusAbstractRule> rules;

    private final CellInfo[][] cellsInfo;

    private VampusAgentMove.Type lastMoveType = null;

    public VampusAgent() {
        this.cellsInfo = initCellsInfo();
        this.rules = List.of(
                new VampusStenchRule(cellsInfo),
                new VampusBreezeRule(cellsInfo),
                new VampusOkRule(cellsInfo),
                new VampusHereRule(cellsInfo),
                new VampusBumpRule(cellsInfo)
        );
    }

    public CellInfo[][] initCellsInfo(){
        CellInfo[][] info = new CellInfo[HEIGHT][WIDTH];

        for(int row = 0; row < VampusGame.HEIGHT; row++){
            for(int col = 0; col < VampusGame.WIDTH; col++){
                info[row][col] = new CellInfo();
            }
        }
        return info;
    }

    public VampusAgentMove decideMove(VampusSensors vampusSensors) {
        moveBackIfBump(vampusSensors);
        concludeAll(vampusSensors);

        VampusAgentMove.Type choice = decideMoveType(vampusSensors);
        move(choice);
        lastMoveType = choice;

        return new VampusAgentMove(choice);
    }

    private void concludeAll(VampusSensors vampusSensors) {
        for (VampusAbstractRule rule:rules) {
            rule.conclude(agentRow, agentCol, vampusSensors, lastMoveType);
        }
    }

    private VampusAgentMove.Type decideMoveType(VampusSensors vampusSensors) {

        if (vampusSensors.isGlitter()) {
            return VampusAgentMove.Type.GRAB_GOLD;
        }

        if (agentRow < HEIGHT - 1 && cellsInfo[agentRow + 1][agentCol].isVampus()) {
            return VampusAgentMove.Type.ARROW_DOWN;
        }
        if (agentRow > 0 && cellsInfo[agentRow - 1][agentCol].isVampus()) {
            return VampusAgentMove.Type.ARROW_UP;
        }
        if (agentCol < WIDTH - 1 && cellsInfo[agentRow][agentCol + 1].isVampus()) {
            return VampusAgentMove.Type.ARROW_RIGHT;
        }
        if (agentCol > 0 && cellsInfo[agentRow][agentCol - 1].isVampus()) {
            return VampusAgentMove.Type.ARROW_LEFT;
        }

        List<VampusAgentMove.Type> types = new ArrayList<>();
        if (agentRow < HEIGHT - 1 && cellsInfo[agentRow + 1][agentCol].isOk()) {
            types.add(VampusAgentMove.Type.MOVE_DOWN);
        }
        if (agentRow > 0 && cellsInfo[agentRow - 1][agentCol].isOk()) {
            types.add(VampusAgentMove.Type.MOVE_UP);
        }
        if (agentCol < WIDTH - 1 && cellsInfo[agentRow][agentCol + 1].isOk()) {
            types.add(VampusAgentMove.Type.MOVE_RIGHT);
        }
        if (agentCol > 0 && cellsInfo[agentRow][agentCol - 1].isOk()) {
            types.add(VampusAgentMove.Type.MOVE_LEFT);
        }

        if (types.isEmpty()) {
            return VampusAgentMove.Type.FINISH;
        }
        return types.get(new Random().nextInt(types.size()));
    }

    private void moveBackIfBump(VampusSensors sensors) {
        if (sensors.isBump()) {
            switch (lastMoveType) {
                case MOVE_UP:
                    agentRow++;
                    break;
                case MOVE_DOWN:
                    agentRow--;
                    break;
                case MOVE_LEFT:
                    agentCol++;
                    break;
                case MOVE_RIGHT:
                    agentCol--;
                    break;
            }
        }
    }

    private void move(VampusAgentMove.Type type) {
            switch (type) {
                case MOVE_UP:
                    agentRow--;
                    break;
                case MOVE_DOWN:
                    agentRow++;
                    break;
                case MOVE_LEFT:
                    agentCol--;
                    break;
                case MOVE_RIGHT:
                    agentCol++;
                    break;
            }
    }

}
