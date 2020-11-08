package logicAgent.vampus.rules;

import logicAgent.vampus.CellInfo;
import logicAgent.vampus.VampusSensors;

import java.util.List;

public class VampusStenchRule extends VampusAbstractRule {

    public VampusStenchRule(List<List<CellInfo>> vampusInfo, List<List<CellInfo>>  holeInfo, List<List<CellInfo>>  wallInfo) {
        super(vampusInfo, holeInfo, wallInfo);
    }

    @Override
    protected void concreteConclude(int row, int col, VampusSensors sensors) {

    }
}
