package no.uib.inf101.sample.themes;

import java.awt.*;

public interface Theme {

    /**
     * Returns the color of the player.
     *
     * @return
     */
    Color getPlayerColor();

    /**
     * Returns the color of the projectile.
     *
     * @return
     */
    Color getProjectileColor();

    /**
     * Returns the path to the background-image.
     *
     * @return
     */
    String getBackground();

    /**
     * Returns the path to the soundtrack. Returns null if theme doesn't have soundtrack.
     *
     * @return
     */
    String getSoundtrack();

}
