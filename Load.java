
public class Load implements Places {

	  public void fire(){
			adb.ready();

	  }
	  
	  public boolean canFire(){
		  return adb.isReady();
	  }
		
		private static Load _instance = null;
		private static int count = 0;
		
		private Load(){
		//	System.out.println("Load is created for " + (++count));
		}
		
		public static Load getInstance(){
			if(_instance == null)
				return new Load();
			return _instance;
		}

}
