package hand_of_god;

import javax.swing.JFrame;

/*
 * 
 * This class basically sets up the frame that the user
 * will play the game in.  Will be 400 units in height, and 
 * 800 units in length.
 * 
 * 
 */
public class Playing_Field extends JFrame {
	
	//Change either of these if you would like to adjust size of frame
	private int Height = 400;
	private int Width = 800;
	
	public Playing_Field(){
		setTitle("Hand of God");
		setSize(Width, Height);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
}
