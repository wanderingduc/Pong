package no.uib.inf101.sample.model;

import no.uib.inf101.sample.board.Position;

public interface Moveable<T> {

    /**
     * Takes a double as a parameter to move the object.
     * Returns an object of the same type.
     *
     *
     * @param delta
     * @return
     */
    T move(double delta);


    /**
     * Returns a Position object with the position of the object.
     *
     * @return
     */
    Position getPos();


}
