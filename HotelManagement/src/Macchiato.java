public class Macchiato {
	private int MAX=10000;
	public Vector cleaningPath(int[][]Graph,int start) {//greedy algorithm£¬returns an acyclic path
		int vertexs=Graph.length;
		Vector res=new Vector();//Two Elements: element 1:shortest distance of this plan;element 2: the path for this plan(store in Queue)
		Queue path=new Queue();
		int cost=0;
		boolean []visited=new boolean[vertexs];
		for(int i=0;i<vertexs;i++)visited[i]=false;visited[start]=true;// An array that stores the status whether the node is visited.
		path.push(start);
		int curr=start;//Marks where we're now.
		for(int i=0;i<vertexs;i++) {// Traverse all vertexes, except the start point(Wing).
			//To look for local optimal solution
			if(i!=start) {
				int opt=MAX; 
				int toNode=0;
				for(int j=0;j<vertexs;j++) {
					int weight=Graph[curr][j];
					if(j!=start&&!visited[j]&&weight<opt&&Graph[curr][j]!=0) {//If it is a better solution.
						opt=weight;
						toNode=j;
					}	
				}	
				cost+=opt;
				visited[toNode]=true;
				path.push(toNode);//Push the better solution into the queue
				curr=toNode;
			}
		}
		cost+=Graph[curr][start];
		res.set(0, cost);
		res.set(1, path);
		return res;
	}
}
