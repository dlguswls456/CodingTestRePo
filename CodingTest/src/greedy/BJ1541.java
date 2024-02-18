package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1541 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringBuilder temp = new StringBuilder();
		int flag = 1;
		int result = 0;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '+' || line.charAt(i) == '-') {
				int num = Integer.parseInt(temp.toString());
				result += num * flag;
				temp.setLength(0);
				if (line.charAt(i) == '-') {
					flag = -1;
				}
			} else {
				temp.append(line.charAt(i));
			}
		}

		int num = Integer.parseInt(temp.toString());
		result += num * flag;
		System.out.println(result);

	}

}
