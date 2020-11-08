package logicAgent.vampus.rules;

import logicAgent.vampus.CellInfo;
import logicAgent.vampus.VampusAgentMove;
import logicAgent.vampus.VampusSensors;
import search.environment.pacman.Pacman;

public class VampusBumpRule extends VampusAbstractRule {

    public VampusBumpRule(CellInfo[][] cellsInfo) {
        super(cellsInfo);
    }

    @Override
    protected void concreteConclude(int row, int col, VampusSensors sensors, VampusAgentMove.Type prevMove) {
        if (sensors.isBump()) {
            switch (prevMove) {
                case MOVE_UP:
                    getCell(row - 1, col).ifPresent(this::setWall);
                    break;
                case MOVE_DOWN:
                    getCell(row + 1, col).ifPresent(this::setWall);
                    break;
                case MOVE_LEFT:
                    getCell(row, col - 1).ifPresent(this::setWall);
                    break;
                case MOVE_RIGHT:
                    getCell(row, col + 1).ifPresent(this::setWall);
                    break;
            }
        }
    }

    private void setWall(CellInfo cellInfo) {
        cellInfo.setWall(CellInfo.Type.TRUE);
        cellInfo.setVampus(CellInfo.Type.FALSE);
        cellInfo.setHole(CellInfo.Type.FALSE);
        cellInfo.setOk(CellInfo.Type.FALSE);
    }
}
