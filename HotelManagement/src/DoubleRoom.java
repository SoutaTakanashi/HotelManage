
public class DoubleRoom extends Room{
	
	
	public DoubleRoom (String w,int num,String sta,int id){
		super(w,num,sta,id);//inheritages its parent's constructor.	
		this.setCapacity(2);
		this.setCleaningDuration(60);//an hour to be cleaned
	}
}
