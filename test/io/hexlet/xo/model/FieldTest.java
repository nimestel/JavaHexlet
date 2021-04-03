package io.hexlet.xo.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void getSize() {
        final Field field = new Field();

        assertEquals(3, field.getSize());
    }

    @Test
    void setFigure() {
        final Field field = new Field();
        final Point point = new Point(0, 0);
        final Figure figure = Figure.O;

        field.setFigure(point, figure);
        final Figure actualFigure = field.getFigure(point);
        final Figure expectedFigure = Figure.O;

        assertEquals(expectedFigure, actualFigure);

    }
}