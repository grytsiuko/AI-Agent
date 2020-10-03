package agent;

public abstract class Agent {

    public void benchmark() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long start = System.nanoTime();
        run();
        long end = System.nanoTime();
        long delta = end - start;
        long memory = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("Spent time: " + (delta / 1e+6) + "ms");
        System.out.println("Total cost: " + getTotalCost());
        System.out.println("Memory:     " + (memory / 1024 / 1024) + "Mb");
    }

    public abstract void run();

    public abstract Integer getTotalCost();
}
