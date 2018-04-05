package net.thumbtack.school.old.figures.v2;

import java.util.Objects;

public class Triangle extends Figure {
    private Point2D point1;
    private Point2D point2;
    private Point2D point3;

    public Triangle(Point2D point1, Point2D point2, Point2D point3, int color) {
        super(color);
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    public Point2D getPoint1() {
        return point1;
    }

    public void setPoint1(Point2D point1) {
        this.point1 = point1;
    }

    public Point2D getPoint2() {
        return point2;
    }

    public void setPoint2(Point2D point2) {
        this.point2 = point2;
    }

    public Point2D getPoint3() {
        return point3;
    }

    public void setPoint3(Point2D point3) {
        this.point3 = point3;
    }

    private double getSide12(){
        return Math.sqrt((point1.getX() - point2.getX()) * (point1.getX() - point2.getX()) +
                (point1.getY() - point2.getY()) * (point1.getY() - point2.getY()));
    }
    private double getSide13(){
        return Math.sqrt((point1.getX() - point3.getX()) * (point1.getX() - point3.getX()) +
                (point1.getY() - point3.getY()) * (point1.getY() - point3.getY()));
    }
    private double getSide23(){
        return Math.sqrt((point2.getX() - point3.getX()) * (point2.getX() - point3.getX()) +
                (point2.getY() - point3.getY()) * (point2.getY() - point3.getY()));
    }

    @Override
    public void moveRel(int dx, int dy){
        point1.moveRel(dx,dy);
        point2.moveRel(dx,dy);
        point3.moveRel(dx,dy);
    }

    @Override
    public double getArea(){
        double a = getSide12();
        double b = getSide13();
        double c = getSide23();
        return Math.sqrt((((a+b+c)/2)*((a+b+c)/2-a)*((a+b+c)/2-b)*((a+b+c)/2-c)));
    }

    @Override
    public double getPerimeter(){
        return getSide12() + getSide13() + getSide23();
    }

    @Override
    public boolean isInside(int x, int y){
        double aX = point1.getX(); double aY = point1.getY();
        double bX = point2.getX(); double bY = point2.getY();
        double cX = point3.getX(); double cY = point3.getY();

        double d1 = (aX - x) * (bY - aY) - (bX - aX) * (aY - y);
        double d2 = (bX - x) * (cY - bY) - (cX - bX) * (bY - y);
        double d3 = (cX - x) * (aY - cY) - (aX - cX) * (cY - y);

        return (d1 >= 0 && d2 >= 0 && d3 >= 0) || (d1 <= 0 && d2 <= 0 && d3 <= 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(point1, triangle.point1) &&
                Objects.equals(point2, triangle.point2) &&
                Objects.equals(point3, triangle.point3);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), point1, point2, point3);
    }
}
