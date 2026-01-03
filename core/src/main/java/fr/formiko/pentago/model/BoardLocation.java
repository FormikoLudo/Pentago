package fr.formiko.pentago.model;

public enum BoardLocation {
    NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST;

    public static BoardLocation fromPoint(Point point) {
        if (point.x() < 3) {
            if (point.y() < 3) {
                return NORTH_WEST;
            } else {
                return SOUTH_WEST;
            }
        } else {
            if (point.y() < 3) {
                return NORTH_EAST;
            } else {
                return SOUTH_EAST;
            }
        }
    }
}
