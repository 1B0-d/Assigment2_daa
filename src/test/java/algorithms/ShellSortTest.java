package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ShellSortTest {

    private void checkSorted(int[] a) {
        for (int i = 1; i < a.length; i++) assertTrue(a[i - 1] <= a[i]);
    }

    @Test void empty() {
        int[] a = {};
        ShellSort.sort(a, ShellSort.GapSeq.KNUTH, new PerformanceTracker());
        checkSorted(a);
    }

    @Test void single() {
        int[] a = {7};
        ShellSort.sort(a, ShellSort.GapSeq.KNUTH, new PerformanceTracker());
        checkSorted(a);
    }

    @Test void duplicates() {
        int[] a = {3, 3, 3, 3};
        ShellSort.sort(a, ShellSort.GapSeq.KNUTH, new PerformanceTracker());
        checkSorted(a);
    }

    @Test void sorted() {
        int[] a = {1, 2, 3, 4, 5};
        ShellSort.sort(a, ShellSort.GapSeq.SEDGEWICK, new PerformanceTracker());
        checkSorted(a);
    }

    @Test void reversed() {
        int[] a = {5, 4, 3, 2, 1};
        ShellSort.sort(a, ShellSort.GapSeq.SHELL, new PerformanceTracker());
        checkSorted(a);
    }

    @Test void mixed() {
        int[] a = {5, 1, 4, 2, 2, 9};
        ShellSort.sort(a, ShellSort.GapSeq.KNUTH, new PerformanceTracker());
        checkSorted(a);
    }

    // Те самые два теста, о которых шла речь:
    @Test void reversed_big() {
        int n = 2000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = n - i;
        ShellSort.sort(a, ShellSort.GapSeq.KNUTH, new PerformanceTracker());
        checkSorted(a);
    }

    @Test void manyDuplicates() {
        int[] a = {5,5,5,5,1,1,1,2,2,2,3,3,3,3,3,4,4};
        ShellSort.sort(a, ShellSort.GapSeq.SEDGEWICK, new PerformanceTracker());
        checkSorted(a);
    }
}
