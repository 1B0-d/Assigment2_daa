package cli;

import algorithms.ShellSort;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BenchmarkRunner {

    private static int[] genRandom(int n, long seed) {
        Random r = new Random(seed);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt();
        return a;
    }

    public static void main(String[] args) throws IOException {
        String seqArg = "KNUTH";
        String out = "docs/performance-plots/shellsort.csv";
        String sizes = "100,1000,10000,100000";

        for (String s : args) {
            if (s.startsWith("seq="))   seqArg = s.substring(4).toUpperCase();
            else if (s.startsWith("out="))   out = s.substring(4);
            else if (s.startsWith("sizes=")) sizes = s.substring(6);
        }

        ShellSort.GapSeq seq = ShellSort.GapSeq.valueOf(seqArg);
        new java.io.File("docs/performance-plots").mkdirs();

        try (FileWriter fw = new FileWriter(out)) {
            fw.write("n,seq,comparisons,swaps,accesses,nanos\n");
            for (String ns : sizes.split(",")) {
                int n = Integer.parseInt(ns.trim());
                int[] a = genRandom(n, 42);
                PerformanceTracker pt = new PerformanceTracker();

                long t0 = System.nanoTime();
                ShellSort.sort(a, seq, pt);
                long t1 = System.nanoTime();
                pt.nanos = (t1 - t0);

                fw.write(n + "," + seq + "," + pt.comparisons + "," + pt.swaps + "," + pt.accesses + "," + pt.nanos + "\n");
                System.out.println("Done n=" + n + " seq=" + seq + " time=" + pt.nanos + " ns");
            }
        }
    }
}
