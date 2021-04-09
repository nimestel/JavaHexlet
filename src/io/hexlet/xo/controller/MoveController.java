package io.hexlet.xo.controller;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exceptions.InvalidPointException;
import io.hexlet.xo.model.exceptions.NullFigureException;
import io.hexlet.xo.model.exceptions.PointAlreadyOccupiedException;

import java.awt.*;

public class MoveController {
    public void placeFigure(Field field,
                            Point point,
                            Figure figure)
            throws
            InvalidPointException,
            PointAlreadyOccupiedException,
            NullFigureException {

        if (field.getFigure(point) != null) {
            throw new PointAlreadyOccupiedException();
        }
        if (figure == null) {
            throw new NullFigureException();
        }
        field.setFigure(point, figure);
    }
}
