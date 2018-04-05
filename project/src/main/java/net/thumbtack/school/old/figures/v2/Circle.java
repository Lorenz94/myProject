package net.thumbtack.school.old.figures.v2;

import java.util.Objects;

public class Circle extends Figure{
    private Point2D center;
    private int radius;

    public Circle(Point2D center, int radius, int color) {
        super(color);
        this.center = center;
        this.radius = radius;
    }

    public Circle(int xCenter, int yCenter, int radius, int color) {
        this(new Point2D(xCenter,yCenter),radius, color);
    }

    public Circle(int radius, int color) {
        this(0,0,radius, color);
    }

    public Circle(int color) {
        this(0,0,1, color);
    }

    public int getxCenter() {
        return center.getX();
    }

    public int getyCenter() {
        return center.getY();
    }

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void enlarge(int n){
        radius *= n;
    }

    @Override
    public void moveRel(int dx, int dy){
        center.moveRel(dx,dy);
    }

    @Override
    public double getArea(){
       return Math.PI * Math.pow(radius,2);
    }

    @Override
    public double getPerimeter(){
        return radius * Math.PI * 2;
    }

    @Override
    public boolean isInside(int x, int y){
        double distance = Math.sqrt(Math.pow(getxCenter() - x,2) + Math.pow(getyCenter() - y,2));
        return Math.pow(distance,2) - Math.pow(radius,2) <= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Circle circle = (Circle) o;
        return radius == circle.radius &&
                Objects.equals(center, circle.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), center, radius);
    }
}
