package GameOfLife;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class InterfaceGameOfLife {
		final String NAME_OF_GAME = "Conway's Game of Life";
	    final int START_LOCATION = 200;
	    final int LIFE_WIDTH = 50;
	    final int SEED_SIZE = 10;
	    final int FIELD_SIZE = LIFE_WIDTH * SEED_SIZE + 7;
	    final int BTN_PANEL_HEIGHT = 58; 
	    public Canvas canvasPanel;
	 
	    //Creating Frame for Game Of Life
	public JFrame Frame() {
        JFrame frame = new JFrame(NAME_OF_GAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FIELD_SIZE, FIELD_SIZE + BTN_PANEL_HEIGHT);
        frame.setLocation(START_LOCATION, START_LOCATION);
        frame.setResizable(false); 
        return frame;
	}
	//Creating Buttons 
	public JButton fillButton() {
 
        JButton fillButton = createSimpleButton("Fill");
        return fillButton;
	}
	
	public JButton stepButton() {
		 
        JButton stepButton = createSimpleButton("Step");
        return stepButton;
	}
	public JButton playButton() {
		 
        final JButton playButton = createSimpleButton("Play");
        return playButton;
	}
 //creating property of buttons
	private static JButton createSimpleButton(String text) {
		  JButton button = new JButton(text);
		  button.setForeground(Color.WHITE);
		  button.setBackground(Color.BLACK);
		  Border line = new LineBorder(Color.BLACK);
		  Border margin = new EmptyBorder(5, 15, 5, 15);
		  Border compound = new CompoundBorder(line, margin);
		  button.setBorder(compound);
		  return button;
	}
}
