package io.hexlet.xo.controllers;

import io.hexlet.xo.controllers.WinnerController;
import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exceptions.InvalidPointException;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WinnerControllerTest {

    private final int fieldSize = 3;

    @Test
    void getWinner_WhenField_IsEmpty() {
        Field field = new Field(fieldSize);
        WinnerController wc = new WinnerController();
        Figure winner = wc.getWinner(field);

        assertNull(winner);
    }

    @Test
    void getWinner_WhenWinner_InRow() {
        WinnerController wc = new WinnerController();
        Figure figure = Figure.O;

        for (int i = 0; i < fieldSize; i++) {
            Field field = new Field(fieldSize);
            try {
                for (int j = 0; j < fieldSize; j++) {
                    field.setFigure(new Point(i, j), figure);
                }
            } catch (InvalidPointException e) {
                e.printStackTrace();
            }

            Figure winner = wc.getWinner(field);
            System.out.println(field.toString());
            assertEquals(figure, winner);
        }
    }

    @Test
    void getWinner_WhenOtherFigure_InBeginOfRow() {
        WinnerController wc = new WinnerController();
        Figure figure = Figure.O;
        Field field = new Field(fieldSize);

        try {
            int row = 0;
            for (int j = 0; j < fieldSize; j++) {
                field.setFigure(new Point(row, j), figure);
            }
            field.setFigure(new Point(row, 0), Figure.X);
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }

        Figure winner = wc.getWinner(field);
        System.out.println(field.toString());
        assertNull(winner);
    }

    @Test
    void getWinner_WhenOtherFigure_InMiddleOfRow() {
        WinnerController wc = new WinnerController();
        Figure figure = Figure.O;
        Field field = new Field(fieldSize);

        try {
            int row = 1;
            for (int j = 0; j < fieldSize; j++) {
                field.setFigure(new Point(row, j), figure);
            }
            field.setFigure(new Point(row, fieldSize - 2), Figure.X);
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }

        Figure winner = wc.getWinner(field);
        System.out.println(field.toString());
        assertNull(winner);
    }

    @Test
    void getWinner_WhenOtherFigure_InEndOfRow() {
        WinnerController wc = new WinnerController();
        Figure figure = Figure.O;
        Field field = new Field(fieldSize);

        try {
            int row = 1;
            for (int j = 0; j < fieldSize; j++) {
                field.setFigure(new Point(row, j), figure);
            }
            field.setFigure(new Point(row, fieldSize - 1), Figure.X);
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }

        Figure winner = wc.getWinner(field);
        System.out.println(field.toString());
        assertNull(winner);
    }

    @Test
    void getWinner_WhenWinner_InColumn() {
        WinnerController wc = new WinnerController();
        Figure figure = Figure.O;

        for (int i = 0; i < fieldSize; i++) {
            Field field = new Field(fieldSize);
            try {
                for (int j = 0; j < fieldSize; j++) {
                    field.setFigure(new Point(j, i), figure);
                }
            } catch (InvalidPointException e) {
                e.printStackTrace();
            }

            Figure winner = wc.getWinner(field);
            System.out.println(field.toString());
            assertEquals(figure, winner);
        }
    }

    @Test
    void getWinner_WhenOtherFigure_InBeginOfColumn() {
        WinnerController wc = new WinnerController();
        Figure figure = Figure.O;
        Field field = new Field(fieldSize);

        try {
            int row = 0;
            for (int j = 0; j < fieldSize; j++) {
                field.setFigure(new Point(j, row), figure);
            }
            field.setFigure(new Point(row, 0), Figure.X);
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }

        Figure winner = wc.getWinner(field);
        System.out.println(field.toString());
        assertNull(winner);
    }

    @Test
    void getWinner_WhenOtherFigure_InMiddleOfColumn() {
        WinnerController wc = new WinnerController();
        Figure figure = Figure.O;
        Field field = new Field(fieldSize);

        try {
            int row = 1;
            for (int j = 0; j < fieldSize; j++) {
                field.setFigure(new Point(j, row), figure);
            }
            field.setFigure(new Point(fieldSize - 2, row), Figure.X);
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }

        Figure winner = wc.getWinner(field);
        System.out.println(field.toString());
        assertNull(winner);
    }

    @Test
    void getWinner_WhenOtherFigure_InEndOfColumn() {
        WinnerController wc = new WinnerController();
        Figure figure = Figure.O;
        Field field = new Field(fieldSize);

        try {
            int row = 1;
            for (int j = 0; j < fieldSize; j++) {
                field.setFigure(new Point(j, row), figure);
            }
            field.setFigure(new Point(fieldSize - 1, row), Figure.X);
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }

        Figure winner = wc.getWinner(field);
        System.out.println(field.toString());
        assertNull(winner);
    }

    @Test
    void getWinner_WhenWinner_InDiagonal1() {
        WinnerController wc = new WinnerController();
        Figure figure = Figure.O;
        Field field = new Field(fieldSize);

        int end = field.getSize() - 1;

        for (int start = 0; start < field.getSize(); start++) {
            final Point p = new Point();
            p.x = start;
            p.y = end;

            try {
                field.setFigure(p, figure);
            } catch (InvalidPointException e) {
                e.printStackTrace();
                fail();
            }
            end--;
        }

        Figure winner = wc.getWinner(field);
        System.out.println(field.toString());
        assertEquals(figure, winner);
    }

    @Test
    void getWinner_WhenOtherFigure_InDiagonal1() {
        WinnerController wc = new WinnerController();
        Figure figure = Figure.O;
        Field field = new Field(fieldSize);

        int end = field.getSize() - 1;

        for (int start = 0; start < field.getSize(); start++) {
            final Point p = new Point();
            p.x = start;
            p.y = end;

            try {
                field.setFigure(p, figure);
            } catch (InvalidPointException e) {
                e.printStackTrace();
                fail();
            }
            end--;
        }
        try {
            field.setFigure(new Point(0, fieldSize - 1), Figure.X);
        } catch (InvalidPointException e) {
            e.printStackTrace();
            fail();
        }

        Figure winner = wc.getWinner(field);
        System.out.println(field.toString());
        assertNull(winner);
    }

    @Test
    void getWinner_WhenWinner_InDiagonal2() {
        WinnerController wc = new WinnerController();
        Figure figure = Figure.O;
        Field field = new Field(fieldSize);

        for (int i = 0; i < field.getSize(); i++) {
            final Point p = new Point();
            p.x = i;
            p.y = i;

            try {
                field.setFigure(p, figure);
            } catch (InvalidPointException e) {
                e.printStackTrace();
                fail();
            }
        }

        Figure winner = wc.getWinner(field);
        System.out.println(field.toString());
        assertEquals(figure, winner);
    }


    @Test
    void getWinner_WhenOtherFigure_InDiagonal2() {
        WinnerController wc = new WinnerController();
        Figure figure = Figure.O;
        Field field = new Field(fieldSize);

        for (int i = 0; i < field.getSize(); i++) {
            final Point p = new Point();
            p.x = i;
            p.y = i;

            try {
                field.setFigure(p, figure);
            } catch (InvalidPointException e) {
                e.printStackTrace();
                fail();
            }
        }
        try {
            field.setFigure(new Point(1, 1), Figure.X);
        } catch (InvalidPointException e) {
            e.printStackTrace();
            fail();
        }

        Figure winner = wc.getWinner(field);
        System.out.println(field.toString());
        assertNull(winner);
    }

    @Test
    void check_Point_LessThanNull() {
        WinnerController winnerController = new WinnerController();
        boolean result = winnerController.check(
                new Field(fieldSize),
                new Point(-1, 0),
                p -> new Point(p.x + 1, p.y + 1)
        );
        assertFalse(result);
    }

    @Test
    void check_Point_MoreThanFieldSize() {
        WinnerController winnerController = new WinnerController();
        boolean result = winnerController.check(
                new Field(fieldSize),
                new Point(1, fieldSize + 1),
                p -> new Point(p.x + 1, p.y)
        );
        assertFalse(result);
    }

    @Test
    void check_Winner() {
        WinnerController winnerController = new WinnerController();
        Field field = new Field(fieldSize);

        for (int j = 0; j < fieldSize; j++) {
            try {
                field.setFigure(new Point(j, 0), Figure.X);
            } catch (InvalidPointException e) {
                e.printStackTrace();
                fail();
            }
        }

        boolean result = winnerController.check(
                field,
                new Point(0, 0),
                p -> new Point(p.x + 1, p.y)
        );
        assertTrue(result);
    }
}