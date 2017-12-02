import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

<<<<<<< HEAD
public class Board extends Canvas {
=======
public class Board extends Canvas{
	

	//instance variables
	public static Tile[][] board = new Tile[4][4];
	boolean board_full = false;
	
	//constructor
	
	public void repaint() {
		paint(this.getGraphics());
	}
	
	public void spawnRandTile() {
		boolean didSpawn = false;
		while(!didSpawn) {
			int xRand = (int)(Math.random() * 4);
			int yRand = (int)(Math.random() * 4);
			int powerRand = (int)(Math.random() * 2) + 1;
			if(board[yRand][xRand] == null) {
				board[yRand][xRand] = new Tile(powerRand, xRand, yRand);
				didSpawn = true;
			}
			
		}

		
	}
>>>>>>> master
	
	//instance variables
	Tile[][] board = new Tile[4][4];
	boolean board_full = false;
	
	//constructor
	Board(){
		setSize(500, 500);
		setBackground(Color.darkGray);

		spawnRandTile();
		
	}
	
	
	public void paint(Graphics g){
		Dimension d = getSize();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, d.width, d.height);//Clear board;
		
		 for(int i = 0; i < board.length; i++) {
			 int yCoord = 0;
			 yCoord = (int)(main.BOARD_SIZE * (1 - (Tile.TILE_RATIO * (5 - i)))) + 
					  (int)((i + 1) * Tile.TILE_SPACING * main.BOARD_SIZE);
			 
			 
			 for(int x = 0; x < board[i].length; x++) {
				 if(board[i][x] != null) {
					 int xCoord = (int)(main.BOARD_SIZE * (1 - (Tile.TILE_RATIO * (5 - x)))) +  
						      (int)((x + 1) * Tile.TILE_SPACING * main.BOARD_SIZE);

					 g.setColor(board[i][x].tileColor);
					 //g.drawRect(xCoord, yCoord, (int)(main.BOARD_SIZE * Tile.TILE_RATIO), 
							// (int)(main.BOARD_SIZE * Tile.TILE_RATIO));
					 g.fillRoundRect(xCoord,
							 		yCoord,
							 		(int)(main.BOARD_SIZE * Tile.TILE_RATIO), 
							 		(int)(main.BOARD_SIZE * Tile.TILE_RATIO), 20, 20);
					 g.setColor(Color.white);
					 g.setFont(new Font("Helvetica", Font.BOLD, 30));
					 
					 int tileCenterX = (xCoord + (int)(main.BOARD_SIZE * Tile.TILE_RATIO)/2);
					 int tileCenterY = (yCoord + (int)(main.BOARD_SIZE * Tile.TILE_RATIO)/2);
					 Rectangle2D titleOffset = (new Font("Helvetica", Font.BOLD, 30).getStringBounds(board[i][x].titleText + "", ((Graphics2D) g).getFontRenderContext()));
					 
					 g.drawString(board[i][x].titleText + "",
							 	tileCenterX - (int) titleOffset.getWidth()/2,
							 	tileCenterY + (int) titleOffset.getHeight()/2);
				 }
				 
			 }
		 }
	}
	
	

	   public void move( char c ) {
		   switch(c) {
		   case 'w':{
		  	  	 for(int y = 0; y < 4; y++) {
		  	  		 for(int x = 0; x < 4; x++) {
		  	  			 if(board[y][x] != null){
		  	  				 System.out.println("Checking if tile at ("+y+", "+x+") can move");
		  	  				 
		  	  				 board[y][x].canMove(c);
		  	  			 }
		  	  		 }
		  	  	 }
		   }
		   case 'a':{
		  	  	 for(int x = 0; x < 4; x++) {
		  	  		 for(int y = 0; y < 4; y++) {
		  	  			 if(board[y][x] != null){
		  	  				 System.out.println("Checking if tile at ("+y+", "+x+") can move");
		  	  				 
		  	  				 board[y][x].canMove(c);
		  	  			 }
		  	  		 }
		  	  	 }
			   
		   }
		   case 's':{
		  	  	 for(int y = 3; y >= 0; y--) {
		  	  		 for(int x = 3; x >= 0; x--) {
		  	  			 if(board[y][x] != null){
		  	  				 System.out.println("Checking if tile at ("+y+", "+x+") can move");
		  	  				 
		  	  				 board[y][x].canMove(c);
		  	  			 }
		  	  		 }
		  	  	 }
		   }
		   case 'd':{
		  	  	 for(int x = 3; x >= 0; x--) {
		  	  		 for(int y = 3; y >= 0; y--) {
		  	  			 if(board[y][x] != null){
		  	  				 System.out.println("Checking if tile at ("+y+", "+x+") can move");
		  	  				 
		  	  				 board[y][x].canMove(c);
		  	  			 }
		  	  		 }
		  	  	 }
			   
		   }
		   default: {
			   
		   }
		   }


	  	 for(int y = 0; y < 4; y++) {
	  		 for(int x = 0; x < 4; x++) {
	  	  			 if(board[y][x] != null){
		  				 board[y][x].justMoved = false;
	  	  			 }
	  			 }
	  		 }
	  	spawnRandTile();
	  	 

	   }
	
	public boolean is_Full() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == null) {
					return false;
				} else {
					continue;
				}
			}
		}
		return true;
	}
	
	//methods
	public boolean is_Full() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == null) {
					return false;
				} else {
					continue;
				}
			}
		}
		return true;
	}

}
