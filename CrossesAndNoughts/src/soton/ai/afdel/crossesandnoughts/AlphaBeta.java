package soton.ai.afdel.crossesandnoughts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AlphaBeta {

	
	/*
	 * 
	 * Max is the first to play
	 * Max plays 1 ad Min plays 2
	 * 
	 */
	
	private static int minimaxDepth = 9;
	
	private static Move moveToGuaranteeBestScore;
	
	private static int statesEvaluated = 0;
	
	public static void main(String[] args) {
		
		BoardState initialBoardState = new BoardState();
		BoardState boardState = initialBoardState;
		
		int ply = 0;
		
		Long signToPlay = 0L;
		Move nextMove = null;
		
		while( !boardState.isTheEnd()){
			
			signToPlay = ply%2 == 0?1L:2L;
			
			nextMove = signToPlay.equals(1L)?calculateTheNextMove1(boardState):calculateTheNextMove2(boardState);
			boardState.apply(nextMove);
			System.out.println(" ########################################################################## ");
			System.out.println(boardState);
			System.out.println(" ########################################################################## ");
			
			ply++;
		}
		
		Long winner = boardState.thereIsAwinner();
		if( winner.equals(0L)){
			System.out.println(" DRAW ");
		}
		else{
			System.out.println(" The Winner is : "+winner);
		}
		
	}


	private static Move calculateTheNextMove1(BoardState boardState) {

		System.out.println(" ##### Calculating Next Move .............. ");
		// This will update the 
		maxValue1( boardState, -1000000, 1000000, minimaxDepth);
		
		System.out.println(" State Evaluated : "+statesEvaluated);
		statesEvaluated = 0;
		System.out.println(" ######## Next Move Calculated ######## ");
		
		return moveToGuaranteeBestScore;
		
	}


	private static Move calculateTheNextMove2(BoardState boardState) {

		// Randomly
		List<Long> emptyCells = boardState.getEmptyCells();
		
		Move move = new Move();
		move.setSignPosition(emptyCells.get(0).intValue());
		move.setSignToPlay(2L);
		
		
		return move;
	}


//	private static Move calculateTheNextMove2(BoardState boardState) {
//
//		// Randomly
//		List<Long> emptyCells = boardState.getEmptyCells();
//		
//		Random r = new Random();
//		int randomIdx = r.nextInt(emptyCells.size());
//		
//		Move move = new Move();
//		move.setSignPosition(emptyCells.get(randomIdx).intValue());
//		move.setSignToPlay(2L);
//		
//		
//		
//		
//		
//		return move;
//	}


	private static int maxValue1(BoardState boardState,int alpha, int beta, int depth){
		
		if( depth == 0){
			return evaluateState1(boardState, 1L);
		}
		if( boardState.isTheEnd()){
			return evaluateState1(boardState, 0L);
		}
		
		
		for(BoardState successorState : generateSuccessors( boardState, 1L)){
			
			int successorEvaluation = minValue1(successorState,alpha,beta,depth-1);
			if( successorEvaluation>alpha){
				alpha = successorEvaluation;
				if( depth == minimaxDepth){
					moveToGuaranteeBestScore = successorState.getMoveToGetHere();
				}
			}
			
			if( alpha >= beta){
				return alpha;
			}
		}
		
		return alpha;
		
	}


	private static int minValue1(BoardState boardState, int alpha, int beta, int depth){
		
		if( depth == 0){
			return evaluateState1(boardState, 2L);
		}
		if( boardState.isTheEnd()){
			return evaluateState1(boardState, 0L);
		}
		
		for(BoardState successorState : generateSuccessors( boardState, 2L)){
			
			int successorEvaluation = maxValue1(successorState,alpha,beta,depth-1);
			if( successorEvaluation<beta){
				beta = successorEvaluation;
			}
			if( beta <= alpha){
				
			}
		}
		
		return beta;
		
	}
	
	
	// First Player's evaluation of the board
	private static int evaluateState1(BoardState boardState, Long signToPlay) {

		// A sign equal to zero means the state is a terminal one
		statesEvaluated++;
		
		if(signToPlay.equals(0L)){
			
			Long winner = boardState.thereIsAwinner();
			if(winner.equals(1L)){ // First Player is the winner
				return 1000;
			}
			else if(winner.equals(2L)){ // Second Player is the winner
				return -1000;
			}
			else{ // Draw
				return 0;
			}
		}
		
		
		int evaluation = 0;
		
		
		// explore rows
		int rowsEvaluation = 0;
		
		for(int i =1; i<5;i++){
		
			int rowEvaluation = 0;
			
			boolean rowHasFirstSign = false;
			boolean rowHasSecondSign = false;
			
			Long signOccuring = 0L;
			int signOccurrenceInRow = 0;
			
			for(int j =1; j<5;j++){
				
				Long cell = boardState.getCellsState()[(i-1)*4 + j - 1];
				
				if(cell.equals(1L)){
					rowHasFirstSign = true;
				}
				if(cell.equals(2L)){
					rowHasSecondSign = true;
				}
				
				if(rowHasSecondSign & rowHasFirstSign){
					break;
				}
				else if(!cell.equals(0L)){
					signOccuring = cell;
					signOccurrenceInRow++;
				}
			}
			
			if(rowHasSecondSign & rowHasFirstSign){
				rowEvaluation = 0;
			}
			else{
				rowEvaluation = signOccurrenceInRow==0?0:(signOccurrenceInRow==1?1:(signOccurrenceInRow==2?4:8));
				if(signOccuring.equals(2L)){
					rowEvaluation = -1*rowEvaluation;
				}
			}
			rowsEvaluation = rowsEvaluation + rowEvaluation;
		}
		
		// explore columns
		int columnsEvaluation = 0;
		
		for(int i =1; i<5;i++){
		
			int columnEvaluation = 0;
			
			boolean columnHasFirstSign = false;
			boolean columnHasSecondSign = false;
			
			Long signOccuring = 0L;
			int signOccurrenceInColumn = 0;
			
			for(int j =1; j<5;j++){
				
				Long cell = boardState.getCellsState()[(i-1)+ 4*(j-1)];
				
				if(cell.equals(1L)){
					columnHasFirstSign = true;
				}
				if(cell.equals(2L)){
					columnHasSecondSign = true;
				}
				
				if(columnHasFirstSign & columnHasSecondSign){
					break;
				}
				else if(!cell.equals(0L)){
					signOccuring = cell;
					signOccurrenceInColumn++;
				}
			}
			
			if(columnHasSecondSign & columnHasFirstSign){
				columnEvaluation = 0;
			}
			else{
				columnEvaluation = signOccurrenceInColumn==0?0:(signOccurrenceInColumn==1?1:(signOccurrenceInColumn==2?4:8));
				if(signOccuring.equals(2L)){
					columnEvaluation = -1*columnEvaluation;
				}
			}
			columnsEvaluation = columnsEvaluation + columnEvaluation;
		}		
		
		// Explore Diagonals
		int diagsEvaluation = 0;
		
		// Diagonal 1
		int diag1Evaluation = 0;
			
		boolean diag1HasFirstSign = false;
		boolean diag1HasSecondSign = false;
			
		Long signOccuringDiag1 = 0L;
		int signOccurrenceInDiag1 = 0;
			
		int[] diag1CellsIdx = {0,5,10,15};
		
		for(int j =0; j<4;j++){
				
			Long cell = boardState.getCellsState()[diag1CellsIdx[j]];
				
				if(cell.equals(1L)){
					diag1HasFirstSign = true;
				}
				if(cell.equals(2L)){
					diag1HasSecondSign = true;
				}
				
				if(diag1HasFirstSign & diag1HasSecondSign){
					break;
				}
				else if(!cell.equals(0L)){
					signOccuringDiag1 = cell;
					signOccurrenceInDiag1++;
				}
			}
			
			if(diag1HasFirstSign & diag1HasSecondSign){
				diag1Evaluation = 0;
			}
			else{
				diag1Evaluation = signOccurrenceInDiag1==0?0:(signOccurrenceInDiag1==1?1:(signOccurrenceInDiag1==2?4:8));
				if(signOccuringDiag1.equals(2L)){
					diag1Evaluation = -1*diag1Evaluation;
				}
			}
			
			// Diagonal 2
			int diag2Evaluation = 0;
				
			boolean diag2HasFirstSign = false;
			boolean diag2HasSecondSign = false;
				
			Long signOccuringDiag2 = 0L;
			int signOccurrenceInDiag2 = 0;
				
			int[] diag2CellsIdx = {3,6,9,12};
			
			for(int j =0; j<4;j++){
					
				Long cell = boardState.getCellsState()[diag2CellsIdx[j]];
					
					if(cell.equals(1L)){
						diag2HasFirstSign = true;
					}
					if(cell.equals(2L)){
						diag2HasSecondSign = true;
					}
					
					if(diag2HasFirstSign & diag2HasSecondSign){
						break;
					}
					else if(!cell.equals(0L)){
						signOccuringDiag2 = cell;
						signOccurrenceInDiag2++;
					}
				}
				
				if(diag2HasFirstSign & diag2HasSecondSign){
					diag2Evaluation = 0;
				}
				else{
					diag2Evaluation = signOccurrenceInDiag2==0?0:(signOccurrenceInDiag2==1?1:(signOccurrenceInDiag2==2?4:8));
					if(signOccuringDiag2.equals(2L)){
						diag2Evaluation = -1*diag2Evaluation;
					}
				}
			
			diagsEvaluation = diagsEvaluation + diag1Evaluation + diag2Evaluation;
		
		evaluation = rowsEvaluation + columnsEvaluation + diagsEvaluation;
		
		if(signToPlay.equals(1L)){
			evaluation++;
		}
		else if(signToPlay.equals(2L)){
			evaluation--;
		}
		
		return evaluation;
	}


	// If state is terminal return the same state but don't update parent
	private static List<BoardState> generateSuccessors(BoardState state, Long currentLevelSign) {

		List<BoardState> successors = new ArrayList<BoardState>();
		
		for(Long emptyCell : state.getEmptyCells()){
			
			Move move = new Move();
			move.setSignPosition(emptyCell.intValue());
			move.setSignToPlay(currentLevelSign);
			
			BoardState newBoardState = new BoardState(state); 
			newBoardState.apply(move);
			newBoardState.setMoveToGetHere(move);
			
			successors.add(newBoardState);
			
		}
		
		Collections.shuffle(successors);
		
		return successors;
	}
	
}
