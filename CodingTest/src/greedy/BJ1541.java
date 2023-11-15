package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ1541 {

	static Queue<String> queue = new LinkedList<String>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String num = "";
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) != '-' && line.charAt(i) != '+') {
				num = num.concat(String.valueOf(line.charAt(i)));
			}
			if (line.charAt(i) == '+' || line.charAt(i) == '-') {
				queue.add(num);
				num = "";
				queue.add(String.valueOf(line.charAt(i)));
			}
		}
		queue.add(num);

		int result = 0;
		while (!queue.isEmpty()) {
			String now = queue.remove();
			if (now.equals("-")) {
				int temp = 0;
				while (!queue.isEmpty()) {
					if (!queue.peek().equals("-")) {
						String now2 = queue.remove();
						if (!now2.equals("+")) {
							temp += Integer.parseInt(now2);
						}
					} else {
						break;
					}
				}
				result -= temp;
				continue;
			}
			if (now.equals("+")) {
				continue;
			}

			result += Integer.parseInt(now);
		}
		System.out.print(result);
	}

}
