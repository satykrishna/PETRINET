
import java.util.LinkedList;
import java.util.Queue;

public class REB implements Places{

	private static REB _instance = null;
	private static int count  = 0;
	public static REB getInstance(){
		if(_instance == null)
			return new REB();
		return _instance;
	}
	
	private Queue<Register>registerBuffer;

	private REB() {
		//System.out.println("REB is initialized for "+ (++count));
		registerBuffer = new LinkedList<Register>();
	}

	public Queue<Register> getRegisterBuffer() {
		return registerBuffer;
	}

	public boolean isEmpty(){
		return registerBuffer.isEmpty();
	}

	public boolean isFull(){
		return registerBuffer.size() >=8;
	}

	public Register getTop(){
		return registerBuffer.peek();
	}

	public Register remove(){
		return registerBuffer.poll();
	}

	public void setRegisterBuffer(Queue<Register> registerBuffer) {
		this.registerBuffer = registerBuffer;
	}

	public void addInstruction(Register register){
	//	System.out.println("ADDED IN REB = "+register.toString() );
		registerBuffer.add(register);
//		System.out.println("LIB BEFORE FIRE");
//		display();
//		fire();
//		System.out.println("LIB AFTER FIRE");
//		fire();
	//	display();
		
	}

	public String toString() {
		String str = "REB:  ";
		for(Register register : registerBuffer){
			str += register.toString()+", ";
		}
		str = str.substring(0, str.length()-2);
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
			Register register = remove();
			checkoutoRGF(register);
		}
	}

	public void checkoutoRGF(Register register) {
		rgf.updateRegister(register);
		
	}
}
