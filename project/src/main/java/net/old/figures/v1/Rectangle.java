package net.thumbtack.school.old.figures.v1;

import java.util.Objects;

public class Rectangle {
    private Point2D leftTop;
    private Point2D rightBottom;

    public Rectangle(Point2D leftTop, Point2D rightBottom) {
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom) {
        this(new Point2D(xLeft,yTop),new Point2D(xRight,yBottom));
    }

    public Rectangle(int length, int width) {
        this(0, -width, length, 0);
    }

    public Rectangle() {
        this(1, 1);
    }

    public Point2D getTopLeft() {
        return leftTop;
    }

    public void setLeftTop(Point2D leftTop) {
        this.leftTop = leftTop;
    }

    public Point2D getBottomRight() {
        return rightBottom;
    }

    public int getLength() {
        return getxRight() - getxLeft();
    }

    public int getWidth() {
        return getyBottom() - getyTop();
    }

    public int getxLeft() {
        return leftTop.getX();
    }

    public int getyTop() {
        return leftTop.getY();
    }

    public int getxRight() {
        return rightBottom.getX();
    }

    public int getyBottom() {
        return rightBottom.getY();
    }

    public void moveRel(int dx, int dy) {
        leftTop.moveRel(dx, dy);
        rightBottom.moveRel(dx, dy);
    }

    public void enlarge(int nx, int ny) {
        rightBottom.moveTo(leftTop.getX() + nx * getLength(), leftTop.getY() + ny * getWidth());
    }

    public double getArea() {
        return getLength() * getWidth();
    }

    public double getPerimeter() {
        return (getLength() + getWidth()) * 2;
    }

    public boolean isInside(int x, int y) {
        return (getxLeft() <= x && x <= getxRight()) && (getyTop() <= y && y <= getyBottom());
    }

    public boolean isInside(Point2D point) {
        return isInside(point.getX(),point.getY());
    }

    public boolean isIntersects(Rectangle rectangle) {
        return leftTop.getX() < rectangle.rightBottom.getX() &&
                rightBottom.getX() > rectangle.leftTop.getX() &&
                leftTop.getY() < rectangle.rightBottom.getY() &&
                rightBottom.getY() > rectangle.leftTop.getY();
    }

    public boolean isInside(Rectangle rectangle) {
        return isInside(rectangle.leftTop) && isInside(rectangle.rightBottom);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(leftTop, rectangle.leftTop) &&
                Objects.equals(rightBottom, rectangle.rightBottom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftTop, rightBottom);
    }
}