// Henry Mound and Sam Naumann
// Section B

// 2048.2

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*; 

public class main extends Applet {//implements ActionListener 

	public void init() {
		
		Board mainBoard = new Board();
		add(mainBoard);
		
		setBackground(Color.red);
		Tile tile = new Tile(1) ;
		mainBoard.add(tile);
		Tile tile2 = new Tile(2) ;
		mainBoard.add(tile2);
		Tile tile3 = new Tile(3) ;
		mainBoard.add(tile3);
		Tile tile4 = new Tile(4) ;
		mainBoard.add(tile4);
		Tile tile5 = new Tile(5) ;
		mainBoard.add(tile5);
		
		
		
		setSize(500, 500);
	}
	
}
