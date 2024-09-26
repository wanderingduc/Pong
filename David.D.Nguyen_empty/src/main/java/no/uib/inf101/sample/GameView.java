package no.uib.inf101.sample;

import no.uib.inf101.sample.board.Position;
import no.uib.inf101.sample.controller.ComputerPlayer;
import no.uib.inf101.sample.controller.PlayerController;
import no.uib.inf101.sample.controller.ProjectileController;
import no.uib.inf101.sample.model.PlayerModel;
import no.uib.inf101.sample.model.ProjectileModel;
import no.uib.inf101.sample.themes.DefaultTheme;
import no.uib.inf101.sample.themes.Theme;
import no.uib.inf101.sample.utils.InputHandler;
import no.uib.inf101.sample.utils.MenuState;
import no.uib.inf101.sample.utils.Vector;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameView extends JPanel implements Runnable{

    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private PlayerModel leftPlayer;
    private  PlayerModel rightPlayer;
    private ProjectileModel projectile;
    private final ProjectileController projController;
    private final InputHandler inputH;
    private final int delta = 5;

    private final int players;

    private int leftPlayerScore = 0;
    private int rightPlayerScore = 0;

    private final Theme colorTheme;
    private Thread gameThread;
    private Thread soundThread;
    private Clip clip;
    private final int FPS = 60;
    private final JFrame gameFrame;

    public GameView(JFrame gameFrame, PlayerModel leftPlayer, PlayerModel rightPlayer, ProjectileModel projectile, InputHandler inputH, int players) {
        this.gameFrame = gameFrame;
        this.inputH = inputH;
        this.leftPlayer = leftPlayer;
        this.rightPlayer = rightPlayer;
        this.projectile = projectile;
        this.projController = new ProjectileController(this.projectile);
        this.players = players;
        this.colorTheme = new DefaultTheme();
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        this.addKeyListener(inputH);
        this.setFocusable(true);
    }

    public GameView(JFrame gameFrame, PlayerModel leftPlayer, PlayerModel rightPlayer, ProjectileModel projectile, InputHandler inputH, int players, Theme colorTheme) {
        this.gameFrame = gameFrame;
        this.inputH = inputH;
        this.leftPlayer = leftPlayer;
        this.rightPlayer = rightPlayer;
        this.projectile = projectile;
        this.projController = new ProjectileController(this.projectile);
        this.players = players;
        this.colorTheme = colorTheme;
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        this.addKeyListener(inputH);
        this.setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 =(Graphics2D) g;
        try {
            drawBackground(g2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        drawProjectile(g2);
        drawPlayer(g2);
        drawScore(g2);
    }

    private void drawBackground(Graphics2D g) throws IOException {
        BufferedImage image;
        image = ImageIO.read(new File(colorTheme.getBackground()));

        g.drawImage(image, 0, 0, getWIDTH(), getHEIGHT(),  this);
    }



    private void drawPlayer(Graphics2D g) {
        Rectangle2D player = new Rectangle2D.Double(this.leftPlayer.getPos().x(), this.leftPlayer.getPos().y(), this.leftPlayer.getSize()[0], this.leftPlayer.getSize()[1]);
        Rectangle2D player2 = new Rectangle2D.Double(this.rightPlayer.getPos().x(), this.rightPlayer.getPos().y(), this.rightPlayer.getSize()[0], this.rightPlayer.getSize()[1]);
        g.setColor(colorTheme.getPlayerColor());
        g.fill(player);
        g.fill(player2);
    }

    private void drawProjectile(Graphics2D g) throws IllegalArgumentException{
        if(isCollidingWith(this.projectile.move(this.delta)) != "") {
            throw new IllegalArgumentException("The next position is invalid");
        }
        Ellipse2D proj = new Ellipse2D.Double(this.projectile.getPos().x()+this.projectile.getDirection().getX(), this.projectile.getPos().y()+this.projectile.getDirection().getY(), this.projectile.getRadius(), this.projectile.getRadius());

        g.setColor(colorTheme.getProjectileColor());
        g.fill(proj);
    }

    private void drawScore(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String scoreString = this.leftPlayerScore + " : " + this.rightPlayerScore;
        int x = g.getFontMetrics().stringWidth(scoreString);
        g.drawString(scoreString, (this.WIDTH/2)-(x/2), 30);
    }

    private void initGameOver() {
        this.gameThread = null;
        MenuView overMenu = new MenuView(gameFrame, MenuState.OVER);
        this.gameFrame.setContentPane(overMenu);
        overMenu.requestFocus();
        this.gameFrame.revalidate();
        this.gameFrame.repaint();
        this.soundThread = null;
        this.clip.stop();
    }

    /**
     * Assigns a new Thread-object to gameThread instance-variable and calls the start()-method to start the game-thread.
     *
     */
    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
        if (colorTheme.getSoundtrack() != null && gameThread != null) {
            soundThread = new Thread(new Runnable() {
                @Override
                public void run() { // Taken from stackoverflow. Source: https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java , 25/04-2024
                    try {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(colorTheme.getSoundtrack()));
                        clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                    } catch (Exception ex) {
                        System.out.println("Error with playing sound.");
                        ex.printStackTrace();
                    }
                }
            });
            soundThread.start();
        }
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;



        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;


            if (delta >= 1) {
                switch (isCollidingWith(this.projectile)) {
                    case "y":
                        this.projectile = bounceY(this.projectile);
                        break;
                    case "xl":
                        this.projectile = bounceX(this.projectile, this.leftPlayer);
                        break;
                    case "xr":
                        this.projectile = bounceX(this.projectile, this.rightPlayer);
                        break;
                    case "ol":
                        this.projectile = new ProjectileModel(new Position((this.WIDTH/2)-this.projectile.getRadius(), (this.HEIGHT/2)-this.projectile.getRadius()), 10, this.projectile.getVelocity(), new Vector(this.projectile.getDirection().getX()*-1, this.projectile.getDirection().getY()));
                        this.rightPlayerScore ++;
                        break;
                    case "or":
                        this.projectile = new ProjectileModel(new Position((this.WIDTH/2)-this.projectile.getRadius(), (this.HEIGHT/2)-this.projectile.getRadius()), 10, this.projectile.getVelocity(), new Vector(this.projectile.getDirection().getX()*-1, this.projectile.getDirection().getY()*-1));
                        this.leftPlayerScore ++;
                        break;
                    default:
                        break;
                };

                this.projectile = this.projectile.move(this.projectile.getVelocity());

                if (this.players == 1) {
                    this.leftPlayer = ComputerPlayer.moveToProjectile(this.leftPlayer, this.projectile, this, this.delta);
                }

                if (this.players == 2) {
                    if (this.inputH.getPressUpLeft() == true) {
                        this.leftPlayer = movePlayer(this.leftPlayer, this.delta * -1);
                    }
                    if (this.inputH.getPressDownLeft() == true) {
                        this.leftPlayer = movePlayer(this.leftPlayer, this.delta);
                    }
                }

                if (this.inputH.getPressUpRight() == true) {
                    this.rightPlayer = movePlayer(this.rightPlayer, this.delta*-1);
                }
                if (this.inputH.getPressDownRight() == true) {
                    this.rightPlayer = movePlayer(this.rightPlayer, this.delta);
                }
                repaint();
                delta --;
            }
            if (this.leftPlayerScore >= 10 || this.rightPlayerScore >= 10) {
                initGameOver();
            }

        }

    }

    /**
     * Calls the movePlayer method of PlayerController-class on player.
     *
     * @param player
     * @param delta
     * @return
     */
    public PlayerModel movePlayer(PlayerModel player, int delta) {

        return PlayerController.movePlayer(player, delta, this);
    }

    private String isCollidingWith(ProjectileModel model) {
        if (model.getPos().y()+model.getRadius() >= this.HEIGHT || model.getPos().y() <= 0) {
            return "y";
        }
        if (model.getPos().x()+model.getRadius() >= this.WIDTH) {
            return "or";
        }
        if (model.getPos().x() <= 0) {
            return "ol";
        }
        if (model.getPos().y()+model.getRadius() >= this.leftPlayer.getPos().y() && model.getPos().y() <= this.leftPlayer.getPos().y()+this.leftPlayer.getSize()[1]) {
            if (model.getPos().x() <= this.leftPlayer.getPos().x() + this.leftPlayer.getSize()[0]) {
                return "xl";
            }
        }
        if (model.getPos().y()+model.getRadius() >= this.rightPlayer.getPos().y() && model.getPos().y() <= this.rightPlayer.getPos().y()+this.rightPlayer.getSize()[1]) {
            if (model.getPos().x()+model.getRadius() >= this.rightPlayer.getPos().x()) {
                return "xr";
            }
        }
        return "";
    }

    private ProjectileModel bounceY(ProjectileModel projectile) {
        return new ProjectileModel(projectile.getPos(), projectile.getRadius(), projectile.getVelocity(), new Vector(projectile.getDirection().getX(), projectile.getDirection().getY()*-1));
    }

    private ProjectileModel bounceX(ProjectileModel projectile, PlayerModel player) {

        /* USE WITH NON-NORMALIZED VECTORS. NO PERFORMANCE ISSUES, BUT GAME IS BORING */

        return new ProjectileModel(projectile.getPos(), projectile.getRadius(), projectile.getVelocity(), new Vector(projectile.getDirection().getX()*-1, projectile.getDirection().getY()));


        /* USE WITH NORMALIZED VECTORS. VERY LARGE PERFORMANCE ISSUES */

//        double projectilePos = projectile.getPos().y();
//        double playerPos = player.getPos().y();
//        double playerFifth = player.getSize()[1]/5;
//        boolean colliding = (projectilePos+projectile.getRadius()>=playerPos && projectilePos-projectile.getRadius()<=playerPos+player.getSize()[1]);
//        boolean innerCollision = (projectilePos+projectile.getRadius()<= playerPos+(playerFifth*3) && projectilePos-projectile.getRadius()>= playerPos+(playerFifth*2));
//        boolean middleCollision = (projectilePos+projectile.getRadius() < playerPos+(playerFifth*2) && projectilePos-projectile.getRadius() >= playerPos+playerFifth) || (projectilePos-projectile.getRadius() > playerPos+(playerFifth*3) && projectilePos-projectile.getRadius() <= playerPos+(playerFifth*4));
//        boolean outerCollision = (projectilePos+projectile.getRadius() < playerPos+playerFifth) || (projectilePos-projectile.getRadius() > playerPos+(playerFifth*4));
//
//        if (projectile.getDirection().getY() > 0) {
//            if (outerCollision) {
//                return new  ProjectileModel(projectile.getPos(), projectile.getRadius(), projectile.getVelocity(), new Vector(projectile.getDirection().getX()*-1, projectile.getDirection().getY()+1));
//            }
//            if (middleCollision) {
//                return new  ProjectileModel(projectile.getPos(), projectile.getRadius(), projectile.getVelocity(), new Vector(projectile.getDirection().getX()*-1, projectile.getDirection().getY()));
//            }
//            if (innerCollision) {
//                return new  ProjectileModel(projectile.getPos(), projectile.getRadius(), projectile.getVelocity(), new Vector(projectile.getDirection().getX()*-1, 0.5));
//            }
//        }
//        if (projectile.getDirection().getY() < 0) {
//            if (outerCollision) {
//                return new  ProjectileModel(projectile.getPos(), projectile.getRadius(), projectile.getVelocity(), new Vector(projectile.getDirection().getX()*-1, projectile.getDirection().getY()-1));
//            }
//            if (middleCollision) {
//                return new  ProjectileModel(projectile.getPos(), projectile.getRadius(), projectile.getVelocity(), new Vector(projectile.getDirection().getX()*-1, projectile.getDirection().getY()));
//            }
//            if (innerCollision) {
//                return new  ProjectileModel(projectile.getPos(), projectile.getRadius(), projectile.getVelocity(), new Vector(projectile.getDirection().getX()*-1, -0.5));
//            }
//        }
//        return new ProjectileModel(projectile.getPos(), projectile.getRadius(), projectile.getVelocity(), new Vector(projectile.getDirection().getX()*-1, projectile.getDirection().getY()));
    }

    /**
     * Returns PlayerModel-object assigned to leftPlayer instance-variable.
     *
     * @return
     */
    public PlayerModel getLeftPlayer() {
        return this.leftPlayer;
    }

    /**
     * Returns PlayerModel-object assigned to rightPlayer instance-variable.
     *
     * @return
     */
    public PlayerModel getRightPlayer() {
        return this.rightPlayer;
    }

    /**
     * Returns height of view.
     *
     * @return
     */
    public int getHEIGHT() {
        return this.HEIGHT;
    }

    /**
     * Returns width of view.
     *
     * @return
     */
    public int getWIDTH() {
        return this.WIDTH;
    }
}
