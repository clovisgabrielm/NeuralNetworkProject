package br.ufal.ic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GameScreen {

    private final JPanel gui = new JPanel(new BorderLayout(4, 4));
    private Location location = new Location();
    private JPanel board;
    private JButton[][] boardSquares = new JButton[9][16];
    private ButtonHandler handler;
    private StartButtonHandler startHandler;
    private boolean drawX = true;
    private JButton start = new JButton("Start to recognize");
       
    GameScreen() {
    	
    	
    	
        initializeGui();
    }
    
 // Classe interna
    public class StartButtonHandler implements ActionListener
    {
    	JButton btn;
    	
    	public StartButtonHandler(JButton btn) {
			this.btn = btn;
		}
    	
    	//TRATA EVENTO DO BOTÃO
    	public void actionPerformed(ActionEvent event)
    	{
    		btn.setEnabled(false);
    		drawX = false;
    		
    		location.startFindingRectangle();
    		
    		location.startFindingSquare();
    	}
    }
    
    // Classe interna para desenhar x
    public class ButtonHandler implements ActionListener
    {
    	JButton btn;
    	int ii;
    	int jj;
    	
    	public ButtonHandler(JButton btn, int ii, int jj) {
			this.btn = btn;
			this.ii = ii;
			this.jj = jj;
		}
    	
    	// Permite adicionar ou nao o X dependendo se é possivel ou nao desenhar
    	public void actionPerformed(ActionEvent event)
    	{
    		if(drawX) {			
    			ImageIcon icon = new ImageIcon(getClass().getResource("x.png"));
    			btn.setIcon(icon);   
    			location.thereIsAnX(ii, jj);
    		}
		
    	}
    }

    public final void initializeGui() {


        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        
        board = new JPanel(new GridLayout(9, 10));
        board.setBorder(new CompoundBorder(
                new EmptyBorder(8,8,8,8),
                new LineBorder(Color.BLACK)
                ));
        
        startHandler = new StartButtonHandler(start);
        start.addActionListener(startHandler);          
        
        // Set the BG to be white
        
        board.setBackground(Color.white);
        JPanel boardConstrain = new JPanel();
        boardConstrain.setLayout(new BoxLayout(boardConstrain, BoxLayout.PAGE_AXIS));
        boardConstrain.setBackground(Color.white);
        boardConstrain.add(board);
        boardConstrain.add(start);
        gui.add(boardConstrain);
        
        

        // create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        
        for (int ii = 0; ii < 9; ii++) {
            for (int jj = 0; jj < 15; jj++) {
                boardSquares[ii][jj] = new JButton();
                
                boardSquares[ii][jj].setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                boardSquares[ii][jj].setIcon(icon);
                
                boardSquares[ii][jj].setBackground(new Color(255,250,250));
                
                handler = new ButtonHandler(boardSquares[ii][jj], ii, jj);
                
                boardSquares[ii][jj].addActionListener(handler);
                
                location.setBoardSquare(ii, jj, boardSquares[ii][jj]);
            }
        }

        /*
         * fill the chess board
         */
       
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 9; ii++) {
            for (int jj = 0; jj < 15; jj++) {
            	                
            	//if(jj == 9) chessBoard.add(new JLabel(""));
            	
            	board.add(location.getBoardSquare(ii, jj));
            }
        }
        
        
    }

    public final JComponent getGui() {
        return gui;
    }

    /**
     * Initializes the icons of the initial chess board piece places
     */

    public static void main(String[] args) {
        Runnable r = new Runnable() {

            
            public void run() {
                GameScreen gs = new GameScreen();

                JFrame f = new JFrame("Shape Recognizer");
                f.add(gs.getGui());
                // Ensures JVM closes after frame(s) closed and
                // all non-daemon threads are finished
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                // See http://stackoverflow.com/a/7143398/418556 for demo.
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        // Swing GUIs should be created and updated on the EDT
        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency
        SwingUtilities.invokeLater(r);
    }
}