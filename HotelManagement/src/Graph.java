public class Graph 
{
	
	 public class Node implements Comparable
	    {
	        private Comparable info;
	        private Vector edges;
	        public Node(Comparable label)
	        {
	            info = label;
	            edges = new Vector();
	        }

	        public void addEdge(Edge e)
	        {
	            edges.addLast(e);
	        }

	        public int compareTo(Object o)
	        {
	            // two nodes are equal if they have the same label
	            Node n = (Node)o;
	            return n.info.compareTo(info);
	        }
	        
	        public Comparable getLabel()
	        {
	            return info;
	        }
	        
	    }
	    
	    private class Edge implements Comparable
	    {
	        private Node toNode;
	        private int weight;
	        public Edge(Node to,int w)
	        {
	            toNode = to;
	            weight=w;
	        }
	        
	        public int compareTo(Object o)
	        {
	            // two edges are equal if they point
	            // to the same node.
	            // this assumes that the edges are
	            // starting from the same node !!!
	            Edge n = (Edge)o;
	            return n.toNode.compareTo(toNode);
	        }
	    }
	
	
	 private Vector nodes;
	    
	    public Graph()
	    {
	        nodes = new Vector();
	    }
	    
	    public void addNode(Comparable label)
	    {
	        nodes.addLast(new Node(label));
	    }
        public void print() {
        	int size=nodes.size();
        	for(int i=0;i<size;i++) {
        		 Node nodecurr=  (Graph.Node) nodes.get(i);
        		 
        		 Comparable nodeLabel1=nodecurr.info;
        		 System.out.println("\nNode Name:"+nodeLabel1);
        		 Node n1 = findNode(nodeLabel1);
        		 Vector edges=n1.edges;
        		 System.out.println("To vertex:");
        		 for(int j=0;j<edges.size();j++) {
        			 Edge curr=(Edge)edges.get(j);
        			 Comparable info=curr.toNode.info;
        			 System.out.print(info+"  Cost="+curr.weight+'\n');
        			 
        		 }
        		 
        	}
        }
	    private Node findNode(Comparable nodeLabel)
	    {
	        Node res = null;
	        for (int i=0; i<nodes.size(); i++)
	        {
	            Node n = (Node)nodes.get(i);
	            if(n.getLabel() == nodeLabel)
	            {
	                res = n;
	                break;
	            }
	        }
	        return res;
	    }
	    
	    public void addEdge(Comparable nodeLabel1,
	                        Comparable nodeLabel2,int weight)
	    {
	        Node n1 = findNode(nodeLabel1);
	        Node n2 = findNode(nodeLabel2);
	        n1.addEdge(new Edge(n2,weight));
	    }
   
    public boolean findPath(Comparable nodeLabel1,Comparable nodeLabel2) {
    	Node startState=findNode(nodeLabel1);
    	Node endState=findNode(nodeLabel2);
    	Stack toDoList=new Stack();
    	toDoList.push(startState);
    	Vector visited=new Vector(); 
    	while(!toDoList.empty()) {
    		Node current=(Node)toDoList.pop();
    		visited.addLast(current);
    		if(current==endState) return true;
    		else {
    			for(int i=0;i<current.edges.size();i++) {
    				Edge e=(Edge)current.edges.get(i);
    				
    				if(visited.index(e.toNode)==-1)
    				toDoList.push(e.toNode);
    				}
    			}
    							}
    	return false;
    }
    public Queue path(Comparable nodeLabel1,Comparable nodeLabel2) {
    	Queue path=new Queue();
    	Node startState=findNode(nodeLabel1);
    	Node endState=findNode(nodeLabel2);
    	Stack toDoList=new Stack();
    	toDoList.push(startState);
    	path.push(startState.info);
    	while(!toDoList.empty()) {
    		Node current=(Node)toDoList.pop();
    		if(current==endState) {break;}
    		else {
    			for(int i=0;i<current.edges.size();i++) {
    				Edge e=(Edge)current.edges.get(i);
    				Node n=e.toNode;
    				
    				if(findPath(n.info,endState.info)==true)
    				{
    					
    					toDoList.push(e.toNode);
        				path.push(e.toNode.info);
        				
    				}
    			}
    			
    			}
    							}
		System.out.print("Path from "+nodeLabel1+" to "+nodeLabel2+": ");
    		return path;
    }
    
    
   
}