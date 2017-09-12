package br.ufal.ic;

public class SquareRecognizer implements Recognizer {

	protected int[][] positionsOfTheSquare = new int[3][4];
	protected int[][] weights = new int[3][4];
	protected static int inf_limiter = 9;
	protected static int sup_limiter = 12;
	
	SquareRecognizer() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				// we consider 4x3 to avoid squares inside a rectangle
				weights[i][j] = 1;
			}
		}
	}
	
	public boolean calculate() {
		
		int sum = 0;
		int real_sqr_sum = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				sum += positionsOfTheSquare[i][j]*weights[i][j];
				
				if (j != 3) real_sqr_sum += positionsOfTheSquare[i][j]*weights[i][j];
			}
		}
		
		if(real_sqr_sum == 9) {
			if (sum >= inf_limiter && sum < sup_limiter) 
				return true;
			else 
				return false;
		}
		else return false;
		
	}
	
	
	public void setPositions(int[][] valuesForANN) {
		// TODO Auto-generated method stub
		positionsOfTheSquare = valuesForANN;
	}

}
