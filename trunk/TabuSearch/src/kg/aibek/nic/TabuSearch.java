package kg.aibek.nic;

public class TabuSearch {

	public static double fitness(int[] x, double[] p, double[][] r, double[] c,
			double pMax, double rMin) {
		return f(x, p) - penalty(x, r, c, pMax, rMin);
	}

	public static double f(int[] x, double[] p) {
		double ret = 0.0;
		for (int i = 0; i < x.length; i++) {
			ret += p[i] * x[i];
		}
		return ret;
	}

	public static double penalty(int[] x, double[][] r, double[] c,
			double pMax, double rMin) {
		double maxCV = Double.MIN_VALUE;

		for (int j = 0; j < r[0].length; j++) {
			maxCV = Math.max(maxCV, CV(x, r, c, j));
		}

		if (rMin == 0.0)
			return 0.0;

		return ((pMax + 1) / rMin) * maxCV;
	}

	public static double CV(int[] x, double[][] r, double[] c, int j) {
		double max = 0.0;

		for (int i = 0; i < x.length; i++) {
			max = Math.max(max, r[i][j] * x[i] - c[j]);
		}

		return max;
	}

	public static int[] integerArray(String str) {
		if (str.length() == 0)
			return new int[] {};

		int[] x = new int[str.length()];

		int i = 0;
		for (char ch : str.toCharArray()) {
			x[i++] = ch == '0' ? 0 : 1;
		}

		return x;
	}
}
