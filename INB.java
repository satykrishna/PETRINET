
import java.util.LinkedList;
import java.util.Queue;

public class INB implements  Places{
	
	private Queue<Instruction> instQueue;
	private static INB _instance = null;
	private static int count = 0;
	
	public static INB getInstance(){
		if(_instance == null)
			return new INB();
		else
			return _instance;
	}
	
	public Queue<Instruction> getInstQueue() {
		return instQueue;
	}

	public void setInstQueue(Queue<Instruction> instQueue) {
		this.instQueue = instQueue;
	}

	private INB(){
		instQueue = new LinkedList<Instruction>();
		//System.out.println("INB is initialized for " + (++count));
	}
	
	public boolean isEmpty(){
		return instQueue.isEmpty();
	}
	
	public boolean isFull(){
		return instQueue.size() >= 8;
	}
	
	public void addInstruction(Instruction i){
	//	System.out.println("ADDED in INB = " +i.toString());
		instQueue.add(i);
		
		//System.out.println("INB: BEFORE FIRE");
		//System.out.println(toString());
		//fire();
		//System.out.println("INB: AFTER FIRE");
		//toString();
	}
	
	public void displayInstructionSet(){
		System.out.println("Instruction Queue in INB");
		for(Instruction instruction: instQueue)
			instruction.display(this);		
	}
	
	public Instruction getTop(){
		return instQueue.peek();
	}
	
	public Instruction remove(){
		return instQueue.poll();
	}

	public boolean isReady(){
		if(isEmpty())
			return false;
		Instruction instruction = getTop();
		if(instruction == null)
			return false;

		if(instruction instanceof ArthimeticInstruction){
			if(aib.isFull())
				return false;
		}else if(instruction instanceof MemoryInstruction){
			if(lib.isFull())
				return false;
		}
		return true;
	}

	public void ready() {
		if(!isReady()){
			System.out.println("Cannot be fired from INB to either AIB/LIB....!!!! due to AIB/LIB is full");
		}
		Instruction instruction = getTop();
		if(instruction instanceof MemoryInstruction){
			instruction = remove();
			checkoutoLIB(instruction);
		}else if( instruction instanceof ArthimeticInstruction){
			instruction = remove();
			checkoutoAIB(instruction);
		}else{
			System.out.println("Not a proper instruction in INB to fire to AIB/LIB");
		}
	}

	public void checkoutoAIB(Instruction instruction) {
		aib.addInstruction(instruction);
	}

	public void checkoutoLIB(Instruction instruction) {
		lib.addInstruction(instruction);
	}
	
	@Override
	public String toString() {
		String str = "INB:  ";
		for(Instruction instruction : instQueue){
			str += instruction.display(this) + ", " ;
		}
		str = str.substring(0, str.length()-2);
		return str;
	}
	
	public void display(){
		System.out.println(toString());
	}

}
	