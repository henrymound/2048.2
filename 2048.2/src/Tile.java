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
	
<<<<<<< HEAD
	Tile(int powerOfTwo) {
		setSize(100, 100);
=======
	static final double TILE_RATIO = 0.2;
	static final double TILE_SPACING = (1 - (TILE_RATIO * 4))/5;
	Color tileColor;
	int titleText;
	int xCoord;
	int yCoord; 
	int power;
	boolean justMoved = false;
	
	Tile(int powerOfTwo, int xCoord, int yCoord) {
		setSize((int)(main.BOARD_SIZE * TILE_RATIO), (int)(main.BOARD_SIZE * TILE_RATIO));
>>>>>>> master
		setBackground(colorFromPower(powerOfTwo));
		tileColor = colorFromPower(powerOfTwo);
		titleText = (int) Math.pow(2, powerOfTwo);
		this.xCoord = xCoord;
		power = powerOfTwo;
		this.yCoord = yCoord;
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
	  			System.out.println("Can move");
	  			Tile[][] tempArray = Board.board;
	  			if(Board.board[yCoord - 1][xCoord] != null && Board.board[yCoord - 1][xCoord].power == this.power) {
	  				this.power++;
	  			}
	  				Board.board[yCoord - 1][xCoord] = this;
	  				Board.board[yCoord][xCoord] = null;
	  			yCoord -= 1;
	  			printArray(Board.board);
	  		}
			
			break;
		}
		case 's':{
			while(canMoveDown()) {
	  			System.out.println("Can move");
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
	  			System.out.println("Can move");
	  			Tile[][] tempArray = Board.board;
	  			tempArray[yCoord][xCoord + 1] = this;
	  			tempArray[yCoord][xCoord] = null;
	  			xCoord += 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);
	  		}
			break;
		}
		

		case 'a':{

			while(canMoveLeft()) {
	  			System.out.println("Can move");
	  			Tile[][] tempArray = Board.board;
	  			tempArray[yCoord][xCoord - 1] = this;
	  			tempArray[yCoord][xCoord] = null;
	  			xCoord -= 1;
	  			Board.board = tempArray;
	  			printArray(Board.board);
	  		}
			break;
		}
		

		case 'q':{

			while(canMoveUp() && canMoveLeft()) {
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
			
		}
	     
			main.mainBoard.repaint();

	    System.out.println(c);
	      
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
		return ((!(yCoord - 1 < 0)) && //If not out of bounds
				((Board.board[yCoord - 1][xCoord] == null)// If the sqaure above is null
				|| Board.board[yCoord - 1][xCoord].power == this.power)
				);
		
	}
	public boolean canMoveDown() {
		return ((!(yCoord + 1 > 3)) && 
				((Board.board[yCoord + 1][xCoord] == null)// If the sqaure above is null
				|| Board.board[yCoord + 1][xCoord].power == this.power)
				);
	}
	
	public static void printArray(Tile[][] matrix) {
	    for (int row = 0; row < matrix.length; row++) {
	        for (int column = 0; column < matrix[row].length; column++) {
	            System.out.print(matrix[row][column] + " ");
	        }
	        System.out.println();
	    }
	}
	


	
}
