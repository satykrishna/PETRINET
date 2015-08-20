
public class MemoryLocation implements Comparable<MemoryLocation>{

	private int memoryIndex;
	private int value;
	private boolean isInitialized;
	
	public boolean isInitialized() {
		return isInitialized;
	}


	public void setInitialized(boolean isInitialized) {
		this.isInitialized = isInitialized;
	}


	public MemoryLocation(int memoryIndex, int value, boolean isInitialized) {
		super();
		this.memoryIndex = memoryIndex;
		this.value = value;
		this.isInitialized = isInitialized;
	}

	
	public int getMemoryIndex() {
		return memoryIndex;
	}

	public void setMemoryIndex(int memoryIndex) {
		this.memoryIndex = memoryIndex;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}


	public MemoryLocation(int memoryIndex, int value){
		this.memoryIndex = memoryIndex;
		this.value = value;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "<"+memoryIndex+", "+value+">"  ;
	}


	public int compareTo(MemoryLocation loc) {
		if(this.memoryIndex <= loc.memoryIndex)
			return 0;
		return 1;
	
	}
	

	
	
}
