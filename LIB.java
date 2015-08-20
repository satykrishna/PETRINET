
import java.util.LinkedList;
import java.util.Queue;

public class LIB implements Places{

	private static LIB _instance = null;
	private static int count = 0;
	
	public static LIB getInstance(){
		if(_instance == null)
			return new LIB();
		return _instance;
	}
	
	private Queue<Instruction> instructionQueue;

	public Queue<Instruction> getInstructionQueue() {
		return instructionQueue;
	}

	public boolean isEmpty(){
		return instructionQueue.isEmpty();
	}
	
	public boolean isFull(){
		return instructionQueue.size() >=8;
	}
	
	public void setInstructionQueue(Queue<Instruction> instructionQueue) {
		this.instructionQueue = instructionQueue;
	}

	private LIB() {
		//System.out.println("LIB is initialized for "+(++count));
		instructionQueue = new LinkedList<Instruction>();
	}
	
	public void addInstruction(Instruction instruction){
	//	System.out.println("Added in LIB = " + instruction.toString());
		instructionQueue.add(instruction);
//		System.out.println("LIB: BEFORE FIRE");
//		display();
//		fire();
//		System.out.println("LIB: AFTER FIRE");
//		display();
	//	display();
	}
	
	public Instruction getTop(){
		return instructionQueue.peek();
	}
	
	public Instruction remove(){
		return instructionQueue.poll();
	}
	
	public String toString() {
		String str = "LIB:  ";
		for(Instruction instruction : instructionQueue){
			str += instruction.display(this) + " " ;
		}
		return str;
	}
	
	public void display(){
		System.out.println(toString());
	}

	public boolean isReady(){
		return isEmpty()?false:true;
	}
	
	public void ready() {
		if(isReady()){
			Instruction instruction = remove();
			instruction.compute();
			checkoutoADB(instruction.getDestination());
		}
	}

	public void checkoutoADB(Register register) {
		adb.addAddresstoQueue(register);
		
	}
	
	

}
