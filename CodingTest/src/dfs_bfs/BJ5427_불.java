package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ5427_불 {
    static int[][] map;
    static boolean[][] isVisited;
    static int T, N, M, result;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            isVisited = new boolean[N][M];
            result = Integer.MAX_VALUE;
            queue = new LinkedList<>();

            int startX = 0;
            int startY = 0;
            // 입력
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    if (line.charAt(j) == '#') { // 벽
                        map[i][j] = -1;
                    } else if (line.charAt(j) == '*') { // 불
                        map[i][j] = -2;

                        queue.add(i);
                        queue.add(j);
                        queue.add(-2);
                        queue.add(0);
                        isVisited[i][j] = true;
                    } else if (line.charAt(j) == '@') { // 출발
                        map[i][j] = 1;

                        startX = i;
                        startY = j;
                    } else { // 빈 공간
                        map[i][j] = 0;
                    }
                }
            }
            queue.add(startX);
            queue.add(startY);
            queue.add(1);
            queue.add(1);
            isVisited[startX][startY] = true;

            bfs();

            if (result == Integer.MAX_VALUE) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(result);
            }
        }


    }

    public static void bfs() {
        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll();
            int elem = queue.poll();
            int time = queue.poll();

            if (elem == -2) { // 불인 경우
                for (int i = 0; i < 4; i++) {
                    int newX = x + dx[i];
                    int newY = y + dy[i];

                    if (!isValid(newX, newY) || isVisited[newX][newY] || map[newX][newY] != 0) {
                        continue;
                    }

                    queue.add(newX);
                    queue.add(newY);
                    queue.add(elem);
                    queue.add(time + 1);
                    isVisited[newX][newY] = true;
                }
            } else { // 사람인 경우
                for (int i = 0; i < 4; i++) {
                    int newX = x + dx[i];
                    int newY = y + dy[i];

                    if (!isValid(newX, newY)) { // 탈출 성공
                        result = time;
                        return;
                    }

                    if (isVisited[newX][newY] || map[newX][newY] != 0) {
                        continue;
                    }

                    queue.add(newX);
                    queue.add(newY);
                    queue.add(elem);
                    queue.add(time + 1);
                    isVisited[newX][newY] = true;
                }
            }


        }
    }


    public static boolean isValid(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return false;
        }
        return true;
    }
}
