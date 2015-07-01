package Main;

import Game.Game;
import Player.Player;
import SaveLoad.SaveLoad;
import Ships.Ship;
import IO.IO;

/**
 *
 * @author Dennis
 *
 */
public class Battleship {

    public static void main(String[] args) {
    	Game game;
        IO.println("Wollen sie das Spiel laden? (ja/nein)");
        String wantLoad = IO.readString();
        boolean answer;
        if(wantLoad.equals("ja")){
        	game = SaveLoad.load();
        	
        }
        else{
        	game = new Game();
            game.initializePlayer();
        }
    	
        SaveLoad.save(game);
        game.playRounds();
    }
}
