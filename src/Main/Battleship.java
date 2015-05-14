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
        int input = 0;
        int shipX;//x-koodinate
        int shipY;//y-koordinate
        boolean shipO;// Ausrichtung des Schiffes (h,v)
        Ship ships[];
        int amountOfPlayer;
        boolean error = false;

        IO.println("Herzlich Willkommen zu Schiffe versenken!");
        do {
            IO.print("Geben Sie die Anzahl der Spieler ein (2-6): ");
            amountOfPlayer = IO.readInt();
            if (amountOfPlayer < 2 || amountOfPlayer > 6) {
                error = true;
                IO.println("Fehler");
            } else {
                error = false;
            }
        } while (error);
        //Spieler-Array erstellen
        Player[] player = new Player[amountOfPlayer];//Spieleranzahl holen
        for (int i = 0; i < amountOfPlayer; i++) {
            IO.print("Spieler " + (i + 1) + " - Geben Sie ihren Namen ein: ");
            String name = IO.readString();
            player[i] = new Player(i + 1, name);

            for (int p = 0; p < player.length; p++) {
                IO.print("Spieler " + player[p].getName() + " , " + "Sie können nun " + player[p].getShips().length + " Schiffe setzen: " + "\n"
                        + "1 Zerstörer, 1 Fregatte, 2 Corvetten und 2 U-Boote." + "\n");
                ships = player[i].getShips();
                for (int s = 0; s < ships.length;) {
                    IO.print("Bitte geben Sie die X-Koordinate für " + ships[s].getName() + " ein:");
                    shipX = IO.readInt();
                    IO.print("Bitte geben Sie die Y-Koordinate für " + ships[s].getName() + " ein:");
                    shipY = IO.readInt();
                    //IO.print("Bitte geben Sie die Ausrichtung des Schiffes an (h = horizontal, v = vertical: ");
                    shipO = setOrientation();
                    if (!ships[s].placeShip(shipX, shipY, shipO, player[i].getField())) {
                        IO.println("Das Schiff konnte nicht gesetzt werden. Bitte erneut versuchen.");
                    } else {
                        s++;
                    }
                }
            }
        }

        //Runde beginnt
        //solange es mehr als einen spieler gibt
        while (player.length
                < 1) {
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

                        //Hierf𲠮och ne Methode schreiben
                        //Hierfuer noch eine Methode schreiben
                        boolean orientation = false;
                        if (shootRange > 1) {
                            orientation = setOrientation();
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


    /*
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
     *
     * @return Integerwert fuer die Richtung
     */
    public static boolean setOrientation() {
        boolean orientation = false;
        IO.print("Bitte geben Sie die Ausrichtung des Schiffes an (h = horizontal, v = vertical: ");
        String o = IO.readString();
        if (o.equals("h")) {
            orientation = true;
        } else if (o.equals("v")) {
            orientation = false;
        } else {
            setOrientation();
        }

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
            if (player[playerN].getShips()[s].getHitpoints() != 0) {
                if (player[playerN].getShips()[s].getReloadTime() == 0) {
                    IO.println("Nummer: " + player[playerN].getShips()[s].getNumber() + " Typ: " + player[playerN].getShips()[s].getName());

                }
            }
        }
    }

}
