package Homework4;

//Marguerite Sutedjo
//I pledge my honor that I have abided by the Stevens Honor System.

import java.util.Random;
import java.util.ArrayList;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	private static class Node<E> {
		public E data; // key for the search
		public int priority; // random heap priority
		public Node<E> left;
		public Node<E> right;

		public Node(E data, int priority) {
			this.data = data;
			this.priority = priority;
		}

		public Node<E> rotateRight() {
			if (left == null) {
				throw new IllegalArgumentException("");
			} 
			else {
				Node<E> temp = new Node<E>(this.data, this.priority);
				temp.right = right;
				temp.left = left.right;
				data = left.data;
				priority = left.priority;
				left = left.left;
				right = temp;
			}
			return this;
		}

		public Node<E> rotateLeft() {
			if (right == null) {
				throw new IllegalArgumentException("");
			} 
			else {
				Node<E> temp = new Node<E>(this.data, this.priority);
				temp.left = left;
				temp.right = right.left;
				data = right.data;
				priority = right.priority;
				right = right.right;
				left = temp;

			}
			return this;
		}

		public String toString() {
			return "(key=" + data + ", priority=" + priority + ")";
		}
	}

	private Random priorityGenerator;
	private Node<E> root;
	private ArrayList<Integer> priorities = new ArrayList<Integer>();

	/** Creates an empty treap
	 */
	public Treap() {
		this.root = null;
		priorityGenerator = new Random();
	}

	/** Creates an empty treap and initializes priority generator using Random(Seed)
	 * @param seed new random number generator
	 */
	public Treap(long seed) {
		this.root = null;
		priorityGenerator = new Random(seed);
	}

	/** Calls add(E key, int priority) once random priority has been generated
	 * @param key in the treap
	 */
	boolean add(E key) {

		int priority = priorityGenerator.nextInt(100);
		while (priorities.contains(priority)) {
			priority = priorityGenerator.nextInt();
		}
		return add(key, priority);
		// Node <E> newNode = new Node<E> (key, priority);

	}

	/** Inserts the given element into the tree 
	 * Returns true if the addition to the treap was successful
	 * Returns false if the addition to the treap was unsuccessful
	 * @param key in the treap as its data
	 * @param priority the random priority amount 
	 */
	boolean add(E key, int priority) {
		Node<E> newNode = new Node<E>(key, priority);
		if (root == null) {
			root = newNode;
			priorities.add(priority);
			return true;
		}
		if (priorities.contains(priority)) {
			return false;
		}
		Node<E> current = root;
		Node<E> prev = null;
		Stack<Node<E>> path = new Stack<Node<E>>();
		while (current != null) {
			prev = current;
			path.push(current);
			int n = current.data.compareTo(key);
			if (n < 0) {
				current = current.right;
			} 
			else if (n > 0) {
				current = current.left;
			} 
			else { // n == 0
				return false;
			}
			// current = null
		}
		int num = prev.data.compareTo(key);
		if (num < 0) {
			prev.right = newNode;
		} 
		else {
			prev.left = newNode;
		}
		//System.out.println(this);
		reheap(path, newNode);
		priorities.add(priority);
		return true;
	}

	/** Takes the stack and pops off nodes to check 
	 * if priorities fits the formatting of a max heap
	 * @param key in the treap
	 * @param current the local root
	 */
	void reheap(Stack<Node<E>> stack, Node<E> point) {
		while (!stack.empty()) {
			Node<E> temps = stack.pop();
			// System.out.println(temps.left);
			if ((temps.right != null) && (temps.right.data.equals(point.data)) && (temps.priority < point.priority)) {
				temps.rotateLeft();
				//System.out.println("rotate left");
			} 
			else if ((temps.left != null) && (temps.left.data.equals(point.data))
					&& (temps.priority < point.priority)) {
				// System.out.println(temps.left.data);
				temps.rotateRight();
				//System.out.println("rotate right");
			} 
			else {
				break;
			}
		}

	}
	
	/** Returns true if the node with the given key is found
	 * if it is not found and the treap isn't modified then false
	 * @param key in the treap
	 */
	boolean delete(E key) {
		if(this.find(key)) {
			Node<E> currNode = deleteHelp(key, root);
			Node<E> prev = deleteHelp2(key, root);
			
			//System.out.println(currNode.data);
			
			while((currNode.left != null) || (currNode.right != null)) {
				if (prev.left == currNode) {
					prev = currNode.rotateRight();
				}
				else if (prev.right == currNode) {
					prev = currNode.rotateLeft();
				}
			}
			/*System.out.println(currNode.data);
			System.out.println(currNode.left);
			System.out.println(currNode.right);*/
			
			if (prev.left == currNode) {
				prev.left = null;
			}
			else if (prev.right == currNode) {
				prev.right = null;
			}
			
			return true;
		}
		else {
			return false;
		}
	}
	
	/** Returns the node that is trying to be deleted
	 * @param key in the treap
	 * @param current the local root
	 */
	public Node<E> deleteHelp(E key, Node<E> current) {
		int n = current.data.compareTo(key);
		if (n < 0) {
			return deleteHelp(key, current.right);
		} 
		else if (n > 0) {
			return deleteHelp(key, current.left);
		}
		else {
			return current;
		}
	}
	
	/** Returns the parent of the node that is getting deleted
	 * @param key in the treap
	 * @param current the local root
	 */
	public Node<E> deleteHelp2(E key, Node<E> current) {
		int n = current.data.compareTo(key);
		
		if((current.left.data == current) || (current.right.data == current)) {
			//at this point one of the children is the targeted node
			return current;
		}
		else {
			if (n < 0) {
				return deleteHelp(key, current.right);
			} 
			else if (n > 0) {
				return deleteHelp(key, current.left);
			}
			else {
				return current;
			}
		}
	}
	
	/** Finds a node with given key rooted at root
	 * If found return true, otherwise return false
	 * @param root node root
	 * @param key in the treap
	 */
	private boolean find(Node<E> root, E key) {
		Node<E> current = root;
		while (current != null) {
			int n = current.data.compareTo(key);
			if (n < 0) {
				current = current.right;
			} 
			else if (n > 0) {
				current = current.left;
			} 
			else if (n == 0) {
				return true;
			}
		}
		return false;
	}
	
	/** Finds a node and returns true if it is found
	 * @param key in the treap
	 */
	public boolean find(E key) {
		return find(root, key);
	}
	
	/** Converts a sub-tree to a string
	 * Performs a preorder traversal
	 * @param current The local root
	 * @param depth The depth
	 * taken from BTree.java toString provided in class
	 */
	private String toString(Node<E> current, int depth) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			sb.append(" ");
		}
		if (current == null) {
			sb.append("null\n");
		} 
		else {
			sb.append(current.toString() + "\n");
			sb.append(toString(current.left, depth + 1)); 
			sb.append(toString(current.right, depth + 1));
		}
		return sb.toString();
	}

	public String toString() {
		return toString(root, 0);
	}
}
