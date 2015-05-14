package Ships;

import PlayingField.PlayField;

public abstract class Ship {

    int size;
    boolean sunk = false;
    int number;
    // true = Horizontal, false = vertikal
    boolean orientation;
    int reloadTime;
    int currentReloadTime;
    int shootRange;
    int hitpoints;
    char sign;
    String name;

    public Ship(char sign, int size, boolean sunk, int number, boolean orientation, int reloadTime, int currentReloadTime, int shootRange, String name) {
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

    public char getSign() {
        return sign;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean getIsSunk() {
        return sunk;
    }

    public void setSunk(boolean sunk) {
        this.sunk = true;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean getOrientation() {
        return orientation;
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }

    //Setzt ReloadTime einen Z�hler runter
    public void setDownReloadTime() {
        this.currentReloadTime--;
    }

    public int getCurrentReloadTime() {
        return currentReloadTime;
    }

    public void setCurrentReloadTime(int currentReloadTime) {
        this.currentReloadTime = this.reloadTime;
    }

    public int getShootRange() {
        return shootRange;
    }

    public void setShootRange(int shootRange) {
        this.shootRange = shootRange;
    }

    public int getHitpoints() {
        return this.hitpoints;
    }

    public void setHitpoints() {
        this.hitpoints = this.hitpoints - 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void shoot(int x, int y) {
        //Zeichen auf dem Spielfeld �ndern
        //Pr�fen, ob etwas getroffen wurde
    }
//Methode um die Schiffe zu setzen

    public boolean placeShip(int x, int y, boolean orientation, PlayField playfield) {

        //true = horizontal
        if (orientation == true) {

            // Testen ob Schiff komplett gezeichnet werden kann
            // 1. ALLE Felder sind active
            // Alle Felder liegen innerhalb des playfields
            for (int i = 0; i < getSize(); i++) {
                try {
                    //Abfrage, welche prüft ob das Feld auf der das Schiff gesetzt werden soll, deaktiviert ist. Falls ja:
                    //gibt die ganze Methode "false zurück".
                    if (!playfield.getPlayField()[x][y + i].isActive()) {
                        System.out.println(" Sorry geht nicht");
                        return false;
                    }
                    //Falls das Schiff mit der Größe nicht in das Array passt, fange die Fehlermeldung ab und gib folgendes aus...
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Schiff passt nicht auf Spielfeld, bitte neue koordinaten eingeben!!!");
                    return false;
                }
            }
            // Setze Schiff
            for (int i = 0; i < getSize(); i++) {
                playfield.getPlayField()[x][y + i].setStatus(this.getSign());

            }

//             Deaktiviere Felder um das Schiff herum
            for (int i = (y - 1); i <= getSize() + y; i++) {
                for (int j = (x - 1); j < x + 2; j++) {
                    try {
                        playfield.getPlayField()[j][i].setActive(false);
                        //Tetstweise eingebaut um zu sehen welche Felder deaktiviert werden
                        playfield.getPlayField()[j][i].setStatus((char) 102);
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                }
            }

        } //false = vertikal
        else {

            for (int i = 0; i < getSize(); i++) {
                try {
                    //Abfrage, welche prüft ob das Feld auf der das Schiff gesetzt werden soll, deaktiviert ist. Falls ja:
                    //gibt die ganze Methode "false zurück".
                    if (!playfield.getPlayField()[x + i][y].isActive()) {
                        System.out.println(" Sorry geht nicht");
                        return false;
                    }
                    //Falls das Schiff mit der Größe nicht in das Array passt, fange die Fehlermeldung ab und gib folgendes aus...
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Schiff passt nicht auf Spielfeld, bitte neue koordinaten eingeben!!!");
                    return false;
                }
            }
            // Setze Schiff
            for (int i = 0; i < getSize(); i++) {
                playfield.getPlayField()[x + i][y].setStatus(this.getSign());
            }

            // Deaktiviere Felder um das Schiff herum
            for (int i = (x - 1); i <= getSize() + x; i++) {
                for (int j = (y - 1); j < y + 2; j++) {
                    try {
                        playfield.getPlayField()[i][j].setActive(false);
                        //Tetstweise eingebaut um zu sehen welche Felder deaktiviert werden
                        playfield.getPlayField()[i][j].setStatus((char) 102);
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                }
            }

        }
        playfield.printPlayField();

        return true;
    }
}
