package fr.mowerandco.core.plan;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Classe immutable.
 * Un point sur une carte.
 */
public final class Point {

    /**
     * Abscisse
     */
    private final int x;

    /**
     * Ordonnee
     */
    private final int y;


    /**
     * Initialise
     * @param x abscisse
     * @param y ordonnee
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point that = (Point) o;
        return Objects.equal(this.x, that.x) &&
                Objects.equal(this.y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x, y);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("x", x).add("y", y).toString();
    }

    /**
     * On translate le point.
     * (Immutable).
     * @param x abscisse
     * @param y ordonnee
     * @return le point translate
     */
    public Point translate(int x, int y) {
        return new Point(this.x + x, this.y + y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
