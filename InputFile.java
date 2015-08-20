import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class InputFile {

	private Queue<Instruction>instructionSet;
	private ArrayList<Register>registers;
	private Queue<MemoryLocation>memLoc;
	private FileReader reader;
	private BufferedReader buffer;
	private File f;

	
	public Queue<Instruction> getInstructionSet() {
		return instructionSet;
	}

	public ArrayList<Register> getRegisters() {
		return registers;
	}


	public Queue<MemoryLocation> getMemLoc() {
		return memLoc;
	}


	public InputFile() throws IOException{
		setMemoryLocations();
		displayLocations();
		setRegisters();
		displayRegisters();
		setInstructionset();
	}
	
	
	public void setMemoryLocations(){
		int memoryIndex = -1, value = -1;
		memLoc = new LinkedList<MemoryLocation>();
		String str = null;
		try{
			f = new File("C:\\Users\\Satya\\My_programs\\javacode\\PetriNet\\src\\io\\datamemory.txt");
			reader = new FileReader(f);
			buffer = new BufferedReader(reader);
			str = buffer.readLine();
			while( str!= null){
				String substr = str.substring(str.indexOf("<")+1, str.indexOf(">"));
				String splitString[] = substr.split(",");
				memoryIndex = Integer.parseInt(splitString[0]);
				value = Integer.parseInt(splitString[1]);
				memLoc.add(new MemoryLocation(memoryIndex, value));
				str = buffer.readLine();		
			}
			//MemoryLocation.addMissing(memLoc);
			buffer.close();
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("unable to find the file correctly");		
		} catch (IOException e) {
			System.out.println("IO exception, buffer reader is not properly working");
		}
	}

	public void displayLocations(){
		System.out.println("display memory");
		for(MemoryLocation mem : memLoc){
			if(mem.isInitialized())
				System.out.println(mem.toString());
		}
	}

	public void setRegisters(){
		int  value = -1;
		String registerNo = null;
		registers = new ArrayList<Register>();
		String str = null;
		try{
			f = new File("C:\\Users\\Satya\\My_programs\\javacode\\PetriNet\\src\\io\\registers.txt");
			reader = new FileReader(f);
			buffer = new BufferedReader(reader);
			str = buffer.readLine();
			while( str!= null){
				String substr = str.substring(str.indexOf("<")+1, str.indexOf(">"));
				String splitString[] = substr.split(",");
				registerNo = splitString[0];
				value = Integer.parseInt(splitString[1]);
				registers.add(new Register(registerNo, value, true));
				str = buffer.readLine();		
			}
		//	Register.addMissing(registers);

			buffer.close();
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("unable to find the file correctly");		
		} catch (IOException e) {
			System.out.println("IO exception, buffer reader is not properly working");
		}	
	}
	
	public void displayRegisters(){
		System.out.println("registers");
		for(Register r : registers){
			System.out.println(r.toString());
		}
	}

	
	@SuppressWarnings({ "unused", "null" })
	public void setInstructionset(){
		int  value = -1;
		String opcode = null, destReg = null, srcReg1 = null, srcReg2 = null;
		instructionSet = new LinkedList<Instruction>();
		String str = null;
		try{
			f = new File("C:\\Users\\Satya\\My_programs\\javacode\\PetriNet\\src\\io\\instructions.txt");
			reader = new FileReader(f);
			buffer = new BufferedReader(reader);
			str = buffer.readLine();
			while( str!= null){
				String substr = str.substring(str.indexOf("<")+1, str.indexOf(">"));
				String splitString[] = substr.split(",");
				opcode = splitString[0];
				destReg = splitString[1];
				srcReg1 = splitString[2];
				srcReg2 = splitString[3];
			//	Register destRegister = Register.findRegister(registers, destReg);
				//Register source1 = Register.findRegister(registers, srcReg1);
				Instruction instruction = null;
				if(opcode.equals("ADD") || opcode.equals("SUB")){
					//Register source2 = Register.findRegister(registers, srcReg2);
					 //instruction = new ArthimeticInstruction(opcode, destRegister, source1, source2);
					 System.out.println(instruction.toString());
				}else{
					value = Integer.parseInt(srcReg2);
				//	instruction = new MemoryInstruction(opcode, destRegister, source1, value);
					System.out.println(instruction.toString());
				}
			instructionSet.add(instruction);
				str = buffer.readLine();		
			}
			buffer.close();
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("unable to find the file correctly");		
		} catch (IOException e) {
			System.out.println("IO exception, buffer reader is not properly working");
		}	
	}
	
	
	public void displayInstructionSet(){
		System.out.println("Instruction Set");
		for(Instruction instruction: instructionSet)
			instruction.display();		
	}
	

	public static void main(String[] args) {
		try {
			@SuppressWarnings("unused")
			InputFile ip = new InputFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
