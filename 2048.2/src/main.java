// Henry Mound and Sam Naumann
// Section B

// 2048.2

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*; 

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
	
	   public void keyPressed( KeyEvent e ) { }
	   public void keyReleased( KeyEvent e ) { }
	   public void keyTyped( KeyEvent e ) {
	      char c = e.getKeyChar();
	      if ( c != KeyEvent.CHAR_UNDEFINED ) {
	    	  	 mainBoard.move(c);
	         e.consume();
	      }
	   }


}
