package no.uib.inf101.sample.controller;

import no.uib.inf101.sample.GameView;
import no.uib.inf101.sample.model.PlayerModel;
import no.uib.inf101.sample.model.ProjectileModel;

public class ComputerPlayer {

    private static char relativePosition(PlayerModel player, ProjectileModel projectile) {
        if (projectile.getPos().y() > player.getPos().y()) {
            return 'd';
        }
        if (projectile.getPos().y() < player.getPos().y()) {
            return 'u';
        }
        return ' ';
    }

    /**
     * Return PlayerModel with new position if y-value of PorjectileModel does not overlap with that of PlayerModel.
     * Return PlayerModel-argument if y-value of ProjectileModel overlaps with that of Playermodel.
     *
     * @param player
     * @param projectile
     * @param game
     * @param delta
     * @return
     */
    public static PlayerModel moveToProjectile(PlayerModel player, ProjectileModel projectile, GameView game, double delta) {
        if (relativePosition(player, projectile) == 'd' && player.getPos().y() + delta + player.getSize()[1] <= game.getHEIGHT()) {
            return player.move(delta);
        }
        if (relativePosition(player, projectile) == 'u' && player.getPos().y() + delta >= 0) {
            return player.move(delta*-1);
        }
        return player;
    }

//    public static PlayerModel advanceMoveToPlayer(PlayerModel player, ProjectileModel projectile) {
//
//    }

}
