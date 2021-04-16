package io.hexlet.xo.controller;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.awt.*;


public class WinnerController {

    public Figure getWinner(final Field<Figure> field) {
        Figure winner;

        try {
            winner = haveWinnerInVertical(field);
            if (winner != null) {
                return winner;
            }
            winner = haveWinnerInHorizontal(field);
            if (winner != null) {
                return winner;
            }
            winner = haveWinnerInDiagonal1(field);
            if (winner != null) {
                return winner;
            }
            winner = haveWinnerInDiagonal2(field);
            if (winner != null) {
                return winner;
            }
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }
        return null;
    }

    Figure haveWinnerInVertical(Field<Figure> field)
            throws InvalidPointException {
        for (int row = 0; row < field.getSize(); row++) {

            if (check(field, new Point(row, 0),
                    p -> new Point(p.x, p.y + 1)))
                return field.getFigure(new Point(row, 0));
        }
        return null;
    }

    Figure haveWinnerInHorizontal(Field<Figure> field)
            throws InvalidPointException {
        for (int column = 0; column < field.getSize(); column++) {

            if (check(field, new Point(0, column),
                    p -> new Point(p.x + 1, p.y)))
                return field.getFigure(new Point(0, column));
        }
        return null;
    }


    Figure haveWinnerInDiagonal1(Field<Figure> field)
            throws InvalidPointException {
        if (check(field, new Point(0, field.getSize() - 1),
                p -> new Point(p.x + 1, p.y - 1)))
            return field.getFigure(new Point(1, 1));

        return null;
    }

    Figure haveWinnerInDiagonal2(Field<Figure> field)
            throws InvalidPointException {
        if (check(field, new Point(0, 0),
                p -> new Point(p.x + 1, p.y + 1)))
            return field.getFigure(new Point(0, 0));

        return null;
    }

    boolean check(final Field<Figure> field,
                          final Point currentPoint,
                          final IPointGenerator pointGenerator) {

        return recursiveCheck(0, field, currentPoint, pointGenerator);
    }

    boolean recursiveCheck(int counter, final Field<Figure> field,
                           final Point currentPoint,
                           final IPointGenerator pointGenerator) {
        final Figure currentFigure;
        final Figure nextFigure;
        final Point nextPoint = pointGenerator.next(currentPoint);

        try {
            currentFigure = field.getFigure(currentPoint);

            if (currentFigure == null) return false;

            nextFigure = field.getFigure(nextPoint);
            if (currentFigure != nextFigure) return false;

        } catch (InvalidPointException e) {
            return (currentPoint.getX() == field.getMaxCoordinate() - 1||
                    currentPoint.getY() == field.getMaxCoordinate() - 1) && counter > 0;
        }

        return recursiveCheck(counter + 1, field, nextPoint, pointGenerator);
    }

    /**
     * Возвращает следующее значение поля, согласно реализованной при вызове
     * логике
     */
    interface IPointGenerator {

        Point next(final Point point);
    }
}
