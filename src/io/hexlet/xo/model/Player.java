package io.hexlet.xo.model;

import java.util.Objects;

public class Player {

    private final String name;
    private final Figure figure;

    public Player(final String name, final Figure figure) {
        this.name = name;
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public Figure getFigure() {
        return figure;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", figure=" + figure +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name) && figure == player.figure;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, figure);
    }
}
