package Ships;

import java.io.Serializable;


/**
 * Subclass of Ship
 * 
 * @author Dennis
 * 
 */
public class Destroyer extends Ship implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2651661978237002757L;
	String name = "Zerstoerer";
	
	//Zerstörer positionieren
	
	public Destroyer(int number) {
		super("Z", 5, false, number, false, 3, 0, 3, "Zerstoerer");
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
