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
    String letter;
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
//    String alphabet = "#abcdefghijklmnopqrstuvwxyz";
//    String figures = "1";

    //Aufbau des Felds
    public PlayField() {
        this.fieldMatrix = new Field[16][16];
        int z = -1;
        for (int i = 0; i < fieldMatrix.length; i++) {
            if (i >= 1) {
                
               letter = alphabet.substring(z, z+1);

            }
            for (int j = 0; j < fieldMatrix[i].length; j++) {
                if (j >= 1) {
                fieldNumber = Integer.toString(j)+ letter;
                fieldMatrix[i][j] = new Field(fieldNumber);
                    
                }
                fieldMatrix[i][j] = new Field();
                
            }
            z++;
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
            for (int j = 0; j < fieldMatrix[i].length; j++) {
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
