package soton.ai.afdel.blocksworldtile;

public class Node {

	
	private Node parentNode;
	private Action action;
	private State newState;
	
	
	
	public Node getParentNode() {
		return parentNode;
	}
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public State getNewState() {
		return newState;
	}
	public void setNewState(State newState) {
		this.newState = newState;
	}
	
	
	@Override
	public String toString() {
		
		
		return " Node : { Action : "+action+" , State : "+newState+" } ";
	}
}
