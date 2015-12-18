package soton.ai.afdel.blocksworldtile;

public class Node {

	
	private Node parentNode;
	private Action action;
	private State newState;
	private int depth;
	
	
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
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	@Override
	public String toString() {
		
		
		return " Node : { Depth : "+ depth+" Action : "+action+" , State : "+newState+" } ";
	}
}
