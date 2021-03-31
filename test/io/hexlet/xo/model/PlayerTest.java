package io.hexlet.xo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getName() {
        final String input = "Test";
        final String expected = input;

        final Player player = new Player(input, null);
        final String actual = player.getName();

        assertEquals(expected, actual);
    }

    @Test
    void getFigure() {
        final Figure input = Figure.X;
        final Figure expected = input;

        final Player player = new Player(null, input);
        final Figure actual = player.getFigure();

        assertEquals(expected, actual);
    }
}