package Ships;

/**
 * 
 * @author Dennis
 *
 */

public class Corvette extends Ship{
		
	String name = "Corvette";
	
	public Corvette(int number){
		super((char)67, 3, false, number, false, 1, 0, 1, "Corvette");
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	
}
