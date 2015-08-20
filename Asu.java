
public class Asu implements Places{
	
	private static Asu _instance = null;
	private static int count = 0;
	
	private Asu(){
		//System.out.println("ASU is created for " + (++count));
	}
	
	public static Asu getInstance(){
		if(_instance == null)
			return new Asu();
		return _instance;
	}

	  public void fire(){
			aib.ready();

	  }
	  
	  public boolean canFire(){
		  return aib.isReady();
	  }
   
      
	
	
}
