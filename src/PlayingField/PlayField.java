package PlayingField;

import IO.IO;

/**
 *
 * @author Dennis
 *
 */
public class PlayField {

    private Field[][] fieldMatrix;
    private String fieldNumber;
    private int fieldFigure;
    char letter;
    private String alphabet = "#abcdefghijklmnopqrstuvwxyz";
    int zI = 0;
    int zJ;
    String getToString;

    //Aufbau des Felds.
//    Jedes Feld bekommt eine Nummerierung
    public PlayField() {
        this.fieldMatrix = new Field[16][16];
//        Hier wird jeder Zeile ein Buchstabe aus dem alphabet String hinzugefügt bis die Länge des Arrays erreicht wurde
        for (int i = 0; i < fieldMatrix.length; i++) {
            if (i >= 1 && zI <= fieldMatrix.length) {
                letter = alphabet.charAt(zI);//In Letter wird pro durchlauf je 1 char gespeichert welcher im alphabet String steht.
            }
            zJ = 0;//Zähler für die Spalten wird resetet
//        Hier wird jeder Spalte eine Zahl  hinzugefügt bis die Länge des Arrays erreicht wurde
            for (int j = 0; j < fieldMatrix[i].length; j++) {
                if (j > 0 && i > 0) {
                    getToString = Integer.toString(zJ);//in der Variable wird der vorher in String konvertierte int gespeichert.
                }
                zJ++;
                fieldNumber = Integer.toString(zJ) + letter; // Die Nummer des Feldes wird zusammengebaut
                fieldMatrix[i][j] = new Field(fieldNumber);// DDie Nummer des Feldes wird übergeben an den Field konstruktor.

            }
            zI++;//Zähler für die characters

        }
    }

    public String getFieldNumber() {
        return fieldNumber;
    }

    public Field[][] getPlayField() {
        return fieldMatrix;
    }

    public void setFieldNumber(String fieldNumber) {
        this.fieldNumber = fieldNumber;
    }

    public void setField(Field[][] playField) {
        this.fieldMatrix = playField;
    }

    public void setShot(int x, int y, int shootRange, boolean orientation) {
        if (orientation == true) {
            for (int i = 0; i < shootRange; i++) {
                this.fieldMatrix[x + i][y].setIsShot();
            }
        } else {
            for (int i = 0; i < shootRange; i++) {
                this.fieldMatrix[x][y + i].setIsShot();

            }

        }
    }

    //Wie sieht das PlayField aus
    public void printPlayField() {
        for (int i = 0; i < fieldMatrix.length; i++) {
    //Das Array wird auf der Position [0][0] vertikal mit Zahlen gefüllt
            if (i + 1 < fieldMatrix.length) {
                    fieldMatrix[i + 1][0].setStatus("" + (i+1));
                }
            for (int j = 0; j < fieldMatrix[i].length; j++) {
    //Das Array wird auf der Position [0][0] horizontal mit Buchstaben gefüllt
                if (j < fieldMatrix[i].length) {
                    fieldMatrix[0][j].setStatus("" + alphabet.charAt(j));
                }
                fieldMatrix[i][j].print();
            }
            IO.println("");
        }
    }

    //Wie sieht das OpponentField aus
    public void printOpponentField() {
        for (int i = 0; i < fieldMatrix.length; i++) {
            for (int j = 0; j < fieldMatrix[i].length; j++) {
                fieldMatrix[i][j].print();
            }
            IO.println("");
        }
    }
}
