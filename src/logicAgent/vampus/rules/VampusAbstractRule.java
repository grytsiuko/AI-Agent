package logicAgent.vampus.rules;

import logicAgent.vampus.CellInfo;
import logicAgent.vampus.VampusSensors;

import java.util.List;

public abstract class VampusAbstractRule {

    protected final List<List<CellInfo>> vampusInfo;
    protected final List<List<CellInfo>>  holeInfo;
    protected final List<List<CellInfo>>  wallInfo;

    public VampusAbstractRule(List<List<CellInfo>> vampusInfo, List<List<CellInfo>>  holeInfo, List<List<CellInfo>>  wallInfo) {
        this.vampusInfo = vampusInfo;
        this.holeInfo = holeInfo;
        this.wallInfo = wallInfo;
    }

    public void conclude(int row, int col, VampusSensors sensors) {
        concreteConclude(row, col, sensors);
    }

    protected abstract void concreteConclude(int row, int col, VampusSensors sensors);

}
