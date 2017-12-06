// Henry Mound and Sam Naumann
// Section B

// 2048.2

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class main extends Applet implements KeyListener, ItemListener, MouseListener, ActionListener{ 

    private static final long serialVersionUID = 1L;
    
	static final int BOARD_SIZE = 500;
	static final int APPLET_SIZE = 700;
	static Font preferredFont = new Font("Helvetica", Font.PLAIN, 20);
	static Board mainBoard; 
	Checkbox classicBox; 
	Checkbox retroBox;
	public static int score = 0;
	Label scoreLabel;
	Panel mainPanel;
	Button AIPlayButton;
	Button RestartButton;
	boolean AIPlaying = false;
	 
	public void init() {
		mainPanel = new Panel();
		mainPanel.setBackground(Color.black);
		add(mainPanel);
		AIPlayButton = new Button("AI Play");
		RestartButton = new Button("Restart");
		AIPlayButton.addActionListener(this);
		RestartButton.addActionListener(this);
        CheckboxGroup themeGroup = new CheckboxGroup();
        setBackground(Color.GRAY);
        //if you create checkboxes and add to group,they become radio buttons
        classicBox = new Checkbox("Classic", themeGroup, true);
        classicBox.setForeground(Color.white);
        retroBox = new Checkbox("Retro", themeGroup, false);
        retroBox.setForeground(Color.white);

        scoreLabel = new Label("Score: 0 ");
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(preferredFont);
		scoreLabel.setAlignment(Label.RIGHT);

        Panel themePanel = new Panel();
        themePanel.setLayout(new GridLayout(4, 1));
        Panel northPanel = new Panel();
        northPanel.setLayout(new GridLayout(1, 3));
        
        themePanel.add(classicBox);
        themePanel.add(retroBox);
        themePanel.add(AIPlayButton);
        themePanel.add(RestartButton);
        classicBox.addItemListener(this);
        retroBox.addItemListener(this);
        northPanel.add(themePanel);
        //add radio buttons
       
		
		mainBoard = new Board();
		Panel boardPanel = new Panel();
		BorderLayout mainLayout = new BorderLayout();
		
		mainPanel.setLayout(mainLayout);
		boardPanel.setSize(BOARD_SIZE, BOARD_SIZE);
		boardPanel.add(mainBoard);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(boardPanel, BorderLayout.CENTER);
		Panel titlePanel = new Panel();
		
		
		Label titleLabel = new Label("2048.2", Label.CENTER);
		titleLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
		titleLabel.setForeground(Color.WHITE);
		
		titleLabel.setForeground(Color.WHITE);
		titlePanel.add(titleLabel, BorderLayout.NORTH);
		northPanel.add(titlePanel);

        northPanel.add(scoreLabel);
        mainPanel.add(northPanel, BorderLayout.NORTH);
		
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setForeground(Color.GRAY);
        
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		boardPanel.addMouseListener(this);
		mainBoard.addMouseListener(this);
        requestFocusInWindow();
        setSize(APPLET_SIZE + 50, APPLET_SIZE - 50);
        
		
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
			scoreLabel.setAlignment(Label.RIGHT);

	    	  	main.mainBoard.repaint();
	    	  	

	    	  	if(gameOver()) {
    	  			mainBoard.paintGameOver();	
    	  		}
	    
	    	  	
	    
		     
	   }
	   
	   public static boolean gameOver() {
   	  	if(mainBoard.is_Full()) {
   	  		for(int y = 0; y < 4; y++) {
		  	  		 for(int x = 0; x < 4; x++) {
		  	  			 if(!(mainBoard.getBoard()[y][x].canMoveLeft()) &&
		  	  				!(mainBoard.getBoard()[y][x].canMoveDown()) &&	 
		  	  				!(mainBoard.getBoard()[y][x].canMoveRight()) &&	 
		  	  				!(mainBoard.getBoard()[y][x].canMoveE()) &&	 
		  	  				!(mainBoard.getBoard()[y][x].canMoveQ()) &&	 
		  	  				!(mainBoard.getBoard()[y][x].canMoveZ()) &&	 
		  	  				!(mainBoard.getBoard()[y][x].canMoveC()) &&
		  	  				!(mainBoard.getBoard()[y][x].canMoveUp())
		  	  				){
		  	  			 }else {
		  	  				 return false;
		  	  			 }
		  	  		 }
		  	  	 }
   	   	  		return true;
   	  		}
			return false;
	   }
	   
	   public static boolean desperate() {
	   	  		for(int y = 0; y < 4; y++) {
			  	  		 for(int x = 0; x < 4; x++) {
			  	  			 if(mainBoard.getBoard()[y][x] != null) {
			  	  				if(!(mainBoard.getBoard()[y][x].canMoveLeft()) &&	 
					  	  				!(mainBoard.getBoard()[y][x].canMoveUp()) &&	 
					  	  				!(mainBoard.getBoard()[y][x].canMoveQ())
					  	  				){
					  	  			 }else {
					  	  				 return false;
					  	  			 }
			  	  			 }
			  	  			 
			  	  		 }
			  	  	 }
	   	   	  		return true;
	   	  		
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
	        Object source = e.getItemSelectable();

	        if (source == retroBox) {
	            System.out.println("Retro Box");
	            mainPanel.setBackground(Color.BLACK);

		        	Tile.TILE_2_COLOR = new Color(253,255,0);
		        	Tile.TILE_4_COLOR = new Color(234,130,229);
		        	Tile.TILE_8_COLOR = new Color(70,191,238);
		        	Tile.TILE_16_COLOR = new Color(208,62,25);
		        	Tile.TILE_32_COLOR = new Color(219,133,28);
		        	Tile.TILE_64_COLOR = Color.blue;
		        	Tile.TILE_128_COLOR = new Color(251,46,1);
		        	Tile.TILE_256_COLOR = new Color(111,203,159);
				Tile.TILE_512_COLOR = new Color(212,238,94);
			    	Tile.TILE_1024_COLOR = new Color(255,66,66);
			    	Tile.TILE_2048_COLOR = new Color(89,79,79);
		        	Tile.themeTextColor = Color.WHITE;
		        	Tile.themeBefore2 = Color.BLACK;
		        	mainBoard.repaint();
	            requestFocusInWindow();
	        } else if (source == classicBox) {
	            System.out.println("Classic Box");
	            
	            mainPanel.setBackground(Color.gray);
		        	Tile.TILE_2_COLOR = new Color(234,227,217);
		        	Tile.TILE_4_COLOR = new Color(232,223,201);
		        	Tile.TILE_8_COLOR = new Color(225,175,123);
		        	Tile.TILE_16_COLOR = new Color(222, 148, 103);
		        	Tile.TILE_32_COLOR = new Color(218, 123, 97);
		        	Tile.TILE_64_COLOR = new Color(215, 95, 63);
		        	Tile.TILE_128_COLOR = new Color(227, 203, 106);
		        	Tile.TILE_256_COLOR = new Color(229, 206, 118);
		        	Tile.TILE_512_COLOR = new Color(219, 190, 61);
		        	Tile.TILE_1024_COLOR = new Color(215, 182, 49);
		        	Tile.TILE_2048_COLOR = new Color(225, 193, 67);
		        	Tile.themeTextColor = Color.white;
		        	Tile.themeBefore2 = new Color(116, 109, 101);

	        	mainBoard.repaint();
	            requestFocusInWindow();
	        }
	      }
	   


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		requestFocusInWindow();
		System.out.println("Mouse pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		requestFocusInWindow();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		requestFocusInWindow();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		requestFocusInWindow();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		requestFocusInWindow();
		System.out.println("Mouse clicked");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == RestartButton) {
			mainBoard.clearBoard();
			mainBoard.spawnRandTile();
			mainBoard.repaint();
		}
		if(e.getSource() == AIPlayButton) {
			if(AIPlaying) {
				AIPlayButton.setLabel("AI Play");
				AIPlaying = false;
			}else {
				AIPlayButton.setLabel("Stop AI Play");
				AIPlaying = true;
				
				Timer t = new Timer();
				t.schedule(new TimerTask() {
				    @Override
				    public void run() {
				    	if(AIPlaying) {
				    	for(int y = 0; y < 4; y++) {
				  	  		 for(int x = 0; x < 4; x++) {
				  	  			 if(mainBoard.getBoard()[y][x] != null) {
				  	  			 
				  	  				if(mainBoard.getBoard()[y][x].canMoveQ()) {
						  	  			mainBoard.moveTry('q');
									}
				  	  				else if(mainBoard.getBoard()[y][x].canMoveUp()) {
						  	  			mainBoard.moveTry('w');
									}
					  	  			else if(mainBoard.getBoard()[y][x].canMoveLeft()) {
					  	  				mainBoard.moveTry('a');
									} 
				  	  				else if(desperate() && mainBoard.getBoard()[y][x] != null) {
				  	  					if(mainBoard.getBoard()[y][x].canMoveE()) {
							  	  				mainBoard.moveTry('e');
										}
						  	  			else if(mainBoard.getBoard()[y][x].canMoveRight()) {
						  	  				mainBoard.moveTry('d');
										}
						  	  			else if(mainBoard.getBoard()[y][x].canMoveC()) {
						  	  				mainBoard.moveTry('c');
										}
					  	  				else if(mainBoard.getBoard()[y][x].canMoveZ()) {
						  	  				mainBoard.moveTry('z');
										}
					  	  				else if(mainBoard.getBoard()[y][x].canMoveDown()) {
						  	  				mainBoard.moveTry('s');
										} 
				  	  				}
				  	  				if(gameOver()) {
				  	    	  				mainBoard.paintGameOver();	
				  	    	  				AIPlaying = false;
				  	    					AIPlayButton.setLabel("AI Play");
				  	  				}

				  	  			 }
				  	  			
				  	  		 }
				    		}
	  	  				mainBoard.repaint();
				}
			}
		}, 0, 200);
				
				
					
					
				}
			}
		}
	}	   


