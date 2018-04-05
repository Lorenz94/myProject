package net.thumbtack.school.old.figures.v3;

import net.thumbtack.school.old.colors.Color;
import net.thumbtack.school.old.colors.ColorException;

public class CircleFactory {

    private static int circleCount=0;

    private static void incrementCircleCount() {
        circleCount++;
    }

    public static Circle createCircle(Point2D point, int radius, Color color) throws ColorException {
        incrementCircleCount();
        return new Circle(point,radius,color);
    }
    public static Circle createCircle(Point2D point, int radius, String color) throws ColorException {
        return createCircle(point,radius,Color.colorFromString(color));
    }

    public static int getCircleCount() {
        return circleCount;
    }
    public static void reset(){
        circleCount = 0;
    }
}

