
public class Addr implements Places	{
	
	private static Addr _instance = null;
	private static int counter = 0;
	
	private Addr(){
	//	System.out.println("ADDR count = " + (++counter));
	}
	
	public static Addr getInstance(){
		if(_instance == null)
			return new Addr();
		return _instance;
	}

	  public void fire(){
			lib.ready();

	  }
	  
	  public boolean canFire(){
		  return lib.isReady();
	  }
}
