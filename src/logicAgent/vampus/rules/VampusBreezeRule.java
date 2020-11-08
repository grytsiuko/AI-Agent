package logicAgent.vampus.rules;

import logicAgent.vampus.CellInfo;
import logicAgent.vampus.VampusSensors;

public class VampusBreezeRule extends VampusAbstractRule {

    public VampusBreezeRule(CellInfo[][] cellsInfo, VampusSensors[][] sensorsInfo) {
        super(cellsInfo, sensorsInfo);
    }

    @Override
    protected void concreteConclude(int row, int col, VampusSensors sensors) {
//        Integer upRow = getUp(row);
//        Integer downRow = getDown(row);
//        Integer leftCol = getLeft(col);
//        Integer rightCol = getRight(col);
//
//        if(!sensors.isBreeze()){
//            if (upRow != null) {
//                cellsInfo[upRow][col].hole = false;
//            }
//            if(downRow != null){
//                cellsInfo[downRow][col].hole = false;
//            }
//            if(leftCol != null){
//                cellsInfo[row][leftCol].hole = false;
//            }
//            if(rightCol != null){
//                cellsInfo[row][rightCol].hole = false;
//            }
//        }

    }
}
