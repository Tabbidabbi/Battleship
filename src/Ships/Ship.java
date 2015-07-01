package Ships;

import java.io.Serializable;

import PlayingField.PlayField;
import PlayingField.Field;
import IO.IO;

public abstract class Ship implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5112884400853827578L;
	int size;
    boolean sunk = false;
    int number;
    // true = Horizontal, false = vertikal
    boolean orientation;
    int reloadTime;
    int currentReloadTime;
    int shootRange;
    int hitpoints;
    String sign;
    String name;

    /**
     * Konstruktor
     */
    public Ship(String sign, int size, boolean sunk, int number, boolean orientation, int reloadTime, int currentReloadTime, int shootRange, String name) {
        this.sign = sign;
        this.number = number;
        this.size = size;
        this.sunk = sunk;
        this.orientation = orientation;
        this.reloadTime = reloadTime;
        this.currentReloadTime = currentReloadTime;
        this.shootRange = shootRange;
        this.hitpoints = size;
        this.name = name;

    }

    //Getter Methoden deffinieren
    public String getSign() {
        return sign;
    }

    public int getSize() {
        return size;
    }

    public boolean getIsSunk() {
        return sunk;
    }

    public int getNumber() {
        return number;
    }

    public boolean getOrientation() {
        return orientation;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public int getCurrentReloadTime() {
        return currentReloadTime;
    }

    public int getShootRange() {
        return shootRange;
    }

    public int getHitpoints() {
        return this.hitpoints;
    }

    public String getName() {
        return name;
    }

    //Setter Methoden deffinieren
    public void setSize(int size) {
        this.size = size;
    }

    public void setSunk(boolean sunk) {
        this.sunk = true;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }

    //Setzt ReloadTime einen Z�hler runter
    public void setDownReloadTime() {
        this.currentReloadTime--;
    }

    public void setCurrentReloadTime() {
        this.currentReloadTime = this.reloadTime + 1;
    }

    public void setShootRange(int shootRange) {
        this.shootRange = shootRange;
    }

    public void setHitpoints() {
    	this.hitpoints--;
    	if(getHitpoints() == 0){
    		setSunk(true);
    		IO.println("Schiff wurde versenkt.");
    		//test
    		IO.println(getNumber()+ " " + getNumber() + " " + getHitpoints() + " " + getIsSunk());
    	}
    }

    public void setName(String name) {
        this.name = name;
    }

    public void shoot(int x, int y) {
        //Zeichen auf dem Spielfeld �ndern
        //Pr�fen, ob etwas getroffen wurde
    }
//Methode um die Schiffe zu setzen

    public boolean placeShip(String input, boolean orientation, PlayField playfield, PlayField opponentfield) {

        //true = horizontal
        if (orientation == true) {
            for (int y = 0; y < playfield.getPlayField().length; y++) {
                for (int x = 0; x < playfield.getPlayField()[y].length; x++) {
                    if (input.equals(playfield.getPlayField()[y][x].getFieldnumber())) {
//                     1. ALLE Felder sind active
//                     Alle Felder liegen innerhalb des playfields
                        for (int i = 0; i < getSize(); i++) {
                            try {
                                //Abfrage, welche prüft ob das Feld auf der das Schiff gesetzt werden soll, deaktiviert ist. Falls ja:
                                //gibt die ganze Methode "false zurück".
                                if (!playfield.getPlayField()[y][x + i].getIsActive()) {
                                    IO.println("Leider nicht möglich, das Schiff muss mindestens 1 Feld Abstand zum nächsten Schiff haben!");
                                    return false;
                                }
                                //Falls das Schiff mit der Größe nicht in das Array passt, fange die Fehlermeldung ab und gib folgendes aus...
                            } catch (ArrayIndexOutOfBoundsException e) {
                                IO.println("Das Schiff passt so nicht auf das Spielfeld, bitte neue koordinaten eingeben!!!");
                                return false;
                            }
                        }
                        // Setze Schiff

                        for (int i = 0; i < getSize(); i++) {
                            playfield.getPlayField()[y][x + i].setStatus(this.getSign());
                            playfield.getPlayField()[y][x + i].setOpponentStatus(this.getSign());
                            playfield.getPlayField()[y][x + i].setWater(false);
                            playfield.getPlayField()[y][x + i].setHasShip(true);
                            playfield.getPlayField()[y][x + i].setShipNumber(getNumber());
                            
                            opponentfield.getPlayField()[y][x + i].setWater(false);
                            opponentfield.getPlayField()[y][x + i].setHasShip(true);
                            opponentfield.getPlayField()[y][x + i].setShipNumber(getNumber());
                            

                        }

//             Deaktiviere Felder um das Schiff herum
                        for (int i = (x - 1); i <= getSize() + x; i++) {
                            for (int j = (y - 1); j < y + 2; j++) {
                                try {
                                    playfield.getPlayField()[j][i].setActive(false);
                                    //Tetstweise eingebaut um zu sehen welche Felder deaktiviert werden
//                        playfield.getPlayField()[j][i].setStatus("F");
                                } catch (ArrayIndexOutOfBoundsException e) {

                                }
                            }
                        }
                    }
                }
            }

        } //false = vertikal
        else {
            for (int y = 0; y < playfield.getPlayField().length; y++) {
                for (int x = 0; x < playfield.getPlayField()[y].length; x++) {
                    if (input.equals(playfield.getPlayField()[y][x].getFieldnumber())) {
                        for (int i = 0; i < getSize(); i++) {
                            try {
                    //Abfrage, welche prüft ob das Feld auf der das Schiff gesetzt werden soll, deaktiviert ist. Falls ja:
                                //gibt die ganze Methode "false zurück".
                                if (!playfield.getPlayField()[y + i][x].getIsActive()) {
                                    IO.println("Leider nicht möglich, das Schiff muss mindestens 1 Feld Abstand zum nächsten Schiff haben!");
                                    return false;
                                }
                                //Falls das Schiff mit der Größe nicht in das Array passt, fange die Fehlermeldung ab und gib folgendes aus...
                            } catch (ArrayIndexOutOfBoundsException e) {
                                IO.println("Das Schiff passt so nicht auf das Spielfeld, bitte neue koordinaten eingeben!!!");
                                return false;
                            }
                        }
                        // Setze Schiff
                        for (int i = 0; i < getSize(); i++) {
                            playfield.getPlayField()[y + i][x].setStatus(this.getSign());
                            playfield.getPlayField()[y + i][x].setOpponentStatus(this.getSign());
                            playfield.getPlayField()[y + i][x].setWater(false);
                            playfield.getPlayField()[y + i][x].setHasShip(true);
                            playfield.getPlayField()[y + i][x].setShipNumber(getNumber());
                            
                            opponentfield.getPlayField()[y][x + i].setWater(false);
                            opponentfield.getPlayField()[y][x + i].setHasShip(true);
                            opponentfield.getPlayField()[y][x + i].setShipNumber(getNumber());
                            
                        }

                        // Deaktiviere Felder um das Schiff herum
                        for (int i = (y - 1); i <= getSize() + y; i++) {
                            for (int j = (x - 1); j < x + 2; j++) {
                                try {
                                    playfield.getPlayField()[i][j].setActive(false);
                                    //Tetstweise eingebaut um zu sehen welche Felder deaktiviert werden
//                        playfield.getPlayField()[i][j].setStatus("F");
                                } catch (ArrayIndexOutOfBoundsException e) {

                                }
                            }
                        }

                    }
                }
            }
        }
        playfield.printPlayField();

        return true;
    }
}
