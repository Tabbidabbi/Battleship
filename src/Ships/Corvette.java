package Ships;

import java.io.Serializable;

/**
 * 
 * @author Dennis
 *
 */

public class Corvette extends Ship implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -6931003438816274399L;
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
