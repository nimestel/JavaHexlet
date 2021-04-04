package io.hexlet.xo.model;

public class Game {
    final private Player[] players;
    final private Field field;
    final private String name;

    Game(Builder builder) {
        this.field = builder.field;
        this.players = builder.players;
        this.name = builder.name;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Field getField() {
        return field;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private Player[] players;
        private Field field;
        private String name = "X x O game";

        public Builder players(Player[] players) {
            this.players = players;
            return this;
        }

        public Builder field(Field field) {
            this.field = field;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }
}
