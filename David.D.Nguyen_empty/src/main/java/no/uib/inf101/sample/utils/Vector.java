package no.uib.inf101.sample.utils;

public final class Vector {

    private final double x;
    private final double y;

    public Vector(double x, double y) {
        /* NORMALIZED VECTORS */
//        this.x = Cooker.fastInverseSquareRoot(y);
//        this.y = Cooker.fastInverseSquareRoot(x);

        /* NON-NORMALIZED VECTORS */
        this.x = x;
        this.y = y;
    }


    /**
     * Returns the x-value of the Vector-object.
     *
     * @return
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-value of the Vector-object.
     *
     * @return
     */
    public double getY() {
        return this.y;
    }
}
