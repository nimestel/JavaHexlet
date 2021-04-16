package io.hexlet.xo;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Game;
import io.hexlet.xo.model.Player;
import io.hexlet.xo.view.ConsoleView;

import java.util.Scanner;

public class XOCLI {

    public static void main(final String[] args) {
        final String name1 = inputPlayerName(1);
        final String name2 = inputPlayerName(2);

        final Player[] players = new Player[]{
                new Player(name1, Figure.X),
                new Player(name2, Figure.O)
        };

        final Field<Figure> field = new Field<>(3);

        final Game<Figure> game = new Game.Builder<Figure>()
                .players(players)
                .field(field)
                .name("XO-XO-XO")
                .build();

        final ConsoleView consoleView = new ConsoleView();

        consoleView.printGameName(game);
        while(consoleView.move(game)){
            consoleView.show(game);
        }

    }

    static String inputPlayerName(final int count) {
        Scanner sc = new Scanner(System.in);
        System.out.format("Enter Player %s name: ", count);
        return sc.nextLine();
    }
}
