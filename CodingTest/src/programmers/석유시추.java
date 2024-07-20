package programmers;

import java.util.*;

public class 석유시추 {

    static int cntArea = 10, cntOil = 0;
    static int maxOil = 0;
    static int N, M;
    static boolean[][] isVisited;
    static HashMap<Integer, Integer> hm = new HashMap<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;

    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;

        map = land;
        isVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !isVisited[i][j]) {
                    cntOil = 0;
                    dfs(i, j);
                    hm.put(cntArea, cntOil);
                    cntArea++;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            HashSet<Integer> hs = new HashSet<>();
            for (int j = 0; j < N; j++) {
                if (map[j][i] < 10) {
                    continue;
                }
                hs.add(map[j][i]);
            }

            int sumOil = 0;
            for (int area : hs) {
                sumOil += hm.get(area);
            }

            maxOil = Math.max(sumOil, maxOil);
        }


        return maxOil;
    }


    public static void dfs(int x, int y) {
        isVisited[x][y] = true;
        map[x][y] = cntArea;
        cntOil++;

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (!isValid(newX, newY) || isVisited[newX][newY] || map[newX][newY] == 0) {
                continue;
            }

            dfs(newX, newY);
        }
    }

    public static boolean isValid(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return false;
        }
        return true;
    }
}

