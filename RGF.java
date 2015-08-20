
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class RGF {

	private ArrayList<Register>registers;
	private File f;
	private FileReader reader;
	private BufferedReader buffer;

	
	private static RGF _instance = null;
	private static int count = 0;
	
	public static RGF getInstance(){
		if( _instance == null){
			return new RGF();
		}
			
		else
			return _instance;
	}
	
	private RGF(){
	//	System.out.println("RGF is initializing for "+ (++count));
		setRegisters();
		//display();
	}
	
	
	public boolean isEmpty(){
		return registers.isEmpty();
	}
	
	public boolean isFull(){
		return registers.size()>=8;
	}
	
	public void setRegisters(){
		int  value = -1;
		String registerNo = null;
		registers = new ArrayList<Register>();
		String str = null;
		try{
			f = new File("registers.txt");
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
			addMissing();
			Collections.sort(registers);
			buffer.close();
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("unable to find the registers file correctly");		
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
	
	public ArrayList<Register> getRegisters() {
		return registers;
	}
	
	
	public Register get(Register reg){
		for(Register r: registers){
			if(r.getRegNo().equals(reg.getRegNo()))
				return r;
		}
		return null;
	}

	private  void addMissing(){
		for(int i = 0; i < 8; i++){
			String str = "R"+i;
			int found = 0;
			for(Register r : registers)
				if(r.getRegNo().equals(str))
					found = 1;
			if(found == 0)
				registers.add(new Register(str, 0));					
		}
	}
	
	public boolean isRegisterAvailable(Register reg){
		Register find = get(reg);
		if(find != null && find.isIntialized())
			return true;
		else
			return false;
	}
	
	public void delete(Register reg){
		registers.remove(reg);
	}
	
	public String toString() {
		String str = "RGF:  ";
		for(Register register : registers){
			if(register.isIntialized())
				str += register.toString()+", ";
		}
		str = str.substring(0, str.length()-2);
		return str;
	}
	
	public void display(){
		System.out.println(toString());
	}

	public void updateRegister(Register register) {
		get(register).update(register.getValue());
		
	}
	
}
