package agent;

// T - class of cost measure
public interface MoveInterface<T> {

    T getCost();

    MoveInterface<T> getReverseMove();
}
