package io.hexlet.xo.model;

import io.hexlet.xo.model.exceptions.AbstractXOException;
import io.hexlet.xo.model.exceptions.InvalidPointException;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void getSize() {
        final Field<Figure> field = new Field<>(3);

        assertEquals(3, field.getSize());
    }

    @Test
    void setFigure() throws AbstractXOException {
        final Field<Figure> field = new Field<>(3);
        final Point point = new Point(0, 0);
        final Figure figure = Figure.O;

        field.setFigure(point, figure);
        final Figure actualFigure = field.getFigure(point);
        final Figure expectedFigure = Figure.O;

        assertEquals(expectedFigure, actualFigure);
    }

    @Test
    void getFigureWhenFigureIsNotSet() throws AbstractXOException {
        final Field<Figure> field = new Field<>(3);
        final Point point = new Point(0, 0);

        final Figure actualFigure = field.getFigure(point);

        assertNull(actualFigure);
    }

    @Test
    void getFigureWhenXIsLessThanZero() throws AbstractXOException {
        final Field<Figure> field = new Field<>(3);
        final Point point = new Point(-1, 0);

        try {
            field.getFigure(point);
            fail();
        } catch (final InvalidPointException e) {

        }
    }

    @Test
    void getFigureWhenXIsMoreThanFieldSize() throws AbstractXOException {
        final Field<Figure> field = new Field<>(3);
        final Point point = new Point(field.getSize() + 1, 0);

        try {
            field.getFigure(point);
            fail();
        } catch (final InvalidPointException e) {

        }
    }

    @Test
    void getFigureWhenYIsLessThanZero() throws AbstractXOException {
        final Field<Figure> field = new Field<>(3);
        final Point point = new Point(0, -1);

        try {
            field.getFigure(point);
            fail();
        } catch (final InvalidPointException e) {

        }
    }

    @Test
    void getFigureWhenYIsMoreThanFieldSize() throws AbstractXOException {
        final Field<Figure> field = new Field<>(3);
        final Point point = new Point(0, field.getSize() + 1);

        try {
            field.getFigure(point);
            fail();
        } catch (final InvalidPointException e) {

        }
    }

    @Test
    void setFigureWhenXIsLessThanZero() throws AbstractXOException {
        final Field<Figure> field = new Field<>(3);
        final Point point = new Point(-1, 0);
        final Figure figure = Figure.X;

        try {
            field.setFigure(point, figure);
            fail();
        } catch (final InvalidPointException e) {

        }
    }

    @Test
    void setFigureWhenXIsMoreThanFieldSize() throws AbstractXOException {
        final Field<Figure> field = new Field<>(3);
        final Point point = new Point(field.getSize() + 1, 0);
        final Figure figure = Figure.X;

        try {
            field.setFigure(point, figure);
            fail();
        } catch (final InvalidPointException e) {

        }
    }

    @Test
    void setFigureWhenYIsLessThanZero() throws AbstractXOException {
        final Field<Figure> field = new Field<>(3);
        final Point point = new Point(0, -1);
        final Figure figure = Figure.X;

        try {
            field.setFigure(point, figure);
            fail();
        } catch (final InvalidPointException e) {

        }
    }

    @Test
    void setFigureWhenYIsMoreThanFieldSize() throws AbstractXOException {
        final Field<Figure> field = new Field<>(3);
        final Point point = new Point(0, field.getSize() + 1);
        final Figure figure = Figure.X;

        try {
            field.setFigure(point, figure);
            fail();
        } catch (final InvalidPointException e) {

        }
    }
}