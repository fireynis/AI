import TSP.TSP;

public class Driver {

    public Driver() {
        long t1 = System.nanoTime();
        System.out.println("Running all the variations. Takes approximately 20 seconds");
        try {
            TSP.GA(System.nanoTime(), 150, 5, 150, "uniform", 1.0, 0.0, 5);
            TSP.GA(System.nanoTime(), 150, 5, 150, "uniform", 1.0, 0.1, 5);
            TSP.GA(System.nanoTime(), 150, 5, 150, "uniform", 0.8, 0.0, 5);
            TSP.GA(System.nanoTime(), 150, 5, 150, "uniform", 0.8, 0.1, 5);
            TSP.GA(System.nanoTime(), 150, 5, 150, "uniform", 1.0, 0.3, 5);
            TSP.GA(System.nanoTime(), 150, 5, 150, "pmx", 1.0, 0.0, 5);
            TSP.GA(System.nanoTime(), 150, 5, 150, "pmx", 1.0, 0.1, 5);
            TSP.GA(System.nanoTime(), 150, 5, 150, "pmx", 0.8, 0.0, 5);
            TSP.GA(System.nanoTime(), 150, 5, 150, "pmx", 0.8, 0.1, 5);
            TSP.GA(System.nanoTime(), 150, 5, 150, "pmx", 1.0, 0.3, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println((System.nanoTime()-t1)/ 1000000000.0);
    }

    public static void main(String[] args) {
        Driver q = new Driver();
    }

}
