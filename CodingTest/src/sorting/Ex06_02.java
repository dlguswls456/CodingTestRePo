package sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Ex06_02 {
	static class Person implements Comparable<Person> {
		String name;
		int score;

		Person(String name, int score) {
			this.name = name;
			this.score = score;
		}

		@Override
		public int compareTo(Person o) {
			// TODO Auto-generated method stub
			return this.score - o.score;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		ArrayList<Person> people = new ArrayList<Person>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int score = Integer.parseInt(st.nextToken());

			people.add(new Person(name, score));
		}

		people.sort(null);

		for (Person person : people) {
			System.out.print(person.name + " ");
		}
	}
}
