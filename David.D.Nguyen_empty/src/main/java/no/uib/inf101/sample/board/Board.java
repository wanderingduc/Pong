package no.uib.inf101.sample.board;

import no.uib.inf101.sample.model.Moveable;
import no.uib.inf101.sample.model.ProjectileModel;

import javax.swing.*;

/* TODO this is not used so can be deleted safely for now... */
public class Board {

    private  int WIDTH = 800;
    private  int HEIGHT = 600;

    private final Moveable playerLeft;
    private final Moveable playerRight;
    private final ProjectileModel projectile;

    public Board(Moveable playerLeft, Moveable playerRight, ProjectileModel projectile) {
        this.playerLeft = playerLeft;
        this.playerRight = playerRight;
        this.projectile = projectile;
    }

    public Board(int width, int height, Moveable playerLeft, Moveable playerRight, ProjectileModel projectile) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.playerLeft = playerLeft;
        this.playerRight = playerRight;
        this.projectile = projectile;
    }

//    public int isColliding() {
//        if (this.projectile.getDirection().getX()>0) {
//            if (this.projectile.getPos().x()+this.projectile.getRadius() >= this.playerRight.getPos().x()) {
//                if (this.projectile.getPos().y()+this.projectile.getRadius() >= this.playerRight.getPos().y() &&
//                        this.projectile.getPos().y()-this.projectile.getRadius() <= this.playerRight.getPos().y()+this.playerRight.getSize()[0]) {
//                    this.projectile.bounceX();
//                }
//            }
//            if (this.projectile.getPos().y()+this.projectile.getRadius() >= this.HEIGHT) {
//                this.projectile.bounceY();
//            }
//        }
//        if (this.projectile.getDirection().getX()<0) {
//            if (this.projectile.getPos().x()-this.projectile.getRadius() <= this.playerLeft.getPos().x()) {
//                if (this.projectile.getPos().y()+this.projectile.getRadius() >= this.playerLeft.getPos().y() &&
//                        this.projectile.getPos().y()-this.projectile.getRadius() <= this.playerLeft.getPos().y()+this.playerLeft.getSize()[0]) {
//                    this.projectile.bounceX();
//                }
//            }
//            if (this.projectile.getPos().y()-this.projectile.getRadius() <= 0) {
//                this.projectile.bounceY();
//            }
//        }
//        return 0;
//    }

}
