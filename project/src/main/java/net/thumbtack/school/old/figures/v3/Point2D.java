package net.thumbtack.school.old.figures.v3;

import java.io.Serializable;

public class Point2D {

	private int x, y;

	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point2D() {
		this(0, 0);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void moveTo(int newX, int newY) {
		x = newX;
		y = newY;
	}

	public void moveRel(int dx, int dy) {
		x += dx;
		y += dy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Point2D point2D = (Point2D) o;
		return x == point2D.x &&
				y == point2D.y;
	}
}