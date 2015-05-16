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
	private String status;
	private String opponentStatus;
	private String fieldnumber;
	private int shipNumber;
        
	
        
     /**
	 * Konstruktor
	 */
    
	public Field() {
		this.isShot = false;
		this.isWater = true;
		this.isHit = false;
		this.hasShip = false;
        this.active = true;
		this.status = "~";
    }

    public String getFieldnumber() {
        return fieldnumber;
    }

    public void setFieldnumber(String fieldnumber) {
        this.fieldnumber = fieldnumber;
    }

	 
	public boolean getIsActive() {
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

    /**
     *         
     * @return Schiffsnummer
     */
	public int setIsShot(){
		if(this.isShot == true){
			IO.println("Sie haben bereits auf dieses Feld geschossen. Ein verschenkter Schuss!");
		}
		else{
			this.isShot = true;
			if(this.hasShip == true){
				this.setStatus("X");
				this.setOpponentStatus("X");
				this.setHit(true);
				IO.println("Sie haben ein Schiff getroffen!");
			}
			else{
				this.setStatus("O");
				this.setOpponentStatus("0");
				IO.println("Sie haben auf Wasser geschossen!");
			}
		}
		return getShipNumber();
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
	
	public String getOpponentStatus() {
		return opponentStatus;
	}

	public void setOpponentStatus(String opponentStatus) {
		this.opponentStatus = opponentStatus;
	}

	public int getShipNumber() {
		return shipNumber;
	}

	public void setShipNumber(int shipNumber) {
		this.shipNumber = shipNumber;
	}

	/**
	 * Methode zum Ausgeben des aktuellen Status
	 */
	public void print(){
		IO.print(this.status + "  ");
//		IO.print(fieldnumber);
	}
	
	/**
	 * Methode zum Ausgeben des aktuellen Status aus Gegnersicht
	 */
	public void printForOpponent(){
		IO.print(this.opponentStatus + "  ");
	}
	
	
}
