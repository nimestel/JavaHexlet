package io.hexlet.xo.view;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.awt.*;
import java.util.Arrays;

public class AICoordinateGetter implements ICoordinateGetter {

    Figure oppositeFigure = Figure.O;

    @Override
    public Point getMoveCoordinate(Field<Figure> field, Figure figure) throws InvalidPointException {
        this.oppositeFigure = figure;
        Point p = new Point(1, 1);
        if (field.getFigure(p) == null) {
            return p;
        }

        p = getCoordinateIfIAlmostWin(field);
        if (p != null) {
            System.out.printf("Я думаю что выигрываю на позиции %s", p);
            return p;
        }

        p = getCoordinateIfOtherAlmostWin(field);
        if (p != null) {
            System.out.printf("Я думаю что враг выигрывает на позиции %s", p);
            return p;
        }

        p = getOppositeCorner(field);
        if (p != null) {
            System.out.printf("Иду в противоположный угол %s!", p);
            return p;
        }

        p = new RandomCoordinateGetter().getMoveCoordinate(field, figure);
        System.out.printf("Все равно куда, пойду в %s", p);
        return p;
    }

    private Point getOppositeCorner(Field<Figure> field) throws InvalidPointException {
        int edge = field.getSize() - 1;
        Point corner1 = new Point(0, edge);
        Point corner2 = new Point(0, 0);
        Point corner3 = new Point(edge, 0);
        Point corner4 = new Point(edge, edge);

        if (field.getFigure(corner1) == oppositeFigure && field.getFigure(corner3) == null) {
            return corner3;
        }
        if (field.getFigure(corner3) == oppositeFigure && field.getFigure(corner1) == null) {
            return corner1;
        }
        if (field.getFigure(corner2) == oppositeFigure && field.getFigure(corner4) == null) {
            return corner4;
        }
        if (field.getFigure(corner4) == oppositeFigure && field.getFigure(corner2) == null) {
            return corner2;
        }
        return null;
    }

    private Point getCoordinateIfIAlmostWin(Field<Figure> field) throws InvalidPointException {
        return checkAlmostWinner(field, Figure.X);
    }

    private Point getCoordinateIfOtherAlmostWin(Field<Figure> field) throws InvalidPointException {
        return checkAlmostWinner(field, oppositeFigure);
    }

    private Point checkAlmostWinner(Field<Figure> field, Figure figure) throws InvalidPointException {
        System.out.println();
        System.out.printf("Проверяю возможность победы %s", figure);
        System.out.println();

        if (checkColumn(field, figure) != null) {
            return checkColumn(field, figure);
        }
        if (checkRow(field, figure) != null) {
            return checkRow(field, figure);
        }
        if (checkDiagonal1(field, figure) != null) {
            return checkDiagonal1(field, figure);
        }
        if (checkDiagonal2(field, figure) != null) {
            return checkDiagonal2(field, figure);
        }
        return null;
    }

    private Point checkColumn(Field<Figure> field, Figure targetFigure) throws InvalidPointException {
        for (int row = 0; row < field.getSize(); row++) {

            Figure[] figures = new Figure[field.getSize()];
            Point firstNullPointCoordinateInLine = null;

            for (int column = 0; column < field.getSize(); column++) {
                final Point p = new Point(row, column);

                System.out.printf("Ищу символы %s по столбцам в столбце %d строке %d ", targetFigure, column, row);
                System.out.println();

                if (checkOppositeFigureInLine(field, targetFigure, p)) continue;
                figures[column] = field.getFigure(p);

                firstNullPointCoordinateInLine = setFirstNullPointInLine(figures, firstNullPointCoordinateInLine, column, p);
            }

            if (isAlmostWinInLine(figures, targetFigure)) {
                System.out.printf("Возвращаю координату %s", firstNullPointCoordinateInLine);
                return firstNullPointCoordinateInLine;
            }
        }
        return null;

    }

    private Point checkRow(Field<Figure> field, Figure targetFigure) throws InvalidPointException {
        for (int column = 0; column < field.getSize(); column++) {

            Figure[] figures = new Figure[field.getSize()];
            Point firstNullPointCoordinateInLine = null;

            for (int row = 0; row < field.getSize(); row++) {
                final Point p = new Point(row, column);

                System.out.printf("Ищу символы %s по строкам в столбце %d строке %d ", targetFigure, column, row);
                System.out.println();

                if (checkOppositeFigureInLine(field, targetFigure, p)) continue;
                figures[row] = field.getFigure(p);

                firstNullPointCoordinateInLine = setFirstNullPointInLine(figures, firstNullPointCoordinateInLine, row, p);
            }

            if (isAlmostWinInLine(figures, targetFigure)) {
                System.out.printf("Возвращаю координату %s", firstNullPointCoordinateInLine);
                return firstNullPointCoordinateInLine;
            }
        }
        return null;
    }

    private Point checkDiagonal1(Field<Figure> field, Figure targetFigure) throws InvalidPointException {
        Figure[] figures = new Figure[field.getSize()];

        int end = field.getSize() - 1;
        Point firstNullPointCoordinateInLine = null;

        for (int start = 0; start < field.getSize(); start++) {
            final Point p = new Point(start, end);

            System.out.printf("Ищу символы %s по диагонали 1 в столбце %d строке %d ", targetFigure, start, end);
            System.out.println();

            if (checkOppositeFigureInLine(field, targetFigure, p)) return null;
            figures[start] = field.getFigure(p);

            firstNullPointCoordinateInLine = setFirstNullPointInLine(figures, firstNullPointCoordinateInLine, start, p);

            end--;
        }

        if (isAlmostWinInLine(figures, targetFigure)) {
            System.out.printf("Возвращаю координату %s", firstNullPointCoordinateInLine);
            return firstNullPointCoordinateInLine;
        }
        return null;
    }


    private Point checkDiagonal2(Field<Figure> field, Figure targetFigure) throws InvalidPointException {
        Figure[] figures = new Figure[field.getSize()];
        Point firstNullPointCoordinateInLine = null;

        for (int column = 0; column < field.getSize(); column++) {
            for (int row = field.getSize() - 1; row >= 0; row--) {
                final Point p = new Point(row, column);

                if (row == column) {

                    System.out.printf("Ищу символы %s по диагонали 2 в столбце %d строке %d ", targetFigure, row, column);
                    System.out.println();

                    if (checkOppositeFigureInLine(field, targetFigure, p)) return null;
                    figures[column] = field.getFigure(p);

                    firstNullPointCoordinateInLine = setFirstNullPointInLine(figures, firstNullPointCoordinateInLine, column, p);
                }
            }
        }

        if (isAlmostWinInLine(figures, targetFigure)) {
            System.out.printf("Возвращаю координату %s", firstNullPointCoordinateInLine);
            System.out.println();
            return firstNullPointCoordinateInLine;
        }
        return null;
    }

    private boolean checkOppositeFigureInLine(Field<Figure> field, Figure targetFigure, Point p)
            throws InvalidPointException {
        if (!(targetFigure.equals(field.getFigure(p))) && (field.getFigure(p) != null)) {
            System.out.printf("Попался вражеский символ %s", field.getFigure(p));
            System.out.println();
            return true;
        }
        return false;
    }

    private boolean isAlmostWinInLine(Figure[] figures, Figure targetFigure) {
        boolean almostWinner = Arrays.stream(figures).allMatch(figure -> figure == targetFigure || figure == null);

        System.out.printf("Проверяю возможность выиграть для линии %s",
                Arrays.deepToString(figures));
        System.out.println();

        int nullCount = countNullsInLine(figures);
        if (almostWinner && nullCount == 1) {
            System.out.println("Успех!");
            return true;
        }
        return false;
    }

    private Point setFirstNullPointInLine(Figure[] figures, Point firstNullPointCoordinateInLine, int column, Point p) {
        if (figures[column] == null && firstNullPointCoordinateInLine == null) firstNullPointCoordinateInLine = p;
        return firstNullPointCoordinateInLine;
    }

    /**
     * Убедиться, что в строке не более 1 null
     */
    private int countNullsInLine(Figure[] figures) {
        int nullCount = 0;
        int i = 0;
        while(nullCount <= 1 && i < figures.length) {
            if (figures[i++] == null) nullCount++;
        }
        System.out.printf("Найдено в строке null - %d", nullCount);
        System.out.println();
        return nullCount;
    }
}
