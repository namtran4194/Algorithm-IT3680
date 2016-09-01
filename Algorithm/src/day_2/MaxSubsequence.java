package day_2;

public class MaxSubsequence {
	public static void main(String[] args) {

	}

	public static long algo1(int[] a) {
		int n = a.length;
		long max = a[0];
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int s = 0;
				for (int k = i; k <= j; k++)
					s = s + a[k];
				max = max < s ? s : max;
			}
		}
		return max;
	}
}
