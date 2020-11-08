package logicAgent.vampus.rules;

import logicAgent.vampus.VampusSensors;

public class VampusStenchRule extends VampusAbstractRule {

    public VampusStenchRule(int a) {
        super(a);
    }

    @Override
    protected void concreteConclude(int row, int col, VampusSensors sensors) {
        System.out.println(super.a);
    }
}
