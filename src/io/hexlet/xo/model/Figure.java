package io.hexlet.xo.model;

public enum Figure {
    X("X"), O("O");

    final String figure;

    Figure(String figure) {
        this.figure = figure;
    }

    @Override
    public String toString() {
        return "Figure{"+ figure +'}';
    }
}
