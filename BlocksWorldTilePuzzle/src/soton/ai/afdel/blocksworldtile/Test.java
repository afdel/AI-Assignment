package soton.ai.afdel.blocksworldtile;

public class Test {

	static int gridSize = 4;
	
	public static void main(String[] args) {
		
		
		System.out.println(diffTwoPoints(12, 16));
		
	}
	
	
	
	private static int diffTwoPoints(int point1, int point2){
		
		int diff = 0;
		
		int diffCol = getColumn(point1) - getColumn(point2);
		int diffRow = getRow(point1) - getRow(point2);
		
//		System.out.println(" Diff col : "+Math.abs(diffCol));
//		System.out.println(" Diff row : "+Math.abs(diffRow));
		
		diff = Math.abs(diffCol) + Math.abs(diffRow);
		
		return diff;
	}



	private static int getColumn(int point) {
		
		int mod = point%gridSize;
		int col = (mod==0?gridSize:mod);
//		System.out.println(" Column "+point+" = "+col);
		return col;
	}



	private static int getRow(int point) {

		int row = (point/gridSize);
		
		if( point%gridSize != 0){
			row++;
		}
		
//		System.out.println(" Row "+point+" = "+row);
		return row;
	}
	
}
