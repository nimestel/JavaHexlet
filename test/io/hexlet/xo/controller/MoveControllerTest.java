package io.hexlet.xo.controller;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exceptions.InvalidPointException;
import io.hexlet.xo.model.exceptions.NullFigureException;
import io.hexlet.xo.model.exceptions.PointAlreadyOccupiedException;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MoveControllerTest {

    @Test
    void placeFigureWhenFieldOccupied()
            throws InvalidPointException {
        Field field = new Field(3);
        Point point = new Point(0, 0);
        Figure figure = Figure.O;
        MoveController mc = new MoveController();

        field.setFigure(point, figure);

        assertThrows(PointAlreadyOccupiedException.class, () ->
                mc.placeFigure(field, point, figure));
    }

    @Test
    void placeFigureWhenFieldIncorrect() {
        Field field = new Field(3);
        Point point = new Point(-1 , 0);
        Figure figure = Figure.O;
        MoveController mc = new MoveController();

        assertThrows(InvalidPointException.class, () ->
                mc.placeFigure(field, point, figure));
    }

    @Test
    void placeFigureWhenFigureIsNull() {
        Field field = new Field(3);
        Point point = new Point(0 , 0);
        MoveController mc = new MoveController();

        assertThrows(NullFigureException.class, () ->
                mc.placeFigure(field, point, null));
    }

    @Test
    void placeFigureWhenFieldFree()
            throws
            InvalidPointException,
            PointAlreadyOccupiedException,
            NullFigureException {
        Field field = new Field(3);
        Point point = new Point(1, 1);
        Figure figure = Figure.O;
        MoveController mc = new MoveController();

        Figure before = field.getFigure(point);
        assertNull(before);

        mc.placeFigure(field, point, figure);

        Figure after = field.getFigure(point);
        assertEquals(figure, after);
    }
}