package soton.ai.afdel.blocksworldtile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IterativeDeepening {

	
	static int gridSize = 4;
	
	static State initialState = new State(13,14,15,16);
//	static State initialState = new State(8,12,16,4);
	
	static State goalState = new State(6,10,14,16);
//	static State goalState = new State(13,15,16,14);
	
	static List<Node> fringe = new ArrayList<Node>();
	
	static int nodeExpanded = 0;
	
	static int maxFringeSize = 0;
	
	public static void main(String[] args) {
		
		Node solutionNode = new Node();
		boolean solutionFound = false;
		
		int depthLimit = 0;
		
		while(true){
			
			depthLimit++;
			System.out.println(" ########################################## Depth Limit is : "+depthLimit);
			Node firstNode = new Node();
			firstNode.setNewState(initialState);
			
			List<Node> nodes = expandNode(firstNode);
			
			// Randomize before adding
//			Collections.shuffle(nodes);
//			System.out.println(" Update fringe : Old fringe : "+fringe);
			fringe.addAll(nodes);
			maxFringeSize = fringe.size();
//			System.out.println(" Update fringe : New fringe : "+fringe);
			
			while(true){
				
				if(fringe.isEmpty()){
					break;
				}
				
				Node nextNode = getNode(fringe); // get last node in the fringe and remove it
//				System.out.println(" Node from fringe : "+nextNode);
				goTo(nextNode); // Applies the move to parentNode's state and get the new state
				solutionFound = evaluateNode(nextNode); // check if the new state is the goal state
				if(solutionFound){
					solutionNode = nextNode;
					break;
				}else {
					
					if( nextNode.getDepth() != depthLimit){
						List<Node> newNodes = expandNode(nextNode); // if possible
						// Randomize before adding if not empty
//						Collections.shuffle(newNodes);
						fringe.addAll(newNodes);
						if( fringe.size() > maxFringeSize){
							maxFringeSize = fringe.size();
						}
					}
					
				}
			}
			
			if(solutionFound){
				break;
			}
		}
		
		
		
//		if(solutionFound){
			printPath(solutionNode);
			System.out.println(" Node expanded : "+nodeExpanded);
			System.out.println(" Max Fringe size : "+maxFringeSize);
//		}
//		else{
//			System.out.println(" No Solution");
//		}
		
	}

	private static void printPath(Node solutionNode) {

		List<Action> actions = new ArrayList<Action>();
		
		Node currentNode = solutionNode;
		
		while(currentNode.getParentNode()!=null){
			actions.add(currentNode.getAction());
			currentNode = currentNode.getParentNode();
		}
		
		int actionsCount = actions.size();
		
		for(int i = 0; i<actionsCount;i++){
			System.out.println(actions.get(actionsCount-i-1));
		}
		System.out.println(" The Solution Depth is : "+actionsCount);
	}

	private static boolean evaluateNode(Node node) {

		return node.getNewState().equals(goalState);
	}

	private static void goTo(Node node) {

		
//		System.out.println(" GO TO NODE : previous state : "+node.getParentNode());
		Action action = node.getAction();
//		System.out.println(" APPLY : "+action.toString());
		State prevState = node.getParentNode().getNewState();
		
		State newState = move(prevState,action);
		node.setNewState(newState);
//		System.out.println(" New State : "+node);
	}

	private static Node getNode(List<Node> fringe) {
//		System.out.println(" Getting node from fringe ");
		return fringe.remove(fringe.size()-1);
	}

	private static List<Node> expandNode(Node prevNode) {
		
//		System.out.println("Expanding current node"+prevNode);
		nodeExpanded++;
		
		List<Node> nodes = new ArrayList<Node>();
		
		State prevState = prevNode.getNewState();
		int prevAgentPos = prevState.getPositionOfAgent();
		int prevNodeDepth = prevNode.getDepth();
		
		if( possibleToMoveDown(prevAgentPos)){
			
//			System.out.println(" Possible to go down");
			
			Node newNode = new Node();
			newNode.setParentNode(prevNode);
			newNode.setAction(Action.DOWN);
			newNode.setDepth(prevNodeDepth+1);
			nodes.add(newNode);
		}
		
		if( possibleToMoveLeft(prevAgentPos)){
			
//			System.out.println(" Possible to go left");
			
			Node newNode = new Node();
			newNode.setParentNode(prevNode);
			newNode.setAction(Action.LEFT);
			newNode.setDepth(prevNodeDepth+1);
			nodes.add(newNode);
		}

		if( possibleToMoveUp(prevAgentPos)){
			
//			System.out.println(" Possible to go up");
			
			Node newNode = new Node();
			newNode.setParentNode(prevNode);
			newNode.setAction(Action.UP);
			newNode.setDepth(prevNodeDepth+1);
			nodes.add(newNode);
		}
		
		if( possibleToMoveRight(prevAgentPos)){
			
//			System.out.println(" Possible to go right");
			
			Node newNode = new Node();
			newNode.setParentNode(prevNode);
			newNode.setAction(Action.RIGHT);
			newNode.setDepth(prevNodeDepth+1);
			nodes.add(newNode);
		}
		
		return nodes;
	}

	
	private static State move(State prevState, Action action) {
		
		State newState = new State();
		
		int prevAgentPos = prevState.getPositionOfAgent();
		
		int newAgentPos = 0;
		
		if( action.equals(Action.RIGHT)){
			newAgentPos = prevAgentPos+1;
		}
		if( action.equals(Action.UP)){
			newAgentPos = prevAgentPos-gridSize;
		}
		if( action.equals(Action.LEFT)){
			newAgentPos = prevAgentPos-1;
		}
		if( action.equals(Action.DOWN)){
			newAgentPos = prevAgentPos+gridSize;
		}
		
		
		newState.setPositionOfAgent(newAgentPos);
		
		if( prevState.getPositionOfA() == newAgentPos){
			newState.setPositionOfA(prevAgentPos);
		}
		else{
			newState.setPositionOfA(prevState.getPositionOfA());
		}
		
		if( prevState.getPositionOfB() == newAgentPos){
			newState.setPositionOfB(prevAgentPos);
		}
		else{
			newState.setPositionOfB(prevState.getPositionOfB());
		}
		
		if( prevState.getPositionOfC() == newAgentPos){
			newState.setPositionOfC(prevAgentPos);
		}
		else{
			newState.setPositionOfC(prevState.getPositionOfC());
		}
		
		return newState;
	}	
	

	private static boolean possibleToMoveDown(int prevAgentPos) {
		return prevAgentPos <= gridSize*(gridSize-1);
	}

	private static boolean possibleToMoveLeft(int prevAgentPos) {
		return prevAgentPos % gridSize != 1;
	}

	private static boolean possibleToMoveUp(int prevAgentPos) {
		
		return prevAgentPos > gridSize;
	}

	private static boolean possibleToMoveRight(int prevAgentPos) {
		
		return prevAgentPos % gridSize != 0;
	}
	
	
}
