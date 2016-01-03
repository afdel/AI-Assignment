package soton.ai.afdel.blocksworldtile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class A_Star2 {

	
	static int gridSize = 4;
	
	static State initialState = new State(13,14,15,16);
//	static State initialState = new State(14,13,9,16);
	
	static State goalState = new State(6,10,14,16);
//	static State goalState = new State(15,14,13,1);
	
	static List<Node_H> fringe = new ArrayList<Node_H>();
	
	static int nodeExpanded = 0;
	static int nodeGenerated = 0;
	
	static int maxFringeSize = 0;
	static int maxMemory = 0;
	
	public static void main(String[] args) throws Exception {
		
		Node_H solutionNode = new Node_H();
		
		Node_H firstNode = new Node_H();
		firstNode.setNewState(initialState);
		firstNode.setCostSoFar(0);
		nodeGenerated++;
		
		List<Node_H> nodes = expandNode(firstNode);
		
//		System.out.println(" Update fringe : Old fringe : "+fringe);
		fringe.addAll(nodes);
		maxFringeSize = fringe.size();
		maxMemory = getCountNodeInMemory(fringe);
//		System.out.println(" Update fringe : New fringe : "+fringe);
		
		boolean solutionFound = false;
		
		while(true){
			
			if(fringe.isEmpty()){
				break;
			}
			Node_H nextNode = getNode(fringe); // get node with the smaller cost function from the fringe and remove it
//			System.out.println(" Node from fringe : "+nextNode);
			solutionFound = evaluateNode(nextNode); // check if the new state is the goal state
			if(solutionFound){
				solutionNode = nextNode;
				break;
			}else {
				List<Node_H> newNodes = expandNode(nextNode); // if possible
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
	
	private static int getCountNodeInMemory(List<Node_H> fringe) {

		Set<Node_H> uniqueNodes = new HashSet<Node_H>();
		
		for( Node_H node : fringe){
			uniqueNodes.add(node);
			uniqueNodes.addAll(getAncestors(node));
		}
		
		return uniqueNodes.size();
	}

	private static List<Node_H> getAncestors(Node_H node) {
		
		List<Node_H> getAncestors = new ArrayList<Node_H>();
		
		Node_H parentNode = node.getParentNode();
		if( parentNode != null){
			getAncestors.add(parentNode);
			getAncestors.addAll(getAncestors(parentNode));
		}
		
		return getAncestors;
	}

	private static void printPath(Node_H solutionNode) {

		List<Action> actions = new ArrayList<Action>();
		
		Node_H currentNode = solutionNode;
		
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

	private static boolean evaluateNode(Node_H node) {

		return node.getNewState().equals(goalState);
	}

	private static void goTo(Node_H node) {

//		System.out.println(" GO TO NODE : previous state : "+node.getParentNode());
		Action action = node.getAction();
//		System.out.println(" APPLY : "+action.toString());
		State prevState = node.getParentNode().getNewState();
		
		State newState = move(prevState,action);
		node.setNewState(newState);
		
		// Calculate cost to goal
		node.setCostToGoal(getCostToGoal(node.getNewState()));
		
//		System.out.println(" New State : "+node);
	}

	private static Node_H getNode(List<Node_H> fringe) throws Exception {
		
//		System.out.println(" Getting node from fringe ");

		Node_H nodeWithLeastCost = null;
		
		int minCost = -1;
		
		for( Node_H node : fringe){
			
			int nodeCost = node.getCostSoFar()+node.getCostToGoal();
			
			if( minCost == -1 || nodeCost < minCost){
				minCost = nodeCost;
				nodeWithLeastCost = node;
			}
		}
		
		if(!fringe.remove(nodeWithLeastCost)){
			throw new Exception(" Failed To Remove returned node from the fringe");
		}
//		System.out.println(" Node from fringe "+nodeWithLeastCost);
		return nodeWithLeastCost;
	}

	private static List<Node_H> expandNode(Node_H prevNode) {
		
//		System.out.println(" *********************** Expanding current node"+prevNode);
		nodeExpanded++;
		
		List<Node_H> nodes = new ArrayList<Node_H>();
		
		State prevState = prevNode.getNewState();
		int prevAgentPos = prevState.getPositionOfAgent();
		
		if( possibleToMoveRight(prevAgentPos)){
			
//			System.out.println(" Possible to go right");
			
			Node_H newNode = new Node_H();
			newNode.setParentNode(prevNode);
			newNode.setAction(Action.RIGHT);
			// Update cost so far
			newNode.setCostSoFar(prevNode.getCostSoFar()+1);
			goTo(newNode);
			
			nodes.add(newNode);
		}

		if( possibleToMoveUp(prevAgentPos)){
			
//			System.out.println(" Possible to go up");
			
			Node_H newNode = new Node_H();
			newNode.setParentNode(prevNode);
			newNode.setAction(Action.UP);
			// Update cost so far
			newNode.setCostSoFar(prevNode.getCostSoFar()+1);
			goTo(newNode);
			
			nodes.add(newNode);
		}
		
		if( possibleToMoveLeft(prevAgentPos)){
			
//			System.out.println(" Possible to go left");
			
			Node_H newNode = new Node_H();
			newNode.setParentNode(prevNode);
			newNode.setAction(Action.LEFT);
			// Update cost so far
			newNode.setCostSoFar(prevNode.getCostSoFar()+1);
			goTo(newNode);

			nodes.add(newNode);
		}
		
		if( possibleToMoveDown(prevAgentPos)){
			
//			System.out.println(" Possible to go down");
			
			Node_H newNode = new Node_H();
			newNode.setParentNode(prevNode);
			newNode.setAction(Action.DOWN);
			// Update cost so far
			newNode.setCostSoFar(prevNode.getCostSoFar()+1);
			goTo(newNode);
			
			nodes.add(newNode);
		}

		nodeGenerated = nodeGenerated + nodes.size();

//		System.out.println(" *********************** End Of Expansion");
		
		return nodes;
	}

	
	private static int getCostToGoal(State newState) {
		
		int diffA = diffTwoPoints(newState.getPositionOfA(), goalState.getPositionOfA());
		int diffB = diffTwoPoints(newState.getPositionOfB(), goalState.getPositionOfB());
		int diffC = diffTwoPoints(newState.getPositionOfC(), goalState.getPositionOfC());
//		int diffAgent = diffTwoPoints(newState.getPositionOfAgent(), goalState.getPositionOfAgent());
		
		return diffA+diffB+diffC;
	}

	private static int diffTwoPoints(int point1, int point2){
		
		int diff = 0;
		
		int diffCol = getColumn(point1) - getColumn(point2);
		int diffRow = getRow(point1) - getRow(point2);
		
//		System.out.println(" Diff col : "+Math.abs(diffCol));
//		System.out.println(" Diff row : "+Math.abs(diffRow));
		
		diff = Math.abs(diffCol) + Math.abs(diffRow);
		
		return diff;
	}



	private static int getColumn(int point) {
		
		int mod = point%gridSize;
		int col = (mod==0?gridSize:mod);
//		System.out.println(" Column "+point+" = "+col);
		return col;
	}



	private static int getRow(int point) {

		int row = (point/gridSize);
		
		if( point%gridSize != 0){
			row++;
		}
		
//		System.out.println(" Row "+point+" = "+row);
		return row;
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
