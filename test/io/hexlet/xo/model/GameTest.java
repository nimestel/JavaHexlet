package io.hexlet.xo.model;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    @Test
    void getField() {
        Game game = new Game.Builder().field(new Field(3)).build();
        Field expected = new Field(3);
        Field actual = game.getField();

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void getPlayers() {
        Player player1 = new Player("qwe", Figure.X);
        Player player2 = new Player("rty", Figure.O);

        Game game = new Game.Builder()
                .field(new Field(3))
                .players(new Player[]{player1,player2}).build();

        Player[] actual = game.getPlayers();
        Player[] expected = new Player[]{player1,player2};

        int i = game.getField().getSize() - 1;
        while (i-- > 0) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void getNameDefault() {
        Game game = new Game.Builder().build();
        String expected = "X x O game";
        String actual = game.getName();

        assertEquals(expected, actual);
    }

    @Test
    void getNameCustom() {
        String input = getRandomString(10);

        Game game = new Game.Builder().name(input).build();
        String expected = input;
        String actual = game.getName();

        assertEquals(expected, actual);
    }

    public String getRandomString(int length) {
        byte[] array = new byte[10];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}