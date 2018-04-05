package net.thumbtack.school.old.colors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import net.thumbtack.school.old.cars.Car;
import net.thumbtack.school.old.colors.Color;
import net.thumbtack.school.old.colors.ColorException;
import net.thumbtack.school.old.colors.Colored;
import net.thumbtack.school.old.figures.v3.Circle;
import net.thumbtack.school.old.figures.v3.Figure;
import net.thumbtack.school.old.figures.v3.Point2D;
import net.thumbtack.school.old.figures.v3.Rectangle;
import net.thumbtack.school.old.figures.v3.Triangle;

public class TestColor {

    @Test
    public void testCompareColors() throws ColorException {
        Figure rect = new Rectangle(10, 20, 30, 40, Color.BLUE);
        Figure circle = new Circle(10, 20, 10, Color.BLUE);
        assertEquals(rect.getColor(), circle.getColor());
        circle.setColor(Color.GREEN);
        assertNotEquals(rect.getColor(), circle.getColor());
    }

    @Test
    public void testArrayOfColored() throws ColorException {
        Colored[] coloredObjects = new Colored[4];
        coloredObjects[0] = new Circle(10, 20, 10, Color.BLUE);
        coloredObjects[1] = new Rectangle(10, 20, 30, 40, Color.BLUE);
        coloredObjects[2] = new Triangle(new Point2D(2, 0), new Point2D(-1, 0), new Point2D(0, 2), Color.BLUE);
        coloredObjects[3] = new Car("Tesla", 1500, 400, Color.BLUE);

        for (Colored colored : coloredObjects)
            colored.setColor(Color.GREEN);
        for (Colored colored : coloredObjects)
            assertEquals(Color.GREEN, colored.getColor());
    }

    @Test
    public void testColors() {
        Color[] colors = Color.values();
        assertEquals(3, colors.length);
        assertEquals(0, Color.RED.ordinal());
        assertEquals(1, Color.GREEN.ordinal());
        assertEquals(2, Color.BLUE.ordinal());
    }

    @Test
    public void testColorFromString() throws ColorException {
        try {
            Circle circle = new Circle(10, 20, 10, "YELLOW");
            fail();
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.WRONG_COLOR_STRING, ex.getErrorCode());
        }
        try {
            Rectangle rect = new Rectangle(10, 20, 10,10, (String) null);
            fail();
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.NULL_COLOR, ex.getErrorCode());
        }
    }

    @Test
    public void testSetMessageColorError(){
        try {
            Circle circle = new Circle(10, 20, 10, "YELLOW");
            fail();
        } catch (ColorException ex) {
            ColorErrorCode.WRONG_COLOR_STRING.setErrorString("WRONG_COLOR_STRING_ERROR");
            assertEquals("WRONG_COLOR_STRING_ERROR", ColorErrorCode.WRONG_COLOR_STRING.getErrorString());
        }
        try {
            Circle circle = new Circle(10, 20, 10, "YELLOW");
            fail();
        } catch (ColorException ex) {
            ColorErrorCode.NULL_COLOR.setErrorString("NULL_COLOR_ERROR");
            assertEquals("NULL_COLOR_ERROR", ColorErrorCode.NULL_COLOR.getErrorString());
        }
    }

}
