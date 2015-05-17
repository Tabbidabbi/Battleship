package Main;

import Player.Player;
import Ships.Ship;
import IO.IO;

/**
 *
 * @author Dennis
 *
 */
public class Battleship {

    public static void main(String[] args) {
        String coordinateInput;//Eingabe der Koordinate
        boolean shipO;// Ausrichtung des Schiffes (h,v)
        Ship ships[];//Speicher fuer alle Schiffe
        int amountOfPlayer;//Speicher fuer Spieleranzahl
        boolean error = false; //Speicher für falsche Eingabe, wenn Spieler falsche Eingabe tätigt wird der Wert auf "true" gestellt.

        //Spiel beginnt
        IO.println("Herzlich Willkommen zu Schiffe versenken!");
        //Do-While Schleife welche bei falsche Eingabe den Spieler auffordert, die Eingabe zu wiederholen.
        do {
            IO.print("Geben Sie die Anzahl der Spieler ein (2-6): ");
            amountOfPlayer = IO.readInt(); //Einlesen der Spieleranzahl
            if (amountOfPlayer < 2 || amountOfPlayer > 6) {//Prüfen ob Spieler kleiner 2 oder größer 6 ist, wenn ja wird error auf true gestellt.
                error = true;
                IO.println("Falsche Eingabe, es muessen mindestens 2 und maximal 6 Spieler spielen.");
            } else {
                error = false;
            }
        } while (error);//Führe die Schleife aus, solange error = true ist.

        //Spieler-Array erstellen
        Player[] player = new Player[amountOfPlayer];//Spieleranzahl holen
        for (int i = 0; i < amountOfPlayer; i++) {
            IO.print("Spieler " + (i + 1) + " - Geben Sie ihren Namen ein: ");
            String name = IO.readString();//Einlesen des Spielernamens
            player[i] = new Player(i + 1, name);//Jeder Index im Array bekommt einen Spieler

            IO.print("Spieler " + player[i].getName() + " , " + "Sie koennen nun "
                    + player[i].getShips().length + " Schiffe setzen: "
                    + "\n"
                    + "1 Zerstoerer, 1 Fregatte, 2 Corvetten und 2 U-Boote."
                    + "\n");
            //Anzahl der Schiffe holen.
            ships = player[i].getShips();
            for (int s = 0; s < ships.length;) {
                error = false;
                //Koordinaten holen
                IO.print("Bitte geben Sie die Koordinaten fuer " + ships[s].getName() + " ein:");
                do {
                    coordinateInput = IO.readString().toLowerCase(); //Großbuchstaben-> Kleinbuchstaben
                    //Teste Eingabe mit RegEx(^ Anfang, 1 Zahl (1-9) und 1 oder keine Zahl(0-9) und 1 Buchstabe (a-z), $ Ende
                    if (coordinateInput.matches("^[1-9]{1}[0-9]{0,1}[a-z]{1}$")) {
                        error = false;
                    } else {
                        IO.println("Falsche Eingabe, bitte geben sie zuerst die Nummer und dann den Buchstaben des Feldes ein: ");
                        error = true;
                    }
                } while (error);

                //Schiffsausrichtung holen
                shipO = setOrientation();
                //Schiff wird gesetzt
                if (!ships[s].placeShip(coordinateInput, shipO, player[i].getField(), player[i].getOpponentField())) {
                    IO.println("Das Schiff konnte nicht gesetzt werden. Bitte erneut versuchen.");
                } else {
                    s++;
                }
            }
        }

        //Runde beginnt
        //solange es mehr als einen spieler gibt
        while (checkAmountOfAvailablePlayers(player) > 1) {
            for (int pl = 0; pl < player.length; pl++) {

                //Spieler, die verloren haben, kommen nicht mehr an die Reihe
                if (player[pl].getIsLost() == false) {

                    //Setzt die Nachladezeit aller Schiffe in jeder Runde um 1 runter
                    for (int sh = 0; sh < player[pl].getShips().length; sh++) {
                        if (player[pl].getShips()[sh].getCurrentReloadTime() > 1) {
                            player[pl].getShips()[sh].setDownReloadTime();
                        }
                    }

                    //Runde des Spielers pla
                    for (int pla = 0; pla < player.length; pla++) {
                        IO.println("Spieler " + player[pla].getNumber() + ": " + player[pla].getName() + " ist am Zug!");
                        player[pla].getField().printPlayField();

                        //1. Auswahl eines verfuegbaren Schiffes. (Methode hierf�r schreiben)
                        int ship = getAvailableShipToShoot(player, pla);

                        //Reichweite des Schusses, um diese der Methode setShot zu uebergeben
                        int shootRange = player[pl].getShips()[ship].getShootRange();

                        //Hierfuer noch eine Methode schreiben
                        boolean orientation = false;
                        if (shootRange > 1) {
                            orientation = setShootOrientation();
                        }

                        //2. Auswahl eines Gegners. (Methode hierfuer schreiben)
                        int opponent = getNumberOfOpponent(player, pla);
                        //Gibt das Spielfeld des Gegners aus
                        player[opponent].getOpponentField().printOpponentField();

                        //3. Koordinate auf dem Spielfeld ausw�hlen. (Methode hierf�r schreiben)
                        //Abfrage
                        String koordinate = getKoordinatesToShoot();
                        //IO.println("Wo soll das Schiff hinschiessen?");
                        //Einlesen X-Koordinate
                        //IO.print("X = ");
                        //int x = IO.readInt();
                        //Einlesen Y-Koordinate
                        //IO.print("Y = ");
                        //int y = IO.readInt();

                        //Schiessen
                        //4. Der Gegner sagt, ob der Schuss ins Wasser ging, ein Schiff getroffen hat, oder ob ein Schiff versenkt wurde.
                        //shootOnPlayField(player, opponent, shootRange, orientation, x, y);
                        shootOnPlayField(player, opponent, shootRange, orientation, koordinate);

                        //Nachladezeit nach Schuss setzen, damit das Schiff erst nachladen muss,
                        //um wieder schiessen zu koennen
                        player[pla].getShips()[ship].setCurrentReloadTime();

                        if (checkIfShipAvailable(player, opponent) == false) {
                            player[opponent].setLost(true);
                        }

                        if (player[opponent].getIsLost() == true) {
                            //Spieler player[pla] aus dem Spieler-Array nehmen
                            IO.println(player[opponent].getName() + " hat verloren!");
                        }
                    }
                }
                //Setzt pl auf 0, damit die Runde vorn beginnt
                if (pl + 1 == player.length) {
                    pl = 0;

                }

            }
        }
        //Ausgabe des Speilers der gewonnen hat

    }

    /**
     * @param player Spielerarray
     * @param opponent Integerwert des Gegners f�r das Finden im Array
     * @param shootRange Anzahl der Felder, die bei einem Schuss getroffen
     * werden
     * @param orientation Richtung des Schusses
     * @param x Korrdinate des Fieldes
     * @param y Korrdinate des Fieldes
     */
    public static void shootOnPlayField(Player[] player, int opponent, int shootRange, boolean orientation, int x, int y) {
        int[] hitShips;

        //Felder des gegnerischen Spielers werden auf abgeschossen gesetzt
        hitShips = player[opponent].getField().setShot(x, y, shootRange, orientation);
        player[opponent].getOpponentField().setShot(x, y, shootRange, orientation);

        //Pr�fen ob schiffe getroffen
        for (int i = 0; i < hitShips.length; i++) {
            player[opponent].getShips()[i].setHitpoints();
        }

        //Gibt das Feld des Gegner aus
        player[opponent].getOpponentField().printOpponentField();
    }

    public static void shootOnPlayField(Player[] player, int opponent, int shootRange, boolean orientation, String koordinate) {
        int[] hitShips;

        //Felder des gegnerischen Spielers werden auf abgeschossen gesetzt
        //hitShips = player[opponent].getField().setShot(x, y, shootRange, orientation);
        //player[opponent].getOpponentField().setShot(x, y, shootRange, orientation);
        hitShips = player[opponent].getField().setShot(koordinate, shootRange, orientation);
        player[opponent].getOpponentField().setShot(koordinate, shootRange, orientation);

        //Pruefen ob schiffe getroffen
        for (int i = 0; i < hitShips.length; i++) {
            for (int s = 0; s < player[opponent].getShips().length; s++) {
                if (player[opponent].getShips()[s].getNumber() == hitShips[i]) {
                    player[opponent].getShips()[s].setHitpoints();
                }
            }

        }

        //Gibt das Feld des Gegner aus
        player[opponent].getOpponentField().printOpponentField();
    }

    /**
     *
     * @return Integerwert fuer die Richtung
     */
    public static boolean setOrientation() {
        boolean orientation = false;
        boolean error = false;
        do {
            IO.print("Bitte geben Sie die Ausrichtung des Schiffes an (h = horizontal, v = vertical: ");
            String o = IO.readString();
            if (o.equals("h")) {
                error = false;
                orientation = true;
            } else if (o.equals("v")) {
                error = false;
                orientation = false;
            } else {
                IO.println("Falsche Eingabe, bitte wiederholen Sie die Eingabe!");
                error = true;
            }
        } while (error);

        return orientation;
    }

    public static boolean setShootOrientation() {
        boolean orientation = false;
        boolean error = false;
        do {
            IO.print("Bitte geben Sie die Ausrichtung des Schusses an (h = horizontal, v = vertical: ");
            String o = IO.readString();
            if (o.equals("h")) {
                error = false;
                orientation = true;
            } else if (o.equals("v")) {
                error = false;
                orientation = false;
            } else {
                IO.println("Falsche Eingabe, bitte wiederholen Sie die Eingabe!");
                error = true;
            }
        } while (error);

        return orientation;
    }

    /**
     * Gibt Liste der Spieler aus
     *
     * @param player Spielerarray
     */
    public static void printListOfPlayers(Player[] player) {
        for (int p = 0; p < player.length; p++) {
            if (player[p].getIsLost() == false) {
                IO.println(player[p].getNumber() + " " + player[p].getName());
            }
        }
    }

    /**
     * Gibt Liste der Spieler aus, die zur Verf�gung stehen ohne des Spielers,
     * der gerade am Zug ist.
     *
     * @param player Spielerarray
     * @param playerN Integerwert des Spielers, der gerade angreift
     */
    public static void printListOfOpponents(Player[] player, int playerN) {
        for (int p = 0; p < player.length; p++) {
            if (player[p].getIsLost() == false) {
                if (player[p] != player[playerN]) {
                    IO.println(player[p].getNumber() + " " + player[p].getName());
                }

            }
        }
    }

    /**
     * Gibt Liste der Schiffe aus, die zur Verf�gung stehen
     *
     * @param player Playerarray
     * @param playerN Index des Spielers in Player-Array
     *
     */
    public static void printListOfShips(Player[] player, int playerN) {
        for (int s = 0; s < player[playerN].getShips().length; s++) {
            if (player[playerN].getShips()[s].getIsSunk() == false) {
                if (player[playerN].getShips()[s].getCurrentReloadTime() == 0) {
                    IO.println("Nummer: " + player[playerN].getShips()[s].getNumber() + " Typ: " + player[playerN].getShips()[s].getName());

                }
            }
        }
    }

    /**
     * Prueft, ob Spieler mindestens ein Schiff hat.
     *
     * @param player Spielerarray
     * @param opponent Nummer des gegnerischen Spielers
     * @return Booleanwert, ob Schiff vorhanden ist
     */
    public static boolean checkIfShipAvailable(Player[] player, int opponent) {
        boolean result = false;
        for (int i = 0; i < player[opponent].getShips().length; i++) {
            if (player[opponent].getShips()[i].getIsSunk() == false) {
                result = true;
            }
        }
        return result;
    }

    /**
     *
     * @param player
     * @return
     */
    public static int checkAmountOfAvailablePlayers(Player[] player) {
        int result = 0;
        for (int i = 0; i < player.length; i++) {
            if (player[i].getIsLost() == false) {
                result++;
            }
        }
        return result;

    }

    public static void printWinner(Player[] player) {
        for (int i = 0; i < player.length; i++) {
            if (player[i].getIsLost() == false) {
                IO.println("Spieler " + player[i].getName() + " hat gewonnen!");
            }
        }
    }

    public static String getKoordinatesToShoot() {
        String koordinate;
        boolean error = false;
        IO.print("Bitte geben Sie die Koordinaten fuer das Schiessen ein:");
        do {
            koordinate = IO.readString().toLowerCase(); //Grossbuchstaben-> Kleinbuchstaben
            if (koordinate.matches("^[1-9]{1}[0-9]{0,1}[a-z]{1}$")) { //Teste Eingabe mit RegEx(^ Anfang, 1 oder 2 Zahlen(0-9) & 1 Buchstabe (a-z), $ Ende
                error = false;
            } else {
                IO.println("Fehler");
                error = true;
            }
        } while (error);
        return koordinate;
    }

    /**
     *
     * @param player Spielerarray
     * @param playerN Spielernummer
     * @return
     */
    public static int getNumberOfOpponent(Player[] player, int playerN) {
        //Abfrage, welcher Spieler angegriffen werden soll
        IO.println("Welchen Spieler moechtest du angreifen?");
        //Gibt die Liste aller Spieler aus, die angegriffen werden koennen
        printListOfOpponents(player, playerN);
        IO.print("Geben Sie die Nummer des Gegners ein: ");
        //Einlesen des SPielers, den man angreifen will
        int opponent = IO.readInt() - 1;
        return opponent;
    }

    public static int getAvailableShipToShoot(Player[] player, int playerN) {
        IO.println("Mit welchem Schiff willst du schiessen?");
        printListOfSunkShips(player, playerN);
        printListOfShips(player, playerN);
        //Einlesen des Schiffs
        boolean error = false;
        int ship;
        //Eingabe, welches Schiff schiessen soll
        IO.println("Gebe die Nummer des Schiffs ein: ");
        /*do{
         ship = IO.readInt();
         if(player[playerN].getShips()[ship-1].getCurrentReloadTime() == 0){
         error = false;
         }else{
         error = true;
         }  
         }while(error);*/
        ship = IO.readInt() - 1;
        IO.println("Sie haben das Schiff mit der Nummer " + player[playerN].getShips()[ship].getNumber() + " mit dem Typ " + player[playerN].getShips()[ship].getName() + " ausgewaehlt!");
        return ship;
    }
    
    public void printListOfWaitingShips(Player[] player, int playerN){
    	int waitingShips = 0;
    	for(int i = 0; i < player.length; i++){
    		if(player[playerN].getShips()[i].getIsSunk() == true){
    			waitingShips++;
    		}
    			
    	}
    	IO.println("Schiffe im Nachladeprozess: " + waitingShips + " Schiffe");
    }
    
    public static void printListOfSunkShips(Player[] player, int playerN){
    	int sunkShips = 0;
    	for(int i = 0; i < player.length; i++){
    		if(player[playerN].getShips()[i].getIsSunk() == true){
    			sunkShips++;
    		}
    			
    	}
    	IO.println("Gesunkene Schiffe: " + sunkShips + " Schiffe");
    }
}
