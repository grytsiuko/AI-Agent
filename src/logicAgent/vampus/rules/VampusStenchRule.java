package logicAgent.vampus.rules;

import logicAgent.vampus.Bool;
import logicAgent.vampus.CellInfo;
import logicAgent.vampus.VampusAgentMove;
import logicAgent.vampus.VampusSensors;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class VampusStenchRule extends VampusAbstractRule {

    public VampusStenchRule(CellInfo[][] cellsInfo, Bool foundNewOk) {
        super(cellsInfo, foundNewOk);
    }

    @Override
    protected void concreteConclude(int row, int col, VampusSensors sensors, VampusAgentMove.Type prevMove) {
        if (sensors.isStench()) {
            getCell(row - 1, col).ifPresent(this::setMaybeVampus);
            getCell(row + 1, col).ifPresent(this::setMaybeVampus);
            getCell(row, col - 1).ifPresent(this::setMaybeVampus);
            getCell(row, col + 1).ifPresent(this::setMaybeVampus);
            List<CellInfo> potential = List.of(
                    getCell(row - 1, col),
                    getCell(row + 1, col),
                    getCell(row, col - 1),
                    getCell(row, col + 1)
            )
                    .stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(cellInfo -> cellInfo.isVampus() || cellInfo.getVampus() == CellInfo.Type.MAYBE)
                    .collect(Collectors.toList());

            if (potential.size() == 1) {
                setTrueVampus(potential.get(0));
            }
        }
    }

    private void setMaybeVampus(CellInfo cellInfo) {
        if (cellInfo.isSomethingTrue()) {
            return;
        }
        cellInfo.setVampus(CellInfo.Type.MAYBE);
    }

    private void setTrueVampus(CellInfo cellInfo) {
        if (cellInfo.isVampus()) {
            return;
        }
        cellInfo.setVampus(CellInfo.Type.TRUE);
        cellInfo.setOk(CellInfo.Type.FALSE);
        cellInfo.setWall(CellInfo.Type.FALSE);
        cellInfo.setHole(CellInfo.Type.FALSE);
        System.out.println("FOUND VAMPUS");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
