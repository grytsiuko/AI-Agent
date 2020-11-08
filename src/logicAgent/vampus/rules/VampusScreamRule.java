package logicAgent.vampus.rules;

import logicAgent.vampus.Bool;
import logicAgent.vampus.CellInfo;
import logicAgent.vampus.VampusAgentMove;
import logicAgent.vampus.VampusSensors;

public class VampusScreamRule extends VampusAbstractRule {

    public VampusScreamRule(CellInfo[][] cellsInfo, Bool foundNewOk) {
        super(cellsInfo, foundNewOk);
    }

    @Override
    protected void concreteConclude(int row, int col, VampusSensors sensors, VampusAgentMove.Type prevMove) {
        if (sensors.isScream()) {
            switch (prevMove) {
                case ARROW_UP:
                    getCell(row - 1, col).ifPresent(this::setNoVampus);
                    break;
                case ARROW_DOWN:
                    getCell(row + 1, col).ifPresent(this::setNoVampus);
                    break;
                case ARROW_LEFT:
                    getCell(row, col - 1).ifPresent(this::setNoVampus);
                    break;
                case ARROW_RIGHT:
                    getCell(row, col + 1).ifPresent(this::setNoVampus);
                    break;
            }
        }
    }

    private void setNoVampus(CellInfo cellInfo) {
        this.foundNewOk.setValue(true);
        cellInfo.setOk(CellInfo.Type.TRUE);
        cellInfo.setWall(CellInfo.Type.FALSE);
        cellInfo.setVampus(CellInfo.Type.FALSE);
        cellInfo.setHole(CellInfo.Type.FALSE);
    }
}
