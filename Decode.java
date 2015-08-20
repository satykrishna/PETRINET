
public class Decode implements Places {


	private static Decode _instance = null;
	private static int count = 0;
	
	private Decode(){
//		System.out.println("Decode is created for " + (++count));
	}
	
	public static Decode getInstance(){
		if(_instance == null)
			return new Decode();
		return _instance;
	}

	
	public boolean canFire(){
		if(inm.isEmpty())
			return false;
		if(inm.isReady())
			return true;
		return false;
	}

	public void fire(){
		inm.ready();
	}


}
