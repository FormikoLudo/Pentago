package fr.formiko.pentago.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Board {
    public static final int SIZE = 6;
    private Map<Point, State> cells;

    public Board() {
        cells = new HashMap<>();

    }

    public boolean place(Point point, State state) {
        if (getState(point) == State.EMPTY) {
            add(point, state);
            return true;
        } else {
            return false;
        }
    }

    public void add(Point point, State state) {
        if (state == State.EMPTY) {
            remove(point);
        } else {
            cells.put(point, state);
        }
    }

    public void remove(Point point) { cells.remove(point); }

    public State getState(Point point) { return cells.getOrDefault(point, State.EMPTY); }

    public List<Point> getAllPoints() {
        // return a list of points with size SIZE*SIZE
        List<Point> points = new LinkedList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                points.add(new Point(i, j));
            }
        }
        return points;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(getState(new Point(i, j)));
                if (j % 3 == 2) {
                    sb.append("   ");
                }
            }
            if (i % 3 == 2) {
                sb.append("\n");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public void rotate(RotatingDirection rotatingDirection, BoardLocation boardLocation) {
        int deltaX = 0;
        int deltaY = 0;
        switch (boardLocation) {
            case NORTH_EAST:
                deltaX = 3;
                break;
            case SOUTH_EAST:
                deltaX = 3;
                deltaY = 3;
                break;
            case SOUTH_WEST:
                deltaY = 3;
                break;
            default:
                break;
        }
        List<Point> corners = List.of(new Point(0 + deltaX, 0 + deltaY), new Point(0 + deltaX, 2 + deltaY),
                new Point(2 + deltaX, 2 + deltaY), new Point(2 + deltaX, 0 + deltaY), new Point(0 + deltaX, 0 + deltaY));
        List<Point> borders = List.of(new Point(0 + deltaX, 1 + deltaY), new Point(1 + deltaX, 2 + deltaY),
                new Point(2 + deltaX, 1 + deltaY), new Point(1 + deltaX, 0 + deltaY), new Point(0 + deltaX, 1 + deltaY));
        if (rotatingDirection == RotatingDirection.CLOCK_WHISE) {
            moveEachPointToNewLocation(corners);
            moveEachPointToNewLocation(borders);
        } else {
            moveEachPointToNewLocation(borders.reversed());
            moveEachPointToNewLocation(corners.reversed());
        }
    }

    private void moveEachPointToNewLocation(List<Point> points) {
        State previous = null;
        for (Point corner : points) {
            State current = getState(corner);
            if (previous != null) {
                this.add(corner, previous);
            }
            previous = current;
        }
    }
}
