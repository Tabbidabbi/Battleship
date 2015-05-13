package Ships;


/**
 * Subclass of Ship
 * 
 * @author Dennis
 * 
 */
public class Destroyer extends Ship {

	String name = "Destroyer";
	
	public Destroyer(int number) {
		super((char)68, 5, false, number, false, 3, 0, 3, "Destroyer");
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
