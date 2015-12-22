package soton.ai.afdel.blocksworldtile;

public class Node_H {

	
	private Node_H parentNode;
	private Action action;
	private State newState;
	private int depth;
	
	private int costSoFar;
	private int costToGoal;
	
	
	public Node_H getParentNode() {
		return parentNode;
	}
	public void setParentNode(Node_H parentNode) {
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
	public int getCostSoFar() {
		return costSoFar;
	}
	public void setCostSoFar(int costSoFar) {
		this.costSoFar = costSoFar;
	}
	public int getCostToGoal() {
		return costToGoal;
	}
	public void setCostToGoal(int costToGoal) {
		this.costToGoal = costToGoal;
	}
	@Override
	public String toString() {
		
		
		return " Node : { Depth : "+ depth+" Action : "+action+" , State : "+newState+" , Cost So Far : "+costSoFar+" , Cost To Goal : "+costToGoal+"} ";
	}
}
