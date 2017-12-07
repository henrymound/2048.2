import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Board extends Canvas {

	private static final long serialVersionUID = 1L;

	// instance variables
	public static Tile[][] board = new Tile[4][4];
	boolean board_full = false;
	boolean tileHasMoved = false;
	Image backBuffer;
	Graphics bBG;
	// constructor




	public void spawnRandTile() {
		boolean didSpawn = false;
		while (!didSpawn && board_full == false) {
			int xRand = (int) (Math.random() * 4);
			int yRand = (int) (Math.random() * 4);
			int powerRand = (int) (Math.random() * 2) + 1;
			if (board[yRand][xRand] == null) {
				board[yRand][xRand] = new Tile(powerRand, yRand, xRand);
				didSpawn = true;
				System.out.println("Spawned new tile at (" + yRand + ", " + xRand + ")");
			}

		}

	}

	Board() {
		setSize(500, 500);
		setBackground(Color.darkGray);

		spawnRandTile();

	}

	public void clearBoard() {
		board = new Tile[4][4];
	}

	public Tile[][] getBoard() {
		return board;
	}


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

	//Use update instead of paint to use DoubleBuffering
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

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);

		Dimension d = getSize();
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(0, 0, d.width, d.height);// Clear board;

		for (int i = 0; i < board.length; i++) {
			int yCoord = 0;
			yCoord = (int) (main.BOARD_SIZE * (1 - (Tile.TILE_RATIO * (5 - i))))
					+ (int) ((i + 1) * Tile.TILE_SPACING * main.BOARD_SIZE);

			for (int x = 0; x < board[i].length; x++) {
				if (board[i][x] != null) {
					board[i][x].tileColor = board[i][x].colorFromPower(board[i][x].power);
					int xCoord = (int) (main.BOARD_SIZE * (1 - (Tile.TILE_RATIO * (5 - x))))
							+ (int) ((x + 1) * Tile.TILE_SPACING * main.BOARD_SIZE);

					g2.setColor(board[i][x].tileColor);
					// g.drawRect(xCoord, yCoord, (int)(main.BOARD_SIZE * Tile.TILE_RATIO),
					// (int)(main.BOARD_SIZE * Tile.TILE_RATIO));
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

	public void moveTry(char c) {
		switch (c) {
		case 'w': {
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < 4; x++) {
					if (board[y][x] != null) {
						System.out.println("Checking if tile at (" + y + ", " + x + ") can move");

						board[y][x].canMove(c);
					}
				}
			}
			break;
		}
		case 'a': {
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					if (board[y][x] != null) {
						System.out.println("Checking if tile at (" + y + ", " + x + ") can move");

						board[y][x].canMove(c);
					}
				}
			}
			break;

		}
		case 's': {
			for (int y = 3; y >= 0; y--) {
				for (int x = 3; x >= 0; x--) {
					if (board[y][x] != null) {
						System.out.println("Checking if tile at (" + y + ", " + x + ") can move");

						board[y][x].canMove(c);
					}
				}
			}
			break;
		}
		case 'd': {
			for (int x = 3; x >= 0; x--) {
				for (int y = 3; y >= 0; y--) {
					if (board[y][x] != null) {
						System.out.println("Checking if tile at (" + y + ", " + x + ") can move");

						board[y][x].canMove(c);
					}
				}
			}
			break;

		}
		case 'e': {
			char e = 'e';
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

		/*
		 * for(int y = 0; y < 4; y++) { for(int x = 0; x < 4; x++) { if(board[y][x] !=
		 * null){ board[y][x].justMoved = false; } } }
		 */
		System.out.println(tileHasMoved);
		if (tileHasMoved == true) {
			System.out.println("move testing");
			spawnRandTile();
		}

	}

	public void move(Tile t, char c) {
		System.out.println("Checking if tile at (" + t.yCoord + ", " + t.xCoord + ") can move");
		t.canMove(c);
	}

	public void tileMoved() {
		tileHasMoved = true;
	}

	public void tileNotMoved() {
		tileHasMoved = false;
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

}
