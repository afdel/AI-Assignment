package soton.ai.afdel.blocksworldtile;

public class State {

	
	private int positionOfA;
	private int positionOfB;
	private int positionOfC;
	private int positionOfAgent;
	
	public State( int positionOfA, int positionOfB, int positionOfC, int positionOfAgent){
		
		this.positionOfA = positionOfA;
		this.positionOfB = positionOfB;
		this.positionOfC = positionOfC;
		this.positionOfAgent = positionOfAgent;
		
	}
	
	public State(State state) {
		
		this.positionOfA = state.getPositionOfA();
		this.positionOfB = state.getPositionOfB();
		this.positionOfC = state.getPositionOfC();
		this.positionOfAgent = state.getPositionOfAgent();
		
	}

	public State() {
		// TODO Auto-generated constructor stub
	}

	public int getPositionOfA() {
		return positionOfA;
	}
	public void setPositionOfA(int positionOfA) {
		this.positionOfA = positionOfA;
	}
	public int getPositionOfB() {
		return positionOfB;
	}
	public void setPositionOfB(int positionOfB) {
		this.positionOfB = positionOfB;
	}
	public int getPositionOfC() {
		return positionOfC;
	}
	public void setPositionOfC(int positionOfC) {
		this.positionOfC = positionOfC;
	}
	public int getPositionOfAgent() {
		return positionOfAgent;
	}
	public void setPositionOfAgent(int positionOfAgent) {
		this.positionOfAgent = positionOfAgent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + positionOfA;
		result = prime * result + positionOfB;
		result = prime * result + positionOfC;
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
		State other = (State) obj;
		if (positionOfA != other.positionOfA)
			return false;
		if (positionOfB != other.positionOfB)
			return false;
		if (positionOfC != other.positionOfC)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return " State = { A : "+positionOfA+" , B : "+positionOfB+" , C : "+positionOfC+" , Agent : "+positionOfAgent+" } ";
	}
	
}
