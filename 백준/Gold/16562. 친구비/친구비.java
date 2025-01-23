import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int N, M, k;
    static int[] friendPrice, roots;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        friendPrice = new int[N + 1];
        roots = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            friendPrice[i] = Integer.parseInt(st.nextToken());
            roots[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        for (int i=1;i<=N;i++) {
            find(i);
        }

        HashSet<Integer> hs = new HashSet<>();
        for (int root : roots) {
            hs.add(root);
        }

        int result = 0;
        for (int root : hs) {
            result += friendPrice[root];
        }

        System.out.println(result <= k ? result : "Oh no");

    }

    public static int find(int node) {
        if (roots[node] == node) return node;
        return roots[node] = find(roots[node]);
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            if (friendPrice[rootA] <= friendPrice[rootB]) {
                roots[rootB] = rootA;
            } else {
                roots[rootA] = rootB;
            }
        }
    }
}