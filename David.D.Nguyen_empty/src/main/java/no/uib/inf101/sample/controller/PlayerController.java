package no.uib.inf101.sample.controller;

import no.uib.inf101.sample.GameView;
import no.uib.inf101.sample.model.PlayerModel;

public class PlayerController {

    /**
     * Return move-method of the PlayerModel if new position is valid.
     * Return PlayerModel-argument if new position is invalid.
     *
     * @param player
     * @param delta
     * @param game
     * @return
     */
    public static PlayerModel movePlayer(PlayerModel player, double delta, GameView game) {
        if (player.getPos().y() + delta >= 0 && player.getPos().y() + delta + player.getSize()[1] <= game.getHEIGHT()) {
            return player.move(delta);
        }
        return player;
    }

}
