import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, Q;
    static int[] parents;
    static Node[] sorted;

    static class Node implements Comparable<Node> {
        int id;
        int x1;
        int x2;
        int y;

        public Node(int id, int x1, int x2, int y) {
            this.id = id;
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if (this.x1 == o.x1) {
                return this.x2 - o.x2;
            } else {
                return this.x1 - o.x1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        sorted = new Node[N];
        parents = new int[N + 1];
        for (int i = 0; i < N; i++) {
            parents[i + 1] = i + 1;

            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            sorted[i] = new Node(i + 1, x1, x2, y);
        }

        findConnection();
        for (int elem : parents) {
            find(elem);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int id1 = Integer.parseInt(st.nextToken());
            int id2 = Integer.parseInt(st.nextToken());

            if (parents[id1] == parents[id2]) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static void findConnection() {
        Arrays.sort(sorted);
        Node node = sorted[0];
        int endPoint = node.x2;
        for (int i = 1; i < N; i++) {
            Node nextNode = sorted[i];
            if (endPoint >= nextNode.x1) {
                union(node.id, nextNode.id);
            }

            endPoint = Math.max(endPoint, nextNode.x2);
            node = nextNode;
        }
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