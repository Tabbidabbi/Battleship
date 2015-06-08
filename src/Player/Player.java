package Player;

import Ships.Corvette;
import Ships.Ship;
import Ships.Submarine;
import Ships.Destroyer;
import Ships.Frigate;
import PlayingField.PlayField;

/**
 *
 * @author Dennis
 *
 */
public class Player {

    private int playerID;
    private String name;
    private Ship[] ships = new Ship[6];
    private PlayField playfield;
    private PlayField opponentField;
    //private Field[][] field;
    private boolean lost = false;

    /**
   	 * Konstruktor
     * @param number
     * @param name
   	 */
    
    public Player(int number, String name) {

        this.playerID = number;
        this.name = name;

		//Schiffs-Array mit Schiffen f�llen
        //1 Zerstoerer
        //1 Fregatte
        //2 Korvetten
        //2 U-Boote
        this.ships[0] = new Destroyer(1);
        this.ships[1] = new Frigate(2);
        this.ships[2] = new Corvette(3);
        this.ships[3] = new Corvette(4);
        this.ships[4] = new Submarine(5);
        this.ships[5] = new Submarine(6);

        //Erstellung des Feldes
        this.playfield = new PlayField();
        playfield.printPlayField();

        this.opponentField = new PlayField();

    }
    
    

    public void initializePlayer() {
        
    }
    
    //Getter Methoden deffinieren
    
    public int getPlayerID() {
        return this.playerID;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Ship[] getShips() {
        return this.ships;
    }
    
    public boolean getIsLost() {
        return this.lost;
    }
    
    public PlayField getField() {
        return playfield;
    }
    
    public PlayField getOpponentField() {
        return this.opponentField;
    }
    
    //Setter Methoden deffinieren

    public void setPlayerID(int number) {
        this.playerID = number;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public void setShips(Ship[] ships) {
        this.ships = ships;
    }

    
    public void setLost(boolean lost) {
        this.lost = lost;
    }

   
    public void setField(PlayField field) {
        this.playfield = field;
    }

    
    public void setOpponentField(PlayField opponentField) {
        this.opponentField = opponentField;
    }
}