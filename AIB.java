
import java.util.LinkedList;
import java.util.Queue;

/*Add condition that AIB wont work if size is > 8 */

public class AIB implements Places{
	private static int count = 0;
	private Queue<Instruction> instructionQueue;

	public Queue<Instruction> getInstructionQueue() {
		return instructionQueue;
	}
	
	private static AIB _instance = null;
	
	public static AIB getInstance(){
		return _instance == null?new AIB():_instance;
	}
	

	public void setInstructionQueue(Queue<Instruction> instructionQueue) {
		this.instructionQueue = instructionQueue;
		//fire();
	}

	private AIB() {
		//System.out.println("AIB is initialized for "+(++count));
		instructionQueue = new LinkedList<Instruction>();
	}
	
	public boolean isEmpty(){
		return instructionQueue.isEmpty();
	}
	
	public boolean isFull(){
		return instructionQueue.size() >= 8;
	}
	
	public void addInstruction(Instruction instruction){
		instructionQueue.add(instruction);
	}
	
	public Instruction getTop(){
		return instructionQueue.peek();
	}
	
	public Instruction remove(){
		return instructionQueue.poll();
	}

	public boolean isReady(){
		return (isEmpty()?false:true);
	}
	
	public void ready() {
		if(isReady()){
			Instruction instruction = remove();
			instruction.compute();
			checkoutoREB(instruction.getDestination());
		}else{
			System.out.println("Cannot be fired to REB from AIB");
		}
		
	}

	public void checkoutoREB(Register register) {
		reb.addInstruction(register);
	}
	
	public String toString() {
		String str = "AIB:  ";
		for(Instruction instruction : instructionQueue){
			str += instruction.display(this) + ", " ;
		}
		str = str.substring(0, str.length()-2);
		return str;
	}
	
	public void display(){
		System.out.println(toString());
	}

}
