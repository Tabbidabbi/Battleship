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
            player[i] = new Player(i, name);

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
                        IO.println("Spieler " + player[pla].getNumber() + " " + player[pla].getName() + " ist am Zug!");

                        //Abfrage, welcher Spieler angegriffen werden soll
                        IO.println("Welchen Spieler m�chtest du angreifen?");

                        //Gibt die Liste aller Spieler aus, die angegriffen werden k�nnen
                        printListOfOpponents(player, pla);

                        IO.print("Gebe die Nummer des Gegners ein: ");

                        //Einlesen des SPielers, den man angreifen will
                        int opponent = IO.readInt();
                        //Wert f�r Array, denn Array f�ngt bei 0 an zu z�hlen
                        opponent = opponent - 1;

                        //Gibt das Spielfeld des Gegners aus
                        player[opponent].getField().printOpponentField();

                        //Schiff
                        IO.println("Mit welchem Schiff willst du schie�en?");
                        for (int shi = 0; shi < player[pla].getShips().length; shi++) {
                            IO.println("Nummer: " + player[pla].getShips()[shi].getNumber() + " Typ: " + player[pla].getShips()[shi].getName());
                        }

                        //Eingabe, welches Schiff schie�en soll
                        IO.println("Gebe die Nummer des Schiffs ein: ");

                        //Einlesen des Schiffs
                        int ship = IO.readInt();
                        ship = ship - 1;

                        //Reichweite des Schusses, um diese der Methode setShot zu �bergeben
                        int shootRange = player[pl].getShips()[ship].getShootRange();

                        //Abfrage
                        IO.println("Wo soll das Schiff hinschie�en?");
                        //Einlesen X-Koordinate
                        IO.print("X = ");
                        int x = IO.readInt();
                        //Einlesen Y-Koordinate
                        IO.print("Y = ");
                        int y = IO.readInt();

                        //Schie�en
                        //Felder des gegnerischen Spielers werden auf abgeschossen gesetzt
                        player[pla].getField().setShot(x, y, shootRange);
                        player[pla].getOpponentField().setShot(x, y, shootRange);
                        player[pla].getOpponentField().printOpponentField();

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

    public static int getXCoordinate() {
        IO.print("Geben Sie die x-Koordinate zum Setzen des Schiffs ein: ");
        int x = IO.readInt();
        return x;
    }

    public static int getYCoordinate() {
        IO.print("Geben Sie die y-Koordinate zum Setzen des Schiffs ein: ");
        int y = IO.readInt();
        return y;
    }

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
		Gibt Liste der Schiffe aus, die zur Verf�gung stehen
		
		@param player Playerarray
		@param playerN Index des Spielers in Player-Array
	**/
	public static void printListOfShips(Player[] player, int playerN){
		for(int s = 0; s < player[playerN].getShips().length; s++){
			if(player[playerN].getShips()[s].getHitpoints() != 0){
				if(player[playerN].getShips()[s].getReloadTime() == 0){
				IO.println("Nummer: " + player[playerN].getShips()[s].getNumber() + " Typ: " + player[playerN].getShips()[s].getName());
			
				}
			}	
		}
	}
	
		
}
