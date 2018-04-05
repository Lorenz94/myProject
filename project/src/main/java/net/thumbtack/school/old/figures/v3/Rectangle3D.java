package net.thumbtack.school.old.figures.v3;

import net.thumbtack.school.old.colors.Color;
import net.thumbtack.school.old.colors.ColorException;

import java.util.Objects;

public class Rectangle3D extends Rectangle {
    private int height;

    public Rectangle3D(Point2D leftTop, Point2D rightBottom, int height, Color color) throws ColorException {
        super(leftTop, rightBottom,color);
        this.height = height;
    }
    public Rectangle3D(Point2D leftTop, Point2D rightBottom, int height, String color) throws ColorException {
        this(leftTop,rightBottom,height,Color.colorFromString(color));
    }

    public Rectangle3D(int xLeft, int yTop, int xRight, int yBottom, int height, Color color) throws ColorException {
        super(xLeft, yTop, xRight, yBottom, color);
        this.height = height;
    }
    public Rectangle3D(int xLeft, int yTop, int xRight, int yBottom, int height, String color) throws ColorException {
        this(xLeft,yTop,xRight,yBottom,height,Color.colorFromString(color));
    }

    public Rectangle3D(int length, int width, int height,Color color) throws ColorException {
        super(length, width,color);
        this.height = height;
    }
    public Rectangle3D(int length, int width, int height,String color) throws ColorException {
        this(length,width,height,Color.colorFromString(color));
    }

    public Rectangle3D(Color color) throws ColorException {
        this(1, 1, 1,color);
    }
    public Rectangle3D(String color) throws ColorException {
        this(Color.colorFromString(color));
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
        return isInside(x, y) && (0 <= z && z <= getHeight());
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

