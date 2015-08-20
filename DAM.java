
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public class DAM {

	private LinkedList<MemoryLocation>memLoc;
	private FileReader reader;
	private BufferedReader buffer;
	private File f;
	private static int count = 0;

	private static DAM _instance = null;
	
	public static DAM getInstance(){
		if(_instance == null)
			return new DAM();
		else
			return _instance;
	}
	
	public LinkedList<MemoryLocation> getMemLoc() {
		return memLoc;
	}

	public boolean isEmpty(){
		return memLoc.isEmpty();
	}
	
	public boolean isFull(){
		return memLoc.size() >= 8;
	}
	
	private DAM(){
//		System.out.println("DAM is initialized for " + (++count));
		setMemoryLocations();
	//	display();
	
	}
	
	public void setMemoryLocations(){
		int memoryIndex = -1, value = -1;
		memLoc = new LinkedList<MemoryLocation>();
		String str = null;
		try{
			f = new File("datamemory.txt");
			reader = new FileReader(f);
			buffer = new BufferedReader(reader);
			str = buffer.readLine();
			while( str!= null){
				String substr = str.substring(str.indexOf("<")+1, str.indexOf(">"));
				String splitString[] = substr.split(",");
				memoryIndex = Integer.parseInt(splitString[0]);
				value = Integer.parseInt(splitString[1]);
				memLoc.add(new MemoryLocation(memoryIndex, value, true));
				str = buffer.readLine();		
			}
			addMissing();
			Collections.sort(memLoc);
			buffer.close();
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("unable to find the file correctly");		
		} catch (IOException e) {
			System.out.println("IO exception, buffer reader is not properly working");
		}
	}

	private void addMissing() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 8; i++){
			int found = 0;
			for(MemoryLocation location : memLoc)
				if(location.getMemoryIndex()==i)
					found = 1;
			if(found == 0)
				memLoc.add(new MemoryLocation(i, 0, false));					
		}
	}

	public void displayLocations(){
		System.out.println("display memory");
		for(MemoryLocation mem : memLoc){
			if(mem.isInitialized())
				System.out.println(mem.toString());
		}
	}
	
	
	public MemoryLocation get(int loc){
		for(MemoryLocation mem : memLoc){
			if(mem.getMemoryIndex()==(loc))
				return mem;
		}
		return null;
	}
	
	public MemoryLocation get(MemoryLocation loc){
		for(MemoryLocation mem : memLoc){
			if(mem.getMemoryIndex() == loc.getMemoryIndex())
				return mem;
		}
		return null;
	}
	
	public boolean isMemoryLocationAvailable(MemoryLocation loc){
		MemoryLocation location = get(loc);
		if(location != null && location.isInitialized() )
			return true;
		return false;
	}
	
	public String toString() {
		String str = "DAM:  ";
		for(MemoryLocation m : memLoc){
			if(m.isInitialized())
				str += m.toString() + ", " ;
		}
		str = str.substring(0, str.length()-2);
		return str;
	}
	
	public void display(){
		System.out.println(toString());
	}


}
