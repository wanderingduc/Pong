package no.uib.inf101.sample;

import javax.swing.*;

public class View extends JPanel{

    private final int WIDTH;
    private final int HEIGHT;
    private final JFrame gameFrame;

    public View(JFrame gameFrame, int width, int height) {
        this.WIDTH = 800;
        this.HEIGHT = 600;
        this.gameFrame = gameFrame;
    }
}
