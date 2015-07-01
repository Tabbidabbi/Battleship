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
        Game game = new Game();
        game.initializePlayer();
        SaveLoad.save(game);
        game.playRounds();
    }
}
