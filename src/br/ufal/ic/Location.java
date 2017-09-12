package br.ufal.ic;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Location extends JFrame {
	private JButton[][] boardSquares = new JButton[9][16];
	private Recognizer recognizer;
	private int[][] thereIsAnX = new int[9][16];
	private int[][] valuesForANN = new int[3][4];
	private ImageIcon oldIcon;
	private ImageIcon oldShapeIcon;
	private static int TIME = 10;
	
	private boolean rectFinished = false;
	
	public JButton[][] getBoardSquares() {
		return boardSquares;
	}
	
	public Location() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 16; j++) {
				thereIsAnX[i][j] = 0;
			}
		}
		
	}
		
	private void setOldIcon(ImageIcon oldIcon) {
		this.oldIcon = oldIcon;
	}
	
	private ImageIcon getOldIcon() {
		return oldIcon;
	}
	
	private void setOldShapeIcon(ImageIcon oldRectIcon) {
		this.oldShapeIcon = oldRectIcon;
	}
	
	private ImageIcon getOldShapeIcon() {
		return oldShapeIcon;
	}
	
	public class MoveToFindTriangle extends Thread {
		
		
	}
	
	
	
	public void thereIsAnX(int ii, int jj) {
		
		thereIsAnX[ii][jj] = 1;
	}
	
	private int rectangle() {
		int ii = 0;
		int jj = 0;
	
		setOldShapeIcon((ImageIcon) boardSquares[ii][jj].getIcon());
		
		ImageIcon icon = new ImageIcon(getClass().getResource("rect-robot.png"));
		boardSquares[ii][jj].setIcon(icon);
		
		recognizer = new RectangleRecognizer();
					
		while(true) {
				
				//values for counting the dimension of the rectangle
				int height = 0;
				int width = 0;
								for(int i = ii; i < ii+3; i++) {
					for(int j = jj; j < jj+4; j++) {
						
						//Data for recognition
						
						valuesForANN[height][width] = thereIsAnX[i][j];
								
						
						//GUI
						try {
							Thread.sleep(TIME);
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						//System.out.println("Rect: " + i + " " + j);
						if(j != jj) 
							boardSquares[i][j-1].setIcon(getOldShapeIcon());
						else if(i != ii && j == jj)
							boardSquares[i-1][j+3].setIcon(getOldShapeIcon());
						else
							boardSquares[i][j].setIcon(getOldShapeIcon());
						
						
						
						setOldShapeIcon((ImageIcon) boardSquares[i][j].getIcon());
						
						
						boardSquares[i][j].setIcon(icon);
						
						if(j == jj+3 && i == ii+2) {
							try {
								Thread.sleep(TIME);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							boardSquares[i][j].setIcon(getOldShapeIcon());
							
							
						}
						
						//width increases one
						width++;
					}
					//height increases one and width is zero
					height++;
					width = 0;
				}
				
				//recognize the shape
				recognizer.setPositions(valuesForANN);
				boolean isRectangle = recognizer.calculate();
				
//				if(isRectangle)
//					System.out.println("é um retângulo!!");
//				else 
//					System.out.println("não é retangulo!");
//				
//				System.out.println("## NEXT SEARCH ##");
				
				if(ii < 6) {
					ii++;
					setOldShapeIcon((ImageIcon) boardSquares[ii][jj].getIcon());
				}
				else if(ii < 7 && jj < 11) {
					jj++;
					ii = 0;
					setOldShapeIcon((ImageIcon) boardSquares[ii][jj].getIcon());
				}
				else break;
					
		}
		
		//System.out.println("Done!");
		return 1;
	}
	
	public class MoveToFindRectangle extends Thread {
		public void run() {
			//int rect = rectangle();
			
			//int sqr = square();
			
			//int tri = triangle();
			
			int pol = poligon();
		}
	}
	
	private int poligon() {
		int ii = 0;
		int jj = 0;
		
		setOldShapeIcon((ImageIcon) boardSquares[ii][jj].getIcon());
		
		ImageIcon icon = new ImageIcon(getClass().getResource("robot-p.png"));
		boardSquares[ii][jj].setIcon(icon);
		
		recognizer = new PoligonRecognizer();
					
		while(true) {
				
				//values for counting the dimension of the rectangle
				int height = 0;
				int width = 0;
				
				for(int i = ii; i < ii+3; i++) {
					for(int j = jj; j < jj+4; j++) {
					
						if(jj != 12) {
							//Data for recognition
							
							valuesForANN[height][width] = thereIsAnX[i][j];
									
							
							//GUI
							try {
								Thread.sleep(TIME);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							if(j != jj) 
								boardSquares[i][j-1].setIcon(getOldShapeIcon());
							else if(i != ii && j == jj)
								boardSquares[i-1][j+3].setIcon(getOldShapeIcon());
							else
								boardSquares[i][j].setIcon(getOldShapeIcon());
							
							
							
							setOldShapeIcon((ImageIcon) boardSquares[i][j].getIcon());
							
							
							boardSquares[i][j].setIcon(icon);
							
							if(j == jj+3 && i == ii+2) {
								try {
									Thread.sleep(TIME);
									
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								boardSquares[i][j].setIcon(getOldShapeIcon());
								
								
							}
							
						}
						else if (j < jj+3) {
							valuesForANN[height][width] = thereIsAnX[i][j];
									
							
							//GUI
							try {
								Thread.sleep(TIME);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							if(j != jj) 
								boardSquares[i][j-1].setIcon(getOldShapeIcon());
							else if(i != ii && j == jj)
								boardSquares[i-1][j+2].setIcon(getOldShapeIcon());
							else
								boardSquares[i][j].setIcon(getOldShapeIcon());
							
							
							
							setOldShapeIcon((ImageIcon) boardSquares[i][j].getIcon());
							
							
							boardSquares[i][j].setIcon(icon);
							
							if(j == jj+2 && i == ii+2) {
								try {
									Thread.sleep(TIME);
									
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								boardSquares[i][j].setIcon(getOldShapeIcon());
								
								
							}
							
						}
						else {
							valuesForANN[height][width] = 0;
						}
						
						//width increases one
						width++;
					}
					//height increases one and width is zero
					height++;
					width = 0;
					
				}
				
				//recognize the shape
				recognizer.setPositions(valuesForANN);
				boolean isPoligon = recognizer.calculate();
				
				if(isPoligon)
					System.out.println("é um poligono!!");
				else 
					System.out.println("não é um poligono!");
				
				System.out.println("## NEXT SEARCH ##");
				
				if(ii < 6) {
					ii++;
					setOldShapeIcon((ImageIcon) boardSquares[ii][jj].getIcon());
				}
				else if(ii < 7 && jj < 12) {
					jj++;
					ii = 0;
					setOldShapeIcon((ImageIcon) boardSquares[ii][jj].getIcon());
				}
				else break;
					
		}
		
		//System.out.println("Done!");
		return 1;
	}
	
	private int triangle() {
		int ii = 0;
		int jj = 0;
		
		setOldShapeIcon((ImageIcon) boardSquares[ii][jj].getIcon());
		
		ImageIcon icon = new ImageIcon(getClass().getResource("robot.png"));
		boardSquares[ii][jj].setIcon(icon);
		
		recognizer = new TriangleRecognizer();
					
		while(true) {
				
				//values for counting the dimension of the rectangle
				int height = 0;
				int width = 0;
				
				for(int i = ii; i < ii+3; i++) {
					for(int j = jj; j < jj+4; j++) {
						
						//Data for recognition
						if(jj != 12) {
							//Data for recognition
							
							valuesForANN[height][width] = thereIsAnX[i][j];
									
							
							//GUI
							try {
								Thread.sleep(TIME);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							if(j != jj) 
								boardSquares[i][j-1].setIcon(getOldShapeIcon());
							else if(i != ii && j == jj)
								boardSquares[i-1][j+3].setIcon(getOldShapeIcon());
							else
								boardSquares[i][j].setIcon(getOldShapeIcon());
							
							
							
							setOldShapeIcon((ImageIcon) boardSquares[i][j].getIcon());
							
							
							boardSquares[i][j].setIcon(icon);
							
							if(j == jj+3 && i == ii+2) {
								try {
									Thread.sleep(TIME);
									
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								boardSquares[i][j].setIcon(getOldShapeIcon());
								
								
							}
							
						}
						else if (j < jj+3) {
							valuesForANN[height][width] = thereIsAnX[i][j];
									
							
							//GUI
							try {
								Thread.sleep(TIME);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							if(j != jj) 
								boardSquares[i][j-1].setIcon(getOldShapeIcon());
							else if(i != ii && j == jj)
								boardSquares[i-1][j+2].setIcon(getOldShapeIcon());
							else
								boardSquares[i][j].setIcon(getOldShapeIcon());
							
							
							
							setOldShapeIcon((ImageIcon) boardSquares[i][j].getIcon());
							
							
							boardSquares[i][j].setIcon(icon);
							
							if(j == jj+2 && i == ii+2) {
								try {
									Thread.sleep(TIME);
									
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								boardSquares[i][j].setIcon(getOldShapeIcon());
								
								
							}
							
						}
						else {
							valuesForANN[height][width] = 0;
						}
						
						//width increases one
						width++;
					}
					//height increases one and width is zero
					height++;
					width = 0;
				}
				
				//recognize the shape
				recognizer.setPositions(valuesForANN);
				boolean isTriangle = recognizer.calculate();
				
//				if(isTriangle)
//					System.out.println("é um triangulo!!");
//				else 
//					System.out.println("não é um triangulo!");
				
				
				if(ii < 6) {
					ii++;
					setOldShapeIcon((ImageIcon) boardSquares[ii][jj].getIcon());
				}
				else if(ii < 7 && jj < 12) {
					jj++;
					ii = 0;
					setOldShapeIcon((ImageIcon) boardSquares[ii][jj].getIcon());
				}
				else break;
					
		}
		
		//System.out.println("Done!");
		return 1;
	}
	
	private int square() {
		int ii = 0;
		int jj = 0;
		
		setOldShapeIcon((ImageIcon) boardSquares[ii][jj].getIcon());
		
		ImageIcon icon = new ImageIcon(getClass().getResource("robot-square.png"));
		boardSquares[ii][jj].setIcon(icon);
		
		recognizer = new SquareRecognizer();
					
		while(true) {
				
				//values for counting the dimension of the rectangle
				int height = 0;
				int width = 0;
				
				for(int i = ii; i < ii+3; i++) {
					for(int j = jj; j < jj+4; j++) {
					
						if(jj != 12) {
							//Data for recognition
							
							valuesForANN[height][width] = thereIsAnX[i][j];
									
							
							//GUI
							try {
								Thread.sleep(TIME);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							if(j != jj) 
								boardSquares[i][j-1].setIcon(getOldShapeIcon());
							else if(i != ii && j == jj)
								boardSquares[i-1][j+3].setIcon(getOldShapeIcon());
							else
								boardSquares[i][j].setIcon(getOldShapeIcon());
							
							
							
							setOldShapeIcon((ImageIcon) boardSquares[i][j].getIcon());
							
							
							boardSquares[i][j].setIcon(icon);
							
							if(j == jj+3 && i == ii+2) {
								try {
									Thread.sleep(TIME);
									
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								boardSquares[i][j].setIcon(getOldShapeIcon());
								
								
							}
							
						}
						else if (j < jj+3) {
							valuesForANN[height][width] = thereIsAnX[i][j];
									
							
							//GUI
							try {
								Thread.sleep(TIME);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							if(j != jj) 
								boardSquares[i][j-1].setIcon(getOldShapeIcon());
							else if(i != ii && j == jj)
								boardSquares[i-1][j+2].setIcon(getOldShapeIcon());
							else
								boardSquares[i][j].setIcon(getOldShapeIcon());
							
							
							
							setOldShapeIcon((ImageIcon) boardSquares[i][j].getIcon());
							
							
							boardSquares[i][j].setIcon(icon);
							
							if(j == jj+2 && i == ii+2) {
								try {
									Thread.sleep(TIME);
									
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								boardSquares[i][j].setIcon(getOldShapeIcon());
								
								
							}
							
						}
						else {
							valuesForANN[height][width] = 0;
						}
						
						//width increases one
						width++;
					}
					//height increases one and width is zero
					height++;
					width = 0;
					
				}
				
				//recognize the shape
				recognizer.setPositions(valuesForANN);
				boolean isRectangle = recognizer.calculate();
				
//				if(isRectangle)
//					System.out.println("é um quadrado!!");
//				else 
//					System.out.println("não é um quadrado!");
//				
//				System.out.println("## NEXT SEARCH ##");
				
				if(ii < 6) {
					ii++;
					setOldShapeIcon((ImageIcon) boardSquares[ii][jj].getIcon());
				}
				else if(ii < 7 && jj < 12) {
					jj++;
					ii = 0;
					setOldShapeIcon((ImageIcon) boardSquares[ii][jj].getIcon());
				}
				else break;
					
		}
		
		//System.out.println("Done!");
		return 1;
	}
	
	public class MoveToFindSquare extends Thread {
		public void run() {
			
		}
	}
	
	public void startFindingTriangle() {
          
		new MoveToFindTriangle().start();
		
	}
	

	public void startFindingRectangle() {
		new MoveToFindRectangle().start();
		
	}
	
	public void startFindingSquare() {
		new MoveToFindSquare().start();
		
	}
	
	public void setBoardSquare(int ii, int jj, JButton btn) {
		boardSquares[ii][jj] = btn;
	}
	
	public JButton getBoardSquare(int ii, int jj) {
		return boardSquares[ii][jj];
	}
}
