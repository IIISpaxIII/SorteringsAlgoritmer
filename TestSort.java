public class TestSort {
    private static int[] create(int N, boolean ordered) {
        return ordered ?
                ArrayUtil.createOrdered(N) :
                ArrayUtil.createShuffeled(N);
    }

    private static double timeit(IntSorter sorter, int[] a) {
        long before = System.currentTimeMillis();
        sorter.sort(a);
        return (System.currentTimeMillis() - before) / 1000.0;
    }

    private static void testSort(IntSorter sorter, int firstN, boolean ordered) {
        double t1 = 0;
        int N = firstN / 2;

        while (t1 < 0.7 && N < 500000) {
            N *= 2;
            int[] a = create(N, ordered);
            t1 = timeit(sorter, a);
            System.out.println("T(" + N + ")=" + t1);
            ArrayUtil.testOrdered(a);
        }
        int[] a = create(4 * N, ordered);
        double t4 = timeit(sorter, a);
        ArrayUtil.testOrdered(a);
        double t01 = t1 / (N * Math.log(N)); // ”tid” per jämförelse
        double t04 = t4 / (4 * N * Math.log(4 * N));
        System.out.println("T(" + 4 * N + ")=" + t4 + " growth per N log N: " + t04 / t01);
        if (t04 / t01 > 1.5) { // should be 1.0, but we give it some slack
            System.out.println(sorter.getClass().getName() + ".sort appears not to run in O(N log N) time");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        long startN = 2500000;
        long maxN = 40000000;

        System.out.println("N \t\t Merge (s) \t Quick (s) \t TreeMap (s)");
        System.out.println("-----------------------------------------------------------------------");

        for (long n = startN; n <= maxN; n *= 2) {
            // Skapa array för aktuell storlek
            int[] master = ArrayUtil.createShuffeled((int)n);

            // 1. Testa  MergeSort (använder internt M=41)
            int[] arrayMerge = master.clone();
            double tidMerge = timeit(new OptimizedMergeSorter(), arrayMerge);

            // 2. Testa QuickSort använder internt M=21
            int[] arrayQuick = master.clone();
            double tidQuick = timeit(new QuickSorter(), arrayQuick);

            // 3. Testa din TreeMapSorter 
            int[] arrayTree = master.clone();
            double tidTree = timeit(new TreeMapSorter(), arrayTree);

            System.out.printf("%-10d \t %.4f \t\t %.4f \t\t %.4f%n", n, tidMerge, tidQuick, tidTree);
        }
    }
}
