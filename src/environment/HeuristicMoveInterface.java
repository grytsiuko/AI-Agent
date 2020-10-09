package environment;

public interface HeuristicMoveInterface<M extends HeuristicMoveInterface<M, I>, I>
        extends MoveInterface<M, I> {

    Integer getTargetHeuristic();
}
