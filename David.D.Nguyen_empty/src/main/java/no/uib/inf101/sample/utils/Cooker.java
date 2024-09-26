package no.uib.inf101.sample.utils;

public class Cooker {

    /**
     * Returns the inverse square root of a double.(Kinda)
     *
     * @param x
     * @return
     */
    public static double fastInverseSquareRoot(double x) { // Modified version of fast inverse square root algorithm from Quake III. Copied and modified from; source: https://stackoverflow.com/questions/11513344/how-to-implement-the-fast-inverse-square-root-in-java , 25/04-2024
        if (x >= 0) {
            double half = 0.5 * x;
            long i = Double.doubleToLongBits(x);
            i = 0x5fe6ec85e7de30daL - (i >> 1);
            x = Double.longBitsToDouble(i);
            x *= (1.5 - half * x * x);
            return x;
        }
        if (x < 0) {
            x = Math.abs(x);
            double half = 0.5 * x;
            long i = Double.doubleToLongBits(x);
            i = 0x5fe6ec85e7de30daL - (i >> 1);
            x = Double.longBitsToDouble(i);
            x *= (1.5 - half * x * x);
            return x*-1;
        }
        throw new IllegalArgumentException("The fucking number doesn't fucking exist");
    }

}
