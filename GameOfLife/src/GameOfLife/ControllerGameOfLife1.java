package GameOfLife;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class ControllerGameOfLife1 {
		final String NAME_OF_GAME = "Conway's Game of Life";
	    final int START_LOCATION = 200;
	    final int LIFE_WIDTH = 50;
	    final int LIFE_HEIGHT = 50;
	    final int SEED_SIZE = 10;
	    final int FIELD_SIZE = LIFE_WIDTH * SEED_SIZE + 7;
	    final int BTN_PANEL_HEIGHT = 58;
	    boolean[]	simulate1=new  boolean[LIFE_WIDTH*LIFE_HEIGHT];
	    int[] currentField2=new  int [LIFE_WIDTH*LIFE_HEIGHT];
	    int [] simulate1Int=new  int [LIFE_WIDTH*LIFE_HEIGHT];
	    volatile boolean NextGeneration = false; 
	    int showDelay = 200;
	    Canvas canvasPanel;
	    Random random = new Random();
	    LogicOfGame lifeSim=new LogicOfGame(LIFE_WIDTH, LIFE_HEIGHT);
	    InterfaceGameOfLife GUI=new InterfaceGameOfLife();
	    
	public static void main(String[] args) {
		
		 new ControllerGameOfLife1().run();
	}
	
	void run() {
        JFrame frame = GUI.Frame(); 
 
        canvasPanel = new Canvas();
        canvasPanel.setBackground(Color.white);
        canvasPanel.addMouseListener(new MouseAdapter()
              
        {
       	    //mouse event put and remove triangles on Canva     
            @Override
            public void mousePressed(MouseEvent event) {
            	int x = event.getX()/SEED_SIZE;
        	    int y = event.getY()/SEED_SIZE;
        	   //  System.out.println("screen(X,Y) = " + x + "," + y);
                 
                if (event.getButton() == MouseEvent.BUTTON1) {
                	lifeSim.putCell(x, y, 1);
                    canvasPanel.repaint();
                } else if (event.getButton() == MouseEvent.BUTTON3) {
                	lifeSim.putCell(x, y, 0);
                    canvasPanel.repaint();
                }
            }
        });
	
 
        JButton fillButton = GUI.fillButton();
        fillButton.addActionListener(new FillButtonListener());
 
        JButton stepButton = GUI.stepButton();
        stepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	lifeSim.simulate();
                canvasPanel.repaint();
            }
        });
 
        final JButton playButton = GUI.playButton();
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NextGeneration = !NextGeneration;
                playButton.setText(NextGeneration? "Stop" : "Play");
            }
        });
 
        JPanel btnPanel = new JPanel();
        btnPanel.add(fillButton);
        btnPanel.add(stepButton);
        btnPanel.add(playButton);
       
        frame.getContentPane().add(BorderLayout.CENTER, canvasPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, btnPanel);
        frame.setVisible(true);
 
        // endless loop of life
        while (true) {
            if (NextGeneration) {
            	lifeSim.simulate();
                canvasPanel.repaint();
                try {
                    Thread.sleep(showDelay);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
 
    // randomly fill cells
    public class FillButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
        
        	for (int y = 0; y < LIFE_HEIGHT; y++) {
    			for (int x = 0; x < LIFE_WIDTH; x++) {
    				int j = y * LIFE_WIDTH  + x;       
    			    simulate1[j]=random.nextBoolean();
    				simulate1Int[j]=boolToInt(simulate1[j]);
    			}
            }
        	
        	lifeSim.putCell1(simulate1Int);
        	canvasPanel.repaint();
        }
    }
    //change boolean variable to integer
    public int boolToInt(boolean b) {
        return b ? 1 : 0;
    }
    
    // paint on the canvas
    public class Canvas extends JPanel {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
        public void paint(Graphics g) {
            super.paint(g);
            currentField2=lifeSim.currentField1();
            for (int y = 0; y < LIFE_HEIGHT; y++) {
    			for (int x = 0; x < LIFE_WIDTH; x++) {
    				int j = y * LIFE_WIDTH  + x;
                    if (currentField2[j]==1) {
                        g.fillRect(x*SEED_SIZE, y*SEED_SIZE, SEED_SIZE, SEED_SIZE);
                    }
                }
            }
        }
    }
    

}