package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA2112 {

    static int D, W, K, result;
    static int[][] film;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            film = new int[D][W];
            result = Integer.MAX_VALUE;
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (test()) {
                result = 0;
            } else {
                findRow(0, 0);
            }

            System.out.println("#" + t + " " + result);
        }
    }

    public static void findRow(int cnt, int idx) {
        if (cnt >= 1) {
            if (result > cnt) {
                if (test()) {
                    result = Math.min(result, cnt);
                    return;
                }
            } else {
                return;
            }
        }

        if (idx >= D || cnt >= D) {
            return;
        }

        // 선택 안함
        findRow(cnt, idx + 1);

        int[] temp = film[idx].clone();

        // 행 바꾸기 A
        for (int j = 0; j < W; j++) {
            film[idx][j] = 0;
        }
        findRow(cnt + 1, idx + 1);

        // 행 바꾸기 B
        for (int j = 0; j < W; j++) {
            film[idx][j] = 1;
        }
        findRow(cnt + 1, idx + 1);

        // 돌려놓기
        film[idx] = temp.clone();

    }

    public static boolean test() {
        boolean result = true;

        for (int i = 0; i < W; i++) {
            int A = 0;
            int B = 0;
            int maxCnt = 0;
            for (int j = 0; j < D; j++) {
                if (film[j][i] == 0) {
                    A++;
                    B = 0;
                    maxCnt = Math.max(maxCnt, A);
                } else {
                    B++;
                    A = 0;
                    maxCnt = Math.max(maxCnt, B);
                }
            }

            if (maxCnt < K) {
                result = false;
                break;
            }
        }

        return result;
    }
}
