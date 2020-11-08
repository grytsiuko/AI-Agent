package logicAgent.vampus.rules;

import logicAgent.vampus.CellInfo;
import logicAgent.vampus.VampusSensors;


public class VampusStenchRule extends VampusAbstractRule {

    public VampusStenchRule(CellInfo[][] cellsInfo, VampusSensors[][] sensorsInfo) {
        super(cellsInfo, sensorsInfo);
    }

    @Override
    protected void concreteConclude(int row, int col, VampusSensors sensors) {
//        Integer upRow = getUp(row);
//        Integer downRow = getDown(row);
//        Integer leftCol = getLeft(col);
//        Integer rightCol = getRight(col);
//
//        if(!sensors.isStench()) {
//            if (upRow != null) {
//                cellsInfo[upRow][col].vampus = false;
//            }
//            if (downRow != null) {
//                cellsInfo[downRow][col].vampus = false;
//            }
//            if (leftCol != null) {
//                cellsInfo[row][leftCol].vampus = false;
//            }
//            if (rightCol != null) {
//                cellsInfo[row][rightCol].vampus = false;
//            }
//        }
    }
}
