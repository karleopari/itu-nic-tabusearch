package kg.aibek.nic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application {
	double[] p; // Profit
	double pMax;
	double[][] r; // Resource consumption
	double rMin;
	double[] c; // The capacity constraint
	int tabuSize = 20; // The size of the memory of tabu list

	int epoch = 10000;

	// ---- Variables
	String bestSolution;
	List<String> tabuList;

	public boolean initialize(double[] p, double[][] r, double[] c) {
		this.p = p;
		this.r = r;
		this.c = c;

		// To set pMax
		pMax = p[0];
		for (double d : p) {
			if (d > pMax)
				pMax = d;
		}

		// To set rMin
		rMin = r[0][0];
		for (double[] rSub : r) {
			for (double d : rSub) {
				if (d < rMin)
					rMin = d;
			}
		}

		return false;
	}

	public boolean stopingCondition(int ep) {
		if (ep > epoch)
			return true;
		else
			return false;
	}

	public String solve() {
		String xStr = initialSolution();
		System.out.println("Initial solution "
				+ TabuSearch.f(TabuSearch.integerArray(xStr), p));

		bestSolution = xStr;
		tabuList = new ArrayList<String>();

		int ep = 0;
		while (!stopingCondition(++ep)) {
			List<String> neghbours = getNeighours(bestSolution);
			List<String> candidateList = new ArrayList<String>();

			for (String candidate : neghbours) {
				if (!tabuList.contains(candidate)) {
					candidateList.add(candidate);
				}
			}

			String bestCandidate = getBestCandidate(candidateList);

			if (bestCandidate != null
					&& TabuSearch.fitness(
							TabuSearch.integerArray(bestCandidate), p, r, c,
							pMax, rMin) > TabuSearch.fitness(
							TabuSearch.integerArray(bestSolution), p, r, c,
							pMax, rMin)) {
				bestSolution = bestCandidate;
				tabuList.add(bestSolution);

				// In list new items added to end and if remove first the oldest
				// would be removed
				while (tabuList.size() > tabuSize) {
					tabuList.remove(0);
				}
			}
		}
		return bestSolution;
	}

	private String initialSolution() {
		String randomSolution = "";
		Random rnd = new Random();

		for (int i = 0; i < p.length; i++) {
			randomSolution += rnd.nextInt(2);
		}

		return randomSolution;
	}

	private String getBestCandidate(List<String> candidates) {
		if (candidates == null || candidates.size() == 0)
			return null;

		String best = candidates.get(0);
		double bestFitness = TabuSearch.fitness(TabuSearch.integerArray(best),
				p, r, c, pMax, rMin);
		for (int i = 1; i < candidates.size(); i++) {
			String curr = candidates.get(i);
			double currFitness = TabuSearch.fitness(
					TabuSearch.integerArray(curr), p, r, c, pMax, rMin);

			if (bestFitness < currFitness) {
				best = curr;
				bestFitness = currFitness;
			}
		}

		return best;
	}

	private List<String> getNeighours(String solution) {

		List<String> neighbours = new ArrayList<String>();
		for (int i = 0; i < solution.length(); i++) {
			String neighbour = solution.substring(0, i)
					+ (solution.charAt(i) == '1' ? "0" : "1")
					+ solution.substring(i + 1);
			neighbours.add(neighbour);
		}

		return neighbours;
	}

}
