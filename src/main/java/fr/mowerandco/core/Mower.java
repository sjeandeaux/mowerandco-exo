package fr.mowerandco.core;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import fr.mowerandco.core.plan.Orientation;
import fr.mowerandco.core.plan.Point;

/**
 * La tondeuse.
 */
public class Mower {

    /**
     * Le nombre de points cardinaux.
     */
    private static final int NB_ORIENTATION = Orientation.values().length;

    /** la position de la tondeuse sur le terrain */
    private Point point;
    /** l'orientation de la tondeuse. */
    private Orientation orientation;


    public Mower(final Point point, final Orientation orientation) {
        Preconditions.checkNotNull(point);
        Preconditions.checkNotNull(orientation);
        this.point = point;
        this.orientation = orientation;
    }


    /**
     * On tourne a droite la tondeuse.
     */
    public void turnRight() {
        //we add position and size of table of Enum.
        final int currentPosition = orientation.ordinal() + NB_ORIENTATION + 1;
        this.orientation = Orientation.values()[(currentPosition) % NB_ORIENTATION];
    }

    /**
     * On tourne a gauche la tondeuse.
     */
    public void turnLeft() {
        //we add position and size of table of Enum.
        final int currentPosition = orientation.ordinal() + NB_ORIENTATION - 1;
        this.orientation = Orientation.values()[(currentPosition) % NB_ORIENTATION];
    }

    /**
     * Calcule le point de destination en fonction de l'orientation de la tondeuse.
     *
     * @return le point de destination
     */
    public Point translate() {
        final Point nextPoint;
        switch (orientation) {
            case NORTH:
                nextPoint = point.translate(0, 1);
                break;
            case EAST:
                nextPoint = point.translate(1, 0);
                break;
            case SOUTH:
                nextPoint = point.translate(0, -1);
                break;
            case WEST:
                nextPoint = point.translate(-1, 0);
                break;
            default:
                throw new IllegalStateException(String.format("%s you translate a orientation"));
        }
        return nextPoint;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("point", point).add("orientation", orientation).toString();
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
