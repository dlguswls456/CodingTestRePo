package problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA19003 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			ArrayList<String> words = new ArrayList<String>();
			ArrayList<String> palindrome = new ArrayList<String>();
			boolean isMoreThanOne = false;
			for (int i = 0; i < N; i++) {
				String word = br.readLine();
				if (isPalindrome(word) && !isMoreThanOne) {
					palindrome.add(word);
					isMoreThanOne = true;
					continue;
				}

				String reverseWord = new StringBuffer(word).reverse().toString();
				if (words.contains(reverseWord)) {
					palindrome.add(word);
					palindrome.add(reverseWord);
				} else {
					words.add(word);
				}

			}

			bw.write("#" + t + " " + palindrome.size() * M);
			bw.newLine();
		}
		bw.close();
	}

	public static boolean isPalindrome(String word) {
		for (int i = 0; i < word.length() / 2; i++) {
			if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}
}
