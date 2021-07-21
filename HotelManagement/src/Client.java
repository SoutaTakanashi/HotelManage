
public class Client extends HotelManagement{
	private String Name;
	private String Email;
	private int id;
	private int roomNum;
	
	public Client(String n, String eml, int id,int rm) {
		setName(n);
		setEmail(eml);
		this.setId(id);
		setRoomNum(rm);
	}
	
	public Client(String n, String eml,int id) {
		setName(n);
		setEmail(eml);
		this.setId(id);
		setRoomNum(-1);
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	
	public String toString() {
		return "ClentName:"+Name+"   Email:"+Email+"   ClientID:"+id;
		
	}
	
}
