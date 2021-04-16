package io.hexlet.xo.view;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.awt.*;

public interface ICoordinateGetter {

    Point getMoveCoordinate(Field<Figure> field, Figure figure) throws InvalidPointException;
}