import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int startX = -1, startY = -1;
        int litterCount = 0;
        
        // Map (x, y) of litter cells to bit index (0 to 9)
        int[][] litterId = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(litterId[i], -1);
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startX = r;
                    startY = c;
                } else if (ch == 'L') {
                    litterId[r][c] = litterCount++;
                }
            }
        }

        int targetMask = (1 << litterCount) - 1;
        
        // Track maximum remaining energy for state: (x, y, mask)
        int[][][] maxEnergy = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(maxEnergy[i][j], -1);
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        
        // State: [x, y, mask, remaining_energy, moves]
        queue.offer(new int[]{startX, startY, 0, energy, 0});
        maxEnergy[startX][startY][0] = energy;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int mask = curr[2];
            int e = curr[3];
            int moves = curr[4];

            if (mask == targetMask) {
                return moves;
            }

            // Out of energy to move further
            if (e == 0) continue;

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                // Check bounds and obstacles
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                int nextE = e - 1;
                int nextMask = mask;
                char nextChar = classroom[nr].charAt(nc);

                // Collect litter
                if (nextChar == 'L') {
                    nextMask |= (1 << litterId[nr][nc]);
                } 
                // Reset energy capacity
                else if (nextChar == 'R') {
                    nextE = energy;
                }

                // Pruning: process only if we reach this state with more remaining energy
                if (nextE > maxEnergy[nr][nc][nextMask]) {
                    maxEnergy[nr][nc][nextMask] = nextE;
                    queue.offer(new int[]{nr, nc, nextMask, nextE, moves + 1});
                }
            }
        }

        return -1;
    }
}