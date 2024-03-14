package problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class BJ16472 {

	static ArrayList<Count> alphabetList = new ArrayList<Count>();

	static class Count {
		char alphabet;
		int count;

		public Count(char alphabet, int count) {
			this.alphabet = alphabet;
			this.count = count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int max = Integer.parseInt(br.readLine());
		String line = br.readLine();

		char preChar = line.charAt(0);
		int count = 1;
		for (int i = 1; i < line.length(); i++) {
			if (preChar != line.charAt(i)) {
				alphabetList.add(new Count(preChar, count));
				preChar = line.charAt(i);
				count = 1;
			} else {
				count++;
			}
		}
		alphabetList.add(new Count(preChar, count));

		int result = 0;
		for (int i = 0; i < alphabetList.size(); i++) {
			HashSet<Character> tempSet = new HashSet<Character>();
			int tempCount = 0;
			for (int j = i; j < alphabetList.size(); j++) {
				tempSet.add(alphabetList.get(j).alphabet);
				if (tempSet.size() > max) {
					result = Math.max(result, tempCount);
					break;
				} else {
					tempCount += alphabetList.get(j).count;
					result = Math.max(result, tempCount);
				}
			}
		}

		System.out.println(result);

	}

}
