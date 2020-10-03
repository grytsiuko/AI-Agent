package environment.graph;

import environment.MoveInterface;

public class MoveGraph implements MoveInterface<MoveGraph, Integer> {

    private final int from;
    private final int to;
    private final int cost;

    public MoveGraph(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public Integer getCost() {
        return this.cost;
    }

    @Override
    public MoveGraph getReverseMove() {
        return new MoveGraph(to, from, cost);
    }

    @Override
    public Integer getTargetId() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[" + from + "," + to + "](" + cost + ")";
    }
}
