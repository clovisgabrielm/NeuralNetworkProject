package br.ufal.ic;

public class PoligonRecognizer implements Recognizer {

	protected int[][] poligonPositions = new int[3][4];
	protected int[][] weights = new int[3][4];
	
	public PoligonRecognizer() {
		// TODO Auto-generated constructor stub
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				if (j < 3)
					weights[i][j] = 1;
				else
					weights[i][j] = 0;
				
			}
		}
	}
	
	public boolean calculate() {
		int sum = 0;
		int real_sqr_sum = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				sum += poligonPositions[i][j]*weights[i][j];
				
				if((i == 0 && j != 0 && j != 2) || (i > 0 && j < 3))
					real_sqr_sum += poligonPositions[i][j]*weights[i][j];
			}
		}
		
		System.out.println("== " + real_sqr_sum);
		if(real_sqr_sum == 7) { 
			if(sum <= 8)
				return true;
			else return false;
		}
		else return false;
	}

	public void setPositions(int[][] valuesForANN) {
		// TODO Auto-generated method stub
		poligonPositions = valuesForANN;
	}

}
