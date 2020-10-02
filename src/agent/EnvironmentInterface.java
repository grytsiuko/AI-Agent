package agent;

import java.util.List;

// M - class of Moves that are available in the environment
// I - class of states ID
public interface EnvironmentInterface<M extends MoveInterface<?>, I> {

    void doMove(M move);

    List<M> getPossibleMoves();

    boolean isFinish();

    I getId();
}
