package no.uib.inf101.sample.themes;

import java.awt.*;

public class ImpossibleTheme implements Theme {
    @Override
    public Color getPlayerColor() {
        return Color.getHSBColor(64, 0, 0.85f);
    }

    @Override
    public Color getProjectileColor() {
        return Color.BLACK;
    }

    @Override
    public String getBackground() {
        return "src/main/resources/default_background.png";
    }

    @Override
    public String getSoundtrack() {
        return null;
    }
}
