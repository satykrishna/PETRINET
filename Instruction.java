
public abstract class Instruction {

	protected Register destination;
	protected Register source1;
 	protected String opcode;
	protected boolean isReady;
	protected String token;



	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}


 	public Register getDestination() {
		return destination;
	}

	public void setDestination(Register destination) {
		this.destination = destination;
	}

	public Register getSource1() {
		return source1;
	}

	public void setSource1(Register source1) {
		this.source1 = source1;
	}


	public Instruction(){
 		System.out.println("setting opcode");
 	}
 	
	public Instruction(String opcode, Register destination, Register source1, boolean isReady, String token){
		this.opcode = opcode;
		this.destination = destination;
		this.source1 = source1;
		this.isReady = isReady;
		this.token = token;
		
	}
	
 	public String getOpcode() {
		return opcode;
	}

	public void setOpcode(String opcode, Register destination, Register source1) {
		this.opcode = opcode;
		this.destination = destination;
		this.source1 = source1;
	}

	
	public abstract String display(Object o);
	

 	public boolean isReady() {
		return isReady;
	}

	public void setReady(RGF rgf) {
		this.isReady = rgf.isRegisterAvailable(source1);
	}
	
	public void setRegisters(RGF rgf){
		this.source1.setValue(rgf.get(source1).getValue());
		this.source1.setIntialized(rgf.get(source1).isIntialized());
	}
	
	public abstract void compute();
	
	public abstract void display();
	
}
