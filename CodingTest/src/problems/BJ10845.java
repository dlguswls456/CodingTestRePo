package problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ10845 {
	//ť
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new LinkedList<Integer>();
		int last = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			switch (order) {
			case "push": {
				int num = Integer.parseInt(st.nextToken());
				queue.add(num);
				last = num;
				break;
			}
			case "pop": {
				if (queue.peek() == null) {
					System.out.println(-1);
				} else {
					System.out.println(queue.poll());
				}
				break;
			}
			case "size": {
				System.out.println(queue.size());
				break;
			}
			case "empty": {
				if (queue.isEmpty()) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
				break;
			}
			case "front": {
				if (queue.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(queue.peek());
				}
				break;
			}
			case "back": {
				if (queue.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(last);
				}
				break;
			}
			}
		}
	}

}
