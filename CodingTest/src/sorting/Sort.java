package sorting;

public class Sort {

	public static void main(String[] args) {

	}

	public static void countSort() {
		// ��� ����
		// O(N+K)
		int[] numbers = { 7, 5, 9, 0, 3, 1, 6, 2, 4, 8, 0, 5, 2 };
		int maxNumber = 0;
		for (int number : numbers) {
			if (number > maxNumber) {
				maxNumber = number;
			}
		}

		int[] countNumbers = new int[maxNumber + 1];
		for (int number : numbers) {
			countNumbers[number]++;
		}

		System.out.print("Count Sorting: ");
		for (int i = 0; i < countNumbers.length; i++) {
			if (countNumbers[i] > 0) {
				for (int j = 0; j < countNumbers[i]; j++) {
					System.out.print(i + " ");
				}
			}
		}
		System.out.println();

	}

	public static void quickSort() {
		// �� ����
		// O(NlogN)
		int[] numbers = { 6, 2, 4, 5, 7, 9, 0, 3, 1, 8 };

		quick(numbers, 0, numbers.length);

		System.out.print("Quick Sorting: ");
		for (int number : numbers) {
			System.out.print(number + " ");
		}
		System.out.println();
	}

	public static void quick(int[] numbers, int startIndex, int endIndex) {
		if (endIndex - startIndex <= 1)
			return;

		int pivot = numbers[startIndex];
		int lowerIndex = startIndex;
		int upperIndex = startIndex;
		for (int i = startIndex + 1; i < endIndex; i++) {
			if (pivot < numbers[i]) {
				upperIndex = i;
				break;
			}
		}

		for (int i = endIndex - 1; i > startIndex; i--) {
			if (pivot > numbers[i]) {
				lowerIndex = i;
				break;
			}
		}

		if (upperIndex < lowerIndex) {
			int temp = numbers[upperIndex];
			numbers[upperIndex] = numbers[lowerIndex];
			numbers[lowerIndex] = temp;

			quick(numbers, startIndex, endIndex);
		} else {
			int temp = numbers[startIndex];
			numbers[startIndex] = numbers[lowerIndex];
			numbers[lowerIndex] = temp;

			quick(numbers, startIndex, lowerIndex);
			quick(numbers, lowerIndex + 1, endIndex);
		}

	}

	public static void insertionSort() {
		// ���� ����
		// O(N^2) ���� ���� ��� O(N)�� ����
		int[] numbers = { 7, 5, 9, 0, 3, 1, 6, 2, 4, 8 };

		// i -> ���� �˻��� ������
		for (int i = 1; i < numbers.length; i++) {
			// ������ ���ĵ� �κп��� ���ӵ� �� �����ͳ��� �� �� ��ȯ
			for (int j = i; j > 0; j--) {
				if (numbers[j] < numbers[j - 1]) {
					int temp = numbers[j];
					numbers[j] = numbers[j - 1];
					numbers[j - 1] = temp;
				} else {
					break;
				}
			}
		}

		System.out.print("Insertion Sorting: ");
		for (int number : numbers) {
			System.out.print(number + " ");
		}
		System.out.println();
	}

	public static void selectionSort() {
		// ���� ����
		// O(N^2)
		int[] numbers = { 7, 5, 9, 0, 3, 1, 6, 2, 4, 8 };

		for (int i = 0; i < numbers.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[j] < numbers[minIndex]) {
					minIndex = j;
				}
			}
			int temp = numbers[i];
			numbers[i] = numbers[minIndex];
			numbers[minIndex] = temp;
		}

		System.out.print("Selection Sorting: ");
		for (int number : numbers) {
			System.out.print(number + " ");
		}
		System.out.println();
	}
}
