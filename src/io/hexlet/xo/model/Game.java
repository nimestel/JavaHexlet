package io.hexlet.xo.model;

public class Game<F> {
    final private Player[] players;
    final private Field<F> field;
    final private String name;

    Game(Builder<F> builder) {
        this.field = builder.field;
        this.players = builder.players;
        this.name = builder.name;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Field<F> getField() {
        return field;
    }

    public String getName() {
        return name;
    }

    public static class Builder<F> {
        private Player[] players;
        private Field<F> field;
        private String name = "X x O game";

        public Builder<F> players(Player[] players) {
            this.players = players;
            return this;
        }

        public Builder<F> field(Field<F> field) {
            this.field = field;
            return this;
        }

        public Builder<F> name(String name) {
            this.name = name;
            return this;
        }

        public Game<F> build() {
            return new Game<>(this);
        }
    }
}
