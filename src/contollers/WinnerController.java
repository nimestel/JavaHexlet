package contollers;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.awt.*;
import java.util.Arrays;

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

            int lineNumber = row;
            Figure[] figures = new Figure[field.getSize()];

            for (int column = 0; column < field.getSize(); column++) {
                final Point p = new Point();
                p.x = lineNumber;
                p.y = column;

                Figure currentFigure = field.getFigure(p);
                if (currentFigure == null) break;
                figures[column] = currentFigure;
            }

            boolean isWinner = Arrays.stream(figures).allMatch(
                    figure -> figure == figures[0] && figures[0] != null);

            if (isWinner) {
                return figures[0];
            }
        }

        return null;
    }

    private Figure haveWinnerInHorizontal(Field field)
            throws InvalidPointException {
        for (int column = 0; column < field.getSize(); column++) {

            int columnNumber = column;
            Figure[] figures = new Figure[field.getSize()];

            for (int row = 0; row < field.getSize(); row++) {
                final Point p = new Point();
                p.x = row;
                p.y = columnNumber;

                Figure currentFigure = field.getFigure(p);
                if (currentFigure == null) break;
                figures[row] = currentFigure;
            }

            boolean isWinner = Arrays.stream(figures).allMatch(
                    figure -> figure == figures[0] && figures[0] != null);

            if (isWinner) {
                return figures[0];
            }
        }
        return null;
    }


    private Figure haveWinnerInDiagonal1(Field field)
            throws InvalidPointException {
        Figure[] figures = new Figure[field.getSize()];

        int end = field.getSize() - 1;

        for (int start = 0; start < field.getSize(); start++) {
            final Point p = new Point();
            p.x = start;
            p.y = end;

            Figure currentFigure = field.getFigure(p);
            if (currentFigure == null) break;
            figures[start] = currentFigure;
            System.out.print(figures[start]);

            end--;
        }
        System.out.println();

        boolean isWinner = Arrays.stream(figures)
                .allMatch(figure -> figure == figures[0] && figures[0] != null);

        if (isWinner) {
            return figures[0];
        }

        return null;
    }

    private Figure haveWinnerInDiagonal2(Field field)
            throws InvalidPointException {
        Figure[] figures = new Figure[field.getSize()];

        for (int column = 0; column < field.getSize(); column++) {
            for (int row = field.getSize() - 1; row >= 0; row--) {
                final Point p = new Point();
                p.x = row;
                p.y = column;

                if (row == column) {
                    Figure currentFigure = field.getFigure(p);
                    if (currentFigure == null) break;
                    figures[column] = currentFigure;
                }
            }
        }

        boolean isWinner = Arrays
                .stream(figures)
                .allMatch(figure -> figure == figures[0] && figures[0] != null);

        if (isWinner) {
            return figures[0];
        }

        return null;
    }
}
