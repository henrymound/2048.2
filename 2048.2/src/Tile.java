import java.awt.*;

public class Tile extends Canvas {
	static final Color TILE_2_COLOR = new Color(236,218,195);
	static final Color TILE_4_COLOR = new Color(223,177,21);
	static final Color TILE_8_COLOR = new Color(233, 130, 75);
	static final Color TILE_16_COLOR = new Color(244, 112, 85);
	static final Color TILE_32_COLOR = new Color(231, 78, 51);
	
	 
	Tile(int powerOfTwo) {
		setSize(100, 100);
		setBackground(colorFromPower(powerOfTwo));
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
	

	
}
