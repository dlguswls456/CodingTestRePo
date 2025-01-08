import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] picks, visited;
    static boolean[] isTeam;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            picks = new int[N + 1];
            visited = new int[N + 1];
            isTeam = new boolean[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                picks[i] = Integer.parseInt(st.nextToken());
            }

            int idx = 1;
            for (int i = 1; i <= N; i++) {
                if (visited[i] == 0) {
                    findCircle(i, idx);
                    idx++;
                }
            }

            visited = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                if (visited[i] == 0 && isTeam[i]) {
                    chkCircle(i);
                }
            }

            int result = 0;
            for (boolean e : isTeam) {
                if (!e) {
                    result++;
                }
            }
            System.out.println(result - 1);
        }
    }

    public static void chkCircle(int node) {
        visited[node] = 1;
        isTeam[node] = true;

        if (visited[picks[node]] == 0) {
            chkCircle(picks[node]);
        }
    }

    public static void findCircle(int node, int idx) {
        visited[node] = idx;

        if (visited[picks[node]] != 0) {
            if (visited[picks[node]] == idx) {
                isTeam[node] = true;
            }
        } else {
            findCircle(picks[node], idx);
        }
    }
}