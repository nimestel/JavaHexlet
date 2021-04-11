package io.hexlet.xo;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Game;
import io.hexlet.xo.model.Player;
import io.hexlet.xo.view.ConsoleView;

public class XOCLI {

    public static void main(final String[] args) {
        final String name1 = "Tanya";
        final String name2 = "Sasha";

        final Player[] players = new Player[]{
                new Player(name1, Figure.X),
                new Player(name2, Figure.O)
        };

        final Field field = new Field(3);

        final Game game = new Game.Builder()
                .players(players)
                .field(field)
                .name("XO-XO-XO")
                .build();

        final ConsoleView consoleView = new ConsoleView();

        while(consoleView.move(game)){
            consoleView.show(game);
        }
    }
}
