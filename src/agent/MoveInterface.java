package agent;

public interface MoveInterface<T> {

    T getCost();

    MoveInterface<T> getReverseMove();
}
