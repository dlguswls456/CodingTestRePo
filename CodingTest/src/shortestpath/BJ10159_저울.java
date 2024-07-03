package shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10159_저울 {

    static int N, M;
    static int[][] result;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];
        result = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int heavy = Integer.parseInt(st.nextToken());
            int light = Integer.parseInt(st.nextToken());

            graph[heavy][light] = 1;
            result[heavy][light] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j)
                    continue;

                if (result[i][j] == 0) {
                    result[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (j == i)
                    continue;

                for (int k = 1; k <= N; k++) {
                    if (k == i || k == j)
                        continue;

                    if (result[j][i] == Integer.MAX_VALUE || result[i][k] == Integer.MAX_VALUE) {
                        result[j][k] = Math.min(Integer.MAX_VALUE, result[j][k]);
                    } else {
                        result[j][k] = Math.min(result[j][i] + result[i][k], result[j][k]);
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                result[i][j] = Math.min(result[i][j], result[j][i]);
            }
        }

        for (int i = 1; i <= N; i++) {
            int impossible = 0;
            for (int j = 1; j <= N; j++) {
                if (result[i][j] == Integer.MAX_VALUE) {
                    impossible++;
                }
            }
            System.out.println(impossible);
        }

    }
}

