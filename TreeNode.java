public class TreeNode {

	Puzzle data;
	TreeNode right;
	TreeNode south;
	TreeNode left;
	TreeNode north;
	boolean expanded;
	int depth;
	int previousMoves;

	public TreeNode(Puzzle p) {
		data = p;
		right = null;
		south = null;
		left = null;
		north = null;
		expanded = false;
		depth = 0;
		previousMoves = 0;
	}
}
