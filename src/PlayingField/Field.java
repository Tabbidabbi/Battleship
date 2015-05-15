package PlayingField;

import IO.IO;

/**
 * 
 * @author Dennis
 *
 */
public class Field {
	
	//Flags
	private boolean isShot;  //O char 79
	private boolean isWater;  //~
	private boolean isHit;   //X char 88
	private boolean hasShip;
	private boolean active;
	// Im Status stehen die Symbole
	private String status;
        private String fieldnumber;
	
        
        /**
	 * Konstruktor
	 */
    
	public Field(String fieldNumber){
		this.fieldnumber = fieldNumber;
		this.isShot = false;
		this.isWater = true;
		this.isHit = false;
		this.hasShip = false;
                this.active = true;
		this.status = "~";
	}
        public Field() {
		this.isShot = false;
		this.isWater = true;
		this.isHit = false;
		this.hasShip = false;
                this.active = true;
		this.status = "~";
        }
	
	public boolean isActive() {
        return this.active;
    }

	//Getter Methoden deffinieren
	
	public boolean getIsShot(){
		return this.isShot;
	}
	
	public boolean getIsWater() {
		return isWater;
	}
	
	public boolean getIsHit() {
		return isHit;
	}
	
	public boolean getHasShip() {
		return hasShip;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	//Setter Methoden deffinieren
	
    public void setActive(boolean active) {
        this.active = active;
    }

            
	public void setIsShot(){
		if(this.isShot == true){
			IO.println("Sie haben bereits auf dieses Feld geschossen. Ein verschenkter Schuss!");
		}
		else{
			this.isShot = true;
			if(this.hasShip == true){
				this.status = "X";
				this.setHit(true);
				IO.println("Sie haben ein Schiff getroffen!");
			}
			else{
				this.status = "O";
				IO.println("Sie haben auf Wasser geschossen!");
			}
		}
	}
	
	
	public void setWater(boolean isWater) {
		this.isWater = true;
	}

	
	public void setHit(boolean isHit) {
		this.isHit = true;
		this.isShot = true;
		this.status = "X";
	}
	
	
	public void setHasShip(boolean hasShip) {
		this.hasShip = true;
	}

	
	/**
	 * 
	 * @param status �bergabe des Status, welches �bernommen werden sollen.
	 */
	public void setStatus(String status){
		this.status = status;
	}
	
	/**
	 * Methode zum Ausgeben des aktuellen Status
	 */
	public void print(){
		IO.print(this.status + "  ");
                IO.print(fieldnumber);
	}
	
	
}
