package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ1339 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}

		int[] number = new int[26];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				number[words[i].charAt(j) - 'A'] += Math.pow(10, words[i].length() - j - 1);
			}
		}

		Arrays.sort(number);
		int endIdx = number.length - 1;
		int flag = 9;
		int result = 0;
		for (int i = endIdx; i > endIdx - 10; i--) {
			result += number[i] * flag;
			flag--;
		}
		
		System.out.println(result);

	}

}
