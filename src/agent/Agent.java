package agent;

// T - class of total cost measure
public abstract class Agent<T> {

    public void benchmark() {
        long start = System.nanoTime();
        run();
        long end = System.nanoTime();
        long delta = end - start;

        System.out.println("Spent time: " + (delta / 1e-6) + "ms");
        System.out.println("Total cost: " + getTotalCost());
    }

    public abstract void run();

    public abstract T getTotalCost();
}
