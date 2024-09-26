package no.uib.inf101.sample.model;

import no.uib.inf101.sample.board.Position;

public  class PlayerModel implements Moveable<PlayerModel>{

    private Position pos;
    private final int WIDTH;
    private final int HEIGHT;


    public PlayerModel(Position pos, int width, int height) {
        this.pos = pos;
        this.WIDTH = Math.abs(width);
        this.HEIGHT = Math.abs(height);
    }

    @Override
    public PlayerModel move(double delta) throws IllegalArgumentException{
        if (this.getPos().y()+delta < 0) {
            throw new IllegalArgumentException("The move is invalid");
        }

        return new PlayerModel(new Position(this.getPos().x(), this.getPos().y()+delta), this.WIDTH, this.HEIGHT);
    }

    @Override
    public Position getPos() {
        return this.pos;
    }

    /**
     * Returns an array of int with the dimensions of the object the method is called on.
     * The first element in the array is width.
     * The second element in the array is height.
     *
     * @return
     */
    public int[] getSize() {
        return new int[]{this.WIDTH, this.HEIGHT};
    }

}
