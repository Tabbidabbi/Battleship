package PlayingField;

import java.io.Serializable;

import IO.IO;
import PlayingField.Field;

/**
 *
 * @author Dennis
 *
 */
public class PlayField implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5797716332246340540L;
	private Field[][] fieldMatrix;
    private String fieldNumber;
    private int fieldFigure;
    char letter;
    private final String alphabet = "#abcdefghijklmnopqrstuvwxyz";
    int zI = 0;
    int zJ;
    String convertInString;

    //Aufbau des Felds.
//    Jedes Feld bekommt eine Nummerierung
    public PlayField() {
        this.fieldMatrix = new Field[16][16];
//        Hier wird jeder Zeile ein Buchstabe aus dem "alphabet" String hinzugefügt bis die Länge des Arrays erreicht wurde.
        for (int i = 0; i < fieldMatrix.length; i++) {
            if (i >= 1 && zI <= fieldMatrix.length) {
                convertInString = Integer.toString(zI);//in der Variable wird der vorher in String konvertierte int gespeichert.
            }
            zJ = 0;//Zähler für die Spalten wird resetet
//        Hier wird jeder Spalte eine Zahl  hinzugefügt bis die Länge des Arrays erreicht wurde
            for (int j = 0; j < fieldMatrix[i].length; j++) {
                if (j > 0 && i > 0) {
                    letter = alphabet.charAt(zJ);//In Letter wird pro durchlauf je 1 char gespeichert welcher im alphabet String steht.
                }
                zJ++;
                fieldNumber = Integer.toString(zI) + letter; // Die Nummer des Feldes wird zusammengebaut
                fieldMatrix[i][j] = new Field();
                fieldMatrix[i][j].setFieldnumber(fieldNumber);
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

    /**
     *
     * @param x
     * @param y
     * @param shootRange
     * @param orientation
     * @return
     */
    public int[] setShot(int x, int y, int shootRange, boolean orientation) {
        //Array, in dem  die getroffenen Schiffe stehen
        int[] hitShips = new int[shootRange];
        if (orientation == true) {
            for (int i = 0; i < shootRange; i++) {
                hitShips[i] = this.fieldMatrix[y][x + i].setIsShot();
            }
        } else {
            for (int i = 0; i < shootRange; i++) {
                hitShips[i] = this.fieldMatrix[y + i][x].setIsShot();

            }

        }
        return hitShips;
    }

    public int[] setShot(String koordinate, int shootRange, boolean orientation) {
        //Array, in dem  die getroffenen Schiffe stehen
        int[] hitShips = new int[shootRange];
        if (orientation == true) {
            for (int y = 0; y < getPlayField().length; y++) {
                for (int x = 0; x < getPlayField()[y].length; x++) {
                    if (koordinate.equals(getPlayField()[y][x].getFieldnumber())) {
                        for (int i = 0; i < shootRange; i++) {
                            hitShips[i] = this.fieldMatrix[y][x + i].setIsShot();
                        }
                    }
                }
            }

        } else {
            for (int y = 0; y < getPlayField().length; y++) {
                for (int x = 0; x < getPlayField()[y].length; x++) {
                    if (koordinate.equals(getPlayField()[y][x].getFieldnumber())) {
                        for (int i = 0; i < shootRange; i++) {
                            hitShips[i] = this.fieldMatrix[y + i][x].setIsShot();
                        }
                    }
                }
            }
        }
        return hitShips;
    }

    //Wie sieht das PlayField aus
    public void printPlayField() {
        for (int i = 0; i < fieldMatrix.length; i++) {
            //Das Array wird auf der Position [0][0] vertikal mit Zahlen gefüllt2
            if (i + 1 < fieldMatrix.length) {
                fieldMatrix[i + 1][0].setStatus(Integer.toString(i + 1));
                fieldMatrix[i][0].setActive(false);
            }
            for (int j = 0; j < fieldMatrix[i].length; j++) {
                //Das Array wird auf der Position [0][0] horizontal mit Buchstaben gefüllt
                if (j < fieldMatrix[i].length) {
                    fieldMatrix[0][j].setStatus(Character.toString(alphabet.charAt(j)));
                    fieldMatrix[0][j].setActive(false);
                }
                fieldMatrix[i][j].print();
            }
            IO.println("");
        }
        IO.println("");
    }

    //Wie sieht das OpponentField aus
    public void printOpponentField() {
        for (int i = 0; i < fieldMatrix.length; i++) {
            if (i + 1 < fieldMatrix.length) {
                fieldMatrix[i + 1][0].setOpponentStatus(Integer.toString(i + 1));
                fieldMatrix[i][0].setActive(false);
            }
            for (int j = 0; j < fieldMatrix[i].length; j++) {
                if (j < fieldMatrix[i].length) {
                    fieldMatrix[0][j].setOpponentStatus(Character.toString(alphabet.charAt(j)));
                    fieldMatrix[0][j].setActive(false);
                }
                fieldMatrix[i][j].printForOpponent();
            }
            IO.println("");
        }
        IO.println("");
    }
}
