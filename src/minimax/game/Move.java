package minimax.game;

public interface Move<M extends Move<M, S>, S> {

    Integer getCost();

    M getReverseMove();

    S getTargetState();

}
