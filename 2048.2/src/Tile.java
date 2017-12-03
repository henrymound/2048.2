// Henry Mound and Sam Naumann
// Section B

// 2048.2
import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.*;

public class Tile extends Canvas {
	static final Color TILE_2_COLOR = new Color(236,218,195);
	static final Color TILE_4_COLOR = new Color(223,177,21);
	static final Color TILE_8_COLOR = new Color(233, 130, 75);
	static final Color TILE_16_COLOR = new Color(244, 112, 85);
	static final Color TILE_32_COLOR = new Color(231, 78, 51);
	/*static final Color TILE_64_COLOR = new Color();
	static final Color TILE_128_COLOR = new Color();
	static final Color TILE_256_COLOR = new Color();
	static final Color TILE_512_COLOR = new Color();
	static final Color TILE_1024_COLOR = new Color();
	static final Color TILE_2048_COLOR = new Color();
	static final Color TILE_4096_COLOR = new Color();
	static final Color TILE_8192_COLOR = new Color();
	static final Color TILE_16384_COLOR = new Color();
	static final Color TILE_32768_COLOR = new Color();
	static final Color TILE_65536_COLOR = new Color();*/
	
	static final double TILE_RATIO = 0.2;
	static final double TILE_SPACING = (1 - (TILE_RATIO * 4))/5;
	Color tileColor;
	int titleText;
	int xCoord;
	int yCoord; 
	int power;

	
	Tile(int powerOfTwo, int yCoord, int xCoord) {
		setSize((int)(main.BOARD_SIZE * TILE_RATIO), (int)(main.BOARD_SIZE * TILE_RATIO));
		setBackground(colorFromPower(powerOfTwo));
		tileColor = colorFromPower(powerOfTwo);
		titleText = (int) Math.pow(2, powerOfTwo);
		this.xCoord = xCoord;
		power = powerOfTwo;
		this.yCoord = yCoord;
	}
	
	Tile(Tile tileToCopy){
		setSize((int)(main.BOARD_SIZE * TILE_RATIO), (int)(main.BOARD_SIZE * TILE_RATIO));
		setBackground(colorFromPower(tileToCopy.power));
		tileColor = colorFromPower(tileToCopy.power);
		titleText = (int) Math.pow(2, tileToCopy.power);
		this.xCoord = tileToCopy.xCoord;
		power = tileToCopy.power;
		this.yCoord = tileToCopy.yCoord;
	}



	public Color colorFromPower(int power) {
		switch (power) {
	        case 1:  return TILE_2_COLOR;
	        case 2:  return TILE_4_COLOR;
	        case 3:  return TILE_8_COLOR;
	        case 4:  return TILE_16_COLOR;
	        case 5:  return TILE_32_COLOR;
	        default: return Color.black;
		}
	}
	
	public void canMove(char c) {
		switch(c) {
		case 'w':{
			while(canMoveUp()) {
				main.mainBoard.tileMoved();
	  			System.out.println("Can move with ("+yCoord+", "+xCoord+")");
	  			Tile[][] tempArray = Board.board;
	  			if(tempArray[yCoord - 1][xCoord] != null &&
	  				tempArray[yCoord - 1][xCoord].power == tempArray[yCoord][xCoord].power) {
	  				tempArray[yCoord - 1][xCoord] = new Tile(this.power + 1, yCoord - 1, xCoord);
	  			}else {
		  			tempArray[yCoord - 1][xCoord] = tempArray[yCoord][xCoord];
		  		}
	  			tempArray[yCoord][xCoord] = null;
	  			yCoord -= 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);
	  		}

			break;
	  	}
			
		
		case 's':{
			while(canMoveDown()) {
				main.mainBoard.tileMoved();
	  			System.out.println("Can move with ("+yCoord+", "+xCoord+")");
	  			Tile[][] tempArray = Board.board;
	  			if(tempArray[yCoord + 1][xCoord] != null && tempArray[yCoord + 1][xCoord].power == tempArray[yCoord][xCoord].power) {
	  				tempArray[yCoord + 1][xCoord] = new Tile(this.power + 1, yCoord + 1, xCoord);
	  			}else {
		  			tempArray[yCoord + 1][xCoord] = tempArray[yCoord][xCoord];
		  		}
	  			tempArray[yCoord][xCoord] = null;
	  			yCoord += 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);
	  		}
			break;
		}

		case 'd':{
			while(canMoveRight()) {
				main.mainBoard.tileMoved();
	  			System.out.println("Can move with ("+yCoord+", "+xCoord+")");
	  			Tile[][] tempArray = Board.board;
	  			if(tempArray[yCoord][xCoord + 1] != null && tempArray[yCoord][xCoord + 1].power == tempArray[yCoord][xCoord].power) {
	  				tempArray[yCoord][xCoord + 1] = new Tile(this.power + 1, yCoord, xCoord + 1);
	  			}else {
		  			tempArray[yCoord][xCoord + 1] = tempArray[yCoord][xCoord];
		  		}
	  			tempArray[yCoord][xCoord] = null;
	  			xCoord += 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);
	  		}
			break;
		}
		

		case 'a':{

			while(canMoveLeft()) {
				main.mainBoard.tileMoved();
	  			System.out.println("Can move with ("+yCoord+", "+xCoord+")");
	  			printArray(Board.board);
	  			//System.out.println();
	  			Tile[][] tempArray = Board.board;
	  			if(tempArray[yCoord][xCoord - 1] != null && tempArray[yCoord][xCoord - 1].power == tempArray[yCoord][xCoord].power) {
	  				tempArray[yCoord][xCoord - 1] = new Tile(this.power + 1, yCoord, xCoord - 1);
	  			}else {
		  			tempArray[yCoord][xCoord - 1] = tempArray[yCoord][xCoord];
		  		}
	  			tempArray[yCoord][xCoord] = null;
	  			xCoord -= 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);
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
			break;
		}


		case 'e':{

			while(canMoveUp() && canMoveRight()) {
				main.mainBoard.tileMoved();
	  			System.out.println("Can move");
	  			Tile[][] tempArray = Board.board;
	  			tempArray[yCoord - 1][xCoord + 1] = this;
	  			tempArray[yCoord][xCoord] = null;
	  			xCoord += 1;
	  			yCoord -= 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);
	  		}
			break;
		}
		
		case 'c':{

			while(canMoveDown() && canMoveRight()) {
				main.mainBoard.tileMoved();
	  			System.out.println("Can move");
	  			Tile[][] tempArray = Board.board;
	  			tempArray[yCoord + 1][xCoord + 1] = this;
	  			tempArray[yCoord][xCoord] = null;
	  			xCoord += 1;
	  			yCoord += 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);
	  		}
			break;
		}
		
		case 'z':{

			while(canMoveDown() && canMoveLeft()) {
				main.mainBoard.tileMoved();
	  			System.out.println("Can move");
	  			Tile[][] tempArray = Board.board;
	  			tempArray[yCoord + 1][xCoord - 1] = this;
	  			tempArray[yCoord][xCoord] = null;
	  			xCoord -= 1;
	  			yCoord += 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);
	  		}
			break;
		}
		*/	
		}
	     
			//main.mainBoard.repaint();

	    //System.out.println(c);
	      
	}

	public boolean canMoveLeft() {
		return ((!(xCoord - 1 < 0)) && 
				((Board.board[yCoord][xCoord - 1] == null)// If the sqaure above is null
				|| Board.board[yCoord][xCoord - 1].power == this.power)
				);
		
	}
	public boolean canMoveRight() {
		return ((!(xCoord + 1 > 3)) && 
				((Board.board[yCoord][xCoord + 1] == null)// If the sqaure above is null
				|| Board.board[yCoord][xCoord + 1].power == this.power)
				);
	}
	public boolean canMoveUp() {
		return (
				(!(yCoord - 1 < 0)) && //If not out of bounds
				((Board.board[yCoord - 1][xCoord] == null)// If the sqaure above is null
				|| Board.board[yCoord - 1][xCoord].power == this.power)
				);
		
	}
	public boolean canMoveDown() {
		return ((!(yCoord + 1 > 3)) && 
				((Board.board[yCoord + 1][xCoord] == null)// If the sqaure below is null
				|| Board.board[yCoord + 1][xCoord].power == this.power)
				);
	}
	
	public static void printArray(Tile[][] matrix) {
	    for (int row = 0; row < matrix.length; row++) {
	        for (int column = 0; column < matrix[row].length; column++) {
	            //System.out.print(matrix[row][column] + " ");
	        }
	        //System.out.println();
	    }
	}
	


	
}
