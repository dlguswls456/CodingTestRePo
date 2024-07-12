package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1245_농장관리 {

    static int[][] map;
    static boolean[][] possible, isVisited;
    static int N, M, result;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        isVisited = new boolean[N][M];
        possible = new boolean[N][M];

        // 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 한칸씩 전부 8방향을 확인
        // 만약 주변 8방향 높이가 자신보다 같거나 작으면 boolean 배열에 true로 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                boolean isPeak = true;
                for (int k = 0; k < 8; k++) {
                    int newX = i + dx[k];
                    int newY = j + dy[k];

                    if (!isValid(newX, newY)) {
                        continue;
                    }

                    if (map[i][j] < map[newX][newY]) {
                        isPeak = false;
                        break;
                    }
                }

                if (isPeak) {
                    possible[i][j] = true;
                }
            }
        }

        // 봉우리 후보 덩어리 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!possible[i][j] || isVisited[i][j]) {
                    continue;
                }

                if (dfs(i, j)) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    static boolean dfs(int x, int y) {
        boolean isPeak = true;
        isVisited[x][y] = true;

        for (int i = 0; i < 8; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (!isValid(newX, newY) || isVisited[newX][newY]) {
                continue;
            }

            if (!possible[newX][newY] && map[x][y] <= map[newX][newY]) {
                isPeak = false;
            }

            if (possible[newX][newY]) {
                isPeak &= dfs(newX, newY);
            }
        }

        return isPeak;
    }

    public static boolean isValid(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M) {
            return false;
        }
        return true;
    }

}
