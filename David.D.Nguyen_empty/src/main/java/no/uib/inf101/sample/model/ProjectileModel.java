package no.uib.inf101.sample.model;

import no.uib.inf101.sample.board.Position;
import no.uib.inf101.sample.utils.Vector;

public class ProjectileModel implements Moveable<ProjectileModel>{

    private Position pos;
    private final int radius;
    private final int velocity;
    private final Vector direction;
    private static final int FALLBACK_VELOCITY = 10;
    private static final int FALLBACK_RADIUS = 10;

    public ProjectileModel(Position pos, int radius, int velocity, Vector direction) {
        this.pos = pos;
        this.direction = direction;
        if (radius != 0) {
            this.radius = Math.abs(radius);
        } else {
            this.radius = this.FALLBACK_RADIUS;
        }
        if (velocity != 0) {
            this.velocity = Math.abs(velocity);
        } else {
            this.velocity = this.FALLBACK_VELOCITY;
        }
    }

    @Override
    public Position getPos() {
        return this.pos;
    }

    /**
     * Returns a Vector-object with the directional vectors for the object the method is called on.
     *
     * @return
     */
    public Vector getDirection() {
        return this.direction;
    }

    /**
     * Returns an int with the radius for the object the method is called on.
     *
     * @return
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Returns an int with the velocity multiplier for the object the method is called on.
     *
     * @return
     */
    public int getVelocity() {
        return velocity;
    }

    @Override
    public ProjectileModel move(double delta) throws IllegalArgumentException{
        if (this.getPos().x()+(this.getDirection().getX()*delta) < 0 || this.getPos().y()+(this.getDirection().getY()*delta) < 0) {
            throw new IllegalArgumentException("The move is invalid");
        }
        return new ProjectileModel(new Position(this.getPos().x()+(this.getDirection().getX()*delta), this.getPos().y()+(this.getDirection().getY()*delta)), this.getRadius(), this.velocity, this.getDirection());
    }
}
