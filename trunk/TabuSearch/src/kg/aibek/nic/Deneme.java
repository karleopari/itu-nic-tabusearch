package kg.aibek.nic;

import java.util.Scanner;

public class Deneme {
	public static void main(String[] args) {
		String str = " 6 10 3800" + " 100 600 1200 2400 500 2000"
				+ " 8 12 13 64 22 41" + " 8 12 13 75 22 41" + " 3 6 4 18 6 4"
				+ " 5 10 8 32 6 12" + " 5 13 8 42 6 20" + " 5 13 8 48 6 20"
				+ " 0 0 0 0 8 0" + " 3 0 4 0 8 0" + " 3 2 4 0 8 4"
				+ " 3 2 4 8 8 4" + " 80 96 20 36 44 48 10 18 22 24";
		String str2 = "011001";
		Scanner input = new Scanner(str);

		int numberOfVariables = input.nextInt();
		int numberOfConstraints = input.nextInt();
		input.nextDouble();

		double[] p = new double[numberOfVariables];
		double[] c = new double[numberOfConstraints];
		double[][] r = new double[numberOfConstraints][numberOfVariables];

		for (int n = 0; n < numberOfVariables; n++) {
			p[n] = input.nextDouble();
		}

		for (int m = 0; m < numberOfConstraints; m++) {
			for (int n = 0; n < numberOfVariables; n++) {
				r[m][n] = input.nextDouble();
			}
		}

		for (int m = 0; m < numberOfConstraints; m++) {
			c[m] = input.nextDouble();
		}
		//
		// Application app = new Application();
		// app.initialize(p, r, c);
		//
		// System.out.println(app.solve());

		System.out.println(TabuSearch.f(TabuSearch.integerArray(str2), p));
		System.out.println(TabuSearch.fitness(TabuSearch.integerArray(str2), p,
				r, c, 2400, 0.000000000001));

	}
}
