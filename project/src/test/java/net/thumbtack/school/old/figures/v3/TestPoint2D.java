package net.thumbtack.school.old.figures.v3;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestPoint2D {

    @Test
    public void testPoint2D() {
        Point2D point = new Point2D(10, 20);
        assertEquals(10, point.getX());
        assertEquals(20, point.getY());
    }

    @Test
    public void testPoint2D_1() {
        Point2D point = new Point2D();
        assertEquals(0, point.getX());
        assertEquals(0, point.getY());
    }

    @Test
    public void testSetXPoint2D() {
        Point2D point = new Point2D();
        point.setX(100);
        assertEquals(100, point.getX());
        assertEquals(0, point.getY());
    }

    @Test
    public void testSetYPoint2D() {
        Point2D point = new Point2D();
        point.setY(100);
        assertEquals(0, point.getX());
        assertEquals(100, point.getY());
    }
    @Test
    public void testMoveToPoint2D() {
        Point2D point = new Point2D(10,10);
        point.moveTo(20,30);
        assertEquals(20,point.getX());
        assertEquals(30, point.getY());
    }
    @Test
    public void testMoveRelPoint2D() {
        Point2D point = new Point2D(10,10);
        point.moveRel(20,30);
        assertEquals(30,point.getX());
        assertEquals(40, point.getY());
    }



    @Test
    public void testEqualsPoint2() {
        Point2D point = new Point2D();
        Point2D point1 = new Point2D(0,0);
        Point2D point2 = new Point2D(10,10);
        Point2D point3 = new Point2D(10,0);
        Point2D point4 = new Point2D(0,10);
        assertEquals(point, point1);
        assertNotEquals(point, point2);
        assertNotEquals(point2, point3);
        assertNotEquals(point2, point4);
    }

}