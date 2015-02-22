package fr.mowerandco.core;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import fr.mowerandco.core.plan.Grid;
import fr.mowerandco.core.plan.Point;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Le terrain avec les tondeuses.
 */
public class Lawn extends Grid {

    /** Deux tondeuses ne peuvent etre sur la meme grille. */
    private Map<Point, Mower> mowers = new HashMap<>();

    /**
     * Initialisation du terrain.
     *
     * @param width  la largeur du terrain
     * @param height la hauteur du terrain
     */
    public Lawn(int width, int height) {
        super(width, height);
    }

    /**
     * Ajout d'une tondeuse sur le terrain.
     *
     * @param mower tondeuse a ajouter
     */
    public boolean addMower(final Mower mower) {
        Preconditions.checkNotNull(mower);
        final Point point = mower.getPoint();
        Preconditions.checkNotNull(point);
        final boolean add = isInAndEmpty(mower.getPoint());
        if (add) {
            mowers.put(point, mower);
        }
        return add;
    }

    /**
     * On bouge la tondeuse sur le point.
     *
     * @param current la tondeuse que l'on doit bouger
     */
    public void move(Mower current) {
        Preconditions.checkNotNull(current);
        final Point optionPoint = current.getPoint();
        final Point nextPoint = current.translate();
        //on verifie que l'on peut ou l'on veut mettre la tondeuse est dedans
        if (isInAndEmpty(nextPoint) && current.equals(mowers.get(optionPoint))) {
            mowers.remove(optionPoint);
            mowers.put(nextPoint, current);
            current.setPoint(nextPoint);
        }
    }

    /**
     * Verifie que le point est contenu dans la grille et qu'il n'y a pas une tondeuse dessus.
     *
     * @param point le point.
     * @return true la place est libre, false sinon.
     */
    private boolean isInAndEmpty(Point point) {
        return super.isIn(point) && !mowers.containsKey(point);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("mowers", mowers).toString();
    }

    public Map<Point, Mower> getMowers() {
        return Collections.unmodifiableMap(mowers);
    }
}

