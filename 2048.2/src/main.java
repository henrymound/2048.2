// Henry Mound and Sam Naumann
// Section B

// 2048.2

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*; 

public class main extends Applet {//implements ActionListener 

	public void init() {
		
		Button larger = new Button("Larger");
		add(larger);
		Tile tile = new Tile(1) ;
		add(tile);
		Tile tile2 = new Tile(2) ;
		add(tile2);
		Tile tile3 = new Tile(3) ;
		add(tile3);
		Tile tile4 = new Tile(4) ;
		add(tile4);
		Tile tile5 = new Tile(5) ;
		add(tile5);
		setSize(500, 500);
	}
	
}
