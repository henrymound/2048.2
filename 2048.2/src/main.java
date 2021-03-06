// Henry Mound and Sam Naumann
// Section B

// 2048.2

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class main extends Applet implements KeyListener, ItemListener, MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;

	// variables that dictate constants for window
	static final int BOARD_SIZE = 500;
	static final int APPLET_SIZE = 700;
	static Font preferredFont = new Font("Helvetica", Font.PLAIN, 20);
	static Board mainBoard;
	Checkbox classicBox;
	Checkbox retroBox;
	public static int score = 0;
	public static Label moveLabel;
	Label scoreLabel;
	Panel mainPanel;

	// variables regarding AI
	Button AIPlayButton;
	Button RestartButton;
	boolean AIPlaying = false;

	// init function that the applet looks for on startup
	// initializes or declares applet objects that add functionality to program
	public void init() {

		// creates a checkboxgroup to contain our two check boxes for themes
		CheckboxGroup themeGroup = new CheckboxGroup();
		makeClassicBox(themeGroup);
		makeRetroBox(themeGroup);
		
		// makes a label that will display the AI's moves 
		makeMoveLabel();
		
		// creates object that will take and display the score
		makeScoreLabel();

		// make north panel into a three box gridLayout
		Panel northPanel = new Panel();
		northPanel.setLayout(new GridLayout(1, 3));

		// add theme panel to the north panel
		northPanel.add(makeThemePanel());

		// declaring the board object that holds our 2D array
		// and the objects that contain it
		mainBoard = new Board();
		BorderLayout mainLayout = new BorderLayout();
		add(makeMainPanel(mainLayout, northPanel));
		setBackground(Color.GRAY);

		// placement of title
		northPanel.add(makeTitlePanel());
		northPanel.add(scoreLabel);
		
		// add the panel that displays score to the north panel
        northPanel.add(makeScoreMovePanel());

        
		// adding listeners and making sure the game understands when it is clicked on
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		mainBoard.addMouseListener(this);
		requestFocusInWindow();
		setSize(APPLET_SIZE + 50, APPLET_SIZE - 50);

	}
	
	// helper methods that create various objects to be added to the mainpanel 
	
	// creates the panel the panel that title rests on
	public Panel makeTitlePanel() {
		Panel titlePanel = new Panel();
		Label titleLabel = new Label("2048.2", Label.CENTER);
		titleLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
		titleLabel.setForeground(Color.WHITE);
		titlePanel.add(titleLabel, BorderLayout.NORTH);
		return titlePanel;
	}
	
	// creates the panel that the score is shown on
	public Panel makeScoreMovePanel() {
		Panel scoreMovePanel = new Panel();
        scoreMovePanel.setLayout(new GridLayout(2, 1));
        scoreMovePanel.add(scoreLabel);
        scoreMovePanel.add(moveLabel);
		return scoreMovePanel;
	}
	
	// makes the panel that the board rests on
	public Panel makeBoardPanel() {
		Panel boardPanel = new Panel();
		boardPanel.setSize(BOARD_SIZE, BOARD_SIZE);
		boardPanel.add(mainBoard);
		boardPanel.addMouseListener(this);
		return boardPanel;
	}
	
	// makes the checkBox that puts the theme into classic
	public Checkbox makeClassicBox(CheckboxGroup themeGroup){
		classicBox = new Checkbox("Classic", themeGroup, true);
		classicBox.setForeground(Color.white);
		classicBox.addItemListener(this);
		return classicBox;
		
	}
	
	// makes the checkBox that puts the theme into retro
	public Checkbox makeRetroBox(CheckboxGroup themeGroup) {
		retroBox = new Checkbox("Retro", themeGroup, false);
		retroBox.setForeground(Color.white);
		retroBox.addItemListener(this);
		return retroBox;
	}
	
	// makes button that restarts the game
	public Button makeRestartButton() {
		RestartButton = new Button("Restart");
		RestartButton.addActionListener(this);
		RestartButton.setForeground(Color.BLACK);
		return RestartButton;
	}
	
	// makes the button that causes the AI to play or stop
	public Button makeAIPlayButton() {
		AIPlayButton = new Button("AI Play");
		AIPlayButton.addActionListener(this);
		AIPlayButton.setForeground(Color.BLACK);
		return AIPlayButton;
	}
	
	// makes the panel that most objects lay on and creates a black border
	public Panel makeMainPanel(BorderLayout mainLayout, Panel northPanel) {
		mainPanel = new Panel();
		mainPanel.setBackground(Color.black);
		mainPanel.setLayout(mainLayout);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(makeBoardPanel(), BorderLayout.CENTER);
		return mainPanel;
	}
	
	// makes the panel that the latest AI move is displayed on
	public Label makeMoveLabel() {
		moveLabel = new Label("");
		moveLabel.setForeground(Color.white);
		moveLabel.setFont(preferredFont);
		moveLabel.setAlignment(Label.RIGHT);
		return moveLabel;
	}
	
	// makes the Label that displays the score
	public Label makeScoreLabel() {
		scoreLabel = new Label("Score: 0 ");
		scoreLabel.setForeground(Color.white);
		scoreLabel.setFont(preferredFont);
		scoreLabel.setAlignment(Label.RIGHT);
		return scoreLabel;
	}
	
	// makes the panel that holds the buttons and theme checkboxes
	public Panel makeThemePanel() {
		Panel themePanel = new Panel();
		themePanel.setLayout(new GridLayout(4, 1));
		themePanel.add(classicBox);
		themePanel.add(retroBox);
		themePanel.add(makeAIPlayButton());
		themePanel.add(makeRestartButton());
		return themePanel;
	}
	

	// Other methods in the main class
	
	// handles the when a key is pressed
	public void keyPressed(KeyEvent e) {

		// reads when the board is full
		mainBoard.board_full = mainBoard.is_Full();

		// acquires the char that is read from the keyboard
		char c = e.getKeyChar();

		// handles above char
		if (c != KeyEvent.CHAR_UNDEFINED) {
			mainBoard.tileNotMoved(); // resets the variable for tiles moved
			mainBoard.moveTry(c); // try to move in the correct direction which is the key passed

		} else {
			System.out.println("Char undefined"); // makes sure nothing breaks if wrong key pressed
		}
		e.consume();

		// handles if the user decides to use the number keys
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_UP:
			mainBoard.moveTry('w');
			break;
		case KeyEvent.VK_DOWN:
			mainBoard.moveTry('s');
			break;
		case KeyEvent.VK_LEFT:
			mainBoard.moveTry('a');
			break;
		case KeyEvent.VK_RIGHT:
			mainBoard.moveTry('d');
			break;
		}

		// displays score
		scoreLabel.setText("Score: " + score + " ");
		scoreLabel.setAlignment(Label.RIGHT);

		// calls update now that all the variables are in order
		main.mainBoard.update();

		// calls the game over method after updating the board
		if (gameOver()) {
			mainBoard.paintGameOver();
		}

	}

	// method determines if the game is over if the board is full
	// it tests if any brick can move in any direction using our other methods
	public static boolean gameOver() {
		if (mainBoard.is_Full()) {
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < 4; x++) {
					if (!(mainBoard.getBoard()[y][x].canMoveLeft()) && !(mainBoard.getBoard()[y][x].canMoveDown())
							&& !(mainBoard.getBoard()[y][x].canMoveRight()) && !(mainBoard.getBoard()[y][x].canMoveE())
							&& !(mainBoard.getBoard()[y][x].canMoveQ()) && !(mainBoard.getBoard()[y][x].canMoveZ())
							&& !(mainBoard.getBoard()[y][x].canMoveC()) && !(mainBoard.getBoard()[y][x].canMoveUp())) {
					} else {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	// AI is desperate for a move because its primary function (attempt to move
	// up into the top left corner) is unavailable
	public static boolean desperate() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (mainBoard.getBoard()[y][x] != null) {
					if (!(mainBoard.getBoard()[y][x].canMoveLeft()) && !(mainBoard.getBoard()[y][x].canMoveUp())
							&& !(mainBoard.getBoard()[y][x].canMoveQ())) {
					} else {
						return false;
					}
				}

			}
		}
		return true;

	}

	// blank methods that are required for keyListener
	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	// determines if our theme options have been pressed and acts accordingly
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();

		if (source == retroBox) {
			mainPanel.setBackground(Color.BLACK);

			Tile.TILE_2_COLOR = new Color(253, 255, 0);
			Tile.TILE_4_COLOR = new Color(234, 130, 229);
			Tile.TILE_8_COLOR = new Color(70, 191, 238);
			Tile.TILE_16_COLOR = new Color(208, 62, 25);
			Tile.TILE_32_COLOR = new Color(219, 133, 28);
			Tile.TILE_64_COLOR = Color.blue;
			Tile.TILE_128_COLOR = new Color(251, 46, 1);
			Tile.TILE_256_COLOR = new Color(111, 203, 159);
			Tile.TILE_512_COLOR = new Color(212, 238, 94);
			Tile.TILE_1024_COLOR = new Color(255, 66, 66);
			Tile.TILE_2048_COLOR = new Color(5, 135, 137);
			Tile.themeTextColor = Color.WHITE;
			Tile.themeBefore2 = Color.BLACK;
			mainBoard.update();

			// gets the applet to respond to key presses
			requestFocusInWindow();
		} else if (source == classicBox) {
			System.out.println("Classic Box");

			mainPanel.setBackground(Color.gray);
			Tile.TILE_2_COLOR = new Color(234, 227, 217);
			Tile.TILE_4_COLOR = new Color(232, 223, 201);
			Tile.TILE_8_COLOR = new Color(225, 175, 123);
			Tile.TILE_16_COLOR = new Color(222, 148, 103);
			Tile.TILE_32_COLOR = new Color(218, 123, 97);
			Tile.TILE_64_COLOR = new Color(215, 95, 63);
			Tile.TILE_128_COLOR = new Color(227, 203, 106);
			Tile.TILE_256_COLOR = new Color(229, 206, 118);
			Tile.TILE_512_COLOR = new Color(219, 190, 61);
			Tile.TILE_1024_COLOR = new Color(215, 182, 49);
			Tile.TILE_2048_COLOR = new Color(225, 193, 67);
			Tile.themeTextColor = Color.white;
			Tile.themeBefore2 = new Color(116, 109, 101);

			mainBoard.update();

			// gets the applet to respond to key presses
			requestFocusInWindow();
		}
	}

	// generated unused methods for mouseListener

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		requestFocusInWindow();
		System.out.println("Mouse pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		requestFocusInWindow();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		requestFocusInWindow();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		requestFocusInWindow();
	}

	// This is the mouse Listener method that is used
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		requestFocusInWindow();
		System.out.println("Mouse clicked");

	}

	// handles if the AI or Restart buttons are clicked
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == RestartButton) {
			scoreLabel.setText("Score: 0 ");
			score = 0;
			mainBoard.clearBoard();
			mainBoard.spawnRandTile();
			mainBoard.update();
		}

		// this is the AI playing method
		if (e.getSource() == AIPlayButton) {

			// if statement that either changes the AI button based on state
			if (AIPlaying) {
				AIPlayButton.setLabel("AI Play");
				AIPlaying = false;
			} else {
				AIPlayButton.setLabel("Stop AI Play");
				AIPlaying = true;

				// based on the state of the label the below method determines the AI actions
				Timer t = new Timer(); // timer that makes the AI run based on the label state
				t.schedule(new TimerTask() {
					@Override
					public void run() {
						if (AIPlaying) {

							// try to move into the top left corner
							// if desperate == true then try other movements
							for (int y = 0; y < 4; y++) {
								for (int x = 0; x < 4; x++) {
									if (mainBoard.getBoard()[y][x] != null) {

										if (mainBoard.getBoard()[y][x].canMoveQ()) {
											mainBoard.moveTry('q');
										} else if (mainBoard.getBoard()[y][x].canMoveUp()) {
											mainBoard.moveTry('w');
										} else if (mainBoard.getBoard()[y][x].canMoveLeft()) {
											mainBoard.moveTry('a');
										} else if (desperate() && mainBoard.getBoard()[y][x] != null) {
											if (mainBoard.getBoard()[y][x].canMoveE()) {
												mainBoard.moveTry('e');
											} else if (mainBoard.getBoard()[y][x].canMoveRight()) {
												mainBoard.moveTry('d');
											} else if (mainBoard.getBoard()[y][x].canMoveC()) {
												mainBoard.moveTry('c');
											} else if (mainBoard.getBoard()[y][x].canMoveZ()) {
												mainBoard.moveTry('z');
											} else if (mainBoard.getBoard()[y][x].canMoveDown()) {
												mainBoard.moveTry('s');
											}
										}

										// stop AI if game is over
										if (gameOver()) {
											mainBoard.paintGameOver();
											AIPlaying = false;
											AIPlayButton.setLabel("AI Play");
										}

									}

								}
							}
							mainBoard.update();						scoreLabel.setText("Score: " + score + " ");
							scoreLabel.setText("Score: " + score + " ");

						}
					}
				}, 0, 200);

			}
		}
	}
}