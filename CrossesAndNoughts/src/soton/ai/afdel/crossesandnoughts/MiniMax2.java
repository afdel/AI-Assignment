package soton.ai.afdel.crossesandnoughts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MiniMax2 {

	
	
	public static void main(String[] args) {
		
		BoardState initialBoardState = new BoardState();
		BoardState boardState = initialBoardState;
		
		int ply = 0;
		
		Long signToPlay = 0L;
		Move nextMove = null;
		
		while( !boardState.isTheEnd()){
			
			signToPlay = ply%2 == 0?1L:2L;
			
			nextMove = calculateTheNextMove(boardState, signToPlay);
			boardState.apply(nextMove);
			ply++;
		}
		
	}


	private static Move calculateTheNextMove(BoardState boardState, Long signToPlay) {

		List<BoardState> currentLevelStates = new ArrayList<BoardState>();
		currentLevelStates.add(boardState);
		
		int plyDepth = 0;
		while( plyDepth != 4){
			
			plyDepth++;
			
			List<BoardState> nextLevelStates = new ArrayList<BoardState>();
			Long currentLevelSign = plyDepth%2==1?signToPlay:(signToPlay%2L + 1L);
			for( BoardState state : currentLevelStates){
				nextLevelStates.addAll(generateSuccessors(state, currentLevelSign));
			}
			
			currentLevelStates = nextLevelStates;
		}
		
		
		
	}


	// If state is terminal return the same state but don't update parent
	private static Collection<? extends BoardState> generateSuccessors(BoardState state, Long currentLevelSign) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
