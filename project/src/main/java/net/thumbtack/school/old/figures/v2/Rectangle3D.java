package net.thumbtack.school.old.figures.v2;

import java.util.Objects;

public class Rectangle3D extends Rectangle {
    private int height;

    public Rectangle3D(Point2D leftTop, Point2D rightBottom, int height, int color) {
        super(leftTop, rightBottom,color);
        this.height = height;
    }

    public Rectangle3D(int xLeft, int yTop, int xRight, int yBottom, int height, int color) {
        super(xLeft, yTop, xRight, yBottom, color);
        this.height = height;
    }

    public Rectangle3D(int length, int width, int height,int color) {
        super(length, width,color);
        this.height = height;
    }

    public Rectangle3D(int color) {
        this(1, 1, 1,color);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getVolume() {
        return getArea() * height;
    }

    public boolean isInside(int x, int y, int z) {
        return isInside(x, y) && (0 < z && z < getHeight());
    }

    public boolean isInside(Point3D point) {
        return isInside(point.getX(), point.getY(), point.getZ());
    }

    public boolean isInside(Rectangle3D rectangle) {
        return isInside(rectangle.getTopLeft()) && isInside(rectangle.getBottomRight()) && (getHeight() >= rectangle.getHeight());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rectangle3D that = (Rectangle3D) o;
        return height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), height);
    }
}

