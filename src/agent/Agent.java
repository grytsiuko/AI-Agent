package agent;

public abstract class Agent {

    public void benchmark() {
        long start = System.nanoTime();
        run();
        long end = System.nanoTime();
        long delta = end - start;

        System.out.println("Spent time: " + (delta / 1e+6) + "ms");
        System.out.println("Total cost: " + getTotalCost());
    }

    public abstract void run();

    public abstract Number getTotalCost();
}
