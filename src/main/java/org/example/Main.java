package org.example;

import algorithms.ShellSort;
import algorithms.ShellSort.GapSeq;
import metrics.PerformanceTracker;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 1000; // размер массива
        int[] original = new Random().ints(n, 0, 10000).toArray();

        for (GapSeq seq : GapSeq.values()) {
            int[] arr = Arrays.copyOf(original, original.length);
            PerformanceTracker pt = new PerformanceTracker();

            long start = System.nanoTime();
            ShellSort.sort(arr, seq, pt);
            long end = System.nanoTime();

            double timeMs = (end - start) / 1_000_000.0;

            System.out.printf("%-10s | Comparisons: %6d | Swaps: %6d | Accesses: %6d | Time: %.3f ms%n",
                    seq, pt.comparisons, pt.swaps, pt.accesses, timeMs);
        }
    }
}
