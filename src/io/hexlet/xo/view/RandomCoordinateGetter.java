package io.hexlet.xo.view;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.awt.*;
import java.util.Random;

public class RandomCoordinateGetter implements ICoordinateGetter {

    private static final Random RANDOM = new Random();

    public Point getMoveCoordinate(final Field<Figure> field, Figure figure) throws InvalidPointException {
        Point randomPoint = new Point(RANDOM.nextInt(field.getSize()), RANDOM.nextInt(field.getSize()));

        while (field.getFigure(randomPoint) != null) {
            randomPoint = new Point(RANDOM.nextInt(field.getSize()), RANDOM.nextInt(field.getSize()));
        }

        return randomPoint;
    }
}

