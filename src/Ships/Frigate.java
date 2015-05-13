package Ships;

/**
 * 
 * @author Dennis
 *
 */

public class Frigate extends Ship {
	
	String name = "Frigate";
	
	public Frigate(int number){
		super((char)70, 4, false, number, false, 2, 0, 2, "Frigate");
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}