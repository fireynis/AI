import TSP.TSP;

public class main {

    public main() {
        long t1 = System.nanoTime();
        TSP test1 = new TSP(System.nanoTime(), 150, 5, 150, "uniform", 1.0, 0.0, 5);
        TSP test2 = new TSP(System.nanoTime(), 150, 5, 150, "uniform", 1.0, 0.1, 5);
        TSP test3 = new TSP(System.nanoTime(), 150, 5, 150, "uniform", 0.8, 0.0, 5);
        TSP test4 = new TSP(System.nanoTime(), 150, 5, 150, "uniform", 0.8, 0.1, 5);
        TSP test5 = new TSP(System.nanoTime(), 150, 5, 150, "uniform", 1.0, 0.3, 5);
        System.out.println((System.nanoTime()-t1)/ 1000000000.0);
    }

    public static void main(String[] args) {
        main q = new main();
    }

}
