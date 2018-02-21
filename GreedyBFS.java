import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class GreedyBFS {
	public static PriorityQueue<TreeNode> fringe;
	public static Set<String> visitedSet;
	public static int maxFringe;
	public static boolean found;
	public static int depth;
	public static int numCreated;
	public static int numExpanded;

	public GreedyBFS(String puzzle, String h) {
		if (h.equals("h1")) {
			h1 h1 = new h1();
			fringe = new PriorityQueue<TreeNode>(h1);
			visitedSet = new HashSet<String>();
			Puzzle p = new Puzzle(puzzle);
			TreeNode root = new TreeNode(p);
			fringe.add(root);
			visitedSet.add(root.data.toString());
			numExpanded = 0;
			numCreated = 0;
			maxFringe = 1;
			search();
		} else if (h.equals("h2")) {
			h2 h1 = new h2();
			fringe = new PriorityQueue<TreeNode>(h1);
			visitedSet = new HashSet<String>();
			Puzzle p = new Puzzle(puzzle);
			TreeNode root = new TreeNode(p);
			fringe.add(root);
			visitedSet.add(root.data.toString());
			numExpanded = 0;
			numCreated = 0;
			maxFringe = 1;
			search();
		}
	}

	public static class h1 implements Comparator<TreeNode> {
		public int compare(TreeNode arg0, TreeNode arg1) {
			return arg0.data.h1 - arg1.data.h1;
		}
	}

	public static class h2 implements Comparator<TreeNode> {
		public int compare(TreeNode arg0, TreeNode arg1) {
			return arg0.data.h2 - arg1.data.h2;
		}
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
			TreeNode current = fringe.remove();
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
