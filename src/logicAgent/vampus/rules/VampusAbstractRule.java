package logicAgent.vampus.rules;

import logicAgent.vampus.VampusSensors;

public abstract class VampusAbstractRule {

    protected int a;

    public VampusAbstractRule(int a) {
        this.a = a;
    }

    public void conclude(int row, int col, VampusSensors sensors) {
        concreteConclude(row, col, sensors);
    }

    protected abstract void concreteConclude(int row, int col, VampusSensors sensors);

}
