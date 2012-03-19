package kg.aibek.nic;

import java.io.File;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		int numberOfTest;
		int numberOfVariables;
		int numberOfConstraints;
		double optimalSolution;

		double[] p; // Profit
		double[][] r; // Resource consumption
		double[] c; // The capacity constraint

		double[] sol;
		Application app = new Application();

		try {
			Scanner input = new Scanner(
					new File(
							"C:/Documents And Settings/aosmonov/Desktop/sabak/mknap1.txt"));

			numberOfTest = input.nextInt();
			System.err.println("------ Number of Tests = " + numberOfTest
					+ " ------");

			sol = new double[numberOfTest];

			for (int i = 0; i < numberOfTest; i++) {
				numberOfVariables = input.nextInt();
				numberOfConstraints = input.nextInt();
				optimalSolution = input.nextDouble();

				System.err.println("--- Test # " + (i + 1) + ". m = "
						+ numberOfVariables + ", n = " + numberOfConstraints
						+ " ---");

				p = new double[numberOfVariables];
				c = new double[numberOfConstraints];
				r = new double[numberOfConstraints][numberOfVariables];

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

				app.initialize(p, r, c);

				String solution = app.solve();
				sol[i] = TabuSearch.f(TabuSearch.integerArray(solution), p);
				System.err.println("Solution for test " + (i + 1) + " is "
						+ solution + " weight: " + sol[i] + ". Optimal is "
						+ optimalSolution);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
