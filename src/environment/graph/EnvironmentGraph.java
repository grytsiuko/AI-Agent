package environment.graph;

import environment.EnvironmentInterface;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EnvironmentGraph implements EnvironmentInterface<MoveGraph, Integer> {

    private final Set<Integer> toVisit = new HashSet<>(List.of(5, 7));
    private final int[][] adjacencyList = new int[][]{
            {1, 2},
            {3, 4},
            {5},
            {},
            {6},
            {7},
            {},
            {}
    };
    private int current;

    public EnvironmentGraph() {
        this.current = 0;
    }

    @Override
    public void doMove(MoveGraph move) {
        this.current = move.getTargetId();
        toVisit.remove(this.current);
    }

    @Override
    public List<MoveGraph> getPossibleMoves() {
        return Arrays
                .stream(this.adjacencyList[this.current])
                .mapToObj(v -> new MoveGraph(this.current, v, 1))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isFinish() {
        return this.toVisit.isEmpty();
    }

    @Override
    public Integer getId() {
        return this.current;
    }
}
