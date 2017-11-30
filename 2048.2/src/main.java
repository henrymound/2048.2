// Henry Mound and Sam Naumann
// Section B

// 2048.2

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*; 

public class main extends Applet {//implements ActionListener 
	
	static final int BOARD_SIZE = 500;
	
	
	public void init() {
		
		Board mainBoard = new Board(BOARD_SIZE);
		add(mainBoard);
		
		setBackground(Color.red);

		
		
		
		
		setSize(500, 500);
	}
	
}
