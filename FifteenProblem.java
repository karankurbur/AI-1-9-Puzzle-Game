public class FifteenProblem {

	public static void main(String[] args) {
		String startState = args[0];
		String search = args[1];
		if (search.equals("DLS")) {
			String depth = args[2];
			DLS dls = new DLS(startState,depth);
		} else if (search.equals("GBFS")) {
			String heuristic = args[2];
			GreedyBFS GBFS = new GreedyBFS(startState,heuristic);
		} else if (search.equals("AStar")) {
			String heuristic = args[2];
			AStar astar = new AStar(startState,heuristic);
		} else if (search.equals("BFS")) {
			BFSTree bfs = new BFSTree(startState);
		} else if (search.equals("DFS")) {
			DFS dfs = new DFS(startState);
		}
	}
}