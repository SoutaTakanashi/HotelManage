
public class Stack {
	
	private Vector data;
	public Stack() {
		data=new Vector();
	}

	public void push(Object o) {
		// todo
		data.addLast(o);
	}

	public Object pop() {
		// todo
		Object o=data.getLast();
		data.removeLast();
		return o;
	}

	public Object top() {
		// todo
		return data.getFirst();
	}

	public int size() {
		// todo
		return data.size();
	}

	public boolean empty() {
		// todo
		return data.isEmpty();
	}
	/*public static void main(String[]args) {
		Stack s=new Stack();
		for(int i=0;i<200;i++) {
			s.push(i);
		}
		for(int i=0;i<200;i++) {
			System.out.println(s.pop());
		}
	}*/

	
	
}
