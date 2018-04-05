package net.thumbtack.school.old.figures.v3;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestPoint3D {
    @Test
    public void testPoint3D() {
        Point3D point = new Point3D(10,20,30);
        assertEquals(10, point.getX());
        assertEquals(20, point.getY());
        assertEquals(30, point.getZ());
    }

    @Test
    public void testPoint3D_1() {
        Point3D point = new Point3D(30);
        assertEquals(0, point.getX());
        assertEquals(0, point.getY());
        assertEquals(30, point.getZ());
    }

    @Test
    public void testPoint3D_2() {
        Point3D point = new Point3D();
        assertEquals(0, point.getX());
        assertEquals(0, point.getY());
        assertEquals(0, point.getZ());
    }

    @Test
    public void testSetZPoint3D() {
        Point3D point = new Point3D(10,20,30);
        point.setZ(300);
        assertEquals(300, point.getZ());
    }

    @Test
    public void testMovePoint3D() {
        Point3D point = new Point3D(10,20,30);
        point.move(5,10,15);
        assertEquals(15, point.getX());
        assertEquals(30, point.getY());
        assertEquals(45, point.getZ());
    }
    @Test
    public void testEqualsPoint3D() {
        Point3D point = new Point3D(0,0,30);
        Point3D point1 = new Point3D(30);
        Point3D point2 = new Point3D();
        Point3D point3 = new Point3D(1,1,30);
        assertEquals(point, point1);
        assertNotEquals(point1, point2);
        assertNotEquals(point,point3);
    }

}