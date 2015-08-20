
public class Issue implements Places{

	
	
	private static Issue _instance = null;
	private static int count = 0;
	
	private Issue(){
		//System.out.println("Issue1 is created for " + (++count));
	}
	
	public static Issue getInstance(){
		if(_instance == null)
			return new Issue();
		return _instance;
	}

	public boolean canFire(){
		
		return inb.isReady();
	}
	
	public void fire(){
		inb.ready();
	}
	

}
