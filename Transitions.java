
public interface Transitions {

	Addr addr = Addr.getInstance();
	Asu asu = Asu.getInstance();
	Decode decode = Decode.getInstance();
	Issue issue = Issue.getInstance();
	Read read = Read.getInstance();
	Write write = Write.getInstance();
	Load load = Load.getInstance();
}
