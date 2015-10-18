import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author dschwenk
 * Implementation of Breadth-First-Search-, Depth-First-Search- and 
 * Iterative-Deepening algorithm to solve 8-Puzzle
 */
public class EightPuzzle {

	private static int puzzleSize; // size of puzzle - size 3 means 3x3 puzzle
	private static int maxDepth; // depth limit
	
	private static int[] goalNode = {1, 2, 3, 4, 5, 6, 7, 8, 0};
	
	
	
	/**
	 * Function verifies if current processed node is equal to goalNode 
	 * @param node the current processed node
	 * @return true if current processed node is equal to goalNode 
	 */
	private boolean zielErreicht(int[] node){
		if(Arrays.equals(node, goalNode)){
			return true;
		}
		return false;
	}
	
	
	/**
	 * Function adds child nodes to the list of nodes
	 * @param parentNode current processed node
	 * @param childNodes list of all nodes
	 */
	private void appendChildNodes(int[] parentNode, ArrayList<int[]> childNodes){
		appendUp(parentNode, childNodes);
		appendDown(parentNode, childNodes);
		appendLeft(parentNode, childNodes);
		appendRight(parentNode, childNodes);
	}
	
	
	/**
	 * function verifies if it큦 possible to move the 0 up, if so a new child is added to the list
	 * @param parentNode current processed node
	 * @param childNodes list of all nodes
	 */
	private void appendUp(int[] parentNode, ArrayList<int[]> childNodes){

		// where is the 0 -> get index of 0
		int index = getIndex(parentNode);

		// swap 0 to new position (if possible)
		if(index > (puzzleSize - 1)){
			// create clone of node (to not affect the parent -> When you pass an array to other method, any changes in the content of array through that reference will affect the original array)			
			int[] child = parentNode.clone();

			// swap 0 with the number the line above 
			swap(child, index, index-3);
			
			// append child to node list
			// System.out.print("up - append node:" + child[0] + child[1] + child[2] + child[3] + child[4] + child[5] + child[6] + child[7] + child[8] + "   goal node: " + goalNode[0] + goalNode[1] + goalNode[2] + goalNode[3] + goalNode[4] + goalNode[5] + goalNode[6] + goalNode[7] + goalNode[8] + "\n");
			childNodes.add(child);
		}
	}
	
	
	/**
	 * function verifies if it큦 possible to move the 0 down, if so a new child is added to the list
	 * @param parentNode current processed node
	 * @param childNodes list of all nodes
	 */
	private void appendDown(int[] parentNode, ArrayList<int[]> childNodes){

		// where is the 0 -> get index of 0
		int index = getIndex(parentNode);
		
		// swap 0 to new position (if possible)
		if(index < (puzzleSize * (puzzleSize - 1))){
			// create clone of node (to not affect the parent -> When you pass an array to other method, any changes in the content of array through that reference will affect the original array)			
			int[] child = parentNode.clone();

			// swap 0 with the number the line below			
			swap(child, index, index+3);
			
			// append child to node list
			// System.out.print("down - append node:" + child[0] + child[1] + child[2] + child[3] + child[4] + child[5] + child[6] + child[7] + child[8] + "   goal node: " + goalNode[0] + goalNode[1] + goalNode[2] + goalNode[3] + goalNode[4] + goalNode[5] + goalNode[6] + goalNode[7] + goalNode[8] + "\n");			
			childNodes.add(child);	
		}	
	}
	

	
	
	/**
	 * function verifies if it큦 possible to move the 0 left, if so a new child is added to the list
	 * @param parentNode current processed node
	 * @param childNodes list of all nodes
	 */
	private void appendLeft(int[] parentNode, ArrayList<int[]> childNodes){

		// where is the 0 -> get index of 0
		int index = getIndex(parentNode);
		
		// swap 0 to new position (if possible)
		if((index % puzzleSize) != 0){
			// create clone of node (to not affect the parent -> When you pass an array to other method, any changes in the content of array through that reference will affect the original array)			
			int[] child = parentNode.clone();

			// swap 0 with the number left		
			swap(child, index, index-1);
			
			// append child to node list
			// System.out.print("left - append node:" + child[0] + child[1] + child[2] + child[3] + child[4] + child[5] + child[6] + child[7] + child[8] + "   goal node: " + goalNode[0] + goalNode[1] + goalNode[2] + goalNode[3] + goalNode[4] + goalNode[5] + goalNode[6] + goalNode[7] + goalNode[8] + "\n");			
			childNodes.add(child);	
		}	
	}
	
	
	
	/**
	 * function verifies if it큦 possible to move the 0 right, if so a new child is added to the list
	 * @param parentNode current processed node
	 * @param childNodes list of all nodes
	 */	
	private void appendRight(int[] parentNode, ArrayList<int[]> childNodes){
		
		// where is the 0 -> get index of 0
		int index = getIndex(parentNode);
		
		
		// swap 0 to new position (if possible)
		if((index % puzzleSize) != (puzzleSize - 1)){
			// create clone of node (to not affect the parent -> When you pass an array to other method, any changes in the content of array through that reference will affect the original array)			
			int[] child = parentNode.clone();

			// swap 0 with the number right			
			swap(child, index, index+1);
			
			// append child to node list
			// System.out.print("right - append node:" + child[0] + child[1] + child[2] + child[3] + child[4] + child[5] + child[6] + child[7] + child[8] + "   goal node: " + goalNode[0] + goalNode[1] + goalNode[2] + goalNode[3] + goalNode[4] + goalNode[5] + goalNode[6] + goalNode[7] + goalNode[8] + "\n");
			childNodes.add(child);	
		}	
	}	
	
	
	/**
	 * Function searches for the index of 0
	 * @param node current processed node
	 * @return index of 0, -1 if not found
	 */
	private int getIndex(int[] node){
		for(int i=0;i<node.length;i++){
			if(node[i] == 0){
				return i;
			}
		}
		return -1;
	}
	
	
	/**
	 * Function to swap values in given array
	 * @param myArr array, in which values should be swapped
	 * @param a index of value a
	 * @param b index of value b
	 */
	private void swap(int[] myArr, int a, int b){
		int t = myArr[a];
		myArr[a] = myArr[b];
		myArr[b] = t;	
	}	


	
	/**
	 * Function searches for a goal node / solution with the Breadth-First-Search algorithm
	 * @param nodeList list with all nodes
	 * @param goalNode solution node
	 * @param depth current depth 
	 * @param maxDepth max depth
	 */
	private void breadthFirstSearch(ArrayList<int[]> nodeList, int[] goalNode, int depth, int maxDepth){
		
		if(depth > maxDepth){
			System.out.println("Reached max Depth: " + depth);
			return;
		} 
		
		ArrayList<int[]> childNodes = new ArrayList<int[]>();
		
		// loop through all nodes of the current depth level
		for(int[] node : nodeList){
			if(zielErreicht(node)){
				System.out.println("Solution found! Depth: " + depth);
				return;
			}
			// append child nodes to current node
			// System.out.print("current procesed node:" + node[0] + node[1] + node[2] + node[3] + node[4] + node[5] + node[6] + node[7] + node[8] + "   goal node: " + goalNode[0] + goalNode[1] + goalNode[2] + goalNode[3] + goalNode[4] + goalNode[5] + goalNode[6] + goalNode[7] + goalNode[8] + "\n");
			appendChildNodes(node, childNodes);			
		}
		
		// call BreadthFirstSearch recursively
		if(!(childNodes.isEmpty())){
			breadthFirstSearch(childNodes, goalNode, depth+1, maxDepth);
		}
		else {
			// there are no more child nodes
			System.out.println("No solution found!");
			return;
		}
	}
	
	
	
	/**
	 * Function searches for a goal node / solution with the Depth-First-Search algorithm
	 * @param node current processed node
	 * @param goalNode solution node
	 * @param depth current depth
	 * @param maxDepth max depth
	 * @return returns true if solution found, false if algorithm reached max depth
	 */
	private boolean depthFirstSearch(int[] node, int[] goalNode, int depth, int maxDepth){
		
		if(depth > maxDepth){
			System.out.println("Reached max Depth: " + depth);
			return false;
		}
		
		if(zielErreicht(node)){
			System.out.println("Solution found! Depth: " + depth);
			return true;
		}
		
		ArrayList<int[]> childNodes = new ArrayList<int[]>();
		
		// append child nodes to current node
		// System.out.print("current procesed node:" + node[0] + node[1] + node[2] + node[3] + node[4] + node[5] + node[6] + node[7] + node[8] + "   goal node: " + goalNode[0] + goalNode[1] + goalNode[2] + goalNode[3] + goalNode[4] + goalNode[5] + goalNode[6] + goalNode[7] + goalNode[8] + "\n");
		appendChildNodes(node, childNodes);				
		
		// for all child nodes at current level, call BreadthFirstSearch recursively
		while(!(childNodes.isEmpty())){
			if(depthFirstSearch(childNodes.get(0), goalNode, depth+1, maxDepth)){
				return true;
			}
			childNodes.remove(0);
		}		
		
		return false;		
	}
	
	
	/**
	 * Function searches for a goal node / solution with the Iterative-Deepening algorithm
	 * @param node current processed node
	 * @param goalNode solution node
	 * @return
	 */
	private boolean iterativeDeepening(int[] node, int[] goalNode)
	{
		int maxDepth = 0; // "Tiefenschranke"
		boolean result = false;
		
		while(!result){
			result = depthFirstSearch(node, goalNode, 0, maxDepth);
			maxDepth++; // increase "Tiefenschranke"
		}
		
		return true;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EightPuzzle puzzle = new EightPuzzle();
		
		maxDepth = 10;
		
		/*
		 * 	1   3
		 *  4 2 6
		 *  7 5 8 
		 */ 
		//int[] startNode = {1, 0, 3, 4, 2, 6, 7, 5, 8};
		int[] startNode = {0, 2, 3, 1, 4, 6, 7, 5, 8};		
		
		
		// size of puzzle - size 3 means 3x3 puzzle
		puzzleSize = (int) Math.sqrt(startNode.length);
		
		// list of nodes
		ArrayList<int[]> nodeList = new ArrayList<int[]>();
		
		// add start node to list
		nodeList.add(startNode);
		
		System.out.println("Start Breadth-first search: ");
		puzzle.breadthFirstSearch(nodeList, goalNode, 0, maxDepth);
		
	    System.out.println("\nStart Depth-first search:");
	    puzzle.depthFirstSearch(startNode, goalNode, 0, maxDepth);
	    
	    System.out.println("\nStart Iterative-Deepening:");
	    puzzle.iterativeDeepening(startNode, goalNode);
	}

}
