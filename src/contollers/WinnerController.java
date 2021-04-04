package contollers;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.awt.*;


public class WinnerController {

    public Figure getWinner(final Field field) {
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

    private Figure haveWinnerInVertical(Field field)
            throws InvalidPointException {
        for (int row = 0; row < field.getSize(); row++) {

            if (check(field, new Point(row, 0),
                    p -> new Point(p.x, p.y + 1)))
                return field.getFigure(new Point(row, 0));
        }
        return null;
    }

    private Figure haveWinnerInHorizontal(Field field)
            throws InvalidPointException {
        for (int column = 0; column < field.getSize(); column++) {

            if (check(field, new Point(0, column),
                    p -> new Point(p.x + 1, p.y)))
                return field.getFigure(new Point(0, column));
        }
        return null;
    }


    private Figure haveWinnerInDiagonal1(Field field)
            throws InvalidPointException {
        if (check(field, new Point(0, field.getSize()),
                p -> new Point(p.x + 1, p.y - 1)))
            return field.getFigure(new Point(1, 1));

        return null;
    }

    private Figure haveWinnerInDiagonal2(Field field)
            throws InvalidPointException {
        if (check(field, new Point(0, 0),
                p -> new Point(p.x + 1, p.y + 1)))
            return field.getFigure(new Point(0, 0));

        return null;
    }

    private boolean check(final Field field,
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
            e.printStackTrace();
        }

        if (currentPoint.getX() == field.getMaxCoordinate() - 1 ||
                currentPoint.getY() == field.getMaxCoordinate() - 1)
            return true;

        return check(field, nextPoint, pointGenerator);
    }

    /**
     * Возвращает следующее значение поля, согласно реализованной при вызове
     * логике
     */
    private interface IPointGenerator {

        Point next(final Point point);
    }
}
