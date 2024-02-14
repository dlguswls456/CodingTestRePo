package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SWEA1218 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			int length = Integer.parseInt(br.readLine());
			String line = br.readLine();
			Stack<Character> stack = new Stack<Character>();
			int result = 1;
			for (int i = 0; i < length; i++) {
				char currentChar = line.charAt(i);
				if (currentChar == ')') {
					if (stack.pop() != '(') {
						result = 0;
						break;
					}
				} else if (currentChar == ']') {
					if (stack.pop() != '[') {
						result = 0;
						break;
					}

				} else if (currentChar == '}') {
					if (stack.pop() != '{') {
						result = 0;
						break;
					}

				} else if (currentChar == '>') {
					if (stack.pop() != '<') {
						result = 0;
						break;
					}

				} else {
					stack.add(currentChar);
				}
			}

			System.out.println("#" + t + " " + result);
		}
	}

}
