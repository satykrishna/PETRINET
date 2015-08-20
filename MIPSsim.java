
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;



public class MIPSsim implements Places, Transitions {


	private File file;

	private  PrintWriter out;

	public PrintWriter getOut() {
		return out;
	}
	
	public MIPSsim(){
		file  = new File("simulation.txt");
		try {
			out = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			System.out.println("Unable to write the output properly to simulation.txt");
		}
	}

	public void output(int step){
		out.println("STEP:" +step);
		out.println(inm.toString());
		out.println(inb.toString());
		out.println(aib.toString());
		out.println(lib.toString());
		out.println(adb.toString());
		out.println(reb.toString());
		out.println(rgf.toString());
		out.println(dam.toString());
		out.println();
		out.println();
	}
	
	public void closeWriter(){
		out.close();
	}

	
	public void display(int step){
		System.out.println("STEP:" + step);
		inm.display();
		inb.display();
		aib.display();
		lib.display();
		adb.display();
		reb.display();
		rgf.display();
		dam.display();
		System.out.println();
		System.out.println();
	}
	
	public void simulate(){
	    display(0);
		output(0);
		int step = 1;
		while(true){
			boolean decodefire = decode.canFire();
			boolean issuefire = issue.canFire();
			boolean addrfire = addr.canFire();
			boolean loadfire = load.canFire();
			boolean writefire = write.canFire();
			boolean asufire = asu.canFire();

			if(!decodefire && !issuefire && !addrfire && !loadfire && !writefire && !asufire)
				break;
			if(decodefire)
				decode.fire();
			if(issuefire)
				issue.fire();
			if(addrfire)
				addr.fire();
			if(loadfire)
				load.fire();
			if(writefire)
				write.fire();
			if(asufire)
				asu.fire();
			
		    display(step);
			output(step);
			step++;
		}
		
		closeWriter();
		System.out.println("DONE!!!");



	}

	public static void main(String[] args) {

		MIPSsim mips = new MIPSsim();
		mips.simulate();
        


	}


}
