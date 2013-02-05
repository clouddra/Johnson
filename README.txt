Approach to problem:

To avoid the need to revisit a point, we will need to find the minimum distance between all the k+1 shopping items (vertex 0 is included). 
To get the minimum distance, we can either use Floyd–Warshall algorithm or Johnson's algorithm. 
A variant of Johnson's algorithm is used as there is no negative edges. We can skip the Bellman Ford steps of the program.

Algorithm:
1) Dijkstra's algorithm is ran k+1 times 
2) Graph is compressed into shopPoint[k+1][k+1]
3) TSP is used to traverse the compressed adjacency list

Collaborators:
Hong Shihan, Chua Wei Ting

