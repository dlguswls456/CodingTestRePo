package binarysearch;

public class BinarySearchExample {

	static int[] numbers = { 0, 2, 4, 6, 8, 10, 12, 14, 16 };

	public static void main(String[] args) {
		int targetNum = 12;
		int targetIdx = whileBinarySearch(targetNum, 0, numbers.length - 1);

		System.out.print(targetIdx);
	}

	public static int whileBinarySearch(int targetNum, int start, int end) {
		while (start <= end) {
			int mid = (start + end) / 2;

			if (numbers[mid] == targetNum) {
				return mid;
			}

			if (numbers[mid] > targetNum) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}

		return -1;
	}

	public static int recursiveBinarySearch(int targetNum, int start, int end) {
		if (start > end)
			return -1;

		int mid = (start + end) / 2;
		if (numbers[mid] == targetNum) {
			return mid;
		}

		if (numbers[mid] > targetNum) {
			end = mid - 1;
		} else {
			start = mid + 1;
		}

		return recursiveBinarySearch(targetNum, start, end);
	}
}
