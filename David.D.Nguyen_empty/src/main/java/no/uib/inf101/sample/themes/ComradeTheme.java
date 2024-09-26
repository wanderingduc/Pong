package no.uib.inf101.sample.themes;

import java.awt.*;
import java.io.File;

public class ComradeTheme implements Theme {


    @Override
    public Color getPlayerColor() {
        return Color.YELLOW;
    }

    @Override
    public Color getProjectileColor() {
        return Color.YELLOW;
    }

    @Override
    public String getBackground() {
        return "src/main/resources/comrade_background.png";

    }

    @Override
    public String getSoundtrack() {
        return "src/main/resources/comrade_song.wav";
    }
}
