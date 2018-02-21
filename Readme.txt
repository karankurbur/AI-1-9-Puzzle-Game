Run configuration: Please use underscore instead of space to represent blank space.
-------------------
BFS
123456789ABC_DFE
3, 17, 8, 10

51246A379_C8DEBF
-1, -1, -1, -1
-------------------
DFS
123456789ABC_DFE
3, 2247, 1065, 13

51246A379_C8DEBF
-1, -1, -1, -1
-------------------
BGFS h1
51246A379_C8DEBF
11, 28, 13, 16

123456789ABC_DFE
3, 7, 4, 4
-------------------
BFGS h2
51246A379_C8DEBF
19, 98, 46, 53

123456789ABC_DFE
3, 7, 4, 4
-----------------
AStar h1
51246A379_C8DEBF
11, 28, 13, 16

123456789ABC_DFE
3, 7, 4, 4
------------------
AStar h2
51246A379_C8DEBF
11, 59, 29, 31

123456789ABC_DFE
3, 7, 4, 4
--------------------
DLS
51246A379_C8DEBF 5
-1, -1, -1, -1

123456789ABC_DFE 4
3, 92, 40, 7
---------------
Time complexity:
BFS - O(b^d)
DFS - O(b^d)
BGFS - O(b^d)
AStar - O(b^d)
DLS - O(b^d)