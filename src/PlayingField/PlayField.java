package PlayingField;

import IO.IO;

/**
 * 
 * @author Dennis
 *
 */

public class PlayField {
	private Field[][] fieldMatrix;

	public PlayField(){
		this.fieldMatrix = new Field[12][12];
		for(int i = 0; i < fieldMatrix.length; i++){
			for(int j = 0; j < fieldMatrix[i].length; j++){
				fieldMatrix[i][j] = new Field();
			}	
		}
	}
	
	public Field[][] getPlayField() {
		return fieldMatrix;
	}

	public void setField(Field[][] playField) {
		this.fieldMatrix = playField;
	}
	
	public void setShot(int x, int y, int shootRange, boolean orientation){
		if(orientation == true){
			for(int i = 0; i < shootRange; i++){
				this.fieldMatrix[x + i][y].setIsShot();
			}
		}
		else{
			for(int i = 0; i < shootRange; i++){
				this.fieldMatrix[x][y + i].setIsShot();
				
			}
			
		}
	}
	
	public void printPlayField(){
		for(int i = 0; i < fieldMatrix.length; i++){
			for(int j = 0; j < fieldMatrix[i].length; j++){
				fieldMatrix[i][j].print();
			}			
			IO.println("");
		}			
	}
	
	public void printOpponentField(){
		for(int i = 0; i < fieldMatrix.length; i++){
			for(int j = 0; j < fieldMatrix[i].length; j++){
				fieldMatrix[i][j].print();
			}			
			IO.println("");
		}
	}
}


