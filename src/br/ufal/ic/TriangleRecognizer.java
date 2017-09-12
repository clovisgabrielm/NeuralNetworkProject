package br.ufal.ic;

public class TriangleRecognizer implements Recognizer {

	protected int[][] positionsTriangle = new int[3][4];
	protected int[][] weights = new int[3][4];
	protected static int o_limiterValue = 6;
	protected static int f_limiterValue = 8;
	
	public TriangleRecognizer() {
		// TODO Auto-generated constructor stub
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				if(j < 3) {
					weights[i][j] = 1;
				}
				else weights[i][j] = 0;
			}
		}
	}
	
	public boolean calculate() {
		int sum = 0;
		int real_sum = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				sum += positionsTriangle[i][j]*weights[i][j];
				
				if((i == 0 && j == 0) || (i == 1 && j <= 1) || (i == 2 && j <= 2)) {
					real_sum += positionsTriangle[i][j]*weights[i][j];
				}
			}
		}
		
		if(real_sum == 6) {
			if (sum >= o_limiterValue && sum <= f_limiterValue) 
				return true;
			else 
				return false;
		}
		else return false;
		
	}

	public void setPositions(int[][] valuesForANN) {
		// TODO Auto-generated method stub
		positionsTriangle = valuesForANN;
	}
	
}
