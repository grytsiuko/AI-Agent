package logicAgent.vampus.rules;

import logicAgent.vampus.CellInfo;
import logicAgent.vampus.VampusSensors;

public class VampusHereRule extends VampusAbstractRule {

    public VampusHereRule(CellInfo[][] cellsInfo, VampusSensors[][] sensorsInfo) {
        super(cellsInfo, sensorsInfo);
    }

    @Override
    protected void concreteConclude(int row, int col, VampusSensors sensors) {
        getCell(row, col).ifPresent(this::setOkNoWall);
    }

    private void setOkNoWall(CellInfo cellInfo) {
        cellInfo.setOk(CellInfo.Type.TRUE);
        cellInfo.setHole(CellInfo.Type.FALSE);
        cellInfo.setVampus(CellInfo.Type.FALSE);
        cellInfo.setWall(CellInfo.Type.FALSE);
    }
}