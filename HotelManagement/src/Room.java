//Thanks for the advice from Mrs.Kostkova.
//Now I separate every class into different files.
//And properities should be private rather than public.
public class Room extends HotelManagement{
	private String Wing;
	private int roomNum;
	private String Status;
	private int IDUnique;
	private int capacity;
	private boolean clean;
	private int cleaningDuration;
	protected Room(String w,int num,String sta,int id) {
		this.setWing(w);
		this.setRoomNum(num);
		this.setStatus(sta);
		this.setIDUnique(id);
		this.setClean(true);
	}


	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getWing() {
		return Wing;
	}
	public void setWing(String wing) {
		Wing = wing;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public int getIDUnique() {
		return IDUnique;
	}
	public void setIDUnique(int iDUnique) {
		IDUnique = iDUnique;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public boolean isClean() {
		return clean;
	}
	public void setClean(boolean clean) {
		this.clean = clean;
	}
	public int getCleaningDuration() {
		return cleaningDuration;
	}
	public void setCleaningDuration(int cleaningDuration) {
		this.cleaningDuration = cleaningDuration;
	}
	public String toString() {//To print all of the information.
		String s="Wing:"+Wing+"   RoomNum:"+roomNum+"   Status:"+Status+"  IDUnique:"+IDUnique;
		if(capacity==4)return s+"   RoomType:Family";
		else return s+"   RoomType:Double";
	}
}
