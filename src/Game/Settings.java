/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import IO.IO;

/**
 *
 * @author Tobias
 */
public class Settings {
    
    boolean error = false;
    int sum;
    int amountOfPlayer;
    int amountOfAllShips;
    int amountOfDestroyer;
    int amountOfFrigate;
    int amountOfCorvette;
    int amountOfSubmarine;

    public int setAmountOfPlayer() {
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
        } while (error);//Fuehre die Schleife aus, solange error = true ist.
        return amountOfPlayer;

    }

    public void getAmountOfAllShips() {
        amountOfAllShips = amountOfDestroyer + amountOfCorvette + amountOfFrigate + amountOfSubmarine;
        System.out.println(amountOfAllShips);
    }

    public int setAmountOfDestroyer() {
        error = false;
        do {
            IO.print("Wieviele Zerstoerer dürfen insgesammt gesetzt werden?");
            amountOfDestroyer = IO.readInt();
            String s = Integer.toString(amountOfDestroyer);
            if (s.matches("^[1-9]{1}[0-9]{0,1}$")) {
                error = false;
            } else {
                IO.println("Falsche Eingabe!");
                error = true;
            }

        } while (error);

        return amountOfDestroyer;
    }
    public int setAmountOfFrigate() {
        error = false;
        do {
            IO.print("Wieviele Fregatten dürfen insgesammt gesetzt werden?");
            amountOfFrigate = IO.readInt();
            String s = Integer.toString(amountOfFrigate);
            if (s.matches("^[1-9]{1}[0-9]{0,1}$")) {
                error = false;
            } else {
                IO.println("Falsche Eingabe!");
                error = true;
            }

        } while (error);

        return amountOfFrigate;
    }
    public int setAmountOfCorvette() {
        error = false;
        do {
            IO.print("Wieviele Korvetten dürfen insgesammt gesetzt werden?");
            amountOfCorvette = IO.readInt();
            String s = Integer.toString(amountOfCorvette);
            if (s.matches("^[1-9]{1}[0-9]{0,1}$")) {
                error = false;
            } else {
                IO.println("Falsche Eingabe!");
                error = true;
            }

        } while (error);

        return amountOfCorvette;
    }
    public int setAmountOfSubmarine() {
        error = false;
        do {
            IO.print("Wieviele U-Boote dürfen insgesammt gesetzt werden?");
            amountOfSubmarine = IO.readInt();
            String s = Integer.toString(amountOfSubmarine);
            if (s.matches("^[1-9]{1}[0-9]{0,1}$")) {
                error = false;
            } else {
                IO.println("Falsche Eingabe!");
                error = true;
            }

        } while (error);

        return amountOfSubmarine;
    }
}
