package soton.ai.afdel.crossesandnoughts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	private static Map<BoardState, Long> boardStatesEvaluations;
	
	public static void main(String[] args) {
		
		
		BoardState boardState1 = new BoardState();
		boardState1.setCellsState(new Long[]{1L,1L,0L,0L,
											0L,0L,0L,0L,
											0L,0L,0L,0L,
											0L,0L,0L,0L});
		boardState1.setMoveToGetHere(new Move(1,1L));
		

		BoardState boardState2 = new BoardState();
		boardState2.setCellsState(new Long[]{1L,0L,1L,0L,
											0L,0L,0L,0L,
											0L,0L,0L,0L,
											0L,0L,0L,0L});
		boardState2.setMoveToGetHere(new Move(2,1L));
		

		BoardState boardState3 = new BoardState();
		boardState3.setCellsState(new Long[]{1L,0L,0L,1L,
											0L,0L,0L,0L,
											0L,0L,0L,0L,
											0L,0L,0L,0L});
		boardState3.setMoveToGetHere(new Move(3,1L));
		
		boardStatesEvaluations = new HashMap<BoardState, Long>();
		boardStatesEvaluations.put(boardState1, 4L);
		boardStatesEvaluations.put(boardState2, 8L);
		boardStatesEvaluations.put(boardState3, 6L);
		
		// ********************************

		BoardState boardState4 = new BoardState();
		boardState4.setCellsState(new Long[]{1L,0L,1L,0L,
											0L,0L,0L,0L,
											0L,0L,0L,0L,
											0L,0L,0L,0L});
		boardState4.setMoveToGetHere(new Move(2,1L));
		

		BoardState boardState5 = new BoardState();
		boardState5.setCellsState(new Long[]{1L,0L,0L,1L,
											0L,0L,0L,0L,
											0L,0L,0L,0L,
											0L,0L,0L,0L});
		boardState5.setMoveToGetHere(new Move(3,1L));
		

		BoardState boardState6 = new BoardState();
		boardState6.setCellsState(new Long[]{1L,0L,0L,0L,
											1L,0L,0L,0L,
											0L,0L,0L,0L,
											0L,0L,0L,0L});
		boardState6.setMoveToGetHere(new Move(4,1L));
		
		
		List<BoardState> successors = new ArrayList<BoardState>();
		successors.add(boardState6);
		successors.add(boardState4);
		successors.add(boardState5);
		
		
		List<BoardState> ordered = order(successors,-1);
	
		System.out.println(" First : "+ordered.get(0).getStateEvaluation());
		System.out.println(" Second : "+ordered.get(1).getStateEvaluation());
		System.out.println(" Third : "+ordered.get(2).getStateEvaluation());
		
	}
	
	
	
	private static List<BoardState> order(List<BoardState> successors, final int maxOrMin) {

		// Populate each successor with its evaluation
		for(BoardState successor : successors){
			
			Long evaluation = boardStatesEvaluations.get(successor);
			successor.setStateEvaluation(evaluation);
		}
		
		// Sort by evaluation
		Collections.sort(successors, new Comparator<BoardState>() {
			public int compare(BoardState o1, BoardState o2) {
				
				
				if( o1.getStateEvaluation()==null && o2.getStateEvaluation()==null){
					return 0;
				}
				else{
					if( o1.getStateEvaluation()==null){
						return -1*maxOrMin;
					}
					if(o2.getStateEvaluation()==null){
						return maxOrMin;
					}
				}
				
				return (o1.getStateEvaluation()).compareTo(o2.getStateEvaluation());
			}
		});
		if( maxOrMin == 1){
			Collections.reverse(successors);
		}
		
		return successors;
	}
	
}
