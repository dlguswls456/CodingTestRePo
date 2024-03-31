package shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ13549 {

    static int N, K;
    static int[] x;
    static PriorityQueue<Point> queue = new PriorityQueue<Point>();
    static int[] dx = { 0, -1, 1 };

    static class Point implements Comparable<Point> {
        int point;
        int time;

        public Point(int point, int time) {
            this.point = point;
            this.time = time;
        }

        @Override
        public int compareTo(Point o) {
            return this.time - o.time;
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        x = new int[100001];
        for (int i = 0; i < x.length; i++) {
            x[i] = Integer.MAX_VALUE;
        }

        queue.add(new Point(N, 0));

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            x[point.point] = point.time;
            if (point.point == K) {
                break;
            }
            dx[0] = point.point;
            for (int i = 0; i < 3; i++) {
                int newPoint = point.point + dx[i];

                if (0 > newPoint || newPoint > 100000) {
                    continue;
                }
                
                if(x[newPoint]>point.time) { // 이게 없으면 메모리 초과
                	queue.add(new Point(newPoint, point.time + (i + 1) / 2));                	
                }
            }
        }
        System.out.println(x[K]);
    }

}