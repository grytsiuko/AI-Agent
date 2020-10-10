package search.environment;

public interface MoveInterface<M extends MoveInterface<M, I>, I> {

    Integer getCost();

    M getReverseMove();

    I getTargetId();
}
