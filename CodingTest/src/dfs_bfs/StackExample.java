package dfs_bfs;

import java.util.Stack;

public class StackExample {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();

		stack.push(5);
		stack.push(2);
		stack.push(3);
		stack.push(7);
		stack.pop();
		stack.push(1);
		stack.push(4);
		stack.pop();

		System.out.print(stack);

	}

}