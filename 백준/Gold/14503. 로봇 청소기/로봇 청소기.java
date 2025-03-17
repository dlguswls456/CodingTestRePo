import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, result;

    static int[][] map;

    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 청소
        find(R, C, D);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    static void find(int x, int y, int dir) {
        // 청소하기
        map[x][y] = 2;
        // 탐색 & 이동하기
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            // 반시계 90도 회전
            int tempDir = (dir - i + 4) % 4;

            int newX = x + dx[tempDir];
            int newY = y + dy[tempDir];

            if (!isValid(newX, newY)) {
                continue;
            }

            if (map[newX][newY] == 0) {
                cnt++;
                break;
            }
        }

        // 주변 다 깨끗
        if (cnt == 0) {
            int backDir = (dir + 2) % 4;
            int backX = x + dx[backDir];
            int backY = y + dy[backDir];

            // 후진하려는 칸이 벽일 때
            if (map[backX][backY] == 1) {
                return;
            }else {
                find(backX, backY, dir);
            }
        }else { // 아니면 청소하러
            for (int i = 1; i <= 4; i++) {
                // 반시계 90도 회전
                int tempDir = (dir - i + 4) % 4;

                int newX = x + dx[tempDir];
                int newY = y + dy[tempDir];

                if (!isValid(newX, newY)) {
                    continue;
                }

                if (map[newX][newY] == 0) {
                    find(newX, newY, tempDir);
                    break;
                }
            }
        }
    }

    static boolean isValid(int x, int y) {
        if (x < 0 | x >= N || y < 0 || y >= M) {
            return false;
        }

        return true;
    }
}