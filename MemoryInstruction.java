
public class MemoryInstruction extends Instruction {

	int value;

	public MemoryInstruction(String opcode, Register destination, Register source, int value, String token) {
		super(opcode, destination, source, false, token);
		this.value = value;
	}

	public String toString() {
		return  "< "+getOpcode() + ", " + destination.getRegNo() +", "+ source1.getRegNo()+", "+value+" >  "+isReady; 
	};

	public String toString(Object o) {
		if(o instanceof INM)
			return  "< "+getOpcode() + ", " + destination.getRegNo() +", "+ source1.getRegNo()+", "+value+" >"; 
		if(o instanceof LIB)
			return "< "+getOpcode() + ", " + destination.getRegNo() +", "+ source1.getValue()+", "+value+" >"; 
        if(o instanceof INB)
			return  "< "+getOpcode() + ", " + destination.getRegNo() +", "+ source1.getValue()+", "+value+" > "; 

		return "CONSTRUCTION";
	}

	@Override
	public String display(Object o) {
		return toString(o);
	}

	public void setReady(RGF rgf) {
		super.setReady(rgf);
	}
	public void setRegisters(RGF rgf){
		super.setRegisters(rgf);
	}

	@Override
	public void compute() {
		destination.setValue(value+source1.getValue());
		destination.setIntialized(false);
	}


   @Override
	public void display() {
		// TODO Auto-generated method stub
	   System.out.println(toString());

	}

}
