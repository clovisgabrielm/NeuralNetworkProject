package br.ufal.ic;

/*
 * Classe que utiliza rede neural para reconhecer um retangulo
 * recebe um vetor de 12 entradas [retangulo 4x3]
 */
public class RectangleRecognizer implements Recognizer {
	
	public int[][] positions = new int[3][4];
	public int[][] weights = new int[3][4];
	public static int HEIGHT = 3;
	public static int WIDTH = 4;
	public static int limiter = 12;
	 
	
	public void setPositions(int[][] positions) {
		this.positions = positions;
	}
	
	/* Setting all the positions of the neuron weights vector for the first layer as 1
	 * to fill the rectangle
	 */
	private void setWeights() {
		for(int i = 0; i < HEIGHT; i++) {
			for(int j = 0; j < WIDTH; j++) {
				weights[i][j] = 1;
			}
		}
	}
	
	public boolean calculate() {
		setWeights();
		
		int a = 0;
		
		for(int i = 0; i < HEIGHT; i++) {
			for(int j = 0; j < WIDTH; j++) {
				a += positions[i][j]*weights[i][j];
			}
		}
		
		if (a == limiter) 
			return true;
		else 
			return false;
	}
	
	

}
