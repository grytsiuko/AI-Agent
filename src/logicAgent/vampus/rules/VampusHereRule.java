package logicAgent.vampus.rules;

import logicAgent.vampus.Bool;
import logicAgent.vampus.CellInfo;
import logicAgent.vampus.VampusAgentMove;
import logicAgent.vampus.VampusSensors;

public class VampusHereRule extends VampusAbstractRule {

    public VampusHereRule(CellInfo[][] cellsInfo, Bool foundNewOk) {
        super(cellsInfo, foundNewOk);
    }

    @Override
    protected void concreteConclude(int row, int col, VampusSensors sensors, VampusAgentMove.Type prevMove) {
        getCell(row, col).ifPresent(this::setOkNoWall);
    }

    private void setOkNoWall(CellInfo cellInfo) {
        cellInfo.setOk(CellInfo.Type.TRUE);
        cellInfo.setHole(CellInfo.Type.FALSE);
        cellInfo.setVampus(CellInfo.Type.FALSE);
        cellInfo.setWall(CellInfo.Type.FALSE);
    }
}
