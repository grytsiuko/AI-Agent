package logicAgent.vampus.rules;

import logicAgent.vampus.CellInfo;
import logicAgent.vampus.VampusSensors;
import search.environment.pacman.Pacman;

public class VampusBumpRule extends VampusAbstractRule {

    public VampusBumpRule(CellInfo[][] cellsInfo, VampusSensors[][] sensorsInfo) {
        super(cellsInfo, sensorsInfo);
    }

    @Override
    protected void concreteConclude(int row, int col, VampusSensors sensors) {
        cellsInfo[row][col].wall = sensors.isBump();
    }
}
