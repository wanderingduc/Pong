package no.uib.inf101.sample;

import no.uib.inf101.sample.board.Position;
import no.uib.inf101.sample.model.PlayerModel;
import no.uib.inf101.sample.model.ProjectileModel;
import no.uib.inf101.sample.themes.*;
import no.uib.inf101.sample.utils.InputHandler;
import no.uib.inf101.sample.utils.Vector;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * Hello world!
 */
public class Main {

  public static void main(String[] args) {

    /* TODO THIS IS FUCKING CORRECT. DON'T FUCKING DELETE THIS */

    JFrame window = new JFrame();
    MenuView menu = new MenuView(window);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setContentPane(menu);
    window.pack();
    window.setVisible(true);



    /* TODO THIS WORKS BUT ABOVE IS BETTER */


//    Scanner obj = new Scanner(System.in);
//    System.out.println("Choose game mode: 1 = Single player | 2 = Two player");
//    String gameMode = obj.nextLine();
//    System.out.println("Choose theme: D = Default | C = Comrade | F = Father | I = Impossible");
//    String theme = obj.nextLine();
//
//    Theme chosenTheme = switch(theme) {
//      case "D":
//        yield new DefaultTheme();
//      case "C":
//        yield new ComradeTheme();
//      case "F":
//        yield new FatherTheme();
//      case "I":
//        yield new ImpossibleTheme();
//      default:
//        yield new DefaultTheme();
//    };

//    ProjectileModel proj = new ProjectileModel(new Position(20,140), 10, 5, new Vector(1, 1));
//    PlayerModel player = new PlayerModel(new Position(10, 10), 10, 100);
//    PlayerModel player2 = new PlayerModel(new Position(780, 10), 10, 100);
//
//    GameView game = new GameView(800, 600, player, player2, proj, new InputHandler(), 1, new ComradeTheme());
//
//    JFrame frame = new JFrame("something");
////    frame.setBackground(Color.blue);
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////    frame.setContentPane(new GameView(400, 300, 10));
//    frame.setContentPane(game);
//    frame.pack();
//    System.out.println(frame.getFocusOwner());
//    frame.setVisible(true);
//    game.requestFocus();
//    game.startThread();
  }
}
