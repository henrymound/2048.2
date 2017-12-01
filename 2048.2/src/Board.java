import java.awt.*;

public class Board extends Canvas {
	

	//instance variables
	Tile[][] board = new Tile[4][4];
	boolean board_full = false;
	
	//constructor
	Board(){
		setSize(500, 500);
		setBackground(Color.darkGray);

		for(int i = 0; i < 4; i++) {
			for(int x = 0; x < 4; x++) {
				Tile tile = new Tile(main.BOARD_SIZE, (i % 4) + 1) ;
				board[i][x] = tile;
			}
		}
		
	}
	
	
	public void paint(Graphics g){
		 for(int i = 0; i < board.length; i++) {
			 int yCoord = 0;
			 System.out.print(Tile.TILE_SPACING);
			 yCoord = (int)(main.BOARD_SIZE * (1 - (Tile.TILE_RATIO * (5 - i)))) + 
					  (int)((i + 1) * Tile.TILE_SPACING * main.BOARD_SIZE);
			 
			 
			 for(int x = 0; x < board[i].length; x++) {
				 int xCoord = (int)(main.BOARD_SIZE * (1 - (Tile.TILE_RATIO * (5 - x)))) +  
						      (int)((x + 1) * Tile.TILE_SPACING * main.BOARD_SIZE);

				 
				 g.setColor(board[i][x].tileColor);
				 g.drawRect(xCoord, yCoord, (int)(main.BOARD_SIZE * Tile.TILE_RATIO), 
						 (int)(main.BOARD_SIZE * Tile.TILE_RATIO));
				 g.fillRect(xCoord, yCoord, (int)(main.BOARD_SIZE * Tile.TILE_RATIO), 
						 (int)(main.BOARD_SIZE * Tile.TILE_RATIO));
				 System.out.println("Added tile at (" + i + ", " + x + ") with coordinates (" + xCoord + ", " + yCoord + ")");
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
