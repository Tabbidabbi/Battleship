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
    int amountOfPlayer;
    String amountOfAllShips;
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
     public String setAmountOfAllShips() {
         error = false;
         do {
             IO.print("Wieviele Schiffe dürfen insgesammt gesetzt werden?");
             amountOfAllShips = IO.readString();
             if (amountOfAllShips.matches("^[1-9]{1}[0-9]{0,1}$")) {
                 error = false;
             }else {
                 IO.println("Falsche Eingabe!");
                 error = true;
             }
             
         } while(error);
         
         return amountOfAllShips;
     }
//    
//     public int setAmountOfDestroyer() {
//         do {
//             IO.print("Wieviele Zerstoerer dürfen in dem Spiel gesetzt werden?");
//             amountOfDestroyer = IO.readInt();
//             if (amountOfDestroyer("^[1-9]{1}[0-9]{1}[a-z]{1}$")) {
//                    error = false;
//                } else {
//                    IO.println("Falsche Eingabe, bitte geben sie zuerst die Nummer und dann den Buchstaben des Feldes ein: ");
//                    error = true;
//                }
//         } while (error);
//     }
}
