package Ships;

/**
 * 
 * @author Dennis
 *
 */

public class Corvette extends Ship{
		
	String name = "Korvette";
	
	public Corvette(int number){
		super("C", 3, false, number, false, 1, 0, 1, "Korvette");
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	
}
