import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] parents;
    static ArrayList<ArrayList<Integer>> parties = new ArrayList<>();
    static HashSet<Integer> knownSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 유니온 배열 초기화
        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }

        // 진실 아는 사람
        st = new StringTokenizer(br.readLine());
        int knownCnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < knownCnt; i++) {
            knownSet.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            parties.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            int partyCnt = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            parties.get(i).add(first);

            for (int j = 0; j < partyCnt - 1; j++) {
                int next = Integer.parseInt(st.nextToken());
                parties.get(i).add(next);
                union(first, next);
            }
        }

        HashSet<Integer> knownLeaders = new HashSet<>();
        for (int known : knownSet) {
            knownLeaders.add(find(known));
        }

        int result = 0;
        for (int i = 0; i < M; i++) {
            int leader = find(parties.get(i).get(0));
            if (!knownLeaders.contains(leader)) {
                result++;
            }
        }

        System.out.println(result);
    }

    public static int find(int node) {
        if (parents[node] == node) return node;
        return parents[node] = find(parents[node]);
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parents[rootB] = rootA;
        }
    }
}