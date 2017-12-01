import java.awt.*;

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
	static final double TILE_RATIO = 0.2;
	
	Color tileColor;
	 
	Tile(int boardSize, int powerOfTwo) {
		setSize((int)(boardSize * TILE_RATIO), (int)(boardSize * TILE_RATIO));
=======
	Tile(int powerOfTwo) {
		setSize(100, 100);
>>>>>>> sam
		setBackground(colorFromPower(powerOfTwo));
		tileColor = colorFromPower(powerOfTwo);
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
