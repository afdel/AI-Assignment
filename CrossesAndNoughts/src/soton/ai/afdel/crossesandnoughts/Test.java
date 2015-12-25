package soton.ai.afdel.crossesandnoughts;

public class Test {

	
	public static void main(String[] args) {
		
		
		BoardState boardState = new BoardState();
		boardState.setCellsState(new Long[]{1L,2L,1L,1L,
											2L,1L,1L,2L,
											0L,1L,2L,1L,
											1L,2L,2L,2L});
		
//				 [ 1,2,1,1] 
//				 [ 2,1,1,2] 
//				 [ 0,1,2,1] 
//				 [ 1,2,2,2]
		
		System.out.println( boardState.thereIsAwinner());
		
//		int rowsEvaluation = 0;
//		for(int i =1; i<5;i++){
//			// If a row contains just a player's sign count the number of signs
//			
//			// if row contains both signs
//			int rowEvaluation = 0;
//			
//			boolean rowHasFirstSign = false;
//			boolean rowHasSecondSign = false;
//			
//			Long signOccuring = 0L;
//			int signOccurrenceInRow = 0;
//			
//			for(int j =1; j<5;j++){
//				
//				Long cell = boardState.getCellsState()[(i-1)*4 + j - 1];
//				
//				if(cell.equals(1L)){
//					rowHasFirstSign = true;
//				}
//				if(cell.equals(2L)){
//					rowHasSecondSign = true;
//				}
//				
//				if(rowHasSecondSign & rowHasFirstSign){
//					break;
//				}
//				else if(!cell.equals(0L)){
//					signOccuring = cell;
//					signOccurrenceInRow++;
//				}
//			}
//			
//			if(rowHasSecondSign & rowHasFirstSign){
//				rowEvaluation = 0;
//			}
//			else{
//				rowEvaluation = signOccurrenceInRow==0?0:(signOccurrenceInRow==1?1:(signOccurrenceInRow==2?4:8));
//				if(signOccuring.equals(2L)){
//					rowEvaluation = -1*rowEvaluation;
//				}
//			}
//			Long[] cells = boardState.getCellsState();
//			int firstCellInRow = (i-1)*4;
//			System.out.println(" Row : ["+cells[firstCellInRow]+","+cells[firstCellInRow+1]+","+cells[firstCellInRow+2]+","+cells[firstCellInRow+3]+"] / Evaluation : "+rowEvaluation);
//			
//			rowsEvaluation = rowsEvaluation + rowEvaluation;
//		}
//		
//		System.out.println(rowsEvaluation);
		
		// explore columns
//				int columnsEvaluation = 0;
//				
//				for(int i =1; i<5;i++){
//				
//					int columnEvaluation = 0;
//					
//					boolean columnHasFirstSign = false;
//					boolean columnHasSecondSign = false;
//					
//					Long signOccuring = 0L;
//					int signOccurrenceInColumn = 0;
//					
//					for(int j =1; j<5;j++){
//						
//						Long cell = boardState.getCellsState()[(i-1)+ 4*(j-1)];
//						
//						if(cell.equals(1L)){
//							columnHasFirstSign = true;
//						}
//						if(cell.equals(2L)){
//							columnHasSecondSign = true;
//						}
//						
//						if(columnHasFirstSign & columnHasSecondSign){
//							break;
//						}
//						else if(!cell.equals(0L)){
//							signOccuring = cell;
//							signOccurrenceInColumn++;
//						}
//					}
//					
//					if(columnHasSecondSign & columnHasFirstSign){
//						columnEvaluation = 0;
//					}
//					else{
//						columnEvaluation = signOccurrenceInColumn==0?0:(signOccurrenceInColumn==1?1:(signOccurrenceInColumn==2?4:8));
//						if(signOccuring.equals(2L)){
//							columnEvaluation = -1*columnEvaluation;
//						}
//					}
//					columnsEvaluation = columnsEvaluation + columnEvaluation;
//					
//					Long[] cells = boardState.getCellsState();
//					int firstCellInColumn = i-1;
//					System.out.println(" Column : ["+cells[firstCellInColumn]+","+cells[firstCellInColumn+4]+","+cells[firstCellInColumn+8]+","+cells[firstCellInColumn+12]+"] / Evaluation : "+columnEvaluation);
//					
//				}
//		
//		System.out.println(columnsEvaluation);
		
		
		// Diagonal 1
//				int diag1Evaluation = 0;
//					
//				boolean diag1HasFirstSign = false;
//				boolean diag1HasSecondSign = false;
//					
//				Long signOccuringDiag1 = 0L;
//				int signOccurrenceInDiag1 = 0;
//					
//				int[] diag1CellsIdx = {0,5,10,15};
//				
//				for(int j =0; j<4;j++){
//						
//					Long cell = boardState.getCellsState()[diag1CellsIdx[j]];
//						
//						if(cell.equals(1L)){
//							diag1HasFirstSign = true;
//						}
//						if(cell.equals(2L)){
//							diag1HasSecondSign = true;
//						}
//						
//						if(diag1HasFirstSign & diag1HasSecondSign){
//							break;
//						}
//						else if(!cell.equals(0L)){
//							signOccuringDiag1 = cell;
//							signOccurrenceInDiag1++;
//						}
//					}
//					
//					if(diag1HasFirstSign & diag1HasSecondSign){
//						diag1Evaluation = 0;
//					}
//					else{
//						diag1Evaluation = signOccurrenceInDiag1==0?0:(signOccurrenceInDiag1==1?1:(signOccurrenceInDiag1==2?4:8));
//						if(signOccuringDiag1.equals(2L)){
//							diag1Evaluation = -1*diag1Evaluation;
//						}
//					}
//		
//				System.out.println(" Diagonal 1 : ["+boardState.getCellsState()[diag1CellsIdx[0]]+","
//													+boardState.getCellsState()[diag1CellsIdx[1]]+","
//													+boardState.getCellsState()[diag1CellsIdx[2]]+","
//													+boardState.getCellsState()[diag1CellsIdx[3]]+"] / Evaluation : "+diag1Evaluation);

		// Diagonal 2
//		int diag2Evaluation = 0;
//			
//		boolean diag2HasFirstSign = false;
//		boolean diag2HasSecondSign = false;
//			
//		Long signOccuringDiag2 = 0L;
//		int signOccurrenceInDiag2 = 0;
//			
//		int[] diag2CellsIdx = {3,6,9,12};
//		
//		for(int j =0; j<4;j++){
//				
//			Long cell = boardState.getCellsState()[diag2CellsIdx[j]];
//				
//				if(cell.equals(1L)){
//					diag2HasFirstSign = true;
//				}
//				if(cell.equals(2L)){
//					diag2HasSecondSign = true;
//				}
//				
//				if(diag2HasFirstSign & diag2HasSecondSign){
//					break;
//				}
//				else if(!cell.equals(0L)){
//					signOccuringDiag2 = cell;
//					signOccurrenceInDiag2++;
//				}
//			}
//			
//			if(diag2HasFirstSign & diag2HasSecondSign){
//				diag2Evaluation = 0;
//			}
//			else{
//				diag2Evaluation = signOccurrenceInDiag2==0?0:(signOccurrenceInDiag2==1?1:(signOccurrenceInDiag2==2?4:8));
//				if(signOccuringDiag2.equals(2L)){
//					diag2Evaluation = -1*diag2Evaluation;
//				}
//			}
//			System.out.println(" Diagonal 2 : ["+boardState.getCellsState()[diag2CellsIdx[0]]+","
//												+boardState.getCellsState()[diag2CellsIdx[1]]+","
//												+boardState.getCellsState()[diag2CellsIdx[2]]+","
//												+boardState.getCellsState()[diag2CellsIdx[3]]+"] / Evaluation : "+diag2Evaluation);	
		
	}
	
}
