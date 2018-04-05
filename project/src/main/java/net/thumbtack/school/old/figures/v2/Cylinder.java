package net.thumbtack.school.old.figures.v2;

public class Cylinder extends Circle {
    private int height;

    public Cylinder(Point2D center, int radius, int height, int color){
        super(center,radius,color);
        this.height = height;
    }
    public Cylinder(int xCenter, int yCenter, int radius, int height,int color){
        this(new Point2D(xCenter,yCenter),radius,height,color);
    }

    public Cylinder(int radius, int height, int color){
        this(0,0,radius,height,color);
    }
    public Cylinder(int color){
        this(0,0,1,1,color);
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
