package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1225 {

	static Queue<Integer> numbers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int t = 1; t <= 10; t++) {
			int T = Integer.parseInt(br.readLine());
			numbers = new LinkedList<Integer>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				numbers.add(Integer.parseInt(st.nextToken()));
			}

			int alpha = 1;
			while (true) {
				int currentNum = numbers.remove() - alpha;
				if (currentNum <= 0) {
					numbers.add(0);
					break;
				}
				numbers.add(currentNum);
				alpha++;
				if (alpha > 5) {
					alpha = 1;
				}
			}
			bw.write("#" + T + " ");
			for (int i : numbers) {
				bw.write(i + " ");
			}
			bw.newLine();
		}
		bw.close();
	}
}
