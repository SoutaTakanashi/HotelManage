public class Queue {//FIFO
	
	private Vector data;
	
	public Queue() {
		// todo
		data=new Vector();
	}

	public void push(Object o) {
		// todo
		data.addLast(o);
	}

	public Object pop() {
		// todo
		Object o=data.getFirst();
		data.removeFirst();
		return o;
	}

	public Object top() {
		// todo
		Object o=data.getFirst();
		return o;
	}

	public int size() {
		// todo
		return data.size();
	}

	public boolean empty() {
		// todo
		return data.isEmpty();
	}
	public void print() {
		Vector temp=data;
		for(int i=0;i<temp.size();i++) {
			System.out.print((Comparable)data.get(i)+" ");
		}
	}
	public void clear() {
		for(int i=0;i<size();i++) {pop();}
	}
}

