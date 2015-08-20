
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/*
 * Following need to be implemented
 * 1. take care of zdINM size must be less than 8 and if there are more than 8 instructions
 * we need to buffer at a particular instance.
 */

public class INM implements Places{

	private Queue<Instruction>instructionSet;
	private File f;
	private FileReader reader;
	private BufferedReader buffer;
	private static INM _instance;
	private static int count = 0;
	private Queue<Instruction> instBuffer;
	private final int size = 8;

	public static INM getInstance(){
		if(_instance == null)
			return new INM();
		return _instance;
	}

	public Instruction getTop(){
		return instructionSet.isEmpty()?null:instructionSet.peek();
	}

	public Instruction removeTop(){
		return instructionSet.isEmpty()?null:instructionSet.poll();
	}

	public Queue<Instruction> getInstructionSet() {
		return instructionSet;
	}

	public boolean isEmpty(){
		return isIBEmpty() && isIQEmpty();
	}

	public boolean isFull(){
		return instructionSet.size() >= 8;
	}
	
	public boolean isIBEmpty(){
		return instBuffer.isEmpty();
	}
	
	public boolean isIQEmpty(){
		return instructionSet.isEmpty();
	}
	
	public boolean isIQFull(){
		return instructionSet.size() >=8;
	}

	public void addInstructiontoQueue(){
		if(isIBEmpty())
			return;
		instructionSet.offer(instBuffer.poll());
	}


	private INM(){
		//System.out.println("INM is initialized for " +(++count) );
		setInstructionset();
	//	display();
	}


	public void setInstructionset(){
		int  value = -1;
		String opcode = null, destReg = null, srcReg1 = null, srcReg2 = null;
		instructionSet = new LinkedList<Instruction>();
		instBuffer = new LinkedList<Instruction>();
		String str = null;
		try{
			f = new File("instructions.txt");
			reader = new FileReader(f);
			buffer = new BufferedReader(reader);
			str = buffer.readLine();
			int index = 0;
			while( str!= null){
				String substr = str.substring(str.indexOf("<")+1, str.indexOf(">"));
				String splitString[] = substr.split(",");
				opcode = splitString[0];
				destReg = splitString[1];
				srcReg1 = splitString[2];
				srcReg2 = splitString[3];
				Register destRegister = new Register(destReg);
				Register source1 = new Register(srcReg1);
				String token = "I"+index;
				Instruction instruction = null;
				if(opcode.equals("ADD") || opcode.equals("SUB")){
					Register source2 = new Register(srcReg2);
					instruction = new ArthimeticInstruction(opcode, destRegister, source1, source2, token);
				}else{
					value = Integer.parseInt(srcReg2);
					instruction = new MemoryInstruction(opcode, destRegister, source1, value, token);
				}
				instBuffer.add( instruction);
				str = buffer.readLine();		
				index++;
			}
			for(int i = 0; i < size; i++){
				instructionSet.offer(instBuffer.poll());
				if(isIBEmpty())
					break;
			}
			buffer.close();
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("unable to find the instructions file correctly");		
		} catch (IOException e) {
			System.out.println("IO exception, buffer reader is not properly working");
		}	
	}


	public void displayInstructionSet(){
		System.out.println("Instruction Set");
		for(Instruction instruction: instructionSet)
			instruction.display(this);		
	}

	public boolean isReady() { /*if(isEmpty() || inb.isFull())*/
		if(isEmpty() || inb.isFull())
			return false;
		Instruction instruction = getTop();
		if(instruction == null)
			return false;
		instruction.setReady(rgf);
		if(instruction.isReady()){
			instruction.setRegisters(rgf);
			return true;
		}
		return false;

	}

	public void checkoutoINB(Instruction instruction) {
		inb.addInstruction(instruction);
	}

	public void ready() {
		Instruction instruction = null;
		if(isReady()){
			instruction = removeTop();
			checkoutoINB(instruction);
			addInstructiontoQueue();
		}else{
			System.out.println("Cannot be fired from INM to INB");
		}

	}

	public String toString() {
		String str = "INM:  ";
		for(Instruction instruction : instructionSet){
			str += instruction.display(this) + ", " ;
		}
		str = str.substring(0, str.length()-2);
		return str;
	}

	public void display(){
		System.out.println(toString());
	}

}
