package net.thumbtack.school.old.figures.v3;

import net.thumbtack.school.old.colors.Color;
import net.thumbtack.school.old.colors.ColorException;

import java.util.Objects;

public class Rectangle extends Figure {
    private Point2D leftTop;
    private Point2D rightBottom;

    public Rectangle(Point2D leftTop, Point2D rightBottom, Color color) throws ColorException {
        super(Color.colorNotNull(color));
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;

    }
    public Rectangle(Point2D leftTop, Point2D rightBottom, String color) throws ColorException {
        this(leftTop,rightBottom,Color.colorFromString(color));
    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom,Color color) throws ColorException {
        this(new Point2D(xLeft,yTop),new Point2D(xRight,yBottom),color);
    }
    public Rectangle(int xLeft, int yTop, int xRight, int yBottom,String color) throws ColorException {
        this(new Point2D(xLeft,yTop),new Point2D(xRight,yBottom),Color.colorFromString(color));
    }

    public Rectangle(int length, int width,Color color) throws ColorException {
        this(0, -width, length, 0, color);
    }
    public Rectangle(int length, int width,String color) throws ColorException {
        this(0, -width, length, 0, color);
    }

    public Rectangle(Color color) throws ColorException {
        this(1, 1,color);
    }
    public Rectangle(String color) throws ColorException {
        this(Color.colorFromString(color));
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

    public void setRightBottom(Point2D rightBottom) {
        this.rightBottom = rightBottom;
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

    public boolean isIntersects(Rectangle rectangle) {
        return leftTop.getX() < rectangle.rightBottom.getX() &&
                rightBottom.getX() > rectangle.leftTop.getX() &&
                leftTop.getY() < rectangle.rightBottom.getY() &&
                rightBottom.getY() > rectangle.leftTop.getY();
    }

    public boolean isInside(Rectangle rectangle) {
        return isInside(rectangle.leftTop) && isInside(rectangle.rightBottom);
    }

    public void enlarge(int nx, int ny) {
        rightBottom.moveTo(leftTop.getX() + nx * getLength(), leftTop.getY() + ny * getWidth());
    }

    @Override
    public void moveRel(int dx, int dy) {
        leftTop.moveRel(dx, dy);
        rightBottom.moveRel(dx, dy);
    }

    @Override
    public double getArea() {
        return getLength() * getWidth();
    }

    @Override
    public double getPerimeter() {
        return (getLength() + getWidth()) * 2;
    }

    @Override
    public boolean isInside(int x, int y) {
        return (getxLeft() <= x && x <= getxRight()) && (getyTop() <= y && y <= getyBottom());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(leftTop, rectangle.leftTop) &&
                Objects.equals(rightBottom, rectangle.rightBottom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), leftTop, rightBottom);
    }
}