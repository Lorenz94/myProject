package net.thumbtack.school.old.figures.v3;

import net.thumbtack.school.old.area.HasArea;
import net.thumbtack.school.old.colors.Color;
import net.thumbtack.school.old.colors.ColorException;
import net.thumbtack.school.old.colors.Colored;

import java.io.Serializable;
import java.util.Objects;

public abstract class Figure implements Colored, HasArea{
    private  Color color;

    abstract void moveRel(int dx, int dy);
    abstract double getPerimeter();
    abstract boolean isInside(int x, int y);

    public Figure(Color color) throws ColorException {
        setColor(color);
    }

    public Figure(String color) throws ColorException {
        this(Color.colorFromString(color));
    }

    public boolean isInside(Point2D point2D) {
        return isInside(point2D.getX(),point2D.getY());
    }

    public void setColor(Color color) throws ColorException {
        this.color = Color.colorNotNull(color);
    }

    @Override
    public Color getColor() {
        return color;
    }

    public void setColor(String color) throws ColorException{
        this.color = Color.colorFromString(color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return color == figure.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
