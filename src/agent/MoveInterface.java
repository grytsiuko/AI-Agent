package agent;

public interface MoveInterface<M extends MoveInterface<M>> {

    Integer getCost();

    M getReverseMove();
}
