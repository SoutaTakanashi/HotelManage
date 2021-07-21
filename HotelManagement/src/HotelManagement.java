
/*Write a class to represent a Room of the hotel. The Room is characterized by these properties:

Wing
Room number
Status of the Room - READY (free&clean) / OCCUPIED / CHECKED OUT
Unique id
*/



public class HotelManagement implements IManagementSystem{
	private int uniqueID=0;
	private int clientID=0;
	private LinkedList allClients;//Okay now clients and rooms will be stored in Linkedlists instead.
	private LinkedList allrooms;  
	private MatrixGraph wings;
	private Vector Wingnames;
	public HotelManagement() {
		allrooms=new LinkedList();
		allClients=new LinkedList();
		wings=new MatrixGraph(1);
		Wingnames=new Vector();
		
	}
	
	public int addDoubleRoom(String wing, int roomNumber, String status) {
			DoubleRoom neuRoom=new DoubleRoom(wing,roomNumber,status,uniqueID);
			allrooms.addLast(neuRoom);
			return uniqueID++;
	}

	/*
	 * Add a new family room with given parameters - wing where the room is located,
	 * number of the room, the status of the room
	 * (READY/OCCUPIED/CHECKEDOUT) - to the management system
	 *
	 * @param wing where the house is located
	 *
	 * @param roomNumber number of the room
	 * 
	 * @param status - A room can be READY, OCCUPIED, CHECKEDOUT
	 *
	 * @return ID of the room
	 */
	
	public int addFamilyRoom(String wing, int roomNumber, String status){
		FamilyRoom neuRoom=new FamilyRoom(wing,roomNumber,status,uniqueID);
		allrooms.addLast(neuRoom);
		return uniqueID++;
	}
	/*
	 * Add a new client with given parameters - name, email address to the
	 * management system
	 *
	 * @param name name of the client
	 * 
	 * @param emailAddress email address of the client
	 *
	 * @return ID of the client
	 */
	public int addClient(String name, String emailAddress){
		Client c=new Client(name,emailAddress,clientID);
		allClients.addLast(c);
		return clientID++;
	}
	/*
	 * Print all rooms in the system. Print number of rooms and a summary details
	 * about each one.
	 */
	public void printRooms(){
		allrooms.print();
	}
	/*
	 * Print all clients. Print number of clients and a summary details
	 * about each one.
	 */
	public void printClients(){
		allClients.print();
	
	}
	
	// Part 2
	
	/*
	 * Perform check in of a double room.
	 *
	 * @param client ID of a client who's requesting a room
	 *
	 * @return the id of the checked in room 
	 */
	public int checkInDoubleRoom(int client){
		Client c=(Client)allClients.get(client);
		if(c.getRoomNum()!=-1) {
			System.out.println("The client "+c.getName()+"  has already been allocated with a Room. No need for a new Room");
			System.out.println("Current roomnumber:"+c.getRoomNum());
			return c.getRoomNum();
		}else {
			Vector avaRooms=searchAvailableRooms();//Return with all of the available rooms, including those checkedout rooms with lower priority than Ready rooms
		while(true) {
			Room r=(Room) avaRooms.getFirst();
			avaRooms.removeFirst();
				if(r.getStatus()=="READY"&&r.getCapacity()==2) {//If there's a vacant double Room	
					c.setRoomNum(r.getRoomNum());
					r.setStatus("OCCUPIED");
					System.out.println("The client "+c.getName()+"(clientNum:"+client+") checks in successfully!");
					return r.getRoomNum();
				}else {
					if(r.getStatus()=="CHECKOUT"&&r.getCapacity()==2) {//If there's only checkedout double Room available
						c.setRoomNum(r.getRoomNum());
						r.setStatus("OCCUPIED");
						r.setClean(false);//If assigned with a checkout Room, it should be cleaned.
						System.out.println("The room "+r.getRoomNum()+" should be cleaned before the client's checking in.");
						//organizeCleanings();//Will be implemented in part 3.
						System.out.println("The cleaning procedure is done. Please let the client check in.");
						System.out.println("The client "+c.getName()+"(clientNum:"+client+") checks in successfully!");
						return r.getRoomNum();
					}
					
				}
		if(avaRooms.size()==0)break;
					
		}
		
			}
		//If no Room available:
		c.setRoomNum(-1);
		return -1;
		
	}
	/*
	 * Perform check in of a family room.
	 *
	 * @param client ID of a client who's requesting a room
	 *
	 * @return the id of the checked in room
	 */
	public int checkInFamilyRoom(int client){
		Client c=(Client)allClients.get(client);
		if(c.getRoomNum()!=-1) {
			System.out.println("The client"+c.getName()+" has already been allocated with a Room. No need for a new Room");
			System.out.println("Current roomnumber:"+c.getRoomNum());
			return c.getRoomNum();}
		else {
			Vector avaRooms=searchAvailableRooms();
			while(true) {
				Room r=(Room) avaRooms.getFirst();
				avaRooms.removeFirst();
					if(r.getStatus()=="READY"&&r.getCapacity()==4) {//If there's a vacant double Room	
						c.setRoomNum(r.getRoomNum());
						System.out.println("The client "+c.getName()+"(clientNum:"+client+") checks in successfully!");
						r.setStatus("OCCUPIED");
						return r.getRoomNum();
					}else {
						if(r.getStatus()=="CHECKOUT"&&r.getCapacity()==4) {//If there's only checkedout family Room available
							c.setRoomNum(r.getRoomNum());
							r.setStatus("OCCUPIED");
							r.setClean(false);//If assigned with a checkout Room, it should be cleaned.
							System.out.println("The room "+r.getRoomNum()+" should be cleaned before the client's checking in.");
							//organizeCleanings();
							System.out.println("The cleaning procedure is done. Please let the client check in.");
							System.out.println("The client "+c.getName()+"(clientNum:"+client+") checks in successfully!");
							return r.getRoomNum();
						}			
					}
			if(avaRooms.size()==0)break;
						
			}
			//If no Room available:

		}
		c.setRoomNum(-1);
		return -1;	
	}
	/*
	 * Perform a check out of a selected room (double  or family)
	 *
	 * @param client ID of a client who's checking out
	 * 
	 * @return true when check out was successful otherwise return false
	 */
	public boolean checkOutRoom(int client){
		Client c=(Client)allClients.get(client);
		Room r=searchRoomByNum(c.getRoomNum());
		if (r.getIDUnique()!=-1){
			r.setStatus("CHECKOUT");
			System.out.println("The client "+c.getName()+"(clientNum:"+client+") checks out successfully!");
			return true;
			}
		else {
			System.out.println("The client "+c.getName()+"(clientNum:"+client+")does not have a room!");
			return false;
		}
		
	}
	
	
	public Room searchRoomByNum(int roomNum) {//Returns the uniqueID of the Room
		for(int i=0;i<allrooms.size();i++) {
			Room r=(Room) allrooms.get(i);
			if (r.getRoomNum()==roomNum) {
				return r;
			}
	}
		Room empty=new Room("Error",roomNum,"DO NOT EXIST!",-1);
		return empty;
	}
	
	/*
	 * Search for a room based on the availability. 
	 * Available rooms are free and cleaned. 
	 *
	 * @return a Vector containing the id of the available rooms.
	*/
	public Vector searchAvailableRooms(){
		Vector a=allrooms.traverse();
		Vector ava=new Vector(100);
		for(int i=0;i<a.size();i++) {
			Room r=(Room) a.getData(i);
			if(r.getStatus()=="READY") {
				ava.addLast(r);
			}
			
		}
		return ava;
		
	}
	
	/*
	 * Print all the rooms in state:READY
	*/
	public void printAvailableRooms(){
		Vector available=searchAvailableRooms();
		available.print();
	}
	
	
	/*
	 * Print all the rooms in state: OCCUPIED
	*/
	public void printOccupiedRooms(){
		Vector busy=searchBusyRooms();
		busy.print();
		
	}
	
	public Vector searchBusyRooms() {
		Vector a=allrooms.traverse();
		Vector busy=new Vector(100);
		for(int i=0;i<a.size();i++) {
			Room r=(Room) a.getData(i);
			if(r.getStatus()=="OCCUPIED") {
				busy.addLast(r);
			}
		}
		return busy;
	}
	public Vector checkoutRooms() {
		Vector a=allrooms.traverse();
		Vector check=new Vector(100);
		for(int i=0;i<a.size();i++) {
			Room r=(Room) a.getData(i);
			if(r.getStatus()=="CHECKOUT") {
				check.addLast(r);
			}
		}
		return check;
	}
	//Part 3
	
	/*
	 * Adds a wing into the system
	 *
	 * @param wingName name of the wing that is added to the system
	 * 
	 */
	public void addWing(String wingName){

		Wingnames.addLast(wingName);
		if(Wingnames.size()>wings.getSize())
		wings.extendMatrix();
	}
	
	/*
	 * Adds connection between wings into the system
	 *
	 * @param wing1 name of the first wing that is being connected
	 * 
	 * @param wing2 name of the second wing that is being connected
	 * 
	 * @param distance is the distance between the two wings
	 * 
	 */
	public void connectWings(String wing1, String wing2, double distance){
		
		int index1=Wingnames.index(wing1);
		int index2=Wingnames.index(wing2);
		
		wings.addEdge(index1+1,index2+1,(int)distance);
		wings.addEdge(index2+1, index1+1, (int)distance);
	}
	
	/*
	 * Organise cleaning for rooms in all the wings.
	 */
	public void organizeCleaning(){
		int res=100000;
		Queue greatestPath=new Queue();
		int size=Wingnames.size();
		int [][]Matrix=wings.getArray();
		wings.printArray();
		System.out.println("________________________Cleanning Plan_____________________________");
		Matrix.toString();
		Macchiato m=new Macchiato();
		for(int i=0;i<size;i++) {
			Vector temp=new Vector();
			temp=m.cleaningPath(Matrix, i);
			int costCurr=(int)temp.get(0);
			if(costCurr<res) {
				res=costCurr;
				greatestPath=(Queue)temp.get(1);
			}
		}
		//System.out.println("The lowest time cost:"+res);
		
		
		Queue temp=greatestPath;
		String start=(String)Wingnames.get((int)greatestPath.top());
		System.out.println("The lowest time spent on the way:"+res);
		System.out.print("The best path:");
		int[]wing=new int[size];
		for(int i=0;i<size;i++) {
			int index=(int)temp.pop();
			System.out.print(Wingnames.get(index)+"->");
			wing[i]=index;
		}
		System.out.println("Finally back to "+start+" ,and start to clean double rooms");
		int [][]time=time(size);
		System.out.println("FamilyRoomTime(Rooms):");
		int total=0;
		for(int i=0;i<size;i++) {
			System.out.print(time[0][wing[i]]+" ("+time[0][wing[i]]/120+")");
			total+=time[0][wing[i]];
			if(i<size-1)System.out.print("->");
		}
		System.out.println("\nFamilyTotalTime:"+total);
		total=0;
		System.out.println("\nDoubleRoomTime(Rooms):");
		for(int i=0;i<size;i++) {
			System.out.print(time[1][wing[i]]+" ("+time[1][wing[i]]/60+")");
			total+=time[1][wing[i]];
			if(i<size-1)System.out.print("->");
		}System.out.println("\nDubleTotalTime:"+total);
		
	}
	public int[][] time(int size){
	int[][]a=new int[2][size];	//Row 0:Time for familyrooms;Row 1:Time for doublerooms
	for(int i=0;i<2;i++) 
		for(int j=0;j<size;j++) {
			a[i][j]=0;
		}
	Vector cr=checkoutRooms();
		for(int i=0;i<cr.size();i++) {
			Room r=(Room)cr.get(i);
			if(r.getCapacity()==4) {//If it is a family room.
				int index=Wingnames.index(r.getWing());
				if(index!=-1)
				a[0][index]+=120;
			}else {//Double room.
				int index=Wingnames.index(r.getWing());
				if(index!=-1)
				a[1][index]+=60;
			}
		}
	return a;
	}
	public static  void main(String[]args) {
		System.out.println("Bienvenue / Welkom. Now we are Simulating the daily affairs of a hotel.");
		System.out.println("_________________________________________________________");
		HotelManagement m=new HotelManagement();
		
		m.addWing("Wing A");
		m.addWing("Wing B");
		m.addWing("Wing C");
		m.addWing("Wing D");
		m.addWing("Wing E");
		System.out.println("Add several rooms and print them below:");

		m.addDoubleRoom("Wing A", 101, "READY");
		m.addDoubleRoom("Wing A", 102, "READY");
		m.addDoubleRoom("Wing A", 103, "READY");
		m.addFamilyRoom("Wing A", 104, "READY");
		m.addFamilyRoom("Wing B", 201, "CHECKOUT");
		m.addFamilyRoom("Wing B", 202, "READY");
		m.addDoubleRoom("Wing B", 203, "READY");
		m.addFamilyRoom("Wing C", 301, "READY");
		m.addFamilyRoom("Wing C", 302, "CHECKOUT");
		m.addDoubleRoom("Wing C", 303, "READY");
		m.addDoubleRoom("Wing C", 304, "CHECKOUT");
		m.addFamilyRoom("Wing D", 405, "READY");
		m.addFamilyRoom("Wing D", 406, "CHECKOUT");
		m.addDoubleRoom("Wing D", 401, "READY");
		m.addDoubleRoom("Wing D", 402, "CHECKOUT");
		m.addFamilyRoom("Wing E", 502, "READY");
		m.addFamilyRoom("Wing E", 503, "CHECKOUT");
		m.addDoubleRoom("Wing E", 501, "CHECKOUT");
		m.addDoubleRoom("Wing E", 504, "CHECKOUT");
		System.out.println("__________________Rooms' information_____________________");
		m.printRooms();
		
		System.out.println("And then, add 4 clients then test if printClient() works.");
		m.addClient("Arjen Robben", "arjen@vub.be");//My favorite soccer player.
		m.addClient("Romelu Lukaku", "lkk@inter.it");
		m.addClient("Robin van Persie", "blabla@knvb.nl");
		m.addClient("Wesley Sneijder", "blabla2@knvb.nl");
		m.addClient("Bart Jansen", "bart@blabla.be");
		System.out.println("__________________Clients' information_____________________");
		m.printClients();
		
		m.checkInDoubleRoom(3);
		m.checkInDoubleRoom(0);
		m.checkInFamilyRoom(1);
		m.checkInFamilyRoom(4);
		m.checkOutRoom(1);
		m.checkOutRoom(3);
		m.checkOutRoom(0);
		m.checkOutRoom(4);
		m.checkInFamilyRoom(2);
		//m.checkInDoubleRoom(1);
		//m.checkInDoubleRoom(2);

		System.out.println("_______________________________ALL AVAILABLE ROOMS NOW:______________________");
		m.printAvailableRooms();
		System.out.println("_______________________________ALL OCCUPIED ROOMS NOW:______________________");
		m.printOccupiedRooms();

		
		m.connectWings("Wing A", "Wing B", 25);
		m.connectWings("Wing A", "Wing D", 80);
		m.connectWings("Wing B", "Wing D", 100);
		m.connectWings("Wing B", "Wing C", 45);
		m.connectWings("Wing C", "Wing D", 130);
		m.connectWings("Wing C", "Wing E", 20);
		m.connectWings("Wing A", "Wing E", 150);
		m.connectWings("Wing D", "Wing E", 120);
		m.organizeCleaning();
		System.out.println("** Project by Yan Yiming who likes macchiato. **");
		System.out.println("** Thank you for your guidance! **");
	}
	
}
