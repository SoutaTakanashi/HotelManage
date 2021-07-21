public class MatrixGraph 
{	
	private Matrix data;
	private int size;
	public MatrixGraph(int nrNodes)
	{
		data=new Matrix(nrNodes);
		size=nrNodes;
	}
	public int getSize() {
		return size;
	}
	public void addEdge(int from, int to, int w)
	{
		data.set(from-1, to-1, w);

	}

	public double getEdge(int from, int to)
	{
		return (Double)getData().get(from-1, to-1);
	}

	public Matrix getData() {
		return data;
	}

	public void setData(Matrix data) {
		this.data = data;
	}
	public void extendMatrix() {
		data.extendMatrix();
		size++;
	}
	
	public int[][]getArray() {
		int theMatrix[][]=new int[size][size];
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) 
				theMatrix[i][j]= (int)data.get(i, j);

		}
		
		return theMatrix;
	}
	public void printArray() {
		System.out.println("________________________Adjacency Matrix__________________________");
		int theMatrix[][]=getArray();
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(theMatrix[i][j]==0)System.out.print("X"+"   ");
				else System.out.print(theMatrix[i][j]+"   ");
			}
				System.out.println();
			}
	}
	
	
}