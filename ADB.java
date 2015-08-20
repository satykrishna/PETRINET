
import java.util.LinkedList;
import java.util.Queue;

public class ADB implements Places{

	private static ADB _instance = null;
	private static int count = 0;
	
	public static ADB getInstance(){
		if(_instance == null)
			return new ADB();
		return _instance;
	}
	
	private Queue<Register>registerQueue;

	public Queue<Register> getRegisterQueue() {
		return registerQueue;
	}

	public void setRegisterQueue(Queue<Register> registerQueue) {
		this.registerQueue = registerQueue;
	}
	
	private ADB(){
		registerQueue = new LinkedList<Register>();
	//	System.out.println("ADB is initialized" + (++count));
	}
	
	public void addAddresstoQueue(Register register){
//		System.out.println("Register is added in ADB " + register.toString());
		registerQueue.add(register);
		//System.out.println("ADB:BEFORE FIRE");
	//	display();
		//fire();
	//	System.out.println("ADB: AFTER FIRE");
		//display();
	}
	
	public Register getTop(){
		return registerQueue.peek();
	}
	
	public Register remove(){
		return registerQueue.poll();
	}
	
	public boolean isEmpty(){
		return registerQueue.isEmpty();
	}
	
	public boolean isFull(){
		return registerQueue.size() >= 8? true:false;
	}

	public boolean isReady(){
		return isEmpty()?false:true;
	}
	
	public void ready() {
		if(isReady()){
			Register register = remove();
			register.loadFromMemory(dam);
			checkoutoREB(register);
		}	
	}

	public void checkoutoREB(Register register) {
		reb.addInstruction(register);
	}
	
	public String toString() {
		String str = "ADB:  ";
		for(Register register : registerQueue){
			str += register.toString() + ", " ;
		}
		str = str.substring(0, str.length()-2);
		return str;
	}
	
	public void display(){
		System.out.println(toString());
	}

	
	
}

