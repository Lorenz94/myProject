package net.thumbtack.school.old.figures.v3;

import net.thumbtack.school.old.colors.Color;
import net.thumbtack.school.old.colors.ColorException;

import java.util.Objects;

public class Circle extends Figure {
    private Point2D center;
    private int radius;


    public Circle(Point2D center, int radius, Color color) throws ColorException {
        super(color); // вызовет Color конструктор в Figure
        this.center = center;
        this.radius = radius;
    }

    public Circle(Point2D center, int radius, String color) throws ColorException {
        this(center,radius,Color.colorFromString(color));
    }

    public Circle(int xCenter, int yCenter, int radius, Color color) throws ColorException {
        this(new Point2D(xCenter,yCenter),radius, color);
    }
    public Circle(int xCenter, int yCenter, int radius, String color) throws ColorException {
        this(xCenter,yCenter,radius,Color.colorFromString(color));
    }

    public Circle(int radius, Color color) throws ColorException {
        this(0,0,radius, color);
    }
    public Circle(int radius, String color) throws ColorException {
        this(0,0,radius, color);
    }

    public Circle(Color color) throws ColorException {
        this(0,0,1, color);
    }

    public Circle(String color) throws ColorException {
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
