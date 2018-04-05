package net.thumbtack.school.old.figures.v1;

public class Cylinder extends Circle {

    private int height;

    public Cylinder(Point2D center, int radius, int height){
        super(center,radius);
        this.height = height;
    }
    public Cylinder(int xCenter, int yCenter, int radius, int height){
        this(new Point2D(xCenter,yCenter),radius,height);
    }

    public Cylinder(int radius, int height){
        this(0,0,radius,height);
    }
    public Cylinder(){
        this(0,0,1,1);
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
}
