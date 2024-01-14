package datastructure;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {

	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<Integer>();

		queue.add(5);
		queue.add(2);
		queue.add(3);
		queue.add(7);
		queue.remove();
		queue.add(1);
		queue.add(4);
		queue.remove();

		System.out.print(queue);

	}

}
