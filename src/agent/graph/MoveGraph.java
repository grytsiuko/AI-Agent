package agent.graph;

import agent.MoveInterface;

public class MoveGraph implements MoveInterface<MoveGraph> {

    private final int from;
    private final int to;
    private final int cost;

    public MoveGraph(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    @Override
    public Integer getCost() {
        return cost;
    }

    @Override
    public MoveGraph getReverseMove() {
        return new MoveGraph(to, from, cost);
    }

    @Override
    public String toString() {
        return "[" + from + "," + to + "](" + cost + ")";
    }
}
