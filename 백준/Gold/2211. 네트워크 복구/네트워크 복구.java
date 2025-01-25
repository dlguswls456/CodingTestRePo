import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static PriorityQueue<Node> minPath = new PriorityQueue<>();
    static ArrayList<ArrayList<Node>> edges = new ArrayList<>();
    static int[][] distance;
    static boolean[] visited;

    static class Node implements Comparable<Node> {
        int node;
        int weight;
        int cntEdge;

        Node(int node, int weight, int cntEdge) {
            this.node = node;
            this.weight = weight;
            this.cntEdge = cntEdge;
        }

        @Override
        public int compareTo(Node o) {
            if (this.weight == o.weight) {
                return this.cntEdge - o.cntEdge;
            } else {
                return this.weight - o.weight;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        distance = new int[N + 1][2];
        for (int i = 0; i <= N; i++) {
            edges.add(new ArrayList<>());
            distance[i][0] = Integer.MAX_VALUE;
        }

        visited = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges.get(node1).add(new Node(node2, w, 1));
            edges.get(node2).add(new Node(node1, w, 1));
        }


        findMinPath(1);

        HashSet<String> result = new HashSet<>();
        for (int i = 2; i <= N; i++) {
            int connected = distance[i][1];
            int min = Math.min(i, connected);
            int max = Math.max(i, connected);

            result.add(min + " " + max);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append("\n");
        for (String edge : result) {
            sb.append(edge).append("\n");
        }
        System.out.println(sb);
    }

    public static void findMinPath(int startNode) {
        minPath.add(new Node(startNode, 0, 0));
        distance[startNode][0] = 0;

        while (!minPath.isEmpty()) {
            Node node = minPath.poll();
            visited[node.node] = true;

            for (Node nextNode : edges.get(node.node)) {
                if (visited[nextNode.node]) {
                    continue;
                }

                if (distance[nextNode.node][0] > distance[node.node][0] + nextNode.weight) {
                    distance[nextNode.node][0] = distance[node.node][0] + nextNode.weight;
                    distance[nextNode.node][1] = node.node;
                    minPath.add(new Node(
                            nextNode.node,
                            distance[nextNode.node][0],
                            distance[nextNode.node][0] + 1));
                }
            }
        }
    }
}