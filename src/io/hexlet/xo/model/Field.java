package io.hexlet.xo.model;

import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.awt.*;
import java.util.Arrays;

public class Field<T> {
    private static final int MIN_COORDINATE = 0;
    private final T[][] field;
    private final int fieldSize;

    public Field(int fieldSize) {
        this.fieldSize = fieldSize;
        field = (T[][])new Object[fieldSize][fieldSize];
    }

    public int getMinCoordinate() {
        return MIN_COORDINATE;
    }

    public int getMaxCoordinate() {
        return getSize();
    }

    public int getSize() {
        return fieldSize;
    }

    public T getFigure(final Point point) throws InvalidPointException {
        if (!checkPoint(point)) throw new InvalidPointException();
        return field[point.x][point.y];
    }

    public void setFigure(final Point point,
                          T figure)
            throws InvalidPointException {
        if (!checkPoint(point)) {
            throw new InvalidPointException();
        }

        field[point.x][point.y] = figure;
    }

    private boolean checkPoint(Point point) {
        return checkCoordinate(point.x) && checkCoordinate(point.y);
    }

    private boolean checkCoordinate(final int coordinate) {
        return coordinate >= getMinCoordinate() && coordinate < getMaxCoordinate();
    }

    @Override
    public String toString() {
        return "Field{" +
                "field=" + Arrays.deepToString(field) +
                '}';
    }
}
