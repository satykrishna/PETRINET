
public class Read implements Places{

	public void fire(){
		reb.ready();
	}
	
	public boolean canFire(){
		return reb.isReady();
	}
	
	
	private static Read _instance = null;
	private static int count = 0;
	
	private Read(){
		//System.out.println("Read is created for " + (++count));
	}
	
	public static Read getInstance(){
		if(_instance == null)
			return new Read();
		return _instance;
	}

	
}
