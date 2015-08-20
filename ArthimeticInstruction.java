
public class ArthimeticInstruction extends Instruction{


	Register source2;


	public Register getSource2() {
		return source2;
	}

	public void setSource2(Register source2) {
		this.source2 = source2;
	}


	public ArthimeticInstruction(String opcode, Register destination, Register source1, Register source2, String token){
		super(opcode, destination, source1, false, token);
		this.destination = destination;
		this.source1 = source1;
		this.source2 = source2;
	}


	public String toString(Object o) {
		if(o instanceof INM )
			return  "< " +getOpcode() + ", " + destination.getRegNo() +", "+ source1.getRegNo() + ", "+ source2.getRegNo() + ">";
		if(o instanceof AIB)
			return "< " +getOpcode() + ", " + destination.getRegNo() +", "+ source1.getValue() + ", "+ source2.getValue() +" >";
		if(o instanceof INB)
			return  "< " +getOpcode() + ", " + destination.getRegNo() +", "+ source1.getValue() + ", "+ source2.getValue() + ">";

		return " ";

	}

	@Override
	public String display(Object o) {
		// TODO Auto-generated method stub
		return toString(o);
	}

	public void setReady(RGF rgf) {
		super.setReady(rgf);
		this.isReady  = this.isReady && rgf.isRegisterAvailable(source2);
	}

	public void setRegisters(RGF rgf){
		super.setRegisters(rgf);
		this.source2.setValue(rgf.get(source2).getValue());
		this.source2.setIntialized(rgf.get(source2).isIntialized());
	}

	@Override
	public void compute() {
		if(opcode.equals("ADD"))
			destination.setValue(source1.getValue()+source2.getValue());
		else
			destination.setValue(source1.getValue()-source2.getValue());

		destination.setIntialized(true);
	}

	@Override
	public String toString() {
		return  "< " +getOpcode() + ", " + destination.getRegNo() +", "+ source1.getRegNo() + ", "+ source2.getRegNo() +" "+isReady;
	}

	@Override
	public void display() {
		System.out.println(toString());
	}




}
