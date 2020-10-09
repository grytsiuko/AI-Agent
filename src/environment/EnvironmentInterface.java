package environment;

import java.util.List;

// M - class of Moves that are available in the environment
// I - class of states ID
public interface EnvironmentInterface<M extends MoveInterface<M, I>, I> {

    void doMove(M move);

    List<M> getPossibleMoves();

    boolean isFinish();

    boolean isReached();

    I getId();
}
