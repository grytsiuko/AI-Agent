package search.environment;

public interface HeuristicMoveInterface<M extends HeuristicMoveInterface<M, I>, I>
        extends MoveInterface<M, I> {

    Double getTargetHeuristic();
}
