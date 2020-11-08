package logicAgent.vampus.rules;

import logicAgent.vampus.Bool;
import logicAgent.vampus.CellInfo;
import logicAgent.vampus.VampusAgentMove;
import logicAgent.vampus.VampusSensors;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VampusBreezeRule extends VampusAbstractRule {

    public VampusBreezeRule(CellInfo[][] cellsInfo, Bool foundNewOk) {
        super(cellsInfo, foundNewOk);
    }

    @Override
    protected void concreteConclude(int row, int col, VampusSensors sensors, VampusAgentMove.Type prevMove) {
        if (sensors.isBreeze()) {
            getCell(row - 1, col).ifPresent(this::setMaybeHole);
            getCell(row + 1, col).ifPresent(this::setMaybeHole);
            getCell(row, col - 1).ifPresent(this::setMaybeHole);
            getCell(row, col + 1).ifPresent(this::setMaybeHole);
            List<CellInfo> potential = List.of(
                    getCell(row - 1, col),
                    getCell(row + 1, col),
                    getCell(row, col - 1),
                    getCell(row, col + 1)
            )
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(cellInfo -> cellInfo.isHole() || cellInfo.getHole() == CellInfo.Type.MAYBE)
                    .collect(Collectors.toList());

            if (potential.size() == 1) {
                setTrueHole(potential.get(0));
            }
        }
    }

    private void setMaybeHole(CellInfo cellInfo) {
        if (cellInfo.isSomethingTrue()) {
            return;
        }
        cellInfo.setHole(CellInfo.Type.MAYBE);
    }

    private void setTrueHole(CellInfo cellInfo) {
        if (cellInfo.isHole()) {
            return;
        }
        cellInfo.setHole(CellInfo.Type.TRUE);
        cellInfo.setOk(CellInfo.Type.FALSE);
        cellInfo.setWall(CellInfo.Type.FALSE);
        cellInfo.setVampus(CellInfo.Type.FALSE);
        System.out.println("FOUND HOLE");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
