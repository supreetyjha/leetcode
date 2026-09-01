import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = -1, sc = -1;
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int totalLitters = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    sr = r;
                    sc = c;
                } else if (ch == 'L') {
                    litterId[r][c] = totalLitters++;
                }
            }
        }

        if (totalLitters == 0) {
            return 0;
        }

        int targetMask = (1 << totalLitters) - 1;

        // bestEnergy[r][c][mask] stores the maximum remaining energy seen for that state
        int[][][] bestEnergy = new int[m][n][1 << totalLitters];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }

        // State: [r, c, mask, curEnergy, moves]
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sr, sc, 0, energy, 0});
        bestEnergy[sr][sc][0] = energy;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int mask = curr[2];
            int curEnergy = curr[3];
            int moves = curr[4];

            if (mask == targetMask) {
                return moves;
            }

            if (curEnergy <= 0) {
                continue;
            }

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    char cell = classroom[nr].charAt(nc);
                    if (cell == 'X') {
                        continue;
                    }

                    int nMask = (cell == 'L') ? (mask | (1 << litterId[nr][nc])) : mask;
                    int nEnergy = (cell == 'R') ? energy : (curEnergy - 1);

                    if (nEnergy > bestEnergy[nr][nc][nMask]) {
                        bestEnergy[nr][nc][nMask] = nEnergy;
                        queue.offer(new int[]{nr, nc, nMask, nEnergy, moves + 1});
                    }
                }
            }
        }

        return -1;
    }
}