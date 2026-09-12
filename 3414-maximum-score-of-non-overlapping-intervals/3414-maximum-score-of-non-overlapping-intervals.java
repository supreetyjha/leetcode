import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, id;
        Interval(int l, int r, int w, int id) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.id = id;
        }
    }

    static class State {
        long weight;
        int[] ids;

        State(long weight, int[] ids) {
            this.weight = weight;
            this.ids = ids;
        }

        boolean isBetterThan(State other) {
            if (other == null) return true;
            if (this.weight != other.weight) {
                return this.weight > other.weight;
            }
            int len = Math.min(this.ids.length, other.ids.length);
            for (int i = 0; i < len; i++) {
                if (this.ids[i] != other.ids[i]) {
                    return this.ids[i] < other.ids[i];
                }
            }
            return this.ids.length < other.ids.length;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> it = intervals.get(i);
            arr[i] = new Interval(it.get(0), it.get(1), it.get(2), i);
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a.l));

        int[] starts = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = arr[i].l;
        }

        State[][] dp = new State[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0L, new int[0]);
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int nextIdx = upperBound(starts, arr[i].r);

            for (int k = 1; k <= 4; k++) {
                State best = dp[i + 1][k];

                State nextState = dp[nextIdx][k - 1];
                long candWeight = (long) arr[i].w + nextState.weight;

                int[] candIds = new int[nextState.ids.length + 1];
                candIds[0] = arr[i].id;
                System.arraycopy(nextState.ids, 0, candIds, 1, nextState.ids.length);
                Arrays.sort(candIds);

                State candState = new State(candWeight, candIds);

                if (candState.isBetterThan(best)) {
                    best = candState;
                }

                dp[i][k] = best;
            }
        }

        return dp[0][4].ids;
    }

    private int upperBound(int[] starts, int target) {
        int low = 0, high = starts.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (starts[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}