
public class LinkedList {
	
	private class ListElement {
		private Object el1;
		private ListElement el2;

		public ListElement(Object el, ListElement nextElement) {
			el1 = el;
			el2 = nextElement;
		}

		public ListElement(Object el) {
			this(el, null);
		}

		public Object first() {
			return el1;
		}

		public ListElement rest() {
			return el2;
		}

		public void setFirst(Object value) {
			el1 = value;
		}

		public void setRest(ListElement value) {
			el2 = value;
		}
	}
	
	private ListElement head;

	public LinkedList() {
		head = null;
	}

	public void addFirst(Object o) {
		head = new ListElement(o, head);
	}

	public Object getFirst() {
		return head.first();
	}

	public Object get(int n) {
		ListElement d = head;
		while (n > 0) {
			d = d.rest();
			n--;
		}
		return d.first();
	}
	
	public String toString() {
		String s = "(";
		ListElement d = head;
		while (d != null) {
			s += d.first().toString();
			s += " ";
			d = d.rest();
		}
		s += ")";
		return s;
	}
	public void print() {
		ListElement d =head;
		while(d!=null) 
		{
			System.out.print(d.first().toString()+"\n");
			d=d.rest();
		}
	}
	public int size() 
	{
		int count=0;
		ListElement d=head;
		while(d!=null) 
		{
			d=d.rest();
			count++;
		}
		return count;
	}
	public void set(int n,Object o)
	{
		ListElement d=head;
		ListElement temp=new ListElement(o);
		for(int i=0;i<n-1;i++) {
			d=d.rest();
		}
		temp.el2=d.el2;
		d.el2=temp;		
	}
	public ListElement getLast() 
	{
		ListElement d=head;
		if(d.el2==null) {return d;}
		
		else{
			while(d.el2!=null) 
			{
				d=d.rest();
			}
			return d;
			
		}
	}
	public void addLast(Object o) 
	{
		ListElement neuveau=new ListElement(o,null);
		if (!isEmpty())getLast().el2=neuveau;
		else head=neuveau;
	}
	public int search(Object key) //Returns the index(minimum 0) of the element with corresponding data
	{
		int index=0;
		boolean found=false;
		ListElement d=head;
		while(d!=null)
		{	
			if(d.el1==key) 
			{
				found=true;
				break;
			}
			d=d.rest();
			index++;
		}
		if(found) {return index;}
		else return -1;
	}
	public Vector traverse() {
		ListElement d=head;
		Vector t=new Vector(100);
		while(d!=null) {
			t.addLast(d.el1);
			d=d.rest();
		}
		return t;
	}
	public void fropple() 
	{
		//I exchange the order of the nodes group by group, and every two nodes are divided into one group
		
		int count=0;//this variable is designed to mark the beginning of the processing procedure
		ListElement d=head;
		ListElement former=d;//this 'pointer' is designed to preserve the 
		while(d!=null) 
		{

			if(d.el2.el2!=null) 
			{
				System.out.print("Route if");print();
				System.out.println("Current d:"+d.el1);
				if(count==0) {//If we just start to process the Linklist
			head=d.el2;count++;
			
			ListElement p2=d.el2;
			d.el2=d.el2.el2;
			p2.el2=d;
			d=d.el2;
		
				}else {
				former.el2=d.el2;	//Bulid the bridge between current two-nodes 'group' and former 'group'
				//Otherwise we will lose connect between groups, leading to the loss of nodes during the process
				d.el2=d.el2.el2;
				former.el2.el2=d;
				former=d;
				d=d.el2;
				}
				
			}
			else if(d.el2!=null) {//When we come to the last group of the whole process
				System.out.print("route else");
				
				former.el2=d.el2;
				former.el2.el2=d;
				d=d.el2=null;
			}
		}
	}
	public void append(LinkedList l) 
	{
		ListElement oprend2=l.head;
		getLast().el2=oprend2;
	
	}
	public boolean isEmpty() {
		if (size()==0) {
			return true;
		}
		else return false;
		
	}
}
