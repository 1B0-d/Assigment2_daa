package metrics;

public class PerformanceTracker {
    public long comparisons = 0;
    public long swaps = 0;
    public long accesses = 0;
    public long nanos = 0;

    public void reset() { comparisons = swaps = accesses = nanos = 0; }
}
