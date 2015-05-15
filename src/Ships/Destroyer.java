package Ships;


/**
 * Subclass of Ship
 * 
 * @author Dennis
 * 
 */
public class Destroyer extends Ship {

	String name = "Zerstörer";
	
	public Destroyer(int number) {
		super("Z", 5, false, number, false, 3, 0, 3, "Zerstörer");
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
