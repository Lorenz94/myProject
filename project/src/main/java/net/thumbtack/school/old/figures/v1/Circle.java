package net.thumbtack.school.old.figures.v1;

import java.util.Objects;

public class Circle {
    private Point2D center;
    private int radius;

    public Circle(Point2D center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Circle(int xCenter, int yCenter, int radius) {
        this(new Point2D(xCenter,yCenter),radius);
    }

    public Circle(int radius) {
        this(0,0,radius);
    }

    public Circle() {
        this(1);
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

    public void moveRel(int dx, int dy){
        center.moveRel(dx,dy);
    }

    public void enlarge(int n){
        radius *= n;
    }

    public double getArea(){
       return Math.PI * Math.pow(radius,2);
    }

    public double getPerimeter(){
        return radius * Math.PI * 2;
    }

    public boolean isInside(int x, int y){
        double distance = Math.sqrt(Math.pow(getxCenter() - x,2) + Math.pow(getyCenter() - y,2));
        return Math.pow(distance,2) - Math.pow(radius,2) <= 0;
    }

    public boolean isInside(Point2D point){
        return isInside(point.getX(),point.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return getxCenter() == circle.getxCenter() &&
                getyCenter() == circle.getyCenter() &&
                radius == circle.radius &&
                Objects.equals(center, circle.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, getxCenter(), getyCenter(), radius);
    }
}
