package io.hexlet.xo.controllers;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.awt.*;

public class CurrentMoveController {

    int countX = 0;
    int countO = 0;

    public Figure currentMove(final Field field) {
        countFigures(field);
        if(countX > countO && countX+countO < field.getSize()*field.getSize()) return Figure.O;
        if(countX == countO && countX != 0) return Figure.X;
        if(countX == 0 && countO == 0) return Figure.X;
        return null;
    }

     void countFigures(Field field) {
        countX = 0;
        countO = 0;

        for (int i = 0; i < field.getSize(); i++) {
            for (int j = 0; j < field.getSize(); j++) {
                Point point = new Point(i, j);
                try {
                    Figure figure = field.getFigure(point);
                    if (Figure.O.equals(figure)) {
                        countO++;
                    }
                    if (Figure.X.equals(figure)) {
                        countX++;
                    }
                } catch (InvalidPointException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
