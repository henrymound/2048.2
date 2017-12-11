import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Board extends Canvas {

	private static final long serialVersionUID = 1L;

	// instance variables
	public static Tile[][] board = new Tile[4][4];
	boolean board_full = false;
	boolean tileHasMoved = false;

	// constructor for beginning of the game
	Board() {
		setSize(500, 500);
		setBackground(Color.darkGray);

		spawnRandTile();

	}

	// repaint method that calls paint for clarity
	public void repaint() {

		paint(this.getGraphics());
	}

	// how to go about spawning a new tile
	// will not spawn on another tile, and won't spawn if the board is full
	public void spawnRandTile() {
		boolean didSpawn = false;
		while (!didSpawn && board_full == false) {
			int xRand = (int) (Math.random() * 4);
			int yRand = (int) (Math.random() * 4);
			int powerRand = (int) (Math.random() * 2) + 1;
			if (board[yRand][xRand] == null) {
				board[yRand][xRand] = new Tile(powerRand, yRand, xRand);
				didSpawn = true;
			}

		}

	}

	// clears the board
	public void clearBoard() {
		board = new Tile[4][4];
		board_full = false;
	}

	// getter for the board variable
	public Tile[][] getBoard() {
		return board;
	}

	// method that is called if the game is over and out puts a message accordingly
	public void paintGameOver() {
		Graphics g = this.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		g2.setFont(new Font("Helvetica", Font.BOLD, 60));
		g2.setColor(Color.WHITE);
		int tileCenter = ((int) (main.BOARD_SIZE) / 2);
		Rectangle2D titleOffset = (new Font("Helvetica", Font.BOLD, 60).getStringBounds("Game Over!",
				(g2).getFontRenderContext()));

		g2.drawString("Game Over!", tileCenter - (int) titleOffset.getWidth() / 2,
				tileCenter + (int) titleOffset.getHeight() / 2 - 10);
	}
	
	public void update() {
		//Get the current graphics element
		Graphics g = this.getGraphics();
		
		//Get the current dimension
		Dimension d = this.getSize();

		//Create a new image and graphics to be drawn over
		Image newImage = createImage(d.width, d.height);
		Graphics newGraphics = newImage.getGraphics();
		
		//Setup the new graphics to have the same gray background
		newGraphics.setColor(getBackground());
		newGraphics.fillRect(0, 0, d.width, d.height);
		
		//Redraw on newImage via paint with parameter newGraphics
		paint(newGraphics);
		
		//Draw the finished image all at once over the current image
		g.drawImage(newImage, 0, 0, this);
	}

	// paint method that draws the app
	public void paint(Graphics g) {

		// cast to 2D object to enable anti-aliasing
		Graphics2D g2 = (Graphics2D) g;

		// calls for anti aliasing of the board objects
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);

		// creates the background of board
		Dimension d = getSize();
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(0, 0, d.width, d.height);// Clear board;

		// drawing of the tiles on the board
		for (int i = 0; i < board.length; i++) {
			int yCoord = 0;
			yCoord = (int) (main.BOARD_SIZE * (1 - (Tile.TILE_RATIO * (5 - i))))
					+ (int) ((i + 1) * Tile.TILE_SPACING * main.BOARD_SIZE);

			// draws the color of the each tile and the numbers on top
			for (int x = 0; x < board[i].length; x++) {
				if (board[i][x] != null) {
					board[i][x].tileColor = board[i][x].colorFromPower(board[i][x].power);
					int xCoord = (int) (main.BOARD_SIZE * (1 - (Tile.TILE_RATIO * (5 - x))))
							+ (int) ((x + 1) * Tile.TILE_SPACING * main.BOARD_SIZE);

					g2.setColor(board[i][x].tileColor);

					g2.fillRoundRect(xCoord, yCoord, (int) (main.BOARD_SIZE * Tile.TILE_RATIO),
							(int) (main.BOARD_SIZE * Tile.TILE_RATIO), 20, 20);
					g2.setColor(board[i][x].textColorFromPower(board[i][x].power));
					g2.setFont(new Font("Helvetica", Font.BOLD, 40));

					int tileCenterX = (xCoord + (int) (main.BOARD_SIZE * Tile.TILE_RATIO) / 2);
					int tileCenterY = (yCoord + (int) (main.BOARD_SIZE * Tile.TILE_RATIO) / 2);
					Rectangle2D titleOffset = (new Font("Helvetica", Font.BOLD, 40)
							.getStringBounds(board[i][x].titleText + "", (g2).getFontRenderContext()));

					g2.drawString(board[i][x].titleText + "", tileCenterX - (int) titleOffset.getWidth() / 2,
							tileCenterY + (int) titleOffset.getHeight() / 2);
				}

			}
		}
	}

	// Initiates the checking of every block in the right order for movement
	public void moveTry(char c) {
		switch (c) {

		// if w check if each tile can move in the up direction, in the correct order
		case 'w': {
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < 4; x++) {
					if (board[y][x] != null) {

						board[y][x].canMove(c);
					}
				}
			}
			break;
		}

		// if a check if each tile can move in the left direction, in the correct order
		case 'a': {
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					if (board[y][x] != null) {

						board[y][x].canMove(c);
					}
				}
			}
			break;

		}

		// if s check if each tile can move in the down direction, in the correct order
		case 's': {
			for (int y = 3; y >= 0; y--) {
				for (int x = 3; x >= 0; x--) {
					if (board[y][x] != null) {

						board[y][x].canMove(c);
					}
				}
			}
			break;
		}

		// if d check if each tile can move in the right direction, in the correct order
		case 'd': {
			for (int x = 3; x >= 0; x--) {
				for (int y = 3; y >= 0; y--) {
					if (board[y][x] != null) {

						board[y][x].canMove(c);
					}
				}
			}
			break;

		}

		// if e check if each tile can move in the up&right direction, in the correct
		// order
		case 'e': {
			char e = 'e';

			// if statements for order instead of complex loops for readability
			if (board[3][3] != null)
				move(board[3][3], e);

			if (board[3][2] != null)
				move(board[3][2], e);
			if (board[2][3] != null)
				move(board[2][3], e);

			if (board[3][1] != null)
				move(board[3][1], e);
			if (board[2][2] != null)
				move(board[2][2], e);
			if (board[1][3] != null)
				move(board[1][3], e);

			if (board[3][0] != null)
				move(board[3][0], e);
			if (board[2][1] != null)
				move(board[2][1], e);
			if (board[1][2] != null)
				move(board[1][2], e);
			if (board[0][3] != null)
				move(board[0][3], e);

			if (board[2][0] != null)
				move(board[2][0], e);
			if (board[1][1] != null)
				move(board[1][1], e);
			if (board[0][2] != null)
				move(board[0][2], e);

			if (board[1][0] != null)
				move(board[1][0], e);
			if (board[0][1] != null)
				move(board[0][1], e);

			if (board[0][0] != null)
				move(board[0][0], e);

			break;

		}

		// if q check if each tile can move in the up&left direction, in the correct
		// order
		case 'q': {
			char e = 'q';

			if (board[0][0] != null)
				move(board[0][0], e);

			if (board[0][1] != null)
				move(board[0][1], e);
			if (board[1][0] != null)
				move(board[1][0], e);

			if (board[0][2] != null)
				move(board[0][2], e);
			if (board[1][1] != null)
				move(board[1][1], e);
			if (board[2][0] != null)
				move(board[2][0], e);

			if (board[0][3] != null)
				move(board[0][3], e);
			if (board[1][2] != null)
				move(board[1][2], e);
			if (board[2][1] != null)
				move(board[2][1], e);
			if (board[3][0] != null)
				move(board[3][0], e);

			if (board[1][3] != null)
				move(board[1][3], e);
			if (board[2][2] != null)
				move(board[2][2], e);
			if (board[3][1] != null)
				move(board[3][1], e);

			if (board[2][3] != null)
				move(board[2][3], e);
			if (board[3][2] != null)
				move(board[3][2], e);

			if (board[3][3] != null)
				move(board[3][3], e);
			break;

		}

		// if c check if each tile can move in the down&right direction, in the correct
		// order
		case 'c': {
			char e = 'c';
			if (board[3][0] != null)
				move(board[3][0], e);

			if (board[2][0] != null)
				move(board[2][0], e);
			if (board[3][1] != null)
				move(board[3][1], e);

			if (board[1][0] != null)
				move(board[1][0], e);
			if (board[2][1] != null)
				move(board[2][1], e);
			if (board[3][2] != null)
				move(board[3][2], e);

			if (board[0][0] != null)
				move(board[0][0], e);
			if (board[1][1] != null)
				move(board[1][1], e);
			if (board[2][2] != null)
				move(board[2][2], e);
			if (board[3][3] != null)
				move(board[3][3], e);

			if (board[0][1] != null)
				move(board[0][1], e);
			if (board[1][2] != null)
				move(board[1][2], e);
			if (board[3][2] != null)
				move(board[3][2], e);

			if (board[0][2] != null)
				move(board[0][2], e);
			if (board[1][3] != null)
				move(board[1][3], e);

			if (board[0][3] != null)
				move(board[0][3], e);

			break;

		}

		// if z check if each tile can move in the down&left direction, in the correct
		// order
		case 'z': {
			char e = 'z';
			if (board[0][0] != null)
				move(board[0][0], e);

			if (board[0][1] != null)
				move(board[0][1], e);
			if (board[1][0] != null)
				move(board[1][0], e);

			if (board[0][2] != null)
				move(board[0][2], e);
			if (board[1][1] != null)
				move(board[1][1], e);
			if (board[2][0] != null)
				move(board[2][0], e);

			if (board[0][3] != null)
				move(board[0][3], e);
			if (board[1][2] != null)
				move(board[1][2], e);
			if (board[2][1] != null)
				move(board[2][1], e);
			if (board[3][0] != null)
				move(board[3][0], e);

			if (board[1][3] != null)
				move(board[1][3], e);
			if (board[2][2] != null)
				move(board[2][2], e);
			if (board[3][1] != null)
				move(board[3][1], e);

			if (board[2][3] != null)
				move(board[2][3], e);
			if (board[3][2] != null)
				move(board[3][2], e);

			if (board[3][3] != null)
				move(board[3][3], e);

			break;

		}
		default: {

		}

		}

		// if a move was successful then add a new tile
		if (tileHasMoved == true) {
			spawnRandTile();
		}

	}

	// helper function to create clarity in the if statements that calls canMove()
	public void move(Tile t, char c) {
		t.canMove(c);
	}

	// setter for HasMoved variable
	public void tileMoved() {
		tileHasMoved = true;
	}

	// setter for HasMoved Variable
	public void tileNotMoved() {
		tileHasMoved = false;
	}

	// function that test if the board is full
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