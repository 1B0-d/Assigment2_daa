package metrics;

public class PerformanceTracker {
    public long comparisons, swaps, accesses, nanos;

    public void reset() { comparisons = swaps = accesses = nanos = 0; }

    public void acc(int k) { accesses += k; }

    @Override public String toString() {
        return "cmp=" + comparisons + ", swp=" + swaps + ", acc=" + accesses + ", ns=" + nanos;
    }
}
