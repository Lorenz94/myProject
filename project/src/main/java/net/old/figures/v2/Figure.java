package net.thumbtack.school.old.figures.v2;

import java.util.Objects;

public abstract class Figure implements Colored {
    private int color;

    abstract void moveRel(int dx, int dy);
    abstract double getArea();
    abstract double getPerimeter();
    abstract boolean isInside(int x, int y);

    public Figure(int color) {
        this.color = color;
    }

    public boolean isInside(Point2D point2D) {
        return isInside(point2D.getX(),point2D.getY());
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
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
