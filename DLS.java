import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class DLS  {
	public static Stack<TreeNode> fringe;
	public static Set<String> visitedSet;
	public static int maxFringe;
	public static boolean found;
	public static int numCreated;
	public static int numExpanded;
	public static int depth;
	public static void main(String[] args) {
//		fringe = new Stack<TreeNode>();
//		visitedSet = new HashSet<String>();
//		Puzzle p = new Puzzle("123456789ABC_DEF");
//		TreeNode root = new TreeNode(p);
//		fringe.add(root);
//		visitedSet.add(root.data.toString());
//		p.display();
//		numExpanded = 0;
//		numCreated = 0;
//		maxFringe = 1;
//		System.out.println();
//		search();
	}
	
	public DLS(String a, String b) {
		int d = Integer.parseInt(b);
		depth = d;
		fringe = new Stack<TreeNode>();
		visitedSet = new HashSet<String>();
		Puzzle p = new Puzzle(a);
		TreeNode root = new TreeNode(p);
		fringe.add(root);
		visitedSet.add(root.data.toString());
		numExpanded = 0;
		numCreated = 0;
		maxFringe = 1;
		search();
	}

	public static void expand(TreeNode node) {
		Puzzle temp = node.data;
		int[] moves = temp.moves;
		if (moves[0] == 1) {
			Puzzle rightTemp = temp.moveRight();
			if (!inVisitedSet(rightTemp)) {
				node.right = new TreeNode(rightTemp);
				node.right.depth = node.depth + 1;
				numCreated++;
				fringe.add(node.right);
				visitedSet.add(rightTemp.toString());
			}
		}
		if (moves[1] == 1) {
			Puzzle rightTemp = temp.moveDown();
			if (!inVisitedSet(rightTemp)) {
				node.south = new TreeNode(rightTemp);
				node.south.depth = node.depth + 1;
				numCreated++;
				fringe.add(node.south);
				visitedSet.add(rightTemp.toString());
			}
		}
		if (moves[2] == 1) {
			Puzzle rightTemp = temp.moveLeft();
			if (!inVisitedSet(rightTemp)) {
				node.left = new TreeNode(rightTemp);
				node.left.depth = node.depth + 1;
				numCreated++;
				fringe.add(node.left);
				visitedSet.add(rightTemp.toString());
			}
		}
		if (moves[3] == 1) {
			Puzzle rightTemp = temp.moveUp();
			if (!inVisitedSet(rightTemp)) {
				node.north = new TreeNode(rightTemp);
				node.north.depth = node.depth + 1;
				numCreated++;
				fringe.add(node.north);
				visitedSet.add(rightTemp.toString());
			}
		}
	}

	public static boolean search() {
		while (!fringe.isEmpty()) {
			TreeNode current = fringe.pop();
			if (current.depth <= depth) {
				numExpanded++;
				expand(current);
				if (current.data.isDone()) {
					depth = current.depth;
					System.out.println(depth + ", " + numCreated + ", " + numExpanded + ", " + maxFringe);
					return true;
				}
				if (fringe.size() > maxFringe) {
					maxFringe = fringe.size();
				}
			}
		}
		System.out.println("-1, -1, -1, -1");
		return false;
	}

	public static boolean inVisitedSet(Puzzle puzzle) {
		boolean contained = false;
		for (String s : visitedSet) {
			if (puzzle.toString().equals(s)) {
				contained = true;
			}
		}
		return contained;
	}

}
