package Main;

import Player.Player;
import IO.IO;

/**
 *
 * @author Dennis
 *
 */
public class Battleship {

    public static void main(String[] args) {
        int input = 0;
        IO.println("Herzlich Willkommen zu Schiffe versenken!");
        int amountOfPlayer = getAmountOfPlayers();//Spieleranzahl 

        //Spieler-Array erstellen
        Player[] player = new Player[amountOfPlayer];//Spieleranzahl holen
        for (int i = 0; i < amountOfPlayer; i++) {
            IO.print("Geben Sie den Namen des " + (i + 1) + " Spielers ein: ");
            String name = IO.readString();
            player[i] = new Player((i + 1), name);

        }

        for (int p = 0; p < player.length; p++) {
            IO.print("Spieler " + player[p].getName() + " , " + "Sie können nun " + player[p].getShips().length + " Schiffe setzen: " + "\n"
                    + "1 Zerstörer, 1 Fregatte, 2 Corvetten und 2 U-Boote." + "\n");
            for (int s = 0; s < player[p].getShips().length; s++) {
                IO.print("Drücken Sie die " + player[p].getShips()[0].getNumber()
                        + " für Zerstörer, die " + player[p].getShips()[1].getNumber()
                        + " für Fregatte, die " + player[p].getShips()[2].getNumber()
                        + " für Corvette und die " + player[p].getShips()[4].getNumber() + " für U-Boot: ");
                input = IO.readInt();
                switch (input) {
                    case 1:
                        int xD = getXCoordinate();
                        int yD = getYCoordinate();
                        boolean orientationD = getOrientation();
                        player[p].getShips()[0].placeShip(xD, yD, orientationD, player[p].getField());
                        break;
                    case 2:
                        int xF = getXCoordinate();
                        int yF = getYCoordinate();
                        boolean orientationF = getOrientation();
                        player[p].getShips()[1].placeShip(xF, yF, orientationF, player[p].getField());
                        break;
                    case 3:
                        int xC = getXCoordinate();
                        int yC = getYCoordinate();
                        boolean orientationC = getOrientation();
                        player[p].getShips()[2].placeShip(xC, yC, orientationC, player[p].getField());
                        break;
                    case 4:
                        int xU = getXCoordinate();
                        int yU = getYCoordinate();
                        boolean orientationU = getOrientation();
                        player[p].getShips()[4].placeShip(xU, yU, orientationU, player[p].getField());
                        break;
                    default:
                        System.out.println("Bitte Schiff erneut setzen!!");

                }

            }
        }

        //Runde beginnt
        //solange es mehr als einen spieler gibt
        while (player.length < 1) {
            for (int pl = 0; pl < player.length; pl++) {

                //Spieler, die verloren haben, kommen nicht mehr an die Reihe
                if (player[pl].getIsLost() == false) {

                    //Setzt die Nachladezeit aller Schiffe in jeder Runde um 1 runter
                    for (int sh = 0; sh < player[pl].getShips().length; sh++) {
                        player[pl].getShips()[sh].setDownReloadTime();
                    }

                    //Runde des Spielers pla
                    for (int pla = 0; pla < player.length; pla++) {
                        IO.println("Spieler " + player[pla].getNumber() + ": " + player[pla].getName() + " ist am Zug!");

                        //1. Auswahl eines verf�gbaren Schiffes. (Methode hierf�r schreiben)
                        IO.println("Mit welchem Schiff willst du schie�en?");
                        for (int shi = 0; shi < player[pla].getShips().length; shi++) {
                            IO.println("Nummer: " + player[pla].getShips()[shi].getNumber() + " Typ: " + player[pla].getShips()[shi].getName());
                        }
						printListOfShips(player, pla);

                        //Eingabe, welches Schiff schie�en soll
                        IO.println("Gebe die Nummer des Schiffs ein: ");

                        //Einlesen des Schiffs
                        int ship = IO.readInt();
                        
                        
                        IO.println("Sie haben das Schiff mit der Nummer " + ship + " mit dem Typ " + player[pla].getShips()[ship - 1].getName() + " ausgew�hlt!");
                        
                        //F�r Shiffs-Array
                        ship = ship - 1;

                        //Reichweite des Schusses, um diese der Methode setShot zu uebergeben
                        int shootRange = player[pl].getShips()[ship].getShootRange();

						//Hierfuer noch eine Methode schreiben
                        boolean orientation = false;
                        if(shootRange > 1){
                        	orientation = getOrientation();
                        }
                        
                        //2. Auswahl eines Gegners. (Methode hierf�r schreiben)
                        //Abfrage, welcher Spieler angegriffen werden soll
                        IO.println("Welchen Spieler m�chtest du angreifen?");

                        //Gibt die Liste aller Spieler aus, die angegriffen werden koennen
                        printListOfOpponents(player, pla);

                        IO.print("Geben Sie die Nummer des Gegners ein: ");

                        //Einlesen des SPielers, den man angreifen will
                        int opponent = IO.readInt();
                        //Wert fuer Array, denn Array faengt bei 0 an zu zaehlen
                        opponent = opponent - 1;

                        //Gibt das Spielfeld des Gegners aus
                        player[opponent].getField().printOpponentField();

                        //3. Koordinate auf dem Spielfeld ausw�hlen. (Methode hierf�r schreiben)
                        //Abfrage
                        IO.println("Wo soll das Schiff hinschie�en?");
                        //Einlesen X-Koordinate
                        IO.print("X = ");
                        int x = IO.readInt();
                        //Einlesen Y-Koordinate
                        IO.print("Y = ");
                        int y = IO.readInt();

                        //Schie�en
                        shootOnPlayField(player, opponent, shootRange, orientation, x, y);
                        
                        //Nachladezeit nach Schuss setzen, damit das Schiff erst nachladen muss,
                        //um wieder schie�en zu koennen
                        player[pla].getShips()[ship].setCurrentReloadTime();
                        
                        //4. Der Gegner sagt, ob der Schuss ins Wasser ging, ein Schiff getroffen hat, oder ob ein Schiff versenkt wurde.
                        
                        if (player[pla].getIsLost() == true) {
                            //Spieler player[pla] aus dem Spieler-Array nehmen
                        }
                    }
                }
                //Setzt pl auf 0, damit die Runde vorn beginnt
                if (pl == player.length) {
                    pl = 0;

                }

            }
        }
    }

    /**
     * Setzen des Schusses auf das PlayField
     * @param player Spielerarray
     * @param opponent Integerwert des Gegners f�r das Finden im Array
     * @param shootRange Anzahl der Felder, die bei einem Schuss getroffen werden
     * @param orientation Richtung des Schusses
     * @param x Korrdinate des Fieldes
     * @param y Korrdinate des Fieldes
     */    
	public static void shootOnPlayField(Player[] player, int opponent, int shootRange, boolean orientation, int x, int y) {
		//Felder des gegnerischen Spielers werden auf abgeschossen gesetzt
		player[opponent].getField().setShot(x, y, shootRange, orientation);
		player[opponent].getOpponentField().setShot(x, y, shootRange, orientation);
		
		//Pr�fen ob schiffe getroffen
		
		
		//Gibt das Feld des Gegner aus
		player[opponent].getOpponentField().printOpponentField();
	}
	
	

	/**
	 * In dieser Methode wird die Anzahl der Spieler abgefragt
	 * 
	 * @return Anzahl der Spieler
	 */

    public static int getAmountOfPlayers() {
        IO.print("Geben Sie die Anzahl der Spieler ein (2-6): ");
        int amountOfPlayer = IO.readInt();
        if (amountOfPlayer <= 1) {
            IO.println("Es müssen mindestens 2 Spieler das Spiel spielen!");
            getAmountOfPlayers();
        } else if (amountOfPlayer > 6) {
            IO.println("Es müssen mindestens 2 Spieler das Spiel spielen!");
            getAmountOfPlayers();

        }
        return amountOfPlayer;
    }

    /**
     * In dieser Methode wird die x-Koordinate f�r Schiffsetzen und Schiessen abgefragt
     * 
     * @return Integerwert fuer x-Koordinate
     */
	public static int getXCoordinate() {
	    IO.print("Geben Sie die x-Koordinate zum Setzen des Schiffs ein: ");
	    int x = IO.readInt();
	    return x;
	}

	/**
	 * In dieser Methode wird die y-Koordinate f�r Schiffsetzen und Schiessen abgefragt
	 * 
	 * @return Integerwert fuer y-Koordinate
	 */
	public static int getYCoordinate() {
	    IO.print("Geben Sie die y-Koordinate zum Setzen des Schiffs ein: ");
	    int y = IO.readInt();
	    return y;
	}

	/**
	 * 
	 * @return Integerwert fuer die Richtung
	 */
    public static boolean getOrientation() {
        boolean orientation = false;
        IO.print("Geben Sie die Ausrichtung des Schiffs ein (h oder v): ");
        String o = IO.readString();
        if (o.equals("h")) {
            orientation = true;
        } else if (o.equals("v")) {
            orientation = false;
        } else {
            getOrientation();
        }

        return orientation;
    }

	/**
	 * Gibt Liste der Spieler aus
	 * 
	 * @param player Spielerarray
	 */
	public static void printListOfPlayers(Player[] player){
		for(int p = 0; p < player.length; p++){
			if(player[p].getIsLost() == false){
				IO.println(player[p].getNumber() + " " + player[p].getName());
			}			
		}
	}
	
	/**
	 * Gibt Liste der Spieler aus, die zur Verf�gung stehen
	 * ohne des Spielers, der gerade am Zug ist.
	 * 
	 * @param player Spielerarray
	 * @param playerN Integerwert des Spielers, der gerade angreift
	 */
	public static void printListOfOpponents(Player[] player, int playerN){
		for(int p = 0; p < player.length; p++){
			if(player[p].getIsLost() == false){
				if(player[p] != player[playerN]){
					IO.println(player[p].getNumber() + " " + player[p].getName());
				}
				
			}			
		}
	}
	
	/**
	*	Gibt Liste der Schiffe aus, die zur Verf�gung stehen
	*	
	*	@param player Playerarray
	*	@param playerN Index des Spielers in Player-Array
	*/
	public static void printListOfShips(Player[] player, int playerN){
		for(int s = 0; s < player[playerN].getShips().length; s++){
			if(player[playerN].getShips()[s].getHitpoints() != 0){
				if(player[playerN].getShips()[s].getCurrentReloadTime() == 0){
				IO.println("Nummer: " + player[playerN].getShips()[s].getNumber() + " Typ: " + player[playerN].getShips()[s].getName());
			
				}
			}	
		}
	}
	
		
}
