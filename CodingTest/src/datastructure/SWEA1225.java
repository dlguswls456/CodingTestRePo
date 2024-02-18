package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1225 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			Integer.parseInt(br.readLine());
			Queue<Integer> queue = new LinkedList<Integer>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				queue.add(Integer.parseInt(st.nextToken()));
			}

			int flag = 1;
			while (true) {
				int currentNum = queue.poll();
				currentNum -= flag;
				if (currentNum <= 0) {
					queue.add(0);
					break;
				}
				queue.add(currentNum);
				flag++;
				if (flag == 6) {
					flag = 1;
				}

			}

			System.out.print("#" + t + " ");
			while (!queue.isEmpty()) {
				System.out.print(queue.poll() + " ");
			}
			System.out.println();
		}

	}

}
