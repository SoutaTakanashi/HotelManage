public class Vector extends Object
{
	private Object data[];
	private int count;

	private int cap;
	public Vector(int capacity)
	{
		setData(new Object[capacity]);
		count = 0;
		cap=capacity;
	}	public Vector()
	{
		setData(new Object[10]);
		count = 0;
		cap=10;
	}
	public int index(Object key) {
		int index=0;
		while (index<size()) {
			if(get(index).equals(key)) {
				return index;
			}	
			index++;
		}
		 return -1;
	}
	public void extendCapacity() 
	{
	    Object data2[];
		cap*=2;
	    data2=new Object[cap];
		for(int i=0;i<size();i++) 
		{
			data2[i]=data[i];
		}
		setData(data2);
	}
	public int size()
	{
		return count;
	}
 
	public boolean isEmpty()
	{
		return size() == 0;
	}

	public Object get(int index)
	{
		return data[index];
	}
	public void print() 
	{
		for(int i=0;i<size();i++)
		{
			System.out.print(data[i].toString()+'\n');
		}
		
	}
	public void set(int index, Object obj)
	{
		data[index] = obj;
	}

	public boolean contains(Object obj)
	{
		for(int i=0;i<count;i++)
		{
			if(data[i] == obj) return true;
		}
		return false;
	}
	
	public void addFirst(Object item)
	{
		// add your code
		for ( int i=count ; i>0; i--){
		data[i]=data[i-1] ;
		} data[0]= item ; 
		count++;
	}
//Assignment 4: method AddFitst
	//And assignment 9:extend capacity!
	public void addLast(Object o)
	{
		if (count==cap) {
			//System.out.println("Vector Full , Extend Capacity!");
			extendCapacity() ;
		}
		data[count] = o;
		count++;
		
		
	}
	
	/*
	public boolean binarySearch(Object key)
	{
	int start = 0;
	int end = count - 1;
	while(start <= end)
	{
		int middle = (start + end + 1) / 2;
		if(key < data[middle]) end = middle -1;
		else if(key > data[middle]) start = middle + 1;
		else return true;
	}
	return false;
	}
	*/

	public Object getFirst()
	{
		// add your code
		return data[0];
	}

	public Object getLast()
	{
		// add your code
		return data[count-1];
	}
//Assignment 5:Add method removeFirst&removeLast
	public void removeLast()
	{
		// add your code
		data[count-1]=null;
		count-=1;
	} 

	public void removeFirst()
	{
		// add your code
		for(int i=0;i<count-1;i++)
		{
			data[i]=getData(i+1);
		}data[count-1]=null;
		count-=1;
	}
	//Assignment 3:Add the method toString, which converts the vector to a string representation
	public String toString() 
	{
		String result=" ";
		for (int i=0;i<count;i++) 
		{
			result+=getData(i).toString();
			result+=' ';
		}
		return result;
	}
	//Assignment 6: add method reverse
	public void reverse() {
		//should not create a new vector,but really change the current vector
		for(int i=0;i<count/2;i++) 
		{
			Object temp=data[i];
			data[i]=data[count-i-1];
			data[count-i-1]=temp;
		}
		
	}
	//Assignment 7
	public Vector repeat() {
		Vector v2=new Vector(2*size());
		for(int i=0;i<size();i++) 
		{
		v2.addLast(getData(i));
		v2.addLast(getData(i));
		}
		return v2;
	}
	
	public Vector interleave(Vector v2) {
		Vector res=new Vector(2*size());
		for(int i=0;i<size();i++) 
		{
			res.addLast(getData(i));
			res.addLast(v2.get(i));
		}
		
		return res;
	}
	public Object getData(int index) {
		return data[index];
	}
	public void setData(Object data[]) {
		this.data = data;
	}
	
}