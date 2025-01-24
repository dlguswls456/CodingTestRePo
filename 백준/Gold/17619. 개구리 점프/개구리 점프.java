import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, Q;
    static int[] parents;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

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
            return this.x1 - o.x1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;

            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.add(new Node(i, x1, x2, y));
        }

        findConnection();
        for (int elem : parents) {
            find(elem);
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int id1 = Integer.parseInt(st.nextToken());
            int id2 = Integer.parseInt(st.nextToken());

            if (parents[id1] == parents[id2]) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    public static void findConnection() {
        Node node = pq.poll();
        while (!pq.isEmpty()) {
            Node nextNode = pq.poll();
            if (node.x2 >= nextNode.x1 && node.y != nextNode.y) {
                union(node.id, nextNode.id);
            }

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