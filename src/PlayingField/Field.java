package PlayingField;

import IO.IO;

/**
 * 
 * @author Dennis
 *
 */
public class Field {
	
	//Flags
	private boolean isShot;  //O
	private boolean isWater;  //~
	private boolean isHit;   //X
	private boolean hasShip;
	private boolean active;
	// Im Status stehen die Symbole
	private char status;
	
	/**
	 * Konstruktor
	 */
	public Field(){
		
		this.isShot = false;
		this.isWater = true;
		this.isHit = false;
		this.hasShip = false;
                this.active = true;
		this.status = 126;
	}

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return this.active;
    }
	
        
	public boolean getIsShot(){
		return this.isShot;
	}	
	
	public void setIsShot(){
		this.isShot = true;
		this.isWater = false;
		this.status = 79;
		if(this.hasShip == true){
			IO.println("Du hast ein Schiff getroffen");
		}
		else{
			IO.println("Du hast auf Wasser geschossen");
		}
	}
	
	public boolean getIsWater() {
		return isWater;
	}

	public void setWater(boolean isWater) {
		this.isWater = true;
	}

	public boolean getIsHit() {
		return isHit;
	}

	public void setHit(boolean isHit) {
		this.isHit = true;
		this.isShot = true;
		this.status = 88;
	}
	
	public boolean isHasShip() {
		return hasShip;
	}

	public void setHasShip(boolean hasShip) {
		this.hasShip = true;
	}

	public char getStatus(){
		return this.status;
	}

	/**
	 * 
	 * @param status �bergabe des Status, welches �bernommen werden sollen.
	 */
	public void setStatus(char status){
		this.status = status;
	}
	
	/**
	 * Methode zum Ausgeben des aktuellen Status
	 */
	public void print(){
		IO.print(this.status + "\t");
	}
	
	
}
