package Ships;

/**
 * 
 * @author Dennis
 *
 */

public class Corvette extends Ship{
		
	String name = "Korvette";
	
	//Korvette positioniert 
	
	public Corvette(int number){
		super("C", 3, false, number, false, 1, 0, 1, "Korvette");
		
	}

	//Getter und Setter Methoden
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	
}
