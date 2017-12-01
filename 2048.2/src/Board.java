import java.awt.*;

public class Board extends Canvas {
	
<<<<<<< HEAD
	Tile[][] board = new Tile[4][4];
	int BOARD_SIZE;
	
	Board(int size){
		BOARD_SIZE = size;
		setSize(size, size);
=======
	//instance variables
	Tile[][] board = new Tile[4][4];
	boolean board_full = false;
	
	//constructor
	Board(){
		setSize(500, 500);
>>>>>>> sam
		setBackground(Color.darkGray);

		for(int i = 0; i < 4; i++) {
			for(int x = 0; x < 4; x++) {
				Tile tile = new Tile(size, (i % 4) + 1) ;
				board[i][x] = tile;
			}
		}
		
	}
	
	
	public void paint(Graphics g){
		 for(int i = 0; i < board.length; i++) {
			 int yCoord = 0;
			 int tileSpacing = (int)((BOARD_SIZE * (1 - (Tile.TILE_RATIO * 4)))/5);
			 switch(i) {
			 	case 0: yCoord = (int)(BOARD_SIZE * (1 - (Tile.TILE_RATIO * 4))) + tileSpacing;
			 	case 1: yCoord = (int)(BOARD_SIZE * (1 - (Tile.TILE_RATIO * 4))) + tileSpacing;
			 	case 2: yCoord = (int)(BOARD_SIZE * (1 - (Tile.TILE_RATIO * 4))) + tileSpacing;
			 	case 3: yCoord = (int)(BOARD_SIZE * (1 - (Tile.TILE_RATIO * 4))) + tileSpacing;
			 	default: break;
			 }
			 for(int x = 0; x < board[i].length; x++) {
				 int xCoord = 0;
				 switch(i) {
				 	case 0: xCoord = (int)(BOARD_SIZE * (1 - (Tile.TILE_RATIO * 4))) + tileSpacing;
				 	case 1: xCoord = (int)(BOARD_SIZE * (1 - (Tile.TILE_RATIO * 4))) + tileSpacing;
				 	case 2: xCoord = (int)(BOARD_SIZE * (1 - (Tile.TILE_RATIO * 4))) + tileSpacing;
				 	case 3: xCoord = (int)(BOARD_SIZE * (1 - (Tile.TILE_RATIO * 4))) + tileSpacing;
				 	default: break;
				 }
				 g.setColor(board[i][x].tileColor);
				 g.drawRect(xCoord, yCoord, (int)(main.BOARD_SIZE * Tile.TILE_RATIO), 
						 (int)(main.BOARD_SIZE * Tile.TILE_RATIO));
				 g.fillRect(xCoord, yCoord, (int)(main.BOARD_SIZE * Tile.TILE_RATIO), 
						 (int)(main.BOARD_SIZE * Tile.TILE_RATIO));
			 }
		 }
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
