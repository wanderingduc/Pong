package no.uib.inf101.sample.themes;

import java.awt.*;
import java.io.File;

public class FatherTheme implements Theme{
    @Override
    public Color getPlayerColor() {
        return Color.RED;
    }

    @Override
    public Color getProjectileColor() {
        return Color.BLUE;
    }

    @Override
    public String getBackground() {
        return "src/main/resources/father_background.png";

    }

    @Override
    public String getSoundtrack() {
        return "src/main/resources/father_song.wav";
    }
}
