package io.hexlet.xo.view;

import io.hexlet.xo.controller.CurrentMoveController;
import io.hexlet.xo.controller.MoveController;
import io.hexlet.xo.controller.WinnerController;
import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Game;
import io.hexlet.xo.model.Player;
import io.hexlet.xo.model.exceptions.InvalidPointException;
import io.hexlet.xo.model.exceptions.NullFigureException;
import io.hexlet.xo.model.exceptions.PointAlreadyOccupiedException;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {

    final String SELECTOR_ROW = "\n~~~~~~~~~~~";
    final String SELECTOR_COLUMN = "|";

    private final CurrentMoveController currentMoveController = new CurrentMoveController();
    private final WinnerController winnerController = new WinnerController();
    private final MoveController moveController = new MoveController();

    public void show(Game<Figure> game) {

        final Field<Figure> field = game.getField();
        for (int i = 0; i < field.getMaxCoordinate(); i++) {
            for (int j = 0; j < field.getMaxCoordinate(); j++) {

                Point point = new Point(i, j);
                Figure figure = null;
                try {
                    figure = field.getFigure(point);
                } catch (InvalidPointException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                printCell(figure);

                if (j == field.getMaxCoordinate() - 1) break;
                printSelectorColumn();
            }
            if (i == field.getMaxCoordinate() - 1) {
                System.out.println();
                break;
            }
            printSelectorRow();
        }
    }

    public void printGameName(Game<Figure> game) {
        System.out.format("Game: %s\n", game.getName());
    }

    private void printCell(Figure figure) {
        System.out.printf(" %s ", figure != null ? figure : " ");
    }

    private void printSelectorColumn() {
        System.out.print(SELECTOR_COLUMN);
    }

    private void printSelectorRow() {
        System.out.println(SELECTOR_ROW);
    }

    Point askPoint() {
        return new Point(askCoordinate("X") - 1, askCoordinate("Y") - 1);
    }

    int askCoordinate(final String coordinateName) {
        System.out.printf("\nPlease input coordinate %s: ", coordinateName);
        final Scanner in = new Scanner(System.in);
        try {
            return in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input! Please retry");
            return askCoordinate(coordinateName);
        }

    }

    public boolean move(Game<Figure> game) {
        final Field<Figure> field = game.getField();

        Figure winner = winnerController.getWinner(field);
        if (winner != null) {
            System.out.printf("\n%s wins!\n", winner);
            for (Player player : game.getPlayers()) {
                if (winner.equals(player.getFigure()))
                    System.out.printf("Congrats %s!", player.getName());
            }
            return false;
        }

        final Figure currentFigure = currentMoveController.currentMove(field);
        if (currentFigure == null) {
            System.out.println("\nNo one wins!\n");
            return false;
        }

        System.out.printf("\nTime to move for %s!", currentFigure);
        final Point point = askPoint();
        try {
            moveController.placeFigure(field, point, currentFigure);
        } catch (final InvalidPointException e) {
            System.out.println("Attention! Point is not valid!\n");
        } catch (final PointAlreadyOccupiedException e) {
            System.out.println("Attention! Point is already occupied!\n");
        } catch (final NullFigureException e) {
            System.out.println("Attention! Figure not defined!\n");
        }
        return true;
    }
}
