package no.uib.inf101.sample;

import no.uib.inf101.sample.board.Position;
import no.uib.inf101.sample.model.PlayerModel;
import no.uib.inf101.sample.model.ProjectileModel;
import no.uib.inf101.sample.themes.*;
import no.uib.inf101.sample.utils.InputHandler;
import no.uib.inf101.sample.utils.MenuState;
import no.uib.inf101.sample.utils.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/* TODO this is not used so can be deleted safely for now... */
public class MenuView extends JPanel {

    private final JFrame gameFrame;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private MenuState state = MenuState.LEVEL;
    private int level = 1;


    public MenuView(JFrame gameView) {
        this.gameFrame = gameView;
        this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        JButton button = new JButton("Safe-mode");
        JButton button2 = new JButton("Not-safe-mode");
        JButton button3 = new JButton("Exit");
        button.addActionListener(e -> {
            this.level = 1;
            this.state = MenuState.MODE;
            chooseMode();
        });
        button2.addActionListener(e -> {
            this.level = 2;
            this.state = MenuState.MODE;
            chooseMode();
        });
        button3.addActionListener(e -> this.gameFrame.dispatchEvent(new WindowEvent(this.gameFrame, WindowEvent.WINDOW_CLOSING)));
        button.setBounds(100, 400, 200, 50);
        button2.setBounds(500, 400, 200, 50);
        button3.setBounds(300, 500, 200, 50);
        this.add(button);
        this.add(button2);
        this.add(button3);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }

    public MenuView(JFrame gameView, MenuState state) {
        this.gameFrame = gameView;
        this.state = state;
        this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        JButton button = new JButton("Safe-mode");
        JButton button2 = new JButton("Not-safe-mode");
        JButton button3 = new JButton("Exit");
        button.addActionListener(e -> {
            this.level = 1;
            this.state = MenuState.MODE;
            chooseMode();
        });
        button2.addActionListener(e -> {
            this.level = 2;
            this.state = MenuState.MODE;
            chooseMode();
        });
        button3.addActionListener(e -> this.gameFrame.dispatchEvent(new WindowEvent(this.gameFrame, WindowEvent.WINDOW_CLOSING)));
        button.setBounds(100, 400, 200, 50);
        button2.setBounds(500, 400, 200, 50);
        button3.setBounds(300, 500, 200, 50);
        this.add(button);
        this.add(button2);
        this.add(button3);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (state == MenuState.LEVEL) {
            drawChooseLevel(g2);
        }
        if (state == MenuState.MODE) {
            drawChooseMode(g2);
        }
        if (state == MenuState.THEME) {
            drawChooseTheme(g2);
        }
        if (state == MenuState.OVER) {
            drawGameOver(g2);
        }

    }



    private void chooseMode() {
        this.removeAll();
        JButton button = new JButton("Single-player");
        JButton button2 = new JButton("Two-player");
        JButton button3 = new JButton("Exit");
        button.addActionListener(e -> singlePlayer());
        button2.addActionListener(e -> twoPlayer());
        button3.addActionListener(e -> this.gameFrame.dispatchEvent(new WindowEvent(this.gameFrame, WindowEvent.WINDOW_CLOSING)));
        button.setBounds(100, 400, 200, 50);
        button2.setBounds(500, 400, 200, 50);
        button3.setBounds(300, 500, 200, 50);
        this.add(button);
        this.add(button2);
        this.add(button3);
        this.gameFrame.revalidate();
        this.gameFrame.repaint();
    }

    private void drawChooseLevel(Graphics2D g) {


        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        String scoreString = "CHOOSE GAME MODE";
        int x = g.getFontMetrics().stringWidth(scoreString);
        g.drawString(scoreString, (this.WIDTH / 2) - (x / 2), 200);

        g.setFont(new Font("Arial", Font.BOLD, 25));
        scoreString = "(Do not choose 'Not-safe-mode' if you are easily offended)";
        x = g.getFontMetrics().stringWidth(scoreString);
        g.drawString(scoreString, (this.WIDTH / 2) - (x / 2), 280);

        scoreString = "(Plz, I don't want any trouble)";
        x = g.getFontMetrics().stringWidth(scoreString);
        g.drawString(scoreString, (this.WIDTH / 2) - (x / 2), 310);

        scoreString = "(Seriously)";
        x = g.getFontMetrics().stringWidth(scoreString);
        g.drawString(scoreString, (this.WIDTH / 2) - (x / 2), 340);

        scoreString = "(Sånn faktisk ikke tull engang)";
        x = g.getFontMetrics().stringWidth(scoreString);
        g.drawString(scoreString, (this.WIDTH / 2) - (x / 2), 370);
    }

    private void drawChooseMode(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        String scoreString = "CHOOSE GAME MODE";
        int x = g.getFontMetrics().stringWidth(scoreString);
        g.drawString(scoreString, (this.WIDTH / 2) - (x / 2), 200);
    }

    private void drawChooseTheme(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        String scoreString = "CHOOSE THEME";
        int x = g.getFontMetrics().stringWidth(scoreString);
        g.drawString(scoreString, (this.WIDTH / 2) - (x / 2), 200);
    }

    private void drawGameOver(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        String scoreString = "GAME OVER";
        int x = g.getFontMetrics().stringWidth(scoreString);
        g.drawString(scoreString, (this.WIDTH / 2) - (x / 2), 200);
    }

    private void singlePlayer() {
        this.state = MenuState.THEME;
        chooseTheme(1);
    }

    private void twoPlayer() {
        this.state = MenuState.THEME;
        chooseTheme(2);
    }

    private void chooseTheme(int mode) throws IllegalArgumentException{
        if (mode != 1 && mode != 2) {
            throw new IllegalArgumentException(mode + "is not a valid game mode");
        }
        this.removeAll();


        if(this.level == 2) {
            JButton defaultButton = new JButton("Default");
            defaultButton.setBounds(100, 325, 200, 50);
            this.add(defaultButton);
            defaultButton.addActionListener(e -> initGame(mode, new DefaultTheme()));

            JButton comradeButton = new JButton("Comrade");
            comradeButton.setBounds(500, 325, 200, 50);
            this.add(comradeButton);
            comradeButton.addActionListener(e -> initGame(mode, new ComradeTheme()));

            JButton fatherButton = new JButton("Father");
            fatherButton.setBounds(100, 475, 200, 50);
            this.add(fatherButton);
            fatherButton.addActionListener(e -> initGame(mode, new FatherTheme()));

            JButton impossibleButton = new JButton("Impossible");
            impossibleButton.setBounds(500, 475, 200, 50);
            this.add(impossibleButton);
            impossibleButton.addActionListener(e -> initGame(mode, new ImpossibleTheme()));
        } else if (this.level == 1) {
            JButton defaultButton = new JButton("Default");
            defaultButton.setBounds(300, 400, 200, 50);
            this.add(defaultButton);
            defaultButton.addActionListener(e -> initGame(mode, new DefaultTheme()));
        }

        this.gameFrame.revalidate();
        this.gameFrame.repaint();
    }

    private void initGame(int mode, Theme theme) {

            ProjectileModel proj = new ProjectileModel(new Position(395, 295), 10, 5, new Vector(1, 1));
            PlayerModel player = new PlayerModel(new Position(10, 10), 10, 100);
            PlayerModel player2 = new PlayerModel(new Position(780, 10), 10, 100);

            GameView game = new GameView(gameFrame, player, player2, proj, new InputHandler(), mode, theme);

            this.gameFrame.setContentPane(game);
            game.requestFocus();
            game.startThread();
            this.gameFrame.revalidate();
            this.gameFrame.repaint();
    }

}
