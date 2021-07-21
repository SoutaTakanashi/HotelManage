public class Matrix 
{
	// some appropriate private members.
	private Comparable[][] matrix;
	private int size;
	public Matrix(Comparable nrNodes)
	{	int size=(int)nrNodes;
		// allocate an N-by-N matrix where N = nrNodes
		// all elements are initially 0
		matrix=new Comparable[size][];
		for (int i=0;i<size;i++) {
			matrix[i]=new Comparable[size];
			for(int j=0;j<size;j++) {
			matrix[i][j]=0;	
			}
		}

	}
	public int getSize() {
		return size;
	}
	public void set(int row, int col, Comparable weight)
	{
		// store the weight at the given row and column.
		matrix[row][col]= weight;
	}

	public Comparable get(int row, int col)
	{
		
		return matrix[row][col];
		// return the weight at the given row and column.
	}

	public Comparable[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Comparable[][] matrix) {
		this.matrix = matrix;
	}
	
	public void extendMatrix() {
		int len=matrix.length+1;
		Comparable[][]temp=new Comparable[len][];
		for(int i=0;i<len;i++) {
		temp[i]=new Comparable[len];
		for(int j=0;j<len;j++) {
			if(i<len-1&&j<len-1) {
				temp[i][j]=matrix[i][j];
			}
			else temp[i][j]=0;
		}
		}
			matrix=temp;
	}
	public void print() {
		int len=matrix.length;
		for(int i=0;i<len;i++) {
			for (int j=0;j<len;j++) {
				if(matrix[i][j].equals(0))System.out.print("X"+"   ");
				else System.out.print(matrix[i][j]+"   ");
			}System.out.println();
		}
	}
	
}