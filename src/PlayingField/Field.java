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
        private String number;
	int nextFigure = 0;
        String nextChar = "abcdefghijklmnopqrstuvwxyz";
	/**
	 * Konstruktor
	 */
	public Field(){
		
		this.isShot = false;
		this.isWater = true;
		this.isHit = false;
		this.hasShip = false;
                this.active = true;
		this.status = "~";
                this.nextFigure++;
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
		this.status = "X";
	}
	
	public boolean getHasShip() {
		return hasShip;
	}

	public void setHasShip(boolean hasShip) {
		this.hasShip = true;
	}

	public String getStatus(){
		return this.status;
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
	}
	
	
}
