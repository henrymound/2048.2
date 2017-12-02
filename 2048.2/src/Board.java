import java.awt.*;

public class Board extends Canvas {
	
	//instance variables
	Tile[][] board = new Tile[4][4];
	boolean board_full = false;
	
	//constructor
	Board(){
		setSize(500, 500);
		setBackground(Color.darkGray);
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
