package net.thumbtack.school.old.figures.v2;

public class CircleFactory {

    private static int circleCount=0;

    private static void incrementCircleCount() {
        circleCount++;
    }

    public static Circle createCircle(Point2D point, int radius,int color) {
        incrementCircleCount();
        return new Circle(point,radius,color);
    }

    public static int getCircleCount() {
        return circleCount;
    }
    public static void reset(){
        circleCount = 0;
    }
}

