package net.thumbtack.school.old.figures.v1;

public class CircleFactory {

    private static int circleCount=0;


    private static void incrementCircleCount() {
        circleCount++;
    }

    public static Circle createCircle(Point2D point, int radius) {
        incrementCircleCount();
        return new Circle(point,radius);
    }

    public static int getCircleCount() {
        return circleCount;
    }
    public static void reset(){
        circleCount = 0;
    }
}

