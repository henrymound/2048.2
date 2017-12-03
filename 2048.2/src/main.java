// Henry Mound and Sam Naumann
// Section B

// 2048.2

import java.applet.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.util.*; 
import javax.swing.JComponent;

public class main extends Applet implements KeyListener{//implements ActionListener implements KeyListener
	
	static final int BOARD_SIZE = 500;
	static final int APPLET_SIZE = 650;
	static Board mainBoard; 
	
	public void init() {
		mainBoard = new Board();
		Panel boardPanel = new Panel();
		BorderLayout mainLayout = new BorderLayout();
		setLayout(mainLayout);
		boardPanel.setSize(BOARD_SIZE, BOARD_SIZE);
		boardPanel.add(mainBoard);
		add(boardPanel, BorderLayout.CENTER);
		Panel titlePanel = new Panel();
		
		
		Label titleLabel = new Label("2048.2", Label.CENTER);
		titleLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
		titleLabel.setForeground(Color.WHITE);
		
		titleLabel.setForeground(Color.WHITE);
		titlePanel.add(titleLabel);
		add(titlePanel, BorderLayout.NORTH);
		
		setBackground(Color.gray);
		setSize(APPLET_SIZE, APPLET_SIZE);
		setFocusable(true);
		addKeyListener(this);
        requestFocusInWindow();
		
	}
	
	   public void keyPressed( KeyEvent e ) { 
		   	
		   	mainBoard.board_full = mainBoard.is_Full();
			char c = e.getKeyChar();
			
		      if ( c != KeyEvent.CHAR_UNDEFINED ) {
		    	  	mainBoard.tileNotMoved();
		    	  	mainBoard.moveTry(c);
		    	  	
		 
		      }else {
		    	  System.out.println("Char undefined");
		      }
		      e.consume();
				
		      int keyCode = e.getKeyCode();
				
				switch (keyCode) {
				case KeyEvent.VK_UP:
					mainBoard.moveTry('w');
					break;
				case KeyEvent.VK_DOWN:
					mainBoard.moveTry('s');
					break;
				case KeyEvent.VK_LEFT:
					mainBoard.moveTry('a');
					break;
				case KeyEvent.VK_RIGHT:
					mainBoard.moveTry('d');
					break;
				}		      

	    	  	main.mainBoard.repaint();
	    	  	
	    	  	//Check if game is over
	    	  	boolean gameOver = true;
	    	  	if(mainBoard.is_Full()) {
	    	  		for(int y = 0; y < 4; y++) {
			  	  		 for(int x = 0; x < 4; x++) {
			  	  			 if(!(mainBoard.board[y][x].canMoveLeft()) &&
			  	  				!(mainBoard.board[y][x].canMoveDown()) &&	 
			  	  				!(mainBoard.board[y][x].canMoveRight()) &&
			  	  				!(mainBoard.board[y][x].canMoveUp())){
			  	  			 }else {
			  	  				 gameOver = false;
			  	  			 }
			  	  		 }
			  	  	 }
	    	  		if(gameOver == true) {
	    	  			JOptionPane.showMessageDialog(null, "Game Over");
	    	  		}
	    	  	}

		     
	   }
	   public void keyReleased( KeyEvent e ) { }
	   public void keyTyped( KeyEvent e ) {
	      /*char c = e.getKeyChar();
	
	      if ( c != KeyEvent.CHAR_UNDEFINED ) {
	    	  	mainBoard.move(c);
	         e.consume();
	      } else {
	    	  System.out.println("Char undefined");
	      }*/
	   }


}
