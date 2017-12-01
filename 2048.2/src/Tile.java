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
	boolean justMoved = false;
	
	Tile(int powerOfTwo, int xCoord, int yCoord) {
		setSize((int)(main.BOARD_SIZE * TILE_RATIO), (int)(main.BOARD_SIZE * TILE_RATIO));
		setBackground(colorFromPower(powerOfTwo));
		tileColor = colorFromPower(powerOfTwo);
		titleText = (int) Math.pow(2, powerOfTwo);
		this.xCoord = xCoord;
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
			if((!(yCoord - 1 < 0)) && Board.board[yCoord - 1][xCoord] == null) {
	  			System.out.println("Can move");
	  			Tile[][] tempArray = Board.board;
	  			tempArray[yCoord - 1][xCoord] = this;
	  			tempArray[yCoord][xCoord] = null;
	  			yCoord -= 1;
	  			Board.board = tempArray;
	  			main.mainBoard.repaint();
	  			printArray(Board.board);
	  		}else if((yCoord - 1 < 0)){
	  			System.out.println("Cant move because yCoord is less than 0");
	  		}
			
			break;
		}
		case 's':{
			if((!(yCoord + 1 > 3)) && Board.board[yCoord + 1][xCoord] == null) {
	  			System.out.println("Can move");
	  			Tile[][] tempArray = Board.board;
	  			tempArray[yCoord + 1][xCoord] = this;
	  			tempArray[yCoord][xCoord] = null;
	  			yCoord += 1;
	  			Board.board = tempArray;
	  			main.mainBoard.repaint();
	  			printArray(Board.board);
	  		}else if((yCoord + 1 > 3)){
	  			System.out.println("Cant move because yCoord is greater than 3, it is " + yCoord);
	  		}
			break;
		}

		case 'd':{
			if((!(xCoord + 1 > 3)) && Board.board[yCoord][xCoord + 1] == null) {
	  			System.out.println("Can move");
	  			Tile[][] tempArray = Board.board;
	  			tempArray[yCoord][xCoord + 1] = this;
	  			tempArray[yCoord][xCoord] = null;
	  			xCoord += 1;
	  			Board.board = tempArray;
	  			main.mainBoard.repaint();
	  			printArray(Board.board);
	  		}else if((xCoord + 1 > 3)){
	  			System.out.println("Cant move because xCoord is greater than 3, it is " + yCoord);
	  		}
			break;
		}
		

		case 'a':{

			if((!(xCoord - 1 < 0)) && Board.board[xCoord][xCoord - 1] == null) {
	  			System.out.println("Can move");
	  			Tile[][] tempArray = Board.board;
	  			tempArray[yCoord][xCoord - 1] = this;
	  			tempArray[yCoord][xCoord] = null;
	  			xCoord -= 1;
	  			Board.board = tempArray;
	  			main.mainBoard.repaint();
	  			printArray(Board.board);
	  		}else if((xCoord - 1 < 0)){
	  			System.out.println("Cant move because yCoord is less than 0");
	  		}
			
		}
			
		}
	     

		justMoved = true;
	    System.out.println(c);
	      
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
