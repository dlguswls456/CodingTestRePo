package shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA1263 {

    static int N;
    static int[][] graph;
    static boolean[] isVisited;
    static int[][] distance;

    static class Node implements Comparable<Node> {
        int node;
        int distance;

        Node(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            graph = new int[N + 1][N + 1];
            isVisited = new boolean[N + 1];
            distance = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }

            for (int i = 1; i <= N; i++) {
                findShortestDist(i);
            }
            
            int minSum =Integer.MAX_VALUE;
            for (int i = 1; i <= N; i++) {
                int sum = 0;
                for (int j = 1; j <= N; j++) {
                    if(distance[i][j] == Integer.MAX_VALUE)
                        continue;
                    
                    sum+=distance[i][j];
                }
                
                if(minSum>sum) {
                    minSum = sum;
                }
            }
            
            
            System.out.printf("#%d %d\n", t, minSum);
        }

    }

    public static void findShortestDist(int startNode) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(startNode, 0));
        distance[startNode][startNode] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (curNode.distance > distance[startNode][curNode.node]) {
                continue;
            }

            int[] tempArr = graph[curNode.node];
            for (int i = 1; i <= N; i++) {
                if (tempArr[i] == 0)
                    continue;

                if (curNode.distance + 1 < distance[startNode][i]) {
                    distance[startNode][i] = curNode.distance + 1;
                    pq.add(new Node(i, distance[startNode][i]));
                }
            }

        }

    }

}
