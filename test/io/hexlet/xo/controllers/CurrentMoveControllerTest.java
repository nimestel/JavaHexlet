package io.hexlet.xo.controllers;

import io.hexlet.xo.controllers.CurrentMoveController;
import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exceptions.InvalidPointException;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CurrentMoveControllerTest {

    int fieldSize = 3;

    @Test
    void currentMove_WhenFieldEmpty() {
        Field field = new Field(fieldSize);
        CurrentMoveController currentMoveController = new CurrentMoveController();

        Figure currentFigure = currentMoveController.currentMove(field);
        assertEquals(Figure.X, currentFigure);
    }

    @Test
    void currentMove_When_OneX() {
        Field field = new Field(fieldSize);
        CurrentMoveController currentMoveController = new CurrentMoveController();

        try {
            field.setFigure(new Point(0,0), Figure.X);
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }

        Figure currentFigure = currentMoveController.currentMove(field);
        assertEquals(Figure.O, currentFigure);
    }

    @Test
    void currentMove_When_OneXOneO() {
        Field field = new Field(fieldSize);
        Figure figure = Figure.X;
        CurrentMoveController currentMoveController = new CurrentMoveController();

        try {
            field.setFigure(new Point(0,0), Figure.X);
            field.setFigure(new Point(0,1), Figure.O);
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }

        Figure currentFigure = currentMoveController.currentMove(field);
        assertEquals(Figure.X, currentFigure);
    }

    @Test
    void currentMove_When_TwoXOneO() {
        Field field = new Field(fieldSize);
        Figure figure = Figure.X;
        CurrentMoveController currentMoveController = new CurrentMoveController();

        try {
            field.setFigure(new Point(1,0), Figure.X);
            field.setFigure(new Point(0,1), Figure.O);
            field.setFigure(new Point(2,2), Figure.X);
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }

        Figure currentFigure = currentMoveController.currentMove(field);
        assertEquals(Figure.O, currentFigure);
    }

    @Test
    void currentMove_WhenField_AllOccupied() {
        Field field = new Field(fieldSize);
        Figure figure;
        CurrentMoveController currentMoveController = new CurrentMoveController();

        int counter = 0;
        for (int i = 0; i < field.getSize(); i++) {
            for (int j = 0; j < field.getSize(); j++) {
                Point point = new Point(i, j);
                try {
                    if (counter % 2 == 0) figure = Figure.X;
                            else figure = Figure.O;
                    field.setFigure(point, figure);
                    counter++;
                } catch (InvalidPointException e) {
                    e.printStackTrace();
                }
            }
        }

        Figure currentFigure = currentMoveController.currentMove(field);
        assertNull(currentFigure);
    }

    @Test
    void currentMove_SeveralSteps() {
        Field field = new Field(fieldSize);
        CurrentMoveController currentMoveController = new CurrentMoveController();

        try {
            Figure currentFigure;

            currentFigure = currentMoveController.currentMove(field);
            assertEquals(Figure.X, currentFigure);

            field.setFigure(new Point(0,0), Figure.X);

            currentFigure = currentMoveController.currentMove(field);
            assertEquals(Figure.O, currentFigure);

            field.setFigure(new Point(0,1), Figure.O);

            currentFigure = currentMoveController.currentMove(field);
            assertEquals(Figure.X, currentFigure);

            field.setFigure(new Point(0,2), Figure.X);

            currentFigure = currentMoveController.currentMove(field);
            assertEquals(Figure.O, currentFigure);
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }
    }

    @Test
    void countFigures_WhenFieldEmpty() {
        Field field = new Field(3);
        CurrentMoveController currentMoveController = new CurrentMoveController();

        int expectedCountO = 0;
        int expectedCountX = 0;

        currentMoveController.countFigures(field);

        assertEquals(expectedCountO, currentMoveController.countO);
        assertEquals(expectedCountX, currentMoveController.countX);
    }

    @Test
    void countFigures_When_OneX() {
        Field field = new Field(3);
        Figure figure = Figure.X;
        CurrentMoveController currentMoveController = new CurrentMoveController();

        int expectedCountO = 0;
        int expectedCountX = 1;

        try {
            field.setFigure(new Point(0,0), figure);
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }
        currentMoveController.countFigures(field);

        assertEquals(expectedCountO, currentMoveController.countO);
        assertEquals(expectedCountX, currentMoveController.countX);
    }

    @Test
    void countFigures_When_TwoXOneO() {
        Field field = new Field(3);
        CurrentMoveController currentMoveController = new CurrentMoveController();

        int expectedCountO = 1;
        int expectedCountX = 2;

        try {
            field.setFigure(new Point(0,0), Figure.X);
            field.setFigure(new Point(1,1), Figure.O);
            field.setFigure(new Point(2,2), Figure.X);
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }
        currentMoveController.countFigures(field);

        assertEquals(expectedCountO, currentMoveController.countO);
        assertEquals(expectedCountX, currentMoveController.countX);
    }


    @Test
    void countFigures_WhenField_AllOccupied() {
        Field field = new Field(3);
        Figure figure;
        CurrentMoveController currentMoveController = new CurrentMoveController();

        int expectedCountO = 4;
        int expectedCountX = 5;

        int counter = 0;
        for (int i = 0; i < field.getSize(); i++) {
            for (int j = 0; j < field.getSize(); j++) {
                Point point = new Point(i, j);
                try {
                    if (counter % 2 == 0) figure = Figure.X;
                    else figure = Figure.O;
                    field.setFigure(point, figure);
                    counter++;
                } catch (InvalidPointException e) {
                    e.printStackTrace();
                }
            }
        }

        currentMoveController.countFigures(field);

        assertEquals(expectedCountO, currentMoveController.countO);
        assertEquals(expectedCountX, currentMoveController.countX);
    }

}