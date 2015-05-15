package Ships;


/**
 * Subclass of Ship
 * 
 * @author Dennis
 * 
 */
public class Destroyer extends Ship {

	String name = "ZerstГ¶rer";
	
	//Zerstörer positionieren
	
	public Destroyer(int number) {
		super("Z", 5, false, number, false, 3, 0, 3, "ZerstГ¶rer");
		// TODO Auto-generated constructor stub
	}

	//Getter und Setter Methoden
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
