package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17836_공주님을구해라 {

    static int N, M, T;
    static int[][] map;
    static boolean[][] isVisited;
    static int minTime = Integer.MAX_VALUE;
    static int knifeX, knifeY, knifeT;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        int shortestFromKnife = Integer.MAX_VALUE;
        if (knifeT != 0) {
            shortestFromKnife = (N - 1 - knifeX) + (M - 1 - knifeY) + knifeT;
        }

        minTime = Math.min(shortestFromKnife, minTime);
        if (minTime == Integer.MAX_VALUE || minTime > T) {
            System.out.println("Fail");
        } else {
            System.out.println(minTime);
        }
    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        queue.add(0);
        queue.add(0);
        isVisited[0][0] = true;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll();
            int t = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (!isValid(newX, newY) || isVisited[newX][newY] || map[newX][newY] == 1) {
                    continue;
                }

                if (newX == N - 1 && newY == M - 1) {
                    minTime = Math.min(minTime, t + 1);
                }

                if (map[newX][newY] == 2) {
                    knifeX = newX;
                    knifeY = newY;
                    knifeT = t + 1;
                }

                queue.add(newX);
                queue.add(newY);
                queue.add(t + 1);
                isVisited[newX][newY] = true;
            }
        }
    }

    static boolean isValid(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return false;
        }
        return true;
    }
}
