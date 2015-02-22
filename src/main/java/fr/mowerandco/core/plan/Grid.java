package fr.mowerandco.core.plan;

import com.google.common.base.Preconditions;

/**
 * Une grille avec une limite.
 */
public class Grid {

    /** La taille min*/
    private static final int MIN_SIZE = 0;
    /** Largeur de la grille. */
    private final int width;
    /** Hauteur de la grille */
    private final int height;


    /**
     * Initialise la taille de la grille.
     * La hauteur et largeur doit etre positives.
     *
     * @param width  la largeur
     * @param height la hauteur
     */
    public Grid(final int width, final int height) {
        Preconditions.checkArgument(width > 0 && height > 0, String.format("Argument must be not negative %d %d", width, height));
        this.width = width;
        this.height = height;
    }


    /**
     * On verifie si le point est dans la grille.
     *
     * @param point le point a verifie
     * @return true on est dedans, false non.
     */
    public boolean isIn(Point point) {
        Preconditions.checkNotNull(point);
        return isBetween(point.getX(), width) &&
                isBetween(point.getY(), height);
    }

    /**
     * On verifie si la valeur est comprise entre min et max.
     *
     * @param value la valeur a verifier
     * @param max   la max valeur
     * @return true on est entre les deux, false non.s
     */
    private boolean isBetween(int value, int max) {
        return value >= MIN_SIZE && value <= max;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
