package net.thumbtack.school.old.figures.v3;

import net.thumbtack.school.old.colors.Color;
import net.thumbtack.school.old.colors.ColorException;

import java.util.Objects;

public class Cylinder extends Circle {
    private int height;

    public Cylinder(Point2D center, int radius, int height, Color color) throws ColorException {
        super(center,radius,color);
        this.height = height;
    }
    public Cylinder(Point2D center, int radius, int height, String color) throws ColorException {
        this(center,radius,height,Color.colorFromString(color));
    }


    public Cylinder(int xCenter, int yCenter, int radius, int height,Color color) throws ColorException {
        this(new Point2D(xCenter,yCenter),radius,height,color);
    }
    public Cylinder(int xCenter, int yCenter, int radius, int height, String color) throws ColorException {
        this(xCenter,yCenter,radius,height,Color.colorFromString(color));
    }

    public Cylinder(int radius, int height, Color color) throws ColorException {
        this(0,0,radius,height,color);
    }
    public Cylinder(int radius, int height, String color) throws ColorException {
        this(radius,height,Color.colorFromString(color));
    }

    public Cylinder(Color color) throws ColorException {
        this(0,0,1,1,color);
    }
    public Cylinder(String color) throws ColorException {
        this(Color.colorFromString(color));
    }

    public int getHeight(){
        return height;
    }

    public double getVolume(){
        return getArea() * getHeight();
    }

    public boolean isInside(int x, int y, int z){
        return isInside(x,y) && (0 <= z && z <= getHeight());
    }

    public boolean isInside(Point3D point){
        return isInside(point.getX(),point.getY(),point.getZ());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cylinder cylinder = (Cylinder) o;
        return height == cylinder.height;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), height);
    }
}
