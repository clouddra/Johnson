import java.util.*;

// A0072292H
// Chong Yun Long
// Hong Shihan, Chua Wei Ting

class Supermarket {
	private int N; // number of items in the supermarket. V = N+1 
	private int K; // the number of items that Steven has to buy
	private int[] shoppingList; // indices of items that Steven has to buy
	private int[][] T; // the complete weighted graph that measures the direct walking time to go from one point to another point in seconds

	// if needed, declare a private data structure here that
	// is accessible to all methods in this class
	// --------------------------------------------
	private int[][] memo, shopPoint  ; 
	private int[] shoppingList2 ;
	private PriorityQueue<IntegerPair> q ; 
	
	public Supermarket() {
		q = new PriorityQueue<IntegerPair>() ;
	}

	int Query() {
		// You have to report the quickest shopping time that is measured
		// since Steven enters the supermarket (vertex 0),
		// completes the task of buying K items in that supermarket as ordered by Grace,
		// then, reaches the cashier of the supermarket (back to vertex 0).
		//
		// write your answer here

		memo = new int[K+1][(1<<K+1)-1] ;
		shopPoint = new int[K+1][K+1] ;
		shoppingList2 = new int[K+1];
		
		// copy and append with 0, we treat 0 as one of the items
		shoppingList2 = Arrays.copyOf(shoppingList, K+1) ; 
		
		// dijkstra all sources (runs faster than Floyd–Warshall)
		for (int i = 0; i < K+1 ; i++) 
			dijkstra(i) ;
		
		for (int i = 0; i < K+1 ; i++)
			Arrays.fill(memo[i], -1) ;
		
		return TSP(0, 0);
	}

	// You can add extra function if needed
	// --------------------------------------------
	
	void dijkstra(int itemIndex)
	{
		int source = shoppingList2[itemIndex] ;
		int[] weights = new int[N+1] ;
		IntegerPair current ;

		Arrays.fill(weights, Integer.MAX_VALUE) ;
		weights[source] = 0 ;
		q.offer(new IntegerPair(source,0)) ;

		while (!q.isEmpty()) {
			current = q.poll() ;
			if (weights[current.index()]==current.dist())
				for (int i=0 ; i<N+1 ; i++) {
					if (weights[i] > current.dist() + T[current.index()][i]) {
						weights[i] = current.dist() + T[current.index()][i] ;
						q.offer(new IntegerPair(i, weights[i])) ;
					}
				}
		}
		// compress the search into only the items we need (size N+1 to K+1)
		for (int i = 0; i < K+1 ; i++)
			shopPoint[itemIndex][i] = weights[shoppingList2[i]] ;
	}
	
	int TSP(int u, int visit)
	{
		if (visit == ( (1 << (K+1) ) - 1 ) ) 
			return shopPoint[u][0] ;
		if (memo[u][visit] != -1)
			return memo[u][visit] ;

		memo[u][visit] = Integer.MAX_VALUE ;
		for (int v=0 ; v<K+1 ; v++) {
			if ( ( visit&(1<<v) ) == 0 )
				memo[u][visit] = Math.min(memo[u][visit], shopPoint[u][v] + TSP(v, (visit | (1 << v) )) ) ;
		}
		return memo[u][visit] ;		
	}


	void run() {
		// do not alter this method
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt(); // there will be several test cases
		while (TC-- > 0) {
			// read the information of the complete graph with N+1 vertices
			N = sc.nextInt(); K = sc.nextInt(); // K is the number of items to be bought

			shoppingList = new int[K];
			for (int i = 0; i < K; i++)
				shoppingList[i] = sc.nextInt();

			T = new int[N+1][N+1];
			for (int i = 0; i <= N; i++)
				for (int j = 0; j <= N; j++)
					T[i][j] = sc.nextInt();

			System.out.println(Query());
		}
	}

	public static void main(String[] args) {
		// do not alter this method
		Supermarket ps7 = new Supermarket();
		ps7.run();
	}
}
