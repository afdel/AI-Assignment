package soton.ai.afdel.blocksworldtile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DepthFirst2 {

	
	static int gridSize = 4;
	
	static State initialState = new State(13,14,15,16);
//	static State initialState = new State(8,12,16,4);
	
	static State goalState = new State(6,10,14,16);
//	static State goalState = new State(4,8,12,16);
	
	static List<Node> fringe = new ArrayList<Node>();
	
	static int nodeExpanded = 0;
	static int nodeGenerated = 0;
	
	static int maxFringeSize = 0;
	static int maxMemory = 0;
	
	public static void main(String[] args) {
		
		Node solutionNode = new Node();
		
		Node firstNode = new Node();
		nodeGenerated++;
		firstNode.setNewState(initialState);
		
		List<Node> nodes = expandNode(firstNode);
		// Randomize before adding
		Collections.shuffle(nodes);
		System.out.println(" Update fringe : Old fringe : "+fringe);
		fringe.addAll(nodes);
		maxFringeSize = fringe.size();
		maxMemory = getCountNodeInMemory(fringe);
		System.out.println(" Update fringe : New fringe : "+fringe);
		
		boolean solutionFound = false;
		
		while(true){
			
			if(fringe.isEmpty()){
				break;
			}
			
			Node nextNode = getNode(fringe); // get last node in the fringe and remove it
			System.out.println(" Node from fringe : "+nextNode);
			goTo(nextNode); // Applies the move to parentNode's state and get the new state
			solutionFound = evaluateNode(nextNode); // check if the new state is the goal state
			if(solutionFound){
				solutionNode = nextNode;
				break;
			}else {
//				removeNode(fringe,nextNode);
				List<Node> newNodes = expandNode(nextNode); // if possible
				// Randomize before adding if not empty
				Collections.shuffle(newNodes);
				fringe.addAll(newNodes);
				if( fringe.size() > maxFringeSize){
					maxFringeSize = fringe.size();
				}
				int nodesInMemory = getCountNodeInMemory(fringe);
				if( nodesInMemory > maxMemory){
					maxMemory = nodesInMemory;
				}
			}
		}
		
		
		if(solutionFound){
			printPath(solutionNode);
			System.out.println(" Node expanded : "+nodeExpanded);
			System.out.println(" Node generated : "+nodeGenerated);
			System.out.println(" Max Fringe size : "+maxFringeSize);
			System.out.println(" Max Node In Memory : "+maxMemory);
		}
		else{
			System.out.println(" No Solution");
		}
		
	}

	private static int getCountNodeInMemory(List<Node> fringe) {

		Set<Node> uniqueNodes = new HashSet<Node>();
		
		for( Node node : fringe){
			uniqueNodes.add(node);
			uniqueNodes.addAll(getAncestors(node));
		}
		
		return uniqueNodes.size();
	}

	private static List<Node> getAncestors(Node node) {
		
		List<Node> getAncestors = new ArrayList<Node>();
		
		Node parentNode = node.getParentNode();
		if( parentNode != null){
			getAncestors.add(parentNode);
			getAncestors.addAll(getAncestors(parentNode));
		}
		
		return getAncestors;
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

		
		System.out.println(" GO TO NODE : previous state : "+node.getParentNode());
		Action action = node.getAction();
		System.out.println(" APPLY : "+action.toString());
		State prevState = node.getParentNode().getNewState();
		
		State newState = move(prevState,action);
		node.setNewState(newState);
		System.out.println(" New State : "+node);
	}

	private static Node getNode(List<Node> fringe) {
		System.out.println(" Getting node from fringe ");
		return fringe.remove(fringe.size()-1);
	}

	private static List<Node> expandNode(Node prevNode) {
		
		System.out.println("Expanding current node"+prevNode);
		nodeExpanded++;
		
		List<Node> nodes = new ArrayList<Node>();
		
		State prevState = prevNode.getNewState();
		int prevAgentPos = prevState.getPositionOfAgent();
		
		if( possibleToMoveRight(prevAgentPos)){
			
			System.out.println(" Possible to go right");
			
//			State newState = move(prevState,Action.RIGHT);
			Node newNode = new Node();
			newNode.setParentNode(prevNode);
			newNode.setAction(Action.RIGHT);
//			newNode.setNewState(newState);
			
			nodes.add(newNode);
		}

		if( possibleToMoveUp(prevAgentPos)){
			
			System.out.println(" Possible to go up");
			
//			State newState = move(prevState,Action.UP);
			Node newNode = new Node();
			newNode.setParentNode(prevNode);
			newNode.setAction(Action.UP);
//			newNode.setNewState(newState);
			
			nodes.add(newNode);
		}
		
		if( possibleToMoveLeft(prevAgentPos)){
			
			System.out.println(" Possible to go left");
			
//			State newState = move(prevState,Action.LEFT);
			Node newNode = new Node();
			newNode.setParentNode(prevNode);
			newNode.setAction(Action.LEFT);
//			newNode.setNewState(newState);
			
			nodes.add(newNode);
		}
		
		if( possibleToMoveDown(prevAgentPos)){
			
			System.out.println(" Possible to go down");
			
//			State newState = move(prevState,Action.DOWN);
			Node newNode = new Node();
			newNode.setParentNode(prevNode);
			newNode.setAction(Action.DOWN);
//			newNode.setNewState(newState);
			
			nodes.add(newNode);
		}


		nodeGenerated = nodeGenerated + nodes.size();
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
