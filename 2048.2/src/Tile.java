// Henry Mound and Sam Naumann
// Section B

// 2048.2
import java.awt.*;

public class Tile extends Canvas {

	// get rid of serializing warning
	private static final long serialVersionUID = 1L;

	static Color TILE_2_COLOR = new Color(234, 227, 217);
	static Color TILE_4_COLOR = new Color(232, 223, 201);
	static Color TILE_8_COLOR = new Color(225, 175, 123);
	static Color TILE_16_COLOR = new Color(222, 148, 103);
	static Color TILE_32_COLOR = new Color(218, 123, 97);
	static Color TILE_64_COLOR = new Color(215, 95, 63);
	static Color themeTextColor = Color.white;
	static Color themeBefore2 = new Color(116, 109, 101);
	static Color TILE_128_COLOR = new Color(227, 203, 106);
	static Color TILE_256_COLOR = new Color(229, 206, 118);
	static Color TILE_512_COLOR = new Color(219, 190, 61);
	static Color TILE_1024_COLOR = new Color(215, 182, 49);
	static Color TILE_2048_COLOR = new Color(225, 193, 67);
	static final Color TILE_4096_COLOR = new Color(143, 217, 248);
	/*
	 * static final Color TILE_8192_COLOR = new Color(); static final Color
	 * TILE_16384_COLOR = new Color(); static final Color TILE_32768_COLOR = new
	 * Color(); static final Color TILE_65536_COLOR = new Color();
	 */

	// instance variables for the tile class
	static final double TILE_RATIO = 0.2;
	static final double TILE_SPACING = (1 - (TILE_RATIO * 4)) / 5;
	Color tileColor;
	int titleText;
	int xCoord;
	int yCoord;
	int power;

	// constructor for Tile
	Tile(int powerOfTwo, int yCoord, int xCoord) {
		setSize((int) (main.BOARD_SIZE * TILE_RATIO), (int) (main.BOARD_SIZE * TILE_RATIO));
		setBackground(colorFromPower(powerOfTwo));
		tileColor = colorFromPower(powerOfTwo);
		titleText = (int) Math.pow(2, powerOfTwo);
		this.xCoord = xCoord;
		power = powerOfTwo;
		this.yCoord = yCoord;
	}

	// returns which color a tile should be based on its power
	public Color colorFromPower(int power) {
		switch (power) {
		case 1:
			return TILE_2_COLOR;
		case 2:
			return TILE_4_COLOR;
		case 3:
			return TILE_8_COLOR;
		case 4:
			return TILE_16_COLOR;
		case 5:
			return TILE_32_COLOR;
		case 6:
			return TILE_64_COLOR;
		case 7:
			return TILE_128_COLOR;
		case 8:
			return TILE_256_COLOR;
		case 9:
			return TILE_512_COLOR;
		case 10:
			return TILE_1024_COLOR;
		case 11:
			return TILE_2048_COLOR;
		case 12:
			return TILE_4096_COLOR;
		default:
			return Color.black;
		}
	}

	// determines which color the number should be so that it shows up in the
	// background
	public Color textColorFromPower(int power) {
		if (power > 2) {
			return themeTextColor;
		} else {
			return themeBefore2;
		}
	}

	// these switch statements move the tile continuously in the desired direction
	// if they are able to move in that direction
	// they also handle the combining of tiles that are the same power
	// they have nothing to do with the order that the tiles are tested in
	public void canMove(char c) {
<<<<<<< HEAD
		switch (c) {
=======
		switch(c) {
		case 'w':{
			while(canMoveUp()) {
				main.mainBoard.tileMoved();
	  			System.out.println("Can move with ("+yCoord+", "+xCoord+")");
	  			Tile[][] tempArray = Board.board;
	  			if(tempArray[yCoord - 1][xCoord] != null &&
	  				tempArray[yCoord - 1][xCoord].power == this.power) {
	  				tempArray[yCoord - 1][xCoord] = new Tile(this.power + 1, yCoord - 1, xCoord);
	  				main.score += Math.pow(2, this.power + 1);
	  			}else {
		  			tempArray[yCoord - 1][xCoord] = tempArray[yCoord][xCoord];
		  		}
	  			tempArray[yCoord][xCoord] = null;
	  			yCoord -= 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);
	  			main.moveLabel.setText("W");
	  		}
>>>>>>> parent of 3b55828... Updated score increment for AI and added spacing for key indicator.

		// move it if the tile can move up
		case 'w': {
			while (canMoveUp()) {
				main.mainBoard.tileMoved();
<<<<<<< HEAD
				System.out.println("Can move with (" + yCoord + ", " + xCoord + ")");
				Tile[][] tempArray = Board.board;
				if (tempArray[yCoord - 1][xCoord] != null && tempArray[yCoord - 1][xCoord].power == this.power) {
					tempArray[yCoord - 1][xCoord] = new Tile(this.power + 1, yCoord - 1, xCoord);
					main.score += Math.pow(2, this.power + 1);
				} else {
					tempArray[yCoord - 1][xCoord] = tempArray[yCoord][xCoord];
				}
				tempArray[yCoord][xCoord] = null;
				yCoord -= 1;
				Board.board = tempArray;

			}

=======
	  			System.out.println("Can move with ("+yCoord+", "+xCoord+")");
	  			Tile[][] tempArray = Board.board;
	  			if(tempArray[yCoord + 1][xCoord] != null && tempArray[yCoord + 1][xCoord].power == this.power) {
	  				tempArray[yCoord + 1][xCoord] = new Tile(this.power + 1, yCoord + 1, xCoord);
	  				main.score += Math.pow(2, this.power + 1);
	  			}else {
		  			tempArray[yCoord + 1][xCoord] = tempArray[yCoord][xCoord];
		  		}
	  			tempArray[yCoord][xCoord] = null;
	  			yCoord += 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);
	  			main.moveLabel.setText("S");
	  		}
			break;
		}

		case 'd':{
			while(canMoveRight()) {
				main.mainBoard.tileMoved();
	  			System.out.println("Can move with ("+yCoord+", "+xCoord+")");
	  			Tile[][] tempArray = Board.board;
	  			if(tempArray[yCoord][xCoord + 1] != null && tempArray[yCoord][xCoord + 1].power == this.power) {
	  				tempArray[yCoord][xCoord + 1] = new Tile(this.power + 1, yCoord, xCoord + 1);
	  				main.score += Math.pow(2, this.power + 1);
	  			}else {
		  			tempArray[yCoord][xCoord + 1] = tempArray[yCoord][xCoord];
		  		}
	  			tempArray[yCoord][xCoord] = null;
	  			xCoord += 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);

	  			main.moveLabel.setText("D");
	  		}
>>>>>>> parent of 3b55828... Updated score increment for AI and added spacing for key indicator.
			break;
		}

		// move it if the tile can move down
		case 's': {
			while (canMoveDown()) {
				main.mainBoard.tileMoved();
<<<<<<< HEAD
				System.out.println("Can move with (" + yCoord + ", " + xCoord + ")");
				Tile[][] tempArray = Board.board;
				if (tempArray[yCoord + 1][xCoord] != null && tempArray[yCoord + 1][xCoord].power == this.power) {
					tempArray[yCoord + 1][xCoord] = new Tile(this.power + 1, yCoord + 1, xCoord);
					main.score += Math.pow(2, this.power + 1);
				} else {
					tempArray[yCoord + 1][xCoord] = tempArray[yCoord][xCoord];
				}
				tempArray[yCoord][xCoord] = null;
				yCoord += 1;
				Board.board = tempArray;

			}
=======
	  			System.out.println("Can move with ("+yCoord+", "+xCoord+")");
	  			printArray(Board.board);
	  			//System.out.println();
	  			Tile[][] tempArray = Board.board;
	  			if(tempArray[yCoord][xCoord - 1] != null
	  					&& tempArray[yCoord][xCoord - 1].power == this.power) {
	  				tempArray[yCoord][xCoord - 1] = new Tile(this.power + 1, yCoord, xCoord - 1);
	  				main.score += Math.pow(2, this.power + 1);
	  			}else {
		  			tempArray[yCoord][xCoord - 1] = tempArray[yCoord][xCoord];
		  		}
	  			tempArray[yCoord][xCoord] = null;
	  			xCoord -= 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);
	  			main.moveLabel.setText("A");
	  		}
>>>>>>> parent of 3b55828... Updated score increment for AI and added spacing for key indicator.
			break;
		}

		// move it if the tile can move right
		case 'd': {
			while (canMoveRight()) {
				main.mainBoard.tileMoved();
<<<<<<< HEAD
				System.out.println("Can move with (" + yCoord + ", " + xCoord + ")");
				Tile[][] tempArray = Board.board;
				if (tempArray[yCoord][xCoord + 1] != null && tempArray[yCoord][xCoord + 1].power == this.power) {
					tempArray[yCoord][xCoord + 1] = new Tile(this.power + 1, yCoord, xCoord + 1);
					main.score += Math.pow(2, this.power + 1);
				} else {
					tempArray[yCoord][xCoord + 1] = tempArray[yCoord][xCoord];
				}
				tempArray[yCoord][xCoord] = null;
				xCoord += 1;
				Board.board = tempArray;

			}
=======
	  			System.out.println("Can move with ("+yCoord+", "+xCoord+")");
	  			Tile[][] tempArray = Board.board;
	  			if(tempArray[yCoord - 1][xCoord + 1] != null && 
	  				tempArray[yCoord - 1][xCoord + 1].power == this.power) {
	  				tempArray[yCoord - 1][xCoord + 1] = new Tile(this.power + 1, yCoord - 1, xCoord + 1);
	  				main.score += Math.pow(2, this.power + 1);
	  			}else {
		  			tempArray[yCoord - 1][xCoord + 1] = tempArray[yCoord][xCoord];
		  		}
	  			tempArray[yCoord][xCoord] = null;
	  			xCoord += 1;
	  			yCoord -= 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);

	  			main.moveLabel.setText("E");
	  		}
>>>>>>> parent of 3b55828... Updated score increment for AI and added spacing for key indicator.
			break;
		}

		// move it if the tile can move left
		case 'a': {

			while (canMoveLeft()) {
				main.mainBoard.tileMoved();
<<<<<<< HEAD
				System.out.println("Can move with (" + yCoord + ", " + xCoord + ")");

				// System.out.println();
				Tile[][] tempArray = Board.board;
				if (tempArray[yCoord][xCoord - 1] != null && tempArray[yCoord][xCoord - 1].power == this.power) {
					tempArray[yCoord][xCoord - 1] = new Tile(this.power + 1, yCoord, xCoord - 1);
					main.score += Math.pow(2, this.power + 1);
				} else {
					tempArray[yCoord][xCoord - 1] = tempArray[yCoord][xCoord];
				}
				tempArray[yCoord][xCoord] = null;
				xCoord -= 1;
				Board.board = tempArray;

			}
=======
	  			System.out.println("Can move with ("+yCoord+", "+xCoord+")");
	  			Tile[][] tempArray = Board.board;
	  			if(tempArray[yCoord - 1][xCoord - 1] != null && 
	  					tempArray[yCoord - 1][xCoord - 1].power == this.power) {
	  				tempArray[yCoord - 1][xCoord - 1] = new Tile(this.power + 1, yCoord - 1, xCoord - 1);
	  				main.score += Math.pow(2, this.power + 1);
	  			}else {
		  			tempArray[yCoord - 1][xCoord - 1] = tempArray[yCoord][xCoord];
		  		}
	  			tempArray[yCoord][xCoord] = null;
	  			xCoord -= 1;
	  			yCoord -= 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);
	  			main.moveLabel.setText("Q");
	  		}
>>>>>>> parent of 3b55828... Updated score increment for AI and added spacing for key indicator.
			break;
		}

		case 'e': {

			// move it if the tile can move up and right
			while (canMoveE()) {
				main.mainBoard.tileMoved();
<<<<<<< HEAD
				System.out.println("Can move with (" + yCoord + ", " + xCoord + ")");
				Tile[][] tempArray = Board.board;
				if (tempArray[yCoord - 1][xCoord + 1] != null
						&& tempArray[yCoord - 1][xCoord + 1].power == this.power) {
					tempArray[yCoord - 1][xCoord + 1] = new Tile(this.power + 1, yCoord - 1, xCoord + 1);
					main.score += Math.pow(2, this.power + 1);
				} else {
					tempArray[yCoord - 1][xCoord + 1] = tempArray[yCoord][xCoord];
				}
				tempArray[yCoord][xCoord] = null;
				xCoord += 1;
				yCoord -= 1;
				Board.board = tempArray;

			}
=======
	  			System.out.println("Can move with ("+yCoord+", "+xCoord+")");
	  			Tile[][] tempArray = Board.board;
	  			if(tempArray[yCoord + 1][xCoord - 1] != null && 
	  					tempArray[yCoord + 1][xCoord - 1].power == this.power) {
	  				tempArray[yCoord + 1][xCoord - 1] = new Tile(this.power + 1, yCoord + 1, xCoord - 1);
	  				main.score += Math.pow(2, this.power + 1);
	  			}else {
		  			tempArray[yCoord + 1][xCoord - 1] = tempArray[yCoord][xCoord];
		  		}
	  			tempArray[yCoord][xCoord] = null;
	  			xCoord -= 1;
	  			yCoord += 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);
	  			main.moveLabel.setText("Z");
	  		}
>>>>>>> parent of 3b55828... Updated score increment for AI and added spacing for key indicator.
			break;
		}

		case 'q': {

			// move it if the tile can move up and left
			while (canMoveQ()) {
				main.mainBoard.tileMoved();
<<<<<<< HEAD
				System.out.println("Can move with (" + yCoord + ", " + xCoord + ")");
				Tile[][] tempArray = Board.board;
				if (tempArray[yCoord - 1][xCoord - 1] != null
						&& tempArray[yCoord - 1][xCoord - 1].power == this.power) {
					tempArray[yCoord - 1][xCoord - 1] = new Tile(this.power + 1, yCoord - 1, xCoord - 1);
					main.score += Math.pow(2, this.power + 1);
				} else {
					tempArray[yCoord - 1][xCoord - 1] = tempArray[yCoord][xCoord];
				}
				tempArray[yCoord][xCoord] = null;
				xCoord -= 1;
				yCoord -= 1;
				Board.board = tempArray;

			}
=======
	  			System.out.println("Can move with ("+yCoord+", "+xCoord+")");
	  			Tile[][] tempArray = Board.board;
	  			if(tempArray[yCoord + 1][xCoord + 1] != null && 
	  					tempArray[yCoord + 1][xCoord + 1].power == this.power) {
	  				tempArray[yCoord + 1][xCoord + 1] = new Tile(this.power + 1, yCoord + 1, xCoord + 1);
	  				main.score += Math.pow(2, this.power + 1);
	  			}else {
		  			tempArray[yCoord + 1][xCoord + 1] = tempArray[yCoord][xCoord];
		  		}
	  			tempArray[yCoord][xCoord] = null;
	  			xCoord += 1;
	  			yCoord += 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);
	  			main.moveLabel.setText("C");
	  		}
			break;
		}
		
		/*
		case 'q':{

			while(canMoveUp() && canMoveLeft()) {
				main.mainBoard.tileMoved();
	  			System.out.println("Can move");
	  			Tile[][] tempArray = Board.board;
	  			tempArray[yCoord - 1][xCoord - 1] = this;
	  			tempArray[yCoord][xCoord] = null;
	  			xCoord -= 1;
	  			yCoord -= 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);
	  		}
>>>>>>> parent of 3b55828... Updated score increment for AI and added spacing for key indicator.
			break;
		}

		// move it if the tile can move down and left
		case 'z': {

			while (canMoveZ()) {
				main.mainBoard.tileMoved();
				System.out.println("Can move with (" + yCoord + ", " + xCoord + ")");
				Tile[][] tempArray = Board.board;
				if (tempArray[yCoord + 1][xCoord - 1] != null
						&& tempArray[yCoord + 1][xCoord - 1].power == this.power) {
					tempArray[yCoord + 1][xCoord - 1] = new Tile(this.power + 1, yCoord + 1, xCoord - 1);
					main.score += Math.pow(2, this.power + 1);
				} else {
					tempArray[yCoord + 1][xCoord - 1] = tempArray[yCoord][xCoord];
				}
				tempArray[yCoord][xCoord] = null;
				xCoord -= 1;
				yCoord += 1;
				Board.board = tempArray;

			}
			break;
		}

		// move it if the tile can move down and right
		case 'c': {

			while (canMoveC()) {
				main.mainBoard.tileMoved();
				System.out.println("Can move with (" + yCoord + ", " + xCoord + ")");
				Tile[][] tempArray = Board.board;
				if (tempArray[yCoord + 1][xCoord + 1] != null
						&& tempArray[yCoord + 1][xCoord + 1].power == this.power) {
					tempArray[yCoord + 1][xCoord + 1] = new Tile(this.power + 1, yCoord + 1, xCoord + 1);
					main.score += Math.pow(2, this.power + 1);
				} else {
					tempArray[yCoord + 1][xCoord + 1] = tempArray[yCoord][xCoord];
				}
				tempArray[yCoord][xCoord] = null;
				xCoord += 1;
				yCoord += 1;
				Board.board = tempArray;

			}
			break;
		}
		}
	}

	// helper functions that determine when to break the while loop of movement
	// they will return false when the square in the desired direction is occupied
	// and of a different power
	public boolean canMoveLeft() {
		return ((!(xCoord - 1 < 0)) && ((Board.board[yCoord][xCoord - 1] == null)// If the square above is null
				|| Board.board[yCoord][xCoord - 1].power == this.power));

	}

	public boolean canMoveRight() {
		return ((!(xCoord + 1 > 3)) && ((Board.board[yCoord][xCoord + 1] == null)// If the square above is null
				|| Board.board[yCoord][xCoord + 1].power == this.power));
	}

	public boolean canMoveUp() {
		return ((!(yCoord - 1 < 0)) && // If not out of bounds
				((Board.board[yCoord - 1][xCoord] == null)// If the square above is null
						|| Board.board[yCoord - 1][xCoord].power == this.power));

	}

	public boolean canMoveDown() {
		return ((!(yCoord + 1 > 3)) && ((Board.board[yCoord + 1][xCoord] == null)// If the square below is null
				|| Board.board[yCoord + 1][xCoord].power == this.power));
	}

	public boolean canMoveE() {
		return ((!(yCoord - 1 < 0)) && (!(xCoord + 1 > 3)) && // If not out of bounds
				((Board.board[yCoord - 1][xCoord + 1] == null)// If the square above is null
						|| Board.board[yCoord - 1][xCoord + 1].power == this.power));
	}

	public boolean canMoveQ() {
		return ((!(yCoord - 1 < 0)) && (!(xCoord - 1 < 0)) && // If not out of bounds
				((Board.board[yCoord - 1][xCoord - 1] == null)// If the square above is null
						|| Board.board[yCoord - 1][xCoord - 1].power == this.power));
	}

	public boolean canMoveZ() {
		return ((!(yCoord + 1 > 3)) && (!(xCoord - 1 < 0)) && // If not out of bounds
				((Board.board[yCoord + 1][xCoord - 1] == null)// If the square above is null
						|| Board.board[yCoord + 1][xCoord - 1].power == this.power));
	}

	public boolean canMoveC() {
		return ((!(yCoord + 1 > 3)) && (!(xCoord + 1 > 3)) && // If not out of bounds
				((Board.board[yCoord + 1][xCoord + 1] == null)// If the square above is null
						|| Board.board[yCoord + 1][xCoord + 1].power == this.power));
	}
}