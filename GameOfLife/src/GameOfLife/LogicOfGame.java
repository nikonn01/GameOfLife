package GameOfLife;
public class LogicOfGame {

	private int[]	currentField		= null;
	private int[]	newField			= null;
	private int		width, height;
	
	private int[]	neighborIN	= null; 
	private int[][]	neighborOFF	= null;
	

	public LogicOfGame ( int width, int height){	
		this.width = width;
		this.height = height;
		currentField = new int[width * height];
		newField = new int[width * height];
		neighborIN = new int[] {-width - 1, -width, -width + 1, -1, 1, width - 1, width, width + 1};
		neighborOFF = new int[][] { { -1, -1 }, { 0, -1 }, { 1, -1 }, { -1, 0 }, { 1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 } };
	}
	
	public void putCell1(int [] gridField) {
		currentField = gridField;
	}
	public void putCell(int x, int y, int c) {
		currentField[y * width + x] = c;
	}
	public  int [] currentField1(){
		return currentField;
	} 
	
	public  int [] simulate() {
		// Handle cells that don't touch edge of field
		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {
				int j = y * width  + x;
				int n = quantityNeighbors(j);
				newField[j] = changeCell(currentField[j], n);
			}
		}
 
		// handle edge cells of field
		// top and bottom cells
		for (int x = 0; x < width; x++) {
            int j = width * (height - 1);
            int n = quantityEdgeNeighbors(x, 0);
            newField[x] = changeCell(currentField[x], n);
            n = quantityEdgeNeighbors(x, height - 1);
            newField[x + j] = changeCell(currentField[x + j], n);
		}
		// edge left and right cells
            for (int y = 1; y < height - 1; y++) {
                int j = width * y;
                int n = quantityEdgeNeighbors(0, y);
                newField[j] = changeCell(currentField[j], n);
                n = quantityEdgeNeighbors(width - 1, y);
                newField[j + width - 1] = changeCell(currentField[j + width - 1], n);
            }
            int[] t = currentField;
            currentField = newField;
            newField = t;
		return currentField;
		
	}
	private int changeCell(int currentField, int value){
			return(currentField == 0 ? (value == 3 ? 1 : 0) : value == 2 || value == 3 ? 1 : 0);
	}
		
		// Calculate neighbors for the cells that don't touch edge of field
	private int quantityNeighbors (int q){
		byte n = 0;
		for (int i = 0; i < 8; i++) {
			n += currentField[q + neighborIN[i]];
		}
		return n;
			
	}
		// Calculate neighbors for the edge cells
	private int quantityEdgeNeighbors (int x, int y){
		int n = 0;
		for (int i = 0; i < 8; i++) {
			int bx = (x + neighborOFF[i][0] + width) % width;
			int by = (y + neighborOFF[i][1] + height) % height;
			n += currentField[by * width + bx];
		}
		return n;
	}
		
}


