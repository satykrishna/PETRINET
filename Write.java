
public class Write implements Places{

	public void fire(){
		reb.ready();
	}
	
	public boolean canFire(){
		return reb.isReady();
	}
	
	
	private static Write _instance = null;
	private static int count = 0;
	
	private Write(){
	//	System.out.println("Write is created for " + (++count));
	}
	
	public static Write getInstance(){
		if(_instance == null)
			return new Write();
		return _instance;
	}

}
