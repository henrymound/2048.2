// Henry Mound and Sam Naumann
// Section B

// 2048.2

import java.applet.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.util.*; 
import javax.swing.JComponent;
import javax.swing.*;



public class main extends Applet implements KeyListener, ItemListener{
	
	static final int BOARD_SIZE = 500;
	static final int APPLET_SIZE = 650;
	static Font preferredFont = new Font("Helvetica", Font.PLAIN, 20);
	static Board mainBoard; 
	Checkbox classicBox; 
	Checkbox retroBox;
	public static int score = 0;
	JLabel scoreLabel;
	
	public void init() {
        CheckboxGroup themeGroup = new CheckboxGroup();
       
        //if you create checkboxes and add to group,they become radio buttons
         classicBox = new Checkbox("Classic", themeGroup, true);
         classicBox.setForeground(Color.white);
         retroBox = new Checkbox("Retro", themeGroup, false);
         retroBox.setForeground(Color.white);

         scoreLabel = new JLabel("Score: 0 ");
         scoreLabel.setForeground(Color.white);
         scoreLabel.setFont(preferredFont);
			scoreLabel.setHorizontalAlignment(JLabel.CENTER);

        Panel themePanel = new Panel();
        themePanel.setLayout(new GridLayout(2, 1));
        Panel northPanel = new Panel();
        northPanel.setLayout(new BorderLayout());
        northPanel.add(scoreLabel, BorderLayout.EAST);
        
        themePanel.add(classicBox);
        themePanel.add(retroBox);
        classicBox.addItemListener(this);
        retroBox.addItemListener(this);
        northPanel.add(themePanel, BorderLayout.WEST);
        //add radio buttons
       
		
		mainBoard = new Board();
		Panel boardPanel = new Panel();
		BorderLayout mainLayout = new BorderLayout();
		setLayout(mainLayout);
		boardPanel.setSize(BOARD_SIZE, BOARD_SIZE);
		boardPanel.add(mainBoard);
		add(northPanel, BorderLayout.NORTH);
		add(boardPanel, BorderLayout.CENTER);
		Panel titlePanel = new Panel();
		
		
		Label titleLabel = new Label("2048.2", Label.CENTER);
		titleLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
		titleLabel.setForeground(Color.WHITE);
		
		titleLabel.setForeground(Color.WHITE);
		titlePanel.add(titleLabel, BorderLayout.NORTH);
		northPanel.add(titlePanel);
		add(northPanel, BorderLayout.NORTH);
		
		setBackground(Color.gray);
		setSize(APPLET_SIZE, APPLET_SIZE - 50);
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

			scoreLabel.setText("Score: " + score + " ");
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
	    	  			int input = JOptionPane.showOptionDialog(null, "Game Over", "Game Over",
	    	  					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		    	  		if(input == JOptionPane.OK_OPTION) {
		    	  			System.out.println("Making a new board");
		    	  			Arrays.fill(mainBoard.board, null);
		    	  			mainBoard.board = new Tile[4][4];
		    	  			mainBoard.spawnRandTile();
		    	  			mainBoard.repaint();
		    	  		}

	    	  		}
	    	  		{
	    	  		    // do something
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

	   public void itemStateChanged(ItemEvent e) {
	        int index = 0;
	        char c = '-';
	        Object source = e.getItemSelectable();

	        if (source == retroBox) {
	            index = 0;
	            c = 'c';
	            System.out.println("Retro Box");
	            this.setBackground(Color.BLACK);

		        	Tile.TILE_2_COLOR = new Color(253,255,0);
		        	Tile.TILE_4_COLOR = new Color(234,130,229);
		        	Tile.TILE_8_COLOR = new Color(70,191,238);
		        	Tile.TILE_16_COLOR = new Color(208,62,25);
		        	Tile.TILE_32_COLOR = new Color(219,133,28);
		        	Tile.themeTextColor = Color.WHITE;
		        	Tile.themeBefore2 = Color.BLACK;
		        	Tile.TILE_64_COLOR = Color.blue;
		        	mainBoard.repaint();
	            requestFocusInWindow();
	        } else if (source == classicBox) {
	            index = 1;
	            c = 'g';
	            System.out.println("Classic Box");

	    		setBackground(Color.gray);

	        	Tile.TILE_2_COLOR = new Color(234,227,217);
	        	Tile.TILE_4_COLOR = new Color(232,223,201);
	        	Tile.TILE_8_COLOR = new Color(225,175,123);
	        	Tile.TILE_16_COLOR = new Color(222, 148, 103);
	        	Tile.TILE_32_COLOR = new Color(218, 123, 97);
	        	Tile.TILE_64_COLOR = new Color(215, 95, 63);
	        	Tile.themeTextColor = Color.white;
	        	Tile.themeBefore2 = new Color(116, 109, 101);

	        	mainBoard.repaint();
	            requestFocusInWindow();
	        }
	      }
	   

}
