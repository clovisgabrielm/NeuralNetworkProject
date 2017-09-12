package br.ufal.ic;

public interface Recognizer {
	public boolean calculate();

	public void setPositions(int[][] valuesForANN);
}
