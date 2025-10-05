package algorithms;

import metrics.PerformanceTracker;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShellSort {

    public enum GapSeq { SHELL, KNUTH, SEDGEWICK }

    private static List<Integer> gaps(int n, GapSeq type) {
        List<Integer> g = new ArrayList<>();
        switch (type) {
            case SHELL -> { // n/2, n/4, ..., 1
                for (int h = n / 2; h >= 1; h /= 2) g.add(h);
            }
            case KNUTH -> { // 1, 4, 13, 40, ... (3k+1) <= n
                int h = 1;
                while (h < n) { g.add(0, h); h = 3*h + 1; }
            }

            case SEDGEWICK -> { // 1, 5, 19, 41, 109, ... (через две формулы)

            case SEDGEWICK -> {

                int k = 0; int h;
                while (true) {
                    int a = (int)(Math.pow(4, k) + 3*Math.pow(2, k-1) + 1); // k>=1
                    int b = (int)(9*(Math.pow(4, k) - Math.pow(2, k)) + 1);
                    if (k == 0) { g.add(1); k++; continue; }
                    h = Math.min(a, b);
                    if (h >= n) break;
                    g.add(0, h);
                    k++;
                }
                if (g.isEmpty() || g.get(g.size()-1) != 1) g.add(1);
            }
        }
        if (g.isEmpty() || g.get(g.size()-1) != 1) g.add(1);
        return g;
    }


    public static void sort(int[] a, GapSeq seq, PerformanceTracker pt) {
        Objects.requireNonNull(a, "array");
        if (pt == null) pt = new PerformanceTracker();
        List<Integer> H = gaps(a.length, seq);

        for (int gap : H) {
            for (int i = gap; i < a.length; i++) {
                pt.accesses++; int temp = a[i];
                int j = i;
                while (j >= gap) {
                    pt.comparisons++;
                    pt.accesses += 2; // a[j-gap], temp
                    if (a[j - gap] <= temp) break;
                    a[j] = a[j - gap]; pt.swaps++; pt.accesses += 2; // write
                    j -= gap;
                }
                if (j != i) { a[j] = temp; pt.accesses++; }
            }
        }
    }

    public static void sort(int[] a, GapSeq seq, PerformanceTracker pt) {
    Objects.requireNonNull(a, "array");
    if (pt == null) pt = new PerformanceTracker();

    List<Integer> H = gaps(a.length, seq);

    for (int gap : H) {
        for (int i = gap; i < a.length; i++) {
            pt.accesses++;           
            int temp = a[i];
            int j = i;

            while (j >= gap) {
                pt.comparisons++;
                pt.accesses++;        
                if (a[j - gap] <= temp) break;

                a[j] = a[j - gap];    
                pt.swaps++;
                pt.accesses++;       
                j -= gap;
            }
            if (j != i) {            
                a[j] = temp;
                pt.accesses++;        
            }
        }
    }
}



}
