package no.uib.inf101.sample.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    private boolean pressUpLeft, pressDownLeft, pressUpRight, pressDownRight;

    /**
     * Returns value of pressUpLeft instance-variable.
     *
     * @return
     */
    public boolean getPressUpLeft() {
        return this.pressUpLeft;
    }

    /**
     * Returns value of pressDownLeft instance-variable.
     *
     * @return
     */
    public boolean getPressDownLeft() {
        return this.pressDownLeft;
    }

    /**
     * Returns value of pressUpRight instance-variable.
     *
     * @return
     */
    public boolean getPressUpRight() {
        return this.pressUpRight;
    }

    /**
     * Returns value of pressDownRight instance-variable.
     *
     * @return
     */
    public boolean getPressDownRight() {
        return this.pressDownRight;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_W) {
            pressUpLeft = true;
        }
        if(keyCode == KeyEvent.VK_S) {
            pressDownLeft = true;
        }
        if(keyCode == KeyEvent.VK_UP) {
            pressUpRight = true;
        }
        if(keyCode == KeyEvent.VK_DOWN) {
            pressDownRight = true;
        }
        System.out.println(keyCode);
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_W) {
            pressUpLeft = false;
        }
        if(keyCode == KeyEvent.VK_S) {
            pressDownLeft = false;
        }
        if(keyCode == KeyEvent.VK_UP) {
            pressUpRight = false;
        }
        if(keyCode == KeyEvent.VK_DOWN) {
            pressDownRight = false;
        }
    }
}
