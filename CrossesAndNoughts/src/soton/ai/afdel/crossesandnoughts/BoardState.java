package soton.ai.afdel.crossesandnoughts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardState {

	private Move moveToGetHere;
	
	private Long[] cellsState = new Long[16];
	
	private Long stateEvaluation = null;
	
	public BoardState() {

		for(int i=0; i<16; i++ ){
			cellsState[i] = 0L; // 0 means the cell is empty
		}
	}
	
	public BoardState(BoardState boardState){
		for(int i=0; i<16; i++ ){
			cellsState[i] = boardState.cellsState[i];
		}
	}

	public Move getMoveToGetHere() {
		return moveToGetHere;
	}

	public void setMoveToGetHere(Move moveToGetHere) {
		this.moveToGetHere = moveToGetHere;
	}

	public Long[] getCellsState() {
		return cellsState;
	}

	public void setCellsState(Long[] cellsState) {
		this.cellsState = cellsState;
	}

	public Long getStateEvaluation() {
		return stateEvaluation;
	}

	public void setStateEvaluation(Long stateEvaluation) {
		this.stateEvaluation = stateEvaluation;
	}

	public boolean isTheEnd() {
		

		if( !thereIsAwinner().equals(0L)){
			return true;
		}
		
		for(int i=0; i<16; i++ ){
			
			if( cellsState[i].equals(0L)){
				return false;
			}
		}
		
		return true;
	}

	public Long thereIsAwinner() {
		
		for(int i= 1;i<5;i++){
			Long colFull = columnFullSameSign(i);
			if( !colFull.equals(0L)){
				return colFull;
			}
			Long rowFull = rowFullSameSign(i);
			if( !rowFull.equals(0L)){
				return rowFull;
			}
		}
		
		if( cellsState[0].equals(1L) && cellsState[5].equals(1L) && cellsState[10].equals(1L) && cellsState[15].equals(1L)){
			return 1L;
		}
		if( cellsState[0].equals(2L) && cellsState[5].equals(2L) && cellsState[10].equals(2L) && cellsState[15].equals(2L)){
			return 2L;
		}
		if( cellsState[3].equals(1L) && cellsState[6].equals(1L) && cellsState[9].equals(1L) && cellsState[12].equals(1L)){
			return 1L;
		}
		if( cellsState[3].equals(2L) && cellsState[6].equals(2L) && cellsState[9].equals(2L) && cellsState[12].equals(2L)){
			return 2L;
		}
		
		return 0L;
	}

	private Long rowFullSameSign(int i) {
		
		int rowFirstCell = 4*(i-1);
		
		if( cellsState[rowFirstCell].equals(1L) && cellsState[rowFirstCell+1].equals(1L) && cellsState[rowFirstCell+2].equals(1L) && cellsState[rowFirstCell+3].equals(1L)){
			return 1L;
		}
		if( cellsState[rowFirstCell].equals(2L) && cellsState[rowFirstCell+1].equals(2L) && cellsState[rowFirstCell+2].equals(2L) && cellsState[rowFirstCell+3].equals(2L)){
			return 2L;
		}
		
		return 0L;
	}

	private Long columnFullSameSign(int i) {
		
		int columnFirstCell = i-1;
		
		if( cellsState[columnFirstCell].equals(1L) && cellsState[columnFirstCell+4].equals(1L)
				&& cellsState[columnFirstCell+8].equals(1L) && cellsState[columnFirstCell+12].equals(1L)){
			
			return 1L;
		}
		if( cellsState[columnFirstCell].equals(2L) && cellsState[columnFirstCell+4].equals(2L)
				&& cellsState[columnFirstCell+8].equals(2L) && cellsState[columnFirstCell+12].equals(2L)){
			return 2L;
		}
		
		return 0L;
	}

	public void apply(Move move) {
		
		this.cellsState[move.getSignPosition()] = move.getSignToPlay();
		
	}

	public List<Long> getEmptyCells() {

		List<Long> emptyCells = new ArrayList<Long>();
		
		for(int i=0; i<16; i++ ){
			if(cellsState[i] == 0L){
				emptyCells.add(new Long(i));
			}
		}
		
		return emptyCells;
	}
	
	@Override
	public String toString() {
		
		String firstRowState = " [ "+cellsState[0]+","+cellsState[1]+","+cellsState[2]+","+cellsState[3]+"]";
		String secondRowState = "[ "+cellsState[4]+","+cellsState[5]+","+cellsState[6]+","+cellsState[7]+"]";
		String thirdRowState = "[ "+cellsState[8]+","+cellsState[9]+","+cellsState[10]+","+cellsState[11]+"]";
		String fourthRowState = "[ "+cellsState[12]+","+cellsState[13]+","+cellsState[14]+","+cellsState[15]+"]";
		
		return firstRowState+" \n "+secondRowState+" \n "+thirdRowState+" \n "+fourthRowState;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(cellsState);
		result = prime * result + ((moveToGetHere == null) ? 0 : moveToGetHere.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardState other = (BoardState) obj;
		if (!Arrays.equals(cellsState, other.cellsState))
			return false;
		if (moveToGetHere == null) {
			if (other.moveToGetHere != null)
				return false;
		} else if (!moveToGetHere.equals(other.moveToGetHere))
			return false;
		return true;
	}
	
	
}
