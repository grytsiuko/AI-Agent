package search.environment;

import java.util.List;

public interface PlanningEnvironment<M extends MoveInterface<M, I>, I> extends EnvironmentInterface<M, I> {
    List<M> getPossibleMoves(I id);
}
