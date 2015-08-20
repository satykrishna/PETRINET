


public class Register implements Comparable<Register> {

  private int value;
  private String regNo;
  private boolean isIntialized;
	
   public boolean isIntialized() {
	return isIntialized;
}

public void setIntialized(boolean isIntialized) {
	this.isIntialized = isIntialized;
}

public Register(String regNo){
	   this.regNo = regNo;
   }
  
	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	
	public Register(String regNo, int value){
		this.regNo = regNo;
		this.value = value;
	}
	
	public Register(String regNo, int value, boolean isIntialized){
		this.regNo  = regNo;
		this.value = value;
		this.isIntialized = isIntialized;
	}
	
	public Register() {
		// TODO Auto-generated constructor stub
	}

	public void loadFromMemory(DAM dam){
		int memLoc = this.value;
		MemoryLocation location = dam.get(memLoc);
		value = location.getValue();
		isIntialized = true;
		
	}
	
	public void update(int value){
		this.value = value;
		isIntialized = true;
	}
	
	@Override
		public String toString() {
			return "< " + regNo + ", " + value + ">";
		
		}

	public int compareTo(Register reg) {
		return this.regNo.compareTo(reg.getRegNo());
	}
	
}
